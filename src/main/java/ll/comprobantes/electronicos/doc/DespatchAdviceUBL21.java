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
import ll.entidades.xmlFacturacion.GuiaRemisionxml;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;

public class DespatchAdviceUBL21 {

    private static DespatchAdviceUBL21 _Instancia;

    private DespatchAdviceUBL21() {
    }

    public static DespatchAdviceUBL21 Instancia() {
        if (_Instancia == null) {
            _Instancia = new DespatchAdviceUBL21();
        }
        return _Instancia;
    }

    public void c_XML(GuiaRemisionxml objGuiaRemision) throws Exception {

        String formatoArchivo = objGuiaRemision.getObjPersonaRemitente().getNroRuc() + "-" + objGuiaRemision.getTipoGuia() + "-" + objGuiaRemision.getNroGuia();

        String ksTipe = "JKS";
        String ksFile = "C:\\Program Files\\Apache Software Foundation\\keys\\keystore.jks";
        String ksPass = "BXpNdA7b3csTr";
        String privateKsPass = "BXpNdA7b3csTr";
        String privateKsAlias = "(pe1_pfvp_730_sw_kpsc)_SAAVEDRA_19419239";
        String fileNameXMLnf = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\sf" + formatoArchivo + ".xml";
        String fileNameXMLcf = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\" + formatoArchivo + ".xml";

        String fileNameCNT = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\cntLetras_" + formatoArchivo + ".cnt";
        String fileNameLOG = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\log_" + formatoArchivo + ".log";
        String fileQR = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\" + formatoArchivo + ".png";
        String file417 = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\417" + formatoArchivo + ".png";
        String fileHash = "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\" + formatoArchivo + ".hash";

        createFile("A:\\DocumentosElectronicos\\DespatchAdvice\\", formatoArchivo);
        File file_log = new File(fileNameLOG);

        if (!file_log.exists()) {
            file_log.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file_log);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        writeXML(fileNameXMLnf, objGuiaRemision);

        FirmarDocumento.firmar(formatoArchivo, fileNameXMLnf, fileNameXMLcf, ksTipe, ksFile, ksPass, privateKsPass, privateKsAlias);
        ComprimirXML.comprimir("A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\" + formatoArchivo + ".zip", fileNameXMLcf, formatoArchivo + ".xml");

        String contenidoCode = objGuiaRemision.getObjPersonaRemitente().getNroRuc() + "|" + objGuiaRemision.getObjPersonaRemitente().getTipoDoc() + "|"
                + objGuiaRemision.getNroGuia().substring(0, 3) + "|" + objGuiaRemision.getNroGuia().substring(5, 13) + "|" + objGuiaRemision.getPesoBruto() + "|"
                + objGuiaRemision.getPesoBruto() + "|" + objGuiaRemision.getFechaGuia() + "|"
                + objGuiaRemision.getObjPersonaDestinatario().getTipoDoc() + "|" + objGuiaRemision.getObjPersonaDestinatario().getNroRuc();
        CodeQR.get(fileNameXMLcf, fileQR, contenidoCode);
        Code417.get(fileNameXMLcf, file417, contenidoCode);
        CodeHash.get(fileNameXMLcf, fileHash);
        H_main.conectarGM(fileNameXMLcf, formatoArchivo);

        DocumentoCompraVentaNEG.Instancia().insertImgDocGeneradoGRM("A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\" + formatoArchivo + ".png", "A:\\DocumentosElectronicos\\DespatchAdvice\\" + formatoArchivo + "\\417" + formatoArchivo + ".png", objGuiaRemision);
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

    public static void writeXML(String fileNameXMLnf, GuiaRemisionxml objGuiaRemision) throws Exception {

        DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFctory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element element = document.createElement("DespatchAdvice");
        document.appendChild(element);

        Attr attr_xmlns_ext = document.createAttribute("xmlns:ext");
        attr_xmlns_ext.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        element.setAttributeNode(attr_xmlns_ext);

        Attr attr_xmlns_cbc = document.createAttribute("xmlns:cbc");
        attr_xmlns_cbc.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        element.setAttributeNode(attr_xmlns_cbc);

        Attr attr_xmlns_cac = document.createAttribute("xmlns:cac");
        attr_xmlns_cac.setValue("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        element.setAttributeNode(attr_xmlns_cac);

        Attr attr_xmlns = document.createAttribute("xmlns");
        attr_xmlns.setValue("urn:oasis:names:specification:ubl:schema:xsd:DespatchAdvice-2");
        element.setAttributeNode(attr_xmlns);

        Attr attr_xmlns_ds = document.createAttribute("xmlns:ds");
        attr_xmlns_ds.setValue("http://www.w3.org/2000/09/xmldsig#");
        element.setAttributeNode(attr_xmlns_ds);

        Element UBLExtensions = document.createElement("ext:UBLExtensions");
        element.appendChild(UBLExtensions);

        Element UBLVersionID = document.createElement("cbc:UBLVersionID");
        UBLVersionID.appendChild(document.createTextNode("2.1"));
        element.appendChild(UBLVersionID);

        // cbc:CustomizationID
        Element CustomizationID = document.createElement("cbc:CustomizationID");
        CustomizationID.appendChild(document.createTextNode("1.0"));
        element.appendChild(CustomizationID);

        Element SERIE = document.createElement("cbc:ID");
        SERIE.appendChild(document.createTextNode(objGuiaRemision.getNroGuia()));
        element.appendChild(SERIE);

        Element fecEmision = document.createElement("cbc:IssueDate");
        fecEmision.appendChild(document.createTextNode(objGuiaRemision.getFechaGuia()));
        element.appendChild(fecEmision);

        Element DespatchTypeCode = document.createElement("cbc:DespatchAdviceTypeCode");
        DespatchTypeCode.appendChild(document.createTextNode(objGuiaRemision.getTipoGuia()));
        element.appendChild(DespatchTypeCode);

        if (!objGuiaRemision.getObservaciones().equals("")) {
            Element Note = document.createElement("cbc:Note");
            Node cdataObservacion = document.createCDATASection(objGuiaRemision.getObservaciones());
            Note.appendChild(cdataObservacion);
            element.appendChild(Note);
        }

        // cac:OrderReference
        Element order_reference = document.createElement("cac:OrderReference");
        element.appendChild(order_reference);

        // cbc:ID       
        Element id_orderReference = document.createElement("cbc:ID");
        id_orderReference.appendChild(document.createTextNode(objGuiaRemision.getNroGuia()));
        order_reference.appendChild(id_orderReference);

        // cbc:OrderTypeCode      
        Element orderTypeCode = document.createElement("cbc:OrderTypeCode");
        orderTypeCode.appendChild(document.createTextNode(objGuiaRemision.getTipoGuia()));
        order_reference.appendChild(orderTypeCode);

        orderTypeCode.setAttribute("name", "Guia de Remision");

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
        Element AccountingSupplierParty = document.createElement("cac:DespatchSupplierParty");
        element.appendChild(AccountingSupplierParty);

        // id
        Element PartyIdentification_ID_EMISOR = document.createElement("cbc:CustomerAssignedAccountID");
        PartyIdentification_ID_EMISOR.appendChild(document.createTextNode(objGuiaRemision.getObjPersonaRemitente().getNroRuc()));
        AccountingSupplierParty.appendChild(PartyIdentification_ID_EMISOR);

        PartyIdentification_ID_EMISOR.setAttribute("schemeID", objGuiaRemision.getObjPersonaRemitente().getTipoDoc());

        // cac:Party
        Element Party_EMISOR = document.createElement("cac:Party");
        AccountingSupplierParty.appendChild(Party_EMISOR);

        // cac:PartyLegalEntity
        Element PartyLegalEntity_EMISOR = document.createElement("cac:PartyLegalEntity");
        Party_EMISOR.appendChild(PartyLegalEntity_EMISOR);

        // cbc:RegistrationName
        Element RegistrationName_EMISOR = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_EMISOR = document.createCDATASection(objGuiaRemision.getObjPersonaRemitente().getRazonSocial());
        RegistrationName_EMISOR.appendChild(cdataRegistrationName_EMISOR);
        PartyLegalEntity_EMISOR.appendChild(RegistrationName_EMISOR);

        ////////  DEFIINCION DE DATOS DL EMISOR
        // cac:AccountingSupplierParty EN RAIZ 
        Element DeliveryCustomerParty = document.createElement("cac:DeliveryCustomerParty");
        element.appendChild(DeliveryCustomerParty);

        // id
        Element PartyIdentification_ID_DESTINATARIO = document.createElement("cbc:CustomerAssignedAccountID");
        PartyIdentification_ID_DESTINATARIO.appendChild(document.createTextNode(objGuiaRemision.getObjPersonaDestinatario().getNroRuc()));
        DeliveryCustomerParty.appendChild(PartyIdentification_ID_DESTINATARIO);

        PartyIdentification_ID_DESTINATARIO.setAttribute("schemeID", objGuiaRemision.getObjPersonaDestinatario().getTipoDoc());

        // cac:Party
        Element Party_DESTINATARIO = document.createElement("cac:Party");
        DeliveryCustomerParty.appendChild(Party_DESTINATARIO);

        // cac:PartyLegalEntity
        Element PartyLegalEntity_DESTINATARIO = document.createElement("cac:PartyLegalEntity");
        Party_DESTINATARIO.appendChild(PartyLegalEntity_DESTINATARIO);

        // cbc:RegistrationName
        Element RegistrationName_DESTINATARIO = document.createElement("cbc:RegistrationName");
        Node cdataRegistrationName_DESTINATARIO = document.createCDATASection(objGuiaRemision.getObjPersonaDestinatario().getRazonSocial());
        RegistrationName_DESTINATARIO.appendChild(cdataRegistrationName_DESTINATARIO);
        PartyLegalEntity_DESTINATARIO.appendChild(RegistrationName_DESTINATARIO);

        //cac:Shipment
        Element Shipment = document.createElement("cac:Shipment");
        element.appendChild(Shipment);

        // id cambio
        Element ID_Shipment = document.createElement("cbc:ID");
        ID_Shipment.appendChild(document.createTextNode("1"));
        Shipment.appendChild(ID_Shipment);

        Element HandlingCode = document.createElement("cbc:HandlingCode");
        HandlingCode.appendChild(document.createTextNode(objGuiaRemision.getObjMotivoTransporte().getCodigo()));
        Shipment.appendChild(HandlingCode);

        Double get_peso_bruto = objGuiaRemision.getPesoBruto();

        Element GrossWeightMeasure = document.createElement("cbc:GrossWeightMeasure");
        GrossWeightMeasure.appendChild(document.createTextNode(Formato.granDinero(get_peso_bruto)));
        Shipment.appendChild(GrossWeightMeasure);
        GrossWeightMeasure.setAttribute("unitCode", objGuiaRemision.getUnidadMedida());

        Element ShipmentStage = document.createElement("cac:ShipmentStage");
        Shipment.appendChild(ShipmentStage);

        Element TransportModeCode = document.createElement("cbc:TransportModeCode");
        TransportModeCode.appendChild(document.createTextNode(objGuiaRemision.getObjModalidadTraslado().getCodigo()));
        ShipmentStage.appendChild(TransportModeCode);

        Element TransitPeriod = document.createElement("cac:TransitPeriod");
        ShipmentStage.appendChild(TransitPeriod);

        Element StartDate1 = document.createElement("cbc:StartDate");
        StartDate1.appendChild(document.createTextNode(objGuiaRemision.getFechaIniTraslado()));
        TransitPeriod.appendChild(StartDate1);
        /*
        Element StartDate2 = document.createElement("cbc:StartDate");
        StartDate2.appendChild(document.createTextNode(objGuiaRemision.getFechaEntrega()));
        TransitPeriod.appendChild(StartDate2);
         */

        if (objGuiaRemision.getObjModalidadTraslado().getCodigo().equals("01")) {

            Element CarrierParty = document.createElement("cac:CarrierParty");
            ShipmentStage.appendChild(CarrierParty);

            Element Party_Identification = document.createElement("cac:PartyIdentification");
            CarrierParty.appendChild(Party_Identification);

            Element ID_Transportista = document.createElement("cbc:ID");
            ID_Transportista.appendChild(document.createTextNode(objGuiaRemision.getObjPersonaTransportista().getNroRuc()));
            Party_Identification.appendChild(ID_Transportista);
            ID_Transportista.setAttribute("schemeID", objGuiaRemision.getObjPersonaTransportista().getTipoDoc());

            Element Party_Name = document.createElement("cac:PartyName");
            CarrierParty.appendChild(Party_Name);

            Element Name_Transportista = document.createElement("cbc:Name");
            Node cdataName_Transportista = document.createCDATASection(objGuiaRemision.getObjPersonaTransportista().getRazonSocial());
            Name_Transportista.appendChild(cdataName_Transportista);
            Party_Name.appendChild(Name_Transportista);

        } else if (objGuiaRemision.getObjModalidadTraslado().getCodigo().equals("02")) {

            Element TransportMeans = document.createElement("cac:TransportMeans");
            ShipmentStage.appendChild(TransportMeans);

            Element RoadTransport = document.createElement("cac:RoadTransport");
            TransportMeans.appendChild(RoadTransport);

            Element LicencePlateID = document.createElement("cbc:LicencePlateID");
            RoadTransport.appendChild(LicencePlateID);

            Element Placa = document.createElement("cbc:ID");
            Placa.appendChild(document.createTextNode(objGuiaRemision.getNroPlacaTransportista()));
            LicencePlateID.appendChild(Placa);

            Element TransportHandlingUnit = document.createElement("cac:TransportHandlingUnit");
            ShipmentStage.appendChild(TransportHandlingUnit);

            Element Placa_vehiculo = document.createElement("cbc:ID");
            Placa_vehiculo.appendChild(document.createTextNode(objGuiaRemision.getNroPlacaTransportista()));
            TransportHandlingUnit.appendChild(Placa_vehiculo);

            Element DriverPerson = document.createElement("cac:DriverPerson");
            ShipmentStage.appendChild(DriverPerson);

            Element DriverPerson_ID = document.createElement("cbc:ID");
            DriverPerson_ID.appendChild(document.createTextNode(objGuiaRemision.getObjPersonaConductor().getNroRuc()));
            DriverPerson.appendChild(DriverPerson_ID);
            DriverPerson_ID.setAttribute("schemeID", objGuiaRemision.getObjPersonaConductor().getTipoDoc());

        }
        Element Delivery = document.createElement("cac:Delivery");
        Shipment.appendChild(Delivery);

        Element Delivery_Address = document.createElement("cac:DeliveryAddress");
        Delivery.appendChild(Delivery_Address);

        Element LlegadaUbigeo = document.createElement("cbc:ID");
        LlegadaUbigeo.appendChild(document.createTextNode(objGuiaRemision.getObjUbigeoLlegada().getCodigo().substring(2, 8)));
        Delivery_Address.appendChild(LlegadaUbigeo);

        Element LlegadaStreetName = document.createElement("cbc:StreetName");
        Node cdataLlegadaStreetName = document.createCDATASection(objGuiaRemision.getObjUbigeoLlegada().getDireccion());
        LlegadaStreetName.appendChild(cdataLlegadaStreetName);
        Delivery_Address.appendChild(LlegadaStreetName);

        Element OriginAddress = document.createElement("cac:OriginAddress");
        Shipment.appendChild(OriginAddress);

        Element PartidaUbigeo = document.createElement("cbc:ID");
        PartidaUbigeo.appendChild(document.createTextNode(objGuiaRemision.getObjUbigeoSalida().getCodigo().substring(2, 8)));
        OriginAddress.appendChild(PartidaUbigeo);

        Element PartidaStreetName = document.createElement("cbc:StreetName");
        Node cdataPartidaStreetName = document.createCDATASection(objGuiaRemision.getObjUbigeoSalida().getDireccion());
        PartidaStreetName.appendChild(cdataPartidaStreetName);
        OriginAddress.appendChild(PartidaStreetName);

        for (int linea = 0; linea < objGuiaRemision.getListDetalleItemxml().size(); linea++) {

            // cac:InvoiceLine
            Element DespatchLine = document.createElement("cac:DespatchLine");
            element.appendChild(DespatchLine);

            // cbc:ID
            Element ID_Item = document.createElement("cbc:ID");
            ID_Item.appendChild(document.createTextNode(objGuiaRemision.getListDetalleItemxml().get(linea).getNroOrden()));
            DespatchLine.appendChild(ID_Item);

            // cbc:InvoicedQuantity 
            Element DeliveredQuantity = document.createElement("cbc:DeliveredQuantity");
            DeliveredQuantity.appendChild(document.createTextNode(objGuiaRemision.getListDetalleItemxml().get(linea).getCantidad()));
            DespatchLine.appendChild(DeliveredQuantity);

            Element Order_lineReference = document.createElement("cac:OrderLineReference");
            DespatchLine.appendChild(Order_lineReference);

            Element LineId = document.createElement("cbc:LineID");
            LineId.appendChild(document.createTextNode(objGuiaRemision.getListDetalleItemxml().get(linea).getNroOrden()));
            Order_lineReference.appendChild(LineId);

            DeliveredQuantity.setAttribute("unitCode", objGuiaRemision.getListDetalleItemxml().get(linea).getUnidadMedida());

            Element Item = document.createElement("cac:Item");
            DespatchLine.appendChild(Item);

            Element Description = document.createElement("cbc:Name");
            Node cdataDescription = document.createCDATASection(objGuiaRemision.getListDetalleItemxml().get(linea).getDescripcion());
            Description.appendChild(cdataDescription);
            Item.appendChild(Description);

        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        //transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
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
