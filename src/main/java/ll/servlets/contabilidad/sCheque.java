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
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.Cheque;
import ll.negocio.contabilidad.ChequeNEG;

/**
 *
 * @author RenRio
 */
public class sCheque extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sCheque</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sCheque at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("registrarCheque")) {
                registrarCheque();
            }
            if (action.equalsIgnoreCase("anularCheque")) {
                anularCheque();
            }
            if (action.equalsIgnoreCase("listarChequesDelDia")) {
                listarChequesDelDia();
            }
            if (action.equalsIgnoreCase("buscarCheques")) {
                buscarCheques();
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
    private void registrarCheque() throws Exception {
        Cheque objCheque = new Cheque();
        HttpSession ses = varRequest.getSession();
        Usuario objUsuario = (Usuario) ses.getAttribute("user");

        objCheque.getObjPersona().setIdPersona(varRequest.getParameter("idpersona"));
        objCheque.setTipMoneda(varRequest.getParameter("tipomoneda"));
        objCheque.setFechaDoc(varRequest.getParameter("fecha"));
        objCheque.setGlosaFija(varRequest.getParameter("glosa"));
        objCheque.setGlosaVariable(varRequest.getParameter("glosavariable"));

        objCheque.getObjCtaBanco().setIdTipCtaBanco(Integer.parseInt(varRequest.getParameter("idtipctabanco")));
        objCheque.getObjCtaBanco().setCtaBanco(varRequest.getParameter("ctabanco"));
        objCheque.getObjBanco().getObjPersonaBanco().setIdPersona(varRequest.getParameter("idpersonabco"));
        objCheque.setNrocheque(varRequest.getParameter("nrocheque"));
        objCheque.setMonto(Double.parseDouble(varRequest.getParameter("monto")));

        String result = ChequeNEG.Instancia().registrarCheque(objCheque, objUsuario);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("Message", result);
        json = gson.toJson(dataObject);

        varResponse.getWriter().write(json);

    }

    private void anularCheque() throws Exception {
        
        Cheque objCheque = new Cheque();
        objCheque.getObjDocGenerado().getObjOficina().setIdOficina(varRequest.getParameter("idOficina"));
        objCheque.getObjDocGenerado().setIdDoc(varRequest.getParameter("idDoc"));
        objCheque.getObjDocGenerado().getObjTipoMoneda().setId(varRequest.getParameter("tipMoneda"));
        objCheque.getObjDocGenerado().setNroDoc(varRequest.getParameter("nroDoc"));
        String motivo = varRequest.getParameter("motivo");
        
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Boolean respuesta = ChequeNEG.Instancia().anularCheque(objCheque, objUsuario, motivo);

        dataObject.put("resultado", String.valueOf(respuesta));
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void listarChequesDelDia() throws Exception {

        
        List<Cheque> listaCheques = ChequeNEG.Instancia().listarChequesDelDia();

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaCheques", listaCheques);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
    
    private void buscarCheques() throws Exception {
        
        String nroCheque = varRequest.getParameter("nroCheque");
        String fecIni = varRequest.getParameter("fecIni");
        String fecFin = varRequest.getParameter("fecFin");
        
        List<Cheque> listaCheques = ChequeNEG.Instancia().buscarCheques(nroCheque,fecIni,fecFin);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaCheques", listaCheques);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
