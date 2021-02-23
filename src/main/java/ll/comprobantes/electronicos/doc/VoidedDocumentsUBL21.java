/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.comprobantes.electronicos.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import ll.comprobantes.electronicos.codigos.Code417;
import ll.comprobantes.electronicos.codigos.CodeHash;
import ll.comprobantes.electronicos.codigos.CodeQR;
import ll.comprobantes.electronicos.connection.H_main;

import ll.comprobantes.electronicos.use.ComprimirXML;
import ll.comprobantes.electronicos.use.FirmarDocumento;
import ll.entidades.xmlFacturacion.DocumentoBajaxml;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PauPar
 */
public class VoidedDocumentsUBL21 {

    private static VoidedDocumentsUBL21 _Instancia;

    private VoidedDocumentsUBL21() {
    }

    public static VoidedDocumentsUBL21 Instancia() {
        if (_Instancia == null) {
            _Instancia = new VoidedDocumentsUBL21();
        }
        return _Instancia;
    }

    public void c_XML(DocumentoBajaxml objDocumentoBaja) throws Exception {

        String formatoArchivo = objDocumentoBaja.getObjPersonaxmlEmisor().getNroRuc() + "-" + objDocumentoBaja.getIdentificador();

        String ksTipe = "JKS";
        String ksFile = "C:\\Program Files\\Apache Software Foundation\\keys\\keystore.jks";
        String ksPass = "BXpNdA7b3csTr";
        String privateKsPass = "BXpNdA7b3csTr";
        String privateKsAlias = "(pe1_pfvp_730_sw_kpsc)_SAAVEDRA_19419239";
        String fileNameXMLnf = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\sf" + formatoArchivo + ".xml";
        String fileNameXMLcf = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\" + formatoArchivo + ".xml";

        String fileNameCNT = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\cntLetras_" + formatoArchivo + ".cnt";
        String fileNameLOG = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\log_" + formatoArchivo + ".log";
        String fileQR = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\" + formatoArchivo + ".png";
        String file417 = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\417" + formatoArchivo + ".png";
        String fileHash = "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\" + formatoArchivo + ".hash";

        createFile("A:\\DocumentosElectronicos\\VoidedDocuments\\", formatoArchivo);
        File file_log = new File(fileNameLOG);

        if (!file_log.exists()) {
            file_log.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file_log);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        writeXML(fileNameXMLnf, objDocumentoBaja);
        FirmarDocumento.firmar(formatoArchivo, fileNameXMLnf, fileNameXMLcf, ksTipe, ksFile, ksPass, privateKsPass, privateKsAlias);

        ComprimirXML.comprimir("A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\" + formatoArchivo + ".zip", fileNameXMLcf, formatoArchivo + ".xml");

        /*
        String contenidoCode = objDocumentoBaja.getObjPersonaxmlEjecutor().getNroRuc() + "|" + objNotaDebito.getObjPersonaxmlEmisor().getTipoDoc() + "|"
                + objDocumentoBaja.getNumeracionFactura().substring(0, 3) + "|" + objNotaDebito.getNumeracionFactura().substring(5, 13) + "|" + objNotaDebito.getIgvTotal() + "|"
                + objDocumentoBaja.getImporteTotal() + "|" + objNotaDebito.getFechaEmision() + "|"
                + objDocumentoBaja.getObjPersonaxmlEjecutor().getTipoDoc() + "|" + objNotaDebito.getObjPersonaxmlEjecutor().getNroRuc();

        CodeQR.get(fileNameXMLcf, fileQR, contenidoCode);
        Code417.get(fileNameXMLcf, file417, contenidoCode);
        CodeHash.get(fileNameXMLcf, fileHash);
         */
        H_main.conectarVD(fileNameXMLcf, formatoArchivo);
        //DocumentoCompraVentaNEG.Instancia().insertImgDocGeneradoND("A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\" + formatoArchivo + ".png", "A:\\DocumentosElectronicos\\VoidedDocuments\\" + formatoArchivo + "\\417" + formatoArchivo + ".png", objDocumentoBaja);
    }

    public static File createFile(String path, String nombre) throws IOException {
        try {
            File file = new File(path + nombre);
            Path toCreatePath = Paths.get(file.toURI());

            if (!Files.exists(toCreatePath)) {
                Files.createDirectories(toCreatePath);
                // Files.setAttribute(file.toPath(), "dos:hidden", true);
            }
            return file;
        } catch (IOException e) {

        }
        return null;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static void writeXML(String fileNameXMLnf, DocumentoBajaxml objDocumentoBaja) throws Exception {

        DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // agregamos la nombre pricipal dentro de este iran todos los elementos
        Element element = document.createElement("VoidedDocuments");
        document.appendChild(element);

        // creamos los atributos de la cabecera
        Attr attr_xmlns = document.createAttribute("xmlns");
        attr_xmlns.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1");
        element.setAttributeNode(attr_xmlns);

        Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
        attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        element.setAttributeNode(attr_xmlns_cac);

        Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
        attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        element.setAttributeNode(attr_xmlns_cbc);

        //	Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
        //	attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
        //	element.setAttributeNode(attr_xmlns_ccts);
        Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
        attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
        element.setAttributeNode(attr_xmlns_ds);

        Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
        attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        element.setAttributeNode(attr_xmlns_ext);

        //	Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
        //	attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        //	element.setAttributeNode(attr_xmlns_qdt);
        Attr attr_xmlns_sac = document.createAttribute("xmlns:sac");
        attr_xmlns_sac.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        element.setAttributeNode(attr_xmlns_sac);

        //	Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
        //	attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        //	element.setAttributeNode(attr_xmlns_udt);
        Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
        attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        element.setAttributeNode(attr_xmlns_xsi);

        /// NODO NUMERO 2  FIRMA DIGITAL  -- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ds:Signature /Invoice/cac:Signature 
        /// NODO UBLExtensions
        Element UBLExtensions = document.createElement("ext:UBLExtensions");
        element.appendChild(UBLExtensions);

        /// NODO UBLExtensio
        //	Element UBLExtension = document.createElement("ext:UBLExtension");
        //	UBLExtensions.appendChild(UBLExtension);
        /// NODO ext:ExtensionContent
        //		Element ExtensionContent = document.createElement("ext:ExtensionContent");
        //		UBLExtension.appendChild(ExtensionContent);
        ///////////////////////////////////////////////// FIN UBLVersionID
        // UBLVersionID
        Element UBLVersionID = document.createElement("cbc:UBLVersionID");
        UBLVersionID.appendChild(document.createTextNode("2.0"));
        element.appendChild(UBLVersionID);

        // cbc:CustomizationID
        Element CustomizationID = document.createElement("cbc:CustomizationID");
        CustomizationID.appendChild(document.createTextNode("1.0"));
        element.appendChild(CustomizationID);

        /// NODO NUMERO 8  SERIE + NUMERO CORRELATIVO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  
        // cbc:ID			
        Element SERIE = document.createElement("cbc:ID");
        SERIE.appendChild(document.createTextNode(objDocumentoBaja.getIdentificador()));
        element.appendChild(SERIE);

        ///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
        /// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 
        //cbc:ReferenceDate					
        Element ReferenceDate = document.createElement("cbc:ReferenceDate");
        ReferenceDate.appendChild(document.createTextNode(objDocumentoBaja.getFechaGeneracion()));
        element.appendChild(ReferenceDate);

        ///////////////////////////////////////////////// FIN NUMERO 1 -- fecEmision	
        /// NODO NUMERO 7  TIPO DEL DOCUMENTO -- (Factura)    /Invoice/cbc:InvoiceTypeCode  
        // cbc:IssueDate			
        Element IssueDate = document.createElement("cbc:IssueDate");
        IssueDate.appendChild(document.createTextNode(objDocumentoBaja.getFechaComunicacion()));
        element.appendChild(IssueDate);

        // cac:Signature  al nivel de raiz
        Element cacSignature = document.createElement("cac:Signature");
        element.appendChild(cacSignature);

        // cbc:ID
        Element cbcID = document.createElement("cbc:ID");
        cacSignature.appendChild(cbcID);
        cbcID.setTextContent(objDocumentoBaja.getObjPersonaxmlEmisor().getNroRuc());

        // cac:SignatoryParty
        Element SignatoryParty = document.createElement("cac:SignatoryParty");
        cacSignature.appendChild(SignatoryParty);

        //	cac:PartyIdentification
        Element PartyIdentification = document.createElement("cac:PartyIdentification");
        SignatoryParty.appendChild(PartyIdentification);

        // cbc:ID
        Element cbcID2 = document.createElement("cbc:ID");
        PartyIdentification.appendChild(cbcID2);
        cbcID2.setTextContent(objDocumentoBaja.getObjPersonaxmlEmisor().getNroRuc());

        //	cac:PartyName
        Element cac_PartyName = document.createElement("cac:PartyName");
        SignatoryParty.appendChild(cac_PartyName);

        // cbc:Name
        Element cbc_Name = document.createElement("cbc:Name");
        cac_PartyName.appendChild(cbc_Name);
        cbc_Name.setTextContent(objDocumentoBaja.getObjPersonaxmlEmisor().getRazonSocial());

        // cac:DigitalSignatureAttachment
        Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
        cacSignature.appendChild(DigitalSignatureAttachment);

        // cac:ExternalReference
        Element ExternalReference = document.createElement("cac:ExternalReference");
        DigitalSignatureAttachment.appendChild(ExternalReference);

        // cbc:URI
        Element URI2 = document.createElement("cbc:URI");
        ExternalReference.appendChild(URI2);
        URI2.setTextContent(objDocumentoBaja.getObjPersonaxmlEmisor().getNroRuc());

        // cac:AccountingSupplierParty EN RAIZ 
        Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
        element.appendChild(AccountingSupplierParty);

        // cbc:CustomerAssignedAccountID
        Element CustomerAssignedAccountID = document.createElement("cbc:CustomerAssignedAccountID");
        CustomerAssignedAccountID.appendChild(document.createTextNode(objDocumentoBaja.getObjPersonaxmlEmisor().getNroRuc()));
        AccountingSupplierParty.appendChild(CustomerAssignedAccountID);

        // cbc:AdditionalAccountID
        Element AdditionalAccountID = document.createElement("cbc:AdditionalAccountID");
        AdditionalAccountID.appendChild(document.createTextNode("6"));
        AccountingSupplierParty.appendChild(AdditionalAccountID);

        // cac:Party
        Element Party3 = document.createElement("cac:Party");
        AccountingSupplierParty.appendChild(Party3);

        // cac:PartyLegalEntity
        Element PartyLegalEntity = document.createElement("cac:PartyLegalEntity");
        Party3.appendChild(PartyLegalEntity);

        // cbc:RegistrationName
        Element RegistrationName = document.createElement("cbc:RegistrationName");
        RegistrationName.appendChild(document.createTextNode(objDocumentoBaja.getObjPersonaxmlEmisor().getRazonSocial()));
        PartyLegalEntity.appendChild(RegistrationName);

        for (int i = 0; i < objDocumentoBaja.getListDetalleDocBajaxml().size(); i++) {
            //    System.out.println(" " + linea);
            // cac:InvoiceLine
            Element VoidedDocumentsLine = document.createElement("sac:VoidedDocumentsLine");
            element.appendChild(VoidedDocumentsLine);

            // cbc:LineID
            Element ID_Item = document.createElement("cbc:LineID");
            //ID_Item.appendChild(document.createTextNode("" + linea));
            ID_Item.appendChild(document.createTextNode(Integer.toString(i + 1)));
            VoidedDocumentsLine.appendChild(ID_Item);

            // cbc:DocumentTypeCode
            Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
            DocumentTypeCode.appendChild(document.createTextNode(objDocumentoBaja.getListDetalleDocBajaxml().get(i).getTipoDocumento()));
            VoidedDocumentsLine.appendChild(DocumentTypeCode);

            String Serie = objDocumentoBaja.getListDetalleDocBajaxml().get(i).getNumeracionDocumento().substring(0, 4);
            String Numero = objDocumentoBaja.getListDetalleDocBajaxml().get(i).getNumeracionDocumento().substring(5, 13);
            //sac:DocumentSerialID			
            Element DocumentSerialID = document.createElement("sac:DocumentSerialID");
            DocumentSerialID.appendChild(document.createTextNode(Serie));
            VoidedDocumentsLine.appendChild(DocumentSerialID);

            //sac:DocumentNumberID			
            Element DocumentNumberID = document.createElement("sac:DocumentNumberID");
            DocumentNumberID.appendChild(document.createTextNode(Numero));
            VoidedDocumentsLine.appendChild(DocumentNumberID);

            //sac:VoidReasonDescription
            Element VoidReasonDescription = document.createElement("sac:VoidReasonDescription");
            VoidReasonDescription.appendChild(document.createTextNode(objDocumentoBaja.getListDetalleDocBajaxml().get(i).getMotivo()));
            VoidedDocumentsLine.appendChild(VoidReasonDescription);

        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        //		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        document.setXmlStandalone(true);
        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileNameXMLnf));
        transformer.transform(source, streamResult);

    }

    public static String readFile(String filename) throws IOException {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return content;
    }

}
