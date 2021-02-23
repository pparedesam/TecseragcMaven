package ll.negocio.logistica;


import java.util.HashMap;
import java.util.Map;
import ll.accesodatos.logistica.GuiaRemisionManualDAO;
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


public class GuiaRemisionManualNEG {

    private static GuiaRemisionManualNEG _Instancia;

    private GuiaRemisionManualNEG() {
    }

    public static GuiaRemisionManualNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new GuiaRemisionManualNEG();
        }
        return _Instancia;
    }

    public List<UnidadMedida> listarUnidadMedida() throws Exception {
        return GuiaRemisionManualDAO.Instancia().listarUnidadMedida();
    }

    public List<MotivoTraslado> listarMotivosTraslado() throws Exception {
        return GuiaRemisionManualDAO.Instancia().listarMotivosTraslado();
    }

    public List<ModalidadTraslado> listarModalidadTraslado() throws Exception {
        return GuiaRemisionManualDAO.Instancia().listarModalidadTraslado();
    } 
   
    public List<ListaDocumentoCompraVenta> listarGuiaRemision(int criterio, String valor) throws Exception {
        return GuiaRemisionManualDAO.Instancia().listarGuiaRemision(criterio, valor);
    }
    
    
    private void generarPDF() throws Exception {
        System.out.println("Esto sale en pantalla\n");
    }
    
    public List<ListaDocumentoCompraVenta> buscarGuiaRemision(int criterio, String valor) throws Exception {
        return GuiaRemisionManualDAO.Instancia().buscarGuiaRemision(criterio, valor);
    }
    
    public HashMap<String, String> registrarGuiaRemision(DocumentoGenerado objDocumentoGenerado, GuiaRemision objGuiaRemision, Usuario objUsuario, ArrayList<GuiaDetalle> listDetalleGRM)
            throws Exception {

        HashMap<String, String> lista = new HashMap<>();
        HashMap<String, String> listadoc = new HashMap<>();
        String result = "ERROR";
         int linea = 0;

        try {
            listadoc = GuiaRemisionManualDAO.Instancia().registrarGuiaRemision(objDocumentoGenerado, objUsuario);

            for (Map.Entry e : listadoc.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    objDocumentoGenerado.setNroDoc(e.getValue().toString());
                }
            }
            result = GuiaRemisionManualDAO.Instancia().registrarGRMDetalle(objDocumentoGenerado, objGuiaRemision, objUsuario);

            for (int i = 0; i < listDetalleGRM.size(); i++) {

                DetalleGuiaRemision objDetalleGuiaRemision = new DetalleGuiaRemision();

                objDetalleGuiaRemision.setCantidad(Double.parseDouble(listDetalleGRM.get(i).getCantidad()));
                objDetalleGuiaRemision.setDescripcion(listDetalleGRM.get(i).getDescripcion());
                objDetalleGuiaRemision.getObjUnidadMedida().setCodigoMedida(listDetalleGRM.get(i).getUniMedida());

                objDetalleGuiaRemision.setObjUsuario(objUsuario);

                lista.put("nroDoc", objDocumentoGenerado.getNroDoc());

                result = GuiaRemisionManualDAO.Instancia().registrarDetalleGuiaRemision(objDocumentoGenerado, objDetalleGuiaRemision, objUsuario, i);

            }
            lista.put("Message", result);
            
            generarPDF();
        } catch (Exception e) {
            throw e;
        }

        return lista;

    }
    
}
