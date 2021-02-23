package ll.negocio.contabilidad;

import java.net.URL;
import java.net.URLConnection;
import ll.exc.ExcepcionPropia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ll.accesodatos.contabilidad.DocumentoCompraVentaDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetDocCompraVenta;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.Facturaxml;
import ll.entidades.xmlFacturacion.GuiaRemisionxml;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.entidades.xmlFacturacion.NotaDebitoxml;

/**
 *
 * @author RenRio
 */
public class DocumentoCompraVentaNEG {

    private static DocumentoCompraVentaNEG _Instancia;

    private DocumentoCompraVentaNEG() {
    }

    public static DocumentoCompraVentaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new DocumentoCompraVentaNEG();
        }
        return _Instancia;
    }

    public List<ListaDocumentoCompraVenta> listarDocVenta(int criterio, String valor) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().listarDocVenta(criterio, valor);
    }

    public List<ListaDocumentoCompraVenta> listarNotaCred(int criterio, String valor) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().listarNotaCred(criterio, valor);
    }

    public List<ListaDocumentoCompraVenta> listarLibro(String valor) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().listarLibro(valor);
    }

    public HashMap<String, String> registrarDocumentoCompra(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, ArrayList<DetDocCompraVenta> detalleDocCompra)
            throws Exception {

        HashMap<String, String> lista = new HashMap<>();

        String result = "ERROR";
        int linea = 0;

        try {
            objDocumentoGenerado.setNroDoc(DocumentoCompraVentaDAO.Instancia().registrarDocCompra(objDocumentoGenerado, objUsuario));

            for (int i = 0; i < detalleDocCompra.size(); i++) {

                //DetDocCompraVenta objDetDocCompraVenta = new DetDocCompraVenta();
                objDocCompraVenta.setTotal(detalleDocCompra.get(i).getMonto());

                //objDetDocCompraVenta.setIdDetGastoSunat(detalleDocCompra.get(i).getIdDetGastoSunat());
                objDocumentoGenerado.setGlosaVariable(detalleDocCompra.get(i).getGlosaVariable());

                linea = Integer.parseInt(DocumentoCompraVentaDAO.Instancia().registrarCompra(objDocumentoGenerado, objDocCompraVenta, objUsuario, i, detalleDocCompra.get(i)).trim());
                result = DocumentoCompraVentaDAO.Instancia().registrarCompraDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, detalleDocCompra.get(i));
//                for (int j = 0; j < 2; j++) {
//
//                    if (j == 0) {
//                        //objDetDocCompraVenta.setMonto(detalleDocCompra.get(i).getValorUnitario());
//                        //objDetDocCompraVenta.setIdDetGastoSunat("0001");
//                    } else {
//
//                        objDocCompraVenta.getListDetalleCpraVta().get(j).setMonto(detalleDocCompra.get(i).getIgv());
//                        objDocCompraVenta.getListDetalleCpraVta().get(j).setIdDetGastoSunat("1000");
//
//                    }
//                    result = DocumentoCompraVentaDAO.Instancia().registrarCompraDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, j, detalleDocCompra.get(i));
//
//                }

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
                lista.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());

            }
            lista.put("Message", result);

        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    public List<ListaDocumentoCompraVenta> listarDocCompra(int criterio, String valor) throws Exception, ExcepcionPropia {
        return DocumentoCompraVentaDAO.Instancia().listarDocCompra(criterio, valor);
    }

    public Boolean anularDocCompraVenta(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().anularDocumentoCompraVenta(objDocumentoGenerado, objUsuario);
    }

    public Boolean insertImgDocGenerado(String pathQR, String path417, Facturaxml objFactura) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().insertImgDocGenerado(pathQR, path417, objFactura);
    }

    public Boolean insertImgDocGeneradoGRM(String pathQR, String path417, GuiaRemisionxml objGuiaRemision) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().insertImgDocGeneradoGRM(pathQR, path417, objGuiaRemision);
    }

    public Boolean insertImgDocGeneradoNC(String pathQR, String path417, NotaCreditoxml objNotaCredito) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().insertImgDocGeneradoNC(pathQR, path417, objNotaCredito);
    }

    public Boolean insertImgDocGeneradoND(String pathQR, String path417, NotaDebitoxml objNotaDebito) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().insertImgDocGeneradoND(pathQR, path417, objNotaDebito);
    }

   
    public HashMap<String, String> registrarNotaCredito(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception, ExcepcionPropia {

        DocCompraVenta objDocCompraVenta = new DocCompraVenta();

        objDocCompraVenta.getObjEmisor().setIdPersona(objDocumentoGenerado.getObjEmisor().getIdPersona());
        objDocCompraVenta.getObjEjecutor().setIdPersona(objDocumentoGenerado.getObjPersona().getIdPersona());

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";

        int linea;

        try {
            listadoc = DocumentoCompraVentaDAO.Instancia().registrarNotaCredito(objDocumentoGenerado, objUsuario);
            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                } else if (e.getKey().equals("nroFact")) {
                    objDocumentoGenerado.setNroDocExt(e.getValue().toString());
                }
            }

            for (int i = 0; i < objDocumentoGenerado.getListDetalleDocCompraVenta().size(); i++) {
                DetDocCompraVenta objDetDocCompraVenta = new DetDocCompraVenta();

                objDocCompraVenta.setTotal(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getMonto());
                objDetDocCompraVenta.setIdDetGastoSunat(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getIdDetGastoSunat());
                objDocCompraVenta.getObjUnidadMedida().setCodigoMedida(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getUnidadMedidad());
                objDocCompraVenta.setPrecioUnitario(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getPrecioUnitario());
                objDocCompraVenta.setValorUnitario(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getValorUnitario());
                objDocCompraVenta.setCantidad(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getCantidad());
                objDocumentoGenerado.setGlosaVariable(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getGlosaVariable());

                linea = Integer.parseInt(DocumentoCompraVentaDAO.Instancia().registrarNotaCredito(objDocumentoGenerado, objDocCompraVenta, objUsuario, i, objDetDocCompraVenta).trim());

                result = DocumentoCompraVentaDAO.Instancia().registrarDocumentoVentaDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, objDocumentoGenerado.getListDetalleDocCompraVenta().get(i));
                /*for (int j = 0; j < 2; j++) {

                    if (j == 0) {
                        objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("Monto")));
                        objDetDocCompraVenta.setIdDetGastoSunat("0001");

                    } else {
                        objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("IGV")));
                        objDetDocCompraVenta.setIdDetGastoSunat("1000");
                    }
                    result = DocumentoCompraVentaDAO.Instancia().registrarVentaDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, j, objDetDocCompraVenta);

                }*/

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
                lista.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());

            }

            /**
             * for (int i = 0; i < listDetalleFact.size(); i++) {
             *
             * DetDocCompraVenta objDetDocCompraVenta = new DetDocCompraVenta();
             *
             * objDocCompraVenta.setTotal(Double.parseDouble(listDetalleFact.get(i).get("Total")));
             * objDetDocCompraVenta.setIdDetGastoSunat(listDetalleFact.get(i).get("IdDescripcion"));
             * objDocCompraVenta.getObjUnidadMedida().setCodigoMedida(listDetalleFact.get(i).get("UM"));
             * objDocCompraVenta.setPrecioUnitario(Double.parseDouble(listDetalleFact.get(i).get("PrecioUnitario")));
             * objDocCompraVenta.setValorUnitario(Double.parseDouble(listDetalleFact.get(i).get("ValorUnitario")));
             * objDocCompraVenta.setCantidad(Double.parseDouble(listDetalleFact.get(i).get("Cantidad")));
             * objDocumentoGenerado.setGlosaVariable(listDetalleFact.get(i).get("Descripcion"));
             *
             * linea =
             * Integer.parseInt(DocumentoCompraVentaDAO.Instancia().registrarNotaCredito(objDocumentoGenerado,
             * objDocCompraVenta, objUsuario, i, objDetDocCompraVenta).trim());
             *
             * for (int j = 0; j < 2; j++) {
             *
             * if (j == 0) {
             * objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("Monto")));
             * objDetDocCompraVenta.setIdDetGastoSunat("0001");
             *
             * } else {
             * objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("IGV")));
             * objDetDocCompraVenta.setIdDetGastoSunat("1000"); } result =
             * DocumentoCompraVentaDAO.Instancia().registrarVentaDetalle(objDocumentoGenerado,
             * i, objDocCompraVenta, objUsuario, j, objDetDocCompraVenta);
             *
             * }
             *
             * lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
             * lista.put("tipMoneda", objDocumentoGenerado.getTipMoneda());
             *
             * }
             */
            lista.put("Message", result);

        } catch (ExcepcionPropia c) {

            throw c;
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    public HashMap<String, String> registrarNotaDebito(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception, ExcepcionPropia {

        DocCompraVenta objDocCompraVenta = new DocCompraVenta();

        objDocCompraVenta.getObjEmisor().setIdPersona(objDocumentoGenerado.getObjEmisor().getIdPersona());
        objDocCompraVenta.getObjEjecutor().setIdPersona(objDocumentoGenerado.getObjPersona().getIdPersona());

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";

        int linea;

        try {
            listadoc = DocumentoCompraVentaDAO.Instancia().registrarNotaDebito(objDocumentoGenerado, objUsuario);
            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                } else if (e.getKey().equals("nroFact")) {
                    objDocumentoGenerado.setNroDocExt(e.getValue().toString());
                }
            }

            for (int i = 0; i < objDocumentoGenerado.getListDetalleDocCompraVenta().size(); i++) {
                DetDocCompraVenta objDetDocCompraVenta = new DetDocCompraVenta();

                objDocCompraVenta.setTotal(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getMonto());
                objDetDocCompraVenta.setIdDetGastoSunat(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getIdDetGastoSunat());
                objDocCompraVenta.getObjUnidadMedida().setCodigoMedida(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getUnidadMedidad());
                objDocCompraVenta.setPrecioUnitario(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getPrecioUnitario());
                objDocCompraVenta.setValorUnitario(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getValorUnitario());
                objDocCompraVenta.setCantidad(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getCantidad());
                objDocumentoGenerado.setGlosaVariable(objDocumentoGenerado.getListDetalleDocCompraVenta().get(i).getGlosaVariable());

                linea = Integer.parseInt(DocumentoCompraVentaDAO.Instancia().registrarNotaDebito(objDocumentoGenerado, objDocCompraVenta, objUsuario, i, objDetDocCompraVenta).trim());

                result = DocumentoCompraVentaDAO.Instancia().registrarDocumentoVentaDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, objDocumentoGenerado.getListDetalleDocCompraVenta().get(i));
                /*for (int j = 0; j < 2; j++) {

                    if (j == 0) {
                        objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("Monto")));
                        objDetDocCompraVenta.setIdDetGastoSunat("0001");

                    } else {
                        objDetDocCompraVenta.setMonto(Double.parseDouble(listDetalleFact.get(i).get("IGV")));
                        objDetDocCompraVenta.setIdDetGastoSunat("1000");
                    }
                    result = DocumentoCompraVentaDAO.Instancia().registrarVentaDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, j, objDetDocCompraVenta);

                }*/

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
                lista.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());

            }

            lista.put("Message", result);

        } catch (ExcepcionPropia c) {

            throw c;
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    public void deleteCatch(Map<String, Object> dataObject) {
        DocumentoCompraVentaDAO.Instancia().deleteCatch(dataObject);
    }

    public void deleteCatchNC(Map<String, Object> dataObject) {
        DocumentoCompraVentaDAO.Instancia().deleteCatchNC(dataObject);
    }

    public void deleteCatchGM(Map<String, Object> dataObject) {
        DocumentoCompraVentaDAO.Instancia().deleteCatchGM(dataObject);
    }

    public List<ListaDocumentoCompraVenta> buscarDocumentoCompraVenta(String tipo, String nroDoc, String fecIni, String fecFin) throws Exception {
        return DocumentoCompraVentaDAO.Instancia().buscarDocumentoCompraVenta(tipo, nroDoc, fecIni, fecFin);
    }

    public HashMap<String, String> registrarFacturaElectronica(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, ArrayList<DetDocCompraVenta> listaDetalleFactura, ArrayList<GuiaRemision> listaDetalleGuias) throws Exception, ExcepcionPropia {

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";
        int linea;
        try {
            listadoc = DocumentoCompraVentaDAO.Instancia().registrarDocVenta(objDocumentoGenerado, objUsuario);
            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                } else if (e.getKey().equals("nroFact")) {
                    objDocCompraVenta.setDocumentoNro(e.getValue().toString());
                }
            }

            for (int i = 0; i < listaDetalleFactura.size(); i++) {

                DetDocCompraVenta objDetDocCompraVenta = new DetDocCompraVenta();

                objDocCompraVenta.setTotal(listaDetalleFactura.get(i).getMonto());
                objDetDocCompraVenta.setIdDetGastoSunat(listaDetalleFactura.get(i).getIdDetGastoSunat());
                objDocumentoGenerado.setGlosaVariable(listaDetalleFactura.get(i).getGlosaVariable());
                objDocCompraVenta.getObjUnidadMedida().setCodigoMedida(listaDetalleFactura.get(i).getUnidadMedidad());
                objDocCompraVenta.setCantidad(listaDetalleFactura.get(i).getCantidad());
                objDocCompraVenta.setPrecioUnitario(listaDetalleFactura.get(i).getPrecioUnitario());
                objDocCompraVenta.setValorUnitario(listaDetalleFactura.get(i).getValorUnitario());

                linea = Integer.parseInt(DocumentoCompraVentaDAO.Instancia().registrarVenta(objDocumentoGenerado, objDocCompraVenta, objUsuario, i, objDetDocCompraVenta).trim());
                result = DocumentoCompraVentaDAO.Instancia().registrarDocumentoVentaDetalle(objDocumentoGenerado, i, objDocCompraVenta, objUsuario, listaDetalleFactura.get(i));
//                for (int j = 0; j < 2; j++) {
//
//                    if (j == 0) {
//                        objDetDocCompraVenta.setMonto(listaDetalleFactura.get(i).getValorUnitarioTotal());
//                        objDetDocCompraVenta.setIdDetGastoSunat("0001");
//
//                    } else {
//                        objDetDocCompraVenta.setMonto(listaDetalleFactura.get(i).getIgvTotal());
//                        objDetDocCompraVenta.setIdDetGastoSunat("1000");
//                    }
//                    
//
//                }

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
                lista.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());

            }
            if (listaDetalleGuias == null || listaDetalleGuias.isEmpty()) {
                lista.put("Message", result);

            } else {

                for (int i = 0; i < listaDetalleGuias.size(); i++) {

//                    GuiaRemision objGuiaRemision = new GuiaRemision();
//                    objGuiaRemision.setNroGuia(listDetalleFactGR.get(i).get("nroGuia"));
//                    objGuiaRemision.setFechaGuia(listDetalleFactGR.get(i).get("fechaGuia"));
//                    objGuiaRemision.setTipoGuia(listDetalleFactGR.get(i).get("tipoGuia"));
//                    objGuiaRemision.setIdTipoGuia(listDetalleFactGR.get(i).get("idTipoGuia"));
                    if (listaDetalleGuias.get(i).getTipo() == 1) {
                        result = DocumentoCompraVentaDAO.Instancia().registrarGuiaRemisionFactManual(objDocumentoGenerado, objUsuario, listaDetalleGuias.get(i));
                    } else if (listaDetalleGuias.get(i).getTipo() == 2) {
                        result = DocumentoCompraVentaDAO.Instancia().registrarGuiaRemisionFact(objDocumentoGenerado, objUsuario, listaDetalleGuias.get(i));
                    }
                }

            }
            lista.put("Message", result);

        } catch (ExcepcionPropia c) {

            throw c;
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    public DocumentoGenerado obtenerDocumenteGenerado(DocumentoGenerado objDocGenerado) throws Exception {
        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
        objDocumentoGenerado = DocumentoCompraVentaDAO.Instancia().obtenerDocumentoGenerado(objDocGenerado);
        objDocumentoGenerado.setListDetalleDocCompraVenta(DocumentoCompraVentaDAO.Instancia().listarDetalleDocumentoCompraVenta(objDocGenerado));
        return objDocumentoGenerado;
    }

    public DocumentoGenerado obtenerDocumenteGeneradoNota(DocumentoGenerado objDocGenerado) throws Exception {

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();
        objDocumentoGenerado = DocumentoCompraVentaDAO.Instancia().obtenerDocumentoGeneradoNota(objDocGenerado);
        objDocumentoGenerado.setListDetalleDocCompraVenta(DocumentoCompraVentaDAO.Instancia().listarDetalleDocumentoCompraVenta(objDocGenerado));
        objDocumentoGenerado.setListGuiaRemision(DocumentoCompraVentaDAO.Instancia().listarGuiaRemision(objDocGenerado));

        return objDocumentoGenerado;
    }

    public static String estadoConexion() {
        String estado;
        try {

            URL ruta = new URL("https://ose.nubefact.com/");
            URLConnection rutaC = ruta.openConnection();
            rutaC.connect();
            estado = "activo";
        } catch (Exception e) {

            estado = "desactivado";
        }

        return estado;
    }

}
