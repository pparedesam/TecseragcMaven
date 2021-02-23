/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.agentes;

import ll.entidades.creditos.DatosCredito;
import ll.entidades.creditos.SeguimientoExpCredito;

/**
 *
 * @author paupar
 */
public class AprobacionExpediente {

    private String moneda;
    private DatosCredito objDatosCredito;
    private SeguimientoExpCredito objSeguimientoExpCredito;
    private String estado;
    private String tipoCreditoOB;
    private String abrev;
    private String n1;
    private String n2;
    private String n3;
    private String maxNivel;
    private String docAnterior;


    public AprobacionExpediente() {
        objDatosCredito = new DatosCredito();
        objSeguimientoExpCredito = new SeguimientoExpCredito();

    }

    public String getMaxNivel() {
        return maxNivel;
    }

    public void setMaxNivel(String maxNivel) {
        this.maxNivel = maxNivel;
    }

    public String getDocAnterior() {
        return docAnterior;
    }

    public void setDocAnterior(String docAnterior) {
        this.docAnterior = docAnterior;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getTipoCreditoOB() {
        return tipoCreditoOB;
    }

    public void setTipoCreditoOB(String tipoCreditoOB) {
        this.tipoCreditoOB = tipoCreditoOB;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public DatosCredito getObjDatosCredito() {
        return objDatosCredito;
    }

    public void setObjDatosCredito(DatosCredito objDatosCredito) {
        this.objDatosCredito = objDatosCredito;
    }

    public SeguimientoExpCredito getObjSeguimientoExpCredito() {
        return objSeguimientoExpCredito;
    }

    public void setObjSeguimientoExpCredito(SeguimientoExpCredito objSeguimientoExpCredito) {
        this.objSeguimientoExpCredito = objSeguimientoExpCredito;
    }

}
