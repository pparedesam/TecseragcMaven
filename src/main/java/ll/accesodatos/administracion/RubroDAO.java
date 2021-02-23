/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Rubro;

/**
 *
 * @author RenRio
 */
public class RubroDAO {

    private static RubroDAO _Instancia;

    private RubroDAO() {
    }

    public static RubroDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new RubroDAO();
        }
        return _Instancia;
    }

    public List<Rubro> obtenerRubro() throws Exception {
        List<Rubro> listaRubro = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String Sql = "SELECT * FROM RUBRO where Estado ='1' order by IdRubro ";

            PreparedStatement pstm = cn.prepareStatement(Sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Rubro objRubro = new Rubro();
                objRubro.setIdRubro(rs.getString("IdRubro"));
                objRubro.setDescripcion(rs.getString("Descripcion"));

                listaRubro.add(objRubro);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaRubro;

    }
}
