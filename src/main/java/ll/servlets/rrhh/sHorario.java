/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.rrhh;

import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.GrupoHorarioPersonal;
import ll.negocio.rrhh.HorarioNEG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RenRio
 */
public class sHorario extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sHorario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sHorario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        try {

            if (action.equalsIgnoreCase("listarGrupoHorario")) {
                listarGrupoHorario();
            } else if (action.equalsIgnoreCase("registrarGrupo")) {
                registrarGrupo();
            } else if (action.equalsIgnoreCase("eliminarGrupo")) {
                eliminarGrupo();
            } else if (action.equalsIgnoreCase("listarGrupoHorarioPersonal")) {
                listarGrupoHorarioPersonal();
            } else if (action.equalsIgnoreCase("registrarHorarioPersonal")) {
                registrarHorarioPersonal();
            } else if (action.equalsIgnoreCase("eliminarPersonal")) {
                eliminarPersonal();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            json = gson.toJson(data);
            response.getWriter().write(json);
        }
    }

    private void listarGrupoHorario() throws Exception {
        //   HttpSession sesion = varRequest.getSession();
        //Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        List<GrupoHorario> listaGrupoHorario = HorarioNEG.Instancia().listarGrupo();

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("listaGrupoHorario", listaGrupoHorario);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void registrarGrupo() throws Exception {

        String desicion = varRequest.getParameter("decision");

        GrupoHorario objGrupoHorario = new GrupoHorario();
        objGrupoHorario.setIdGrupo(Integer.parseInt(varRequest.getParameter("idGrupo")));
        objGrupoHorario.setNombreGrupo(varRequest.getParameter("Grupo"));
        objGrupoHorario.setDescGrupo(varRequest.getParameter("Descripcion"));
        objGrupoHorario.setDiaEntrada(varRequest.getParameter("MananaEntrada"));
        objGrupoHorario.setDiaSalida(varRequest.getParameter("MananaSalida"));
        objGrupoHorario.setTardeEntrada(varRequest.getParameter("TardeEntrada"));
        objGrupoHorario.setTardeSalida(varRequest.getParameter("TardeSalida"));

        String result = HorarioNEG.Instancia().registrarGrupoHorario(objGrupoHorario, desicion);

        data.put("Result", "OK");
        data.put("Message", result);
        json = gson.toJson(data);
        varResponse.getWriter().write(json);
    }

    private void eliminarGrupo() throws Exception {

        String desicion = varRequest.getParameter("decision");

        GrupoHorario objGrupoHorario = new GrupoHorario();
        objGrupoHorario.setIdGrupo(Integer.parseInt(varRequest.getParameter("idGrupo")));
        String result = HorarioNEG.Instancia().registrarGrupoHorario(objGrupoHorario, desicion);

        data.put("Result", "OK");
        data.put("Message", result);
        json = gson.toJson(data);
        varResponse.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarGrupoHorarioPersonal() throws Exception {

        HttpSession ses = varRequest.getSession();
        String idGrupo = varRequest.getParameter("idGrupo");

        List<GrupoHorarioPersonal> listaGrupoHorarioPersonal = HorarioNEG.Instancia().listarGrupoHorarioPersonal(idGrupo);
        ses.removeAttribute("listaDetalleFactO");
        ArrayList<HashMap<String, String>> listaGHP = new ArrayList<>();
        for (int i = 0; i < listaGrupoHorarioPersonal.size(); i++) {
            HashMap<String, String> lista = new HashMap<>();
            lista.put("idGrupo", String.valueOf(listaGrupoHorarioPersonal.get(i).getObjGrupoHorario().getIdGrupo()));
            lista.put("nombreGrupo", listaGrupoHorarioPersonal.get(i).getObjGrupoHorario().getNombreGrupo());
            lista.put("idPersona", listaGrupoHorarioPersonal.get(i).getObjEmpleado().getIdPersona());
            lista.put("personal", listaGrupoHorarioPersonal.get(i).getObjEmpleado().getNombres());
            lista.put("oficina", listaGrupoHorarioPersonal.get(i).getObjEmpleado().getObjOficina().getNombre());
            lista.put("area", listaGrupoHorarioPersonal.get(i).getObjEmpleado().getObjArea().getDescripcion());
            lista.put("puesto", listaGrupoHorarioPersonal.get(i).getObjEmpleado().getObjPuesto().getNombre());
            listaGHP.add(lista);
        }
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("listaGrupoHorarioPersonal", listaGHP);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void registrarHorarioPersonal() throws Exception {
        //   HttpSession sesion = varRequest.getSession();
        //Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        String idGrupo = varRequest.getParameter("idGrupo");
        String idPersona = varRequest.getParameter("idpersona");

        String result = HorarioNEG.Instancia().registrarGrupoHorarioPersonal(idGrupo, idPersona);

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("Codigo", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void eliminarPersonal() throws Exception {
        HttpSession ses = varRequest.getSession();
        Usuario objUsuario = (Usuario) ses.getAttribute("user");
        String idGrupo = varRequest.getParameter("idGrupo");
        String idPersona = varRequest.getParameter("idpersona");

        String result = HorarioNEG.Instancia().eliminarPersonal(idGrupo, idPersona, objUsuario);

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("Codigo", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

}
