/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.servlets.contabilidad;

import com.google.common.reflect.TypeToken;
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
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetDocCompraVenta;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.negocio.contabilidad.DocumentoCompraVentaNEG;

public class sDocCompra extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.00");
    double total, importe, igv, subtotal, igvImporte, totalDoc = 0;
    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

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
            out.println("<title>Servlet sDocCompra</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sDocCompra at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("comprobarDetalleCompra")) {
                comprobarDetalleCompra();
            }
            if (action.equalsIgnoreCase("quitarDetalleCompra")) {
                quitarDetalleCompra();
            }
//            if (action.equalsIgnoreCase("registrarDocCompra")) {
//                registrarCompra();
//            }
            if (action.equalsIgnoreCase("anularCompra")) {
                anularCompra();
            }
            if (action.equalsIgnoreCase("limpiarSession")) {
                limpiarSession();
            }
            if (action.equalsIgnoreCase("registrarDocumentoCompra")) {
                registrarDocumentoCompra();
            }
            if (action.equalsIgnoreCase("buscarDocumentoCompraVenta")) {
                buscarDocumentoCompraVenta();
            }

        } catch (Exception ex) {

            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            response.getWriter().write(json);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void comprobarDetalleCompra() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoFactura");

        if (detalleFactura == null || detalleFactura.isEmpty()) {
            detalleFactura = new ArrayList<>();
            importe = 0.00;
            subtotal = 0.00;
            igv = 0.00;
            total = 0.00;
            totalDoc = 0.00;

        }

        importe = Double.parseDouble(varRequest.getParameter("importe"));
        igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
        subtotal = fijarNumero(subtotal + importe, 2);
        igv = fijarNumero(igv + igvImporte, 2);
        total = importe + igvImporte;
        totalDoc = subtotal + igv;

        lista.put("descripcion", varRequest.getParameter("servicio"));
        lista.put("importe", varRequest.getParameter("importe"));
        lista.put("igv", varRequest.getParameter("igv"));
        lista.put("total", String.valueOf(total));

        detalleFactura.add(lista);
        ses.setAttribute("detalladoFactura", detalleFactura);

        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", detalleFactura);
        dataObject.put("total", totalDoc);
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

    private void quitarDetalleCompra() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String idTipoServicio = varRequest.getParameter("servicio");

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoFactura");
        ArrayList<HashMap<String, String>> nuevaDetalleFactura = new ArrayList<>();

        int band = 0;
        for (int i = 0; i < detalleFactura.size(); i++) {

            if (idTipoServicio.compareTo(detalleFactura.get(i).get("descripcion")) != 0) {
                nuevaDetalleFactura.add(detalleFactura.get(i));

            } else {
                importe = Double.parseDouble(varRequest.getParameter("importe"));
                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));

                subtotal = fijarNumero(subtotal - importe, 2);
                igv = fijarNumero(igv - igvImporte, 2);
                totalDoc = fijarNumero(subtotal + igv, 2);
                total = 0;

            }
            band = 1;

        }
        if (band == 1 && nuevaDetalleFactura.size() > 0) {

            lista = nuevaDetalleFactura.get(0);
            nuevaDetalleFactura.remove(0);
            nuevaDetalleFactura.add(lista);
        }

        ses.setAttribute("detalladoFactura", nuevaDetalleFactura);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalleFactura", nuevaDetalleFactura);
        dataObject.put("total", totalDoc);
        dataObject.put("igv", igv);
        dataObject.put("subtotal", subtotal);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

