package ll.servlets.administracion;

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
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Usuario;

import ll.entidades.agentes.Menu;
import ll.negocio.administracion.EmpleadoNEG;
import ll.negocio.agente.MenuNEG;

public class sUsuario extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/html;charset=UTF-8");

    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
            if (action.equalsIgnoreCase("iniciarSesion")) {
                iniciarSesion();
            }
            if (action.equalsIgnoreCase("logout")) {
                logout();
            }

         } catch (Exception e) {

            dataString.put("Result", "error");
            dataString.put("Message", e.getMessage());

            String json = gson.toJson(dataString);
            varResponse.getWriter().write(json);
        }
    }

    private void logout() throws IOException {
        HttpSession ses = varRequest.getSession();
        HttpSession ses1 = varRequest.getSession();
        ses.invalidate();
        ses1.invalidate();
        varResponse.sendRedirect("../frmLogin.jsp");
    }

    private void iniciarSesion() throws Exception {

        String _idusuario = varRequest.getParameter("usuario");
        String _clave = varRequest.getParameter("clave");
        List<Menu> lstMenu = null;
        Usuario objUsuario = EmpleadoNEG.Instancia().verficarAcceso(_idusuario, _clave);

        if (objUsuario.getIdUsuario() == null) {
            dataString.put("Result", "error");
            dataString.put("message", "Usuario o Contraseña Incorrecta");
            String json = gson.toJson(dataString);
            varResponse.getWriter().write(json);

        } else {

            lstMenu = MenuNEG.Instancia().obtenerMenu(objUsuario);
            HttpSession ses = varRequest.getSession();

            ses.setAttribute("user", objUsuario);
            HttpSession ses1 = varRequest.getSession();
            ses1.setAttribute("menu", lstMenu);

            dataString.put("Result", "ok");
            dataString.put("message", "frmPrincipal.jsp");
            String json = gson.toJson(dataString);
            varResponse.getWriter().write(json);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
