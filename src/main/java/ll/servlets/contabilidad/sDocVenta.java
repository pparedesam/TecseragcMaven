package ll.servlets.contabilidad;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.accesodatos.agente.ParametroDAO;
import ll.accesodatos.contabilidad.DocumentoCompraVentaDAO;
import ll.comprobantes.electronicos.doc.FacturaUBL21;
import ll.comprobantes.electronicos.use.ReadXML;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetDocCompraVenta;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.xmlFacturacion.Facturaxml;

import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.exc.ExcepcionPropia;
import ll.negocio.mySQL.MySQLDocumentoCompraVentaNEG;

public class sDocVenta extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    DecimalFormat df = new DecimalFormat("#.00");
    double total, importe, igv, subtotal, igvImporte, cantidad, precioU, totalImp, valorunitario = 0;

    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json;
    PrintWriter out = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Frameset//EN\" \"http://www.w3.org/TR/REC-html40/frameset.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sDocVenta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sDocVenta at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("comprobarDetalleVenta")) {
                comprobarDetalleVenta();
            }
            if (action.equalsIgnoreCase("quitarDetalleVenta")) {
                quitarDetalleVenta();
            }

            if (action.equalsIgnoreCase("anularVenta")) {
                anularVenta();
            }
            if (action.equalsIgnoreCase("limpiarSession")) {
                limpiarSession();
            }
            if (action.equalsIgnoreCase("comprobarDetalleGuia")) {
                comprobarDetalleGuia();
            }
            if (action.equalsIgnoreCase("quitarDetalleGuia")) {
                quitarDetalleGuia();
            }
            if (action.equalsIgnoreCase("modificarDetalleVentaF")) {
                modificarDetalleVenta();
            }

            if (action.equalsIgnoreCase("obtenerRsptaSUNAT")) {
                responseSUNAT();
            }
            if (action.equalsIgnoreCase("buscarFacturas")) {
                buscarFacturas();
            }
            if (action.equalsIgnoreCase("registrarFacturaElectronica")) {
                registrarFacturaElectronica();
            }

        } catch (ExcepcionPropia ex) {
            int a = -1;
            for (Map.Entry e : dataObject.entrySet()) {
                if (e.getKey().equals("registro")) {
                    a = Integer.parseInt(e.getValue().toString());
                }
            }
            if (a == 1) {
                DocumentoCompraVentaNEG.Instancia().deleteCatch(dataObject);
            }
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            response.getWriter().write(json);
        } catch (Exception ex) {
            int a = -1;

            for (Map.Entry e : dataObject.entrySet()) {
                if (e.getKey().equals("registro")) {
                    a = Integer.parseInt(e.getValue().toString());
                }
            }
            if (a == 1) {
                // DocumentoCompraVentaNEG.Instancia().deleteCatch(dataObject);
            }
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

    private void comprobarDetalleVenta() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoVenta");

        String descripcion = varRequest.getParameter("servicio");

        totalImp = 0;

        if (detalleFactura == null || detalleFactura.isEmpty()) {
            detalleFactura = new ArrayList<>();
            precioU = 0.00;
            cantidad = 0.00;
            importe = 0.00;
            subtotal = 0.00;
            igv = 0.00;
            valorunitario = 0.00;

            valorunitario = Double.parseDouble(varRequest.getParameter("valorunitario"));
            importe = Double.parseDouble(varRequest.getParameter("importe"));
            igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
            subtotal = fijarNumero(subtotal + importe, 2);
            igv = fijarNumero(igv + igvImporte, 2);

            totalImp = importe + igvImporte;
            total = subtotal + igv;

            lista.put("UM", varRequest.getParameter("UM"));
            lista.put("uniMedida", varRequest.getParameter("uniMedida"));
            lista.put("preciounitario", varRequest.getParameter("preciounitario"));
            lista.put("cantidad", varRequest.getParameter("cantidad"));
            lista.put("idTipoServicio", varRequest.getParameter("idTipoServicio"));
            lista.put("valorunitario", varRequest.getParameter("valorunitario"));
            lista.put("descripcion", varRequest.getParameter("servicio"));
            lista.put("importe", varRequest.getParameter("importe"));
            lista.put("igv", varRequest.getParameter("igv"));
            lista.put("total", String.valueOf(totalImp));

            detalleFactura.add(lista);
            ses.setAttribute("detalladoVenta", detalleFactura);
            dataObject.put("Result", "OK");

        } else {
            int i = 0;
            while (detalleFactura.size() > i) {
                String a = (detalleFactura.get(i).get("descripcion"));

                if (descripcion.equalsIgnoreCase(a)) {
                    i = -1;
                    dataObject.put("Result", "KO");
                    break;
                }
                i++;
            }
            if (i >= 0) {
                valorunitario = Double.parseDouble(varRequest.getParameter("valorunitario"));
                importe = Double.parseDouble(varRequest.getParameter("importe"));
                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
                subtotal = fijarNumero(subtotal + importe, 2);
                igv = fijarNumero(igv + igvImporte, 2);

                totalImp = importe + igvImporte;
                total = subtotal + igv;

                lista.put("UM", varRequest.getParameter("UM"));
                lista.put("uniMedida", varRequest.getParameter("uniMedida"));
                lista.put("preciounitario", varRequest.getParameter("preciounitario"));
                lista.put("cantidad", varRequest.getParameter("cantidad"));
                lista.put("idTipoServicio", varRequest.getParameter("idTipoServicio"));
                lista.put("valorunitario", varRequest.getParameter("valorunitario"));
                lista.put("descripcion", varRequest.getParameter("servicio"));
                lista.put("importe", varRequest.getParameter("importe"));
                lista.put("igv", varRequest.getParameter("igv"));
                lista.put("total", String.valueOf(totalImp));

                detalleFactura.add(lista);
                ses.setAttribute("detalladoVenta", detalleFactura);
                dataObject.put("Result", "OK");
            }

        }

        dataObject.put("detalleFactura", detalleFactura);
        dataObject.put("total", total);
        dataObject.put("igv", igv);
        dataObject.put("subtotal", subtotal);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    private void quitarDetalleVenta() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String idTipoServicio = varRequest.getParameter("servicio");

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoVenta");
        ArrayList<HashMap<String, String>> nuevaDetalleVenta = new ArrayList<>();

        int band = 0;
        for (int i = 0; i < detalleFactura.size(); i++) {

            if (idTipoServicio.compareTo(detalleFactura.get(i).get("descripcion")) != 0) {
                nuevaDetalleVenta.add(detalleFactura.get(i));

            } else {

                importe = Double.parseDouble(varRequest.getParameter("importe"));
                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
                subtotal = fijarNumero(subtotal - importe, 2);
                igv = fijarNumero(igv - igvImporte, 2);
                total = fijarNumero(subtotal + igv, 2);
            }
            band = 1;

        }
        if (band == 1 && nuevaDetalleVenta.size() > 0) {

            lista = nuevaDetalleVenta.get(0);
            nuevaDetalleVenta.remove(0);
            nuevaDetalleVenta.add(lista);
        }

        ses.setAttribute("detalladoVenta", nuevaDetalleVenta);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", nuevaDetalleVenta);
        dataObject.put("total", total);
        dataObject.put("igv", igv);
        dataObject.put("subtotal", subtotal);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void comprobarDetalleGuia() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String descripcion = varRequest.getParameter("nroGuia");
        String idTipoGuia = varRequest.getParameter("idTipoGuia");
        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalladoGuiaRemisionF = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoGuiaRemisionF");

        if (detalladoGuiaRemisionF == null || detalladoGuiaRemisionF.isEmpty()) {
            detalladoGuiaRemisionF = new ArrayList<>();
            lista.put("idTipoGuia", varRequest.getParameter("idTipoGuia"));
            lista.put("tipoGuia", varRequest.getParameter("tipoGuia"));
            lista.put("nroGuia", varRequest.getParameter("nroGuia"));
            lista.put("fechaGuia", varRequest.getParameter("fechaGuia"));
            detalladoGuiaRemisionF.add(lista);
            ses.setAttribute("detalladoGuiaRemisionF", detalladoGuiaRemisionF);
            dataObject.put("Result", "OK");
        } else {
            int i = 0;
            while (detalladoGuiaRemisionF.size() > i) {
                String a = (detalladoGuiaRemisionF.get(i).get("idTipoGuia"));
                String b = (detalladoGuiaRemisionF.get(i).get("nroGuia"));
                if (idTipoGuia.equalsIgnoreCase(a) && descripcion.equalsIgnoreCase(b)) {
                    i = -1;

                    dataObject.put("Result", "KO");
                    break;
                }
                i++;
            }
            if (i >= 0) {
                lista.put("idTipoGuia", varRequest.getParameter("idTipoGuia"));
                lista.put("tipoGuia", varRequest.getParameter("tipoGuia"));
                lista.put("nroGuia", varRequest.getParameter("nroGuia"));
                lista.put("fechaGuia", varRequest.getParameter("fechaGuia"));
                detalladoGuiaRemisionF.add(lista);
                ses.setAttribute("detalladoGuiaRemisionF", detalladoGuiaRemisionF);
                dataObject.put("Result", "OK");
            }
        }
        /* if (detalladoGuiaRemisionF == null || detalladoGuiaRemisionF.isEmpty()) {
            detalladoGuiaRemisionF = new ArrayList<>();
        }
        lista.put("idTipoGuia", varRequest.getParameter("idTipoGuia"));
        lista.put("tipoGuia", varRequest.getParameter("tipoGuia"));
        lista.put("nroGuia", varRequest.getParameter("nroGuia"));
        lista.put("fechaGuia", varRequest.getParameter("fechaGuia"));
        detalladoGuiaRemisionF.add(lista);

        ses.setAttribute("detalladoGuiaRemisionF", detalladoGuiaRemisionF);
        dataObject.put("Result", "OK");*/
        dataObject.put("detalladoGuiaRemisionF", detalladoGuiaRemisionF);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void quitarDetalleGuia() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String descripcion = varRequest.getParameter("nroGuia");
        String idTipoGuia = varRequest.getParameter("idTipoGuia");
        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalladoGuiaRemisionF = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoGuiaRemisionF");
        ArrayList<HashMap<String, String>> nuevaDetalleGuia = new ArrayList<>();

        int band = 0;
        for (int i = 0; i < detalladoGuiaRemisionF.size(); i++) {
            String a = descripcion + idTipoGuia;
            if (a.compareTo(detalladoGuiaRemisionF.get(i).get("nroGuia") + detalladoGuiaRemisionF.get(i).get("idTipoGuia")) != 0) {
                nuevaDetalleGuia.add(detalladoGuiaRemisionF.get(i));
            } else {
            }
            band = 1;

        }
        if (band == 1 && nuevaDetalleGuia.size() > 0) {

            lista = nuevaDetalleGuia.get(0);
            nuevaDetalleGuia.remove(0);
            nuevaDetalleGuia.add(lista);
        }

        ses.setAttribute("detalladoGuiaRemisionF", nuevaDetalleGuia);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalladoGuiaRemisionF", nuevaDetalleGuia);

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void anularVenta() throws Exception {
        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
        objDocumentoGenerado.getObjOficina().setIdOficina(varRequest.getParameter("idoficina"));
        objDocumentoGenerado.setIdDoc(varRequest.getParameter("IdDoc"));
        objDocumentoGenerado.getObjTipoMoneda().setId(varRequest.getParameter("TipMoneda"));
        objDocumentoGenerado.setNroDoc(varRequest.getParameter("NroDoc"));
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Boolean respuesta = DocumentoCompraVentaDAO.Instancia().anularDocumentoCompraVenta(objDocumentoGenerado, objUsuario);

        dataObject.put("resultado", String.valueOf(respuesta));
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void pasarData() throws Exception {
        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
        objDocumentoGenerado.getObjOficina().setIdOficina(varRequest.getParameter("idoficina"));
        objDocumentoGenerado.setIdDoc(varRequest.getParameter("IdDoc"));
        objDocumentoGenerado.getObjTipoMoneda().setId(varRequest.getParameter("TipMoneda"));
        objDocumentoGenerado.setNroDoc(varRequest.getParameter("NroDoc"));
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Boolean respuesta = DocumentoCompraVentaDAO.Instancia().anularDocumentoCompraVenta(objDocumentoGenerado, objUsuario);

        dataObject.put("resultado", String.valueOf(respuesta));
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void limpiarSession() throws IOException {
        HttpSession ses = varRequest.getSession(true);
        ses.removeAttribute("detalladoVenta");
        ses.removeAttribute("detalladoGuiaRemisionF");
    }

    private void responseSUNAT() throws IOException, ServletException {

        Facturaxml objFacturaxml = new Facturaxml();

        objFacturaxml.setNumeracionFactura(varRequest.getParameter("nroFactura"));

        objFacturaxml = ReadXML.getResult("A:\\DocumentosElectronicos\\Invoice\\20315295573-01-" + objFacturaxml.getNumeracionFactura() + "\\R-20315295573-01-" + objFacturaxml.getNumeracionFactura() + ".xml", objFacturaxml);

        dataObject.put("Result", "OK");
        dataObject.put("response", objFacturaxml.getResponse());
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void modificarDetalleVenta() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoVenta");
        ArrayList<HashMap<String, String>> nuevaDetalleVenta = new ArrayList<>();
        int i = 0;
        int band = 0;
        while (detalleFactura.size() > i) {
            String a = (detalleFactura.get(i).get("descripcion"));
            String descripcion = varRequest.getParameter("detalleant");

            if (descripcion.equals(a)) {

                lista.put("UM", detalleFactura.get(i).get("UM"));
                lista.put("uniMedida", detalleFactura.get(i).get("uniMedida"));
                lista.put("preciounitario", varRequest.getParameter("preciounitario"));
                lista.put("cantidad", varRequest.getParameter("cantidad"));
                lista.put("idTipoServicio", detalleFactura.get(i).get("idTipoServicio"));
                lista.put("valorunitario", varRequest.getParameter("valorunitario"));
                lista.put("descripcion", detalleFactura.get(i).get("descripcion"));
                lista.put("importe", varRequest.getParameter("importe"));
                lista.put("igv", varRequest.getParameter("igv"));

                totalImp = Double.parseDouble(varRequest.getParameter("importe")) + Double.parseDouble(varRequest.getParameter("igv"));
                lista.put("total", String.valueOf(totalImp));

                importe = Double.parseDouble(detalleFactura.get(i).get("importe"));
                igvImporte = Double.parseDouble(detalleFactura.get(i).get("igv"));
                subtotal = fijarNumero(subtotal - importe, 2);
                igv = fijarNumero(igv - igvImporte, 2);
                total = fijarNumero(subtotal + igv, 2);

                importe = Double.parseDouble(varRequest.getParameter("importe"));
                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
                subtotal = fijarNumero(subtotal + importe, 2);
                igv = fijarNumero(igv + igvImporte, 2);
                total = fijarNumero(subtotal + igv, 2);

                nuevaDetalleVenta.add(lista);

            } else {

                nuevaDetalleVenta.add(detalleFactura.get(i));
            }
            band = 1;
            i++;
        }
        if (band == 1 && nuevaDetalleVenta.size() > 0) {

            lista = nuevaDetalleVenta.get(0);
            nuevaDetalleVenta.remove(0);
            nuevaDetalleVenta.add(lista);
        }

        ses.setAttribute("detalladoVenta", nuevaDetalleVenta);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", nuevaDetalleVenta);
        dataObject.put("total", total);
        dataObject.put("igv", igv);
        dataObject.put("subtotal", subtotal);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void buscarFacturas() throws Exception {

        String tipo = varRequest.getParameter("tipo");
        String nroDoc = varRequest.getParameter("nroDoc");
        String fecIni = varRequest.getParameter("fecIni");
        String fecFin = varRequest.getParameter("fecFin");

        List<ListaDocumentoCompraVenta> listaDocumentosCompraVenta = new ArrayList<>();

        listaDocumentosCompraVenta = DocumentoCompraVentaNEG.Instancia().buscarDocumentoCompraVenta(tipo, nroDoc, fecIni, fecFin);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaDocumentosCompraVenta", listaDocumentosCompraVenta);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void registrarFacturaElectronica() throws Exception, ExcepcionPropia {

        dataObject = new HashMap<>();

        String conexion = DocumentoCompraVentaNEG.Instancia().estadoConexion();

        if (conexion == "activo") {
            HttpSession sesion = varRequest.getSession();
            Facturaxml objFacturaXML = new Facturaxml();
            DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
            DocCompraVenta objDocCompraVenta = new DocCompraVenta();

            objDocumentoGenerado.getObjTipoMoneda().setId(varRequest.getParameter("tipoMoneda"));
            objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaCliente"));
            objDocumentoGenerado.setIdDoc("0001");
            objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("observacion"));
            objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));

            Usuario objUsuario = (Usuario) sesion.getAttribute("user");

            Gson gson1 = new Gson();
            String detalle = varRequest.getParameter("listaDetalle");
            java.lang.reflect.Type listaDetalle = new TypeToken<ArrayList<DetDocCompraVenta>>() {
            }.getType();
            ArrayList<DetDocCompraVenta> listaDetalleFactura = gson1.fromJson(detalle, listaDetalle);

            Gson gson2 = new Gson();
            String detalleGuia = varRequest.getParameter("listaGuias");
            java.lang.reflect.Type tmpListaDetalleGuias = new TypeToken<ArrayList<GuiaRemision>>() {
            }.getType();
            ArrayList<GuiaRemision> listaDetalleGuias = gson2.fromJson(detalleGuia, tmpListaDetalleGuias);

            objDocCompraVenta.getObjEmisor().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
            objDocCompraVenta.getObjEjecutor().setIdPersona(varRequest.getParameter("idPersonaCliente"));

            HashMap<String, String> result = new HashMap<>();

            result = DocumentoCompraVentaNEG.Instancia().registrarFacturaElectronica(objDocumentoGenerado, objDocCompraVenta, objUsuario, listaDetalleFactura, listaDetalleGuias);

            objDocumentoGenerado.setNroDoc(result.get("nroDoc"));
            objDocumentoGenerado.setObjOficina(objUsuario.getObjEmpleado().getObjOficina());

            objFacturaXML = MySQLDocumentoCompraVentaNEG.Instancia().obtenerFacturaXML(objDocumentoGenerado);

            FacturaUBL21.Instancia().c_XML(objFacturaXML);

            Boolean band = ParametroDAO.Instancia().actualizarParametroFacturaElectronica(objDocCompraVenta.getDocumentoNro(), objDocumentoGenerado.getNroDoc(), objDocumentoGenerado.getObjOficina().getIdOficina(), objDocumentoGenerado.getObjTipoMoneda().getId());

            if (band == false) {
                throw new ExcepcionPropia("ERROR PARAMETRO");
            }

            dataObject.put("nroDoc", objFacturaXML.getNroDoc());
            dataObject.put("idDoc", objFacturaXML.getIdDoc());
            dataObject.put("idOficina", objFacturaXML.getIdOfi());
            dataObject.put("tipMoneda", objFacturaXML.getTipM());
            dataObject.put("registro", 1);
            dataObject.put("Result", "OK");
            dataObject.put("result", result);
        } else {
            dataObject.put("Result", "errorConexion");
            dataObject.put("message", "NO HAY CONEXION CON EL SERVIDOR OSE");
        }

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

}
