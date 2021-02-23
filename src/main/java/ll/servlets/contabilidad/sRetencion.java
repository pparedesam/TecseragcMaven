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

/**
 *
 * @author renrio
 */
public class sRetencion extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.00");
    //double total, importe, igv, subtotal, igvImporte, cantidad, precioU, totalImp, valorunitario = 0;
    double totalRetencion, totalImporteNeto, retencion, importeNeto = 0;
    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json;
    PrintWriter out = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sRetencion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sRetencion at " + request.getContextPath() + "</h1>");
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

            if (action.equalsIgnoreCase("comprobarDetalleRetencion")) {
                comprobarDetalleRetencion();
            } else if (action.equalsIgnoreCase("quitarDetalleRetencion")) {
                quitarDetalleRetencion();
            } else if (action.equalsIgnoreCase("limpiarSession")) {
                limpiarSessionRetencion();
            }
        } catch (Exception ex) {

            dataObject.put("Result", "error");
            dataObject.put("Message", ex.getMessage());
            json = gson.toJson(dataObject);
            response.getWriter().write(json);
        }
    }

    private void comprobarDetalleRetencion() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();

        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleRetencion = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoRetencion");

        String idTipDocumento = varRequest.getParameter("idTipDocumento");
        String nroSerie = varRequest.getParameter("nroSerie");
        String nroDoc = varRequest.getParameter("nroDoc");

        //totalImp = 0;
        if (detalleRetencion == null || detalleRetencion.isEmpty()) {
            detalleRetencion = new ArrayList<>();
//            precioU = 0.00;
//            cantidad = 0.00;
//            importe = 0.00;
//            subtotal = 0.00;
//            igv = 0.00;
//            valorunitario = 0.00;
//
//            valorunitario = Double.parseDouble(varRequest.getParameter("valorunitario"));
//            importe = Double.parseDouble(varRequest.getParameter("importe"));
//            igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
//            subtotal = fijarNumero(subtotal + importe, 2);
//            igv = fijarNumero(igv + igvImporte, 2);
//
//            totalImp = importe + igvImporte;
//            total = subtotal + igv;

            retencion = Double.parseDouble(varRequest.getParameter("importeRetencion"));
            importeNeto = Double.parseDouble(varRequest.getParameter("importeNeto"));

            totalRetencion = totalRetencion + retencion;
            totalImporteNeto = totalImporteNeto + importeNeto;

            lista.put("idTipDocumento", varRequest.getParameter("idTipDocumento"));
            lista.put("tipoDocumento", varRequest.getParameter("tipoDocumento"));
            lista.put("nroSerie", varRequest.getParameter("nroSerie"));
            lista.put("nroDoc", varRequest.getParameter("nroDoc"));
            lista.put("fechaEmisionDoc", varRequest.getParameter("fechaEmisionDoc"));
            lista.put("totalComprobante", varRequest.getParameter("totalComprobante"));
            lista.put("nroPago", varRequest.getParameter("nroPago"));
            lista.put("importePago", varRequest.getParameter("importePago"));
            lista.put("regimenRetencion", varRequest.getParameter("regimenRetencion"));
            lista.put("importeRetencion", varRequest.getParameter("importeRetencion"));
            lista.put("importeNeto", varRequest.getParameter("importeNeto"));

            detalleRetencion.add(lista);
            ses.setAttribute("detalladoRetencion", detalleRetencion);
            dataObject.put("Result", "OK");

        } else {
            int i = 0;
            while (detalleRetencion.size() > i) {
                String a = (detalleRetencion.get(i).get("idTipDocumento"));
                String b = (detalleRetencion.get(i).get("nroDoc"));
                String c = (detalleRetencion.get(i).get("nroSerie"));

                if (idTipDocumento.equalsIgnoreCase(a) && nroDoc.equalsIgnoreCase(b) && nroSerie.equalsIgnoreCase(c)) {
                    i = -1;
                    dataObject.put("Result", "KO");
                    break;
                }
                i++;
            }
            if (i >= 0) {
//                valorunitario = Double.parseDouble(varRequest.getParameter("valorunitario"));
//                importe = Double.parseDouble(varRequest.getParameter("importe"));
//                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
//                subtotal = fijarNumero(subtotal + importe, 2);
//                igv = fijarNumero(igv + igvImporte, 2);
//
//                totalImp = importe + igvImporte;
//                total = subtotal + igv;

                lista.put("idTipDocumento", varRequest.getParameter("idTipDocumento"));
                lista.put("tipoDocumento", varRequest.getParameter("tipoDocumento"));
                lista.put("nroSerie", varRequest.getParameter("nroSerie"));
                lista.put("nroDoc", varRequest.getParameter("nroDoc"));
                lista.put("fechaEmisionDoc", varRequest.getParameter("fechaEmisionDoc"));
                lista.put("totalComprobante", varRequest.getParameter("totalComprobante"));
                lista.put("nroPago", varRequest.getParameter("nroPago"));
                lista.put("importePago", varRequest.getParameter("importePago"));
                lista.put("regimenRetencion", varRequest.getParameter("regimenRetencion"));
                lista.put("importeRetencion", varRequest.getParameter("importeRetencion"));
                lista.put("importeNeto", varRequest.getParameter("importeNeto"));

                detalleRetencion.add(lista);
                ses.setAttribute("detalladoRetencion", detalleRetencion);
                dataObject.put("Result", "OK");
            }

        }

        dataObject.put("detalleRetencion", detalleRetencion);
