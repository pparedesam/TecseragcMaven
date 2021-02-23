/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.agentes;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Persona;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author paupar
 */
public class TicketCaja {
    
    private int numeroTicket;
    private Oficina objOficina;
    private Area objArea;
    private String fechaReg;
    private String horaReg;
    private String modGeneracion;
    private String estado;
    private String horaIni;
    private String fechaFin;
    private String tiempo;
    private Usuario objUsuario;
    private int ponderacion;
    private Persona objPersona;
    private String aleatorio;

    public String getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(String aleatorio) {
        this.aleatorio = aleatorio;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public Area getObjArea() {
        return objArea;
    }

    public void setObjArea(Area objArea) {
        this.objArea = objArea;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public String getHoraReg() {
        return horaReg;
    }

    public void setHoraReg(String horaReg) {
        this.horaReg = horaReg;
    }

    public String getModGeneracion() {
        return modGeneracion;
    }

    public void setModGeneracion(String modGeneracion) {
        this.modGeneracion = modGeneracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }
    
    
}
