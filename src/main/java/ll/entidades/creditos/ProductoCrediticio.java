/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

/**
 *
 * @author Andrew
 */
public class ProductoCrediticio {
    
    private String idTipProducto;
    private String nomProducto;
    private Double montoMin;
    private Double montoMax;
    private Double derechoTramite;
    private Double seguroDesgravamen;
    private Double derechoCarpeta;

    public String getIdTipProducto() {
        return idTipProducto;
    }

    public void setIdTipProducto(String idTipProducto) {
        this.idTipProducto = idTipProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public Double getMontoMin() {
        return montoMin;
    }

    public void setMontoMin(Double montoMin) {
        this.montoMin = montoMin;
    }

    public Double getMontoMax() {
        return montoMax;
    }

    public void setMontoMax(Double montoMax) {
        this.montoMax = montoMax;
    }

    public Double getDerechoTramite() {
        return derechoTramite;
    }

    public void setDerechoTramite(Double derechoTramite) {
        this.derechoTramite = derechoTramite;
    }

    public Double getSeguroDesgravamen() {
        return seguroDesgravamen;
    }

    public void setSeguroDesgravamen(Double seguroDesgravamen) {
        this.seguroDesgravamen = seguroDesgravamen;
    }

    public Double getDerechoCarpeta() {
        return derechoCarpeta;
    }

    public void setDerechoCarpeta(Double derechoCarpeta) {
        this.derechoCarpeta = derechoCarpeta;
    }
    
    public ProductoCrediticio(){
    }
    
           
    
}
