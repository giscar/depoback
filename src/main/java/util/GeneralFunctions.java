package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author oswaldo
 */
public class GeneralFunctions {

    private static Log log = LogFactory.getLog(GeneralFunctions.class);


    public static String crearZip(String unidadEnvio, File signatureFile) {
        String resultado = "";
        /*try {
            log.info("generarXMLZipiadoFactura - Crear ZIP ");
            String inputFile = signatureFile.toString();
            FileInputStream in = new FileInputStream(inputFile);
            FileOutputStream out = new FileOutputStream(unidadEnvio + items.getEmpr_nroruc() + "-" + items.getDocu_tipodocumento() + "-" + items.getDocu_numero() + ".zip");

            byte b[] = new byte[2048];
            try (ZipOutputStream zipOut = new ZipOutputStream(out)) {
                ZipEntry entry2 = new ZipEntry(items.getEmpr_nroruc() + "-" + items.getDocu_tipodocumento() + "-" + items.getDocu_numero() + ".xml");
                zipOut.putNextEntry(entry2);
                System.out.println("==>Zip generado: " + items.getEmpr_nroruc() + "-" + items.getDocu_tipodocumento() + "-" + items.getDocu_numero() + ".zip");
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    zipOut.write(b, 0, len);
                }
                zipOut.closeEntry();
            }
            out.close();
            in.close();
            log.info("generarXMLZipiadoFactura - Zip creado " + unidadEnvio + items.getEmpr_nroruc() + "-" + items.getDocu_tipodocumento() + "-" + items.getDocu_numero() + ".zip");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("generarXMLZipiadoFactura - error  " + ex.toString());

        }*/
        return resultado;
    }
}
