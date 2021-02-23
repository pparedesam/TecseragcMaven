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
import ll.entidades.administracion.TipoApertura;

/**
 *
 * @author CesGue
 */
public class TipoAperturaDAO {
    
    private static TipoAperturaDAO _Instancia;

    private TipoAperturaDAO() {
    }

    public static TipoAperturaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoAperturaDAO();
        }
        return _Instancia;
    }

    public List<TipoApertura> obtenerTiposApertura(String tipoPersona) throws Exception {

        List<TipoApertura> listTipoAperturas = new ArrayList<>();
        String sql;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            if (tipoPersona.equals("N")) {
                sql = "SELECT ta.IdTipoApertura,ta.Descripcion FROM TipoApertura ta WHERE ta.Activo='1'";
            } else {
                sql = "SELECT ta.IdTipoApertura,ta.Descripcion FROM TipoApertura ta WHERE ta.Activo='1' AND ta.IdTipoApertura='0901'";
            }

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoApertura objApertura = new TipoApertura();
                objApertura.setIdTipoApertura(rs.getString("IdTipoApertura"));
                objApertura.setDescripcion(rs.getString("Descripcion"));
                listTipoAperturas.add(objApertura);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoAperturas;
    }
    
    public List<TipoApertura> obtenerTipoAperturaxId(String id) throws Exception {

        List<TipoApertura> listTipoAperturas = new ArrayList<>();
        String sql;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

           
            sql = "SELECT IdTabla,Descripcion,Valor \n"
                    + "from TABLAS \n"
                    + "WHERE LEFT(IdTabla,2)='"+id+"' AND \n"
                    + "Tipo<>'0' AND Estado='1'";


            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoApertura objApertura = new TipoApertura();
                objApertura.setIdTipoApertura(rs.getString("IdTabla"));
                objApertura.setDescripcion(rs.getString("Descripcion"));
                listTipoAperturas.add(objApertura);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoAperturas;
    }
}
