/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.logistica;

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
import ll.entidades.contabilidad.UnidadMedida;
import ll.negocio.logistica.GuiaRemisionNEG;

/**
 *
 * @author RenRio
 */
public class sUnidadMedida extends HttpServlet {

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
            out.println("<title>Servlet sUnidadMedida</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sUnidadMedida at " + request.getContextPath() + "</h1>");
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
            if (action.equals("obtenerUnidadMedida")) {
                obtenerUnidadMedida();
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void obtenerUnidadMedida() throws Exception {

        List<UnidadMedida> listarUnidadMedida = GuiaRemisionNEG.Instancia().listarUnidadMedida();

        dataObject.put("Result", "OK");
        dataObject.put("listarUnidadMedida", listarUnidadMedida);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
}
