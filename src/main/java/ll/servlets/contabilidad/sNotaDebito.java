/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import ll.comprobantes.electronicos.doc.NotaDebitoUBL21;
import ll.comprobantes.electronicos.use.ReadXML;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.TipoNotaDebito;
import ll.entidades.contabilidad.factura.CabeceraFact;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.NotaDebitoxml;
import ll.exc.ExcepcionPropia;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import ll.negocio.contabilidad.FacturaNEG;
import ll.negocio.contabilidad.TipoNotaDebitoNEG;
import ll.negocio.mySQL.MySQLDocumentoCompraVentaNEG;

/**
 *
 * @author RenRio
 */
public class sNotaDebito extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.00");
    double total, importe, igv, subtotal, igvImporte, cantidad, precioU, totalImp, valorunitario = 0;
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
            out.println("<title>Servlet sNotaDebito</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sNotaDebito at " + request.getContextPath() + "</h1>");
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
            } else if (action.equalsIgnoreCase("registrarNotaDebito")) {
                registrarNotaDebito();
            } else if (action.equalsIgnoreCase("DetallePenalidad")) {
                detallePenalidad();
            } else if (action.equalsIgnoreCase("listarTipoNotaDebito")) {
                listarTipoNotaDebito();
            } else if (action.equalsIgnoreCase("obtenerDocumentoGeneradoNota")) {
                obtenerDocumentoGeneradoNota();
            } else if (action.equalsIgnoreCase("obtenerRsptaSUNAT")) {
                responseSUNAT();
            }
        } catch (ExcepcionPropia ex) {
            //DocumentoCompraVentaNEG.Instancia().deleteCatchNC(dataObject);
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            String json = gson.toJson(dataObject);
            response.getWriter().write(json);
        } catch (Exception ex) {
            DocumentoCompraVentaNEG.Instancia().deleteCatchNC(dataObject);
            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            response.getWriter().write(json);
        }
    }

    private void detallePenalidad() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        ses.removeAttribute("listaDetalleFactO");
        ses.removeAttribute("detalladoGuiaRemisionF");
        HttpSession sesion = varRequest.getSession();
        sesion.removeAttribute("listaDetalleFactO");
        sesion.removeAttribute("detalladoGuiaRemisionF");
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("listaDetalleFactO");

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
//            importe = Double.parseDouble(varRequest.getParameter("importe"));
//            igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
            subtotal = fijarNumero(valorunitario, 2);
            igv = fijarNumero(0, 2);

            // totalImp = importe + igvImporte;
            total = subtotal + igv;

            lista.put("UM", "TNE");
            lista.put("UMdes", "TONELADA");
            lista.put("PrecioUnitario", "0");
            lista.put("Cantidad", "0");
            lista.put("IdDescripcion", "");
            lista.put("ValorUnitario", varRequest.getParameter("valorunitario"));
            lista.put("Descripcion", varRequest.getParameter("descripcion"));
            lista.put("Monto", varRequest.getParameter("valorunitario"));
            lista.put("IGV", "0");
            lista.put("total", String.valueOf(totalImp));

            detalleFactura.add(lista);
            ses.setAttribute("listaDetalleFactO", detalleFactura);
            dataObject.put("Result", "OK");

        }

        dataObject.put(
                "DetalleFactura", detalleFactura);
        dataObject.put(
                "total", total);
        dataObject.put(
                "igv", igv);
        dataObject.put(
                "subtotal", subtotal);
        json = gson.toJson(dataObject);

        varResponse.getWriter()
                .write(json);

    }

    private void obtenerCabFactura() throws Exception {
        String nroFactura = varRequest.getParameter("nroFactura");
        HttpSession ses = varRequest.getSession();
        CabeceraFact objCabeceraFact;

        if ((FacturaNEG.Instancia().verificarNotaDebito(nroFactura)) == 0) {
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

    private void actualizarItem() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura
                = (ArrayList<HashMap<String, String>>) sesion.getAttribute("listaDetalleFactO");
        ArrayList<HashMap<String, String>> nuevaDetalleVenta = new ArrayList<>();
        int i = 0;
        int band = 0;
        while (detalleFactura.size() > i) {
            String a = (detalleFactura.get(i).get("Descripcion"));
            String descripcion = varRequest.getParameter("detalleant");

            if (descripcion.equals(a)) {

                lista.put("UM", detalleFactura.get(i).get("UM"));
                lista.put("UMdes", detalleFactura.get(i).get("UMdes"));
                lista.put("PrecioUnitario", varRequest.getParameter("preciounitario"));
                lista.put("Cantidad", varRequest.getParameter("cantidad"));
                lista.put("IdDescripcion", detalleFactura.get(i).get("IdDescripcion"));
                lista.put("ValorUnitario", varRequest.getParameter("valorunitario"));
                lista.put("Descripcion", detalleFactura.get(i).get("Descripcion"));
                lista.put("Monto", varRequest.getParameter("importe"));
                lista.put("IGV", varRequest.getParameter("igv"));

                totalImp = Double.parseDouble(varRequest.getParameter("importe")) + Double.parseDouble(varRequest.getParameter("igv"));
                lista.put("total", String.valueOf(totalImp));

                importe = Double.parseDouble(detalleFactura.get(i).get("Monto"));
                igvImporte = Double.parseDouble(detalleFactura.get(i).get("IGV"));

                subtotal = Double.parseDouble(varRequest.getParameter("subTotal"));
                igv = Double.parseDouble(varRequest.getParameter("igvTotal"));

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

        ses.setAttribute("listaDetalleFactO", nuevaDetalleVenta);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("DetalleFactura", nuevaDetalleVenta);
        dataObject.put("total", total);
        dataObject.put("igv", igv);
        dataObject.put("subtotal", subtotal);
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

    private void registrarNotaDebito() throws Exception, ExcepcionPropia {

        dataObject = new HashMap<>();

        String conexion = DocumentoCompraVentaNEG.Instancia().estadoConexion();

        if (conexion == "activo") {
            final Gson gson = new Gson();
            String jsonDocGenerado = varRequest.getParameter("objDocGenerado");
            final DocumentoGenerado objDocumentoGenerado = gson.fromJson(jsonDocGenerado, DocumentoGenerado.class);

            NotaDebitoxml objNotaDebitoxml = new NotaDebitoxml();
            HttpSession sesion = varRequest.getSession();
            Usuario objUsuario = (Usuario) sesion.getAttribute("user");

            objDocumentoGenerado.setGlosaFija(varRequest.getParameter("idTipoNota") + "/" + objDocumentoGenerado.getGlosaVariable());
            objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("detalleMotivo"));

            objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));

            objDocumentoGenerado.setIdDoc("0008");

            HashMap<String, String> result = new HashMap<>();

            result = DocumentoCompraVentaNEG.Instancia().registrarNotaDebito(objDocumentoGenerado, objUsuario);

            objDocumentoGenerado.setNroDoc(result.get("nroDoc"));
            objDocumentoGenerado.setObjOficina(objUsuario.getObjEmpleado().getObjOficina());

            objNotaDebitoxml = MySQLDocumentoCompraVentaNEG.Instancia().obtenerNotaDebitoXML(objDocumentoGenerado);

            NotaDebitoUBL21.Instancia().c_XML(objNotaDebitoxml);
            Boolean band = ParametroDAO.Instancia().actualizarParametroNotaDebitoElectronica(objDocumentoGenerado.getNroDocExt(), objDocumentoGenerado.getNroDoc(), objDocumentoGenerado.getObjOficina().getIdOficina(), objDocumentoGenerado.getObjTipoMoneda().getId());
            if (band == false) {

                throw new ExcepcionPropia("ERROR PARAMETRO");
            } else {
                //objNotaDebitoxml = ReadXML.getSignNC("A:\\DocumentosElectronicos\\CreditNote\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + "\\" + objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc() + "-" + objNotaCreditoxml.getTipoDocumento() + "-" + objNotaCreditoxml.getNumeracionFactura() + ".xml", objNotaCreditoxml);
                //MySQLDocumentoCompraVentaNEG.Instancia().registrarNotaCreditoXML(objNotaCreditoxml);
            }

            dataObject = new HashMap<>();
            dataObject.put("Result", "OK");
            dataObject.put("nroDoc", objDocumentoGenerado.getNroDoc());
            dataObject.put("idDoc", objDocumentoGenerado.getIdDoc());
            dataObject.put("idOficina", objDocumentoGenerado.getObjOficina().getIdOficina());
            dataObject.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());
        }else {
            dataObject.put("Result", "errorConexion");
            dataObject.put("message", "NO HAY CONEXION CON EL SERVIDOR OSE");
        }

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

    private void listarTipoNotaDebito() throws Exception {

        List<TipoNotaDebito> listarTipoNotaDebito = TipoNotaDebitoNEG.Instancia().listarTipoNotaDebito();

        dataObject.put("Result", "OK");
        dataObject.put("listarTipoNotaDebito", listarTipoNotaDebito);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void responseSUNAT() throws IOException {

        NotaDebitoxml objNotaDebitoxml = new NotaDebitoxml();

        objNotaDebitoxml.setNumeracionFactura(varRequest.getParameter("nroFactura"));

        objNotaDebitoxml = ReadXML.getResultND("A:\\DocumentosElectronicos\\DebitNote\\20315295573-08-" + objNotaDebitoxml.getNumeracionFactura() + "\\R-20315295573-08-" + objNotaDebitoxml.getNumeracionFactura() + ".xml", objNotaDebitoxml);

        dataObject.put("Result", "OK");
        dataObject.put("response", objNotaDebitoxml.getResponse());
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
