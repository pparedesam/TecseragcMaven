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
import ll.entidades.administracion.Producto;

/**
 *
 * @author CesGue
 */
public class ProductoDAO {
    private static ProductoDAO _Instancia;

    private ProductoDAO() {
    }

    public static ProductoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ProductoDAO();
        }
        return _Instancia;
    }

    public List<Producto> listarProductosMovCta() throws Exception {

        List<Producto> listProducto = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT p.IdProducto,p.Producto FROM  Producto p WHERE p.IdProducto IN('04','05') ";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Producto objProducto = new Producto();
                objProducto.setIdProducto(rs.getString("IdProducto"));
                objProducto.setDesProducto(rs.getString("Producto"));
                listProducto.add(objProducto);
            }

        } catch (Exception e) {
            throw e;
        }

        return listProducto;
    }
}