//        dataObject.put("total", total);
//        dataObject.put("igv", igv);
//        dataObject.put("subtotal", subtotal);
        dataObject.put("totalRetencion", totalRetencion);
        dataObject.put("totalImporteNeto", totalImporteNeto);
        json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void quitarDetalleRetencion() throws IOException {

        HashMap<String, String> lista = new HashMap<>();
        HttpSession ses = varRequest.getSession();
        String idTipDocumento = varRequest.getParameter("idTipDocumento");
        String nroSerie = varRequest.getParameter("nroSerie");
        String nroDoc = varRequest.getParameter("nroDoc");
        HttpSession sesion = varRequest.getSession();
        ArrayList<HashMap<String, String>> detalleRetencion = (ArrayList<HashMap<String, String>>) sesion.getAttribute("detalladoRetencion");
        ArrayList<HashMap<String, String>> nuevaDetalleRetencion = new ArrayList<>();

        int band = 0;
        for (int i = 0; i < detalleRetencion.size(); i++) {
            String a = idTipDocumento + nroSerie + nroDoc;
            if (a.compareTo(detalleRetencion.get(i).get("idTipDocumento") + detalleRetencion.get(i).get("nroSerie") + detalleRetencion.get(i).get("nroDoc")) != 0) {
                nuevaDetalleRetencion.add(detalleRetencion.get(i));

            } else {

//                importe = Double.parseDouble(varRequest.getParameter("importe"));
//                igvImporte = Double.parseDouble(varRequest.getParameter("igv"));
//                subtotal = fijarNumero(subtotal - importe, 2);
//                igv = fijarNumero(igv - igvImporte, 2);
//                total = fijarNumero(subtotal + igv, 2);
            }
            band = 1;
        }
        if (band == 1 && nuevaDetalleRetencion.size() > 0) {

            lista = nuevaDetalleRetencion.get(0);
            nuevaDetalleRetencion.remove(0);
            nuevaDetalleRetencion.add(lista);
        }

        ses.setAttribute("detalladoRetencion", nuevaDetalleRetencion);
        dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("detalleRetencion", nuevaDetalleRetencion);
//        dataObject.put("total", total);
//        dataObject.put("igv", igv);
//        dataObject.put("subtotal", subtotal);
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

    private void limpiarSessionRetencion() throws IOException {
        HttpSession ses = varRequest.getSession();
        ses.removeAttribute("detalladoRetencion");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
