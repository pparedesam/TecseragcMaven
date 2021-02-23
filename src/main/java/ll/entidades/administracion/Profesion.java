/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author paupar
 */
public class Profesion {

    private String idProfesion;
    private String profesion;
    private String estado;

    public Profesion() {
    }

    public String getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(String idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
