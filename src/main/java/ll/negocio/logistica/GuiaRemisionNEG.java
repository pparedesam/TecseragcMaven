package ll.negocio.logistica;


import java.util.HashMap;
import java.util.Map;
import ll.accesodatos.logistica.GuiaRemisionDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetalleGuiaRemision;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.contabilidad.UnidadMedida;
import ll.entidades.logistica.ModalidadTraslado;
import ll.entidades.logistica.MotivoTraslado;
import ll.entidades.operaciones.DocumentoGenerado;
import java.util.ArrayList;
import java.util.List;
import ll.entidades.logistica.GuiaDetalle;


public class GuiaRemisionNEG {

    private static GuiaRemisionNEG _Instancia;

    private GuiaRemisionNEG() {
    }

    public static GuiaRemisionNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new GuiaRemisionNEG();
        }
        return _Instancia;
    }

    public List<UnidadMedida> listarUnidadMedida() throws Exception {
        return GuiaRemisionDAO.Instancia().listarUnidadMedida();
    }

    public List<MotivoTraslado> listarMotivosTraslado() throws Exception {
        return GuiaRemisionDAO.Instancia().listarMotivosTraslado();
    }

    public List<ModalidadTraslado> listarModalidadTraslado() throws Exception {
        return GuiaRemisionDAO.Instancia().listarModalidadTraslado();
    } 
    
    /*
    public HashMap<String, String> registrarGuiaRemision(DocumentoGenerado objDocumentoGenerado, GuiaRemision objGuiaRemision, Usuario objUsuario, ArrayList<HashMap<String, String>> listDetalleGRM)
            throws Exception {

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";
        int linea = 0;

        try {
            listadoc = GuiaRemisionDAO.Instancia().registrarGuiaRemision(objDocumentoGenerado, objUsuario);

            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                } else if (e.getKey().equals("nroGRM")) {
                    objGuiaRemision.setNroGuia(e.getValue().toString());
                }
            }
            result = GuiaRemisionDAO.Instancia().registrarGRMDetalle(objDocumentoGenerado, objGuiaRemision, objUsuario);

            for (int i = 0; i < listDetalleGRM.size(); i++) {

                DetalleGuiaRemision objDetalleGuiaRemision = new DetalleGuiaRemision();

                objDetalleGuiaRemision.setCantidad(Double.parseDouble(listDetalleGRM.get(i).get("cantidad")));
                objDetalleGuiaRemision.setDescripcion(listDetalleGRM.get(i).get("descripcion"));
                objDetalleGuiaRemision.getObjUnidadMedida().setCodigoMedida(listDetalleGRM.get(i).get("uniMedida"));

                objDetalleGuiaRemision.setObjUsuario(objUsuario);

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());

                result = GuiaRemisionDAO.Instancia().registrarDetalleGuiaRemision(objDocumentoGenerado, objDetalleGuiaRemision, objUsuario, i);

            }
            lista.put("Message", result);
            
            generarPDF();
        } catch (Exception e) {
            throw e;
        }

        return lista;

    }*/

    public List<ListaDocumentoCompraVenta> listarGuiaRemision(int criterio, String valor) throws Exception {
        return GuiaRemisionDAO.Instancia().listarGuiaRemision(criterio, valor);
    }
    

    public List<ListaDocumentoCompraVenta> buscarGuiaRemision(int criterio, String valor) throws Exception {
        return GuiaRemisionDAO.Instancia().buscarGuiaRemision(criterio, valor);
    }
    
    public List<ListaDocumentoCompraVenta> buscarDocumentoGuiaRemision(String nroDoc, String fecIni, String fecFin) throws Exception {
        return GuiaRemisionDAO.Instancia().buscarDocumentoGuiaRemision(nroDoc, fecIni,fecFin);
    }
    
    public HashMap<String, String> registrarGuiaRemision(DocumentoGenerado objDocumentoGenerado, GuiaRemision objGuiaRemision, Usuario objUsuario, ArrayList<GuiaDetalle> listDetalleGRM)
            throws Exception {

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";
     

        try {
            listadoc = GuiaRemisionDAO.Instancia().registrarGuiaRemision(objDocumentoGenerado, objUsuario);

            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                } else if (e.getKey().equals("nroGRM")) {
                    objGuiaRemision.setNroGuia(e.getValue().toString());
                }
            }
            result = GuiaRemisionDAO.Instancia().registrarGRMDetalle(objDocumentoGenerado, objGuiaRemision, objUsuario);

            for (int i = 0; i < listDetalleGRM.size(); i++) {

                DetalleGuiaRemision objDetalleGuiaRemision = new DetalleGuiaRemision();

                objDetalleGuiaRemision.setCantidad(Double.parseDouble(listDetalleGRM.get(i).getCantidad()));
                objDetalleGuiaRemision.setDescripcion(listDetalleGRM.get(i).getDescripcion());
                objDetalleGuiaRemision.getObjUnidadMedida().setCodigoMedida(listDetalleGRM.get(i).getUniMedida());

                objDetalleGuiaRemision.setObjUsuario(objUsuario);

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());

                result = GuiaRemisionDAO.Instancia().registrarDetalleGuiaRemision(objDocumentoGenerado, objDetalleGuiaRemision, objUsuario, i);

            }
            lista.put("Message", result);
            
          
        } catch (Exception e) {
            throw e;
        }

        return lista;

    }
    
}
