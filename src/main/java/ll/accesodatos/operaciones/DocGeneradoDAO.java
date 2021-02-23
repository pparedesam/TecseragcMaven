/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ll.accesodatos.agente.Connect;

import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author paupar
 */
public class DocGeneradoDAO {

    public static Boolean insertar(DocumentoGenerado objDocumentoGenerado) throws Exception {

        
       Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {

            String sql = "INSERT INTO  DocGenerado "
                    + " ( IdOficina , IdDoc , NroDoc , TipMoneda , IdEstDoc "
                    + " , IdOpe , FechaDocumento , HoraDocumento , FechaCambioEstado "
                    + " , DocumentoAnterior , IdPersona , GlosaFija , GlosaVariable "
                    + " , IdUsuario, Judicial ) "
                    + " VALUES "
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pstm = objConexion.prepareStatement(sql);

            pstm.setString(1, objDocumentoGenerado.getObjOficina().getIdOficina());
            pstm.setString(2, objDocumentoGenerado.getIdDoc());
            pstm.setString(3, objDocumentoGenerado.getNroDoc());
            pstm.setString(4, objDocumentoGenerado.getObjTipoMoneda().getId());
            pstm.setString(5, objDocumentoGenerado.getIdEstadoDocumento());
            pstm.setString(6, objDocumentoGenerado.getObjOperacion().getIdOperacion());
            pstm.setString(7, objDocumentoGenerado.getFechaDocumento());
            pstm.setString(8, objDocumentoGenerado.getHoraDocumento());
            pstm.setString(9, objDocumentoGenerado.getFechaCambioEstado());
            pstm.setString(10, objDocumentoGenerado.getDocAnterior());
            pstm.setString(11, objDocumentoGenerado.getObjPersona().getIdPersona());
            pstm.setString(12, objDocumentoGenerado.getGlosaFija());
            pstm.setString(13, objDocumentoGenerado.getGlosaVariable());
            pstm.setString(14, objDocumentoGenerado.getObjUsuario().getIdUsuario());
            pstm.setString(15, objDocumentoGenerado.getJudicial());

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
