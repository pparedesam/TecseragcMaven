/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.rrhh;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Banco;

import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;
import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.Cargo;
import ll.entidades.rrhh.Contrato;
import ll.entidades.rrhh.ContratosEmpleado;
import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.SistemaPensiones;

import ll.entidades.rrhh.TipoPlanEPS;

import ll.negocio.rrhh.ContratosEmpleadoNEG;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
 * @author AndZuñ
 */
public class sContrato extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        try {
            if (action.equalsIgnoreCase("obtenerContratosEmpleado")) {
                obtenerContratosEmpleado();
            }
            if (action.equalsIgnoreCase("registarEmpleado")) {
                registarEmpleado();
            }
            if (action.equalsIgnoreCase("actualizarContratoEmpleado")) {
                actualizarContratoEmpleado();
            }
            if (action.equalsIgnoreCase("agregarContratoEmpleado")) {
                agregarContratoEmpleado();
            }
            if (action.equalsIgnoreCase("finContratoEmpleado")) {
                finContratoEmpleado();
            }
            if (action.equalsIgnoreCase("obtenerContratosColaborador")) {
                obtenerContratosColaborador();
            }
            if (action.equalsIgnoreCase("registarColaborador")) {
                registarColaborador();
            }
            if (action.equalsIgnoreCase("actualizarContratoColaborador")) {
                actualizarContratoColaborador();
            }
            if (action.equalsIgnoreCase("agregarContratoColaborador")) {
                agregarContratoColaborador();
            }
            if (action.equalsIgnoreCase("finContratoColaborador")) {
                finContratoColaborador();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }
    }

    private void obtenerContratosEmpleado() throws Exception {

        String idPersona = varRequest.getParameter("idPersona");
        ContratosEmpleado contratosEmpleado = ContratosEmpleadoNEG.Instancia().obtenerContratosEmpleado(idPersona);
        dataObject = new HashMap<>();

        dataObject.put("Result", "OK");
        dataObject.put("ContratosEmpleado", contratosEmpleado);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void registarEmpleado() throws Exception {

        Empleado objEmpleado = new Empleado();
        Banco objBanco = new Banco();
        Contrato objContrato = new Contrato();
        Puesto objPuesto = new Puesto();
        Area objArea = new Area();
        Oficina objOficina = new Oficina();
        SistemaPensiones objSistemaPensiones = new SistemaPensiones();

        String idPersona = varRequest.getParameter("idPersona");

        String idBanco = varRequest.getParameter("Banco");
        String AFP = varRequest.getParameter("AFP");
        String NroCuenta = varRequest.getParameter("NroCuenta");
        String CodigoAFP = varRequest.getParameter("CodigoAFP");
        String EPS = varRequest.getParameter("EPS");

        String TipoComision = varRequest.getParameter("TipoComision");
        String ESSALUD = varRequest.getParameter("ESSALUD");
        String NroHijos = varRequest.getParameter("NroHijos");
        String Foto = varRequest.getParameter("croppedImageDataURLFoto");
        String Fiscalizado = varRequest.getParameter("Fiscalizado");
        int PlanEPS = Integer.parseInt(varRequest.getParameter("PlanEPS"));

        String[] resultfoto = Foto.split(",");

        String idCargo = varRequest.getParameter("idCargo");
        String tipContrato = varRequest.getParameter("tipContrato");
        String fecFinal = varRequest.getParameter("fecFinal");
        String salActual = varRequest.getParameter("salActual");
        HttpSession sesion = varRequest.getSession();

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        objSistemaPensiones.setIdSistema(AFP);

        objBanco.setIdBanco(idBanco);

        TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
        objTipoPlanEPS.setIdEPS(PlanEPS);

        Cargo objCargo = new Cargo();
        objCargo.setIdCargo(idCargo);

        objEmpleado.setBanco(objBanco);
        objEmpleado.setObjSistemaPensiones(objSistemaPensiones);
        objEmpleado.setCtaSueldo(NroCuenta);
        objEmpleado.setCodigoSistema(CodigoAFP);
        objEmpleado.setEPS(EPS);
        objEmpleado.setComisionMixta(TipoComision);
        objEmpleado.setESSALUD(ESSALUD);
        objEmpleado.setNroHijos(Integer.parseInt(NroHijos));
        objEmpleado.setFoto(resultfoto[1]);
        objEmpleado.setFiscalizado(Fiscalizado);

        objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);

        objPuesto.setIdPuesto(varRequest.getParameter("puesto"));
        objArea.setCodigo(varRequest.getParameter("departamento"));
        objOficina.setIdOficina(varRequest.getParameter("oficina"));

        GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
        objGrupoHorarioLunVie.setIdGrupo(Integer.parseInt(varRequest.getParameter("LunVie")));

        GrupoHorario objGrupoHorarioSab = new GrupoHorario();
        objGrupoHorarioSab.setIdGrupo(Integer.parseInt(varRequest.getParameter("Sab")));

        objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);
        objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

        objContrato.setObjCargo(objCargo);
        objContrato.setPuesto(objPuesto);
        objContrato.setFecInicio(varRequest.getParameter("fecInicio"));
        objContrato.setRemIncio(salActual);
        objContrato.setFecFin(fecFinal);
        objContrato.setRemFinal(salActual);
        objContrato.setArea(objArea);
        objContrato.setTipoContrato(tipContrato);
        objContrato.setOficina(objOficina);

        String result = ContratosEmpleadoNEG.Instancia().registarEmpleado(idPersona, objEmpleado, objContrato, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void actualizarContratoEmpleado() throws Exception {

        Empleado objEmpleado = new Empleado();
        Banco objBanco = new Banco();
        Contrato objContrato = new Contrato();
        SistemaPensiones objSistemaPensiones = new SistemaPensiones();

        String idPersona = varRequest.getParameter("idPersona");

        String idBanco = varRequest.getParameter("Banco");
        String AFP = varRequest.getParameter("AFP");
        String NroCuenta = varRequest.getParameter("NroCuenta");
        String CodigoAFP = varRequest.getParameter("CodigoAFP");
        String EPS = varRequest.getParameter("EPS");

        String TipoComision = varRequest.getParameter("TipoComision");
        String ESSALUD = varRequest.getParameter("ESSALUD");
        String NroHijos = varRequest.getParameter("NroHijos");
        String Foto = varRequest.getParameter("croppedImageDataURLFoto");
        String Fiscalizado = varRequest.getParameter("Fiscalizado");
        int PlanEPS = Integer.parseInt(varRequest.getParameter("PlanEPS"));

        String[] resultfoto = Foto.split(",");

        String idCargo = varRequest.getParameter("idCargo");
        String tipContrato = varRequest.getParameter("tipContrato");
        String fecFinal = varRequest.getParameter("fecFinal");
        String salActual = varRequest.getParameter("salActual");
        HttpSession sesion = varRequest.getSession();

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        objBanco.setIdBanco(idBanco);
        objSistemaPensiones.setIdSistema(AFP);

        TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
        objTipoPlanEPS.setIdEPS(PlanEPS);

        Cargo objCargo = new Cargo();
        objCargo.setIdCargo(idCargo);

        objEmpleado.setBanco(objBanco);
        objEmpleado.setObjSistemaPensiones(objSistemaPensiones);
        objEmpleado.setCtaSueldo(NroCuenta);
        objEmpleado.setCodigoSistema(CodigoAFP);
        objEmpleado.setEPS(EPS);
        objEmpleado.setComisionMixta(TipoComision);
        objEmpleado.setESSALUD(ESSALUD);
        objEmpleado.setNroHijos(Integer.parseInt(NroHijos));
        objEmpleado.setFoto(resultfoto[1]);
        objEmpleado.setFiscalizado(Fiscalizado);
        objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);

        objContrato.setObjCargo(objCargo);
        objContrato.setTipoContrato(tipContrato);
        objContrato.setFecFin(fecFinal);
        objContrato.setRemFinal(salActual);

        GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
        objGrupoHorarioLunVie.setIdGrupo(Integer.parseInt(varRequest.getParameter("LunVie")));

        GrupoHorario objGrupoHorarioSab = new GrupoHorario();
        objGrupoHorarioSab.setIdGrupo(Integer.parseInt(varRequest.getParameter("Sab")));

        objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);
        objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

        String result = ContratosEmpleadoNEG.Instancia().actualizarContratoEmpleado(idPersona, objEmpleado, objContrato, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void agregarContratoEmpleado() throws Exception {
        Puesto objPuesto = new Puesto();
        Area objArea = new Area();
        Oficina objOficina = new Oficina();

        objPuesto.setIdPuesto(varRequest.getParameter("puesto"));
        objArea.setCodigo(varRequest.getParameter("departamento"));
        objOficina.setIdOficina(varRequest.getParameter("oficina"));
        String idCargo = varRequest.getParameter("idCargo");

        Cargo objCargo = new Cargo();
        objCargo.setIdCargo(idCargo);

        Contrato objContrato = new Contrato();
        String idPersona = varRequest.getParameter("idPersona");

        objContrato.setObjCargo(objCargo);
        objContrato.setPuesto(objPuesto);
        objContrato.setFecInicio(varRequest.getParameter("fecInicio"));
        objContrato.setRemIncio(varRequest.getParameter("salActual"));
        objContrato.setFecFin(varRequest.getParameter("fecFinal"));
        objContrato.setRemFinal(varRequest.getParameter("salActual"));
        objContrato.setArea(objArea);

        objContrato.setTipoContrato(varRequest.getParameter("tipContrato"));
        objContrato.setOficina(objOficina);
        HttpSession sesion = varRequest.getSession();

        String result = ContratosEmpleadoNEG.Instancia().agregarContratoEmpleado(objContrato, idPersona);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void finContratoEmpleado() throws Exception {
        String idPersona = varRequest.getParameter("idPersona");
        String fecSalida = varRequest.getParameter("fecFinal");
        String motSalida = varRequest.getParameter("motSalida");
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        String result = ContratosEmpleadoNEG.Instancia().finContratoEmpleado(idPersona, fecSalida, motSalida, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    /*----------------------------------------------------------------*/
    private void obtenerContratosColaborador() throws Exception {

        String idPersona = varRequest.getParameter("idPersona");
        ContratosEmpleado contratosEmpleado = ContratosEmpleadoNEG.Instancia().obtenerContratosColaborador(idPersona);
        dataObject = new HashMap<>();

        dataObject.put("Result", "OK");
        dataObject.put("ContratosEmpleado", contratosEmpleado);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void registarColaborador() throws Exception {

        Empleado objEmpleado = new Empleado();
        Banco objBanco = new Banco();
        Contrato objContrato = new Contrato();
        Puesto objPuesto = new Puesto();
        Area objArea = new Area();
        Oficina objOficina = new Oficina();
//        SistemaPensiones objSistemaPensiones = new SistemaPensiones();

        String idPersona = varRequest.getParameter("idPersona");

        String idBanco = varRequest.getParameter("Banco");
//        String AFP = varRequest.getParameter("AFP");
        String NroCuenta = varRequest.getParameter("NroCuenta");
//        String CodigoAFP = varRequest.getParameter("CodigoAFP");
//        String EPS = varRequest.getParameter("EPS");

//        String TipoComision = varRequest.getParameter("TipoComision");
//        String ESSALUD = varRequest.getParameter("ESSALUD");
//        String NroHijos = varRequest.getParameter("NroHijos");
        String Foto = varRequest.getParameter("croppedImageDataURLFoto");
        String Fiscalizado = varRequest.getParameter("Fiscalizado");
//        int PlanEPS = Integer.parseInt(varRequest.getParameter("PlanEPS"));

        String[] resultfoto = Foto.split(",");

//        String idCargo = varRequest.getParameter("idCargo");
        String tipContrato = varRequest.getParameter("tipContrato");
        String fecFinal = varRequest.getParameter("fecFinal");
        String salActual = varRequest.getParameter("salActual");
        HttpSession sesion = varRequest.getSession();

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

//        objSistemaPensiones.setIdSistema(AFP);
        objBanco.setIdBanco(idBanco);

//        TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
//        objTipoPlanEPS.setIdEPS(PlanEPS);
//        Cargo objCargo = new Cargo();
//        objCargo.setIdCargo(idCargo);
        objEmpleado.setBanco(objBanco);
//        objEmpleado.setObjSistemaPensiones(objSistemaPensiones);
        objEmpleado.setCtaSueldo(NroCuenta);
//        objEmpleado.setCodigoSistema(CodigoAFP);
//        objEmpleado.setEPS(EPS);
//        objEmpleado.setComisionMixta(TipoComision);
//        objEmpleado.setESSALUD(ESSALUD);
//        objEmpleado.setNroHijos(Integer.parseInt(NroHijos));
        objEmpleado.setFoto(resultfoto[1]);
        objEmpleado.setFiscalizado(Fiscalizado);

//        objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);
        objPuesto.setIdPuesto(varRequest.getParameter("puesto"));
        objArea.setCodigo(varRequest.getParameter("departamento"));
        objOficina.setIdOficina(varRequest.getParameter("oficina"));

        GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
        objGrupoHorarioLunVie.setIdGrupo(Integer.parseInt(varRequest.getParameter("LunVie")));

        GrupoHorario objGrupoHorarioSab = new GrupoHorario();
        objGrupoHorarioSab.setIdGrupo(Integer.parseInt(varRequest.getParameter("Sab")));

        objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);
        objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

//        objContrato.setObjCargo(objCargo);
        objContrato.setPuesto(objPuesto);
        objContrato.setFecInicio(varRequest.getParameter("fecInicio"));
        objContrato.setRemIncio(salActual);
        objContrato.setFecFin(fecFinal);
        objContrato.setRemFinal(salActual);
        objContrato.setArea(objArea);
        objContrato.setTipoContrato(tipContrato);
        objContrato.setOficina(objOficina);

        String result = ContratosEmpleadoNEG.Instancia().registarColaborador(idPersona, objEmpleado, objContrato, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void actualizarContratoColaborador() throws Exception {

        Empleado objEmpleado = new Empleado();
        Banco objBanco = new Banco();
        Contrato objContrato = new Contrato();
//        SistemaPensiones objSistemaPensiones = new SistemaPensiones();

        String idPersona = varRequest.getParameter("idPersona");

        String idBanco = varRequest.getParameter("Banco");
//        String AFP = varRequest.getParameter("AFP");
        String NroCuenta = varRequest.getParameter("NroCuenta");
//        String CodigoAFP = varRequest.getParameter("CodigoAFP");
//        String EPS = varRequest.getParameter("EPS");
//
//        String TipoComision = varRequest.getParameter("TipoComision");
//        String ESSALUD = varRequest.getParameter("ESSALUD");
//        String NroHijos = varRequest.getParameter("NroHijos");
        String Foto = varRequest.getParameter("croppedImageDataURLFoto");
        String Fiscalizado = varRequest.getParameter("Fiscalizado");
//        int PlanEPS = Integer.parseInt(varRequest.getParameter("PlanEPS"));

        String[] resultfoto = Foto.split(",");

//        String idCargo = varRequest.getParameter("idCargo");
        String tipContrato = varRequest.getParameter("tipContrato");
        String fecFinal = varRequest.getParameter("fecFinal");
        String salActual = varRequest.getParameter("salActual");
        HttpSession sesion = varRequest.getSession();

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        objBanco.setIdBanco(idBanco);
//        objSistemaPensiones.setIdSistema(AFP);

//        TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
//        objTipoPlanEPS.setIdEPS(PlanEPS);
//
//        Cargo objCargo = new Cargo();
//        objCargo.setIdCargo(idCargo);
        objEmpleado.setBanco(objBanco);
//        objEmpleado.setObjSistemaPensiones(objSistemaPensiones);
        objEmpleado.setCtaSueldo(NroCuenta);
//        objEmpleado.setCodigoSistema(CodigoAFP);
//        objEmpleado.setEPS(EPS);
//        objEmpleado.setComisionMixta(TipoComision);
//        objEmpleado.setESSALUD(ESSALUD);
//        objEmpleado.setNroHijos(Integer.parseInt(NroHijos));
        objEmpleado.setFoto(resultfoto[1]);
        objEmpleado.setFiscalizado(Fiscalizado);
//        objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);

//        objContrato.setObjCargo(objCargo);
        objContrato.setTipoContrato(tipContrato);
        objContrato.setFecFin(fecFinal);
        objContrato.setRemFinal(salActual);

        GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
        objGrupoHorarioLunVie.setIdGrupo(Integer.parseInt(varRequest.getParameter("LunVie")));

        GrupoHorario objGrupoHorarioSab = new GrupoHorario();
        objGrupoHorarioSab.setIdGrupo(Integer.parseInt(varRequest.getParameter("Sab")));

        objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);
        objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

        String result = ContratosEmpleadoNEG.Instancia().actualizarContratoColaborador(idPersona, objEmpleado, objContrato, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

     private void agregarContratoColaborador() throws Exception {
        Puesto objPuesto = new Puesto();
        Area objArea = new Area();
        Oficina objOficina = new Oficina();

        objPuesto.setIdPuesto(varRequest.getParameter("puesto"));
        objArea.setCodigo(varRequest.getParameter("departamento"));
        objOficina.setIdOficina(varRequest.getParameter("oficina"));
        String idCargo = varRequest.getParameter("idCargo");

        Cargo objCargo = new Cargo();
        objCargo.setIdCargo(idCargo);

        Contrato objContrato = new Contrato();
        String idPersona = varRequest.getParameter("idPersona");

        objContrato.setObjCargo(objCargo);
        objContrato.setPuesto(objPuesto);
        objContrato.setFecInicio(varRequest.getParameter("fecInicio"));
        objContrato.setRemIncio(varRequest.getParameter("salActual"));
        objContrato.setFecFin(varRequest.getParameter("fecFinal"));
        objContrato.setRemFinal(varRequest.getParameter("salActual"));
        objContrato.setArea(objArea);

        objContrato.setTipoContrato(varRequest.getParameter("tipContrato"));
        objContrato.setOficina(objOficina);
        HttpSession sesion = varRequest.getSession();

        String result = ContratosEmpleadoNEG.Instancia().agregarContratoColaborador(objContrato, idPersona);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }
    
    private void finContratoColaborador() throws Exception {
        String idPersona = varRequest.getParameter("idPersona");
        String fecSalida = varRequest.getParameter("fecFinal");
        String motSalida = varRequest.getParameter("motSalida");
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        String result = ContratosEmpleadoNEG.Instancia().finContratoColaborador(idPersona, fecSalida, motSalida, objUsuario);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

}
