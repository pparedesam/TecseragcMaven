package ll.comprobantes.electronicos.codigos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CodeHash {

    private static CodeHash _Instancia;

    private CodeHash() {
    }

    public static CodeHash Instancia() {
        if (_Instancia == null) {
            _Instancia = new CodeHash();
        }
        return _Instancia;
    }

    public static void get(String xml_file_name, String hash_file_name) {

        try {

            File fXmlFile = new File(xml_file_name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // cbc:ID	
            //  NodeList nList_id = doc.getElementsByTagName("ext:UBLExtensions");
            //  Node nNode_id = nList_id.item(0)
            //	  System.out.println("" + nNode_id.getNodeName());;
            //	  System.out.println("Numero de Ticket:   " + nNode_id.getTextContent());
            // DigestValue
            NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            System.out.println("" + nNode_IssueDate.getNodeName());
            System.out.println("Codigo Hash:    " + nNode_IssueDate.getTextContent());

            File archivo_hash = new File(hash_file_name);
            archivo_hash.delete();
            try (FileWriter chanel_write = new FileWriter(archivo_hash, true)) {
                chanel_write.write(nNode_IssueDate.getTextContent());
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
    }

}
