package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ll.accesodatos.agente.Connect;


import ll.entidades.administracion.Urbanizacion;

public class UrbanizacionDAO {

    private static UrbanizacionDAO _Instancia;

    private UrbanizacionDAO() {
    }

    public static UrbanizacionDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new UrbanizacionDAO();
        }
        return _Instancia;
    }

  

    public List<Urbanizacion> listar() throws Exception {

        List<Urbanizacion> listUrbanizacion = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT IdUrbanizacion, Nombre, Estado from Urbanizacion  where Estado = 1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Urbanizacion objUrbanizacion = new Urbanizacion();
                objUrbanizacion.setCodigo(rs.getString("IdUrbanizacion"));
                objUrbanizacion.setDescripcion(rs.getString("Nombre"));
                objUrbanizacion.setEstado(rs.getBoolean("Estado"));

                listUrbanizacion.add(objUrbanizacion);
            }

        } catch (Exception e) {
            throw e;
        }

        return listUrbanizacion;
    }

    public String registrar(Urbanizacion objUrbanizacion) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call (?,?)}");

            cs.setString(1, objUrbanizacion.getDescripcion());
            cs.setString(2, objUrbanizacion.getCodigo());
            cs.registerOutParameter(2, java.sql.Types.CHAR);
            cs.execute();

            result = cs.getString(2);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean delete(String idUrbanizacion) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "Update Urbanizacion set  Estado = 0 where IdUrbanizacion='" + idUrbanizacion + "'";
            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.execute();

        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

}
