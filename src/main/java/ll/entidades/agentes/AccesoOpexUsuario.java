/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.agentes;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;
import ll.entidades.administracion.TipoMoneda;

/**
 *
 * @author CesGue
 */
public class AccesoOpexUsuario {
    private Puesto objPuesto;
    private Area objDepartamento;
    private Oficina objOficina;
    private MaeOperaciones objMaeOperaciones;
    private TipoMoneda objMoneda;
    private String TieneAcceso;
    
    public AccesoOpexUsuario(){
        
        objPuesto = new Puesto();
        objDepartamento = new Area();
        objOficina = new Oficina();
        objMaeOperaciones =  new MaeOperaciones();
        objMoneda = new TipoMoneda();
    }
    

    public Puesto getObjPuesto() {
        return objPuesto;
    }

    public void setObjPuesto(Puesto objPuesto) {
        this.objPuesto = objPuesto;
    }

    public Area getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Area objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public MaeOperaciones getObjMaeOperaciones() {
        return objMaeOperaciones;
    }

    public void setObjMaeOperaciones(MaeOperaciones objMaeOperaciones) {
        this.objMaeOperaciones = objMaeOperaciones;
    }

    public TipoMoneda getObjMoneda() {
        return objMoneda;
    }

    public void setObjMoneda(TipoMoneda objMoneda) {
        this.objMoneda = objMoneda;
    }

    public String getTieneAcceso() {
        return TieneAcceso;
    }

    public void setTieneAcceso(String TieneAcceso) {
        this.TieneAcceso = TieneAcceso;
    }    
}
