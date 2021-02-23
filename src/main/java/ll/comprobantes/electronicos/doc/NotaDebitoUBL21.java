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
import ll.entidades.xmlFacturacion.NotaDebitoxml;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author PauPar
 */
public class NotaDebitoUBL21 {

    private static NotaDebitoUBL21 _Instancia;

    private NotaDebitoUBL21() {
    }

    public static NotaDebitoUBL21 Instancia() {
        if (_Instancia == null) {
            _Instancia = new NotaDebitoUBL21();
        }
        return _Instancia;
    }

    public void c_XML(NotaDebitoxml objNotaDebito) throws Exception {

        String formatoArchivo = objNotaDebito.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaDebito.getTipoDocumento() + "-" + objNotaDebito.getNumeracionFactura();

        String ksTipe = "JKS";
        String ksFile = "C:\\Program Files\\Apache Software Foundation\\keys\\keystore.jks";
        String ksPass = "BXpNdA7b3csTr";
        String privateKsPass = "BXpNdA7b3csTr";
        String privateKsAlias = "(pe1_pfvp_730_sw_kpsc)_SAAVEDRA_19419239";
        String fileNameXMLnf = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\sf" + formatoArchivo + ".xml";
        String fileNameXMLcf = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\" + formatoArchivo + ".xml";

        String fileNameCNT = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\cntLetras_" + formatoArchivo + ".cnt";
        String fileNameLOG = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\log_" + formatoArchivo + ".log";
        String fileQR = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\" + formatoArchivo + ".png";
        String file417 = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\417" + formatoArchivo + ".png";
        String fileHash = "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\" + formatoArchivo + ".hash";

        createFile("A:\\DocumentosElectronicos\\DebitNote\\", formatoArchivo);
        File file_log = new File(fileNameLOG);

        if (!file_log.exists()) {
            file_log.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file_log);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        writeXML(fileNameXMLnf, objNotaDebito);
        FirmarDocumento.firmar(formatoArchivo, fileNameXMLnf, fileNameXMLcf, ksTipe, ksFile, ksPass, privateKsPass, privateKsAlias);

        ComprimirXML.comprimir("A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\" + formatoArchivo + ".zip", fileNameXMLcf, formatoArchivo + ".xml");

        String contenidoCode = objNotaDebito.getObjPersonaxmlEjecutor().getNroRuc() + "|" + objNotaDebito.getObjPersonaxmlEmisor().getTipoDoc() + "|"
                + objNotaDebito.getNumeracionFactura().substring(0, 3) + "|" + objNotaDebito.getNumeracionFactura().substring(5, 13) + "|" + objNotaDebito.getIgvTotal() + "|"
                + objNotaDebito.getImporteTotal() + "|" + objNotaDebito.getFechaEmision() + "|"
                + objNotaDebito.getObjPersonaxmlEjecutor().getTipoDoc() + "|" + objNotaDebito.getObjPersonaxmlEjecutor().getNroRuc();

        CodeQR.get(fileNameXMLcf, fileQR, contenidoCode);
        Code417.get(fileNameXMLcf, file417, contenidoCode);
        CodeHash.get(fileNameXMLcf, fileHash);

        H_main.conectarND(fileNameXMLcf, formatoArchivo);

        DocumentoCompraVentaNEG.Instancia().insertImgDocGeneradoND("A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\" + formatoArchivo + ".png", "A:\\DocumentosElectronicos\\DebitNote\\" + formatoArchivo + "\\417" + formatoArchivo + ".png", objNotaDebito);

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

    public static void writeXML(String fileNameXMLnf, NotaDebitoxml objNotaDebito) throws Exception {

        DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // agregamos la nombre pricipal dentro de este iran todos los elementos
        Element element = document.createElement("DebitNote");
        document.appendChild(element);

        Attr attr_xmlns = document.createAttribute("xmlns");
        attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:DebitNote-2");
        element.setAttributeNode(attr_xmlns);

//        // xmlns:xsi 11
//        Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
//        attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
//        element.setAttributeNode(attr_xmlns_xsi);
//        // xmlns:udt 5
//        Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
//        attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
//        element.setAttributeNode(attr_xmlns_udt);
//
//        // xmlns:qdt  8
//        Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
//        attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
//        element.setAttributeNode(attr_xmlns_qdt);
        // xmlns:ext 7
        Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
        attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        element.setAttributeNode(attr_xmlns_ext);

        // xmlns:ds 9
        Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
        attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
        element.setAttributeNode(attr_xmlns_ds);

        // xmlns:cbc 4
        Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
        attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        element.setAttributeNode(attr_xmlns_cbc);

//        // xmlns:ccts 6
//        Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
//        attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
//        element.setAttributeNode(attr_xmlns_ccts);
        // xmlns:cac  3
        Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
        attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        element.setAttributeNode(attr_xmlns_cac);

        /// NODO UBLExtensions
        Element UBLExtensions = document.createElement("ext:UBLExtensions");
        element.appendChild(UBLExtensions);

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
        SERIE.appendChild(document.createTextNode(objNotaDebito.getNumeracionFactura()));
        element.appendChild(SERIE);

        ///////////////////////////////////////////////// FIN NUMERO 7 -- TIPO DE DOCUMENTO
        /// NODO NUMERO 1  FECHA DE EMISION DEL DOCUMENTO -- fecEmision --- /Invoice/cbc:IssueDate 
        Element fecEmision = document.createElement("cbc:IssueDate");
        fecEmision.appendChild(document.createTextNode(objNotaDebito.getFechaEmision()));
        element.appendChild(fecEmision);

        //cbc:DocumentCurrencyCode
        Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
        DocumentCurrencyCode.appendChild(document.createTextNode(objNotaDebito.getTipMoneda()));
        element.appendChild(DocumentCurrencyCode);

        //cac:DiscrepancyResponse
        Element DiscrepancyResponse = document.createElement("cac:DiscrepancyResponse");
        element.appendChild(DiscrepancyResponse);

        // cbc:ReferenceID
        Element ReferenceID = document.createElement("cbc:ReferenceID");
        DiscrepancyResponse.appendChild(ReferenceID);
        ReferenceID.setTextContent(objNotaDebito.getObjFacturaxml().getNumeracionFactura());

        // cbc:ResponseCode
        Element ResponseCode = document.createElement("cbc:ResponseCode");
        DiscrepancyResponse.appendChild(ResponseCode);
        ResponseCode.setTextContent(objNotaDebito.getTipoNota());

        // cbc:Description
        Element Description = document.createElement("cbc:Description");
        DiscrepancyResponse.appendChild(Description);
        Description.setTextContent(objNotaDebito.getDescripcionMotivo());

        //cac:BillingReference
        Element BillingReference = document.createElement("cac:BillingReference");
        element.appendChild(BillingReference);

        //cac:InvoiceDocumentReference
        Element InvoiceDocumentReference = document.createElement("cac:InvoiceDocumentReference");
        BillingReference.appendChild(InvoiceDocumentReference);

        // cbc:ID
        Element ID_DocumentReference = document.createElement("cbc:ID");
        InvoiceDocumentReference.appendChild(ID_DocumentReference);
        ID_DocumentReference.setTextContent(objNotaDebito.getObjFacturaxml().getNumeracionFactura());

        //cbc:DocumentTypeCode
        Element DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
        InvoiceDocumentReference.appendChild(DocumentTypeCode);
        DocumentTypeCode.setTextContent("01");

        // cac:Signature  al nivel de raiz
        Element cacSignature = document.createElement("cac:Signature");
        element.appendChild(cacSignature);

        // cbc:ID
        Element cbcID = document.createElement("cbc:ID");
        cacSignature.appendChild(cbcID);
        cbcID.setTextContent(objNotaDebito.getObjPersonaxmlEmisor().getNroRuc());

        // cac:SignatoryParty
        Element SignatoryParty = document.createElement("cac:SignatoryParty");
        cacSignature.appendChild(SignatoryParty);

        //	cac:PartyIdentification
        Element PartyIdentification = document.createElement("cac:PartyIdentification");
        SignatoryParty.appendChild(PartyIdentification);

        // cbc:ID
        Element cbcID2 = document.createElement("cbc:ID");
        PartyIdentification.appendChild(cbcID2);
        cbcID2.setTextContent(objNotaDebito.getObjPersonaxmlEmisor().getNroRuc());

        //	cac:PartyName
        Element cac_PartyName = document.createElement("cac:PartyName");
        SignatoryParty.appendChild(cac_PartyName);

        // cbc:Name
        Element cbc_Name = document.createElement("cbc:Name");
        cac_PartyName.appendChild(cbc_Name);
        cbc_Name.setTextContent(objNotaDebito.getObjPersonaxmlEmisor().getRazonSocial());

        // cac:DigitalSignatureAttachment
        Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
        cacSignature.appendChild(DigitalSignatureAttachment);

        // cac:ExternalReference
        Element ExternalReference = document.createElement("cac:ExternalReference");
        DigitalSignatureAttachment.appendChild(ExternalReference);

        // cbc:URI
        Element URI2 = document.createElement("cbc:URI");
        ExternalReference.appendChild(URI2);
        URI2.setTextContent(objNotaDebito.getObjPersonaxmlEmisor().getNroRuc());

        // cac:AccountingSupplierParty EN RAIZ 
        Element AccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
        element.appendChild(AccountingSupplierParty);
        /* PAUL
        // cbc:CustomerAssignedAccountID
        Element CustomerAssignedAccountID = document.createElement("cbc:CustomerAssignedAccountID");
        CustomerAssignedAccountID.setTextContent(objNotaDebito.getObjPersonaxmlEjecutor().getNroRuc());
        AccountingSupplierParty.appendChild(CustomerAssignedAccountID);

        // cbc:AdditionalAccountID
        Element AdditionalAccountID = document.createElement("cbc:AdditionalAccountID");
        AdditionalAccountID.setTextContent(objNotaDebito.getObjPersonaxmlEjecutor().getTipoDoc());
        AccountingSupplierParty.appendChild(AdditionalAccountID);
         */
        // cac:Party
        Element Party_EMISOR = document.createElement("cac:Party");
        AccountingSupplierParty.appendChild(Party_EMISOR);

        // cac:PartyIdentification
        Element PartyIdentification_EMISOR = document.createElement("cac:PartyIdentification");
        Party_EMISOR.appendChild(PartyIdentification_EMISOR);

        // id
        Element PartyIdentification_ID_EMISOR = document.createElement("cbc:ID");
        PartyIdentification_EMISOR.appendChild(PartyIdentification_ID_EMISOR);
        PartyIdentification_ID_EMISOR.appendChild(document.createTextNode(objNotaDebito.getObjPersonaxmlEmisor().getNroRuc()));

        PartyIdentification_ID_EMISOR.setAttribute("schemeID", "6");

        // cac:PartyName
        Element PartyName_EMISOR = document.createElement("cac:PartyName");
        Party_EMISOR.appendChild(PartyName_EMISOR);

        // cbc:Name  (con caracteres especiales)
        Element Name_EMISOR = document.createElement("cbc:Name");
        Node cdata = document.createCDATASection(objNotaDebito.getObjPersonaxmlEmisor().getRazonSocial());
        Name_EMISOR.appendChild(cdata);
        PartyName_EMISOR.appendChild(Name_EMISOR);

        // cac:PartyLegalEntity
        Element PartyLegalEntity_EMISOR = document.createElement("cac:PartyLegalEntity");
        Party_EMISOR.appendChild(PartyLegalEntity_EMISOR);

        // cbc:RegistrationName
        Element RegistrationName_EMISOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_EMISOR = document.createCDATASection(objNotaDebito.getObjPersonaxmlEmisor().getRazonSocial());
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
        PartyIdentification_ID_RECEPTOR.appendChild(document.createTextNode(objNotaDebito.getObjPersonaxmlEjecutor().getNroRuc()));
        PartyIdentification_RECEPTOR.appendChild(PartyIdentification_ID_RECEPTOR);

        PartyIdentification_ID_RECEPTOR.setAttribute("schemeID", "6");

        // cac:PartyLegalEntity
        Element PartyLegalEntity_RECEPTOR = document.createElement("cac:PartyLegalEntity");
        Party_RECEPTOR.appendChild(PartyLegalEntity_RECEPTOR);

        // cbc:RegistrationName
        Element RegistrationName_RECEPTOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_RECEPTOR = document.createCDATASection(objNotaDebito.getObjPersonaxmlEjecutor().getRazonSocial());
        RegistrationName_RECEPTOR.appendChild(cdataRegistrationName_RECEPTOR);
        PartyLegalEntity_RECEPTOR.appendChild(RegistrationName_RECEPTOR);

        Element TaxTotal_Header = document.createElement("cac:TaxTotal");
        element.appendChild(TaxTotal_Header);

        // cbc:TaxAmount
        Element TaxAmount_Header = document.createElement("cbc:TaxAmount");
        TaxTotal_Header.appendChild(TaxAmount_Header);

        Attr attr_Moneda = document.createAttribute("currencyID");
        attr_Moneda.setValue(objNotaDebito.getTipMoneda());
        TaxAmount_Header.setAttributeNode(attr_Moneda);

        Double _impuestos = new Double(objNotaDebito.getIgvTotal());
        TaxAmount_Header.appendChild(document.createTextNode("" + Formato._xml(_impuestos)));

        // gravadas ///
        // cac:TaxSubtotal
        Element TaxSubtotal_Header_Gra = document.createElement("cac:TaxSubtotal");
        TaxTotal_Header.appendChild(TaxSubtotal_Header_Gra);

        Double _base_gravable = new Double(objNotaDebito.getObjOperacionGravada().getMonto());
        // cbc:TaxableAmount
        Element TaxableAmount_header_Gra = document.createElement("cbc:TaxableAmount");
        TaxableAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_base_gravable)));
        TaxSubtotal_Header_Gra.appendChild(TaxableAmount_header_Gra);
        Attr Atr_TaxableAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxableAmount_header_Gra.setValue(objNotaDebito.getTipMoneda());
        TaxableAmount_header_Gra.setAttributeNode(Atr_TaxableAmount_header_Gra);

        // cbc:TaxAmount 
        Double _igv = new Double(objNotaDebito.getIgvTotal());

        Element TaxAmount_header_Gra = document.createElement("cbc:TaxAmount");
        TaxAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_igv)));

        TaxSubtotal_Header_Gra.appendChild(TaxAmount_header_Gra);

        Attr Atr_TaxAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxAmount_header_Gra.setValue(objNotaDebito.getTipMoneda());
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

        Element RequestedMoneryTotal_Header = document.createElement("cac:RequestedMonetaryTotal");
        element.appendChild(RequestedMoneryTotal_Header);

        Element PayableAmount = document.createElement("cbc:PayableAmount");
        RequestedMoneryTotal_Header.appendChild(PayableAmount);
        PayableAmount.appendChild(document.createTextNode(objNotaDebito.getImporteTotal()));
        Attr Atr_PayableAmount = document.createAttribute("currencyID");
        Atr_PayableAmount.setValue(objNotaDebito.getTipMoneda());
        PayableAmount.setAttributeNode(Atr_PayableAmount);

        for (int linea = 0; linea < objNotaDebito.getListDetalleItemxml().size(); linea++) {

            // cac:InvoiceLine
            Element InvoiceLine = document.createElement("cac:DebitNoteLine");
            element.appendChild(InvoiceLine);

            // cbc:ID
            Element ID_Item = document.createElement("cbc:ID");
            ID_Item.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getNroOrden()));
            InvoiceLine.appendChild(ID_Item);

            // cbc:InvoicedQuantity 
            Element InvoicedQuantity = document.createElement("cbc:DebitedQuantity");
            InvoicedQuantity.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getCantidad()));
            InvoiceLine.appendChild(InvoicedQuantity);

            InvoicedQuantity.setAttribute("unitCode", objNotaDebito.getListDetalleItemxml().get(linea).getUnidadMedida());

            // cbc:LineExtensionAmount
            //Double _granDinero = new Double();
            Element LineExtensionAmount_DL = document.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount_DL.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getValUnitario()));
            InvoiceLine.appendChild(LineExtensionAmount_DL);

            Attr Atr_LineExtensionAmountDL = document.createAttribute("currencyID");
            Atr_LineExtensionAmountDL.setValue(objNotaDebito.getTipMoneda());
            LineExtensionAmount_DL.setAttributeNode(Atr_LineExtensionAmountDL);

            // cac:PricingReference
            Element PricingReference = document.createElement("cac:PricingReference");
            InvoiceLine.appendChild(PricingReference);

            // cac:AlternativeConditionPrice
            Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
            PricingReference.appendChild(AlternativeConditionPrice);

            // cbc:PriceAmount
            Element PriceAmount_item_reference = document.createElement("cbc:PriceAmount");
            Double get_precio_unit = new Double(objNotaDebito.getListDetalleItemxml().get(linea).getObjprecUnitario().getPrecioUnit());
            PriceAmount_item_reference.appendChild(document.createTextNode(Formato.granDinero(get_precio_unit)));
            AlternativeConditionPrice.appendChild(PriceAmount_item_reference);

            // currencyID
            Attr Atr_PriceAmount_Detail_ref = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail_ref.setValue(objNotaDebito.getTipMoneda());
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
            TaxAmount_Detalle.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getIgv()));
            TaxTotal_Detalle.appendChild(TaxAmount_Detalle);

            Attr Atr_TaxableAmount_detalle_Gra01 = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra01.setValue(objNotaDebito.getTipMoneda());
            TaxAmount_Detalle.setAttributeNode(Atr_TaxableAmount_detalle_Gra01);
            // cac:TaxSubtotal
            Element TaxSubtotal_detalle_Gra = document.createElement("cac:TaxSubtotal");
            TaxTotal_Detalle.appendChild(TaxSubtotal_detalle_Gra);

            // cbc:TaxableAmount
            Element TaxableAmount_detalle_Gra = document.createElement("cbc:TaxableAmount");
            Double _precioUnitarioIGV = new Double(objNotaDebito.getListDetalleItemxml().get(linea).getValUnitario());
            TaxableAmount_detalle_Gra.appendChild(document.createTextNode("" + Formato.granDinero(_precioUnitarioIGV))); // PAUL
            TaxSubtotal_detalle_Gra.appendChild(TaxableAmount_detalle_Gra);
            Attr Atr_TaxableAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra.setValue(objNotaDebito.getTipMoneda());
            TaxableAmount_detalle_Gra.setAttributeNode(Atr_TaxableAmount_detalle_Gra);

            // cbc:TaxAmount 
            Element TaxAmount_detalle_Gra = document.createElement("cbc:TaxAmount");
            TaxAmount_detalle_Gra.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getIgv()));
            TaxSubtotal_detalle_Gra.appendChild(TaxAmount_detalle_Gra);

            Attr Atr_TaxAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxAmount_detalle_Gra.setValue(objNotaDebito.getTipMoneda());
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
            Node cdataDescription = document.createCDATASection(objNotaDebito.getListDetalleItemxml().get(linea).getDescripcion());
            Description1.appendChild(cdataDescription);
            Item.appendChild(Description1);

            // cac:Price
            Element Price_item = document.createElement("cac:Price");
            InvoiceLine.appendChild(Price_item);

            // cbc:PriceAmount
            Element PriceAmount_item = document.createElement("cbc:PriceAmount");
            PriceAmount_item.appendChild(document.createTextNode(objNotaDebito.getListDetalleItemxml().get(linea).getValorVta()));
            Price_item.appendChild(PriceAmount_item);

            // currencyID
            Attr Atr_PriceAmount_Detail = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail.setValue(objNotaDebito.getTipMoneda());
            PriceAmount_item.setAttributeNode(Atr_PriceAmount_Detail);

        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        //transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                "yes");

        document.setXmlStandalone(
                true);
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
