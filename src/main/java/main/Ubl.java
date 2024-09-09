package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;


public class Ubl {
	
	private static Log log = LogFactory.getLog(Ubl.class);

	public static void main(String[] args) {
		System.out.println("hola mundo");
		generarXMLZipiadoBoleta();
	}
	
	public static String generarXMLZipiadoBoleta() {
		log.info("generarXMLZipiadoBoleta - Inicializamos el ambiente");
        org.apache.xml.security.Init.init();
        String resultado = "";
        String unidadEnvio; 
        String pathXMLFile;
        try {
            log.info("generarXMLZipiadoBoleta - Extraemos datos para preparar XML ");
            unidadEnvio = "/Users/carlosleon/libre/";
            log.info("generarXMLZipiadoBoleta - Ruita de directorios " + unidadEnvio);

            log.info("generarXMLZipiadoBoleta - Iniciamos cabecera ");

            pathXMLFile = "/Users/carlosleon/libre/20601491193-01-BF006-00004733.xml";
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
            //Parametros del keystore
            String keystoreType = "JKS";
            String keystoreFile = "/Users/carlosleon/libre/MiAlmacen.jks";
            String keystorePass = "miAlmacen";
            String privateKeyAlias = "miAlmacen";
            String privateKeyPass = "miAlmacen";
            String certificateAlias = "miAlmacen";
            
            log.info("generarXMLZipiadoBoleta - Lectura de cerificado ");
            CDATASection cdata;
            log.info("generarXMLZipiadoBoleta - Iniciamos la generacion del XML");
            File signatureFile = new File(pathXMLFile);
            ///////////////////Creación del certificado//////////////////////////////
            KeyStore ks = KeyStore.getInstance(keystoreType);
            FileInputStream fis = new FileInputStream(keystoreFile);
            ks.load(fis, keystorePass.toCharArray());
            //obtener la clave privada para firmar
            PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
            if (privateKey == null) {
                throw new RuntimeException("Private key is null");
            }
            X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);

            javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            //Firma XML genera espacio para los nombres o tag
            dbf.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();
            ////////////////////////////////////////////////// 

            log.info("generar factura paso 1 - cabecera XML ");
            Element envelope = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", "Invoice");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            envelope.appendChild(doc.createTextNode("\n"));
            doc.appendChild(envelope);

            Element UBLExtensions = doc.createElementNS("", "ext:UBLExtensions");
            envelope.appendChild(UBLExtensions);

            //2do grupo
            Element UBLExtension = doc.createElementNS("", "ext:UBLExtension");
            envelope.appendChild(UBLExtension);
            Element ExtensionContent = doc.createElementNS("", "ext:ExtensionContent");
            envelope.appendChild(ExtensionContent);

            //El baseURI es la URI que se utiliza para anteponer a URIs relativos
            String BaseURI = signatureFile.toURI().toURL().toString();
            //Crea un XML Signature objeto desde el documento, BaseURI and signature algorithm (in this case RSA)
            //XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA); Cadena URI que se ajusta a la sintaxis URI y representa el archivo XML de entrada
            XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

            ExtensionContent.appendChild(sig.getElement());
            UBLExtension.appendChild(ExtensionContent);
            UBLExtensions.appendChild(UBLExtension);

            log.info("inicio: generar factura paso 2. Versión del UBL.");
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            envelope.appendChild(UBLVersionID);
            UBLVersionID.appendChild(doc.createTextNode("2.1"));

            log.info("inicio: generar factura paso 3. Versión de la estructura del documento.");
            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            envelope.appendChild(CustomizationID);
            CustomizationID.appendChild(doc.createTextNode("2.0"));

            log.info("inicio: generar factura paso 5. Numeración, conformada por serie y número correlativo.");
            Element ID5 = doc.createElement("cbc:ID");
            envelope.appendChild(ID5);
            ID5.appendChild(doc.createTextNode("BF006-00004733"));

            log.info("inicio: generar factura paso 6. Fecha de emisión."); 
            Element IssueDate = doc.createElement("cbc:IssueDate");
            envelope.appendChild(IssueDate);
            IssueDate.appendChild(doc.createTextNode("2024-08-15"));
            
            log.info("inicio: generar factura paso 7. Hora de emisión."); 
            Element IssueTime = doc.createElement("cbc:IssueTime");
            envelope.appendChild(IssueTime);
            IssueTime.appendChild(doc.createTextNode("14:39:17"));
            
            log.info("inicio: generar factura paso 9 Código de Tipo de documento."); 
            Element InvoiceTypeCode = doc.createElement("cbc:InvoiceTypeCode");
            InvoiceTypeCode.setAttribute("listAgencyName", "PE:SUNAT");
            InvoiceTypeCode.setAttribute("listName", "SUNAT:Identificador de Tipo de Documento");
            InvoiceTypeCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");
            envelope.appendChild(InvoiceTypeCode);
            InvoiceTypeCode.appendChild(doc.createTextNode("01"));
            
            log.info("inicio: generar factura paso legend1. Monto expresado en letras."); 
            Element LegendMonto = doc.createElement("cbc:Note");
            LegendMonto.setAttribute("languageLocaleID", "1000");
            envelope.appendChild(LegendMonto);
            LegendMonto.appendChild(doc.createTextNode("MIL CIENTO OCHENTA CON 00/100 Soles"));
            
            log.info("inicio: generar factura paso legend2. Código interno generado por el software de Facturación."); 
            Element LegendIdSW = doc.createElement("cbc:Note");
            LegendIdSW.setAttribute("languageLocaleID", "3000");
            envelope.appendChild(LegendIdSW);
            LegendIdSW.appendChild(doc.createTextNode("05010020170428000005"));
            
            log.info("inicio: generar factura paso 11 Tipo de moneda."); 
            Element CurrencyCode = doc.createElement("cbc:DocumentCurrencyCode");
            CurrencyCode.setAttribute("listID", "ISO 4217 Alpha");
            CurrencyCode.setAttribute("listName", "Currency");
            CurrencyCode.setAttribute("listAgencyName", "United Nations Economic Commission for Europe");
            envelope.appendChild(CurrencyCode);
            CurrencyCode.appendChild(doc.createTextNode("PEN"));
             
            log.info("inicio: generar factura paso 14 Nombre Comercial."); 
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");
            envelope.appendChild(AccountingSupplierParty);
            AccountingSupplierParty.appendChild(doc.createTextNode("\n"));

            Element Party = doc.createElement("cac:Party");
            AccountingSupplierParty.appendChild(Party);
            Party.appendChild(doc.createTextNode("\n"));

            Element PartyName1 = doc.createElement("cac:PartyName");
            Party.appendChild(PartyName1);
            PartyName1.appendChild(doc.createTextNode("\n"));

            Element Name2 = doc.createElement("cbc:Name");
            PartyName1.appendChild(Name2);
            cdata = doc.createCDATASection("DEPOSITOS Y VENTAS S.A.");
            Name2.appendChild(cdata);
            
            log.info("inicio: generar factura paso 15 Apellidos y nombres o denominación o razón social del emisor.");
            Element PartyTaxScheme = doc.createElement("cbc:PartyTaxScheme");
            PartyName1.appendChild(PartyTaxScheme);
            PartyTaxScheme.appendChild(doc.createTextNode("\n"));
            
            Element NameComplete = doc.createElement("cbc:Name");
            PartyTaxScheme.appendChild(NameComplete);
            cdata = doc.createCDATASection("DEPOSITOS Y VENTAS S.A.");
            NameComplete.appendChild(cdata);
            
            log.info("inicio: generar factura paso 16 Tipo y Número de RUC del Emisor."); 
            Element CompanyID = doc.createElement("cbc:CompanyID");
            CompanyID.setAttribute("schemeID", "6");
            CompanyID.setAttribute("schemeName", "SUNAT:Identificador de Documento de Identidad");
            CompanyID.setAttribute("schemeAgencyName", "PE:SUNAT");
            CompanyID.setAttribute("schemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");
            PartyTaxScheme.appendChild(CompanyID);
            CompanyID.appendChild(doc.createTextNode("20100014476"));

            Element ID = doc.createElement("cac:ID");
            PartyTaxScheme.appendChild(ID);
            ID.appendChild(doc.createTextNode("--"));
         
            log.info("inicio: generar factura paso 16 Tipo y Número de RUC del Emisor."); 
            Element RegistrationAddress = doc.createElement("cbc:RegistrationAddress");
            PartyTaxScheme.appendChild(RegistrationAddress);
            RegistrationAddress.appendChild(doc.createTextNode("\n"));
            
            log.info("inicio: generar factura paso 17 Código del domicilio fiscal o de local anexo del emisor.");
            Element AddressTypeCode = doc.createElement("cac:AddressTypeCode");
            RegistrationAddress.appendChild(AddressTypeCode);
            AddressTypeCode.appendChild(doc.createTextNode("0000"));

            log.info("inicio: generar factura paso 18 Tipo y número de documento de identidad del adquirente o usuario.");  
            Element AccountingCustomerParty = doc.createElement("cac:AccountingCustomerParty");
            envelope.appendChild(AccountingCustomerParty);
            AccountingCustomerParty.appendChild(doc.createTextNode("\n"));

            Element PartyCustomer = doc.createElement("cac:Party");
            AccountingCustomerParty.appendChild(PartyCustomer);
            PartyCustomer.appendChild(doc.createTextNode("\n"));
            
            Element PartyTaxSchemeCustomer = doc.createElement("cac:PartyTaxScheme");
            PartyCustomer.appendChild(PartyTaxSchemeCustomer);
            PartyTaxSchemeCustomer.appendChild(doc.createTextNode("\n"));
            
            Element CompanyIDCustomer = doc.createElement("cbc:CompanyID");
            CompanyIDCustomer.setAttribute("schemeID", "6");
            CompanyIDCustomer.setAttribute("schemeName", "SUNAT:Identificador de Documento de Identidad");
            CompanyIDCustomer.setAttribute("schemeAgencyName", "PE:SUNAT");
            CompanyIDCustomer.setAttribute("schemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");
            PartyTaxSchemeCustomer.appendChild(CompanyIDCustomer);
            CompanyIDCustomer.appendChild(doc.createTextNode("20100113612"));
            
            log.info("inicio: generar factura paso 19 Apellidos y nombres o denominación o razón social del adquirente o usuario");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            PartyTaxSchemeCustomer.appendChild(RegistrationName);
            cdata = doc.createCDATASection("INVERSIONES HERMANOS DAVILA E.I.R.L.");
            RegistrationName.appendChild(cdata);
            
            log.info("inicio: generar factura paso 22 Monto Total de Impuestos.");
            Element TaxTotal = doc.createElement("cac:TaxTotal");
            envelope.appendChild(TaxTotal);
            TaxTotal.appendChild(doc.createTextNode("\n"));
            
            Element TaxAmount = doc.createElement("cbc:TaxAmount");
            TaxAmount.setAttribute("currencyID", "PEN");
            TaxTotal.appendChild(TaxAmount);
            TaxAmount.appendChild(doc.createTextNode("180.00"));
   
            log.info("inicio: generar factura paso 23 Total valor de venta - operaciones gravadas.");
            Element TaxSubtotal = doc.createElement("cac:TaxSubtotal");
            TaxTotal.appendChild(TaxSubtotal);
            TaxSubtotal.appendChild(doc.createTextNode("\n")); 
            
            Element TaxableAmount = doc.createElement("cbc:TaxableAmount");
            TaxableAmount.setAttribute("currencyID", "PEN");
            TaxSubtotal.appendChild(TaxableAmount);
            TaxableAmount.appendChild(doc.createTextNode("1000.00"));
            
            Element TaxAmount1 = doc.createElement("cbc:TaxAmount");
            TaxAmount1.setAttribute("currencyID", "PEN");
            TaxSubtotal.appendChild(TaxAmount1);
            TaxAmount1.appendChild(doc.createTextNode("180.00"));
            
            Element TaxCategory = doc.createElement("cbc:TaxCategory");
            TaxSubtotal.appendChild(TaxCategory);
            TaxCategory.appendChild(doc.createTextNode("\n"));
            
            Element IDTaxCategory = doc.createElement("cbc:ID");
            IDTaxCategory.setAttribute("schemeID", "UN/ECE 5305");
            IDTaxCategory.setAttribute("schemeName", "Tax Category Identifier");
            IDTaxCategory.setAttribute("schemeAgencyName", "United Nations Economic Commission for Europe");
            TaxCategory.appendChild(IDTaxCategory);
            IDTaxCategory.appendChild(doc.createTextNode("S"));
            
            Element TaxSchemeTaxCategory = doc.createElement("cac:TaxScheme");
            TaxCategory.appendChild(TaxSchemeTaxCategory);
            TaxSchemeTaxCategory.appendChild(doc.createTextNode("\n"));
            
            Element IDTaxScheme= doc.createElement("cbc:ID");
            IDTaxScheme.setAttribute("schemeID", "UN/ECE 5153");
            IDTaxScheme.setAttribute("schemeAgencyID", "6");
            TaxSchemeTaxCategory.appendChild(IDTaxScheme);
            IDTaxScheme.appendChild(doc.createTextNode("1000"));
            
            Element NameTaxScheme= doc.createElement("cbc:Name");
            TaxSchemeTaxCategory.appendChild(NameTaxScheme);
            NameTaxScheme.appendChild(doc.createTextNode("IGV"));

            Element TaxTypeCodeTaxScheme= doc.createElement("cbc:TaxTypeCode");
            TaxSchemeTaxCategory.appendChild(TaxTypeCodeTaxScheme);
            TaxTypeCodeTaxScheme.appendChild(doc.createTextNode("VAT"));
            
            log.info("inicio: generar factura paso 30 Total Valor de Venta.");
            Element LegalMonetaryTotal = doc.createElement("cac:LegalMonetaryTotal");
            envelope.appendChild(LegalMonetaryTotal);
            LegalMonetaryTotal.appendChild(doc.createTextNode("\n"));

            Element LineExtensionAmount= doc.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount.setAttribute("currencyID", "PEN");
            LegalMonetaryTotal.appendChild(LineExtensionAmount);
            LineExtensionAmount.appendChild(doc.createTextNode("1000"));
           
            log.info("inicio: generar factura paso 31 Total Precio de Venta.");
            Element TaxInclusiveAmount= doc.createElement("cbc:TaxInclusiveAmount");
            TaxInclusiveAmount.setAttribute("currencyID", "PEN");
            LegalMonetaryTotal.appendChild(TaxInclusiveAmount);
            TaxInclusiveAmount.appendChild(doc.createTextNode("1180"));
            
            log.info("inicio: generar factura paso 34 Importe total de la venta, de la cesión en uso o del servicio prestado."); 
            Element PayableAmount= doc.createElement("cbc:PayableAmount");
            PayableAmount.setAttribute("currencyID", "PEN");
            LegalMonetaryTotal.appendChild(PayableAmount);
            PayableAmount.appendChild(doc.createTextNode("1180"));
            
            log.info("inicio: generar factura paso 35 Número de orden del Ítem."); 
            Element InvoiceLine = doc.createElement("cac:InvoiceLine");
            envelope.appendChild(InvoiceLine);
            InvoiceLine.appendChild(doc.createTextNode("\n"));
            
            Element IDInvoiceLine= doc.createElement("cbc:ID");
            InvoiceLine.appendChild(IDInvoiceLine);
            IDInvoiceLine.appendChild(doc.createTextNode("1"));

            log.info("inicio: generar factura paso 36 Cantidad y Unidad de Medida por ítem."); 
            Element InvoicedQuantity= doc.createElement("cbc:InvoicedQuantity");
            InvoicedQuantity.setAttribute("unitCode", "ZZ");
            InvoicedQuantity.setAttribute("unitCodeListID", "UN/ECE rec 20");
            InvoicedQuantity.setAttribute("unitCodeListAgencyName", "United Nations Economic Commission for Europe");
            InvoiceLine.appendChild(InvoicedQuantity);
            InvoicedQuantity.appendChild(doc.createTextNode("1"));
            
            
            log.info("inicio: generar factura paso 37 Valor de venta por ítem."); 
            Element LineExtensionAmount1= doc.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount1.setAttribute("currencyID", "PEN");
            InvoiceLine.appendChild(LineExtensionAmount1);
            LineExtensionAmount1.appendChild(doc.createTextNode("1000.00"));
            
            log.info("inicio: generar factura paso 38 Precio de venta unitario por ítem y código."); 
            Element PricingReference = doc.createElement("cac:PricingReference");
            envelope.appendChild(PricingReference);
            PricingReference.appendChild(doc.createTextNode("\n"));
            
            Element AlternativeConditionPrice = doc.createElement("cac:AlternativeConditionPrice");
            envelope.appendChild(AlternativeConditionPrice);
            AlternativeConditionPrice.appendChild(doc.createTextNode("\n"));
            
            Element PriceAmount= doc.createElement("cbc:PriceAmount");
            PriceAmount.setAttribute("currencyID", "PEN");
            AlternativeConditionPrice.appendChild(PriceAmount);
            PriceAmount.appendChild(doc.createTextNode("1180.00"));
         
            Element PriceTypeCode= doc.createElement("cbc:PriceTypeCode");
            PriceTypeCode.setAttribute("listName", "SUNAT:Indicador de Tipo de Precio");
            PriceTypeCode.setAttribute("listAgencyName", "PE:SUNAT");
            PriceTypeCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16");
            AlternativeConditionPrice.appendChild(PriceTypeCode);
            PriceTypeCode.appendChild(doc.createTextNode("01"));
            
            
            log.info("inicio: generar factura paso 42 Afectación al IGV por ítem"); 
            Element TaxTotal1 = doc.createElement("cac:TaxTotal");
            envelope.appendChild(TaxTotal1);
            TaxTotal1.appendChild(doc.createTextNode("\n"));
            
            Element TaxAmount2 = doc.createElement("cbc:TaxAmount");
            TaxAmount2.setAttribute("currencyID", "PEN");
            TaxTotal1.appendChild(TaxAmount2);
            TaxAmount2.appendChild(doc.createTextNode("180.00"));
            
            Element TaxSubtotal1 = doc.createElement("cac:TaxSubtotal");
            TaxTotal1.appendChild(TaxSubtotal1);
            TaxSubtotal1.appendChild(doc.createTextNode("\n"));
            
            Element TaxableAmount1 = doc.createElement("cbc:TaxableAmount");
            TaxableAmount1.setAttribute("currencyID", "PEN");
            TaxSubtotal1.appendChild(TaxableAmount1);
            TaxableAmount1.appendChild(doc.createTextNode("1000.00"));

            Element TaxAmount4 = doc.createElement("cbc:TaxAmount");
            TaxAmount4.setAttribute("currencyID", "PEN");
            TaxSubtotal1.appendChild(TaxAmount4);
            TaxAmount4.appendChild(doc.createTextNode("180.00"));

            Element TaxCategory1 = doc.createElement("cac:TaxCategory");
            TaxSubtotal1.appendChild(TaxCategory1);
            TaxCategory1.appendChild(doc.createTextNode("\n"));

            Element ID1 = doc.createElement("cbc:ID");
            ID1.setAttribute("schemeID", "UN/ECE 5305");
            ID1.setAttribute("schemeName", "Tax Category Identifier");
            ID1.setAttribute("schemeAgencyName", "United Nations Economic Commission for Europe");
            TaxCategory1.appendChild(ID1);
            ID1.appendChild(doc.createTextNode("S"));

            Element Percent = doc.createElement("cbc:Percent");
            TaxCategory1.appendChild(Percent);
            Percent.appendChild(doc.createTextNode("18.00"));
            
            Element TaxExemptionReasonCode = doc.createElement("cbc:TaxExemptionReasonCode");
            TaxExemptionReasonCode.setAttribute("listAgencyName", "PE:SUNAT");
            TaxExemptionReasonCode.setAttribute("listName", "SUNAT:Codigo de Tipo de Afectación del IGV");
            TaxExemptionReasonCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07");
            TaxCategory1.appendChild(TaxExemptionReasonCode);
            TaxExemptionReasonCode.appendChild(doc.createTextNode("10"));
            
            Element TaxScheme = doc.createElement("cac:TaxScheme");
            TaxCategory1.appendChild(TaxScheme);
            TaxScheme.appendChild(doc.createTextNode("\n"));
            
            Element ID2 = doc.createElement("cbc:ID");
            TaxScheme.appendChild(ID2);
            ID2.appendChild(doc.createTextNode("1000"));
            
            Element Name = doc.createElement("cbc:Name");
            TaxScheme.appendChild(Name);
            Name.appendChild(doc.createTextNode("IGV"));
            
            Element TaxTypeCode = doc.createElement("cbc:TaxTypeCode");
            TaxScheme.appendChild(TaxTypeCode);
            TaxTypeCode.appendChild(doc.createTextNode("VAT"));
            
            log.info("inicio: generar factura paso 44 Descripción detallada"); 
            Element Item = doc.createElement("cac:Item");
            envelope.appendChild(Item);
            Item.appendChild(doc.createTextNode("\n"));

            Element Description = doc.createElement("cbc:Description");
            Item.appendChild(Description);
            cdata = doc.createCDATASection("CAPTOPRIL 25mg X 30");
            Description.appendChild(cdata);
            
            log.info("inicio: generar factura paso 48 Valor unitario por ítem. "); 
            Element Price = doc.createElement("cac:Price");
            envelope.appendChild(Price);
            Price.appendChild(doc.createTextNode("\n"));
            
            Element PriceAmount1 = doc.createElement("cbc:PriceAmount");
            Price.appendChild(PriceAmount1);
            PriceAmount1.appendChild(doc.createTextNode("1000.00"));
            

            log.info("generarXMLZipiadoBoleta - Prepara firma digital ");
            sig.setId("20601491193");
            sig.addKeyInfo(cert);
            {
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
            }
            {
                //Firmar el documento
                log.info("generarXMLZipiadoBoleta - firma el XML ");
                sig.sign(privateKey);
            }
            
          
        

            //--------------------fin de construccion del xml---------------------
            ///*combinacion de firma y construccion xml////
            FileOutputStream f = new FileOutputStream(signatureFile);
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            tf.setOutputProperty(OutputKeys.STANDALONE, "no");
            StreamResult sr = new StreamResult(f);
            tf.transform(new DOMSource(doc), sr);
            sr.getOutputStream().close();
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "0100|Error al generar el archivo de formato xml de la Boleta.";
            log.error("generarXMLZipiadoBoleta - error  " + ex.toString());
        }
        return resultado;
    }  

}
