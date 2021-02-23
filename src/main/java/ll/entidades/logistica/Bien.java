/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.logistica;

import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.TipoMoneda;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author CesGue
 */
public class Bien {
   private int idbien;
   private String descripcion;
   private String nombre;
   private String ubicacion;
   private String estado;
   private Empleado objEmpleado ;
   private Departamento objDepartamento;
   private String fechaAdquisicion;
   private String fechaAsignacion;
   private Double saldoInicial;
   private String enUso;
   private EstadoBien objEstadoBien;
   private DocumentoGenerado objDocGenerado;
   private TipoMoneda objTipoMoneda;
   private Oficina objOficina;
   private CaracteristicaBien objCaractBien;
   private int ctaContable;
   private String ctaPresupuestal;
   
       public Bien() {
        
        objEstadoBien= new EstadoBien();
        objDocGenerado = new DocumentoGenerado();
        objTipoMoneda = new TipoMoneda();
        objOficina = new Oficina();
        objCaractBien = new CaracteristicaBien();
        objDepartamento = new Departamento();
        objEmpleado = new Empleado();
        
    }
   
   
    public int getIdbien() {
        return idbien;
    }

    public void setIdbien(int idbien) {
        this.idbien = idbien;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getObjEmpleado() {
        return objEmpleado;
    }

    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }



    public Departamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }



    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEnUso() {
        return enUso;
    }

    public void setEnUso(String enUso) {
        this.enUso = enUso;
    }

    public EstadoBien getObjEstadoBien() {
        return objEstadoBien;
    }

    public void setObjEstadoBien(EstadoBien objEstadoBien) {
        this.objEstadoBien = objEstadoBien;
    }

    public DocumentoGenerado getObjDocGenerado() {
        return objDocGenerado;
    }

    public void setObjDocGenerado(DocumentoGenerado objDocGenerado) {
        this.objDocGenerado = objDocGenerado;
    }

    public int getCtaContable() {
        return ctaContable;
    }

    public void setCtaContable(int ctaContable) {
        this.ctaContable = ctaContable;
    }

    public TipoMoneda getObjTipoMoneda() {
        return objTipoMoneda;
    }

    public void setObjTipoMoneda(TipoMoneda objTipoMoneda) {
        this.objTipoMoneda = objTipoMoneda;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public CaracteristicaBien getObjCaractBien() {
        return objCaractBien;
    }

    public void setObjCaractBien(CaracteristicaBien objCaractBien) {
        this.objCaractBien = objCaractBien;
    }

    public String getCtaPresupuestal() {
        return ctaPresupuestal;
    }

    public void setCtaPresupuestal(String ctaPresupuestal) {
        this.ctaPresupuestal = ctaPresupuestal;
    }

   
    
    
}
