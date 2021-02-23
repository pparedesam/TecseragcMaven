package ll.accesodatos.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.AsignaDocumentoxOperacion;
import ll.entidades.administracion.Documento;

public class AsignarDocumentoxOperacionDAO {

    private static AsignarDocumentoxOperacionDAO _Instancia;

    private AsignarDocumentoxOperacionDAO() {
    }

    public static AsignarDocumentoxOperacionDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new AsignarDocumentoxOperacionDAO();
        }
        return _Instancia;
    }

    public AsignaDocumentoxOperacion verficarAsignacionDOCxOPE(String idOpe, String tipMoneda) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        Documento objDocumento = null;
        AsignaDocumentoxOperacion objAsignaDocumentoxOperacion = null;

        objDocumento = new Documento();
        objAsignaDocumentoxOperacion = new AsignaDocumentoxOperacion();

        String sql = null;

        sql = "SELECT ADO.IdOpe as idOpe, ADO.IdDoc as idDoc, TD.NombreDoc as nombreDoc,"
                + " ADO.TipMoneda as tipMoneda,TD.Parametro as parametro"
                + " FROM AsignaDocxOpe ADO"
                + " INNER JOIN TabDocumento TD ON ADO.IdDoc = TD.IdDoc"
                + " WHERE ADO.IdOpe = '" + idOpe + "' AND ADO.TipMoneda = '" + tipMoneda + "' AND ADO.Activo='1'";

        try {

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objAsignaDocumentoxOperacion = new AsignaDocumentoxOperacion();
                objDocumento = new Documento();

                objAsignaDocumentoxOperacion.setIdOpe(rs.getString("idOpe"));
                objDocumento.setIdDocumento(rs.getString("idDoc"));
                objDocumento.setNombreDoc(rs.getString("nombreDoc"));
                objAsignaDocumentoxOperacion.setTipMoneda(rs.getString("tipMoneda"));
                objDocumento.setParametro(rs.getString("parametro"));

                objAsignaDocumentoxOperacion.setObjDocumento(objDocumento);

            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            objConexion.close();
        }

        return objAsignaDocumentoxOperacion;
    }
}
