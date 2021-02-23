/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import ll.entidades.administracion.Distrito;
import ll.entidades.administracion.Provincia;
import ll.negocio.administracion.UbigeoNEG;

/**
 *
 * @author paupar
 */
public class sDistrito extends HttpServlet {

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
            out.println("<title>Servlet sDistrito</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sDistrito at " + request.getContextPath() + "</h1>");
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
          response.setContentType("application/json");
         response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        List<Distrito> lstDistrito = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectDistrito = new JSONObject();
        try {
            lstDistrito = UbigeoNEG.Instancia().lstDistrito(request.getParameter("idPais"), request.getParameter("idDepartamento"), request.getParameter("idProvincia"));

            try {
                for (int i = 0; i < lstDistrito.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("codigo", lstDistrito.get(i).getCodigo());
                    jsonObject.accumulate("descripcion", lstDistrito.get(i).getDescripcion());

                    jsonArray.put(jsonObject);

                }
                jsonObjectDistrito.put("Distritos", jsonArray);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception ex) {
            Logger.getLogger(sPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print(jsonObjectDistrito.toString());
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
        processRequest(request, response);
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
