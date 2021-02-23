package ll.comprobantes.electronicos.connection;

//import java.io.File;
import java.io.BufferedWriter;
import ll.comprobantes.electronicos.errors.CodigosErrores;
import ll.comprobantes.electronicos.use.ReadXML;
import ll.comprobantes.electronicos.use.UnZip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import ll.exc.ExcepcionPropia;
import ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService;
import ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service;
import org.apache.soap.util.mime.ByteArrayDataSource;

public class H_main {

    private static H_main _Instancia;

    private H_main() {
    }

    public static H_main Instancia() {
        if (_Instancia == null) {
            _Instancia = new H_main();
        }
        return _Instancia;
    }

    public static void conectar(String fileXMLcf, String archivo) throws Exception, ExcepcionPropia {

        String zipRpta = "A:\\DocumentosElectronicos\\Invoice\\" + archivo + "\\" + archivo + "R.zip";
        String xmlRpta = "A:\\DocumentosElectronicos\\Invoice\\" + archivo + "\\R-" + archivo + ".xml";
        String txtRpta = "A:\\DocumentosElectronicos\\Invoice\\" + archivo + "\\" + archivo + "R.txt";

        BillService_Service service = new BillService_Service();
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver("20315295573");
        service.setHandlerResolver(handlerResolver);

        InputStream is = new FileInputStream("A:\\DocumentosElectronicos\\Invoice\\" + archivo + "\\" + archivo + ".zip");

        DataSource ds = new ByteArrayDataSource(is, "application/octet-stream");
        H_main objeto = new H_main();
        DataHandler contentFile = new DataHandler(ds);

        BillService port = service.getBillServicePort();

        try {
            byte[] resultado = port.sendBill(archivo + ".zip", contentFile, txtRpta);
            objeto.writeSmallBinaryFile(resultado, zipRpta);
            UnZip.descomprimir(zipRpta, "A:\\DocumentosElectronicos\\Invoice\\" + archivo);
            ReadXML.get_respuesta(xmlRpta);
            //	codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

        } catch (javax.xml.ws.soap.SOAPFaultException soapFaultException) {

            javax.xml.soap.SOAPFault fault = soapFaultException.getFault();

            System.out.println("Code   : " + fault.getFaultCode());

            System.out.println("Mensage:" + soapFaultException.getMessage());

            System.out.println("String:" + soapFaultException.toString());

            //System.out.println("Fault:"+soapFaultException.getLocalizedMessage() );
            String _cadena_error = fault.getFaultCode().substring(16, 20);
            System.out.println(_cadena_error);

            int _error = Integer.parseInt(_cadena_error);
            System.out.println(CodigosErrores.main(_error));

            if (_error != 0) {

                File archivo_resultados = new File(txtRpta);
                archivo_resultados.delete();
                FileWriter chanel_write = new FileWriter(archivo_resultados, true);
                chanel_write.write(_cadena_error + ":" + CodigosErrores.main(_error));
                chanel_write.close();

            }
        }
    }

