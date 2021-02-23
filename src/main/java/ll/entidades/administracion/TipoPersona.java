/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author davicho
 */
public class TipoPersona {
    private String idTipoPersona;
    private String Descripcion;

    public String getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(String idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
        if(this.idTipoPersona.equalsIgnoreCase("N")){
            this.Descripcion="PERSONA NATURAL";
        }else{
            this.Descripcion = "PERSONA JURIDICA";
        }
            
    }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
