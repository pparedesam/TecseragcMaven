/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.xmlFacturacion;

/**
 *
 * @author PauPar
 */
public class DetalleItemxml {

    private String unidadMedida;
    private String cantidad;
    private String descripcion;
    private String valUnitario;
    private String precioUnitario;
    private TipoPrecioVtaxml objprecUnitario;
    private String igv;
    private String valorVta;
    private String nroOrden;
    private String tipoAfectacion;
    private String codTributo;
    private String nomTributo;
    private String cit;

    public DetalleItemxml() {
        objprecUnitario = new TipoPrecioVtaxml();
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValUnitario() {
        return valUnitario;
    }

    public void setValUnitario(String valUnitario) {
        this.valUnitario = valUnitario;
    }

    public TipoPrecioVtaxml getObjprecUnitario() {
        return objprecUnitario;
    }

    public void setObjprecUnitario(TipoPrecioVtaxml objprecUnitario) {
        this.objprecUnitario = objprecUnitario;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getValorVta() {
        return valorVta;
    }

    public void setValorVta(String valorVta) {
        this.valorVta = valorVta;
    }

    public String getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getTipoAfectacion() {
        return tipoAfectacion;
    }

    public void setTipoAfectacion(String tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

    public String getCodTributo() {
        return codTributo;
    }

    public void setCodTributo(String codTributo) {
        this.codTributo = codTributo;
    }

    public String getNomTributo() {
        return nomTributo;
    }

    public void setNomTributo(String nomTributo) {
        this.nomTributo = nomTributo;
    }

    public String getCit() {
        return cit;
    }

    public void setCit(String cit) {
        this.cit = cit;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}
