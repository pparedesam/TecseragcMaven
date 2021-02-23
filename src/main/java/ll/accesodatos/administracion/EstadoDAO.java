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
import ll.entidades.administracion.Estado;

/**
 *
 * @author DAVID
 */
public class EstadoDAO {
    
    private static EstadoDAO _Instancia;

    private EstadoDAO() {
    }

    public static EstadoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new EstadoDAO();
        }
        return _Instancia;
    }
    
 
    public List<Estado> listar() throws Exception {

        List<Estado> listEstado = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT t.IdTabla,t.Descripcion from Tablas t where t.IdTabla BETWEEN '7301' AND '7305' AND t.Estado='1'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Estado estado = new Estado();
                estado.setCodigo(rs.getString("IdTabla"));
                estado.setDescripcion(rs.getString("Descripcion"));
               

              listEstado.add(estado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listEstado;
    }

    
}
