/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author CesGue
 */
public class TipoMoneda {

    private String id;
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String Descripcion) {
//        this.descripcion = Descripcion;
//    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        switch (descripcion) {
            case "1":
                this.descripcion = "SOLES";
                break;
            case "2":
                this.descripcion = "DOLARES";
                break;
            default:
                this.descripcion = descripcion;
        }
    }
}
