/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.contabilidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.contabilidad.TipoNotaDebito;

/**
 *
 * @author RenRio
 */
public class TipoNotaDebitoDAO {
    

    private static TipoNotaDebitoDAO _Instancia;

    private TipoNotaDebitoDAO() {
    }

    public static TipoNotaDebitoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoNotaDebitoDAO();
        }
        return _Instancia;
    }

    public List<TipoNotaDebito> listarTipoNotaDebito() throws Exception {

        List<TipoNotaDebito> listTipoNotaDebito = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select idTipo, descripcion from TipoNotaDebito where estado = '1'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoNotaDebito objTipoNotaDebito = new TipoNotaDebito();
                objTipoNotaDebito.setIdTipo(rs.getString("idTipo"));
                objTipoNotaDebito.setDescripcion(rs.getString("descripcion"));

                listTipoNotaDebito.add(objTipoNotaDebito);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoNotaDebito;
    }

}