    //	File file_log = new File($FILE_NAME_LOGS);
//TimeUnit.SECONDS.sleep(4);
    public static void conectarNC(String fileXMLcf, String archivo) throws Exception {

        String zipRpta = "A:\\DocumentosElectronicos\\CreditNote\\" + archivo + "\\" + archivo + "R.zip";
        String xmlRpta = "A:\\DocumentosElectronicos\\CreditNote\\" + archivo + "\\R-" + archivo + ".xml";
        String txtRpta = "A:\\DocumentosElectronicos\\CreditNote\\" + archivo + "\\" + archivo + "R.txt";

        ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service service = new ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service();
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver("20315295573");
        service.setHandlerResolver(handlerResolver);
        BillService port = service.getBillServicePort();

        InputStream is = new FileInputStream("A:\\DocumentosElectronicos\\CreditNote\\" + archivo + "\\" + archivo + ".zip");

        DataSource ds = new ByteArrayDataSource(is, "application/octet-stream");
        H_main objeto = new H_main();
        DataHandler contentFile = new DataHandler(ds);

        try {
            byte[] resultado = port.sendBill(archivo + ".zip", contentFile, txtRpta);

            objeto.writeSmallBinaryFile(resultado, zipRpta);
            UnZip.descomprimir(zipRpta, "A:\\DocumentosElectronicos\\CreditNote\\" + archivo);
            ReadXML.get_respuesta(xmlRpta);
            //	codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

        } catch (javax.xml.ws.soap.SOAPFaultException soapFaultException) {

            javax.xml.soap.SOAPFault fault = soapFaultException.getFault();

            System.out.println("Code   : " + fault.getFaultCode());

            System.out.println("Mensage:" + soapFaultException.getMessage());

            System.out.println("String:" + soapFaultException.toString());

            //System.out.println("Fault:"+soapFaultException.getLocalizedMessage() );
            String _cadena_error = fault.getFaultCode().substring(16, 20);
            System.out.println(_cadena_error);

            int _error = Integer.parseInt(_cadena_error);
            System.out.println(CodigosErrores.main(_error));

            if (_error != 0) {

                File archivo_resultados = new File(txtRpta);
                archivo_resultados.delete();
                FileWriter chanel_write = new FileWriter(archivo_resultados, true);
                chanel_write.write(_cadena_error + ":" + CodigosErrores.main(_error));
                chanel_write.close();

            }

            //	File file_log = new File($FILE_NAME_LOGS);
        }
        //TimeUnit.SECONDS.sleep(4);
    }
    
    
        public static void conectarND(String fileXMLcf, String archivo) throws Exception {

        String zipRpta = "A:\\DocumentosElectronicos\\DebitNote\\" + archivo + "\\" + archivo + "R.zip";
        String xmlRpta = "A:\\DocumentosElectronicos\\DebitNote\\" + archivo + "\\R-" + archivo + ".xml";
        String txtRpta = "A:\\DocumentosElectronicos\\DebitNote\\" + archivo + "\\" + archivo + "R.txt";
        
        ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service service = new ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service();
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver("20315295573");
        service.setHandlerResolver(handlerResolver);
        BillService port = service.getBillServicePort();

        InputStream is = new FileInputStream("A:\\DocumentosElectronicos\\DebitNote\\" + archivo + "\\" + archivo + ".zip");

        DataSource ds = new ByteArrayDataSource(is, "application/octet-stream");
        H_main objeto = new H_main();
        DataHandler contentFile = new DataHandler(ds);

        try {
            byte[] resultado = port.sendBill(archivo + ".zip", contentFile, txtRpta);

            objeto.writeSmallBinaryFile(resultado, zipRpta);
            UnZip.descomprimir(zipRpta, "A:\\DocumentosElectronicos\\DebitNote\\" + archivo);
            ReadXML.get_respuesta(xmlRpta);
            //	codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

        } catch (javax.xml.ws.soap.SOAPFaultException soapFaultException) {

            javax.xml.soap.SOAPFault fault = soapFaultException.getFault();

            System.out.println("Code   : " + fault.getFaultCode());

            System.out.println("Mensage:" + soapFaultException.getMessage());

            System.out.println("String:" + soapFaultException.toString());

            //System.out.println("Fault:"+soapFaultException.getLocalizedMessage() );
            String _cadena_error = fault.getFaultCode().substring(16, 20);
            System.out.println(_cadena_error);

            int _error = Integer.parseInt(_cadena_error);
            System.out.println(CodigosErrores.main(_error));

            if (_error != 0) {

                File archivo_resultados = new File(txtRpta);
                archivo_resultados.delete();
                FileWriter chanel_write = new FileWriter(archivo_resultados, true);
                chanel_write.write(_cadena_error + ":" + CodigosErrores.main(_error));
                chanel_write.close();

            }

            //	File file_log = new File($FILE_NAME_LOGS);
        }
        //TimeUnit.SECONDS.sleep(4);
    }

