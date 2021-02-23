/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.riesgos;

/**
 *
 * @author CesGue
 */
public class TiposRespuesta {

    private int codigo;
    private String descripcion;
    private int estado;
    private float calificacion ;
    private float impacto;
    private SubFactor objSubfactor;
    private int combo;

    public TiposRespuesta()
    {
       objSubfactor = new SubFactor();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    

    public SubFactor getObjSubfactor() {
        return objSubfactor;
    }

    public void setObjSubfactor(SubFactor objSubfactor) {
        this.objSubfactor = objSubfactor;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public float getImpacto() {
        return impacto;
    }

    public void setImpacto(float impacto) {
        this.impacto = impacto;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }



}
