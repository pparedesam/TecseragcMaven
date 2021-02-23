/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

import ll.entidades.administracion.AsignaDocumentoxOperacion;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author Andrew
 */
public class DefineAprobacion {

    private String nivel;
    private Oficina objOficina;
    private AsignaDocumentoxOperacion objAsignaDocumentoxOperacion;
    private Double aprobacionMinima;
    private Double aprobacionMaxima;
    private Usuario objUsuario;

    public DefineAprobacion(){
    }
    
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public AsignaDocumentoxOperacion getObjAsignaDocumentoxOperacion() {
        return objAsignaDocumentoxOperacion;
    }

    public void setObjAsignaDocumentoxOperacion(AsignaDocumentoxOperacion objAsignaDocumentoxOperacion) {
        this.objAsignaDocumentoxOperacion = objAsignaDocumentoxOperacion;
    }

    public Double getAprobacionMinima() {
        return aprobacionMinima;
    }

    public void setAprobacionMinima(Double aprobacionMinima) {
        this.aprobacionMinima = aprobacionMinima;
    }

    public Double getAprobacionMaxima() {
        return aprobacionMaxima;
    }

    public void setAprobacionMaxima(Double aprobacionMaxima) {
        this.aprobacionMaxima = aprobacionMaxima;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

}
