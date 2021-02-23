/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.rrhh;

import ll.accesodatos.agente.Connect;
import ll.entidades.rrhh.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AndZuñ
 */
public class CargoDAO {

    private static CargoDAO _Instancia;

    private CargoDAO() {
    }

    public static CargoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new CargoDAO();
        }
        return _Instancia;
    }

    public List<Cargo> listarCargos() throws Exception {

        List<Cargo> listaCargos= new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "Select * from Cargo";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Cargo objCargo = new Cargo();
                objCargo.setIdCargo(rs.getString("idCargo"));
                objCargo.setNombreCargo(rs.getString("nombreCargo"));

                listaCargos.add(objCargo);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCargos;
    }
}
