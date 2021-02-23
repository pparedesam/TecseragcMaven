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
import ll.entidades.administracion.TipoDocIdentidad;

/**
 *
 * @author PauPar
 */
public class TipoDocIdentidadDAO {

    private static TipoDocIdentidadDAO _Instancia;

    private TipoDocIdentidadDAO() {
    }

    public static TipoDocIdentidadDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoDocIdentidadDAO();
        }
        return _Instancia;
    }

    public List<TipoDocIdentidad> listarTipoDocIdentidad(String tipoPersona) throws Exception {
        List<TipoDocIdentidad> listTipoDocIdentidad = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select * from TabDocIdentidad where tipoPersona = '" + tipoPersona + "' and Estado=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoDocIdentidad objTipoDocIdentidad = new TipoDocIdentidad();
                objTipoDocIdentidad.setDescripcion(rs.getString("descripcion"));
                objTipoDocIdentidad.setTipoPersona(rs.getString("tipoPersona"));
                objTipoDocIdentidad.setIdTipoDocIdentidad(rs.getString("idTipDocIdentidad"));

                listTipoDocIdentidad.add(objTipoDocIdentidad);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoDocIdentidad;
    }
}
