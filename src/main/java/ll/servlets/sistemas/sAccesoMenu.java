/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.sistemas;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;
import ll.entidades.agentes.Menu;
import ll.negocio.administracion.AreaNEG;
import ll.negocio.administracion.OficinaNEG;
import ll.negocio.administracion.PuestoNEG;
import ll.negocio.agente.MenuNEG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RenRio
 */
@WebServlet(name = "sAccesoMenu", urlPatterns = {"/sAccesoMenu"})
public class sAccesoMenu extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
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
            out.println("<title>Servlet sAccesoMenu</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sAccesoMenu at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("obtenerOficina")) {
                obtenerOficina();
            }
            if (action.equalsIgnoreCase("obtenerDptoxOficina")) {
                obtenerDptoxOficina();
            }
            if (action.equalsIgnoreCase("obtenerPstoxDptoxOficina")) {
                obtenerPstoxDptoxOficina();
            }
            if (action.equalsIgnoreCase("obtenerListaMenu")) {
                obtenerListaMenu();
            }
            if (action.equalsIgnoreCase("cambiarestadoMenu")) {
                cambiarestadoMenu();
            }
            if (action.equalsIgnoreCase("actualizarMenu")) {
                actualizarMenu();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            json = gson.toJson(data);
            response.getWriter().write(json);
        }
    }

    private void obtenerOficina() throws Exception {

        List<Oficina> listOficina = OficinaNEG.Instancia().listarOficinas();
        dataObject.put("Result", "OK");
        dataObject.put("listOficina", listOficina);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerDptoxOficina() throws Exception {
        String idoficina = null;
        idoficina = varRequest.getParameter("idOficina");

        List<Area> listArea = AreaNEG.Instancia().obtenerDptoxOficina(idoficina);
        dataObject.put("Result", "OK");
        dataObject.put("listArea", listArea);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerPstoxDptoxOficina() throws Exception {
        String idOficina = null;
        String idDpto = null;
        idOficina = varRequest.getParameter("idOficina");
        idDpto = varRequest.getParameter("idDpto");

        List<Puesto> listPuesto = PuestoNEG.Instancia().obtenerPstoxDptoxOficina(idOficina, idDpto);
        dataObject.put("Result", "OK");
        dataObject.put("listPuesto", listPuesto);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerListaMenu() throws Exception {
        String idOficina = null;
        String idDpto = null;
        String idPsto = null;
        idOficina = varRequest.getParameter("idOficina");
        idDpto = varRequest.getParameter("idDpto");
        idPsto = varRequest.getParameter("idPsto");
        HttpSession ses = varRequest.getSession();
        ses.removeAttribute("listaMenu");
        List<Menu> listMenu = MenuNEG.Instancia().obtenerListaMenu(idOficina, idDpto, idPsto);
        ArrayList<HashMap<String, String>> listaMenu = new ArrayList<>();
        for (int i = 0; i < listMenu.size(); i++) {
            HashMap<String, String> lista = new HashMap<>();
            lista.put("idOficina", idOficina);
            lista.put("idDpto", idDpto);
            lista.put("idPsto", idPsto);
            lista.put("idMenu", String.valueOf(listMenu.get(i).getCodigo()));
            lista.put("nivel", String.valueOf(listMenu.get(i).getNivel()));
            lista.put("descripcion", listMenu.get(i).getDescripcion());
            lista.put("dependiente", String.valueOf(listMenu.get(i).getDependiente()));
            lista.put("descripcionN2", listMenu.get(i).getDescripcionN2());
            lista.put("dependiente2", String.valueOf(listMenu.get(i).getDependiente2()));
            lista.put("descripcionN1", listMenu.get(i).getDescripcionN1());
            lista.put("estado", String.valueOf(listMenu.get(i).getEstado()));
            listaMenu.add(lista);
        }
        //List<Menu> listMenu = MenuNEG.Instancia().obtenerListaMenu(idOficina, idDpto, idPsto);
        ses.setAttribute("listaMenu", listaMenu);

        dataObject.put("Result", "OK");
        dataObject.put("listaMenu", listaMenu);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void cambiarestadoMenu() throws IOException {

        HttpSession ses = varRequest.getSession();
        String idMenu = varRequest.getParameter("idMenu");
        String dependiente = varRequest.getParameter("dependiente");
        String dependiente2 = varRequest.getParameter("dependiente2");
        String estado = varRequest.getParameter("estado");

        ArrayList<HashMap<String, String>> listaMenu = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaMenu");
        ArrayList<HashMap<String, String>> nuevalistaMenu = new ArrayList<>();
        HashMap<String, String> nvalista = new HashMap<>();

        if (estado.equals("0")) {
            estado = "1";

        } else {
            estado = "0";
        }

        for (int i = 0; i < listaMenu.size(); i++) {
            if (idMenu.compareTo(listaMenu.get(i).get("idMenu")) != 0) {

                nuevalistaMenu.add(listaMenu.get(i));

            } else {

                nvalista.put("idOficina", listaMenu.get(i).get("idOficina"));
                nvalista.put("idDpto", listaMenu.get(i).get("idDpto"));
                nvalista.put("idPsto", listaMenu.get(i).get("idPsto"));
                nvalista.put("idMenu", listaMenu.get(i).get("idMenu"));
                nvalista.put("nivel", listaMenu.get(i).get("nivel"));
                nvalista.put("descripcion", listaMenu.get(i).get("descripcion"));
                nvalista.put("dependiente", listaMenu.get(i).get("dependiente"));
                nvalista.put("descripcionN2", listaMenu.get(i).get("descripcionN2"));
                nvalista.put("dependiente2", listaMenu.get(i).get("dependiente2"));
                nvalista.put("descripcionN1", listaMenu.get(i).get("descripcionN1"));
                if (listaMenu.get(i).get("estado").equals("0")) {
                    nvalista.put("estado", "1");

                } else {
                    nvalista.put("estado", "0");
                }
                nuevalistaMenu.add(nvalista);
            }
        }

        ArrayList<HashMap<String, String>> listafinalMenux = new ArrayList<>();

        for (int i = 0; i < nuevalistaMenu.size(); i++) {
            HashMap<String, String> listaFinalx = new HashMap<>();
            if (idMenu.compareTo(nuevalistaMenu.get(i).get("dependiente")) != 0) {

                listafinalMenux.add(nuevalistaMenu.get(i));

            } else {

                listaFinalx.put("idOficina", nuevalistaMenu.get(i).get("idOficina"));
                listaFinalx.put("idDpto", nuevalistaMenu.get(i).get("idDpto"));
                listaFinalx.put("idPsto", nuevalistaMenu.get(i).get("idPsto"));
                listaFinalx.put("idMenu", nuevalistaMenu.get(i).get("idMenu"));
                listaFinalx.put("nivel", nuevalistaMenu.get(i).get("nivel"));
                listaFinalx.put("descripcion", nuevalistaMenu.get(i).get("descripcion"));
                listaFinalx.put("descripcionN2", nuevalistaMenu.get(i).get("descripcionN2"));
                listaFinalx.put("dependiente", nuevalistaMenu.get(i).get("dependiente"));
                listaFinalx.put("dependiente2", nuevalistaMenu.get(i).get("dependiente2"));
                listaFinalx.put("descripcionN1", nuevalistaMenu.get(i).get("descripcionN1"));
                listaFinalx.put("estado", estado);
                listafinalMenux.add(listaFinalx);
            }
        }

        ArrayList<HashMap<String, String>> listafinalMenu = new ArrayList<>();

        for (int i = 0; i < listafinalMenux.size(); i++) {
            HashMap<String, String> listaFinal = new HashMap<>();
            if (idMenu.compareTo(listafinalMenux.get(i).get("dependiente2")) != 0) {

                listafinalMenu.add(listafinalMenux.get(i));

            } else {

                listaFinal.put("idOficina", listafinalMenux.get(i).get("idOficina"));
                listaFinal.put("idDpto", listafinalMenux.get(i).get("idDpto"));
                listaFinal.put("idPsto", listafinalMenux.get(i).get("idPsto"));
                listaFinal.put("idMenu", listafinalMenux.get(i).get("idMenu"));
                listaFinal.put("nivel", listafinalMenux.get(i).get("nivel"));
                listaFinal.put("descripcion", listafinalMenux.get(i).get("descripcion"));
                listaFinal.put("descripcionN2", listafinalMenux.get(i).get("descripcionN2"));
                listaFinal.put("dependiente", listafinalMenux.get(i).get("dependiente"));
                listaFinal.put("dependiente2", listafinalMenux.get(i).get("dependiente2"));
                listaFinal.put("descripcionN1", listafinalMenux.get(i).get("descripcionN1"));
                listaFinal.put("estado", estado);
                listafinalMenu.add(listaFinal);
            }
        }

        ses.setAttribute("listaMenu", listafinalMenu);

        Map<String, Object> dataObject = new HashMap<>();
        String json;
        dataObject = new HashMap<>();

        dataObject.put(
                "Result", "OK");
        dataObject.put(
                "listaMenu", listafinalMenu);
        json = gson.toJson(dataObject);

        varResponse.getWriter()
                .write(json);
    }

    private void actualizarMenu() throws Exception {

        HttpSession ses = varRequest.getSession();
        ArrayList<HashMap<String, String>> listaMenu = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaMenu");

        ArrayList<HashMap<String, String>> nuevalistaMenu = new ArrayList<>();

        for (int i = 0; i < listaMenu.size(); i++) {

            HashMap<String, String> nvalista = new HashMap<>();
            nvalista.put("idOficina", listaMenu.get(i).get("idOficina"));
            nvalista.put("idDpto", listaMenu.get(i).get("idDpto"));
            nvalista.put("idPsto", listaMenu.get(i).get("idPsto"));
            nvalista.put("idMenu", listaMenu.get(i).get("idMenu"));
            nvalista.put("nivel", listaMenu.get(i).get("nivel"));
            nvalista.put("descripcion", listaMenu.get(i).get("descripcion"));
            nvalista.put("dependiente", listaMenu.get(i).get("dependiente"));
            nvalista.put("descripcionN2", listaMenu.get(i).get("descripcionN2"));
            nvalista.put("dependiente2", listaMenu.get(i).get("dependiente2"));
            nvalista.put("descripcionN1", listaMenu.get(i).get("descripcionN1"));
            nvalista.put("estado", listaMenu.get(i).get("estado"));
            nuevalistaMenu.add(nvalista);

        }

        String result = MenuNEG.Instancia().actualizarMenu(nuevalistaMenu);

        Map<String, Object> dataObject = new HashMap<>();
        String json;
        dataObject = new HashMap<>();

        dataObject.put(
                "Result", "OK");
        dataObject.put("Message", result);

        json = gson.toJson(dataObject);

        varResponse.getWriter()
                .write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
