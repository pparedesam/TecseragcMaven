/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

/**
 *
 * @author RenRio
 */
public class Bancos {

    private Persona objPersonaBanco;
    private String nombreBanco;
    private String nombreAbv;

    public Bancos(Persona objPersonaBanco, String nombreBanco, String nombreAbv) {
        this.objPersonaBanco = objPersonaBanco;
        this.nombreBanco = nombreBanco;
        this.nombreAbv = nombreAbv;
    }

    public Bancos() {
        objPersonaBanco = new Persona();
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Persona getObjPersonaBanco() {
        return objPersonaBanco;
    }

    public void setObjPersonaBanco(Persona objPersonaBanco) {
        this.objPersonaBanco = objPersonaBanco;
    }

    public String getNombreAbv() {
        return nombreAbv;
    }

    public void setNombreAbv(String nombreAbv) {
        this.nombreAbv = nombreAbv;
    }

}
