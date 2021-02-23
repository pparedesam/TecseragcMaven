package ll.entidades.contabilidad.factura;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.administracion.PersonaJuridica;

/**
 *
 * @author RenRio
 */
public class CabeceraFact {

    private String idOficina;
    private String observacion;
    private String idDoc;
    private String tipMoneda;
    private String nroDoc;
    private String nroDocExt;
    private String fechaDocumento;
    private PersonaJuridica objEmisor;
    private PersonaJuridica objEjecutor;
    private List<DetalleFact> listDetalleFact;
    private List<DetalleGuiaRemision> listDetalleGuiaRemision;
    private Double valorV;
    private Double igv;
    private Double total;
    private int verificarNC;

    public CabeceraFact() {
        objEmisor = new PersonaJuridica();
        objEjecutor = new PersonaJuridica();
        listDetalleFact = new ArrayList<DetalleFact>();
        listDetalleGuiaRemision = new ArrayList<DetalleGuiaRemision>();
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getVerificarNC() {
        return verificarNC;
    }

    public void setVerificarNC(int verificarNC) {
        this.verificarNC = verificarNC;
    }

    public String getNroDocExt() {
        return nroDocExt;
    }

    public void setNroDocExt(String nroDocExt) {
        this.nroDocExt = nroDocExt;
    }

    public String getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(String idOficina) {
        this.idOficina = idOficina;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
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

    public List<DetalleFact> getListDetalleFact() {
        return listDetalleFact;
    }

    public void setListDetalleFact(List<DetalleFact> listDetalleFact) {
        this.listDetalleFact = listDetalleFact;
    }

    public Double getValorV() {
        return valorV;
    }

    public void setValorV(Double valorV) {
        this.valorV = valorV;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleGuiaRemision> getListDetalleGuiaRemision() {
        return listDetalleGuiaRemision;
    }

    public void setListDetalleGuiaRemision(List<DetalleGuiaRemision> listDetalleGuiaRemision) {
        this.listDetalleGuiaRemision = listDetalleGuiaRemision;
    }

}
