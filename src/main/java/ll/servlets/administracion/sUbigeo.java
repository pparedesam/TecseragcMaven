/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.administracion;

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
import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Distrito;
import ll.entidades.administracion.Pais;
import ll.entidades.administracion.Provincia;
import ll.entidades.administracion.Ubigeo;
import ll.negocio.administracion.UbigeoNEG;

/**
 *
 * @author paupar
 */
public class sUbigeo extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final PrintWriter varOut = null;
    String json;

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
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sUbigeo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sUbigeo at " + request.getContextPath() + "</h1>");
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
        try {

            varRequest = request;
            varResponse = response;
            String action = request.getParameter("action");

            varResponse.setContentType("application/json");
            varResponse.setHeader("Cache-Control", "no-cache");
            if (action.equals("editar")) {
                editar();
            }
            if (action.equals("registrar")) {
                registrar();
            }
            if (action.equalsIgnoreCase("obtenerUbigeo")) {
                obtenerUbigeo();
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void editar() throws Exception {

        Ubigeo objUbigeo = new Ubigeo();

        String LastDescripcion;
        objUbigeo.setCodigo(varRequest.getParameter("codigo"));
        objUbigeo.setDescripcion(varRequest.getParameter("descripcion"));
        objUbigeo.setUbigeo(varRequest.getParameter("ubigeo"));
        objUbigeo.setNivel(varRequest.getParameter("nivel"));
        LastDescripcion = (varRequest.getParameter("LastUbigeo"));

        String valor = UbigeoNEG.Instancia().editarUbigeo(objUbigeo, LastDescripcion);

        if (valor.equals("up_ok")) {
            data.put("Result", "ok");
            data.put("Message", "Actualizacion Exitoso");
        } else {
            data.put("Result", "error");
            data.put("Message", valor);
        }
        String json = gson.toJson(data);
        varResponse.getWriter().write(json);

    }

    private void registrar() throws Exception {

        Ubigeo objUbigeo = new Ubigeo();

        objUbigeo.setCodigo(varRequest.getParameter("txtCodigo"));
        objUbigeo.setDescripcion(varRequest.getParameter("txtDescripcion"));
        objUbigeo.setNivel(varRequest.getParameter("txtNivel"));
        objUbigeo.setUbigeo(varRequest.getParameter("txtUbigeo"));
        String accion = (varRequest.getParameter("txtAccion"));

        String valor = UbigeoNEG.Instancia().registrarUbigeo(objUbigeo, accion);
        if (valor.equals("in_ok")) {
            data.put("Result", "ok");
            data.put("Message", "Registro Exitoso");
        } else {
            data.put("Result", "error");
            data.put("Message", valor);
        }

        String json = gson.toJson(data);
        varResponse.getWriter().write(json);

    }

    private void obtenerUbigeo() throws Exception {

        List<Pais> listarPaisesRO = UbigeoNEG.Instancia().listarPaisesGR();
        List<Departamento> listDepartamentos = UbigeoNEG.Instancia().listarDepartamento();
        List<Provincia> listProvincia = UbigeoNEG.Instancia().listarPronvincias();
        List<Distrito> listDistrito = UbigeoNEG.Instancia().listarDistritos();

        List<List> listaUbigeo = new ArrayList();
        listaUbigeo.add(listarPaisesRO);
        listaUbigeo.add(listDepartamentos);
        listaUbigeo.add(listProvincia);
        listaUbigeo.add(listDistrito);
        dataObject.put("Result", "OK");
        dataObject.put("listaUbigeo", listaUbigeo);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
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

}
