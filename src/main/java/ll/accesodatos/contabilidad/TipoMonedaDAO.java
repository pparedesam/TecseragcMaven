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
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.TipoMoneda;

/**
 *
 * @author PauPar
 */
public class TipoMonedaDAO {
    
    
    private static TipoMonedaDAO _Instancia;

    private TipoMonedaDAO() {
    }

    public static TipoMonedaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoMonedaDAO();
        }
        return _Instancia;
    }

    
     public List<TipoMoneda> listar(Oficina objOficina) throws Exception {

        List<TipoMoneda> listTipoMoneda = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT idTipMoneda, Descripcion from TipoMoneda where idOficina='" + objOficina.getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoMoneda objTipoMoneda = new TipoMoneda();
                objTipoMoneda.setId(rs.getString("idTipMoneda"));
                objTipoMoneda.setDescripcion(rs.getString("Descripcion"));
               

                listTipoMoneda.add(objTipoMoneda);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoMoneda;
    }
    
}
