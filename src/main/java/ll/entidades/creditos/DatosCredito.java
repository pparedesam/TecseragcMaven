/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.creditos;

import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Socio;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author Andrew
 */
public class DatosCredito {

    private DocumentoGenerado objDocGenerado;
    private String tipoCreditoObtenido;
    private ProductoCrediticio objProductoCrediticio;
    private String idTipoSBS;
    private String idTipoDestinoSBS;
    private Double montoSolicitado;
    private String frecuenciaPago;
    private String tipoCuota;
    private int plazoSolicitado;
    private Double icSolicitado;
    private int plazoAprobado;
    private Double icAprobado;
    private int diafijo;
    private String fechaVenPrimCuota;
    private String comentarios;
    private Empleado objEmpleadoAnalista;
    private Double montoAprobado;
    private Usuario objUsuario;
    private Double im;
    private String evaluacionSBS;
    private String calificacionSBS;
    private String idMercado;
    private String idModalidad;
    private String fechaDesembolso;
    private String tipoDesembolso;
    private Double liqApoIni;
    private Double liqDerIng;
    private Double liqDerSI;
    private Double liqFM;
    private Double liqSegCre;
    private Socio objSocio;
    private Double liqDerAdm;
    private Double liqVerifi;
    private Double liqMulEle;
    private Double liqCarpeta;
    private String categoria;
    private Empleado objEmpleadoAnalistaD;
    private String fechaCambioAnalista;

    public DatosCredito() {

        objProductoCrediticio = new ProductoCrediticio();
        objEmpleadoAnalista = new Empleado();
        objUsuario = new Usuario();
        objSocio = new Socio();
        objEmpleadoAnalistaD = new Empleado();
        objDocGenerado = new DocumentoGenerado();

    }

    public DocumentoGenerado getObjDocGenerado() {
        return objDocGenerado;
    }

    public void setObjDocGenerado(DocumentoGenerado objDocGenerado) {
        this.objDocGenerado = objDocGenerado;
    }

    public String getTipoCreditoObtenido() {
        return tipoCreditoObtenido;
    }

    public void setTipoCreditoObtenido(String tipoCreditoObtenido) {
        this.tipoCreditoObtenido = tipoCreditoObtenido;
    }

    public ProductoCrediticio getObjProductoCrediticio() {
        return objProductoCrediticio;
    }

    public void setObjProductoCrediticio(ProductoCrediticio objProductoCrediticio) {
        this.objProductoCrediticio = objProductoCrediticio;
    }

    public String getIdTipoSBS() {
        return idTipoSBS;
    }

    public void setIdTipoSBS(String idTipoSBS) {
        this.idTipoSBS = idTipoSBS;
    }

    public String getIdTipoDestinoSBS() {
        return idTipoDestinoSBS;
    }

    public void setIdTipoDestinoSBS(String idTipoDestinoSBS) {
        this.idTipoDestinoSBS = idTipoDestinoSBS;
    }

    public Double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(Double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public String getFrecuenciaPago() {
        return frecuenciaPago;
    }

    public void setFrecuenciaPago(String frecuenciaPago) {
        this.frecuenciaPago = frecuenciaPago;
    }

    public String getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }

    public int getPlazoSolicitado() {
        return plazoSolicitado;
    }

    public void setPlazoSolicitado(int plazoSolicitado) {
        this.plazoSolicitado = plazoSolicitado;
    }

    public Double getIcSolicitado() {
        return icSolicitado;
    }

    public void setIcSolicitado(Double icSolicitado) {
        this.icSolicitado = icSolicitado;
    }

    public int getPlazoAprobado() {
        return plazoAprobado;
    }

    public void setPlazoAprobado(int plazoAprobado) {
        this.plazoAprobado = plazoAprobado;
    }

    public Double getIcAprobado() {
        return icAprobado;
    }

    public void setIcAprobado(Double icAprobado) {
        this.icAprobado = icAprobado;
    }

    public int getDiafijo() {
        return diafijo;
    }

    public void setDiafijo(int diafijo) {
        this.diafijo = diafijo;
    }

