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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Bitacora;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.BitacoraNEG;

/**
 *
 * @author CesGue
 */
public class sBitacora extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    String json;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
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

        processRequest(varRequest, varResponse);
        String action = varRequest.getParameter("action");

        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        
        try {
            if (action.equalsIgnoreCase("registrarBitacora")) {
                registrarBitacora();
            }        

        } catch (Exception ex) {
            dataString.put("Result", "error");
            dataString.put("Message", ex.getMessage());
            json = gson.toJson(dataString);
            varResponse.getWriter().write(json);
        }
    }   
    private void registrarBitacora() throws UnknownHostException, Exception {

        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        Bitacora objBitacora = new Bitacora();

        String computername = InetAddress.getLocalHost().getHostName();
        String nombreFormulario = varRequest.getParameter("nombre");
        String idPersona = varRequest.getParameter("idPersona");
        String motivo = varRequest.getParameter("motivo");
        String registro
                = nombreFormulario + "/" + idPersona + "/" + computername
                + "/IP:" + InetAddress.getLocalHost().getHostAddress()
                + "/" + objUsuario.getIdUsuario() + "/Motivo :" + motivo;
        objBitacora.setRegistro(registro);
        
        dataObject.put("Result", "OK");
        dataObject.put("Respuesta", BitacoraNEG.Instancia().registrar(objBitacora));

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
