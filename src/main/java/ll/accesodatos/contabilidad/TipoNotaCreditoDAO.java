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
import ll.entidades.contabilidad.TipoNotaCredito;

/**
 *
 * @author PauPar
 */
public class TipoNotaCreditoDAO {

    private static TipoNotaCreditoDAO _Instancia;

    private TipoNotaCreditoDAO() {
    }

    public static TipoNotaCreditoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoNotaCreditoDAO();
        }
        return _Instancia;
    }

    public List<TipoNotaCredito> listar() throws Exception {

        List<TipoNotaCredito> listTipoNotaCredito = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select idTipo, descripcion from TipoNotaCredito where estado = '1'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoNotaCredito objTipoNotaCredito = new TipoNotaCredito();
                objTipoNotaCredito.setIdTipo(rs.getString("idTipo"));
                objTipoNotaCredito.setDescripcion(rs.getString("descripcion"));

                listTipoNotaCredito.add(objTipoNotaCredito);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoNotaCredito;
    }

}
