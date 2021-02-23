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
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.IntegrantePlanillaConvenio;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.IntegrantePlanillaConvenioNEG;

/**
 *
 * @author CesGue
 */
public class sIntegrantePlanillaConvenio extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sIntegrantePlanillaConvenio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sIntegrantePlanillaConvenio at " + request.getContextPath() + "</h1>");
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
    
    // <editor-fold defaultstate="collapsed" desc="Ejemplo">
      // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodo Post.">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        
         try {

            if (action.equalsIgnoreCase("obtenerIntegrantePlanilla")) {

                ObtenerApoderadoPlanilla();

            }
            if (action.equalsIgnoreCase("RegistroIntegrante")) {

                registrarIntegrante();

            }
            if (action.equalsIgnoreCase("eliminarIntegrante")) {

                eliminarIntegrante();

            }
           

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }
       
    }
    //</editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="Obtener Integrantes.">
    private void ObtenerApoderadoPlanilla() throws Exception{
        String Convenio = varRequest.getParameter("idConvenio");
        String IdApoderado = varRequest.getParameter("idApoderado");
       
        
        List<IntegrantePlanillaConvenio> listaIntegrantePlanilla = IntegrantePlanillaConvenioNEG.Instancia().obtenerIntegrantePlanillaConvenio(Convenio, IdApoderado);      
       
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("listIntegrantesPlanilla", listaIntegrantePlanilla);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Registrar Modificar Integrantes.">
    private void registrarIntegrante() throws Exception{
        
        IntegrantePlanillaConvenio objIntegrantePlanillaConvenio = new  IntegrantePlanillaConvenio();
       
        
        objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().setIdPersona(varRequest.getParameter("IdPersona"));
        objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().setIdPersonaApoderado(varRequest.getParameter("idApoderado"));
        objIntegrantePlanillaConvenio.getObjCrediticio().setIdTipProducto(varRequest.getParameter("idConvenio"));
        objIntegrantePlanillaConvenio.setAporte(varRequest.getParameter("Aporte"));
        objIntegrantePlanillaConvenio.setFondoMortuorio(varRequest.getParameter("FondoM"));
        objIntegrantePlanillaConvenio.setPrestamo(varRequest.getParameter("Prestamo"));
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        
        String registro = IntegrantePlanillaConvenioNEG.Instancia().registrarApoderadoPlanilla(objIntegrantePlanillaConvenio, objUsuario,varRequest.getParameter("accion"));
        
        switch (registro) {
            case "ins_ok":
                data.put("Result", "OK");
                data.put("Message",registro);
                break;
            case "upd_ok":
                data.put("Result", "OK");
                data.put("Message", registro);
                break;
            default:
                data.put("Result", "error");
                data.put("Message", registro);
                break;
        }
        String json = gson.toJson(data);           
        varResponse.getWriter().write(json);
    }
       //</editor-fold>
    
    
    private void eliminarIntegrante() throws Exception{
      
      IntegrantePlanillaConvenio objIntegrantePlanillaConvenio = new  IntegrantePlanillaConvenio();
       
        
        objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().setIdPersona(varRequest.getParameter("idIntegrante"));
        objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().setIdPersonaApoderado(varRequest.getParameter("idApoderado"));
        objIntegrantePlanillaConvenio.getObjCrediticio().setIdTipProducto(varRequest.getParameter("idConvenio"));
 
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        
        String registro = IntegrantePlanillaConvenioNEG.Instancia().eliminarApoderado(objIntegrantePlanillaConvenio, objUsuario,varRequest.getParameter("accion"));
        
        if (registro.equals("del_ok")) {
            data.put("Result", "OK");
            data.put("Message", "Se Elimino Correctamente");
        }
         String json = gson.toJson(data);
            varResponse.getWriter().write(json);
    
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
