/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.operaciones;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Operacion;
import ll.entidades.administracion.Persona;
import ll.entidades.administracion.PersonaJuridica;
import ll.entidades.administracion.TipoMoneda;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetDocCompraVenta;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.contabilidad.RelaDoc;
import ll.entidades.contabilidad.TipoComprobantePago;

/**
 *
 * @author paupar
 */
public class DocumentoGenerado {

    private Oficina objOficina;
    private String idDoc;
    private String nroDoc;
    private TipoMoneda objTipoMoneda;
    private String idEstadoDocumento;
    private Operacion objOperacion;
    private String fechaDocumento;
    private String horaDocumento;
    private String fechaCambioEstado;
    private String docAnterior;
    private Persona objPersona;
    private String glosaFija;
    private String glosaVariable;
    private Usuario objUsuario;
    private String judicial;
    private List<DocCompraVenta> listDocCompraVenta;
    private List<GuiaRemision> listGuiaRemision;
    private RelaDoc objRelaDoc;

    private TipoComprobantePago objTipoComprobante;

    private String nroDocExt;

    private double total;

    private double totalNeto;
    private double totalIGV;

    private PersonaJuridica objEmisor;
    private PersonaJuridica objEjecutor;

    private String estado;

    private List<DetDocCompraVenta> listDetalleDocCompraVenta;

    public DocumentoGenerado() {
        objOficina = new Oficina();
        objOperacion = new Operacion();
        objPersona = new Persona();
        objUsuario = new Usuario();
        objEmisor = new PersonaJuridica();
        objEjecutor = new PersonaJuridica();
        objTipoComprobante = new TipoComprobantePago();
        objTipoMoneda = new TipoMoneda();
        objRelaDoc = new RelaDoc();

        listDocCompraVenta = new ArrayList<DocCompraVenta>();
        listGuiaRemision = new ArrayList<GuiaRemision>();
        listDetalleDocCompraVenta = new ArrayList<DetDocCompraVenta>();

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNroDocExt() {
        return nroDocExt;
    }

    public void setNroDocExt(String nroDocExt) {
        this.nroDocExt = nroDocExt;
    }

    public TipoMoneda getObjTipoMoneda() {
        return objTipoMoneda;
    }

    public void setObjTipoMoneda(TipoMoneda objTipoMoneda) {
        this.objTipoMoneda = objTipoMoneda;
    }

    public TipoComprobantePago getObjTipoComprobante() {
        return objTipoComprobante;
    }

    public void setObjTipoComprobante(TipoComprobantePago objTipoComprobante) {
        this.objTipoComprobante = objTipoComprobante;
    }

    public List<DetDocCompraVenta> getListDetalleDocCompraVenta() {
        return listDetalleDocCompraVenta;
    }

    public void setListDetalleDocCompraVenta(List<DetDocCompraVenta> listDetalleDocCompraVenta) {
        this.listDetalleDocCompraVenta = listDetalleDocCompraVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public double getTotalIGV() {
        return totalIGV;
    }

    public void setTotalIGV(double totalIGV) {
        this.totalIGV = totalIGV;
    }

    public PersonaJuridica getObjEmisor() {
        return objEmisor;
    }

    public void setObjEmisor(PersonaJuridica objEmisor) {
        this.objEmisor = objEmisor;
    }

    public PersonaJuridica getObjEjecutor() {
        return objEjecutor;
    }

    public void setObjEjecutor(PersonaJuridica objEjecutor) {
        this.objEjecutor = objEjecutor;
    }

    public List<GuiaRemision> getListGuiaRemision() {
        return listGuiaRemision;
    }

    public void setListGuiaRemision(List<GuiaRemision> listGuiaRemision) {
        this.listGuiaRemision = listGuiaRemision;
    }

    public List<DocCompraVenta> getListDocCompraVenta() {
        return listDocCompraVenta;
    }

    public void setListDocCompraVenta(List<DocCompraVenta> listDocCompraVenta) {
        this.listDocCompraVenta = listDocCompraVenta;
    }

    public RelaDoc getObjRelaDoc() {
        return objRelaDoc;
    }

    public void setObjRelaDoc(RelaDoc objRelaDoc) {
        this.objRelaDoc = objRelaDoc;
    }

    public String getFechaCambioEstado() {
        return fechaCambioEstado;
    }

    public void setFechaCambioEstado(String fechaCambioEstado) {
        this.fechaCambioEstado = fechaCambioEstado;
    }

    public String getHoraDocumento() {
        return horaDocumento;
    }

    public void setHoraDocumento(String horaDocumento) {
        this.horaDocumento = horaDocumento;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
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

    public String getIdEstadoDocumento() {
        return idEstadoDocumento;
    }

    public void setIdEstadoDocumento(String idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public Operacion getObjOperacion() {
        return objOperacion;
    }

    public void setObjOperacion(Operacion objOperacion) {
        this.objOperacion = objOperacion;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getDocAnterior() {
        return docAnterior;
    }

    public void setDocAnterior(String docAnterior) {
        this.docAnterior = docAnterior;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public String getGlosaFija() {
        return glosaFija;
    }

    public void setGlosaFija(String glosaFija) {
        this.glosaFija = glosaFija;
    }

    public String getGlosaVariable() {
        return glosaVariable;
    }

    public void setGlosaVariable(String glosaVariable) {
        this.glosaVariable = glosaVariable;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public String getJudicial() {
        return judicial;
    }

    public void setJudicial(String judicial) {
        this.judicial = judicial;
    }

    public void getFechaDocumento(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
