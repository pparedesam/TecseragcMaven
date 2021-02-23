/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.xmlFacturacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauPar
 */
public class GuiaRemisionxml {

    private String nroDoc;
    private String idDoc;
    private String tipM;
    private String idOfi;

    private String nroGuia;
    private String fechaGuia;
    private String hora;
    private String tipoGuia;
    private String descGuia;
    private String tipoDoc;
    private Personaxml objPersonaRemitente;
    private Personaxml objPersonaDestinatario;
    private Personaxml objPersonaTransportista;
    private String nroPlacaTransportista;
    private Personaxml objPersonaConductor;
    private Ubigeoxml objUbigeoLlegada;
    private Ubigeoxml objUbigeoSalida;
    private double pesoBruto;
    private String unidadMedida;
    private String fechaIniTraslado;
    private String fechaEntrega;
    private ModalidadTrasladoxml objModalidadTraslado;
    private MotivoTransportexml objMotivoTransporte;
    private List<DetalleItemxml> listDetalleItemxml;
    private String observaciones;
    private String signatureValue;
    private String digestValue;
    private String response;

    public GuiaRemisionxml() {
        objPersonaRemitente = new Personaxml();
        objPersonaDestinatario = new Personaxml();
        objPersonaTransportista = new Personaxml();
        objPersonaConductor = new Personaxml();
        objUbigeoLlegada = new Ubigeoxml();
        objUbigeoSalida = new Ubigeoxml();;
        objModalidadTraslado = new ModalidadTrasladoxml();
        objMotivoTransporte = new MotivoTransportexml();
        listDetalleItemxml = new ArrayList<DetalleItemxml>();

    }

    public GuiaRemisionxml(Personaxml objPersonaRemitente, Personaxml objPersonaDestinatario, Personaxml objPersonaTransportista, Personaxml objPersonaConductor, Ubigeoxml objUbigeoLlegada, Ubigeoxml objUbigeoSalida, ModalidadTrasladoxml objModalidadTraslado, MotivoTransportexml objMotivoTransporte, List<DetalleItemxml> listDetalleItemxml) {
        this.objPersonaRemitente = objPersonaRemitente;
        this.objPersonaDestinatario = objPersonaDestinatario;
        this.objPersonaTransportista = objPersonaTransportista;
        this.objPersonaConductor = objPersonaConductor;
        this.objUbigeoLlegada = objUbigeoLlegada;
        this.objUbigeoSalida = objUbigeoSalida;
        this.objModalidadTraslado = objModalidadTraslado;
        this.objMotivoTransporte = objMotivoTransporte;
        this.listDetalleItemxml = listDetalleItemxml;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNroGuia() {
        return nroGuia;
    }

    public void setNroGuia(String nroGuia) {
        this.nroGuia = nroGuia;
    }

    public String getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(String tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    public String getDescGuia() {
        return descGuia;
    }

    public void setDescGuia(String descGuia) {
        this.descGuia = descGuia;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getTipM() {
        return tipM;
    }

    public void setTipM(String tipM) {
        this.tipM = tipM;
    }

    public String getIdOfi() {
        return idOfi;
    }

    public void setIdOfi(String idOfi) {
        this.idOfi = idOfi;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaGuia() {
        return fechaGuia;
    }

    public void setFechaGuia(String fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public Personaxml getObjPersonaRemitente() {
        return objPersonaRemitente;
    }

    public void setObjPersonaRemitente(Personaxml objPersonaRemitente) {
        this.objPersonaRemitente = objPersonaRemitente;
    }

    public Personaxml getObjPersonaDestinatario() {
        return objPersonaDestinatario;
    }

    public void setObjPersonaDestinatario(Personaxml objPersonaDestinatario) {
        this.objPersonaDestinatario = objPersonaDestinatario;
    }

    public Personaxml getObjPersonaTransportista() {
        return objPersonaTransportista;
    }

    public void setObjPersonaTransportista(Personaxml objPersonaTransportista) {
        this.objPersonaTransportista = objPersonaTransportista;
    }

    public String getNroPlacaTransportista() {
        return nroPlacaTransportista;
    }

    public void setNroPlacaTransportista(String nroPlacaTransportista) {
        this.nroPlacaTransportista = nroPlacaTransportista;
    }

    public Personaxml getObjPersonaConductor() {
        return objPersonaConductor;
    }

    public void setObjPersonaConductor(Personaxml objPersonaConductor) {
        this.objPersonaConductor = objPersonaConductor;
    }

    public Ubigeoxml getObjUbigeoLlegada() {
        return objUbigeoLlegada;
    }

    public void setObjUbigeoLlegada(Ubigeoxml objUbigeoLlegada) {
        this.objUbigeoLlegada = objUbigeoLlegada;
    }

    public Ubigeoxml getObjUbigeoSalida() {
        return objUbigeoSalida;
    }

    public void setObjUbigeoSalida(Ubigeoxml objUbigeoSalida) {
        this.objUbigeoSalida = objUbigeoSalida;
    }

    public double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

 

    public String getFechaIniTraslado() {
        return fechaIniTraslado;
    }

    public void setFechaIniTraslado(String fechaIniTraslado) {
        this.fechaIniTraslado = fechaIniTraslado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public ModalidadTrasladoxml getObjModalidadTraslado() {
        return objModalidadTraslado;
    }

    public void setObjModalidadTraslado(ModalidadTrasladoxml objModalidadTraslado) {
        this.objModalidadTraslado = objModalidadTraslado;
    }

    public MotivoTransportexml getObjMotivoTransporte() {
        return objMotivoTransporte;
    }

    public void setObjMotivoTransporte(MotivoTransportexml objMotivoTransporte) {
        this.objMotivoTransporte = objMotivoTransporte;
    }

    public List<DetalleItemxml> getListDetalleItemxml() {
        return listDetalleItemxml;
    }

    public void setListDetalleItemxml(List<DetalleItemxml> listDetalleItemxml) {
        this.listDetalleItemxml = listDetalleItemxml;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
