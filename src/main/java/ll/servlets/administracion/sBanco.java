/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.administracion;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Bancos;
import ll.entidades.administracion.CtaBanco;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.BancoNEG;
import ll.negocio.administracion.BancosNEG;

/**
 *
 * @author RenRio
 */
public class sBanco extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
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
            out.println("<title>Servlet sBanco</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sBanco at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        try {

            if (action.equalsIgnoreCase("buscarBanco")) {
                buscarBanco();
            }
            if (action.equalsIgnoreCase("registrarBanco")) {
                registrarBanco();
            }
            if (action.equalsIgnoreCase("registrarCtaBanco")) {
                registrarCtaBanco();
            }
            if (action.equalsIgnoreCase("listarCta")) {
                listarCta();
            }
            if (action.equalsIgnoreCase("listarBancos")) {
                listarBancos();
            }
            //funciones nuevas
            if (action.equalsIgnoreCase("listarBancosNEW")) {
                listarBancosNEW();
            }

            if (action.equalsIgnoreCase("obtenerBanco")) {
                obtenerBanco();
            }

            if (action.equalsIgnoreCase("registrarBancoNEW")) {
                registrarBancoNEW();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }
    }

    private void buscarBanco() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String txtCriterioBusqueda = null;
        int optradio;
        optradio = Integer.parseInt(varRequest.getParameter("optradiobcocta"));
        txtCriterioBusqueda = varRequest.getParameter("txtCriterioBusqueda");

        List<Bancos> listaBanco = BancosNEG.Instancia().listarBancosBusqueda(optradio, txtCriterioBusqueda);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaBanco", listaBanco);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void registrarBanco() throws Exception {
        Bancos objBanco = new Bancos();
        objBanco.getObjPersonaBanco().setIdPersona(varRequest.getParameter("txtidPersonaBanco"));
        objBanco.setNombreBanco(varRequest.getParameter("txtBanco"));
        objBanco.setNombreAbv(varRequest.getParameter("txtAbrev"));

        boolean result = BancosNEG.Instancia().registrarBanco(objBanco);

        data = new HashMap<>();
        data.put("Result", "OK");
        data.put("Message", String.valueOf(result));
        String json = gson.toJson(data);

        varResponse.getWriter().write(json);

    }

    private void registrarCtaBanco() throws Exception {
        CtaBanco objCtaBanco = new CtaBanco();
        HttpSession ses = varRequest.getSession();
        Usuario objUsuario = (Usuario) ses.getAttribute("user");
        objCtaBanco.getObjBanco().getObjPersonaBanco().setIdPersona(varRequest.getParameter("txtidPersonaBancocta"));
        objCtaBanco.getObjBanco().setNombreBanco(varRequest.getParameter("txtBancocta"));
        objCtaBanco.setCtaBanco(varRequest.getParameter("txtCta"));
        objCtaBanco.setIdTipCtaBanco(Integer.parseInt(varRequest.getParameter("cboctabco")));
        objCtaBanco.setTipMoneda(varRequest.getParameter("cboTipoMoneda"));
        objCtaBanco.setFechaApertura(varRequest.getParameter("txtFechaApertura"));

        boolean result = BancosNEG.Instancia().registrarCtaBanco(objCtaBanco, objUsuario);

        data = new HashMap<>();
        data.put("Result", "OK");
        data.put("Message", String.valueOf(result));
        String json = gson.toJson(data);

        varResponse.getWriter().write(json);

    }

    private void listarCta() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String txtidpersonabanco = null;

        txtidpersonabanco = varRequest.getParameter("txtidPersonaBancocta");

        List<CtaBanco> listacta = BancosNEG.Instancia().listarCta(txtidpersonabanco);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listacta", listacta);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void listarBancos() throws Exception {

        List<Bancos> listaBancos = BancoNEG.Instancia().listarBancos();

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaBancos", listaBancos);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
//funciones nuevas

    private void listarBancosNEW() throws IOException, Exception {

        List<Bancos> listBancos = BancosNEG.Instancia().listarBancosNEW();

        dataObject.put("Result", "OK");
        dataObject.put("listBancos", listBancos);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void obtenerBanco() throws Exception {

        String idPersona = varRequest.getParameter("idPersona");

        Bancos objBancos = BancosNEG.Instancia().obtenerBanco(idPersona);
        dataObject.put("Result", "OK");
        dataObject.put("Banco", objBancos);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void registrarBancoNEW() throws Exception {

        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        String resultado = "OK";
        String mensaje = "";

        Gson gson1 = new Gson();

        String ctaBancos = varRequest.getParameter("listaCtasBanco");
        java.lang.reflect.Type listctaBancos = new TypeToken<ArrayList<CtaBanco>>() {
        }.getType();
        ArrayList<CtaBanco> listaCtaBanco = gson1.fromJson(ctaBancos, listctaBancos);

        try {
            Bancos objBancos = new Bancos();

            objBancos.getObjPersonaBanco().setIdPersona(varRequest.getParameter("idPersona"));
            objBancos.setNombreBanco(varRequest.getParameter("banco"));
            objBancos.setNombreAbv(varRequest.getParameter("abrev"));
            String regBanco = BancosNEG.Instancia().registrarBancoNEW(objBancos);
            if (!regBanco.equals("0")) {
                if (listaCtaBanco.size() > 0) {
                    for (CtaBanco objCtaBanco : listaCtaBanco) {
                        objCtaBanco.getObjBanco().getObjPersonaBanco().setIdPersona(objBancos.getObjPersonaBanco().getIdPersona());
                        mensaje = BancosNEG.Instancia().registrarCtaBancos(objCtaBanco, objUsuario);
                        if (mensaje.equalsIgnoreCase("1")) {
                            resultado = "OK";
                        } else {
                            resultado = "error";
                            break;
                        }
                    }
                }
            } else {
                resultado = "error";
                mensaje = "Problema.";
            }
            dataObject.put("Result", resultado);
            dataObject.put("Message", mensaje);
            String json = gson.toJson(dataObject);
            varResponse.getWriter().write(json);
            dataObject.clear();
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
