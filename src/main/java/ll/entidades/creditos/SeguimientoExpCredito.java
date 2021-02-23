/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author Andrew
 */
public class SeguimientoExpCredito {

    private Oficina objOficina;
    private DocumentoGenerado objDocumentoGenerado;
    private String idOpe;
    private String idEstDoc;
    private String nivel;
    private Oficina objOficinaU;
    private Usuario objUsuario;
    private String fechaCambioEstado;
    private String horaCambioEstado;
    private Usuario objUsuarioR;

    public SeguimientoExpCredito() {
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public DocumentoGenerado getObjDocumentoGenerado() {
        return objDocumentoGenerado;
    }

    public void setObjDocumentoGenerado(DocumentoGenerado objDocumentoGenerado) {
        this.objDocumentoGenerado = objDocumentoGenerado;
    }

    public String getIdOpe() {
        return idOpe;
    }

    public void setIdOpe(String idOpe) {
        this.idOpe = idOpe;
    }

    public String getIdEstDoc() {
        return idEstDoc;
    }

    public void setIdEstDoc(String idEstDoc) {
        this.idEstDoc = idEstDoc;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Oficina getObjOficinaU() {
        return objOficinaU;
    }

    public void setObjOficinaU(Oficina objOficinaU) {
        this.objOficinaU = objOficinaU;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public String getFechaCambioEstado() {
        return fechaCambioEstado;
    }

    public void setFechaCambioEstado(String fechaCambioEstado) {
        this.fechaCambioEstado = fechaCambioEstado;
    }

    public String getHoraCambioEstado() {
        return horaCambioEstado;
    }

    public void setHoraCambioEstado(String horaCambioEstado) {
        this.horaCambioEstado = horaCambioEstado;
    }

    public Usuario getObjUsuarioR() {
        return objUsuarioR;
    }

    public void setObjUsuarioR(Usuario objUsuarioR) {
        this.objUsuarioR = objUsuarioR;
    }

}
