package ll.comprobantes.electronicos.connection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.soap.util.mime.ByteArrayDataSource;

/**
 *
 * @author estebanok
 */
public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

    private static HeaderHandler _Instancia;

    private HeaderHandler() {
    }

    public static HeaderHandler Instancia() {
        if (_Instancia == null) {
            _Instancia = new HeaderHandler();
        }
        return _Instancia;
    }

    public static String[] myParam = new String[1];

    public static String _RUC = "";

    public HeaderHandler(String _ruc) {

        _RUC = _ruc;

    }

    @SuppressWarnings("unused")
    @Override
    public boolean handleMessage(SOAPMessageContext smc) {

        String _ruc = "20315295573";
        //String _usuario_secundario = "SVRLL087";
        String _usuario_secundario = "USULL780";
        //String _password = "Sistemas780.";
        String _password = "USULL780";
        String _usuario = _ruc + _usuario_secundario;

        Date fecha = new Date();

        SimpleDateFormat limite = new SimpleDateFormat("yyyy-MM-dd"); //Para declarar valores en nuevos objetos date, usa el mismo formato date que usaste al crear las fechas 
        Date Limite = null;
        try {
            Limite = limite.parse("2016-12-31");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (fecha.compareTo(Limite) > 0) {
            //  		_usuario="hielo";

        }

        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {

            SOAPMessage message = smc.getMessage();

            try {

                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
                if (envelope.getHeader() != null) {
                    envelope.getHeader().detachNode();
                }
                SOAPHeader header = envelope.addHeader();
                envelope.setPrefix("soapenv");
                header.setPrefix("soapenv");
                envelope.getBody().setPrefix("soapenv");
                envelope.removeAttribute("xmlns:S");

                SOAPElement ser
                        = envelope.addAttribute(new QName("xmlns:ser"), "http://service.sunat.gob.pe");
                envelope.removeAttribute("xmlns:soapenv");
                SOAPElement soapenv
                        = envelope.addAttribute(new QName("xmlns:soapenv"), "http://schemas.xmlsoap.org/soap/envelope/");

                SOAPElement wsse
                        = envelope.addAttribute(new QName("xmlns:wsse"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

                SOAPElement security
                        = header.addChildElement("Security", "wsse");

                SOAPElement usernameToken
                        = security.addChildElement("UsernameToken", "wsse");

                SOAPElement username
                        = usernameToken.addChildElement("Username", "wsse");
                username.addTextNode(_usuario);

                SOAPElement password
                        = usernameToken.addChildElement("Password", "wsse");
                password.addTextNode(_password);

                //Print out the outbound SOAP message to System.out
                //message.writeTo(System.out);
                HeaderHandler h = new HeaderHandler(_RUC);
                h.handleFault(smc);

                System.out.println("SOAP:   " + h.toString());
            } catch (Exception e) {

                System.out.println("Error: " + e);;
            }

        } else {
            try {

                //This handler does nothing with the response from the Web Service so
                //we just print out the SOAP message.
                SOAPMessage message = smc.getMessage();

                message.writeTo(System.out);
                System.out.println("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return outboundProperty;

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Set getHeaders() {
        return null;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
    }

    public static void readParam(String _file_parametros) throws Exception {

        InputStream is_param = new FileInputStream(_file_parametros);
        DataSource ds_param = new ByteArrayDataSource(is_param, "application/octet-stream");
        DataHandler dhandler_param = new DataHandler(ds_param);

        Object content = dhandler_param.getContent();

        BufferedReader br = null;

        try {

            String sCurrentLine;
            br = new BufferedReader(new InputStreamReader((InputStream) content));

            while ((sCurrentLine = br.readLine()) != null) {
                myParam[0] = sCurrentLine;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
