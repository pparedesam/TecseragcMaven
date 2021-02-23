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
public class TipoApertura {
    
    private String IdTipoApertura; 
    private String Descripcion;
    private boolean Activo;
    
    public TipoApertura(){}

    public String getIdTipoApertura() {
        return IdTipoApertura;
    }

    public void setIdTipoApertura(String IdTipoApertura) {
        this.IdTipoApertura = IdTipoApertura;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }
    
}
