/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

import ll.entidades.administracion.Usuario;

/**
 *
 * @author Andrew
 */
public class InteresCompensatorio {
    
    private String idTipoSBS;
    private ProductoCrediticio objProductoCrediticio;
    private int plazo;
    private String idFrecuenciaPago;
    private String tipoCuota;
    private Double tasaIc;
    private String tipMoneda;
    private Usuario objUsuario;

    public InteresCompensatorio(){
    }
    
    public String getIdTipoSBS() {
        return idTipoSBS;
    }

    public void setIdTipoSBS(String idTipoSBS) {
        this.idTipoSBS = idTipoSBS;
    }

    public ProductoCrediticio getObjProductoCrediticio() {
        return objProductoCrediticio;
    }

    public void setObjProductoCrediticio(ProductoCrediticio objProductoCrediticio) {
        this.objProductoCrediticio = objProductoCrediticio;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getIdFrecuenciaPago() {
        return idFrecuenciaPago;
    }

    public void setIdFrecuenciaPago(String idFrecuenciaPago) {
        this.idFrecuenciaPago = idFrecuenciaPago;
    }

    public String getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }

    public Double getTasaIc() {
        return tasaIc;
    }

    public void setTasaIc(Double tasaIc) {
        this.tasaIc = tasaIc;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }
    
}
