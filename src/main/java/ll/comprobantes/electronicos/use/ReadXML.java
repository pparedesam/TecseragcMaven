/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.comprobantes.electronicos.use;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import ll.entidades.xmlFacturacion.Facturaxml;
import ll.entidades.xmlFacturacion.GuiaRemisionxml;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.entidades.xmlFacturacion.NotaDebitoxml;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class ReadXML {

    private static ReadXML _Instancia;

    private ReadXML() {
    }

    public static ReadXML Instancia() {
        if (_Instancia == null) {
            _Instancia = new ReadXML();
        }
        return _Instancia;
    }

    public static void get_respuesta(String xml_file_name) {

        String raya = "------------------------------------------------";
        try {

            File fXmlFile = new File(xml_file_name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // cbc:ID	
            NodeList nList_id = doc.getElementsByTagName("cbc:ID");
            Node nNode_id = nList_id.item(0);
            System.out.println("" + nNode_id.getNodeName());
            System.out.println("Numero de Ticket:   " + nNode_id.getTextContent());
            System.out.println(raya);

            // cbc:IssueDate
            NodeList nList_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            System.out.println("" + nNode_IssueDate.getNodeName());
            System.out.println("Fecha de Envio:    " + nNode_IssueDate.getTextContent());

            //cbc:IssueTime
            NodeList nList_IssueTime = doc.getElementsByTagName("cbc:IssueTime");
            Node nNode_IssueTime = nList_IssueTime.item(0);
            //System.out.println("" + nNode_IssueTime.getNodeName());
            System.out.println("Hora de Envio:     " + nNode_IssueTime.getTextContent());

            // cbc:ResponseDate
            NodeList nList_ResponseDate = doc.getElementsByTagName("cbc:ResponseDate");
            Node nNode_ResponseDate = nList_ResponseDate.item(0);
            System.out.println("" + nNode_ResponseDate.getNodeName());
            System.out.println("Fecha de Recepcion:" + nNode_ResponseDate.getTextContent());

            //cbc:ResponseTime
            NodeList nList_ResponseTime = doc.getElementsByTagName("cbc:ResponseTime");
            Node nNode_ResponseTime = nList_ResponseTime.item(0);
            //System.out.println("" + nNode_IssueTime.getNodeName());
            System.out.println("Hora de Recepcion: " + nNode_ResponseTime.getTextContent());

            System.out.println(raya);

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("cac:DocumentResponse");

            System.out.println(raya);

            for (int temp_Signature = 0; temp_Signature < nList.getLength(); temp_Signature++) {

                Node nNode = nList.item(temp_Signature);
                System.out.println("Nodo de Firma:        " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    //cbc:ReferenceID		  
                    System.out.println("Id de Referencia:     " + eElement.getElementsByTagName("cbc:ReferenceID").item(0).getTextContent());

                    //cbc:ResponseCode
                    System.out.println("Codigo de Respuesta:  " + eElement.getElementsByTagName("cbc:ResponseCode").item(0).getTextContent());

                    //cbc:Description
                    System.out.println("Descripcion:          " + eElement.getElementsByTagName("cbc:Description").item(0).getTextContent());

                    //			  NodeList SignatoryParty = eElement.getElementsByTagName("cac:SignatoryParty");
                    // 		NodeList PartyIdentification = eElement.getElementsByTagName("cac:PartyIdentification");	
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
    }

    public static Facturaxml getSign(String xml_file, Facturaxml objFactura) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            NodeList nList_IssueValue = doc.getElementsByTagName("ds:SignatureValue");
            Node nNode_IssueValue = nList_IssueValue.item(0);

            objFactura.setDigestValue(nNode_IssueDate.getTextContent());
            objFactura.setSignatureValue(nNode_IssueValue.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objFactura;
    }

    public static Facturaxml getResult(String xml_file, Facturaxml objFactura) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("cbc:Description");
            Node nNode_IssueDate = nList_IssueDate.item(0);

            objFactura.setResponse(nNode_IssueDate.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objFactura;
    }

    public static NotaCreditoxml getResultNC(String xml_file, NotaCreditoxml objNotaCreditoxml) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("cbc:Description");
            Node nNode_IssueDate = nList_IssueDate.item(0);

            objNotaCreditoxml.setResponse(nNode_IssueDate.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objNotaCreditoxml;
    }

    public static GuiaRemisionxml getResultGRM(String xml_file, GuiaRemisionxml objGuiaRemisionxml) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("cbc:Description");
            Node nNode_IssueDate = nList_IssueDate.item(0);

            objGuiaRemisionxml.setResponse(nNode_IssueDate.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objGuiaRemisionxml;
    }

    public static NotaCreditoxml getSignNC(String xml_file, NotaCreditoxml objNotaCredito) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            NodeList nList_IssueValue = doc.getElementsByTagName("ds:SignatureValue");
            Node nNode_IssueValue = nList_IssueValue.item(0);

            objNotaCredito.setDigestValue(nNode_IssueDate.getTextContent());
            objNotaCredito.setSignatureValue(nNode_IssueValue.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objNotaCredito;
    }

    public static NotaDebitoxml getResultND(String xml_file, NotaDebitoxml objNotaDebitoxml) {

        try {
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList_IssueDate = doc.getElementsByTagName("cbc:Description");
            Node nNode_IssueDate = nList_IssueDate.item(0);

            objNotaDebitoxml.setResponse(nNode_IssueDate.getTextContent());

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            StackTraceElement[] trace = e.getStackTrace();
            System.err.println(trace[0].toString());
        }
        return objNotaDebitoxml;
    }
}
