/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.logistica;

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
import ll.accesodatos.agente.ParametroDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;
import ll.entidades.logistica.GuiaDetalle;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.exc.ExcepcionPropia;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import ll.negocio.logistica.GuiaRemisionManualNEG;

/**
 *
 * @author RenRio
 */
public class sGuiaRemisionManual extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

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
            out.println("<title>Servlet sGuiaRemision</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sGuiaRemision at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("comprobarDetalleGuia")) {
                comprobarDetalleGuia();
            }
            if (action.equalsIgnoreCase("quitarDetalleGuia")) {
                quitarDetalleGuia();
            }
            if (action.equalsIgnoreCase("limpiarSession")) {
                limpiarSession();
            }
            if (action.equalsIgnoreCase("registrarGuiaRemision")) {
                registrarGuia();
            }

            if (action.equalsIgnoreCase("buscarGuiaRemision")) {
                buscarGuiaRemision();
            }
        } catch (Exception ex) {

            DocumentoCompraVentaNEG.Instancia().deleteCatchGM(dataObject);
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            response.getWriter().write(json);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void comprobarDetalleGuia() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoGuiaRemision");

        if (detalleFactura == null || detalleFactura.isEmpty()) {
            detalleFactura = new ArrayList<>();
        }
        lista.put("uniMedida", varRequest.getParameter("UM"));
        lista.put("descripcion", varRequest.getParameter("descripcion"));
        lista.put("cantidad", varRequest.getParameter("cantidad"));

        detalleFactura.add(lista);

        ses.setAttribute("detalladoGuiaRemision", detalleFactura);
        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", detalleFactura);

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void quitarDetalleGuia() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String descripcion = varRequest.getParameter("descripcion");

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoGuiaRemision");
        ArrayList<HashMap<String, String>> nuevaDetalleVenta = new ArrayList<>();

        int band = 0;
        for (int i = 0; i < detalleFactura.size(); i++) {

            if (descripcion.compareTo(detalleFactura.get(i).get("descripcion")) != 0) {
                nuevaDetalleVenta.add(detalleFactura.get(i));

            }
            band = 1;

        }
        if (band == 1 && nuevaDetalleVenta.size() > 0) {

            lista = nuevaDetalleVenta.get(0);
            nuevaDetalleVenta.remove(0);
            nuevaDetalleVenta.add(lista);
        }

        ses.setAttribute("detalladoGuiaRemision", nuevaDetalleVenta);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", nuevaDetalleVenta);

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void limpiarSession() throws IOException {
        HttpSession ses = varRequest.getSession(true);
        ses.removeAttribute("detalladoGuiaRemision");
    }

    private void registrarGuia() throws Exception {
        String Pais, Dept, Prov, Dist;

        HttpSession sesion = varRequest.getSession();

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fecha"));
        objDocumentoGenerado.getObjTipoMoneda().setId("0");
        objDocumentoGenerado.setIdDoc("0012");
        objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("observacion"));
        objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaDestino"));

        GuiaRemision objGuiaRemision = new GuiaRemision();

        objGuiaRemision.setDireccionLlegada(varRequest.getParameter("llegada"));
        objGuiaRemision.setDireccionPartida(varRequest.getParameter("partida"));
        objGuiaRemision.setFechaEntrega(varRequest.getParameter("fechaEntrega"));
        objGuiaRemision.setFechaGuia(varRequest.getParameter("fecha"));
        objGuiaRemision.setFechaIniTraslado(varRequest.getParameter("fechaTraslado"));
        objGuiaRemision.setIdTipoGuia("09");
        objGuiaRemision.setNroPlacaTransportista(varRequest.getParameter("placa"));

        objGuiaRemision.getObjModalidadTraslado().setCodigo(varRequest.getParameter("idModalidadTraslado"));
        objGuiaRemision.getObjMotivosTraslado().setCodigo(varRequest.getParameter("idMotivoTraslado"));
        objGuiaRemision.getObjPersonaConductor().setIdPersona(varRequest.getParameter("idPersonaConductor"));
        objGuiaRemision.getObjPersonaDestinatario().setIdPersona(varRequest.getParameter("idPersonaDestino"));
        objGuiaRemision.getObjPersonaRemitente().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
        objGuiaRemision.getObjPersonaTransportista().setIdPersona(varRequest.getParameter("idPersonaEmpresaTransporte"));
        objGuiaRemision.setPesoBruto(Double.parseDouble(varRequest.getParameter("Pbruto")));
        objGuiaRemision.getObjUnidadMedida().setCodigoMedida(varRequest.getParameter("Umedida"));

        Pais = varRequest.getParameter("idPaisLlegada");
        Dept = varRequest.getParameter("idDepartamentoLlegada");
        Prov = varRequest.getParameter("idProvinciasLlegada");
        Dist = varRequest.getParameter("idDistritosLlegada");
        objGuiaRemision.getObjUbigeoLlegada().comprobarUbigeo(Pais, Dept, Prov, Dist);
        Pais = varRequest.getParameter("idPaisPartida");
        Dept = varRequest.getParameter("idDepartamentoPartida");
        Prov = varRequest.getParameter("idProvinciasPartida");
        Dist = varRequest.getParameter("idDistritosPartida");
        objGuiaRemision.getObjUbigeoPartida().comprobarUbigeo(Pais, Dept, Prov, Dist);
        objGuiaRemision.setNroGuia("PRUEBA12345678");

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Gson gson1 = new Gson();
        String detalle = varRequest.getParameter("listaDetalle");
        java.lang.reflect.Type listaDetalle = new TypeToken<ArrayList<GuiaDetalle>>() {
        }.getType();
        ArrayList<GuiaDetalle> detalleGM = gson1.fromJson(detalle, listaDetalle);

        HashMap<String, String> result = new HashMap<>();

        result = GuiaRemisionManualNEG.Instancia().registrarGuiaRemision(objDocumentoGenerado, objGuiaRemision, objUsuario, detalleGM);

        Boolean band = ParametroDAO.Instancia().actualizarParametroGuiaRemisionManual(objDocumentoGenerado.getNroDoc(), objUsuario.getObjEmpleado().getObjOficina().getIdOficina(), objDocumentoGenerado.getObjTipoMoneda().getId());

        if (band == false) {

            throw new ExcepcionPropia("ERROR PARAMETRO");
        } else {
            //objNotaCreditoxml = ReadXML.getSignNC("A:\\DocumentosElectronicos\\CreditNote\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + "\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + ".xml", objNotaCreditoxml);
            //MySQLDocumentoCompraVentaNEG.Instancia().registrarNotaCreditoXML(objNotaCreditoxml);
        }

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("result", result);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void buscarGuiaRemision() throws Exception {

        int criterio = Integer.parseInt(varRequest.getParameter("criterio"));

        String valor = "";

        if (criterio == 1) {
            valor = varRequest.getParameter("nroGuia");
        } else {
            valor = varRequest.getParameter("fecIni") + "&" + varRequest.getParameter("fecFin");
        }

        List<ListaDocumentoCompraVenta> listaDetDocCompraVenta = GuiaRemisionManualNEG.Instancia().buscarGuiaRemision(criterio, valor);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaDetDocCompraVenta", listaDetDocCompraVenta);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

}
