/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

/**
 *
 * @author PauPar
 */
public class DetDocCompraVenta  {

    /**
     * @return the valorUnitarioTotal
     */
    public double getValorUnitarioTotal() {
        return valorUnitarioTotal;
    }

    /**
     * @param valorUnitarioTotal the valorUnitarioTotal to set
     */
    public void setValorUnitarioTotal(double valorUnitarioTotal) {
        this.valorUnitarioTotal = valorUnitarioTotal;
    }

    /**
     * @return the igv
     */
    public double getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(double igv) {
        this.igv = igv;
    }

    /**
     * @return the precioUnitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    private int linea;

    private double cantidad;
    private String unidadMedidad;
    private String glosaVariable;
    private double valorUnitario;
    private double valorUnitarioTotal;
    private double precioUnitario;
    private double monto;
    private double igv;
    private double igvTotal;
    private String idDetGastoSunat;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getIdDetGastoSunat() {
        return idDetGastoSunat;
    }

    public void setIdDetGastoSunat(String idDetGastoSunat) {
        this.idDetGastoSunat = idDetGastoSunat;
    }

    public void getDocumentoNro(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the unidadMedidad
     */
    public String getUnidadMedidad() {
        return unidadMedidad;
    }

    /**
     * @param unidadMedidad the unidadMedidad to set
     */
    public void setUnidadMedidad(String unidadMedidad) {
        this.unidadMedidad = unidadMedidad;
    }

    /**
     * @return the glosaVariable
     */
    public String getGlosaVariable() {
        return glosaVariable;
    }

    /**
     * @param glosaVariable the glosaVariable to set
     */
    public void setGlosaVariable(String glosaVariable) {
        this.glosaVariable = glosaVariable;
    }

    /**
     * @return the valorUnitario
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the igvTotal
     */
    public double getIgvTotal() {
        return igvTotal;
    }

    /**
     * @param igvTotal the igvTotal to set
     */
    public void setIgvTotal(double igvTotal) {
        this.igvTotal = igvTotal;
    }

}
