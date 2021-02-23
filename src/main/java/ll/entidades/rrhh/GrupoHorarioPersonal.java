/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.rrhh;
import ll.entidades.administracion.Empleado;

/**
 *
 * @author RenRio
 */
public class GrupoHorarioPersonal {

    private GrupoHorario objGrupoHorario;
    private Empleado objEmpleado;

    public GrupoHorarioPersonal() {
        objGrupoHorario = new GrupoHorario();
        objEmpleado = new Empleado();
    }

    public GrupoHorario getObjGrupoHorario() {
        return objGrupoHorario;
    }

    public void setObjGrupoHorario(GrupoHorario objGrupoHorario) {
        this.objGrupoHorario = objGrupoHorario;
    }

    public Empleado getObjEmpleado() {
        return objEmpleado;
    }

    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }

}
