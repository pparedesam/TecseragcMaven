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
import ll.comprobantes.electronicos.formats.Formato;
import ll.comprobantes.electronicos.use.ComprimirXML;
import ll.comprobantes.electronicos.use.FirmarDocumento;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author PauPar
 */
public class NotaCreditoUBL21 {

    private static NotaCreditoUBL21 _Instancia;

    private NotaCreditoUBL21() {
    }

    public static NotaCreditoUBL21 Instancia() {
        if (_Instancia == null) {
            _Instancia = new NotaCreditoUBL21();
        }
        return _Instancia;
    }

    public void c_XML(NotaCreditoxml objNotaCredito) throws Exception {

        String formatoArchivo = objNotaCredito.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCredito.getTipoDocumento() + "-" + objNotaCredito.getNumeracionFactura();

        String ksTipe = "JKS";
        String ksFile = "C:\\Program Files\\Apache Software Foundation\\keys\\keystore.jks";
        String ksPass = "BXpNdA7b3csTr";
        String privateKsPass = "BXpNdA7b3csTr";
        String privateKsAlias = "(pe1_pfvp_730_sw_kpsc)_SAAVEDRA_19419239";
        String fileNameXMLnf = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\sf" + formatoArchivo + ".xml";
        String fileNameXMLcf = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\" + formatoArchivo + ".xml";

        String fileNameCNT = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\cntLetras_" + formatoArchivo + ".cnt";
        String fileNameLOG = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\log_" + formatoArchivo + ".log";
        String fileQR = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\" + formatoArchivo + ".png";
        String file417 = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\417" + formatoArchivo + ".png";
        String fileHash = "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\" + formatoArchivo + ".hash";

        createFile("A:\\DocumentosElectronicos\\CreditNote\\", formatoArchivo);
        File file_log = new File(fileNameLOG);

        if (!file_log.exists()) {
            file_log.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file_log);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        writeXML(fileNameXMLnf, objNotaCredito);
        FirmarDocumento.firmar(formatoArchivo, fileNameXMLnf, fileNameXMLcf, ksTipe, ksFile, ksPass, privateKsPass, privateKsAlias);

        ComprimirXML.comprimir("A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\" + formatoArchivo + ".zip", fileNameXMLcf, formatoArchivo + ".xml");

        String contenidoCode = objNotaCredito.getObjPersonaxmlEjecutor().getNroRuc() + "|" + objNotaCredito.getObjPersonaxmlEmisor().getTipoDoc() + "|"
                + objNotaCredito.getNumeracionFactura().substring(0, 3) + "|" + objNotaCredito.getNumeracionFactura().substring(5, 13) + "|" + objNotaCredito.getIgvTotal() + "|"
                + objNotaCredito.getImporteTotal() + "|" + objNotaCredito.getFechaEmision() + "|"
                + objNotaCredito.getObjPersonaxmlEjecutor().getTipoDoc() + "|" + objNotaCredito.getObjPersonaxmlEjecutor().getNroRuc();

        CodeQR.get(fileNameXMLcf, fileQR, contenidoCode);
        Code417.get(fileNameXMLcf, file417, contenidoCode);
        CodeHash.get(fileNameXMLcf, fileHash);

        H_main.conectarNC(fileNameXMLcf, formatoArchivo);

        DocumentoCompraVentaNEG.Instancia().insertImgDocGeneradoNC("A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\" + formatoArchivo + ".png", "A:\\DocumentosElectronicos\\CreditNote\\" + formatoArchivo + "\\417" + formatoArchivo + ".png", objNotaCredito);

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

    public static void writeXML(String fileNameXMLnf, NotaCreditoxml objNotaCredito) throws Exception {

        DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // agregamos la nombre pricipal dentro de este iran todos los elementos
        Element element = document.createElement("CreditNote");
        document.appendChild(element);

        Attr attr_xmlns = document.createAttribute("xmlns");
        attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2");
        element.setAttributeNode(attr_xmlns);

        // xmlns:xsi 11
        Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
        attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        element.setAttributeNode(attr_xmlns_xsi);

        // xmlns:udt 5
        Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
        attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        element.setAttributeNode(attr_xmlns_udt);

        // xmlns:sac 2
        Attr attr_xmlns_sac = document.createAttribute("xmlns:sac");
        attr_xmlns_sac.setValue("urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        element.setAttributeNode(attr_xmlns_sac);

        // xmlns:qdt  8
        Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
        attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        element.setAttributeNode(attr_xmlns_qdt);

        // xmlns:ext 7
        Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
        attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        element.setAttributeNode(attr_xmlns_ext);

        // xmlns:ds 9
        Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
        attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
        element.setAttributeNode(attr_xmlns_ds);
        // xmlns:ccts 6
        Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
        attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
        element.setAttributeNode(attr_xmlns_ccts);
        // xmlns:cbc 4
        Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
        attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        element.setAttributeNode(attr_xmlns_cbc);

        // xmlns:cac  3
        Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
        attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        element.setAttributeNode(attr_xmlns_cac);

        /// NODO UBLExtensions
        Element UBLExtensions = document.createElement("ext:UBLExtensions");
        element.appendChild(UBLExtensions);

        // UBLVersionID
        Element UBLVersionID = document.createElement("cbc:UBLVersionID");
        UBLVersionID.appendChild(document.createTextNode("2.1"));
        element.appendChild(UBLVersionID);

        // cbc:CustomizationID
        Element CustomizationID = document.createElement("cbc:CustomizationID");
        CustomizationID.appendChild(document.createTextNode("2.0"));
        element.appendChild(CustomizationID);

        /// NODO NUMERO 8  SERIE + NUMERO CORRELATIVO -- (Factura)    /Invoice/cbc:InvoiceTypeCode 
        // cbc:ID			
        Element SERIE = document.createElement("cbc:ID");
        SERIE.appendChild(document.createTextNode(objNotaCredito.getNumeracionFactura()));
        element.appendChild(SERIE);

        ///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
        /// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 
        Element fecEmision = document.createElement("cbc:IssueDate");
        fecEmision.appendChild(document.createTextNode(objNotaCredito.getFechaEmision()));
        element.appendChild(fecEmision);

        if (!objNotaCredito.getObservaciones().equals("")) {
            Element Note = document.createElement("cbc:Note");
            Node cdataObservacion = document.createCDATASection(objNotaCredito.getObservaciones());
            Note.appendChild(cdataObservacion);
            element.appendChild(Note);
        }

        //cbc:DocumentCurrencyCode
        Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
        DocumentCurrencyCode.appendChild(document.createTextNode(objNotaCredito.getTipMoneda()));
        element.appendChild(DocumentCurrencyCode);

        //cac:DiscrepancyResponse
        Element DiscrepancyResponse = document.createElement("cac:DiscrepancyResponse");
        //DiscrepancyResponse.appendChild(document.createTextNode(myCabecera_nc.get_moneda()));
        element.appendChild(DiscrepancyResponse);

        // cbc:ReferenceID
        Element ReferenceID = document.createElement("cbc:ReferenceID");
        DiscrepancyResponse.appendChild(ReferenceID);
        ReferenceID.setTextContent(objNotaCredito.getObjFacturaxml().getNumeracionFactura());

        // cbc:ResponseCode
        Element ResponseCode = document.createElement("cbc:ResponseCode");
        DiscrepancyResponse.appendChild(ResponseCode);
        ResponseCode.setTextContent(objNotaCredito.getTipoNota());

        // cbc:Description
        Element Description = document.createElement("cbc:Description");
        DiscrepancyResponse.appendChild(Description);
        Description.setTextContent(objNotaCredito.getDescripcionMotivo());

        //cac:BillingReference
        Element BillingReference = document.createElement("cac:BillingReference");
        element.appendChild(BillingReference);

        //cac:InvoiceDocumentReference
        Element InvoiceDocumentReference = document.createElement("cac:InvoiceDocumentReference");
        BillingReference.appendChild(InvoiceDocumentReference);

        // cbc:ID
        Element ID_DocumentReference = document.createElement("cbc:ID");
        InvoiceDocumentReference.appendChild(ID_DocumentReference);
        ID_DocumentReference.setTextContent(objNotaCredito.getObjFacturaxml().getNumeracionFactura());

        //cbc:DocumentTypeCode
        Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
        InvoiceDocumentReference.appendChild(DocumentTypeCode);
        DocumentTypeCode.setTextContent("01");

        //cac:DespatchDocumentReference
        for (int i = 0; i < objNotaCredito.getListGuiaRemisionxml().size(); i++) {

            Element DespatchDocumentReference = document.createElement("cac:DespatchDocumentReference");
            element.appendChild(DespatchDocumentReference);

            // id
            Element DespatchDocumentReference_ID = document.createElement("cbc:ID");
            DespatchDocumentReference_ID.appendChild(document.createTextNode(objNotaCredito.getListGuiaRemisionxml().get(i).getNroGuia()));
            DespatchDocumentReference.appendChild(DespatchDocumentReference_ID);

            // documentTypeCode
            Element DespatchDocumentReference_DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
            DespatchDocumentReference_DocumentTypeCode.appendChild(document.createTextNode(objNotaCredito.getListGuiaRemisionxml().get(i).getTipoGuia()));
            DespatchDocumentReference.appendChild(DespatchDocumentReference_DocumentTypeCode);

            DespatchDocumentReference_DocumentTypeCode.setAttribute("listAgencyName", "PE:SUNAT");
            DespatchDocumentReference_DocumentTypeCode.setAttribute("listName", "SUNAT:Identificador de guia relacionado");
            DespatchDocumentReference_DocumentTypeCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");

        }

        // cac:Signature  al nivel de raiz
        Element cacSignature = document.createElement("cac:Signature");
        element.appendChild(cacSignature);

        // cbc:ID
        Element cbcID = document.createElement("cbc:ID");
        cacSignature.appendChild(cbcID);
        cbcID.setTextContent(objNotaCredito.getObjPersonaxmlEmisor().getNroRuc());

        // cac:SignatoryParty
        Element SignatoryParty = document.createElement("cac:SignatoryParty");
        cacSignature.appendChild(SignatoryParty);

        //	cac:PartyIdentification
        Element PartyIdentification = document.createElement("cac:PartyIdentification");
        SignatoryParty.appendChild(PartyIdentification);

        // cbc:ID
        Element cbcID2 = document.createElement("cbc:ID");
        PartyIdentification.appendChild(cbcID2);
        cbcID2.setTextContent(objNotaCredito.getObjPersonaxmlEmisor().getNroRuc());

        //	cac:PartyName
        Element cac_PartyName = document.createElement("cac:PartyName");
        SignatoryParty.appendChild(cac_PartyName);

        // cbc:Name
        Element cbc_Name = document.createElement("cbc:Name");
        cac_PartyName.appendChild(cbc_Name);
        cbc_Name.setTextContent(objNotaCredito.getObjPersonaxmlEmisor().getRazonSocial());

        // cac:DigitalSignatureAttachment
        Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
        cacSignature.appendChild(DigitalSignatureAttachment);

        // cac:ExternalReference
        Element ExternalReference = document.createElement("cac:ExternalReference");
        DigitalSignatureAttachment.appendChild(ExternalReference);

        // cbc:URI
        Element URI2 = document.createElement("cbc:URI");
        ExternalReference.appendChild(URI2);
        URI2.setTextContent(objNotaCredito.getObjPersonaxmlEmisor().getNroRuc());

        // cac:AccountingSupplierParty EN RAIZ 
        Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
        element.appendChild(AccountingSupplierParty);

        // cac:Party
        Element Party_EMISOR = document.createElement("cac:Party");
        AccountingSupplierParty.appendChild(Party_EMISOR);

        // cac:PartyIdentification
        Element PartyIdentification_EMISOR = document.createElement("cac:PartyIdentification");
        Party_EMISOR.appendChild(PartyIdentification_EMISOR);

        // id
        Element PartyIdentification_ID_EMISOR = document.createElement("cbc:ID");
        PartyIdentification_ID_EMISOR.appendChild(document.createTextNode(objNotaCredito.getObjPersonaxmlEmisor().getNroRuc()));
        PartyIdentification_EMISOR.appendChild(PartyIdentification_ID_EMISOR);

        PartyIdentification_ID_EMISOR.setAttribute("schemeID", "6");

        // cac:PartyName
        Element PartyName_EMISOR = document.createElement("cac:PartyName");
        Party_EMISOR.appendChild(PartyName_EMISOR);

        // cbc:Name  (con caracteres especiales)
        Element Name_EMISOR = document.createElement("cbc:Name");
        Node cdata = document.createCDATASection(objNotaCredito.getNombreComercial());
        Name_EMISOR.appendChild(cdata);
        PartyName_EMISOR.appendChild(Name_EMISOR);

        // cac:PartyLegalEntity
        Element PartyLegalEntity_EMISOR = document.createElement("cac:PartyLegalEntity");
        Party_EMISOR.appendChild(PartyLegalEntity_EMISOR);

        // cbc:RegistrationName
        Element RegistrationName_EMISOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_EMISOR = document.createCDATASection(objNotaCredito.getObjPersonaxmlEmisor().getRazonSocial());
        RegistrationName_EMISOR.appendChild(cdataRegistrationName_EMISOR);
        PartyLegalEntity_EMISOR.appendChild(RegistrationName_EMISOR);

        // cac:RegistrationAddress
        Element RegistrationAddress_EMISOR = document.createElement("cac:RegistrationAddress");
        PartyLegalEntity_EMISOR.appendChild(RegistrationAddress_EMISOR);

        // cbc:AddressTypeCode
        Element AddressTypeCode_EMISOR = document.createElement("cbc:AddressTypeCode");
        AddressTypeCode_EMISOR.appendChild(document.createTextNode("0002")); //Solicitar alta de Anexo a SUNAT
        RegistrationAddress_EMISOR.appendChild(AddressTypeCode_EMISOR);

        AddressTypeCode_EMISOR.setAttribute("listAgencyName", "PE:SUNAT");
        AddressTypeCode_EMISOR.setAttribute("listName", "Establecimientos anexos");

        Element AccountingCustomerParty = document.createElement("cac:AccountingCustomerParty");
        element.appendChild(AccountingCustomerParty);

        // cac:Party
        Element Party_RECEPTOR = document.createElement("cac:Party");
        AccountingCustomerParty.appendChild(Party_RECEPTOR);

        // cac:PartyIdentification
        Element PartyIdentification_RECEPTOR = document.createElement("cac:PartyIdentification");
        Party_RECEPTOR.appendChild(PartyIdentification_RECEPTOR);

        // id
        Element PartyIdentification_ID_RECEPTOR = document.createElement("cbc:ID");
        PartyIdentification_ID_RECEPTOR.appendChild(document.createTextNode(objNotaCredito.getObjPersonaxmlEjecutor().getNroRuc()));
        PartyIdentification_RECEPTOR.appendChild(PartyIdentification_ID_RECEPTOR);

        PartyIdentification_ID_RECEPTOR.setAttribute("schemeID", objNotaCredito.getObjPersonaxmlEjecutor().getTipoDoc());
        // PartyIdentification_ID_RECEPTOR.setAttribute("schemeName", "Documento de Identidad");
        // PartyIdentification_ID_RECEPTOR.setAttribute("schemeAgencyName", "PE:SUNAT");
        // PartyIdentification_ID_RECEPTOR.setAttribute("schemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");

        // cac:PartyLegalEntity
        Element PartyLegalEntity_RECEPTOR = document.createElement("cac:PartyLegalEntity");
        Party_RECEPTOR.appendChild(PartyLegalEntity_RECEPTOR);

        // cbc:RegistrationName
        Element RegistrationName_RECEPTOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_RECEPTOR = document.createCDATASection(objNotaCredito.getObjPersonaxmlEjecutor().getRazonSocial());
        RegistrationName_RECEPTOR.appendChild(cdataRegistrationName_RECEPTOR);
        PartyLegalEntity_RECEPTOR.appendChild(RegistrationName_RECEPTOR);

        Element TaxTotal_Header = document.createElement("cac:TaxTotal");
        //	TaxTotal_Header.appendChild(document.createTextNode(""+_tax_total));
        element.appendChild(TaxTotal_Header);

        // cbc:TaxAmount
        Element TaxAmount_Header = document.createElement("cbc:TaxAmount");

        Attr attr_Moneda = document.createAttribute("currencyID");
        attr_Moneda.setValue(objNotaCredito.getTipMoneda());
        TaxAmount_Header.setAttributeNode(attr_Moneda);

        Double _impuestos = new Double(objNotaCredito.getIgvTotal());

        TaxAmount_Header.appendChild(document.createTextNode("" + Formato._xml(_impuestos)));
        TaxTotal_Header.appendChild(TaxAmount_Header);

        // gravadas ///
        // cac:TaxSubtotal
        Element TaxSubtotal_Header_Gra = document.createElement("cac:TaxSubtotal");
        TaxTotal_Header.appendChild(TaxSubtotal_Header_Gra);

        Double _base_gravable = new Double(objNotaCredito.getObjOperacionGravada().getMonto());

        // cbc:TaxableAmount
        Element TaxableAmount_header_Gra = document.createElement("cbc:TaxableAmount");
        TaxableAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_base_gravable)));
        TaxSubtotal_Header_Gra.appendChild(TaxableAmount_header_Gra);
        Attr Atr_TaxableAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxableAmount_header_Gra.setValue(objNotaCredito.getTipMoneda());
        TaxableAmount_header_Gra.setAttributeNode(Atr_TaxableAmount_header_Gra);
        // cbc:TaxAmount 

        Double _igv = new Double(objNotaCredito.getIgvTotal());
        Element TaxAmount_header_Gra = document.createElement("cbc:TaxAmount");
        TaxAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_igv)));
        TaxSubtotal_Header_Gra.appendChild(TaxAmount_header_Gra);
        Attr Atr_TaxAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxAmount_header_Gra.setValue(objNotaCredito.getTipMoneda());
        TaxAmount_header_Gra.setAttributeNode(Atr_TaxAmount_header_Gra);
        // cac:TaxCategory
        Element TaxCategory_header_Gra = document.createElement("cac:TaxCategory");
        TaxSubtotal_Header_Gra.appendChild(TaxCategory_header_Gra);

        //cac:TaxScheme
        Element TaxScheme_header_Gra = document.createElement("cac:TaxScheme");
        TaxCategory_header_Gra.appendChild(TaxScheme_header_Gra);

        Element TaxScheme_header_id_Gra = document.createElement("cbc:ID");
        TaxScheme_header_id_Gra.appendChild(document.createTextNode("1000"));
        TaxScheme_header_Gra.appendChild(TaxScheme_header_id_Gra);
        //schemeAgencyName="PE:SUNAT"
        //Attr Atr_schemeAgencyNameID_Gra = document.createAttribute("schemeAgencyName");
        //Atr_schemeAgencyNameID_Gra.setValue("PE:SUNAT");
        //TaxScheme_header_id_Gra.setAttributeNode(Atr_schemeAgencyNameID_Gra);
        TaxScheme_header_id_Gra.setAttribute("schemeID", "UN/ECE 5153");
        TaxScheme_header_id_Gra.setAttribute("schemeAgencyID", "6");
        // cbc:Name
        Element TaxScheme_header_Name_Gra = document.createElement("cbc:Name");
        TaxScheme_header_Name_Gra.appendChild(document.createTextNode("IGV"));
        TaxScheme_header_Gra.appendChild(TaxScheme_header_Name_Gra);
        // cbc:TaxTypeCode
        Element TaxScheme_header_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
        TaxScheme_header_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
        TaxScheme_header_Gra.appendChild(TaxScheme_header_TaxTypeCode_Gra);

        Element LegalMonetaryTotal_Header = document.createElement("cac:LegalMonetaryTotal");
        element.appendChild(LegalMonetaryTotal_Header);

        // cbc:LineExtensionAmount
        Element LineExtensionAmount_Header = document.createElement("cbc:LineExtensionAmount");
        Double subTotal = Double.parseDouble(objNotaCredito.getImporteTotal()) - Double.parseDouble(objNotaCredito.getIgvTotal());
        LineExtensionAmount_Header.appendChild(document.createTextNode(Formato.granDinero(subTotal)));
        LegalMonetaryTotal_Header.appendChild(LineExtensionAmount_Header);

        Attr Atr_LineExtensionAmount_Header = document.createAttribute("currencyID");
        Atr_LineExtensionAmount_Header.setValue(objNotaCredito.getTipMoneda());
        LineExtensionAmount_Header.setAttributeNode(Atr_LineExtensionAmount_Header);

        // cbc:TaxInclusiveAmount
        Element TaxInclusiveAmount = document.createElement("cbc:TaxInclusiveAmount");
        TaxInclusiveAmount.appendChild(document.createTextNode(objNotaCredito.getImporteTotal()));
        LegalMonetaryTotal_Header.appendChild(TaxInclusiveAmount);

        Attr Atr_TaxInclusiveAmount = document.createAttribute("currencyID");
        Atr_TaxInclusiveAmount.setValue(objNotaCredito.getTipMoneda());
        TaxInclusiveAmount.setAttributeNode(Atr_TaxInclusiveAmount);

        // cbc:PayableAmount
        Element PayableAmount = document.createElement("cbc:PayableAmount");
        PayableAmount.appendChild(document.createTextNode(objNotaCredito.getImporteTotal()));
        LegalMonetaryTotal_Header.appendChild(PayableAmount);

        Attr Atr_PayableAmount = document.createAttribute("currencyID");
        Atr_PayableAmount.setValue(objNotaCredito.getTipMoneda());
        PayableAmount.setAttributeNode(Atr_PayableAmount);

        for (int linea = 0; linea < objNotaCredito.getListDetalleItemxml().size(); linea++) {
            // cac:InvoiceLine
            Element InvoiceLine = document.createElement("cac:CreditNoteLine");
            element.appendChild(InvoiceLine);

            // cbc:ID
            Element ID_Item = document.createElement("cbc:ID");
            ID_Item.appendChild(document.createTextNode(objNotaCredito.getListDetalleItemxml().get(linea).getNroOrden()));
            InvoiceLine.appendChild(ID_Item);

            // cbc:InvoicedQuantity 
            Element InvoicedQuantity = document.createElement("cbc:CreditedQuantity");
            InvoicedQuantity.appendChild(document.createTextNode("" + objNotaCredito.getListDetalleItemxml().get(linea).getCantidad()));
            InvoiceLine.appendChild(InvoicedQuantity);

            InvoicedQuantity.setAttribute("unitCode", objNotaCredito.getListDetalleItemxml().get(linea).getUnidadMedida());

            // cbc:LineExtensionAmount
            //Double _granDinero = new Double();
            Element LineExtensionAmount = document.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount.appendChild(document.createTextNode(objNotaCredito.getListDetalleItemxml().get(linea).getValUnitario()));
            InvoiceLine.appendChild(LineExtensionAmount);

            // currencyID
            Attr Atr_currencyID = document.createAttribute("currencyID");
            Atr_currencyID.setValue(objNotaCredito.getTipMoneda());
            LineExtensionAmount.setAttributeNode(Atr_currencyID);

            // cac:PricingReference
            Element PricingReference = document.createElement("cac:PricingReference");
            InvoiceLine.appendChild(PricingReference);

            // cac:AlternativeConditionPrice
            Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
            PricingReference.appendChild(AlternativeConditionPrice);

            // cbc:PriceAmount
            Element PriceAmount_item_reference = document.createElement("cbc:PriceAmount");
            Double get_precio_unit = new Double(objNotaCredito.getListDetalleItemxml().get(linea).getPrecioUnitario());
            //	PriceAmount_item_reference.appendChild(document.createTextNode(Formato.GranDinero(myDetalle[linea].get_precio_unit()-myDetalle[linea].get_desc_unit()    )));
            PriceAmount_item_reference.appendChild(document.createTextNode(Formato.granDinero(get_precio_unit)));
            AlternativeConditionPrice.appendChild(PriceAmount_item_reference);

            // currencyID
            Attr Atr_PriceAmount_Detail_ref = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail_ref.setValue(objNotaCredito.getTipMoneda());
            PriceAmount_item_reference.setAttributeNode(Atr_PriceAmount_Detail_ref);

            String _codigo_precio = "01";

            // cbc:PriceTypeCode
            Element PriceTypeCode = document.createElement("cbc:PriceTypeCode");
            PriceTypeCode.appendChild(document.createTextNode(_codigo_precio));
            AlternativeConditionPrice.appendChild(PriceTypeCode);

            // cac:TaxTotal
            Element TaxTotal_Detalle = document.createElement("cac:TaxTotal");
            InvoiceLine.appendChild(TaxTotal_Detalle);
            // cac:TaxAmount
            Element TaxAmount_Detalle = document.createElement("cbc:TaxAmount");
            TaxAmount_Detalle.appendChild(document.createTextNode(objNotaCredito.getListDetalleItemxml().get(linea).getIgv()));
            TaxTotal_Detalle.appendChild(TaxAmount_Detalle);
            Attr Atr_TaxableAmount_detalle_Gra01 = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra01.setValue(objNotaCredito.getTipMoneda());
            TaxAmount_Detalle.setAttributeNode(Atr_TaxableAmount_detalle_Gra01);
            // cac:TaxSubtotal
            Element TaxSubtotal_detalle_Gra = document.createElement("cac:TaxSubtotal");
            TaxTotal_Detalle.appendChild(TaxSubtotal_detalle_Gra);

            // cbc:TaxableAmount
            Element TaxableAmount_detalle_Gra = document.createElement("cbc:TaxableAmount");
            Double _precioUnitarioIGV = new Double(objNotaCredito.getListDetalleItemxml().get(linea).getValUnitario());
            TaxableAmount_detalle_Gra.appendChild(document.createTextNode("" + Formato.granDinero(_precioUnitarioIGV))); // PAUL
            TaxSubtotal_detalle_Gra.appendChild(TaxableAmount_detalle_Gra);
            Attr Atr_TaxableAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra.setValue(objNotaCredito.getTipMoneda());
            TaxableAmount_detalle_Gra.setAttributeNode(Atr_TaxableAmount_detalle_Gra);

            // cbc:TaxAmount 
            Element TaxAmount_detalle_Gra = document.createElement("cbc:TaxAmount");
            TaxAmount_detalle_Gra.appendChild(document.createTextNode(objNotaCredito.getListDetalleItemxml().get(linea).getIgv()));
            TaxSubtotal_detalle_Gra.appendChild(TaxAmount_detalle_Gra);
            Attr Atr_TaxAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxAmount_detalle_Gra.setValue(objNotaCredito.getTipMoneda());
            TaxAmount_detalle_Gra.setAttributeNode(Atr_TaxAmount_detalle_Gra);

            // cac:TaxCategory
            Element TaxCategory_detalle_Gra = document.createElement("cac:TaxCategory");
            TaxSubtotal_detalle_Gra.appendChild(TaxCategory_detalle_Gra);

            // cbc:Percent
            Element Percent_igv = document.createElement("cbc:Percent");
            Percent_igv.appendChild(document.createTextNode("18"));
            TaxCategory_detalle_Gra.appendChild(Percent_igv);

            // cbc:TaxExemptionReasonCode
            Element TaxExemptionReasonCode = document.createElement("cbc:TaxExemptionReasonCode");
            TaxExemptionReasonCode.appendChild(document.createTextNode("10"));
            TaxCategory_detalle_Gra.appendChild(TaxExemptionReasonCode);

            //cac:TaxScheme
            Element TaxScheme_detail = document.createElement("cac:TaxScheme");
            TaxScheme_detail.appendChild(document.createTextNode(""));
            TaxCategory_detalle_Gra.appendChild(TaxScheme_detail);

            // cbc:ID
            Element TaxScheme_detalle_id_Gra = document.createElement("cbc:ID");
            TaxScheme_detalle_id_Gra.appendChild(document.createTextNode("1000"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_id_Gra);

            // cbc:Name
            Element TaxScheme_detalle_Name_Gra = document.createElement("cbc:Name");
            TaxScheme_detalle_Name_Gra.appendChild(document.createTextNode("IGV"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_Name_Gra);
            // cbc:TaxTypeCode
            Element TaxScheme_detalle_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
            TaxScheme_detalle_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_TaxTypeCode_Gra);

            Element Item = document.createElement("cac:Item");
            InvoiceLine.appendChild(Item);

            Element Description1 = document.createElement("cbc:Description");
            Node cdataDescription = document.createCDATASection(objNotaCredito.getListDetalleItemxml().get(linea).getDescripcion());
            Description1.appendChild(cdataDescription);
            Item.appendChild(Description1);

            // cac:Price
            Element Price_item = document.createElement("cac:Price");
            InvoiceLine.appendChild(Price_item);

            // cbc:PriceAmount
            Element PriceAmount_item = document.createElement("cbc:PriceAmount");
            PriceAmount_item.appendChild(document.createTextNode(objNotaCredito.getListDetalleItemxml().get(linea).getValorVta()));
            Price_item.appendChild(PriceAmount_item);

            // currencyID
            Attr Atr_PriceAmount_Detail = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail.setValue(objNotaCredito.getTipMoneda());
            PriceAmount_item.setAttributeNode(Atr_PriceAmount_Detail);

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
