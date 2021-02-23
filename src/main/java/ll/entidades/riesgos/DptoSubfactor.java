/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.riesgos;

import ll.entidades.riesgos.SubFactor;

/**
 *
 * @author CesGue
 */
public class DptoSubfactor {
    
    private String Codigo;
    private SubFactor subFactor;
    private boolean estado;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public SubFactor getSubFactor() {
        return subFactor;
    }

    public void setSubFactor(SubFactor subFactor) {
        this.subFactor = subFactor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
