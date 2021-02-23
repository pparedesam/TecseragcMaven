/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad.factura;

/**
 *
 * @author PauPar
 */
public class DetalleGuiaRemision {
    
    private String nroGuia;
    private String descripcion;
    private String tipGuia;
    private String fecha;

    public String getNroGuia() {
        return nroGuia;
    }

    public void setNroGuia(String nroGuia) {
        this.nroGuia = nroGuia;
    }

    public String getTipGuia() {
        return tipGuia;
    }

    public void setTipGuia(String tipGuia) {
        this.tipGuia = tipGuia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
