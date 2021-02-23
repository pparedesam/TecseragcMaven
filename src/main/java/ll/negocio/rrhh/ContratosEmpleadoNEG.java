/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;

import java.util.List;

import ll.accesodatos.rrhh.ContratosEmpleadoDAO;
import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.Contrato;
import ll.entidades.rrhh.ContratosEmpleado;

/**
 *
 * @author AndZuñ
 */
public class ContratosEmpleadoNEG {

    private static ContratosEmpleadoNEG _Instancia;

    private ContratosEmpleadoNEG() {

    }

    public static ContratosEmpleadoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ContratosEmpleadoNEG();
        }
        return _Instancia;
    }

    public ContratosEmpleado obtenerContratosEmpleado(String idPersona) throws Exception {
        return ContratosEmpleadoDAO.Instancia().obtenerContratosEmpleado(idPersona);
    }

    public String actualizarContratoEmpleado(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().actualizarContratoEmpleado(idPersona, objEmpleado, objContrato, objUsuario);
        return result;

    }

    public String registarEmpleado(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().registarEmpleado(idPersona, objEmpleado, objContrato, objUsuario);
        return result;

    }

    public String agregarContratoEmpleado(Contrato objContrato, String idPersona) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().agregarContratoEmpleado(objContrato, idPersona);
        return result;

    }

    public String finContratoEmpleado(String idPersona, String fecSalida, String motSalida, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().finContratoEmpleado(idPersona, fecSalida, motSalida, objUsuario);
        return result;

    }

    /*----------------------------------------------------*/
    public ContratosEmpleado obtenerContratosColaborador(String idPersona) throws Exception {
        return ContratosEmpleadoDAO.Instancia().obtenerContratosColaborador(idPersona);
    }

    public String registarColaborador(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().registarColaborador(idPersona, objEmpleado, objContrato, objUsuario);
        return result;

    }

    public String actualizarContratoColaborador(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().actualizarContratoColaborador(idPersona, objEmpleado, objContrato, objUsuario);
        return result;

    }

    public String agregarContratoColaborador(Contrato objContrato, String idPersona) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().agregarContratoColaborador(objContrato, idPersona);
        return result;

    }

    public String finContratoColaborador(String idPersona, String fecSalida, String motSalida, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        result = ContratosEmpleadoDAO.Instancia().finContratoColaborador(idPersona, fecSalida, motSalida, objUsuario);
        return result;

    }

}
