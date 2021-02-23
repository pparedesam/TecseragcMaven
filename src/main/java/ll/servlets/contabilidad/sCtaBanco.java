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
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.CtaBanco;
import ll.negocio.administracion.BancosNEG;

/**
 *
 * @author RenRio
 */
public class sCtaBanco extends HttpServlet {

    Map<String, Object> data = new HashMap<String, Object>();
    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;

    Map<String, String> dataString = new HashMap<>();
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
            out.println("<title>Servlet sCtaBanco</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sCtaBanco at " + request.getContextPath() + "</h1>");
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

        try {

            String action = request.getParameter("action");
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");

            if (action.equalsIgnoreCase("obtenerCtaBanco")) {
                obtenerCtaBanco();
            }
            if (action.equalsIgnoreCase("obtenerCuentasBanco")) {
                obtenerCuentasBanco();
            }

        } catch (Exception e) {
            Map<String, String> data = new HashMap<>();
            data.put("Result", "error");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(data);
            response.getWriter().write(json);
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

    private void obtenerCtaBanco() throws Exception {
        String cboBanco = varRequest.getParameter("cboBanco");
        String cboTipMoneda = varRequest.getParameter("cboTipoMoneda");
        dataObject.put("Result", "OK");
        dataObject.put("listaCtaBanco", BancosNEG.Instancia().listarCtaBancos(cboBanco, cboTipMoneda));
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerCuentasBanco() throws Exception {

        String idPersona = varRequest.getParameter("idPersona");

        List<CtaBanco> listCtaBanco = BancosNEG.Instancia().listaCuentaBancos(idPersona);
        dataObject.put("Result", "OK");
        dataObject.put("listCtaBanco", listCtaBanco);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
}
