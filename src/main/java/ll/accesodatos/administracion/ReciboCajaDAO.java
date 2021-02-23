/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;

public class ReciboCajaDAO {

    private static ReciboCajaDAO _Instancia;

    private ReciboCajaDAO() {
    }

    public static ReciboCajaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ReciboCajaDAO();
        }
        return _Instancia;
    }

    public String registrarReciboCaja(DocumentoGenerado objDocGenerado, Float monto, String firmaVB, String firmaRC, Usuario objUsuario) throws Exception {
        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Administracion_Insert_DocGenerado_ReciboCaja(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocGenerado.getObjOficina().getIdOficina());
            cs.setString(2, objDocGenerado.getObjPersona().getIdPersona());
            cs.setString(3, objDocGenerado.getObjTipoMoneda().getId());
            cs.setString(4, objDocGenerado.getIdDoc());
            cs.setString(5, objDocGenerado.getObjOperacion().getIdOperacion());
            cs.setString(6, objDocGenerado.getFechaDocumento());
            cs.setString(7, objDocGenerado.getGlosaFija());
            cs.setString(8, objDocGenerado.getGlosaVariable());
            cs.setDouble(9, monto);
            cs.setString(10, firmaVB);
            cs.setString(11, firmaRC);
            cs.setString(12, objUsuario.getIdUsuario());

            cs.setString(13, result);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(13);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;

    }

    public List<DocumentoGenerado> buscarRecibosCaja(String nroDoc, String fecIni, String fecFin) throws Exception {

        List<DocumentoGenerado> listaDocumentosGenerados = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Administracion_sp_buscarRecibos(?,?,?)}");
            cstmt.setString(1, nroDoc);
            cstmt.setString(2, fecIni);
            cstmt.setString(3, fecFin);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                DocumentoGenerado objDocGenerado = new DocumentoGenerado();

                objDocGenerado.setNroDoc(rs.getString("NroDoc"));
                objDocGenerado.setIdDoc(rs.getString("IdDoc"));
                objDocGenerado.getObjTipoMoneda().setId(rs.getString("TipMoneda"));
                objDocGenerado.getObjOficina().setIdOficina(rs.getString("IdOficina"));
                objDocGenerado.setFechaDocumento(rs.getString("fechaDoc"));
                objDocGenerado.getObjTipoComprobante().setDescripcion(rs.getString("Operacion"));
                objDocGenerado.getObjEjecutor().setNombres(rs.getString("Ejecutor"));
                objDocGenerado.setEstado(rs.getString("Estado"));
                objDocGenerado.setTotal(rs.getDouble("Monto"));

                listaDocumentosGenerados.add(objDocGenerado);

            }

        } catch (Exception e) {
            throw e;
        }

        return listaDocumentosGenerados;
    }

    public Boolean anularRecibo(DocumentoGenerado objDocGenerado, Usuario objUsuario, String motivo) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE DocGenerado\n"
                    + "   SET \n"
                    + "      [IdEstDoc] = '05'\n"
                    + "      ,[FechaCambioEstado] = CONVERT(CHAR(10),GETDATE(),103)\n"
                    + "      ,[GlosaVariable] = '" + motivo + "'\n"
                    + "      ,[HoraCambioEstado] = RIGHT( '0'+LTRIM(RIGHT(CONVERT(CHAR(19),GETDATE()),7)),7)\n"
                    + "      ,[IdUsuario] = '" + objUsuario.getIdUsuario() + "' "
                    + " where IdDoc='" + objDocGenerado.getIdDoc() + "' and NroDoc ='" + objDocGenerado.getNroDoc() + "' and IdOficina='" + objDocGenerado.getObjOficina().getIdOficina() + "' and TipMoneda='" + objDocGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (Exception e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public double obtenerSaldoCaja() throws Exception {
        double saldoCaja = 0.00;

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT\n"
                    + "		saldoCaja=isnull (SUM(tI.MontoSoles),0)-isnull (SUM(tE.MontoSoles),0)\n"
                    + "	FROM\n"
                    + "		DocGenerado dg\n"
                    + "		LEFT JOIN Transaccion tI ON dg.IdDoc=tI.IdDoc AND dg.NroDoc=tI.NroDoc AND dg.IdOficina=tI.IdOficina AND dg.TipMoneda=tI.idTipMoneda AND tI.IdOpe='00001'\n"
                    + "		LEFT JOIN Transaccion tE ON dg.IdDoc=tE.IdDoc AND dg.NroDoc=tE.NroDoc AND dg.IdOficina=tE.IdOficina AND dg.TipMoneda=tE.idTipMoneda AND tE.IdOpe='00002'\n"
                    + "	WHERE\n"
                    + "		dg.IdDoc='0013'\n"
                    + "		AND dg.IdEstDoc='08'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                saldoCaja = rs.getDouble("saldoCaja");
            }

        } catch (Exception e) {
            throw e;
        }

        return saldoCaja;

    }

}
