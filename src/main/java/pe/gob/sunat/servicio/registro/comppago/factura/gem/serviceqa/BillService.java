
package pe.gob.sunat.servicio.registro.comppago.factura.gem.serviceqa;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pe.gob.sunat.service.ObjectFactory;
import pe.gob.sunat.service.StatusResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "billService", targetNamespace = "http://service.sunat.gob.pe")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BillService {


    /**
     * 
     * @param fileName
     * @param contentFile
     * @return
     *     returns byte[]
     */
    @WebMethod(action = "urn:sendBill")
    @WebResult(name = "applicationResponse", targetNamespace = "")
    @RequestWrapper(localName = "sendBill", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.SendBill")
    @ResponseWrapper(localName = "sendBillResponse", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.SendBillResponse")
    public byte[] sendBill(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "contentFile", targetNamespace = "")
        DataHandler contentFile);

    /**
     * 
     * @param fileName
     * @param contentFile
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "urn:sendSummary")
    @WebResult(name = "ticket", targetNamespace = "")
    @RequestWrapper(localName = "sendSummary", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.SendSummary")
    @ResponseWrapper(localName = "sendSummaryResponse", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.SendSummaryResponse")
    public String sendSummary(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "contentFile", targetNamespace = "")
        DataHandler contentFile);

    /**
     * 
     * @param ticket
     * @return
     *     returns pe.gob.sunat.service.StatusResponse
     */
    @WebMethod(action = "urn:getStatus")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "getStatus", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.GetStatus")
    @ResponseWrapper(localName = "getStatusResponse", targetNamespace = "http://service.sunat.gob.pe", className = "pe.gob.sunat.service.GetStatusResponse")
    public StatusResponse getStatus(
        @WebParam(name = "ticket", targetNamespace = "")
        String ticket);

}
