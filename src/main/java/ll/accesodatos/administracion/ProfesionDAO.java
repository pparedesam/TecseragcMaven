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
import ll.entidades.administracion.Profesion;

public class ProfesionDAO {

    private static ProfesionDAO _Instancia;

    private ProfesionDAO() {
    }

    public static ProfesionDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ProfesionDAO();
        }
        return _Instancia;
    }

    public List<Profesion> listar() throws Exception {

        List<Profesion> listProfesion = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT IdProfesion, Descripcion, Estado from Profesiones  where Estado=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Profesion objProfesion = new Profesion();
                objProfesion.setIdProfesion(rs.getString("IdProfesion"));
                objProfesion.setProfesion(rs.getString("Descripcion"));
                objProfesion.setEstado(rs.getString("Estado"));

                listProfesion.add(objProfesion);
            }

        } catch (Exception e) {
            throw e;
        }

        return listProfesion;
    }

    public String registrarProfesion(Profesion objProfesion,String accion) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Afiliaciones_pa_ins_upd_del_profesion(?,?,?,?,?)}");

            cs.setString(1, objProfesion.getIdProfesion());
            cs.setString(2, objProfesion.getProfesion());
            cs.setString(3, objProfesion.getEstado());
            cs.setString(4,accion);
            cs.registerOutParameter(5,java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    } 

}
