/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.riesgos;

import ll.entidades.administracion.Area;

/**
 *
 * @author DAVID
 */
public class AreaSubFactor {
    
    private int codigo;   
    private Area objArea;
    private SubFactor objSubFactor;
    private int estado;

    public AreaSubFactor()
    {
        objArea = new Area();
        objSubFactor = new SubFactor();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

   

    public Area getObjArea() {
        return objArea;
    }

    public void setObjArea(Area objArea) {
        this.objArea = objArea;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public SubFactor getObjSubFactor() {
        return objSubFactor;
    }

    public void setObjSubFactor(SubFactor objSubFactor) {
        this.objSubFactor = objSubFactor;
    }
    
}
