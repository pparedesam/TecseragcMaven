/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.agente;

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
import ll.entidades.agentes.AccesoOpexUsuario;
import ll.negocio.agente.AccesoOpexUsuarioNEG;

/**
 *
 * @author CesGue
 */
public class sAccesoOpexUsuario extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;

    Map<String, Object> dataObject = new HashMap<>();
    String json;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sAccesoOpexUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sAccesoOpexUsuario at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);
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

        String action = varRequest.getParameter("action");

        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");

        try {
            if (action.equalsIgnoreCase("obtenerAccesoOpexUsuarioRptMvtoCtas")) {
                obtenerAccesoOpexUsuarioRptMvtoCtas();
            }

        } catch (Exception ex) {
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
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

     private void obtenerAccesoOpexUsuarioRptMvtoCtas() throws IOException {

        String[][] lista = {
        {"17570", "0"}};
        HttpSession sesion = varRequest.getSession();
        List<AccesoOpexUsuario> listaAcceso = (List<AccesoOpexUsuario>) sesion.getAttribute("AccesoOpexUsuario");
        lista = AccesoOpexUsuarioNEG.Instancia().tieneAcceso(listaAcceso, lista);
        dataObject.put("Result", "OK");
        dataObject.put("listaAcceso", lista);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

}
