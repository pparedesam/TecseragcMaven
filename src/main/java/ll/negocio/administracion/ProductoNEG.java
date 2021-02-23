/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.administracion.ProductoDAO;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Producto;

/**
 *
 * @author CesGue
 */
public class ProductoNEG {
    private static ProductoNEG _Instancia;

    private ProductoNEG() {
    }

    public static ProductoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ProductoNEG();
        }
        return _Instancia;
    }

    public List<Producto> listarProductosMovCta() throws Exception {
       return  ProductoDAO.Instancia().listarProductosMovCta();
    } 
}
