package ll.pe.gob.sunat.servicio.registro.comppago.ose.facturacion;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


import ll.pe.gob.sunat.serviceOSE.ObjectFactory;
import ll.pe.gob.sunat.serviceOSE.StatusCdr;
import ll.pe.gob.sunat.serviceOSE.StatusResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "billService", targetNamespace = "http://service.sunat.gob.pe")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BillService {


    /**
     * 
     * @param ticket
     * @return
     *     returns pe.gob.sunat.service.StatusResponse
     */
    @WebMethod(action = "http://service.sunat.gob.pe/getStatus")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "getStatus", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.GetStatus")
    @ResponseWrapper(localName = "getStatusResponse", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.GetStatusResponse")
    public StatusResponse getStatus(
        @WebParam(name = "ticket", targetNamespace = "")
        String ticket);

    /**
     * 
     * @param fileName
     * @param contentFile
     * @param partyType
     * @return
     *     returns byte[]
     */
    @WebMethod(action = "http://service.sunat.gob.pe/sendBill")
    @WebResult(name = "applicationResponse", targetNamespace = "")
    @RequestWrapper(localName = "sendBill", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendBill")
    @ResponseWrapper(localName = "sendBillResponse", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendBillResponse")
    public byte[] sendBill(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "contentFile", targetNamespace = "")
        DataHandler contentFile,
        @WebParam(name = "partyType", targetNamespace = "")
        String partyType);

    /**
     * 
     * @param fileName
     * @param contentFile
     * @param partyType
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://service.sunat.gob.pe/sendPack")
    @WebResult(name = "ticket", targetNamespace = "")
    @RequestWrapper(localName = "sendPack", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendPack")
    @ResponseWrapper(localName = "sendPackResponse", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendPackResponse")
    public String sendPack(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "contentFile", targetNamespace = "")
        DataHandler contentFile,
        @WebParam(name = "partyType", targetNamespace = "")
        String partyType);

    /**
     * 
     * @param fileName
     * @param contentFile
     * @param partyType
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://service.sunat.gob.pe/sendSummary")
    @WebResult(name = "ticket", targetNamespace = "")
    @RequestWrapper(localName = "sendSummary", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendSummary")
    @ResponseWrapper(localName = "sendSummaryResponse", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.SendSummaryResponse")
    public String sendSummary(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "contentFile", targetNamespace = "")
        DataHandler contentFile,
        @WebParam(name = "partyType", targetNamespace = "")
        String partyType);

    /**
     * 
     * @param rucComprobante
     * @param serieComprobante
     * @param numeroComprobante
     * @param tipoComprobante
     * @return
     *     returns pe.gob.sunat.service.StatusCdr
     */
    @WebMethod(action = "http://service.sunat.gob.pe/getStatusCdr")
    @WebResult(name = "statusCdr", targetNamespace = "")
    @RequestWrapper(localName = "getStatusCdr", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.GetStatusCdr")
    @ResponseWrapper(localName = "getStatusCdrResponse", targetNamespace = "http://service.sunat.gob.pe", className = "ll.pe.gob.sunat.serviceOSE.GetStatusCdrResponse")
    public StatusCdr getStatusCdr(
        @WebParam(name = "rucComprobante", targetNamespace = "")
        String rucComprobante,
        @WebParam(name = "tipoComprobante", targetNamespace = "")
        String tipoComprobante,
        @WebParam(name = "serieComprobante", targetNamespace = "")
        String serieComprobante,
        @WebParam(name = "numeroComprobante", targetNamespace = "")
        String numeroComprobante);

}
