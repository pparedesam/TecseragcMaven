/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author Andrew
 */
public class UsuarioAprobacion {

    private DefineAprobacion objDefineAprobacion;
    private Oficina objOficinaApro;
    private Usuario objUsuarioApro;
    private Usuario objUsuario;
    private String activo;

    public UsuarioAprobacion() {
    }

    public DefineAprobacion getObjDefineAprobacion() {
        return objDefineAprobacion;
    }

    public void setObjDefineAprobacion(DefineAprobacion objDefineAprobacion) {
        this.objDefineAprobacion = objDefineAprobacion;
    }

    public Oficina getObjOficinaApro() {
        return objOficinaApro;
    }

    public void setObjOficinaApro(Oficina objOficinaApro) {
        this.objOficinaApro = objOficinaApro;
    }

    public Usuario getObjUsuarioApro() {
        return objUsuarioApro;
    }

    public void setObjUsuarioApro(Usuario objUsuarioApro) {
        this.objUsuarioApro = objUsuarioApro;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

}
