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
public class SubFactor {
    
    private int codigo;
    private String descripcion;
    private Factor objFactor;
    private int combo;
    private int estado;
    
    public SubFactor ()
    {
        objFactor = new Factor();
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

    public Factor getObjFactor() {
        return objFactor;
    }

    public void setObjFactor(Factor objFactor) {
        this.objFactor = objFactor;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    

    
}
