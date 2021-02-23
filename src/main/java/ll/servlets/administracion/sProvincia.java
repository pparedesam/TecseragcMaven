/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.administracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ll.entidades.administracion.Provincia;
import ll.negocio.administracion.UbigeoNEG;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sProvincia extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sProvincia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sProvincia at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        varRequest = request;
        varResponse = response;

        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        String action = varRequest.getParameter("action");

        if (action.equalsIgnoreCase("obtenerddlProvincia")) {
            try {
                obtenerddlProvincia();
            } catch (Exception ex) {
                dataString.put("Result", "error");
                dataString.put("Message", ex.getMessage());

                String json = gson.toJson(dataString);
                varResponse.getWriter().write(json);
            }
        }
    }

    private void obtenerddlProvincia() throws Exception {

        List<Provincia> lstProvincia = new ArrayList<>();
        lstProvincia = UbigeoNEG.Instancia().lstProvincia(varRequest.getParameter("idPais"), varRequest.getParameter("idDepartamento"));
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("Provincias", lstProvincia);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
