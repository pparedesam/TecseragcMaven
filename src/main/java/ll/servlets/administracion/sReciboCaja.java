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
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Usuario;

import ll.entidades.operaciones.DocumentoGenerado;
import ll.negocio.administracion.ReciboCajaNEG;

/**
 *
 * @author AndZuñ
 */
public class sReciboCaja extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, String> dataString = new HashMap<>();
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
            out.println("<title>Servlet sReciboCaja</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sReciboCaja at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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

            if (action.equalsIgnoreCase("registrarReciboCaja")) {
                registrarReciboCaja();
            } else if (action.equalsIgnoreCase("buscarRecibos")) {
                buscarRecibos();
            } else if (action.equalsIgnoreCase("anularRecibo")) {
                anularRecibo();
            } else if (action.equalsIgnoreCase("obtenerSaldoCaja")) {
                obtenerSaldoCaja();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
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
    }

    private void registrarReciboCaja() throws Exception {
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        DocumentoGenerado objDocGenerado = new DocumentoGenerado();

        objDocGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersona"));
        objDocGenerado.getObjTipoMoneda().setId("1");
        objDocGenerado.getObjOficina().setIdOficina("01");
        Float monto = Float.parseFloat(varRequest.getParameter("monto"));

        objDocGenerado.setGlosaFija("ReciboCaja");
        objDocGenerado.setGlosaVariable(varRequest.getParameter("glosa"));
        objDocGenerado.getObjOperacion().setIdOperacion(varRequest.getParameter("idOpe"));
        objDocGenerado.setIdDoc("0013");
        objDocGenerado.setFechaDocumento(varRequest.getParameter("fecha"));

        String tmpFirmaVB = varRequest.getParameter("firmaVB");
        String tmpFirmaRC = varRequest.getParameter("firmaRC");

        String[] firmaVB = tmpFirmaVB.split(",");
        String[] firmaRC = tmpFirmaRC.split(",");

        String result = ReciboCajaNEG.Instancia().registrarReciboCaja(objDocGenerado, monto, firmaVB[1], firmaRC[1], objUsuario);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("nroDoc", result);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void buscarRecibos() throws Exception {

        String nroDoc = varRequest.getParameter("nroDoc");
        String fecIni = varRequest.getParameter("fecIni");
        String fecFin = varRequest.getParameter("fecFin");

        List<DocumentoGenerado> listaDocumentosGenerado = new ArrayList<>();

        listaDocumentosGenerado = ReciboCajaNEG.Instancia().buscarRecibosCaja(nroDoc, fecIni, fecFin);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaDocumentosGenerado", listaDocumentosGenerado);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void anularRecibo() throws Exception {

        DocumentoGenerado objDocGenerado = new DocumentoGenerado();
        objDocGenerado.getObjOficina().setIdOficina(varRequest.getParameter("idOficina"));
        objDocGenerado.setIdDoc(varRequest.getParameter("idDoc"));
        objDocGenerado.getObjTipoMoneda().setId(varRequest.getParameter("tipMoneda"));
        objDocGenerado.setNroDoc(varRequest.getParameter("nroDoc"));
        String motivo = varRequest.getParameter("motivo");

        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Boolean respuesta = ReciboCajaNEG.Instancia().anularRecibo(objDocGenerado, objUsuario, motivo);

        dataObject.put("resultado", String.valueOf(respuesta));
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerSaldoCaja() throws Exception {

        double saldoCaja = ReciboCajaNEG.Instancia().obtenerSaldoCaja();

        dataObject.put("Result", "OK");
        dataObject.put("saldoCaja", saldoCaja);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

}
