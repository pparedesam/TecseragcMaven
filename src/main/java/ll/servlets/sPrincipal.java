/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.EmpleadoNEG;

/**
 *
 * @author PauPar
 */
public class sPrincipal extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sPrincipal</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sPrincipal at " + request.getContextPath() + "</h1>");
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

        String action = varRequest.getParameter("action");
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");

        try {

            if (action.equalsIgnoreCase("cambiarClave")) {
                cambiarClave();
            }

        } catch (Exception e) {

            dataString.put("Result", "error");
            dataString.put("Message", e.getMessage());

            String json = gson.toJson(dataString);
            varResponse.getWriter().write(json);
        }
    }

    private void cambiarClave() throws Exception {
        String claveAnt = varRequest.getParameter("claveactual");
        String claveNew = varRequest.getParameter("clavenueva");
        HttpSession ses = varRequest.getSession();
        Usuario objUsuario = (Usuario) ses.getAttribute("user");
        Boolean result = EmpleadoNEG.Instancia().cambioClave(objUsuario, claveAnt, claveNew);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("resultado", result);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
