/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.contabilidad;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.administracion.PersonaJuridica;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author RenRio
 */
public class DocCompraVenta {

    private String documentoId;
    private TipoComprobantePago objTipoDocumento;
    private String documentoNro;
    private PersonaJuridica objEmisor;
    private PersonaJuridica objEjecutor;
    private Usuario objUsuario;
    private Double total;
    private List<DetDocCompraVenta> listDetalleCpraVta;
    private UnidadMedida objUnidadMedida;
    private double cantidad;
    private double precioUnitario;
    private double valorUnitario;

    public DocCompraVenta() {

        objEmisor = new PersonaJuridica();
        objEjecutor = new PersonaJuridica();
        objUsuario = new Usuario();
        objUnidadMedida = new UnidadMedida();
        objTipoDocumento = new TipoComprobantePago();
        listDetalleCpraVta = new ArrayList<DetDocCompraVenta>();
    }

    public List<DetDocCompraVenta> getListDetalleCpraVta() {
        return listDetalleCpraVta;
    }

    public void setListDetalleCpraVta(List<DetDocCompraVenta> listDetalleCpraVta) {
        this.listDetalleCpraVta = listDetalleCpraVta;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public TipoComprobantePago getObjTipoDocumento() {
        return objTipoDocumento;
    }

    public void setObjTipoDocumento(TipoComprobantePago objTipoDocumento) {
        this.objTipoDocumento = objTipoDocumento;
    }

    public PersonaJuridica getObjEmisor() {
        return objEmisor;
    }

    public void setObjEmisor(PersonaJuridica objEmisor) {
        this.objEmisor = objEmisor;
    }

    public PersonaJuridica getObjEjecutor() {
        return objEjecutor;
    }

    public void setObjEjecutor(PersonaJuridica objEjecutor) {
        this.objEjecutor = objEjecutor;
    }

    public String getDocumentoNro() {
        return documentoNro;
    }

    public void setDocumentoNro(String documentoNro) {
        this.documentoNro = documentoNro;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public UnidadMedida getObjUnidadMedida() {
        return objUnidadMedida;
    }

    public void setObjUnidadMedida(UnidadMedida objUnidadMedida) {
        this.objUnidadMedida = objUnidadMedida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitarioM) {
        this.precioUnitario = precioUnitarioM;
    }

}
