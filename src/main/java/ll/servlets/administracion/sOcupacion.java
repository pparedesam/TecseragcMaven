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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ll.entidades.administracion.Ocupacion;

import ll.negocio.administracion.OcupacionNEG;

/**
 *
 * @author paupar
 */
public class sOcupacion extends HttpServlet {

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
            out.println("<title>Servlet sOcupacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sOcupacion at " + request.getContextPath() + "</h1>");
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
            String action = request.getParameter("action");
            Ocupacion objOcupacion = new Ocupacion();
            if (action.equalsIgnoreCase("registrar")) {

                objOcupacion.setCodigo((request.getParameter("codigo")));
                objOcupacion.setDescripcion(request.getParameter("descripcion"));
                objOcupacion.setEstado(request.getParameter("estado"));
                String result = OcupacionNEG.Instancia().registrar(objOcupacion);

                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-cache");
                Map<String, String> data = new HashMap<String, String>();
                data.put("codigo", String.valueOf(result));
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(data);
                response.getWriter().write(json);
            }
            if (action.equalsIgnoreCase("delete")) {
                objOcupacion.setCodigo(request.getParameter("codigo"));

                Boolean result = OcupacionNEG.Instancia().delete(objOcupacion.getCodigo());
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-cache");
                Map<String, String> data = new HashMap<String, String>();
                data.put("codigo", String.valueOf(result));
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(data);
                response.getWriter().write(json);
            }

        } catch (Exception ex) {
            Logger.getLogger(sOcupacion.class.getName()).log(Level.SEVERE, null, ex);
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

}
