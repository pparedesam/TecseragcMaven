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
public class Facturaxml {

    private String nroDoc;
    private String idDoc;
    private String tipM;
    private String idOfi;
    private String observacion;

    private String fechaEmision;
    private String nombreComercial;
    private DomicilioFiscalxml objDomicilioFiscalxml;
    private Personaxml objPersonaxmlEmisor;
    private String tipoDocumento;
    private String numeracionFactura;
    private Personaxml objPersonaxmlEjecutor;
    private List<DetalleItemxml> listDetalleItemxml;
    private ValorVentaxml objOperacionGravada;
    private ValorVentaxml objOperacionInafecta;
    private ValorVentaxml objOperacionExonerada;
    private String importeTotal;
    private String tipMoneda;
    private Leyendaxml objLeyendaxml;
    private String versionUBL;
    private String versionDoc;
    private String igvTotal;
    private String porcentajeImpuesto;
    private String signatureValue;
    private String digestValue;
    private String response;

    private List<GuiaRemisionxml> listGuiaRemisionxml;

    public Facturaxml() {
        objDomicilioFiscalxml = new DomicilioFiscalxml();
        objPersonaxmlEmisor = new Personaxml();
        objPersonaxmlEjecutor = new Personaxml();
        listDetalleItemxml = new ArrayList<DetalleItemxml>();
        objOperacionGravada = new ValorVentaxml();
        objOperacionInafecta = new ValorVentaxml();
        objOperacionExonerada = new ValorVentaxml();
        objLeyendaxml = new Leyendaxml();
        listGuiaRemisionxml = new ArrayList<GuiaRemisionxml>();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<GuiaRemisionxml> getListGuiaRemisionxml() {
        return listGuiaRemisionxml;
    }

    public void setListGuiaRemisionxml(List<GuiaRemisionxml> listGuiaRemisionxml) {
        this.listGuiaRemisionxml = listGuiaRemisionxml;
    }

    public String getIgvTotal() {
        return igvTotal;
    }

    public void setIgvTotal(String igvTotal) {
        this.igvTotal = igvTotal;
    }

    public String getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(String porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getTipM() {
        return tipM;
    }

    public void setTipM(String tipM) {
        this.tipM = tipM;
    }

    public String getIdOfi() {
        return idOfi;
    }

    public void setIdOfi(String idOfi) {
        this.idOfi = idOfi;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public DomicilioFiscalxml getObjDomicilioFiscalxml() {
        return objDomicilioFiscalxml;
    }

    public void setObjDomicilioFiscalxml(DomicilioFiscalxml objDomicilioFiscalxml) {
        this.objDomicilioFiscalxml = objDomicilioFiscalxml;
    }

    public Personaxml getObjPersonaxmlEmisor() {
        return objPersonaxmlEmisor;
    }

    public void setObjPersonaxmlEmisor(Personaxml objPersonaxmlEmisor) {
        this.objPersonaxmlEmisor = objPersonaxmlEmisor;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeracionFactura() {
        return numeracionFactura;
    }

    public void setNumeracionFactura(String numeracionFactura) {
        this.numeracionFactura = numeracionFactura;
    }

    public Personaxml getObjPersonaxmlEjecutor() {
        return objPersonaxmlEjecutor;
    }

    public void setObjPersonaxmlEjecutor(Personaxml objPersonaxmlEjecutor) {
        this.objPersonaxmlEjecutor = objPersonaxmlEjecutor;
    }

    public List<DetalleItemxml> getListDetalleItemxml() {
        return listDetalleItemxml;
    }

    public void setListDetalleItemxml(List<DetalleItemxml> listDetalleItemxml) {
        this.listDetalleItemxml = listDetalleItemxml;
    }

    public ValorVentaxml getObjOperacionGravada() {
        return objOperacionGravada;
    }

    public void setObjOperacionGravada(ValorVentaxml objOperacionGravada) {
        this.objOperacionGravada = objOperacionGravada;
    }

    public ValorVentaxml getObjOperacionInafecta() {
        return objOperacionInafecta;
    }

    public void setObjOperacionInafecta(ValorVentaxml objOperacionInafecta) {
        this.objOperacionInafecta = objOperacionInafecta;
    }

    public ValorVentaxml getObjOperacionExonerada() {
        return objOperacionExonerada;
    }

    public void setObjOperacionExonerada(ValorVentaxml objOperacionExonerada) {
        this.objOperacionExonerada = objOperacionExonerada;
    }

    public String getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public Leyendaxml getObjLeyendaxml() {
        return objLeyendaxml;
    }

    public void setObjLeyendaxml(Leyendaxml objLeyendaxml) {
        this.objLeyendaxml = objLeyendaxml;
    }

    public String getVersionUBL() {
        return versionUBL;
    }

    public void setVersionUBL(String versionUBL) {
        this.versionUBL = versionUBL;
    }

    public String getVersionDoc() {
        return versionDoc;
    }

    public void setVersionDoc(String versionDoc) {
        this.versionDoc = versionDoc;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

}