    public String getFechaVenPrimCuota() {
        return fechaVenPrimCuota;
    }

    public void setFechaVenPrimCuota(String fechaVenPrimCuota) {
        this.fechaVenPrimCuota = fechaVenPrimCuota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Empleado getObjEmpleadoAnalista() {
        return objEmpleadoAnalista;
    }

    public void setObjEmpleadoAnalista(Empleado objEmpleadoAnalista) {
        this.objEmpleadoAnalista = objEmpleadoAnalista;
    }

    public Double getMontoAprobado() {
        return montoAprobado;
    }

    public void setMontoAprobado(Double montoAprobado) {
        this.montoAprobado = montoAprobado;
    }

    public Usuario getObUsuario() {
        return objUsuario;
    }

    public void setObUsuario(Usuario obUsuario) {
        this.objUsuario = obUsuario;
    }

    public Double getIm() {
        return im;
    }

    public void setIm(Double im) {
        this.im = im;
    }

    public String getEvaluacionSBS() {
        return evaluacionSBS;
    }

    public void setEvaluacionSBS(String evaluacionSBS) {
        this.evaluacionSBS = evaluacionSBS;
    }

    public String getCalificacionSBS() {
        return calificacionSBS;
    }

    public void setCalificacionSBS(String calificacionSBS) {
        this.calificacionSBS = calificacionSBS;
    }

    public String getIdMercado() {
        return idMercado;
    }

    public void setIdMercado(String idMercado) {
        this.idMercado = idMercado;
    }

    public String getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(String idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(String fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public String getTipoDesembolso() {
        return tipoDesembolso;
    }

    public void setTipoDesembolso(String tipoDesembolso) {
        this.tipoDesembolso = tipoDesembolso;
    }

    public Double getLiqApoIni() {
        return liqApoIni;
    }

    public void setLiqApoIni(Double liqApoIni) {
        this.liqApoIni = liqApoIni;
    }

    public Double getLiqDerIng() {
        return liqDerIng;
    }

    public void setLiqDerIng(Double liqDerIng) {
        this.liqDerIng = liqDerIng;
    }

    public Double getLiqDerSI() {
        return liqDerSI;
    }

    public void setLiqDerSI(Double liqDerSI) {
        this.liqDerSI = liqDerSI;
    }

    public Double getLiqFM() {
        return liqFM;
    }

    public void setLiqFM(Double liqFM) {
        this.liqFM = liqFM;
    }

    public Double getLiqSegCre() {
        return liqSegCre;
    }

    public void setLiqSegCre(Double liqSegCre) {
        this.liqSegCre = liqSegCre;
    }

    public Socio getObjSocio() {
        return objSocio;
    }

    public void setObjSocio(Socio objSocio) {
        this.objSocio = objSocio;
    }

    public Double getLiqDerAdm() {
        return liqDerAdm;
    }

    public void setLiqDerAdm(Double liqDerAdm) {
        this.liqDerAdm = liqDerAdm;
    }

    public Double getLiqVerifi() {
        return liqVerifi;
    }

    public void setLiqVerifi(Double liqVerifi) {
        this.liqVerifi = liqVerifi;
    }

    public Double getLiqMulEle() {
        return liqMulEle;
    }

    public void setLiqMulEle(Double liqMulEle) {
        this.liqMulEle = liqMulEle;
    }

    public Double getLiqCarpeta() {
        return liqCarpeta;
    }

    public void setLiqCarpeta(Double liqCarpeta) {
        this.liqCarpeta = liqCarpeta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Empleado getObjEmpleadoAnalistaD() {
        return objEmpleadoAnalistaD;
    }

    public void setObjEmpleadoAnalistaD(Empleado objEmpleadoAnalistaD) {
        this.objEmpleadoAnalistaD = objEmpleadoAnalistaD;
    }

    public String getFechaCambioAnalista() {
        return fechaCambioAnalista;
    }

    public void setFechaCambioAnalista(String fechaCambioAnalista) {
        this.fechaCambioAnalista = fechaCambioAnalista;
    }

}
