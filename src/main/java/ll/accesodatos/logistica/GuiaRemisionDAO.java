package ll.accesodatos.logistica;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.DetalleGuiaRemision;
import ll.entidades.contabilidad.ListaDocumentoCompraVenta;

import ll.entidades.contabilidad.UnidadMedida;
import ll.entidades.logistica.GuiaRemision;
import ll.entidades.logistica.ModalidadTraslado;
import ll.entidades.logistica.MotivoTraslado;
import ll.entidades.operaciones.DocumentoGenerado;

public class GuiaRemisionDAO {

    private static GuiaRemisionDAO _Instancia;

    private GuiaRemisionDAO() {
    }

    public static GuiaRemisionDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new GuiaRemisionDAO();
        }
        return _Instancia;
    }

    public List<UnidadMedida> listarUnidadMedida() throws Exception {

        List<UnidadMedida> listUnidadMedida = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select * from UnidadesMedida";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                UnidadMedida objUnidadMedida = new UnidadMedida();
                objUnidadMedida.setCodigoMedida(rs.getString("UM"));
                objUnidadMedida.setDescripcion(rs.getString("Descripcion"));
                listUnidadMedida.add(objUnidadMedida);
            }

        } catch (Exception e) {
            throw e;
        }

        return listUnidadMedida;
    }

    public List<MotivoTraslado> listarMotivosTraslado() throws Exception {

        List<MotivoTraslado> listMotivosTraslado = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select * from MotivosTraslado";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                MotivoTraslado objMotivosTraslado = new MotivoTraslado();
                objMotivosTraslado.setCodigo(rs.getString("Codigo"));
                objMotivosTraslado.setDescripcion(rs.getString("Descripcion"));
                listMotivosTraslado.add(objMotivosTraslado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listMotivosTraslado;
    }

    public HashMap<String, String> registrarGuiaRemision(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        String result = "ERROR";
        String result1 = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Logistica_Insert_DocGenerado_GuiaRemisionE(?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(3, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(4, objDocumentoGenerado.getIdDoc());
            cs.setString(5, objDocumentoGenerado.getFechaDocumento());
            cs.setString(6, "GUIA REMISION ELECTRONICA");
            cs.setString(7, objDocumentoGenerado.getGlosaVariable());
            cs.setString(8, objUsuario.getIdUsuario());
            cs.setString(9, result);
            cs.setString(10, result1);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(9);
            result1 = cs.getString(10);

        } catch (SQLException e) {
            e.getMessage();
        }

        lista.put("nroDoc", result);
        lista.put("nroGRM", result1);

        return lista;
    }

    public String registrarDetalleGuiaRemision(DocumentoGenerado objDocumentoGenerado, DetalleGuiaRemision objDetalleGuiaRemision, Usuario objUsuario, int i) throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Logistica_Insert_DetalleGuiaRemision(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getIdDoc());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setInt(5, i);
            cs.setDouble(6, objDetalleGuiaRemision.getCantidad());
            cs.setString(7, objDetalleGuiaRemision.getObjUnidadMedida().getCodigoMedida());
            cs.setString(8, objDetalleGuiaRemision.getDescripcion());
            cs.setString(9, objUsuario.getIdUsuario());
            cs.setString(10, result);

            cs.registerOutParameter(10, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(10);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarGRMDetalle(DocumentoGenerado objDocumentoGenerado, GuiaRemision objGuiaRemision, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Logistica_Insert_DocGenerado_GuiaRemisionElect(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getIdDoc());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(5, objGuiaRemision.getNroGuia());
            cs.setString(6, objGuiaRemision.getFechaGuia());
            cs.setString(7, objGuiaRemision.getIdTipoGuia());
            cs.setString(8, objGuiaRemision.getObjPersonaRemitente().getIdPersona());
            cs.setString(9, objGuiaRemision.getObjPersonaDestinatario().getIdPersona());
            cs.setString(10, objGuiaRemision.getObjPersonaTransportista().getIdPersona());
            cs.setString(11, objGuiaRemision.getNroPlacaTransportista());
            cs.setString(12, objGuiaRemision.getObjPersonaConductor().getIdPersona());
            cs.setString(13, objGuiaRemision.getObjUbigeoLlegada().getCodigo());
            cs.setString(14, objGuiaRemision.getDireccionLlegada());
            cs.setString(15, objGuiaRemision.getObjUbigeoPartida().getCodigo());
            cs.setString(16, objGuiaRemision.getDireccionPartida());
            cs.setString(17, objGuiaRemision.getObjMotivosTraslado().getCodigo());
            cs.setString(18, objGuiaRemision.getObjMotivosTraslado().getCodigo());
            cs.setDouble(19, objGuiaRemision.getPesoBruto());
            cs.setString(20, objGuiaRemision.getObjUnidadMedida().getCodigoMedida());
            cs.setString(21, objGuiaRemision.getFechaIniTraslado());
            cs.setString(22, objGuiaRemision.getFechaEntrega());
            cs.setString(23, objUsuario.getIdUsuario());
            cs.setString(24, result);
            cs.registerOutParameter(24, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(24);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public List<ModalidadTraslado> listarModalidadTraslado() throws Exception {

        List<ModalidadTraslado> listModalidadTraslado = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select Codigo, Descripcion from MovilidadTraslado";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ModalidadTraslado objModalidadTraslado = new ModalidadTraslado();
                objModalidadTraslado.setCodigo(rs.getString("Codigo"));
                objModalidadTraslado.setDescripcion(rs.getString("Descripcion"));
                listModalidadTraslado.add(objModalidadTraslado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listModalidadTraslado;
    }

    public List<ListaDocumentoCompraVenta> listarGuiaRemision(int criterio, String valor) throws Exception {
        List<ListaDocumentoCompraVenta> listaDocCompraVenta = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String[] parts = valor.split("&");
            String CriterioSQL = null;
            switch (criterio) {

                case 1:

                    CriterioSQL = "dcv.FechaDoctoExt >= '" + parts[0] + "' and dcv.FechaDoctoExt<= '" + parts[1] + "'";
                    break;
                case 2:
                    CriterioSQL = "dcv.NroDocExt='" + valor + "'";

                    break;
                case 3:
                    CriterioSQL = "dcv.FechaDoctoExt >= '" + parts[0] + "' and dcv.FechaDoctoExt<= '" + parts[1] + "' and dg.TipMoneda='" + parts[2] + "'";
                    break;
            }

            String sql = "select distinct dg.idestdoc, dcv.NroDocExt,pj.RazonSocial, CONVERT(VARCHAR(10), dcv.FechaDoctoExt, 103) as FechaDoctoExt , \n"
                    + " tabd.Estado, CONVERT(VARCHAR(15), dcv.PesoBruto) + ' ' + dcv.IdUM as Total , dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina\n"
                    + "from DocGenerado dg \n"
                    + "join GuiaRemision dcv  \n"
                    + "on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina \n"
                    + "join PersonaJuridica pj on dg.IdPersona = pj.IdPersona \n"
                    + "join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc AND " + CriterioSQL + " where dg.IdDoc = '0005' and NroDocExt like 'T%' \n"
                    + "order by dcv.NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEjecutor(rs.getString("RazonSocial"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotalCarga(rs.getString("Total"));
                objListaDocumentoCompraVenta.setEstado(rs.getString("Estado"));
                objListaDocumentoCompraVenta.setNroDoc(rs.getString("nrodoc"));
                objListaDocumentoCompraVenta.setIdDoc(rs.getString("iddoc"));
                objListaDocumentoCompraVenta.setIdOficina(rs.getString("idOficina"));
                objListaDocumentoCompraVenta.setTipMoneda(rs.getString("tipMoneda"));
                listaDocCompraVenta.add(objListaDocumentoCompraVenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDocCompraVenta;

    }
    
    public List<ListaDocumentoCompraVenta> buscarGuiaRemision(int criterio, String valor) throws Exception {
        List<ListaDocumentoCompraVenta> listaDocCompraVenta = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String[] parts = valor.split("&");
            String CriterioSQL = null;
            switch (criterio) {

                case 1:
                    CriterioSQL = "dcv.NroDocExt='" + valor + "'";
                    
                    break;
                case 2:
                    CriterioSQL = "dcv.FechaDoctoExt >= '" + parts[0] + "' and dcv.FechaDoctoExt<= '" + parts[1] + "'";

                    break;
                
            }
            
            String sql ="select distinct \n" +
                        "	dg.idestdoc\n" +
                        "	,dcv.NroDocExt\n" +
                        "	,pj.RazonSocial\n" +
                        "	,CONVERT(VARCHAR(10), dcv.FechaDoctoExt, 103) as FechaDoctoExt \n" +
                        "	,tabd.Estado\n" +
                        "	, CONVERT(VARCHAR(15), dcv.PesoBruto) + ' ' + dcv.IdUM as Total \n" +
                        "	, dg.iddoc\n" +
                        "	, dg.nrodoc\n" +
                        "	, dg.tipMoneda\n" +
                        "	, dg.idOficina\n" +
                        "from \n" +
                        "	DocGenerado dg \n" +
                        "    join GuiaRemision dcv on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina\n" +
                        "    join PersonaJuridica pj on dg.IdPersona = pj.IdPersona\n" +
                        "    join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc \n" +
                        "	where \n" +
                        "	dg.IdDoc = '0005'\n" +
                        "	and "+  CriterioSQL + "\n" +
                        "	and NroDocExt like 'T%'\n" +
                        "order by \n" +
                        "	dcv.NroDocExt";

//            String sql = "select distinct dg.idestdoc, dcv.NroDocExt,pj.RazonSocial, CONVERT(VARCHAR(10), dcv.FechaDoctoExt, 103) as FechaDoctoExt , \n"
//                    + " tabd.Estado, CONVERT(VARCHAR(15), dcv.PesoBruto) + ' ' + dcv.IdUM as Total , dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina\n"
//                    + "from DocGenerado dg \n"
//                    + "join GuiaRemision dcv  \n"
//                    + "on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina \n"
//                    + "join PersonaJuridica pj on dg.IdPersona = pj.IdPersona \n"
//                    + "join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc AND " + CriterioSQL + " where dg.IdDoc = '0005' and NroDocExt like 'T%' \n"
//                    + "order by dcv.NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEjecutor(rs.getString("RazonSocial"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotalCarga(rs.getString("Total"));
                objListaDocumentoCompraVenta.setEstado(rs.getString("Estado"));
                objListaDocumentoCompraVenta.setNroDoc(rs.getString("nrodoc"));
                objListaDocumentoCompraVenta.setIdDoc(rs.getString("iddoc"));
                objListaDocumentoCompraVenta.setIdOficina(rs.getString("idOficina"));
                objListaDocumentoCompraVenta.setTipMoneda(rs.getString("tipMoneda"));
                listaDocCompraVenta.add(objListaDocumentoCompraVenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDocCompraVenta;

    }
    
    public List<ListaDocumentoCompraVenta> buscarDocumentoGuiaRemision(String nroDoc, String fecIni, String fecFin) throws Exception {
        
        List<ListaDocumentoCompraVenta> listaDocumentosCompraVenta = new ArrayList<>();
        
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Logistica_Buscar_GuiaRemision(?,?,?)}");
            cstmt.setString(1, nroDoc);
            cstmt.setString(2, fecIni);
            cstmt.setString(3, fecFin);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objDocCompraVenta = new ListaDocumentoCompraVenta();
                
                
                objDocCompraVenta.getObjTipComprobante().setIdDoc(rs.getString("IdDoc"));
                objDocCompraVenta.getObjTipComprobante().setDescripcion(rs.getString("tipoDocumento"));
                objDocCompraVenta.setIdDoc(rs.getString("idDoc"));
                objDocCompraVenta.setNroDoc(rs.getString("nroDoc"));
                objDocCompraVenta.setEjecutor(rs.getString("Emisor"));
                objDocCompraVenta.getObjMoneda().setId(rs.getString("tipMoneda"));
                objDocCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objDocCompraVenta.setFecha(rs.getString("Fecha"));
                objDocCompraVenta.setEstado(rs.getString("estado"));
                listaDocumentosCompraVenta.add(objDocCompraVenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDocumentosCompraVenta;
    }
}
