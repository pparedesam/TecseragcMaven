/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Ocupacion;

/**
 *
 * @author paupar
 */
public class OcupacionDAO {

    private static OcupacionDAO _Instancia;

    private OcupacionDAO() {
    }

    public static OcupacionDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new OcupacionDAO();
        }
        return _Instancia;
    }

    public List<Ocupacion> listar() throws Exception {

        List<Ocupacion> listOcupacion = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT IdOcupacion, Descripcion, Estado from Ocupaciones  where Estado=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Ocupacion objOcupacion = new Ocupacion();
                objOcupacion.setCodigo(rs.getString("IdOcupacion"));
                objOcupacion.setDescripcion(rs.getString("Descripcion"));
                objOcupacion.setEstado(rs.getString("Estado"));

                listOcupacion.add(objOcupacion);
            }

        } catch (Exception e) {
            throw e;
        }

        return listOcupacion;
    }

    public String registrar(Ocupacion objOcupacion) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_ocupacion(?,?,?)}");

            cs.setString(1, objOcupacion.getDescripcion());
            cs.setString(2, objOcupacion.getEstado());
            cs.setString(3, objOcupacion.getCodigo());
            
            cs.registerOutParameter(3, java.sql.Types.CHAR);
            cs.execute();

            result = cs.getString(3);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean delete(String idOcupacion) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "Update Ocupaciones set  Estado = 0 where IdOcupacion='" + idOcupacion + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.execute();

        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

}
