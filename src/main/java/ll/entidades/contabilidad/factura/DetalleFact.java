package ll.entidades.contabilidad.factura;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.contabilidad.TipoServicio;

/**
 *
 * @author RenRio
 */
public class DetalleFact {

    private String linea;
    private String idTipDocSUNAT;
    private String cantidad;
    private TipoServicio objTipoServicio;
    private String glosaVariable;
    private String total;
    private UnidadMedida objUnidadMedida;
    private String precioUnitario;
    private String valorUnitario;
    private List<DetalleMontoFact> listDetalleMontoFact;

    public DetalleFact() {
        objTipoServicio = new TipoServicio();
        listDetalleMontoFact = new ArrayList<DetalleMontoFact>();
        objUnidadMedida = new UnidadMedida();

    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    
    public UnidadMedida getObjUnidadMedida() {
        return objUnidadMedida;
    }

    public void setObjUnidadMedida(UnidadMedida objUnidadMedida) {
        this.objUnidadMedida = objUnidadMedida;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public TipoServicio getObjTipoServicio() {
        return objTipoServicio;
    }

    public void setObjTipoServicio(TipoServicio objTipoServicio) {
        this.objTipoServicio = objTipoServicio;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getIdTipDocSUNAT() {
        return idTipDocSUNAT;
    }

    public void setIdTipDocSUNAT(String idTipDocSUNAT) {
        this.idTipDocSUNAT = idTipDocSUNAT;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getGlosaVariable() {
        return glosaVariable;
    }

    public void setGlosaVariable(String glosaVariable) {
        this.glosaVariable = glosaVariable;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DetalleMontoFact> getListDetalleMontoFact() {
        return listDetalleMontoFact;
    }

    public void setListDetalleMontoFact(List<DetalleMontoFact> listDetalleMontoFact) {
        this.listDetalleMontoFact = listDetalleMontoFact;
    }

}
