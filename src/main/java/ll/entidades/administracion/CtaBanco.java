/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author RenRio
 */
public class CtaBanco {

    private Bancos objBanco;
    private int idTipCtaBanco;
    private String ctaBanco;
    private String tipMoneda;
    private String fechaApertura;
    private String tipCtaBanco;
    private String estado;
    private String accion;

    private TipoMoneda objTipoMoneda;
    private TipoCtaBanco objTipoCtaBanco;

    public CtaBanco() {
        this.objBanco = new Bancos();
        this.objTipoCtaBanco = new TipoCtaBanco();
        this.objTipoMoneda = new TipoMoneda();
    }

    public String getTipCtaBanco() {
        return tipCtaBanco;
    }

    public void setTipCtaBanco(String tipCtaBanco) {
        this.tipCtaBanco = tipCtaBanco;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public Bancos getObjBanco() {
        return objBanco;
    }

    public void setObjBanco(Bancos objBanco) {
        this.objBanco = objBanco;
    }

    public int getIdTipCtaBanco() {
        return idTipCtaBanco;
    }

    public void setIdTipCtaBanco(int idTipCtaBanco) {
        this.idTipCtaBanco = idTipCtaBanco;
    }

    public String getCtaBanco() {
        return ctaBanco;
    }

    public void setCtaBanco(String ctaBanco) {
        this.ctaBanco = ctaBanco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public TipoMoneda getObjTipoMoneda() {
        return objTipoMoneda;
    }

    public void setObjTipoMoneda(TipoMoneda objTipoMoneda) {
        this.objTipoMoneda = objTipoMoneda;
    }

    public TipoCtaBanco getObjTipoCtaBanco() {
        return objTipoCtaBanco;
    }

    public void setObjTipoCtaBanco(TipoCtaBanco objTipoCtaBanco) {
        this.objTipoCtaBanco = objTipoCtaBanco;
    }

}
