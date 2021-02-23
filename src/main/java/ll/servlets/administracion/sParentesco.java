package ll.servlets.administracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ll.entidades.administracion.Parentesco;
import ll.negocio.administracion.ParentescoNEG;


public class sParentesco extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sParentesco</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sParentesco at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            varRequest = request;
            varResponse = response;
            String action = request.getParameter("action");
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");

            if(action.equalsIgnoreCase("obtenercboParentesco")){
                obtenerParentesco();
            }else {
                
                dataString.put("Result", "error");
                dataString.put("Message", "El numero de Identificacion ingresado no pertenece a un socio o Socio esta retirado");
                gson = new GsonBuilder().setPrettyPrinting().create();
                json = gson.toJson(dataString);
                response.getWriter().write(json);
            }
        } catch (Exception e) {
           
            dataString.put("Result", "error");
            dataString.put("Message", e.getMessage());
            gson = new GsonBuilder().setPrettyPrinting().create();
            json = gson.toJson(dataString);
            response.getWriter().write(json);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    private void obtenerParentesco() throws Exception {       
        List<Parentesco> lstParentesco = ParentescoNEG.Instancia().obtenerParentesco();

        if (lstParentesco.get(0).getIdParentesco() != null) {      
            dataObject.put("Result", "OK");
            dataObject.put("ListaParentesco", lstParentesco);
            gson = new GsonBuilder().setPrettyPrinting().create();
            json = gson.toJson(dataObject);
            varResponse.getWriter().write(json);
        }
    }
    

}