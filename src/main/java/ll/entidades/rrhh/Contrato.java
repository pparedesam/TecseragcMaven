/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.rrhh;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;
import java.sql.Date;

/**
 *
 * @author AndZuñ
 */
public class Contrato {
    private String codTra;
    private Puesto puesto;
    private String fecInicio;
    private String fecFin;
    private String remIncio;
    private String remFinal;
    private String estado;
    private Area area;
    private String tipoContrato;
    private Oficina oficina;
    private Cargo objCargo;
    
    public String getRemIncio() {
        return remIncio;
    }

    public void setRemIncio(String remIncio) {
        this.remIncio = remIncio;
    }

    public String getRemFinal() {
        return remFinal;
    }

    public void setRemFinal(String remFinal) {
        this.remFinal = remFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getCodTra() {
        return codTra;
    }

    public void setCodTra(String codTra) {
        this.codTra = codTra;
    }

    public String getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(String fecInicio) {
        this.fecInicio = fecInicio;
    }

    public String getFecFin() {
        return fecFin;
    }

    public void setFecFin(String fecFin) {
        this.fecFin = fecFin;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Cargo getObjCargo() {
        return objCargo;
    }

    public void setObjCargo(Cargo objCargo) {
        this.objCargo = objCargo;
    }
}
