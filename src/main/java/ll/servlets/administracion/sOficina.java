/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.administracion;

import ll.entidades.administracion.Oficina;
import ll.negocio.administracion.OficinaNEG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AndZuñ
 */
public class sOficina extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        try {
            if (action.equalsIgnoreCase("listarOficinas")) {
                listarOficinas();
            }
            

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }
        
        
    }
    
     private void listarOficinas() throws Exception {
        List<Oficina> listaOficinas = OficinaNEG.Instancia().listarOficinas();
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaOficinas", listaOficinas);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

}
