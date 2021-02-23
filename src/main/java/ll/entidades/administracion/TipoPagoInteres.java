/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author MarVer
 */
public class TipoPagoInteres {
    
    private String idTipoPagoInteres;
    private String descripcion;
    private boolean estado;
    
    public TipoPagoInteres() {
        
    }

    public String getIdTipoPagoInteres() {
        return idTipoPagoInteres;
    }

    public void setIdTipoPagoInteres(String idTipoPagoInteres) {
        this.idTipoPagoInteres = idTipoPagoInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
