/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.contabilidad;

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
import ll.entidades.contabilidad.TipoComprobantePago;
import ll.negocio.contabilidad.TipoComprobantePagoNEG;

/**
 *
 * @author AndZuñ
 */
public class sTipoComprobantePago extends HttpServlet {
    
     private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sTipoComprobantePago</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sTipoComprobantePago at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("listarComprobanteCompra")) {
                listarComprobanteCompra();
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

    
    private void listarComprobanteCompra() throws Exception {

        
        List<TipoComprobantePago> listaComprobanteCompra = TipoComprobantePagoNEG.Instancia().listarComprobanteCompra();

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaComprobanteCompra", listaComprobanteCompra);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
}
