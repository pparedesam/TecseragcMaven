package ll.comprobantes.electronicos.doc;

import ll.comprobantes.electronicos.codigos.CodeHash;
import ll.comprobantes.electronicos.codigos.Code417;
import ll.comprobantes.electronicos.codigos.CodeQR;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import ll.comprobantes.electronicos.use.ComprimirXML;
import ll.comprobantes.electronicos.use.FirmarDocumento;
import ll.comprobantes.electronicos.formats.Formato;
import ll.entidades.xmlFacturacion.Facturaxml;
import ll.comprobantes.electronicos.connection.H_main;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;

public class FacturaUBL21 {

    private static FacturaUBL21 _Instancia;

    private FacturaUBL21() {
    }

    public static FacturaUBL21 Instancia() {
        if (_Instancia == null) {
            _Instancia = new FacturaUBL21();
        }
        return _Instancia;
    }

    public void c_XML(Facturaxml objFactura) throws Exception {

        String formatoArchivo = objFactura.getObjPersonaxmlEmisor().getNroRuc() + "-" + objFactura.getTipoDocumento() + "-" + objFactura.getNumeracionFactura();

        String ksTipe = "JKS";
        String ksFile = "C:\\Program Files\\Apache Software Foundation\\keys\\keystore.jks";
        String ksPass = "BXpNdA7b3csTr";
        String privateKsPass = "BXpNdA7b3csTr";
        String privateKsAlias = "(pe1_pfvp_730_sw_kpsc)_SAAVEDRA_19419239";
        String fileNameXMLnf = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\sf" + formatoArchivo + ".xml";
        String fileNameXMLcf = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\" + formatoArchivo + ".xml";

        String fileNameCNT = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\cntLetras_" + formatoArchivo + ".cnt";
        String fileNameLOG = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\log_" + formatoArchivo + ".log";
        String fileQR = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\" + formatoArchivo + ".png";
        String file417 = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\417" + formatoArchivo + ".png";
        String fileHash = "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\" + formatoArchivo + ".hash";

        createFile("A:\\DocumentosElectronicos\\Invoice\\", formatoArchivo);
        File file_log = new File(fileNameLOG);

        if (!file_log.exists()) {
            file_log.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file_log);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        writeXML(fileNameXMLnf, objFactura);

        FirmarDocumento.firmar(formatoArchivo, fileNameXMLnf, fileNameXMLcf, ksTipe, ksFile, ksPass, privateKsPass, privateKsAlias);

        ComprimirXML.comprimir("A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\" + formatoArchivo + ".zip", fileNameXMLcf, formatoArchivo + ".xml");

        String contenidoCode = objFactura.getObjPersonaxmlEjecutor().getNroRuc() + "|" + objFactura.getObjPersonaxmlEmisor().getTipoDoc() + "|"
                + objFactura.getNumeracionFactura().substring(0, 3) + "|" + objFactura.getNumeracionFactura().substring(5, 13) + "|" + objFactura.getIgvTotal() + "|"
                + objFactura.getImporteTotal() + "|" + objFactura.getFechaEmision() + "|"
                + objFactura.getObjPersonaxmlEjecutor().getTipoDoc() + "|" + objFactura.getObjPersonaxmlEjecutor().getNroRuc();

        CodeQR.get(fileNameXMLcf, fileQR, contenidoCode);
        Code417.get(fileNameXMLcf, file417, contenidoCode);
        CodeHash.get(fileNameXMLcf, fileHash);

        H_main.conectar(fileNameXMLcf, formatoArchivo);

        DocumentoCompraVentaNEG.Instancia().insertImgDocGenerado("A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\" + formatoArchivo + ".png", "A:\\DocumentosElectronicos\\Invoice\\" + formatoArchivo + "\\417" + formatoArchivo + ".png", objFactura);

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

    public static void writeXML(String fileNameXMLnf, Facturaxml objFactura) throws Exception {

        DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element element = document.createElement("Invoice");
        document.appendChild(element);

        Attr attr_xmlns_xsi = document.createAttribute("xmlns:xsi");
        attr_xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        element.setAttributeNode(attr_xmlns_xsi);

        Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
        attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        element.setAttributeNode(attr_xmlns_cac);

        Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
        attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        element.setAttributeNode(attr_xmlns_cbc);

        Attr attr_xmlns_ccts = document.createAttribute("xmlns:ccts");
        attr_xmlns_ccts.setValue("urn:un:unece:uncefact:documentation:2");
        element.setAttributeNode(attr_xmlns_ccts);

        Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
        attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
        element.setAttributeNode(attr_xmlns_ds);

        Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
        attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        element.setAttributeNode(attr_xmlns_ext);

        Attr attr_xmlns_qdt = document.createAttribute("xmlns:qdt");
        attr_xmlns_qdt.setValue("urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        element.setAttributeNode(attr_xmlns_qdt);

        //xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2"
        Attr attr_xmlns_udt = document.createAttribute("xmlns:udt");
        attr_xmlns_udt.setValue("urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        element.setAttributeNode(attr_xmlns_udt);

        Attr attr_xmlns = document.createAttribute("xmlns");
        attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
        element.setAttributeNode(attr_xmlns);

        Element UBLExtensions = document.createElement("ext:UBLExtensions");
        element.appendChild(UBLExtensions);

        Element UBLVersionID = document.createElement("cbc:UBLVersionID");
        UBLVersionID.appendChild(document.createTextNode("2.1"));
        element.appendChild(UBLVersionID);

        // cbc:CustomizationID
        Element CustomizationID = document.createElement("cbc:CustomizationID");
        CustomizationID.appendChild(document.createTextNode("2.0"));
        element.appendChild(CustomizationID);

        Element SERIE = document.createElement("cbc:ID");
        SERIE.appendChild(document.createTextNode(objFactura.getNumeracionFactura()));
        element.appendChild(SERIE);

        Element fecEmision = document.createElement("cbc:IssueDate");
        fecEmision.appendChild(document.createTextNode(objFactura.getFechaEmision()));
        element.appendChild(fecEmision);

        Element InvoiceTypeCode = document.createElement("cbc:InvoiceTypeCode");
        InvoiceTypeCode.appendChild(document.createTextNode(objFactura.getTipoDocumento()));
        element.appendChild(InvoiceTypeCode);

        if (!objFactura.getObservacion().equals("")) {
            Element Note = document.createElement("cbc:Note");
            Node cdataObservacion = document.createCDATASection(objFactura.getObservacion());
            Note.appendChild(cdataObservacion);
            element.appendChild(Note);
        }
        /**/

        Element DocumentCurrencyCode = document.createElement("cbc:DocumentCurrencyCode");
        DocumentCurrencyCode.appendChild(document.createTextNode(objFactura.getTipMoneda()));
        element.appendChild(DocumentCurrencyCode);

        //cac:DespatchDocumentReference
        for (int i = 0; i < objFactura.getListGuiaRemisionxml().size(); i++) {

            Element DespatchDocumentReference = document.createElement("cac:DespatchDocumentReference");
            element.appendChild(DespatchDocumentReference);

            // id
            Element DespatchDocumentReference_ID = document.createElement("cbc:ID");
            DespatchDocumentReference_ID.appendChild(document.createTextNode(objFactura.getListGuiaRemisionxml().get(i).getNroGuia()));
            DespatchDocumentReference.appendChild(DespatchDocumentReference_ID);

            // documentTypeCode
            Element DespatchDocumentReference_DocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
            DespatchDocumentReference_DocumentTypeCode.appendChild(document.createTextNode(objFactura.getListGuiaRemisionxml().get(i).getTipoGuia()));
            DespatchDocumentReference.appendChild(DespatchDocumentReference_DocumentTypeCode);

            DespatchDocumentReference_DocumentTypeCode.setAttribute("listAgencyName", "PE:SUNAT");
            DespatchDocumentReference_DocumentTypeCode.setAttribute("listName", "Tipo de Documento");
            DespatchDocumentReference_DocumentTypeCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01");

        }

        // cac:Signature
        Element Signature = document.createElement("cac:Signature");
        element.appendChild(Signature);

        // id
        Element Signature_ID = document.createElement("cbc:ID");
        Signature_ID.appendChild(document.createTextNode("20315295573"));
        Signature.appendChild(Signature_ID);

        // cac:SignatoryParty
        Element SignatoryParty = document.createElement("cac:SignatoryParty");
        Signature.appendChild(SignatoryParty);

        // cac:PartyIdentification
        Element PartyIdentification = document.createElement("cac:PartyIdentification");
        SignatoryParty.appendChild(PartyIdentification);

        // id
        Element PartyIdentification_ID = document.createElement("cbc:ID");
        PartyIdentification_ID.appendChild(document.createTextNode("20315295573"));
        PartyIdentification.appendChild(PartyIdentification_ID);

        // cac:PartyName
        Element PartyName_SIG = document.createElement("cac:PartyName");
        SignatoryParty.appendChild(PartyName_SIG);

        // cbc:Name
        Element Name_sig = document.createElement("cbc:Name");
        Name_sig.appendChild(document.createTextNode("COMUNIDAD CAMPESINA LLACUABAMBA"));
        PartyName_SIG.appendChild(Name_sig);

        // cac:DigitalSignatureAttachment
        Element DigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
        Signature.appendChild(DigitalSignatureAttachment);

        // cac:ExternalReference
        Element ExternalReference = document.createElement("cac:ExternalReference");
        DigitalSignatureAttachment.appendChild(ExternalReference);

        // cbc:URI
        Element URI = document.createElement("cbc:URI");
        URI.appendChild(document.createTextNode("20315295573"));
        ExternalReference.appendChild(URI);

        ////////  DEFIINCION DE DATOS DL EMISOR
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
        PartyIdentification_ID_EMISOR.appendChild(document.createTextNode(objFactura.getObjPersonaxmlEmisor().getNroRuc()));
        PartyIdentification_EMISOR.appendChild(PartyIdentification_ID_EMISOR);

        PartyIdentification_ID_EMISOR.setAttribute("schemeID", "6");

        // cac:PartyName
        Element PartyName_EMISOR = document.createElement("cac:PartyName");
        Party_EMISOR.appendChild(PartyName_EMISOR);

        // cbc:Name  (con caracteres especiales)
        Element Name_EMISOR = document.createElement("cbc:Name");
        Node cdata = document.createCDATASection(objFactura.getNombreComercial());
        Name_EMISOR.appendChild(cdata);
        PartyName_EMISOR.appendChild(Name_EMISOR);

        // cac:PartyLegalEntity
        Element PartyLegalEntity_EMISOR = document.createElement("cac:PartyLegalEntity");
        Party_EMISOR.appendChild(PartyLegalEntity_EMISOR);

        // cbc:RegistrationName
        Element RegistrationName_EMISOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_EMISOR = document.createCDATASection(objFactura.getObjPersonaxmlEmisor().getRazonSocial());
        RegistrationName_EMISOR.appendChild(cdataRegistrationName_EMISOR);
        PartyLegalEntity_EMISOR.appendChild(RegistrationName_EMISOR);

        // cac:RegistrationAddress
        Element RegistrationAddress_EMISOR = document.createElement("cac:RegistrationAddress");
        PartyLegalEntity_EMISOR.appendChild(RegistrationAddress_EMISOR);

        // cbc:AddressTypeCode
        Element AddressTypeCode_EMISOR = document.createElement("cbc:AddressTypeCode");
        AddressTypeCode_EMISOR.appendChild(document.createTextNode("0002")); //Solicitar alta de Anexo a SUNAT
        RegistrationAddress_EMISOR.appendChild(AddressTypeCode_EMISOR);

        ////////////////  datos del emisor
        // cac:AccountingSupplierParty EN RAIZ 
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
        PartyIdentification_ID_RECEPTOR.appendChild(document.createTextNode(objFactura.getObjPersonaxmlEjecutor().getNroRuc()));
        PartyIdentification_RECEPTOR.appendChild(PartyIdentification_ID_RECEPTOR);

        PartyIdentification_ID_RECEPTOR.setAttribute("schemeID", objFactura.getObjPersonaxmlEjecutor().getTipoDoc());
        PartyIdentification_ID_RECEPTOR.setAttribute("schemeName", "Documento de Identidad");
        PartyIdentification_ID_RECEPTOR.setAttribute("schemeAgencyName", "PE:SUNAT");
        PartyIdentification_ID_RECEPTOR.setAttribute("schemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06");

        // cac:PartyLegalEntity
        Element PartyLegalEntity_RECEPTOR = document.createElement("cac:PartyLegalEntity");
        Party_RECEPTOR.appendChild(PartyLegalEntity_RECEPTOR);

        // cbc:RegistrationName
        Element RegistrationName_RECEPTOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_RECEPTOR = document.createCDATASection(objFactura.getObjPersonaxmlEjecutor().getRazonSocial());
        RegistrationName_RECEPTOR.appendChild(cdataRegistrationName_RECEPTOR);
        PartyLegalEntity_RECEPTOR.appendChild(RegistrationName_RECEPTOR);
        //Detalle

        // cac:TaxTotal
        Element TaxTotal_Header = document.createElement("cac:TaxTotal");
        //	TaxTotal_Header.appendChild(document.createTextNode(""+_tax_total));
        element.appendChild(TaxTotal_Header);

        // cbc:TaxAmount
        Element TaxAmount_Header = document.createElement("cbc:TaxAmount");

        Attr attr_Moneda = document.createAttribute("currencyID");
        attr_Moneda.setValue(objFactura.getTipMoneda());
        TaxAmount_Header.setAttributeNode(attr_Moneda);

        Double _impuestos = new Double(objFactura.getIgvTotal());

        TaxAmount_Header.appendChild(document.createTextNode("" + Formato._xml(_impuestos)));
        TaxTotal_Header.appendChild(TaxAmount_Header);

        // gravadas ///
        // cac:TaxSubtotal
        Element TaxSubtotal_Header_Gra = document.createElement("cac:TaxSubtotal");
        TaxTotal_Header.appendChild(TaxSubtotal_Header_Gra);

        Double _base_gravable = new Double(objFactura.getObjOperacionGravada().getMonto());

        // cbc:TaxableAmount
        Element TaxableAmount_header_Gra = document.createElement("cbc:TaxableAmount");
        TaxableAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_base_gravable)));
        TaxSubtotal_Header_Gra.appendChild(TaxableAmount_header_Gra);
        Attr Atr_TaxableAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxableAmount_header_Gra.setValue(objFactura.getTipMoneda());
        TaxableAmount_header_Gra.setAttributeNode(Atr_TaxableAmount_header_Gra);
        // cbc:TaxAmount 

        Double _igv = new Double(objFactura.getIgvTotal());
        Element TaxAmount_header_Gra = document.createElement("cbc:TaxAmount");
        TaxAmount_header_Gra.appendChild(document.createTextNode("" + Formato._xml(_igv)));
        TaxSubtotal_Header_Gra.appendChild(TaxAmount_header_Gra);
        Attr Atr_TaxAmount_header_Gra = document.createAttribute("currencyID");
        Atr_TaxAmount_header_Gra.setValue(objFactura.getTipMoneda());
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
        Attr Atr_schemeAgencyNameID_Gra = document.createAttribute("schemeAgencyName");
        Atr_schemeAgencyNameID_Gra.setValue("PE:SUNAT");
        TaxScheme_header_id_Gra.setAttributeNode(Atr_schemeAgencyNameID_Gra);
        TaxScheme_header_id_Gra.setAttribute("schemeID", "UN/ECE 5305");
        TaxScheme_header_id_Gra.setAttribute("schemeAgencyID", "6");
        // cbc:Name
        Element TaxScheme_header_Name_Gra = document.createElement("cbc:Name");
        TaxScheme_header_Name_Gra.appendChild(document.createTextNode("IGV"));
        TaxScheme_header_Gra.appendChild(TaxScheme_header_Name_Gra);
        // cbc:TaxTypeCode
        Element TaxScheme_header_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
        TaxScheme_header_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
        TaxScheme_header_Gra.appendChild(TaxScheme_header_TaxTypeCode_Gra);
        /////////////////////////////////////////////////////////// FIN DE GRABADAS

        // cac:LegalMonetaryTotal
        Element LegalMonetaryTotal_Header = document.createElement("cac:LegalMonetaryTotal");
        element.appendChild(LegalMonetaryTotal_Header);

        // cbc:LineExtensionAmount
        Element LineExtensionAmount_Header = document.createElement("cbc:LineExtensionAmount");
        Double subTotal = Double.parseDouble(objFactura.getImporteTotal()) - Double.parseDouble(objFactura.getIgvTotal());
        LineExtensionAmount_Header.appendChild(document.createTextNode(Formato.granDinero(subTotal)));
        LegalMonetaryTotal_Header.appendChild(LineExtensionAmount_Header);

        Attr Atr_LineExtensionAmount_Header = document.createAttribute("currencyID");
        Atr_LineExtensionAmount_Header.setValue(objFactura.getTipMoneda());
        LineExtensionAmount_Header.setAttributeNode(Atr_LineExtensionAmount_Header);

        // cbc:TaxInclusiveAmount
        Element TaxInclusiveAmount = document.createElement("cbc:TaxInclusiveAmount");
        TaxInclusiveAmount.appendChild(document.createTextNode(objFactura.getImporteTotal()));
        LegalMonetaryTotal_Header.appendChild(TaxInclusiveAmount);

        Attr Atr_TaxInclusiveAmount = document.createAttribute("currencyID");
        Atr_TaxInclusiveAmount.setValue(objFactura.getTipMoneda());
        TaxInclusiveAmount.setAttributeNode(Atr_TaxInclusiveAmount);

        // cbc:PayableAmount
        Element PayableAmount = document.createElement("cbc:PayableAmount");
        PayableAmount.appendChild(document.createTextNode(objFactura.getImporteTotal()));
        LegalMonetaryTotal_Header.appendChild(PayableAmount);

        Attr Atr_PayableAmount = document.createAttribute("currencyID");
        Atr_PayableAmount.setValue(objFactura.getTipMoneda());
        PayableAmount.setAttributeNode(Atr_PayableAmount);

        for (int linea = 0; linea < objFactura.getListDetalleItemxml().size(); linea++) {

            // cac:InvoiceLine
            Element InvoiceLine = document.createElement("cac:InvoiceLine");
            element.appendChild(InvoiceLine);

            // cbc:ID
            Element ID_Item = document.createElement("cbc:ID");
            ID_Item.appendChild(document.createTextNode(objFactura.getListDetalleItemxml().get(linea).getNroOrden()));
            InvoiceLine.appendChild(ID_Item);

            // cbc:InvoicedQuantity 
            Element InvoicedQuantity = document.createElement("cbc:InvoicedQuantity");
            InvoicedQuantity.appendChild(document.createTextNode("" + objFactura.getListDetalleItemxml().get(linea).getCantidad()));
            InvoiceLine.appendChild(InvoicedQuantity);

            InvoicedQuantity.setAttribute("unitCode", objFactura.getListDetalleItemxml().get(linea).getUnidadMedida());

            // cbc:LineExtensionAmount
            //Double _granDinero = new Double();
            Element LineExtensionAmount = document.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount.appendChild(document.createTextNode(objFactura.getListDetalleItemxml().get(linea).getValUnitario()));
            InvoiceLine.appendChild(LineExtensionAmount);

            // currencyID
            Attr Atr_currencyID = document.createAttribute("currencyID");
            Atr_currencyID.setValue(objFactura.getTipMoneda());
            LineExtensionAmount.setAttributeNode(Atr_currencyID);

            // cac:PricingReference
            Element PricingReference = document.createElement("cac:PricingReference");
            InvoiceLine.appendChild(PricingReference);

            // cac:AlternativeConditionPrice
            Element AlternativeConditionPrice = document.createElement("cac:AlternativeConditionPrice");
            PricingReference.appendChild(AlternativeConditionPrice);

            // cbc:PriceAmount
            Element PriceAmount_item_reference = document.createElement("cbc:PriceAmount");
            Double get_precio_unit = new Double(objFactura.getListDetalleItemxml().get(linea).getPrecioUnitario());
            //	PriceAmount_item_reference.appendChild(document.createTextNode(Formato.GranDinero(myDetalle[linea].get_precio_unit()-myDetalle[linea].get_desc_unit()    )));
            PriceAmount_item_reference.appendChild(document.createTextNode(Formato.granDinero(get_precio_unit)));
            AlternativeConditionPrice.appendChild(PriceAmount_item_reference);

            // currencyID
            Attr Atr_PriceAmount_Detail_ref = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail_ref.setValue(objFactura.getTipMoneda());
            PriceAmount_item_reference.setAttributeNode(Atr_PriceAmount_Detail_ref);

            String _codigo_precio = "01";

            // cbc:PriceTypeCode
            Element PriceTypeCode = document.createElement("cbc:PriceTypeCode");
            PriceTypeCode.appendChild(document.createTextNode(_codigo_precio));
            AlternativeConditionPrice.appendChild(PriceTypeCode);

            PriceTypeCode.setAttribute("listAgencyName", "PE:SUNAT");
            PriceTypeCode.setAttribute("listName", "Tipo de Precio");
            PriceTypeCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16");

            // cac:TaxTotal
            Element TaxTotal_Detalle = document.createElement("cac:TaxTotal");
            InvoiceLine.appendChild(TaxTotal_Detalle);
            // cac:TaxAmount
            Element TaxAmount_Detalle = document.createElement("cbc:TaxAmount");
            TaxAmount_Detalle.appendChild(document.createTextNode(objFactura.getListDetalleItemxml().get(linea).getIgv()));
            TaxTotal_Detalle.appendChild(TaxAmount_Detalle);
            Attr Atr_TaxableAmount_detalle_Gra01 = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra01.setValue(objFactura.getTipMoneda());
            TaxAmount_Detalle.setAttributeNode(Atr_TaxableAmount_detalle_Gra01);
            // cac:TaxSubtotal
            Element TaxSubtotal_detalle_Gra = document.createElement("cac:TaxSubtotal");
            TaxTotal_Detalle.appendChild(TaxSubtotal_detalle_Gra);
            // cbc:TaxableAmount
            Element TaxableAmount_detalle_Gra = document.createElement("cbc:TaxableAmount");
            Double _precioUnitarioIGV = new Double(objFactura.getListDetalleItemxml().get(linea).getValUnitario());
            TaxableAmount_detalle_Gra.appendChild(document.createTextNode("" + Formato.granDinero(_precioUnitarioIGV))); // PAUL
            TaxSubtotal_detalle_Gra.appendChild(TaxableAmount_detalle_Gra);
            Attr Atr_TaxableAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxableAmount_detalle_Gra.setValue(objFactura.getTipMoneda());
            TaxableAmount_detalle_Gra.setAttributeNode(Atr_TaxableAmount_detalle_Gra);
            // cbc:TaxAmount 
            Element TaxAmount_detalle_Gra = document.createElement("cbc:TaxAmount");
            TaxAmount_detalle_Gra.appendChild(document.createTextNode(objFactura.getListDetalleItemxml().get(linea).getIgv()));
            TaxSubtotal_detalle_Gra.appendChild(TaxAmount_detalle_Gra);
            Attr Atr_TaxAmount_detalle_Gra = document.createAttribute("currencyID");
            Atr_TaxAmount_detalle_Gra.setValue(objFactura.getTipMoneda());
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
            TaxExemptionReasonCode.setAttribute("listAgencyName", "PE:SUNAT");
            TaxExemptionReasonCode.setAttribute("listName", "Afectación del IGV");
            TaxExemptionReasonCode.setAttribute("listURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07");

            //cac:TaxScheme
            Element TaxScheme_detail = document.createElement("cac:TaxScheme");
            TaxScheme_detail.appendChild(document.createTextNode(""));
            TaxCategory_detalle_Gra.appendChild(TaxScheme_detail);

            // cbc:ID
            Element TaxScheme_detalle_id_Gra = document.createElement("cbc:ID");
            TaxScheme_detalle_id_Gra.appendChild(document.createTextNode("1000"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_id_Gra);
            // schemeID="UN/ECE 5153"
            Attr Atr_id_schemeAgencyName_Gra = document.createAttribute("schemeAgencyName");
            Atr_id_schemeAgencyName_Gra.setValue("PE:SUNAT");
            TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeAgencyName_Gra);
            //  schemeName="Tax Scheme Identifier"
            Attr Atr_id_schemeID_Gra = document.createAttribute("schemeID");
            Atr_id_schemeID_Gra.setValue("UN/ECE 5153");
            TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeID_Gra);
            // schemeAgencyName="United Nations Economic Commission for Europe"
            Attr Atr_id_schemeName_Gra = document.createAttribute("schemeName");
            Atr_id_schemeName_Gra.setValue("Codigo de tributos");
            TaxScheme_detalle_id_Gra.setAttributeNode(Atr_id_schemeName_Gra);
            // cbc:Name
            Element TaxScheme_detalle_Name_Gra = document.createElement("cbc:Name");
            TaxScheme_detalle_Name_Gra.appendChild(document.createTextNode("IGV"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_Name_Gra);
            // cbc:TaxTypeCode
            Element TaxScheme_detalle_TaxTypeCode_Gra = document.createElement("cbc:TaxTypeCode");
            TaxScheme_detalle_TaxTypeCode_Gra.appendChild(document.createTextNode("VAT"));
            TaxScheme_detail.appendChild(TaxScheme_detalle_TaxTypeCode_Gra);

            // listAgencyName
            Attr Atr_TaxExemptionReasonCode_slistAgencyName_Gra = document.createAttribute("listAgencyName");
            Atr_TaxExemptionReasonCode_slistAgencyName_Gra.setValue("PE:SUNAT");
            TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_slistAgencyName_Gra);

            //slistName
            Attr Atr_TaxExemptionReasonCode_listName_Gra = document.createAttribute("listName");
            Atr_TaxExemptionReasonCode_listName_Gra.setValue("Afectacion del IGV");
            TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_listName_Gra);

            //listUR
            Attr Atr_TaxExemptionReasonCode_listUR_Gra = document.createAttribute("listURI");
            Atr_TaxExemptionReasonCode_listUR_Gra.setValue("urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07");
            TaxExemptionReasonCode.setAttributeNode(Atr_TaxExemptionReasonCode_listUR_Gra);

            Element Item = document.createElement("cac:Item");
            InvoiceLine.appendChild(Item);

            Element Description = document.createElement("cbc:Description");
            Node cdataDescription = document.createCDATASection(objFactura.getListDetalleItemxml().get(linea).getDescripcion());
            Description.appendChild(cdataDescription);
            Item.appendChild(Description);

            // cac:Price
            Element Price_item = document.createElement("cac:Price");
            InvoiceLine.appendChild(Price_item);

            // cbc:PriceAmount
            Element PriceAmount_item = document.createElement("cbc:PriceAmount");
            PriceAmount_item.appendChild(document.createTextNode(objFactura.getListDetalleItemxml().get(linea).getValorVta()));
            Price_item.appendChild(PriceAmount_item);

            // currencyID
            Attr Atr_PriceAmount_Detail = document.createAttribute("currencyID");
            Atr_PriceAmount_Detail.setValue(objFactura.getTipMoneda());
            PriceAmount_item.setAttributeNode(Atr_PriceAmount_Detail);

        }

        ///  aqui ira el tipo de documento
        InvoiceTypeCode.setAttribute("listID", "0101");
        InvoiceTypeCode.setAttribute("name", "Tipo de Operacion");
        InvoiceTypeCode.setAttribute("listSchemeURI", "urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51");

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
