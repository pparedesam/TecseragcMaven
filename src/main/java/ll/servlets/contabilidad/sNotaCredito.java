package ll.servlets.contabilidad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
import ll.comprobantes.electronicos.doc.NotaCreditoUBL21;
import ll.comprobantes.electronicos.use.ReadXML;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.contabilidad.TipoNotaCredito;

import ll.entidades.contabilidad.factura.CabeceraFact;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.exc.ExcepcionPropia;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import ll.negocio.contabilidad.FacturaNEG;
import ll.negocio.contabilidad.TipoNotaCreditoNEG;
import ll.negocio.mySQL.MySQLDocumentoCompraVentaNEG;

public class sNotaCredito extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.00");
    double total, importe, igv, subtotal, igvImporte = 0;
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
            out.println("<title>Servlet sNotaCredito</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sNotaCredito at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            if (action.equalsIgnoreCase("obtenerCabFactura")) {
                obtenerCabFactura();
            } else if (action.equalsIgnoreCase("actualizarItem")) {
                actualizarItem();
            } else if (action.equalsIgnoreCase("registrarNotaCredito")) {
                registrarNotaCredito();
            } else if (action.equalsIgnoreCase("obtenerRsptaSUNAT")) {
                responseSUNAT();
            } else if (action.equalsIgnoreCase("listarTipoNotaCredito")) {
                listarTipoNotaCredito();
            } else if (action.equalsIgnoreCase("obtenerDocumentoGeneradoNota")) {
                obtenerDocumentoGeneradoNota();
            }

        } catch (ExcepcionPropia ex) {
            DocumentoCompraVentaNEG.Instancia().deleteCatchNC(dataObject);
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            String json = gson.toJson(dataObject);
            response.getWriter().write(json);
        } catch (Exception ex) {
            //DocumentoCompraVentaNEG.Instancia().deleteCatchNC(dataObject);
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
    }

    private void obtenerCabFactura() throws Exception {
        String nroFactura = varRequest.getParameter("nroFactura");
        HttpSession ses = varRequest.getSession();
        CabeceraFact objCabeceraFact;

        if ((FacturaNEG.Instancia().verificarNotaCredito(nroFactura)) == 0) {
            objCabeceraFact = FacturaNEG.Instancia().obtenerCabFactura(nroFactura);
            ses.removeAttribute("listaDetalleFactO");
            ses.removeAttribute("listaGuiaRemisionO");
            ArrayList<HashMap<String, String>> listaDetalleFact = new ArrayList<>();
            ArrayList<HashMap<String, String>> listaGuiaRemision = new ArrayList<>();
            for (int i = 0; i < objCabeceraFact.getListDetalleFact().size(); i++) {
                HashMap<String, String> lista = new HashMap<>();

                Integer var1 = objCabeceraFact.getListDetalleFact().get(i).getObjTipoServicio().getDescripcion().length();
                String var2 = objCabeceraFact.getListDetalleFact().get(i).getGlosaVariable().substring(var1);

                lista.put("IdDescripcion", objCabeceraFact.getListDetalleFact().get(i).getObjTipoServicio().getIdTipoServicio());
                lista.put("Cantidad", objCabeceraFact.getListDetalleFact().get(i).getCantidad());
                lista.put("Total", objCabeceraFact.getListDetalleFact().get(i).getTotal());
                lista.put("Detalle", var2);
                lista.put("ValorUnitario", objCabeceraFact.getListDetalleFact().get(i).getValorUnitario());
                lista.put("PrecioUnitario", objCabeceraFact.getListDetalleFact().get(i).getPrecioUnitario());
                lista.put("UM", objCabeceraFact.getListDetalleFact().get(i).getObjUnidadMedida().getIdUM());
                lista.put("UMdes", objCabeceraFact.getListDetalleFact().get(i).getObjUnidadMedida().getDesUM());
                lista.put("Descripcion", objCabeceraFact.getListDetalleFact().get(i).getGlosaVariable());
                lista.put("Indice", String.valueOf(i));
                for (int j = 0; j < objCabeceraFact.getListDetalleFact().get(i).getListDetalleMontoFact().size(); j++) {
                    if (objCabeceraFact.getListDetalleFact().get(i).getListDetalleMontoFact().get(j).getIdDetGastoSunat().equals("0001")) {
                        lista.put("Monto", objCabeceraFact.getListDetalleFact().get(i).getListDetalleMontoFact().get(j).getMonto());
                    } else if (objCabeceraFact.getListDetalleFact().get(i).getListDetalleMontoFact().get(j).getIdDetGastoSunat().equals("1000")) {
                        lista.put("IGV", objCabeceraFact.getListDetalleFact().get(i).getListDetalleMontoFact().get(j).getMonto());
                    }
                }

                listaDetalleFact.add(lista);
                ses.setAttribute("listaDetalleFactO", listaDetalleFact);
                ses.setAttribute("listaDetalleFactE", new ArrayList<>());
            }

            for (int i = 0; i < objCabeceraFact.getListDetalleGuiaRemision().size(); i++) {
                HashMap<String, String> lista = new HashMap<>();

                lista.put("NroGuia", objCabeceraFact.getListDetalleGuiaRemision().get(i).getNroGuia());
                lista.put("TipGuia", objCabeceraFact.getListDetalleGuiaRemision().get(i).getTipGuia());
                lista.put("Fecha", objCabeceraFact.getListDetalleGuiaRemision().get(i).getFecha());
                lista.put("DescripcionGuia", objCabeceraFact.getListDetalleGuiaRemision().get(i).getDescripcion());

                listaGuiaRemision.add(lista);
                ses.setAttribute("listaGuiaRemisionO", listaGuiaRemision);
                ses.setAttribute("listaGuiaRemisionE", new ArrayList<>());

            }
            ArrayList<HashMap<String, String>> listaDetalleFactO = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaDetalleFactO");
            ArrayList<HashMap<String, String>> listaGuiaRemisionO = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaGuiaRemisionO");
            Map<String, Object> datos = new HashMap<>();
            datos.put("Result", "OK");
            datos.put("CabeceraFact", objCabeceraFact);
            datos.put("DetalleFactura", listaDetalleFactO);
            datos.put("listaGuiaRemision", listaGuiaRemisionO);
            json = gson.toJson(datos);
            varResponse.getWriter().write(json);
        } else {
            throw new ExcepcionPropia("FACTURA YA TIENE NOTA");
        }

    }

    private void actualizarItem() throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        importe = 0;
        igvImporte = 0;

        ArrayList<HashMap<String, String>> listaDetalleFactO = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaDetalleFactO");
        ArrayList<HashMap<String, String>> listaDetalleFactE = (ArrayList<HashMap<String, String>>) ses.getAttribute("listaDetalleFactE");
        ArrayList<HashMap<String, String>> nuevalistaDetalleFactE = new ArrayList<>();

        lista.put("IdDescripcion", varRequest.getParameter("idTipoServicio"));
        lista.put("Total", String.valueOf(Double.parseDouble(varRequest.getParameter("importe")) + Double.parseDouble(varRequest.getParameter("igv"))));
        lista.put("Detalle", varRequest.getParameter("detalle"));
        lista.put("Descripcion", (varRequest.getParameter("servicio") + " " + varRequest.getParameter("detalle")));
        lista.put("Monto", varRequest.getParameter("importe"));
        lista.put("IGV", varRequest.getParameter("igv"));
        //lista.put("PrecioUnitario", objCabeceraFact.getListDetalleFact().get(i).getPrecioUnitario());
        lista.put("Indice", varRequest.getParameter("indice"));
        listaDetalleFactE.add(lista);

        for (int i = 0; i < listaDetalleFactO.size(); i++) {
            for (int j = 0; j < listaDetalleFactE.size(); j++) {
                String varA = listaDetalleFactO.get(i).get("Indice");
                String varB = listaDetalleFactE.get(j).get("Indice");
                if (varA.equals(varB)) {

                    nuevalistaDetalleFactE.add(listaDetalleFactE.get(j));
                    importe = importe + Double.parseDouble(listaDetalleFactE.get(j).get("Monto"));
                    igvImporte = igvImporte + Double.parseDouble(listaDetalleFactE.get(j).get("IGV"));

                } else {

                    nuevalistaDetalleFactE.add(listaDetalleFactO.get(i));
                    importe = importe + Double.parseDouble(listaDetalleFactO.get(i).get("Monto"));
                    igvImporte = igvImporte + Double.parseDouble(listaDetalleFactO.get(i).get("IGV"));
                }
            }
        }

        subtotal = fijarNumero(importe, 2);
        igv = fijarNumero(igvImporte, 2);
        total = subtotal + igv;

        ses.removeAttribute("listaDetalleFactO");
        ses.removeAttribute("listaDetalleFactE");
        ses.setAttribute("listaDetalleFactO", nuevalistaDetalleFactE);
        ses.setAttribute("listaDetalleFactE", new ArrayList<>());

        Map<String, Object> datos = new HashMap<>();

        datos.put("Result", "OK");
        datos.put("DetalleFactura", nuevalistaDetalleFactE);
        datos.put("total", total);
        datos.put("igv", igv);
        datos.put("subtotal", subtotal);
        json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    private void responseSUNAT() throws IOException {

        NotaCreditoxml objNotaCreditoxml = new NotaCreditoxml();

        objNotaCreditoxml.setNumeracionFactura(varRequest.getParameter("nroFactura"));

        objNotaCreditoxml = ReadXML.getResultNC("A:\\DocumentosElectronicos\\CreditNote\\20315295573-07-" + objNotaCreditoxml.getNumeracionFactura() + "\\R-20315295573-07-" + objNotaCreditoxml.getNumeracionFactura() + ".xml", objNotaCreditoxml);

        dataObject.put("Result", "OK");
        dataObject.put("response", objNotaCreditoxml.getResponse());
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void registrarNotaCredito() throws Exception, ExcepcionPropia {

        dataObject = new HashMap<>();

        String conexion = DocumentoCompraVentaNEG.Instancia().estadoConexion();

        if (conexion == "activo") {

            final Gson gson = new Gson();
            String jsonDocGenerado = varRequest.getParameter("objDocGenerado");
            final DocumentoGenerado objDocumentoGenerado = gson.fromJson(jsonDocGenerado, DocumentoGenerado.class);

            NotaCreditoxml objNotaCreditoxml = new NotaCreditoxml();
            HttpSession sesion = varRequest.getSession();
            Usuario objUsuario = (Usuario) sesion.getAttribute("user");

            objDocumentoGenerado.setGlosaFija(varRequest.getParameter("idTipoNota") + "/" + objDocumentoGenerado.getGlosaVariable());
            objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("detalleMotivo"));

            objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));

            objDocumentoGenerado.setIdDoc("0004");

            HashMap<String, String> result = new HashMap<>();

            result = DocumentoCompraVentaNEG.Instancia().registrarNotaCredito(objDocumentoGenerado, objUsuario);

            objDocumentoGenerado.setNroDoc(result.get("nroDoc"));
            objDocumentoGenerado.setObjOficina(objUsuario.getObjEmpleado().getObjOficina());

            objNotaCreditoxml = MySQLDocumentoCompraVentaNEG.Instancia().obtenerNotaCreditoXML(objDocumentoGenerado);

            NotaCreditoUBL21.Instancia().c_XML(objNotaCreditoxml);
            Boolean band = ParametroDAO.Instancia().actualizarParametroNotaCreditoElectronica(objDocumentoGenerado.getNroDocExt(), objDocumentoGenerado.getNroDoc(), objDocumentoGenerado.getObjOficina().getIdOficina(), objDocumentoGenerado.getObjTipoMoneda().getId());
            if (band == false) {

                throw new ExcepcionPropia("ERROR PARAMETRO");
            } else {
                //objNotaDebitoxml = ReadXML.getSignNC("A:\\DocumentosElectronicos\\CreditNote\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + "\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + ".xml", objNotaCreditoxml);
                //MySQLDocumentoCompraVentaNEG.Instancia().registrarNotaCreditoXML(objNotaCreditoxml);
            }

            dataObject.put("Result", "OK");

            dataObject.put("nroDoc", objNotaCreditoxml.getNroDoc());
            dataObject.put("idDoc", objNotaCreditoxml.getIdDoc());
            dataObject.put("idOficina", objNotaCreditoxml.getIdOfi());
            dataObject.put("tipMoneda", objNotaCreditoxml.getTipM());
            //dataObject.put("result", result);

        } else {
            dataObject.put("Result", "errorConexion");
            dataObject.put("message", "NO HAY CONEXION CON EL SERVIDOR OSE");
        }

        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

        /**
         * HttpSession sesion = varRequest.getSession(); NotaCreditoxml
         * objNotaCreditoxml = new NotaCreditoxml(); DocumentoGenerado
         * objDocumentoGenerado = new DocumentoGenerado(); DocCompraVenta
         * objDocCompraVenta = new DocCompraVenta();
         *
         * objDocumentoGenerado.getObjRelaDoc().setIdOficinaR(varRequest.getParameter("idoficinaR"));
         * objDocumentoGenerado.getObjRelaDoc().setIdDocR(varRequest.getParameter("idDocR"));
         * objDocumentoGenerado.getObjRelaDoc().setTipMonedaR(varRequest.getParameter("tipMonedaR"));
         * objDocumentoGenerado.getObjRelaDoc().setNroDocR(varRequest.getParameter("nroDocR"));
         *
         * objDocumentoGenerado.setTipMoneda(varRequest.getParameter("tipoMoneda"));
         * objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaCliente"));
         * objDocumentoGenerado.setIdDoc("0004");
         *
         * objDocumentoGenerado.setGlosaFija(varRequest.getParameter("idTipoNota")
         * + "/" + varRequest.getParameter("observacion"));
         * objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("detalleMotivo"));
         *
         * objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));
         *
         * Usuario objUsuario = (Usuario) sesion.getAttribute("user");
         * ArrayList<HashMap<String, String>> listaDetalleFactura =
         * (ArrayList<HashMap<String, String>>)
         * sesion.getAttribute("listaDetalleFactO");
         *
         *
         * ArrayList<HashMap<String, String>> listaDetalleGuiaRemision = new
         * ArrayList<HashMap<String, String>>(); listaDetalleGuiaRemision =
         * (ArrayList<HashMap<String, String>>)
         * sesion.getAttribute("listaGuiaRemisionO");
         *
         * objDocCompraVenta.getObjEmisor().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
         * objDocCompraVenta.getObjEjecutor().setIdPersona(varRequest.getParameter("idPersonaCliente"));
         *
         * HashMap<String, String> result = new HashMap<>();
         *
         * result =
         * DocumentoCompraVentaNEG.Instancia().registrarNotaCredito(objDocumentoGenerado,
         * objDocCompraVenta, objUsuario, listaDetalleFactura,
         * listaDetalleGuiaRemision);
         *
         * objDocumentoGenerado.setNroDoc(result.get("nroDoc"));
         * objDocumentoGenerado.setObjOficina(objUsuario.getObjEmpleado().getObjOficina());
         *
         * objNotaCreditoxml =
         * MySQLDocumentoCompraVentaNEG.Instancia().obtenerNotaCreditoXML(objDocumentoGenerado);
         *
         * dataObject.put("nroDoc", objNotaCreditoxml.getNroDoc());
         * dataObject.put("idDoc", objNotaCreditoxml.getIdDoc());
         * dataObject.put("idOficina", objNotaCreditoxml.getIdOfi());
         * dataObject.put("tipMoneda", objNotaCreditoxml.getTipM());
         *
         * NotaCreditoUBL21.Instancia().c_XML(objNotaCreditoxml);
         *
         * Boolean band =
         * ParametroDAO.Instancia().actualizarParametroNotaCreditoElectronica(objDocCompraVenta.getDocumentoNro(),
         * objDocumentoGenerado.getNroDoc(),
         * objDocumentoGenerado.getObjOficina().getIdOficina(),
         * objDocumentoGenerado.getTipMoneda()); if (band == false) {
         *
         * throw new ExcepcionPropia("ERROR PARAMETRO"); } else {
         * //objNotaCreditoxml =
         * ReadXML.getSignNC("A:\\DocumentosElectronicos\\CreditNote\\" +
         * objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" +
         * objNotaCreditoxml.getTipoDocumento() + "-" +
         * objNotaCreditoxml.getNumeracionFactura() + "\\" +
         * objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" +
         * objNotaCreditoxml.getTipoDocumento() + "-" +
         * objNotaCreditoxml.getNumeracionFactura() + ".xml",
         * objNotaCreditoxml);
         * //MySQLDocumentoCompraVentaNEG.Instancia().registrarNotaCreditoXML(objNotaCreditoxml);
         * }
         *
         * dataObject = new HashMap<>(); dataObject.put("Result", "OK");
         * dataObject.put("result", result); json = gson.toJson(dataObject);
         * varResponse.getWriter().write(json);
         */
    }

    private void listarTipoNotaCredito() throws Exception {

        List<TipoNotaCredito> listarTipoNotaCredito = TipoNotaCreditoNEG.Instancia().listar();

        dataObject.put("Result", "OK");
        dataObject.put("listarTipoNotaCredito", listarTipoNotaCredito);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void obtenerDocumentoGeneradoNota() throws Exception {

        DocumentoGenerado objDocGenerado = new DocumentoGenerado();

        objDocGenerado.setIdDoc(varRequest.getParameter("idDoc"));
        objDocGenerado.setNroDoc(varRequest.getParameter("nroDoc"));
        objDocGenerado.getObjOficina().setIdOficina(varRequest.getParameter("idOficina"));
        objDocGenerado.getObjTipoMoneda().setId(varRequest.getParameter("tipMoneda"));

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        objDocumentoGenerado = DocumentoCompraVentaNEG.Instancia().obtenerDocumenteGeneradoNota(objDocGenerado);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("objDocumentoGenerado", objDocumentoGenerado);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }
}
