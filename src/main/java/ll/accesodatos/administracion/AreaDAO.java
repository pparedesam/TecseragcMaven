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
import ll.entidades.administracion.Area;

/**
 *
 * @author DAVID
 */
public class AreaDAO {

    private static AreaDAO _Instancia;

    private AreaDAO() {
    }

    public static AreaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new AreaDAO();
        }
        return _Instancia;
    }

    public List<Area> listarAreaEncuestacombo() throws Exception {

        List<Area> listaAreas = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select IdDpto,Nombre from  Dpto where IdDpto in (01,02,05)";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Area objArea = new Area();
                objArea.setCodigo(rs.getString("IdDpto"));
                objArea.setDescripcion(rs.getString("Nombre"));
                listaAreas.add(objArea);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaAreas;
    }
    
    public List<Area> listarArea() throws Exception {

        List<Area> listaArea = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select IdDpto,Nombre from DptoLog order by IdDpto ";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Area objArea = new Area();
                objArea.setCodigo(rs.getString("IdDpto"));
                objArea.setDescripcion(rs.getString("Nombre"));
                listaArea.add(objArea);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaArea;
    }
    
    
   public List<Area> obtenerDptoxOficina(String idOficina) throws Exception {

        List<Area> listArea = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select distinct Dpto.IdDpto,Dpto.Nombre from PuestosxDptoxOficina \n"
                    + "inner join Dpto on dpto.IdDpto = PuestosxDptoxOficina.IdDpto\n"
                    + "where IdOficina='" + idOficina + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Area objarea = new Area();
                objarea.setCodigo(rs.getString("IdDpto"));
                objarea.setDescripcion(rs.getString("Nombre"));

                listArea.add(objarea);
            }

        } catch (Exception e) {
            throw e;
        }

        return listArea;
    }

    public List<Area> listarAreaOficina(String idOficina) throws Exception {

        List<Area> ListArea = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select distinct d.IdDpto, d.nombre from PuestosxDptoxOficina pdo inner join Dpto d on d.IdDpto=pdo.IdDpto where pdo.IdOficina='" + idOficina + "' order by d.nombre";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Area area = new Area();
                area.setCodigo(rs.getString("IdDpto"));
                area.setDescripcion(rs.getString("Nombre"));

                ListArea.add(area);
            }

        } catch (Exception e) {
            throw e;
        }

        return ListArea;
    }
    

}
