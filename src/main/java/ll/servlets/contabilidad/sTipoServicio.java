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
import ll.entidades.contabilidad.TipoServicio;
import ll.negocio.contabilidad.TipoServicioNEG;

public class sTipoServicio extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;
    TipoServicio objTipoServicio = new TipoServicio();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sTipoServicio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sTipoServicio at " + request.getContextPath() + "</h1>");
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

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");

        try {

            if (action.equalsIgnoreCase("registrar")) {
                registrar();
            }if (action.equalsIgnoreCase("listarTipoServicio")) {
                listarTipoServicio();
            }

        } catch (Exception ex) {

            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            varResponse.getWriter().write(json);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void registrar() throws Exception {

        objTipoServicio.setIdTipoServicio(varRequest.getParameter("codigo"));
        objTipoServicio.setDescripcion(varRequest.getParameter("descripcion"));
        objTipoServicio.setEstado(Integer.parseInt(varRequest.getParameter("estado")));
        String decision = varRequest.getParameter("decision");

        String result = TipoServicioNEG.Instancia().registrar(objTipoServicio, decision);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("Message", result);
        json = gson.toJson(dataObject);

        varResponse.getWriter().write(json);

    }
    
    private void listarTipoServicio() throws Exception {
        
        List<TipoServicio> listaTipoServicio= TipoServicioNEG.Instancia().listar();

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaTipoServicio", listaTipoServicio);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

}
