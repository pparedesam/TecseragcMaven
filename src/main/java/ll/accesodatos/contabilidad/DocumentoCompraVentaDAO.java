package ll.accesodatos.contabilidad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ll.accesodatos.agente.Connect;
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

public class DocumentoCompraVentaDAO {

    private static DocumentoCompraVentaDAO _Instancia;

    private DocumentoCompraVentaDAO() {
    }

    public static DocumentoCompraVentaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new DocumentoCompraVentaDAO();
        }
        return _Instancia;
    }

    public String registrarVenta(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, int i, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocumentoCompraVentaFactElect(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setInt(1, i);
            cs.setString(2, objDocCompraVenta.getDocumentoNro());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(5, objDocumentoGenerado.getIdDoc());
            cs.setString(6, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(7, objDocumentoGenerado.getFechaDocumento());
            cs.setDouble(8, objDocCompraVenta.getCantidad());
            cs.setString(9, objDetDocCompraVenta.getIdDetGastoSunat());
            cs.setDouble(10, objDocCompraVenta.getValorUnitario());
            cs.setString(11, objDocumentoGenerado.getGlosaVariable());
            cs.setString(12, objDocCompraVenta.getObjEmisor().getIdPersona());
            cs.setString(13, objDocCompraVenta.getObjEjecutor().getIdPersona());
            cs.setDouble(14, objDocCompraVenta.getTotal());
            cs.setString(15, objUsuario.getIdUsuario());
            cs.setString(16, "01");
            cs.setString(17, objDocCompraVenta.getObjUnidadMedida().getCodigoMedida());
            cs.setDouble(18, objDocCompraVenta.getPrecioUnitario());
            cs.setString(19, result);
            cs.registerOutParameter(19, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(19);

        } catch (SQLException e) {
            DocumentoCompraVentaDAO.Instancia().anularVentaCatch(objDocumentoGenerado);
            e.getMessage();
        }

        return result;
    }

    public String registrarNotaCredito(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, int i, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocumentoCompraVentaFactElect(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setInt(1, i);
            cs.setString(2, objDocumentoGenerado.getNroDocExt());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(5, objDocumentoGenerado.getIdDoc());
            cs.setString(6, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(7, objDocumentoGenerado.getFechaDocumento());
            cs.setDouble(8, objDocCompraVenta.getCantidad());
            cs.setString(9, objDetDocCompraVenta.getIdDetGastoSunat());
            cs.setDouble(10, objDocCompraVenta.getValorUnitario());
            cs.setString(11, objDocumentoGenerado.getGlosaVariable());
            cs.setString(12, objDocCompraVenta.getObjEmisor().getIdPersona());
            cs.setString(13, objDocCompraVenta.getObjEjecutor().getIdPersona());
            cs.setDouble(14, objDocCompraVenta.getTotal());
            cs.setString(15, objUsuario.getIdUsuario());
            cs.setString(16, "07");
            cs.setString(17, objDocCompraVenta.getObjUnidadMedida().getCodigoMedida());
            cs.setDouble(18, objDocCompraVenta.getPrecioUnitario());
            cs.setString(19, result);
            cs.registerOutParameter(19, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(19);

        } catch (SQLException e) {
            DocumentoCompraVentaDAO.Instancia().anularVentaCatch(objDocumentoGenerado);
            e.getMessage();
        }

        return result;
    }

    public String registrarNotaDebito(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, int i, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocumentoCompraVentaFactElect(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setInt(1, i);
            cs.setString(2, objDocumentoGenerado.getNroDocExt());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(5, objDocumentoGenerado.getIdDoc());
            cs.setString(6, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(7, objDocumentoGenerado.getFechaDocumento());
            cs.setDouble(8, objDocCompraVenta.getCantidad());
            cs.setString(9, objDetDocCompraVenta.getIdDetGastoSunat());
            cs.setDouble(10, objDocCompraVenta.getValorUnitario());
            cs.setString(11, objDocumentoGenerado.getGlosaVariable());
            cs.setString(12, objDocCompraVenta.getObjEmisor().getIdPersona());
            cs.setString(13, objDocCompraVenta.getObjEjecutor().getIdPersona());
            cs.setDouble(14, objDocCompraVenta.getTotal());
            cs.setString(15, objUsuario.getIdUsuario());
            cs.setString(16, "08");
            cs.setString(17, objDocCompraVenta.getObjUnidadMedida().getCodigoMedida());
            cs.setDouble(18, objDocCompraVenta.getPrecioUnitario());
            cs.setString(19, result);
            cs.registerOutParameter(19, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(19);

        } catch (SQLException e) {
            DocumentoCompraVentaDAO.Instancia().anularVentaCatch(objDocumentoGenerado);
            e.getMessage();
        }

        return result;
    }

    public String registrarVentaDetalle(DocumentoGenerado objDocumentoGenerado, int i, DocCompraVenta objDocCompraVenta, Usuario objUsuario, int j, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DCV_Detalle(?,?,?,?,?,?,?,?,?,?)}");

            cs.setInt(1, j);
            cs.setString(2, objDocumentoGenerado.getNroDoc());
            cs.setString(3, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(5, objDocumentoGenerado.getIdDoc());
            cs.setString(6, objDetDocCompraVenta.getIdDetGastoSunat());
            cs.setDouble(7, objDetDocCompraVenta.getMonto());
            cs.setString(8, objUsuario.getIdUsuario());
            cs.setInt(9, i);
            cs.setString(10, result);
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(10);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarDocumentoVentaDetalle(DocumentoGenerado objDocumentoGenerado, int i, DocCompraVenta objDocCompraVenta, Usuario objUsuario, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DCV_DetalleFE(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocumentoGenerado.getIdDoc());
            cs.setString(2, objDocumentoGenerado.getNroDoc());
            cs.setString(3, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setDouble(5, objDetDocCompraVenta.getValorUnitarioTotal());
            cs.setDouble(6, objDetDocCompraVenta.getIgvTotal());
            cs.setDouble(7, i);
            cs.setString(8, objUsuario.getIdUsuario());
            cs.setString(9, result);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(9);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarGuiaRemisionFactManual(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario, GuiaRemision objGuiaRemision) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_GR_Rela_FacturaManual(?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(3, "0");
            cs.setString(4, objGuiaRemision.getNroGuia());
            cs.setString(5, objGuiaRemision.getFechaGuia());
            cs.setString(6, objGuiaRemision.getIdTipoGuia());
            cs.setString(7, objUsuario.getIdUsuario());
            cs.setString(8, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(9, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(10, objDocumentoGenerado.getIdDoc());
            cs.setString(11, objDocumentoGenerado.getNroDoc());

            cs.setString(12, result);
            cs.registerOutParameter(12, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(12);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarGuiaRemisionFact(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario, GuiaRemision objGuiaRemision) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_GR_Rela_FacturaElect(?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objGuiaRemision.getIdOficina());
            cs.setString(2, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(3, objGuiaRemision.getTipMoneda());
            cs.setString(4, objGuiaRemision.getNroDoc());
            cs.setString(5, objGuiaRemision.getFechaGuia());
            cs.setString(6, objGuiaRemision.getIdDoc());
            cs.setString(7, objGuiaRemision.getFechaGuia());
            cs.setString(8, objGuiaRemision.getIdTipoGuia());
            cs.setString(9, objUsuario.getIdUsuario());
            cs.setString(10, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(11, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(12, objDocumentoGenerado.getIdDoc());
            cs.setString(13, objDocumentoGenerado.getNroDoc());

            cs.setString(14, result);
            cs.registerOutParameter(14, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(12);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean anularVenta(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE DocGenerado\n"
                    + "   SET \n"
                    + "      [IdEstDoc] = '05'\n"
                    + "      ,[FechaCambioEstado] = CONVERT(CHAR(10),GETDATE(),103)\n"
                    + "      ,[HoraCambioEstado] = RIGHT( '0'+LTRIM(RIGHT(CONVERT(CHAR(19),GETDATE()),7)),7)\n"
                    + "      ,[IdUsuario] = '" + objUsuario.getIdUsuario() + "' "
                    + " where IdDoc='" + objDocumentoGenerado.getIdDoc() + "' and NroDoc ='" + objDocumentoGenerado.getNroDoc() + "' and IdOficina='" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and TipMoneda='" + objDocumentoGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public Boolean anularVentaCatch(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE DocGenerado\n"
                    + "   SET \n"
                    + "      [IdEstDoc] = '05'\n"
                    + "      ,[FechaCambioEstado] = CONVERT(CHAR(10),GETDATE(),103)\n"
                    + "      ,[HoraCambioEstado] = RIGHT( '0'+LTRIM(RIGHT(CONVERT(CHAR(19),GETDATE()),7)),7)\n"
                    + "      ,[IdUsuario] = 'SerVer' "
                    + " where IdDoc='" + objDocumentoGenerado.getIdDoc() + "' and NroDoc ='" + objDocumentoGenerado.getNroDoc() + "' and IdOficina='" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and TipMoneda='" + objDocumentoGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public Boolean insertImgDocGenerado(String pathQR, String path417, Facturaxml objFactura) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "  insert into imageDocumentoGenerado (IdDoc, NroDoc, IdOficina,TipMoneda,ImageBitsQR,ImageBits417) values \n"
                    + " ('" + objFactura.getIdDoc() + "','" + objFactura.getNroDoc() + "','" + objFactura.getIdOfi() + "','" + objFactura.getTipM() + "',(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + pathQR + "', \n"
                    + "  SINGLE_BLOB)MyImage),(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + path417 + "', \n"
                    + "  SINGLE_BLOB)MyImage))";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public Boolean insertImgDocGeneradoGRM(String pathQR, String path417, GuiaRemisionxml objGuiaRemisionxml) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "  insert into imageDocumentoGenerado (IdDoc, NroDoc, IdOficina,TipMoneda,ImageBitsQR,ImageBits417) values \n"
                    + " ('" + objGuiaRemisionxml.getIdDoc() + "','" + objGuiaRemisionxml.getNroDoc() + "','" + objGuiaRemisionxml.getIdOfi() + "','" + objGuiaRemisionxml.getTipM() + "',(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + pathQR + "', \n"
                    + "  SINGLE_BLOB)MyImage),(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + path417 + "', \n"
                    + "  SINGLE_BLOB)MyImage))";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public Boolean insertImgDocGeneradoNC(String pathQR, String path417, NotaCreditoxml objNotaCredito) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "  insert into imageDocumentoGenerado (IdDoc, NroDoc, IdOficina,TipMoneda,ImageBitsQR,ImageBits417) values \n"
                    + " ('" + objNotaCredito.getIdDoc() + "','" + objNotaCredito.getNroDoc() + "','" + objNotaCredito.getIdOfi() + "','" + objNotaCredito.getTipM() + "',(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + pathQR + "', \n"
                    + "  SINGLE_BLOB)MyImage),(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + path417 + "', \n"
                    + "  SINGLE_BLOB)MyImage))";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public Boolean insertImgDocGeneradoND(String pathQR, String path417, NotaDebitoxml objNotaDebito) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "  insert into imageDocumentoGenerado (IdDoc, NroDoc, IdOficina,TipMoneda,ImageBitsQR,ImageBits417) values \n"
                    + " ('" + objNotaDebito.getIdDoc() + "','" + objNotaDebito.getNroDoc() + "','" + objNotaDebito.getIdOfi() + "','" + objNotaDebito.getTipM() + "',(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + pathQR + "', \n"
                    + "  SINGLE_BLOB)MyImage),(select  BulkColumn FROM OPENROWSET \n"
                    + " (BULK '" + path417 + "', \n"
                    + "  SINGLE_BLOB)MyImage))";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    /*documento compra*/
    public String registrarCompra(DocumentoGenerado objDocumentoGenerado, DocCompraVenta objDocCompraVenta, Usuario objUsuario, int i, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocumentoCompraVenta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setInt(1, i);
            cs.setString(2, objDocumentoGenerado.getIdDoc());
            cs.setString(3, objDocumentoGenerado.getNroDoc());
            cs.setString(4, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(5, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(6, objDocCompraVenta.getObjTipoDocumento().getIdTipoComprobante());
            cs.setString(7, objDocCompraVenta.getDocumentoNro());
            cs.setString(8, objDocumentoGenerado.getFechaDocumento());
            cs.setDouble(9, objDetDocCompraVenta.getCantidad());
            cs.setString(10, objDetDocCompraVenta.getUnidadMedidad());
            cs.setDouble(11, objDetDocCompraVenta.getValorUnitario());
            cs.setDouble(12, objDetDocCompraVenta.getPrecioUnitario());
            cs.setString(13, "0000");
            cs.setString(14, objDocumentoGenerado.getGlosaVariable());
            cs.setDouble(15, objDetDocCompraVenta.getMonto());
            cs.setString(16, objDocCompraVenta.getObjEmisor().getIdPersona());
            cs.setString(17, objDocCompraVenta.getObjEjecutor().getIdPersona());
            cs.setString(18, objUsuario.getIdUsuario());
            cs.setString(19, result);
            cs.registerOutParameter(19, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(19);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarDocCompra(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocGenerado_DocumentoCpraVta(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(3, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(4, objDocumentoGenerado.getIdDoc());
            cs.setString(5, objDocumentoGenerado.getFechaDocumento());
            cs.setString(6, objDocumentoGenerado.getGlosaFija());
            cs.setString(7, "");
            cs.setString(8, objUsuario.getIdUsuario());
            cs.setString(9, result);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(9);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public HashMap<String, String> registrarDocVenta(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        String result = "ERROR";
        String result1 = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocGenerado_DocumentoCpraVtaFE(?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(3, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(4, objDocumentoGenerado.getIdDoc());
            cs.setString(5, objDocumentoGenerado.getFechaDocumento());
            cs.setString(6, "FACTURA ELECTRONICA");
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
        lista.put("nroFact", result1);

        return lista;
    }

    public HashMap<String, String> registrarNotaCredito(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        String result = "ERROR";
        String result1 = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocGenerado_DocumentoCpraVtaNCE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocumentoGenerado.getObjRelaDoc().getIdOficinaR());
            cs.setString(2, objDocumentoGenerado.getObjRelaDoc().getTipMonedaR());
            cs.setString(3, objDocumentoGenerado.getObjRelaDoc().getIdDocR());
            cs.setString(4, objDocumentoGenerado.getObjRelaDoc().getNroDocR());
            cs.setString(5, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(6, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(7, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(8, "0004");
            cs.setString(9, objDocumentoGenerado.getFechaDocumento());
            cs.setString(10, objDocumentoGenerado.getGlosaFija());
            cs.setString(11, objDocumentoGenerado.getGlosaVariable());
            cs.setString(12, objUsuario.getIdUsuario());
            cs.setString(13, result);
            cs.setString(14, result1);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);
            cs.registerOutParameter(14, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(13);
            result1 = cs.getString(14);

        } catch (SQLException e) {
            e.getMessage();
        }

        lista.put("nroDoc", result);
        lista.put("nroFact", result1);

        return lista;
    }

    public HashMap<String, String> registrarNotaDebito(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {
        HashMap<String, String> lista = new HashMap<>();
        String result = "ERROR";
        String result1 = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocGenerado_DocumentoCpraVtaNDE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocumentoGenerado.getObjRelaDoc().getIdOficinaR());
            cs.setString(2, objDocumentoGenerado.getObjRelaDoc().getTipMonedaR());
            cs.setString(3, objDocumentoGenerado.getObjRelaDoc().getIdDocR());
            cs.setString(4, objDocumentoGenerado.getObjRelaDoc().getNroDocR());
            cs.setString(5, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(6, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(7, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setString(8, "0008");
            cs.setString(9, objDocumentoGenerado.getFechaDocumento());
            cs.setString(10, objDocumentoGenerado.getGlosaFija());
            cs.setString(11, objDocumentoGenerado.getGlosaVariable());
            cs.setString(12, objUsuario.getIdUsuario());
            cs.setString(13, result);
            cs.setString(14, result1);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);
            cs.registerOutParameter(14, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(13);
            result1 = cs.getString(14);

        } catch (SQLException e) {
            e.getMessage();
        }

        lista.put("nroDoc", result);
        lista.put("nroFact", result1);

        return lista;
    }

    public String registrarCompraDetalle(DocumentoGenerado objDocumentoGenerado, int i, DocCompraVenta objDocCompraVenta, Usuario objUsuario, DetDocCompraVenta objDetDocCompraVenta) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DCV_Detalle(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocumentoGenerado.getIdDoc());
            cs.setString(2, objDocumentoGenerado.getNroDoc());
            cs.setString(3, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            cs.setDouble(5, objDetDocCompraVenta.getValorUnitarioTotal());
            cs.setDouble(6, objDetDocCompraVenta.getIgvTotal());
            cs.setDouble(7, i);
            cs.setString(8, objUsuario.getIdUsuario());
            cs.setString(9, result);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(9);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public List<ListaDocumentoCompraVenta> listarDocCompra(int criterio, String valor) throws Exception {
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
                    + "convert(numeric(16,2),sum(ddcv.Monto)) as Total, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina \n"
                    + "from DocGenerado dg \n"
                    + "join DocumentoCompraVenta dcv  \n"
                    + "on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina \n"
                    + "join DetalleDocumentoCompraVta ddcv \n"
                    + "on ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea  \n"
                    + "join PersonaJuridica pj on dg.IdPersona = pj.IdPersona "
                    + "join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc AND " + CriterioSQL + " where dg.IdDoc = '0003' \n"
                    + "group by dg.idestdoc, dcv.NroDocExt, pj.RazonSocial, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina,dcv.FechaDoctoExt \n"
                    + "order by dcv.NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEmisor(rs.getString("RazonSocial"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotal(rs.getDouble("Total"));
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

    public Boolean anularDocumentoCompraVenta(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE DocGenerado\n"
                    + "   SET \n"
                    + "      [IdEstDoc] = '05'\n"
                    + "      ,[FechaCambioEstado] = CONVERT(CHAR(10),GETDATE(),103)\n"
                    + "      ,[HoraCambioEstado] = RIGHT( '0'+LTRIM(RIGHT(CONVERT(CHAR(19),GETDATE()),7)),7)\n"
                    + "      ,[IdUsuario] = '" + objUsuario.getIdUsuario() + "' "
                    + " where IdDoc='" + objDocumentoGenerado.getIdDoc() + "' and NroDoc ='" + objDocumentoGenerado.getNroDoc() + "' and IdOficina='" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and TipMoneda='" + objDocumentoGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public List<ListaDocumentoCompraVenta> listarDocVenta(int criterio, String valor) throws Exception {
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
                    + "convert(numeric(16,2),sum(ddcv.Monto)) as Total, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina \n"
                    + "from DocGenerado dg \n"
                    + "join DocumentoCompraVenta dcv  \n"
                    + "on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina \n"
                    + "join DetalleDocumentoCompraVta ddcv \n"
                    + "on ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea \n"
                    + "join PersonaJuridica pj on dg.IdPersona = pj.IdPersona "
                    + "join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc AND " + CriterioSQL + " where dg.IdDoc = '0001' \n"
                    + "group by dg.idestdoc, dcv.NroDocExt, pj.RazonSocial, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina,dcv.FechaDoctoExt \n"
                    + "order by dcv.NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEjecutor(rs.getString("RazonSocial"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotal(rs.getDouble("Total"));
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

    public List<ListaDocumentoCompraVenta> listarNotaCred(int criterio, String valor) throws Exception {
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
                    + "convert(numeric(16,2),sum(ddcv.Monto)) as Total, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina \n"
                    + "from DocGenerado dg \n"
                    + "join DocumentoCompraVenta dcv  \n"
                    + "on dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina \n"
                    + "join DetalleDocumentoCompraVta ddcv \n"
                    + "on ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea \n"
                    + "join PersonaJuridica pj on dg.IdPersona = pj.IdPersona "
                    + "join TabEstadoDoc tabd on dg.IdEstDoc = tabd.IdEstDoc AND " + CriterioSQL + " where dg.IdDoc = '0004' \n"
                    + "group by dg.idestdoc, dcv.NroDocExt, pj.RazonSocial, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina,dcv.FechaDoctoExt \n"
                    + "order by dcv.NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEjecutor(rs.getString("RazonSocial"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotal(rs.getDouble("Total"));
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

    public List<ListaDocumentoCompraVenta> listarLibro(String valor) throws Exception {
        List<ListaDocumentoCompraVenta> listaDocCompraVenta = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String[] parts = valor.split("&");

            String sql = "SELECT DISTINCT dcv.NroDocExt, \n"
                    + "Emisor = pjem.RazonSocial, \n"
                    + "Ejecutor = pjej.RazonSocial, \n"
                    + "CONVERT(VARCHAR(10), dcv.FechaDoctoExt, 103) AS FechaDoctoExt,\n"
                    + "tabd.Estado, dg.IdDoc, dg.NroDoc, dg.TipMoneda, dg.IdOficina,\n"
                    + "VALORV = case when dg.IdEstDoc = '08' then (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end,\n"
                    + "IGV =  case when dg.IdEstDoc = '08' then  (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '1000' and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina ) else 0 end,\n"
                    + "Total=case when dg.IdEstDoc = '08' then (select sum(Total) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end\n"
                    + "FROM  DocGenerado AS dg \n"
                    + "JOIN DocumentoCompraVenta AS dcv \n"
                    + "ON dg.IdDoc = dcv.IdDoc AND dg.NroDoc = dcv.NroDoc AND dg.TipMoneda = dcv.TipMoneda AND dg.IdOficina = dcv.IdOficina\n"
                    + "JOIN DetalleDocumentoCompraVta AS ddcv \n"
                    + "ON dg.iddoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                    + "JOIN PersonaJuridica AS pjej ON dcv.IdEjecutor = pjej.IdPersona \n"
                    + "JOIN PersonaJuridica AS pjem ON dcv.IdEmisor = pjem.IdPersona \n"
                    + "JOIN TabEstadoDoc AS tabd ON dg.IdEstDoc = tabd.IdEstDoc \n"
                    + "where dcv.FechaDoctoExt >= '" + parts[0] + "' AND dcv.FechaDoctoExt <= '" + parts[1] + "' and dg.TipMoneda = '" + parts[2] + "' and \n"
                    + "dg.IdDoc = '" + parts[3] + "'\n"
                    + "group by dg.idestdoc, dcv.NroDocExt, pjem.RazonSocial, pjej.RazonSocial, tabd.Estado, dg.iddoc, dg.nrodoc, dg.tipMoneda, dg.idOficina,dcv.FechaDoctoExt\n"
                    + "order by NroDocExt";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objListaDocumentoCompraVenta = new ListaDocumentoCompraVenta();

                objListaDocumentoCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objListaDocumentoCompraVenta.setEmisor(rs.getString("Emisor"));
                objListaDocumentoCompraVenta.setEjecutor(rs.getString("Ejecutor"));
                objListaDocumentoCompraVenta.setFecha(rs.getString("FechaDoctoExt"));
                objListaDocumentoCompraVenta.setTotal(rs.getDouble("Total"));
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

    public void deleteCatch(Map<String, Object> dataObject) {
        String nroDoc = null, idDoc = null, idOficina = null, tipMoneda = null, sql = null;
        try (Connection cn = Connect.Instancia().getConnectBD()) {
            for (Map.Entry e : dataObject.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    nroDoc = e.getValue().toString();
                } else if (e.getKey().equals("idDoc")) {
                    idDoc = e.getValue().toString();
                } else if (e.getKey().equals("idOficina")) {
                    idOficina = e.getValue().toString();
                } else if (e.getKey().equals("tipMoneda")) {
                    tipMoneda = e.getValue().toString();
                }
            }

            sql = "delete DetalleDocumentoCompraVta where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'";
            cn.prepareStatement(sql).execute();
            sql = "delete DocumentoCompraVenta where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'";
            cn.prepareStatement(sql).execute();
            sql = "delete ImageDocumentoGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'";
            cn.prepareStatement(sql).execute();
            sql = "delete RelaDoc where NroDocR = '" + nroDoc + "' and IdDocR = '" + idDoc + "'"
                    + " and IdOficinaR = '" + idOficina + "' and TipMonedaR  = '" + tipMoneda + "' and IdDoc = '0005'";
            cn.prepareStatement(sql).execute();
            sql = "delete RelaDoc where NroDocR = '" + nroDoc + "' and IdDocR = '" + idDoc + "'"
                    + " and IdOficinaR = '" + idOficina + "' and TipMonedaR  = '" + tipMoneda + "' and IdDoc = '0006'";
            cn.prepareStatement(sql).execute();
            sql = "delete DocGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'";
            cn.prepareStatement(sql).execute();
            sql = "UPDATE DocGenerado set IdEstDoc = '05' where NroDoc in (select NroDoc from RelaDoc where IdDocR = '" + idDoc + "' and NroDocR = '" + nroDoc + "' and TipMonedar = '" + tipMoneda + "' and IdOficinaR = '" + idOficina + "' AND IdDoc = '0005') "
                    + " AND IdDoc = '0005' and IdOficina = '" + idOficina + "' and TipMoneda = '0'";
            cn.prepareStatement(sql).execute();
            sql = "UPDATE DocGenerado set IdEstDoc = '05' where NroDoc in (select NroDoc from RelaDoc where IdDocR = '" + idDoc + "' and NroDocR = '" + nroDoc + "' and TipMonedar = '" + tipMoneda + "' and IdOficinaR = '" + idOficina + "' AND IdDoc = '0006') "
                    + " AND IdDoc = '0006' and IdOficina = '" + idOficina + "' and TipMoneda = '0'";
            cn.prepareStatement(sql).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteCatchGM(Map<String, Object> dataObject) {
        String nroDoc = null, idDoc = null, idOficina = null, tipMoneda = null;
        try (Connection cn = Connect.Instancia().getConnectBD()) {
            for (Map.Entry e : dataObject.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    nroDoc = e.getValue().toString();
                } else if (e.getKey().equals("idDoc")) {
                    idDoc = e.getValue().toString();
                } else if (e.getKey().equals("idOficina")) {
                    idOficina = e.getValue().toString();
                } else if (e.getKey().equals("tipMoneda")) {
                    tipMoneda = e.getValue().toString();
                }
            }
            cn.prepareStatement("delete DetalleGuiaRemision where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete GuiaRemision where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete ImageDocumentoGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete DocGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteCatchNC(Map<String, Object> dataObject) {
        String nroDoc = null, idDoc = null, idOficina = null, tipMoneda = null;
        try (Connection cn = Connect.Instancia().getConnectBD()) {
            for (Map.Entry e : dataObject.entrySet()) {
                if (e.getKey().equals("nroDoc")) {
                    nroDoc = e.getValue().toString();
                } else if (e.getKey().equals("idDoc")) {
                    idDoc = e.getValue().toString();
                } else if (e.getKey().equals("idOficina")) {
                    idOficina = e.getValue().toString();
                } else if (e.getKey().equals("tipMoneda")) {
                    tipMoneda = e.getValue().toString();
                }
            }
            cn.prepareStatement("delete Reladoc where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete DetalleDocumentoCompraVta where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete DocumentoCompraVenta where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete ImageDocumentoGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();
            cn.prepareStatement("delete DocGenerado where NroDoc = '" + nroDoc + "' and IdDoc = '" + idDoc + "'"
                    + " and IdOficina = '" + idOficina + "' and TipMoneda  = '" + tipMoneda + "'").execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<ListaDocumentoCompraVenta> buscarDocumentoCompraVenta(String tipo, String nroDoc, String fecIni, String fecFin) throws Exception {

        List<ListaDocumentoCompraVenta> listaDocumentosCompraVenta = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_Buscar_DocumentoCompraVenta(?,?,?,?)}");
            cstmt.setString(1, tipo);
            cstmt.setString(2, nroDoc);
            cstmt.setString(3, fecIni);
            cstmt.setString(4, fecFin);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                ListaDocumentoCompraVenta objDocCompraVenta = new ListaDocumentoCompraVenta();

                objDocCompraVenta.getObjTipComprobante().setDescripcion(rs.getString("tipoDocumento"));
                objDocCompraVenta.setIdDoc(rs.getString("idDoc"));
                objDocCompraVenta.setNroDoc(rs.getString("nroDoc"));
                objDocCompraVenta.setEjecutor(rs.getString("Ejecutor"));
                objDocCompraVenta.setEmisor(rs.getString("Emisor"));
                objDocCompraVenta.getObjMoneda().setDescripcion(rs.getString("Moneda"));
                objDocCompraVenta.getObjMoneda().setId(rs.getString("tipMoneda"));
                objDocCompraVenta.setDocumentoNro(rs.getString("NroDocExt"));
                objDocCompraVenta.setFecha(rs.getString("Fecha"));
                objDocCompraVenta.setTotalIGV(rs.getDouble("TotalIGV"));
                objDocCompraVenta.setTotalVN(rs.getDouble("TotalNeto"));
                objDocCompraVenta.setTotal(rs.getDouble("Total"));
                objDocCompraVenta.setEstado(rs.getString("estado"));
                listaDocumentosCompraVenta.add(objDocCompraVenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDocumentosCompraVenta;
    }

    public DocumentoGenerado obtenerDocumentoGenerado(DocumentoGenerado objDocGenerado) throws Exception {

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_obtenerDocumentoCompraVenta(?,?,?,?)}");
            cstmt.setString(1, objDocGenerado.getIdDoc());
            cstmt.setString(2, objDocGenerado.getNroDoc());
            cstmt.setString(3, objDocGenerado.getObjTipoMoneda().getId());
            cstmt.setString(4, objDocGenerado.getObjOficina().getIdOficina());
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                objDocumentoGenerado.getObjTipoComprobante().setDescripcion(rs.getString("tipoDocumento"));
                objDocumentoGenerado.setIdDoc(rs.getString("idDoc"));
                objDocumentoGenerado.setNroDoc(rs.getString("nroDoc"));
                objDocumentoGenerado.getObjEjecutor().setNombres(rs.getString("Ejecutor"));
                objDocumentoGenerado.getObjEjecutor().setNroDocumento(rs.getString("RUC"));
                objDocumentoGenerado.getObjEjecutor().setDireccion(rs.getString("Direccion"));
                objDocumentoGenerado.getObjTipoMoneda().setDescripcion(rs.getString("Moneda"));
                objDocumentoGenerado.getObjTipoMoneda().setId(rs.getString("tipMoneda"));
                objDocumentoGenerado.setNroDocExt(rs.getString("NroDocExt"));
                objDocumentoGenerado.setFechaDocumento(rs.getString("Fecha"));
                objDocumentoGenerado.setTotalIGV(rs.getDouble("TotalIGV"));
                objDocumentoGenerado.setTotalNeto(rs.getDouble("TotalNeto"));
                objDocumentoGenerado.setTotal(rs.getDouble("Total"));
                objDocumentoGenerado.setEstado(rs.getString("estado"));
                objDocumentoGenerado.setGlosaVariable(rs.getString("observaciones"));
            }

        } catch (Exception e) {
            throw e;
        }

        return objDocumentoGenerado;
    }

    public DocumentoGenerado obtenerDocumentoGeneradoNota(DocumentoGenerado objDocGenerado) throws Exception {

        DocumentoGenerado objDocumentoGenerado = new DocumentoGenerado();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_obtenerDocumentoCompraVenta(?,?,?,?)}");
            cstmt.setString(1, objDocGenerado.getIdDoc());
            cstmt.setString(2, objDocGenerado.getNroDoc());
            cstmt.setString(3, objDocGenerado.getObjTipoMoneda().getId());
            cstmt.setString(4, objDocGenerado.getObjOficina().getIdOficina());
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                objDocumentoGenerado.getObjTipoComprobante().setDescripcion(rs.getString("tipoDocumento"));
                objDocumentoGenerado.getObjRelaDoc().setIdDocR(rs.getString("idDoc"));
                objDocumentoGenerado.getObjRelaDoc().setNroDocR(rs.getString("nroDoc"));
                objDocumentoGenerado.getObjEjecutor().setNombres(rs.getString("Ejecutor"));
                objDocumentoGenerado.getObjEjecutor().setNroDocumento(rs.getString("RUC"));
                objDocumentoGenerado.getObjEjecutor().setDireccion(rs.getString("Direccion"));
                objDocumentoGenerado.getObjTipoMoneda().setDescripcion(rs.getString("Moneda"));
                objDocumentoGenerado.getObjRelaDoc().setTipMonedaR(rs.getString("tipMoneda"));
                objDocumentoGenerado.getObjTipoMoneda().setId(rs.getString("tipMoneda"));
                objDocumentoGenerado.setNroDocExt(rs.getString("NroDocExt"));
                objDocumentoGenerado.setFechaDocumento(rs.getString("Fecha"));
                objDocumentoGenerado.setTotalIGV(rs.getDouble("TotalIGV"));
                objDocumentoGenerado.setTotalNeto(rs.getDouble("TotalNeto"));
                objDocumentoGenerado.setTotal(rs.getDouble("Total"));
                objDocumentoGenerado.setEstado(rs.getString("estado"));
                objDocumentoGenerado.setGlosaVariable(rs.getString("observaciones"));
                objDocumentoGenerado.getObjRelaDoc().setIdOficinaR(rs.getString("idOficina"));
                objDocumentoGenerado.getObjPersona().setIdPersona(rs.getString("idPersona"));
                objDocumentoGenerado.getObjEmisor().setIdPersona(rs.getString("idEmisor"));

            }

        } catch (Exception e) {
            throw e;
        }

        return objDocumentoGenerado;
    }

    public List<DetDocCompraVenta> listarDetalleDocumentoCompraVenta(DocumentoGenerado objDocGenerado) throws Exception {

        List<DetDocCompraVenta> listaDetalleDocumentosCompraVenta = new ArrayList<DetDocCompraVenta>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_obtenerDetalleDocumentoCompraVenta(?,?,?,?)}");
            cstmt.setString(1, objDocGenerado.getIdDoc());
            cstmt.setString(2, objDocGenerado.getNroDoc());
            cstmt.setString(3, objDocGenerado.getObjTipoMoneda().getId());
            cstmt.setString(4, objDocGenerado.getObjOficina().getIdOficina());
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                DetDocCompraVenta objDetalleDocCompraVenta = new DetDocCompraVenta();

                objDetalleDocCompraVenta.setCantidad(rs.getDouble("Cantidad"));
                objDetalleDocCompraVenta.setGlosaVariable(rs.getString("GlosaVariable"));
                objDetalleDocCompraVenta.setUnidadMedidad(rs.getString("UM"));
                objDetalleDocCompraVenta.setValorUnitario(rs.getDouble("ValorUnitario"));

                objDetalleDocCompraVenta.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                objDetalleDocCompraVenta.setIgvTotal(rs.getDouble("igv"));
                objDetalleDocCompraVenta.setValorUnitarioTotal(rs.getDouble("vneto"));
                objDetalleDocCompraVenta.setMonto(rs.getDouble("total"));
                objDetalleDocCompraVenta.setIdDetGastoSunat(rs.getString("IdTipDocSUNAT"));
                listaDetalleDocumentosCompraVenta.add(objDetalleDocCompraVenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDetalleDocumentosCompraVenta;
    }

    public List<GuiaRemision> listarGuiaRemision(DocumentoGenerado objDocGenerado) throws Exception {

        List<GuiaRemision> listaDetalleDocumentosCompraVenta = new ArrayList<GuiaRemision>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_obtenerListaGuiasDocumentoCompraVenta(?,?,?,?)}");
            cstmt.setString(1, objDocGenerado.getIdDoc());
            cstmt.setString(2, objDocGenerado.getNroDoc());
            cstmt.setString(3, objDocGenerado.getObjTipoMoneda().getId());
            cstmt.setString(4, objDocGenerado.getObjOficina().getIdOficina());
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                GuiaRemision objGuiaRemision = new GuiaRemision();

                objGuiaRemision.setNroGuia(rs.getString("NroGuia"));
                objGuiaRemision.setFechaGuia(rs.getString("FechaDocumento"));
                objGuiaRemision.getObjPersonaDestinatario().setNombres(rs.getString("Destinatario"));
                objGuiaRemision.setTipoGuia(rs.getString("TipoGuia"));

                listaDetalleDocumentosCompraVenta.add(objGuiaRemision);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaDetalleDocumentosCompraVenta;
    }

}
