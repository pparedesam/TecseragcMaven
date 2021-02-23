/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.contabilidad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.contabilidad.TipoServicio;

/**
 *
 * @author PauPar
 */
public class TipoServicioDAO {

    private static TipoServicioDAO _Instancia;

    private TipoServicioDAO() {
    }

    public static TipoServicioDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoServicioDAO();
        }
        return _Instancia;
    }

    public List<TipoServicio> listar() throws Exception {

        List<TipoServicio> listTipoServicio = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT idGlosaDetCV, Descripcion, Estado FROM GlosaDetalleCompraVenta";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoServicio objTipoServicio = new TipoServicio();
                objTipoServicio.setIdTipoServicio(rs.getString("idGlosaDetCV"));
                objTipoServicio.setDescripcion(rs.getString("Descripcion"));
                objTipoServicio.setEstado(rs.getInt("Estado"));

                listTipoServicio.add(objTipoServicio);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoServicio;
    }
    
    
    
     public String registrar(TipoServicio objTipoServicio, String registro) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Contabilidad_pa_MantenimientoTipoServicio(?,?,?,?,?)}");

            cs.setString(1, objTipoServicio.getDescripcion());
            cs.setString(2, objTipoServicio.getIdTipoServicio());
            cs.setString(3, registro);
            cs.setInt(4, objTipoServicio.getEstado());
            cs.registerOutParameter(5, java.sql.Types.CHAR);
            
         
            cs.execute();

            result = cs.getString(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

  

}
