/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.operaciones;

import ll.entidades.administracion.EstructuraContable;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Operacion;
import ll.entidades.administracion.Persona;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author paupar
 */
public class Transaccion {

    private Oficina objOficina;
    private DocumentoGenerado objDocGenerado;
    private String nroTransaccion;
    private EstructuraContable objEstructuraContable;
    private String fechaTransaccion;
    private Persona objPersona;
    private String glosaFija;
    private String glosaVariable;
    private Double montoSoles;
    private Double montoDolar;
    private Usuario objUsuario;
    private Operacion objOperacion;

    public Transaccion() {
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public DocumentoGenerado getObjDocGenerado() {
        return objDocGenerado;
    }

    public void setObjDocGenerado(DocumentoGenerado objDocGenerado) {
        this.objDocGenerado = objDocGenerado;
    }

    public String getNroTransaccion() {
        return nroTransaccion;
    }

    public void setNroTransaccion(String nroTransaccion) {
        this.nroTransaccion = nroTransaccion;
    }

    public EstructuraContable getObjEstructuraContable() {
        return objEstructuraContable;
    }

    public void setObjEstructuraContable(EstructuraContable objEstructuraContable) {
        this.objEstructuraContable = objEstructuraContable;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
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

    public Double getMontoSoles() {
        return montoSoles;
    }

    public void setMontoSoles(Double montoSoles) {
        this.montoSoles = montoSoles;
    }

    public Double getMontoDolar() {
        return montoDolar;
    }

    public void setMontoDolar(Double montoDolar) {
        this.montoDolar = montoDolar;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public Operacion getObjOperacion() {
        return objOperacion;
    }

    public void setObjOperacion(Operacion objOperacion) {
        this.objOperacion = objOperacion;
    }

}
