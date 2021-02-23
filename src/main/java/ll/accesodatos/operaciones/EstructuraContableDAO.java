package ll.accesodatos.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.EstructuraContable;
import ll.entidades.administracion.Operacion;

public class EstructuraContableDAO {

    public static EstructuraContable obtenerEstructuraContable(String idOpe, String tipMoneda, String idOficina, String idDpto, String idPuesto) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        EstructuraContable objEstructuraContable = new EstructuraContable();
        Operacion objOperacion = new Operacion();

        String sql = null;

        sql = "SELECT EstructuraContable.IdOpe as idOpe,CuentaCargo,CuentaAbono,MaeOperaciones.Operacion as Operacion "
                + "FROM EstructuraContable "
                + "Inner Join MaeOperaciones On EstructuraContable.IdOpe=MaeOperaciones.IdOpe"
                + " WHERE EstructuraContable.IdOpe='" + idOpe + "' AND EstructuraContable.TipMoneda='" + tipMoneda + "'"
                + " AND EstructuraContable.IdOficina='" + idOficina + "' AND EstructuraContable.IdDpto='" + idDpto + "' "
                + "AND EstructuraContable.IdPuesto='" + idPuesto + "' AND EstructuraContable.Activo= '1'";

        try {

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objOperacion.setIdOperacion(rs.getString("idOpe"));
                objOperacion.setNombreOperacion(rs.getString("Operacion"));
                objEstructuraContable.setCuentaCargo(rs.getString("CuentaCargo"));
                objEstructuraContable.setCuentaAbono(rs.getString("CuentaAbono"));
                objEstructuraContable.setObjOperaciones(objOperacion);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            objConexion.close();
        }

        return objEstructuraContable;
    }
}
