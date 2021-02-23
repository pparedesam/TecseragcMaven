/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ll.accesodatos.agente.Connect;
import ll.entidades.operaciones.Transaccion;

/**
 *
 * @author paupar
 */
public class TransaccionDAO {

    public static Boolean insertar(Transaccion objTransaccion) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {

            String sql = "INSERT INTO Transaccion "
                    + " ( IdOficina , NroTransaccion , IdOficinaDoc , IdDoc , TipMonedaDoc "
                    + " , NroDoc , IdOficinaE , IdDpto , IdPuesto , IdOpe , TipMoneda , CuentaCargo "
                    + " , CuentaAbono , FechaTransaccion , IdPersona , GlosaFija , GlosaVariable , MontoSoles "
                    + " , MontoDolar , IdUsuario ) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            pstm.setString(1, objTransaccion.getObjOficina().getIdOficina());
            pstm.setString(2, objTransaccion.getNroTransaccion());
            pstm.setString(3, objTransaccion.getObjDocGenerado().getObjOficina().getIdOficina());
            pstm.setString(4, objTransaccion.getObjDocGenerado().getIdDoc());
            pstm.setString(5, objTransaccion.getObjDocGenerado().getObjTipoMoneda().getId());
            pstm.setString(6, objTransaccion.getObjDocGenerado().getNroDoc());
            pstm.setString(7, objTransaccion.getObjEstructuraContable().getObjOficina().getIdOficina());
            pstm.setString(8, objTransaccion.getObjEstructuraContable().getObjArea().getCodigo());
            pstm.setString(9, objTransaccion.getObjEstructuraContable().getObjPuesto().getIdPuesto());
            pstm.setString(10, objTransaccion.getObjEstructuraContable().getObjOperaciones().getIdOperacion());
            pstm.setString(11, objTransaccion.getObjEstructuraContable().getTipMoneda());
            pstm.setString(12, objTransaccion.getObjEstructuraContable().getCuentaCargo());
            pstm.setString(13, objTransaccion.getObjEstructuraContable().getCuentaAbono());
            pstm.setString(14, objTransaccion.getFechaTransaccion());
            pstm.setString(15, objTransaccion.getObjPersona().getIdPersona());
            pstm.setString(16, objTransaccion.getGlosaFija());
            pstm.setString(17, objTransaccion.getGlosaVariable());
            pstm.setDouble(18, objTransaccion.getMontoSoles());
            pstm.setDouble(19, objTransaccion.getMontoDolar());
            pstm.setString(20, objTransaccion.getObjUsuario().getIdUsuario());

            pstm.executeUpdate();

            result = true;

        } catch (Exception e) {
            objConexion.rollback();
            throw e;

        } finally {
            objConexion.close();
            objConexion.setAutoCommit(true);

        }
        return result;
    }

}
