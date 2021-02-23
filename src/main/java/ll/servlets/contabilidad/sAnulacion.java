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
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.comprobantes.electronicos.doc.VoidedDocumentsUBL21;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.factura.CabeceraFact;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.DocumentoBajaxml;
import ll.exc.ExcepcionPropia;
import ll.negocio.contabilidad.DocumentoBajaNEG;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;
import ll.negocio.contabilidad.FacturaNEG;
import ll.negocio.mySQL.MySQLDocumentoCompraVentaNEG;

/**
 *
 * @author RenRio
 */
public class sAnulacion extends HttpServlet {

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
            out.println("<title>Servlet sAnulacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sAnulacion at " + request.getContextPath() + "</h1>");
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
            if (action.equalsIgnoreCase("obtenerCabFacturaAnulacion")) {
                obtenerCabFacturaAnulacion();
            }
            if (action.equalsIgnoreCase("baja")) {
                darBaja();
            }
        } catch (ExcepcionPropia ex) {
            DocumentoCompraVentaNEG.Instancia().deleteCatchNC(dataObject);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void obtenerCabFacturaAnulacion() throws Exception {
        String idDoc = varRequest.getParameter("idDoc");
        String nroFactura = varRequest.getParameter("nroFactura");
        HttpSession ses = varRequest.getSession();
        CabeceraFact objCabeceraFact;

        if (0 == 0) {
            objCabeceraFact = FacturaNEG.Instancia().obtenerCabFacturaAnulacion(nroFactura, idDoc);
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

    private void darBaja() throws Exception {
        HttpSession sesion = varRequest.getSession();
        DocumentoBajaxml objDocumentoBajaxml = new DocumentoBajaxml();

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        objDocumentoGenerado.getObjTipoMoneda().setId("0");
        objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaCliente"));
        objDocumentoGenerado.setIdDoc("0007");
        objDocumentoGenerado.setGlosaVariable(varRequest.getParameter("observacion"));

        objDocumentoGenerado.getObjRelaDoc().setIdOficinaR(varRequest.getParameter("idoficinaR"));
        objDocumentoGenerado.getObjRelaDoc().setIdDocR(varRequest.getParameter("idDocR"));
        objDocumentoGenerado.getObjRelaDoc().setTipMonedaR(varRequest.getParameter("tipMonedaR"));
        objDocumentoGenerado.getObjRelaDoc().setNroDocR(varRequest.getParameter("nroDocR"));
        objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fecha"));

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaEmisor"));

        HashMap<String, String> result = new HashMap<>();

        result = DocumentoBajaNEG.Instancia().registrarDocumentoBaja(objDocumentoGenerado, objUsuario);

        objDocumentoGenerado.setNroDoc(result.get("nroDoc"));
        objDocumentoGenerado.getObjTipoMoneda().setId("0");
        objDocumentoGenerado.setIdDoc("0007");
        objDocumentoGenerado.setObjOficina(objUsuario.getObjEmpleado().getObjOficina());

        objDocumentoBajaxml = MySQLDocumentoCompraVentaNEG.Instancia().obtenerDocumentoBajaXML(objDocumentoGenerado);

//        dataObject.put("nroDoc", objDocumentoBajaxml.getNroDoc());
//        dataObject.put("idDoc", objDocumentoBajaxml.getIdDoc());
//        dataObject.put("idOficina", objDocumentoBajaxml.getIdOfi());
//        dataObject.put("tipMoneda", objDocumentoBajaxml.getTipM());
//        dataObject.put("registro", 1);
        VoidedDocumentsUBL21.Instancia().c_XML(objDocumentoBajaxml);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("result", result);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }
}
