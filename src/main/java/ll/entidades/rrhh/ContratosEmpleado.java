/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.rrhh;

import ll.entidades.administracion.Empleado;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AndZuñ
 */
public class ContratosEmpleado {

    private Empleado objEmpleado;
    private Contrato objContratoActual;
    private List<Contrato> objHistorialContratos;

    public ContratosEmpleado() {
        objEmpleado = new Empleado();
        objContratoActual = new Contrato();
        objHistorialContratos = new ArrayList<>();
    }

    public Empleado getObjEmpleado() {
        return objEmpleado;
    }

    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }

    public Contrato getObjContratoActual() {
        return objContratoActual;
    }

    public void setObjContratoActual(Contrato objContratoActual) {
        this.objContratoActual = objContratoActual;
    }

    public List<Contrato> getObjHistorialContratos() {
        return objHistorialContratos;
    }

    public void setObjHistorialContratos(List<Contrato> objHistorialContratos) {
        this.objHistorialContratos = objHistorialContratos;
    }
    
    
}
