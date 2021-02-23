/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.contabilidad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author PauPar
 */
public class DocumentoBajaDAO {

    private static DocumentoBajaDAO _Instancia;

    private DocumentoBajaDAO() {
    }

    public static DocumentoBajaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new DocumentoBajaDAO();
        }
        return _Instancia;
    }

    public String registrarDocBaja(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario) throws Exception {

        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_Insert_DocGenerado_DocumentoBajaE(?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objDocumentoGenerado.getObjRelaDoc().getIdOficinaR());
            cs.setString(2, objDocumentoGenerado.getObjRelaDoc().getTipMonedaR());
            cs.setString(3, objDocumentoGenerado.getObjRelaDoc().getIdDocR());
            cs.setString(4, objDocumentoGenerado.getObjRelaDoc().getNroDocR());
            cs.setString(5, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(6, objDocumentoGenerado.getObjPersona().getIdPersona());
            cs.setString(7, objDocumentoGenerado.getIdDoc());
            cs.setString(8, objDocumentoGenerado.getFechaDocumento());
            cs.setString(9, objDocumentoGenerado.getGlosaVariable());
            cs.setString(10, objUsuario.getIdUsuario());
            cs.setString(11, result);

            cs.registerOutParameter(11, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(11);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

}
