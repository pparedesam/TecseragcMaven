/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

/**
 *
 * @author PauPar
 */
public class TipoComprobantePago {

    private String idTipoComprobante;
    private String descripcion;
    private String idDoc;
    private int estado;

    public String getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(String idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

}
