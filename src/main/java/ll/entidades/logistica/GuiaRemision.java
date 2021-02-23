/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.logistica;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.administracion.Persona;
import ll.entidades.administracion.Ubigeo;
import ll.entidades.contabilidad.DetalleGuiaRemision;
import ll.entidades.contabilidad.UnidadMedida;
import ll.entidades.logistica.ModalidadTraslado;
import ll.entidades.logistica.MotivoTraslado;

/**
 *
 * @author PauPar
 */
public class GuiaRemision {

    private String idDoc;
    private String nroDoc;
    private String idOficina;
    private String tipMoneda;
    
    private String nroGuia;
    private String fechaGuia;
    private String tipoGuia;
    private String idTipoGuia;
    private Persona objPersonaRemitente;
    private Persona objPersonaDestinatario;
    private Persona objPersonaTransportista;
    private String nroPlacaTransportista;
    private Persona objPersonaConductor;
    private Ubigeo objUbigeoLlegada;
    private String direccionLlegada;
    private Ubigeo objUbigeoPartida;
    private String direccionPartida;
    private MotivoTraslado objMotivosTraslado;
    private ModalidadTraslado objModalidadTraslado;
    private double pesoBruto;
    private UnidadMedida objUnidadMedida;
    private String fechaIniTraslado;
    private String fechaEntrega;
    private List<DetalleGuiaRemision> listDetalleGuiaRemision;
    private int tipo;

    public GuiaRemision() {

        objPersonaRemitente = new Persona();
        objPersonaDestinatario = new Persona();
        objPersonaTransportista = new Persona();
        objPersonaConductor = new Persona();
        objUbigeoLlegada = new Ubigeo();
        objUbigeoPartida = new Ubigeo();
        objMotivosTraslado = new MotivoTraslado();
        objModalidadTraslado = new ModalidadTraslado();
        objUnidadMedida = new UnidadMedida();
        listDetalleGuiaRemision = new ArrayList<DetalleGuiaRemision>();
    }

    public List<DetalleGuiaRemision> getListDetalleGuiaRemision() {
        return listDetalleGuiaRemision;
    }

    public void setListDetalleGuiaRemision(List<DetalleGuiaRemision> listDetalleGuiaRemision) {
        this.listDetalleGuiaRemision = listDetalleGuiaRemision;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(String idOficina) {
        this.idOficina = idOficina;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    
    public String getNroGuia() {
        return nroGuia;
    }

    public void setNroGuia(String nroGuia) {
        this.nroGuia = nroGuia;
    }

    public String getFechaGuia() {
        return fechaGuia;
    }

    public void setFechaGuia(String fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public String getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(String tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    public String getIdTipoGuia() {
        return idTipoGuia;
    }

    public void setIdTipoGuia(String idTipoGuia) {
        this.idTipoGuia = idTipoGuia;
    }

    public Persona getObjPersonaRemitente() {
        return objPersonaRemitente;
    }

    public void setObjPersonaRemitente(Persona objPersonaRemitente) {
        this.objPersonaRemitente = objPersonaRemitente;
    }

    public Persona getObjPersonaDestinatario() {
        return objPersonaDestinatario;
    }

    public void setObjPersonaDestinatario(Persona objPersonaDestinatario) {
        this.objPersonaDestinatario = objPersonaDestinatario;
    }

    public Persona getObjPersonaTransportista() {
        return objPersonaTransportista;
    }

    public void setObjPersonaTransportista(Persona objPersonaTransportista) {
        this.objPersonaTransportista = objPersonaTransportista;
    }

    public String getNroPlacaTransportista() {
        return nroPlacaTransportista;
    }

    public void setNroPlacaTransportista(String nroPlacaTransportista) {
        this.nroPlacaTransportista = nroPlacaTransportista;
    }

    public Persona getObjPersonaConductor() {
        return objPersonaConductor;
    }

    public void setObjPersonaConductor(Persona objPersonaConductor) {
        this.objPersonaConductor = objPersonaConductor;
    }

    public Ubigeo getObjUbigeoLlegada() {
        return objUbigeoLlegada;
    }

    public void setObjUbigeoLlegada(Ubigeo objUbigeoLlegada) {
        this.objUbigeoLlegada = objUbigeoLlegada;
    }

    public String getDireccionLlegada() {
        return direccionLlegada;
    }

    public void setDireccionLlegada(String direccionLlegada) {
        this.direccionLlegada = direccionLlegada;
    }

    public Ubigeo getObjUbigeoPartida() {
        return objUbigeoPartida;
    }

    public void setObjUbigeoPartida(Ubigeo objUbigeoPartida) {
        this.objUbigeoPartida = objUbigeoPartida;
    }

    public String getDireccionPartida() {
        return direccionPartida;
    }

    public void setDireccionPartida(String direccionPartida) {
        this.direccionPartida = direccionPartida;
    }

    public MotivoTraslado getObjMotivosTraslado() {
        return objMotivosTraslado;
    }

    public void setObjMotivosTraslado(MotivoTraslado objMotivosTraslado) {
        this.objMotivosTraslado = objMotivosTraslado;
    }

    public ModalidadTraslado getObjModalidadTraslado() {
        return objModalidadTraslado;
    }

    public void setObjModalidadTraslado(ModalidadTraslado objModalidadTraslado) {
        this.objModalidadTraslado = objModalidadTraslado;
    }

    public double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public UnidadMedida getObjUnidadMedida() {
        return objUnidadMedida;
    }

    public void setObjUnidadMedida(UnidadMedida objUnidadMedida) {
        this.objUnidadMedida = objUnidadMedida;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }



}
