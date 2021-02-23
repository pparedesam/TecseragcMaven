package ll.comprobantes.electronicos.codigos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.pdf.BarcodePDF417;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Code417 {

    private static Code417 _Instancia;

    private Code417() {
    }

    public static Code417 Instancia() {
        if (_Instancia == null) {
            _Instancia = new Code417();
        }
        return _Instancia;
    }

    public static void get(String xml_file_name, String _417_file_name, String _417_value) {

        try {

            File fXmlFile = new File(xml_file_name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            // DigestValue
            NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            System.out.println("" + nNode_IssueDate.getNodeName());
            System.out.println("Codigo Hash:    " + nNode_IssueDate.getTextContent());

            // SignatureValue
            NodeList nList_SignatureValue = doc.getElementsByTagName("ds:SignatureValue");
            Node nNode_SignatureValue = nList_SignatureValue.item(0);
            System.out.println("" + nNode_SignatureValue.getNodeName());

            BarcodePDF417 barcode = new BarcodePDF417();
            String _value_final = _417_value + "|" + nNode_IssueDate.getTextContent() + "|" + nNode_SignatureValue.getTextContent();
            barcode.setText(_value_final);

            java.awt.Image img = barcode.createAwtImage(Color.BLACK, Color.WHITE);
            BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            outImage.getGraphics().drawImage(img, 0, 0, null);
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ImageIO.write(outImage, "png", bytesOut);
            bytesOut.flush();
            byte[] pngImageData = bytesOut.toByteArray();
            try (FileOutputStream fos = new FileOutputStream(_417_file_name)) {
                fos.write(pngImageData);
                fos.flush();
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
    }

}