    public static void conectarVD(String fileXMLcf, String archivo) throws Exception {

        String zipRpta = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + archivo + "\\" + archivo + "R.zip";
        String xmlRpta = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + archivo + "\\R-" + archivo + ".xml";
        String txtRpta = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + archivo + "\\" + archivo + "R.txt";

        ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service service = new ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion.BillService_Service();
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver("20315295573");
        service.setHandlerResolver(handlerResolver);
        BillService port = service.getBillServicePort();

        InputStream is = new FileInputStream("A:\\DocumentosElectronicos\\VoidedDocuments\\" + archivo + "\\" + archivo + ".zip");

        DataSource ds = new ByteArrayDataSource(is, "application/octet-stream");
        H_main objeto = new H_main();
        DataHandler contentFile = new DataHandler(ds);
        File archivo_txt = new File(txtRpta);
        if (archivo_txt.exists()) {
            archivo_txt.delete();
        }

        BufferedWriter bw;
        try {
            String resultado = port.sendSummary(archivo + ".zip", contentFile, txtRpta);

            bw = new BufferedWriter(new FileWriter(archivo_txt));
            bw.write(resultado);
            bw.close();
            UnZip.descomprimir(zipRpta, "A:\\DocumentosElectronicos\\VoidedDocuments\\" + archivo);
            ReadXML.get_respuesta(xmlRpta);
            //	codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

        } catch (javax.xml.ws.soap.SOAPFaultException soapFaultException) {

            javax.xml.soap.SOAPFault fault = soapFaultException.getFault();

            System.out.println("Code   : " + fault.getFaultCode());

            System.out.println("Mensage:" + soapFaultException.getMessage());

            System.out.println("String:" + soapFaultException.toString());

            //System.out.println("Fault:"+soapFaultException.getLocalizedMessage() );
            String _cadena_error = fault.getFaultCode().substring(16, 20);
            System.out.println(_cadena_error);

            int _error = Integer.parseInt(_cadena_error);
            System.out.println(CodigosErrores.main(_error));

            if (_error != 0) {

                File archivo_resultados = new File(txtRpta);
                archivo_resultados.delete();
                FileWriter chanel_write = new FileWriter(archivo_resultados, true);
                chanel_write.write(_cadena_error + ":" + CodigosErrores.main(_error));
                chanel_write.close();

            }

            //	File file_log = new File($FILE_NAME_LOGS);
        }
        //TimeUnit.SECONDS.sleep(4);
    }

    public static void conectarGM(String fileXMLcf, String archivo) throws Exception {

        String zipRpta = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + archivo + "\\" + archivo + "R.zip";
        String xmlRpta = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + archivo + "\\R-" + archivo + ".xml";
        String txtRpta = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + archivo + "\\" + archivo + "R.txt";

        BillService_Service service = new BillService_Service();
        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver("20315295573");
        service.setHandlerResolver(handlerResolver);

        BillService port = service.getBillServicePort();

        InputStream is = new FileInputStream("A:\\DocumentosElectronicos\\DespatchAdvice\\" + archivo + "\\" + archivo + ".zip");

        DataSource ds = new ByteArrayDataSource(is, "application/octet-stream");
        H_main objeto = new H_main();
        DataHandler contentFile = new DataHandler(ds);

        try {
            byte[] resultado = port.sendBill(archivo + ".zip", contentFile, txtRpta);

            objeto.writeSmallBinaryFile(resultado, zipRpta);
            UnZip.descomprimir(zipRpta, "A:\\DocumentosElectronicos\\DespatchAdvice\\" + archivo);
            ReadXML.get_respuesta(xmlRpta);
            //	codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

        } catch (javax.xml.ws.soap.SOAPFaultException soapFaultException) {

            javax.xml.soap.SOAPFault fault = soapFaultException.getFault();

            soapFaultException.printStackTrace();

            System.out.println("Code   : " + fault.getFaultCode());

            System.out.println("Mensage:" + soapFaultException.getMessage());

            System.out.println("String:" + soapFaultException.toString());

            //System.out.println("Fault:"+soapFaultException.getLocalizedMessage() );
            String _cadena_error = fault.getFaultCode().substring(16, 20);
            System.out.println(_cadena_error);

            int _error = Integer.parseInt(_cadena_error);
            System.out.println(CodigosErrores.main(_error));

            if (_error != 0) {

                File archivo_resultados = new File(txtRpta);
                archivo_resultados.delete();
                try (FileWriter chanel_write = new FileWriter(archivo_resultados, true)) {
                    chanel_write.write(_cadena_error + ":" + CodigosErrores.main(_error));
                }

            }

            //	File file_log = new File($FILE_NAME_LOGS);
        }
        //TimeUnit.SECONDS.sleep(4);

    }

    void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aBytes);
    }

}
