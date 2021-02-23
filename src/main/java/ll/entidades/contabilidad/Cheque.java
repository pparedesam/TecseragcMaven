/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

import ll.entidades.administracion.Bancos;
import ll.entidades.administracion.CtaBanco;
import ll.entidades.administracion.Persona;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author RenRio
 */
public class Cheque {

    

    private Persona objPersona;
    private String tipMoneda;
    private String fechaDoc;
    private String GlosaFija;
    private String GlosaVariable;
    private CtaBanco objCtaBanco;
    private Bancos objBanco;
    private String nrocheque;
    private double monto;
    private String montoLetras;
    private String estado;
    private DocumentoGenerado objDocGenerado;

    public Cheque(Persona objPersona, String tipMoneda, String fechaDoc, String GlosaFija, String GlosaVariable, CtaBanco objCtaBanco, Bancos objBanco, String nrocheque, double monto, String estado, DocumentoGenerado objDocGenerado) {
        this.objPersona = objPersona;
        this.tipMoneda = tipMoneda;
        this.fechaDoc = fechaDoc;
        this.GlosaFija = GlosaFija;
        this.GlosaVariable = GlosaVariable;
        this.objCtaBanco = objCtaBanco;
        this.objBanco = objBanco;
        this.nrocheque = nrocheque;
        this.monto = monto;
        this.estado = estado;
        this.objDocGenerado = objDocGenerado;
    }

    public Cheque() {

        objPersona = new Persona();
        objCtaBanco = new CtaBanco();
        objBanco = new Bancos();
        objDocGenerado = new DocumentoGenerado();
    }

    public DocumentoGenerado getObjDocGenerado() {
        return objDocGenerado;
    }

    public void setObjDocGenerado(DocumentoGenerado objDocGenerado) {
        this.objDocGenerado = objDocGenerado;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public String getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(String fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public String getGlosaFija() {
        return GlosaFija;
    }

    public void setGlosaFija(String GlosaFija) {
        this.GlosaFija = GlosaFija;
    }

    public String getGlosaVariable() {
        return GlosaVariable;
    }

    public void setGlosaVariable(String GlosaVariable) {
        this.GlosaVariable = GlosaVariable;
    }

    public CtaBanco getObjCtaBanco() {
        return objCtaBanco;
    }

    public void setObjCtaBanco(CtaBanco objCtaBanco) {
        this.objCtaBanco = objCtaBanco;
    }

    public Bancos getObjBanco() {
        return objBanco;
    }

    public void setObjBanco(Bancos objBanco) {
        this.objBanco = objBanco;
    }

    public String getNrocheque() {
        return nrocheque;
    }

    public void setNrocheque(String nrocheque) {
        this.nrocheque = nrocheque;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
   
    public String getMontoLetras() {
        return montoLetras;
    }

    
    public void setMontoLetras(String montoLetras) {
        this.montoLetras = montoLetras;
    }

}
