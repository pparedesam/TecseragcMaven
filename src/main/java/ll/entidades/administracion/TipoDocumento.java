/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author Davicho
 */
public class TipoDocumento {
    
    private String codigo;
    private String Descripcion;
        
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    @Override
    public String toString() {
        return "TipoDocumento{" + "codigo=" + getCodigo() + ", Descripcion=" + getDescripcion() + '}';
    }
}
