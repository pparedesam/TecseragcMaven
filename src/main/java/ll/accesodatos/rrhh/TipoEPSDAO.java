/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.rrhh;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.rrhh.TipoEPS;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class TipoEPSDAO {

    private static TipoEPSDAO _Instancia;

    private TipoEPSDAO() {
    }

    public static TipoEPSDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoEPSDAO();
        }
        return _Instancia;
    }

    public List<TipoEPS> listaTipoEPS() throws Exception {

        List<TipoEPS> listaTipoEPS = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select * from TipoSeguroEps";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TipoEPS objTipoEPS= new TipoEPS();
                objTipoEPS.setIdTipoEPS(rs.getString("IdTipoSeguroEPS"));
                objTipoEPS.setNombre(rs.getString("Nombre"));
                listaTipoEPS.add(objTipoEPS);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaTipoEPS;
    }
}
