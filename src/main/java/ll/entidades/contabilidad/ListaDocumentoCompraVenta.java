/*,
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

import ll.entidades.administracion.TipoMoneda;

/**
 *
 * @author PauPar
 */
public class ListaDocumentoCompraVenta {

    
    
    private TipoComprobantePago objTipComprobante;
    private TipoMoneda objMoneda;
    private String documentoNro;
    private String emisor;
    private String ejecutor;
    private String fecha;
    private Double total;
    private Double totalIGV;
    private Double totalVN;
    private String estado;
    private String nroDoc;
    private String idDoc;
    private String idOficina;
    private String tipMoneda;
    private String totalCarga;

    public ListaDocumentoCompraVenta() {
        objTipComprobante = new TipoComprobantePago();
        objMoneda = new TipoMoneda();
    }

    public TipoComprobantePago getObjTipComprobante() {
        return objTipComprobante;
    }

    public void setObjTipComprobante(TipoComprobantePago objTipComprobante) {
        this.objTipComprobante = objTipComprobante;
    }

    public TipoMoneda getObjMoneda() {
        return objMoneda;
    }

    public void setObjMoneda(TipoMoneda objMoneda) {
        this.objMoneda = objMoneda;
    }

    public String getDocumentoNro() {
        return documentoNro;
    }

    public void setDocumentoNro(String documentoNro) {
        this.documentoNro = documentoNro;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalIGV() {
        return totalIGV;
    }

    public void setTotalIGV(Double totalIGV) {
        this.totalIGV = totalIGV;
    }

    public Double getTotalVN() {
        return totalVN;
    }

    public void setTotalVN(Double totalVN) {
        this.totalVN = totalVN;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getTotalCarga() {
        return totalCarga;
    }

    public void setTotalCarga(String totalCarga) {
        this.totalCarga = totalCarga;
    }


}