//    private void registrarCompra() throws Exception {
//        HttpSession sesion = varRequest.getSession();
//        DocCompraVenta objDocCompraVenta = new DocCompraVenta();
//        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
//
//        objDocumentoGenerado.setTipMoneda(varRequest.getParameter("tipoMoneda"));
//        objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
//        objDocCompraVenta.getObjEmisor().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
//        objDocCompraVenta.getObjEjecutor().setIdPersona("0000001");//Emisor Comunidad Campesina Llacuabamba
//        objDocCompraVenta.getObjTipoDocumento().setIdTipoComprobante(varRequest.getParameter("tipoComprobante"));
//        objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));
//        objDocumentoGenerado.setIdDoc("0003");//Parametro RegistroCompra
//        objDocCompraVenta.setDocumentoNro(varRequest.getParameter("numFactura"));
//        objDocumentoGenerado.setGlosaFija(varRequest.getParameter("comprobante") + "/" + varRequest.getParameter("numFactura"));
//
//        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
//
//        ArrayList<HashMap<String, String>> listaDetalleFactura = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoFactura");
//        HashMap<String, String> result = new HashMap<>();
//
//        result = DocumentoCompraVentaNEG.Instancia().registrarDocumentoCompra(objDocumentoGenerado, objDocCompraVenta, objUsuario, listaDetalleFactura);
//
//        dataObject = new HashMap<>();
//        dataObject.put("Result", "OK");
//        dataObject.put("result", result);
//        json = gson.toJson(dataObject);
//        varResponse.getWriter().write(json);
//
//    }

    private void anularCompra() throws Exception {
        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
        objDocumentoGenerado.getObjOficina().setIdOficina(varRequest.getParameter("idoficina"));
        objDocumentoGenerado.setIdDoc(varRequest.getParameter("IdDoc"));
        objDocumentoGenerado.getObjTipoMoneda().setId(varRequest.getParameter("TipMoneda"));
        objDocumentoGenerado.setNroDoc(varRequest.getParameter("NroDoc"));
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");

        Boolean respuesta = DocumentoCompraVentaNEG.Instancia().anularDocCompraVenta(objDocumentoGenerado, objUsuario);

        dataObject.put("resultado", String.valueOf(respuesta));
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void limpiarSession() throws IOException {
        HttpSession ses = varRequest.getSession(true);
        ses.removeAttribute("detalladoFactura");
    }
    
    
    
    private void registrarDocumentoCompra() throws Exception {
        HttpSession sesion = varRequest.getSession();
        DocCompraVenta objDocCompraVenta = new DocCompraVenta();
        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        objDocumentoGenerado.getObjTipoMoneda().setId(varRequest.getParameter("tipoMoneda"));
        objDocumentoGenerado.getObjPersona().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
        objDocCompraVenta.getObjEmisor().setIdPersona(varRequest.getParameter("idPersonaEmisor"));
        objDocCompraVenta.getObjEjecutor().setIdPersona("0000001");//Emisor Comunidad Campesina Llacuabamba
        objDocCompraVenta.getObjTipoDocumento().setIdTipoComprobante(varRequest.getParameter("tipoComprobante"));
        objDocumentoGenerado.setFechaDocumento(varRequest.getParameter("fechaDoc"));
        objDocumentoGenerado.setIdDoc("0003");//Parametro RegistroCompra
        objDocCompraVenta.setDocumentoNro(varRequest.getParameter("numDoc"));
        objDocumentoGenerado.setGlosaFija(varRequest.getParameter("desComprobante") + "/" + varRequest.getParameter("numDoc"));

        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        
        
        Gson gson1 = new Gson();
        String detalle = varRequest.getParameter("listaDetalle");
        java.lang.reflect.Type listaDetalle = new TypeToken<ArrayList<DetDocCompraVenta>>() {
            }.getType();
        ArrayList<DetDocCompraVenta> detalleDocCompra= gson1.fromJson(detalle, listaDetalle);
        

        HashMap<String, String> result = new HashMap<>();

        result = DocumentoCompraVentaNEG.Instancia().registrarDocumentoCompra(objDocumentoGenerado, objDocCompraVenta, objUsuario, detalleDocCompra);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("result", result);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }
    
    private void buscarDocumentoCompraVenta() throws Exception {
        
        String tipo=varRequest.getParameter("tipo");
        String nroDoc=varRequest.getParameter("nroDoc");
        String fecIni=varRequest.getParameter("fecIni");
        String fecFin=varRequest.getParameter("fecFin");


        List<ListaDocumentoCompraVenta> listaDocumentosCompraVenta= new ArrayList<>();

        listaDocumentosCompraVenta = DocumentoCompraVentaNEG.Instancia().buscarDocumentoCompraVenta(tipo, nroDoc,  fecIni, fecFin);

        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("listaDocumentosCompraVenta", listaDocumentosCompraVenta);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }
    
    

}
