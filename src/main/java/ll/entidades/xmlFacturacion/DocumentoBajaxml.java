/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.xmlFacturacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauPar
 */
public class DocumentoBajaxml {

    private Personaxml objPersonaxmlEmisor;

    private String identificador;
    private String fechaComunicacion;
    private String fechaGeneracion;
    private List<DetalleDocBajaxml> listDetalleDocBajaxml;

    public Personaxml getObjPersonaxmlEmisor() {
        return objPersonaxmlEmisor;
    }

    public void setObjPersonaxmlEmisor(Personaxml objPersonaxmlEmisor) {
        this.objPersonaxmlEmisor = objPersonaxmlEmisor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getFechaComunicacion() {
        return fechaComunicacion;
    }

    public void setFechaComunicacion(String fechaComunicacion) {
        this.fechaComunicacion = fechaComunicacion;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public List<DetalleDocBajaxml> getListDetalleDocBajaxml() {
        return listDetalleDocBajaxml;
    }

    public void setListDetalleDocBajaxml(List<DetalleDocBajaxml> listDetalleDocBajaxml) {
        this.listDetalleDocBajaxml = listDetalleDocBajaxml;
    }

    public DocumentoBajaxml() {

        objPersonaxmlEmisor = new Personaxml();
        listDetalleDocBajaxml =  new ArrayList<DetalleDocBajaxml>(); 
    }

}
