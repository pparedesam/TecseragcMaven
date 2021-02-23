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
import ll.entidades.administracion.Oficina;

/**
 *
 * @author CesGue
 */
public class OficinaDAO {

    private static OficinaDAO _Instancia;

    private OficinaDAO() {
    }

    public static OficinaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new OficinaDAO();
        }
        return _Instancia;
    }

    public List<Oficina> listarOficinas() throws Exception {

        List<Oficina> listaOficina = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT IdOficina,Nombre from oficina";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Oficina objOficina = new Oficina();
                objOficina.setIdOficina(rs.getString("IdOficina"));
                objOficina.setNombre(rs.getString("Nombre"));
                listaOficina.add(objOficina);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaOficina;
    }

}
