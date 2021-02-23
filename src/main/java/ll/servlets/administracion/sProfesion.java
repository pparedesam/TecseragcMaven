package ll.servlets.administracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import java.util.Map;
import ll.entidades.administracion.Profesion;
import ll.negocio.administracion.ProfesionNEG;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class sProfesion extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    String json;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sProfesiones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sProfesiones at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        varRequest = request;
        varResponse = response;
       
        String action = varRequest.getParameter("action");

        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        
        try {           
           
            if (action.equalsIgnoreCase("registrar")) {        
               registrarProfesion();
            }                     
        } catch (Exception ex) {
            
            dataString.put("Result","error");    
            dataString.put("Message",ex.getMessage());        
            json = gson.toJson(dataString);
            varResponse.getWriter().write(json);
        }

    }

    private void registrarProfesion() throws Exception {

        Profesion objProfesion = new Profesion();
        objProfesion.setIdProfesion((varRequest.getParameter("idProfesion")));
        objProfesion.setProfesion(varRequest.getParameter("descripcion"));
        objProfesion.setEstado(varRequest.getParameter("estado"));
        String result = ProfesionNEG.Instancia().registrar(objProfesion,varRequest.getParameter("accion"));
       
        if(result.length()==4){
            dataString.put("Result","OK");           
        }else {
            dataString.put("Result","error");           
        }
        
        dataString.put("Message",result);        
        
        json = gson.toJson(dataString);
        varResponse.getWriter().write(json);
    }
   

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
