/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.rrhh;

import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.EmpleadoNEG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
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
public class sEmpleado extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    String json;
    Map<String, String> data = new HashMap<>();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sEmpleado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sEmpleado at " + request.getContextPath() + "</h1>");
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

        varRequest = request;
        varResponse = response;
        dataObject.clear();
        dataString.clear();

        String action = varRequest.getParameter("action");

        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        varResponse.setHeader("Pragma", "no-cache");
        varResponse.setDateHeader("Expires", 0);
        try {

            if (action.equalsIgnoreCase("obtenerEmpleado")) {
                obtenerEmpleado();
            }
            if (action.equalsIgnoreCase("listEmpleados")) {
                listEmpleados();
            }
            if (action.equalsIgnoreCase("listColaboradores")) {
                listColaboradores();
            }
            if (action.equalsIgnoreCase("listPersonalCobranzas")) {
                listPersonalCobranzas();
            }
            if (action.equalsIgnoreCase("obtenerPersonal")) {
                obtenerPersonal();
            } else if (action.equalsIgnoreCase("obtenerUsuarioMarcacion")) {
                obtenerUsuarioMarcacion();
            } else if (action.equalsIgnoreCase("vinculacionMarcacion")) {
                vinculacionMarcacion();
            } else if (action.equalsIgnoreCase("listarEmpleados")) {
                listarEmpleados();
            }

        } catch (Exception ex) {
            dataString.put("Result", "error");
            dataString.put("Message", ex.getMessage());
            json = gson.toJson(dataString);
            varResponse.getWriter().write(json);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void obtenerEmpleado() throws Exception {
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        List<Empleado> listEmpleado = EmpleadoNEG.Instancia().listaEmpleadoxCargo(objUsuario);
        dataObject.put("Result", "OK");
        dataObject.put("listEmpleado", listEmpleado);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void listPersonalCobranzas() throws Exception {

        String idoficina = varRequest.getParameter("cboOficina");

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("listarPersonalCobranza", EmpleadoNEG.Instancia().obtenerPersonalCobranza(idoficina));
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void listEmpleados() throws Exception {
        String txtCriterio = varRequest.getParameter("txtBuscarEmpleado");
        int txtTipoBusqueda = Integer.parseInt(varRequest.getParameter("cboTipoBusqueda"));

        List<Empleado> listEmpleados = EmpleadoNEG.Instancia().obtenerlistaEmpleado(txtTipoBusqueda, txtCriterio);

        dataObject.put("Result", "OK");
        dataObject.put("listEmpleados", listEmpleados);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void listColaboradores() throws Exception {
        String txtCriterio = varRequest.getParameter("txtBuscarEmpleado");
        int txtTipoBusqueda = Integer.parseInt(varRequest.getParameter("cboTipoBusqueda"));

        List<Empleado> listEmpleados = EmpleadoNEG.Instancia().obtenerListaColaborador(txtTipoBusqueda, txtCriterio);

        dataObject.put("Result", "OK");
        dataObject.put("listEmpleados", listEmpleados);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerPersonal() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String optradio, txtCriterioBusqueda = null;

        optradio = varRequest.getParameter("optradiobus");
        txtCriterioBusqueda = varRequest.getParameter("txtCriterioBusqueda");

        List<Empleado> listaEmpleado = EmpleadoNEG.Instancia().obtenerPersonal(txtCriterioBusqueda, optradio);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaEmpleado", listaEmpleado);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void obtenerUsuarioMarcacion() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String criterio, tipoBusqueda = null;

        criterio = varRequest.getParameter("criterio");
        tipoBusqueda = varRequest.getParameter("tipoBusqueda");

        List<Usuario> listaUsuario = EmpleadoNEG.Instancia().obtenerUsuarioMarcacion(criterio, tipoBusqueda);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaUsuario", listaUsuario);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void vinculacionMarcacion() throws Exception {

        Usuario objUsuario = new Usuario();

        objUsuario.setIdUsuario(varRequest.getParameter("idMarcacion"));
        objUsuario.getObjEmpleado().setIdPersona(varRequest.getParameter("idPersona"));

        boolean result = EmpleadoNEG.Instancia().vinculacionMarcacion(objUsuario);

        data.put("Result", "OK");
        data.put("Message", String.valueOf(result));

        String json = gson.toJson(data);
        varResponse.getWriter().write(json);

    }

    private void listarEmpleados() throws Exception {

        String idOficina = null;
        String idDpto = null;
        idOficina = varRequest.getParameter("idOficina");
        idDpto = varRequest.getParameter("idDpto");

        List<Empleado> listaEmpleados = EmpleadoNEG.Instancia().listarEmpleados(idOficina, idDpto);

        dataObject.put("Result", "OK");
        dataObject.put("listaEmpleados", listaEmpleados);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

}
