/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author PauPar
 */
public class TipoDocIdentidad {
    private String idTipoDocIdentidad;
    private String tipoPersona;
    private String descripcion;
    private Boolean estado;

    public String getIdTipoDocIdentidad() {
        return idTipoDocIdentidad;
    }

    public void setIdTipoDocIdentidad(String idTipoDocIdentidad) {
        this.idTipoDocIdentidad = idTipoDocIdentidad;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoDocIdentidad() {
    }
    
    
    
}
