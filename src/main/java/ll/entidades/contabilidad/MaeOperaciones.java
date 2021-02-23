/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

/**
 *
 * @author RenRio
 */
public class MaeOperaciones {

    private String idDoc;
    private String operacion;
    private TipoOperacion objTipoOperacion;
    private String estado;

    public MaeOperaciones() {

        objTipoOperacion = new TipoOperacion();

    }

    /**
     * @return the idDoc
     */
    public String getIdDoc() {
        return idDoc;
    }

    /**
     * @param idDoc the idDoc to set
     */
    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the objTipoOperacion
     */
    public TipoOperacion getObjTipoOperacion() {
        return objTipoOperacion;
    }

    /**
     * @param objTipoOperacion the objTipoOperacion to set
     */
    public void setObjTipoOperacion(TipoOperacion objTipoOperacion) {
        this.objTipoOperacion = objTipoOperacion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
