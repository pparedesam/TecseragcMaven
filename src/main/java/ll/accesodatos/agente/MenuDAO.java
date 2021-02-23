/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.agente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.entidades.administracion.Usuario;
import ll.entidades.agentes.Menu;
import java.sql.CallableStatement;
import java.sql.SQLException;
import ll.entidades.agentes.MainMenu;

public class MenuDAO {

    private static MenuDAO _Instancia;

    private MenuDAO() {
    }

    public static MenuDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new MenuDAO();
        }
        return _Instancia;
    }

    public List<Menu> obtenerMenu(Usuario objUsuario) throws Exception {

        List<Menu> lstMenu = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            String sql = "select N1.IdMenu, N1.Descripcion, N1.URL, N1.Dependiente, N1.Nivel, N2.Dependiente as Dependiente2,Estado, Icon = isnull(N1.Icon,'')  from MainMenu N1 "
                    + "inner join MainMenu N2 on N1.Dependiente=N2.IdMenu "
                    + "inner join MainMenu N3 on N2.Dependiente=N3.IdMenu "
                    + "join MenuxPuestosxDptoxOficina MP on N1.IdMenu = MP.IdMenu "
                    + "where MP.IdOficina = '" + objUsuario.getObjEmpleado().getObjOficina().getIdOficina() + "' and MP.IdPuesto = '" + objUsuario.getObjEmpleado().getObjPuesto().getIdPuesto() + "' and MP.IdDpto = '" + objUsuario.getObjEmpleado().getObjArea().getCodigo() + "' and MP.estado = 1"
                    + " order by cast(N1.Descripcion as nvarchar(45)), N3.Dependiente,N1.subnivel,N1.Nivel";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Menu objMenu = new Menu();
                objMenu.setCodigo(rs.getInt("IdMenu"));
                objMenu.setDescripcion(rs.getString("Descripcion"));
                objMenu.setUrl(rs.getString("URL"));
                objMenu.setDependiente(rs.getInt("Dependiente"));
                objMenu.setNivel(rs.getInt("Nivel"));
                objMenu.setDependiente2(rs.getInt("Dependiente2"));
                objMenu.setEstado(rs.getInt("Estado"));
                objMenu.setIcon(rs.getString("Icon"));

                lstMenu.add(objMenu);
            }

        } catch (Exception e) {

            throw e;

        }

        return lstMenu;

    }

    public List<Menu> obtenerListaMenu(String idOficina, String idDpto, String idPsto) throws Exception {

        List<Menu> lstMenu = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            String sql = "select  N1.IdMenu, N1.Descripcion, N1.URL, N1.Dependiente, N1.Nivel,\n"
                    + "DescripcionN2=case when (LEN(N1.Dependiente)=1 or LEN(N1.Dependiente)=2) then '' else N2.Descripcion end,\n"
                    + " N2.Dependiente as Dependiente2,DescripcionN1=N3.Descripcion,\n"
                    + "Estado= case when c.Estado is null then 0 else c.Estado end,Icon = isnull(N1.Icon,'') \n"
                    + "from MainMenu N1\n"
                    + "inner join MainMenu N2 on N1.Dependiente=N2.IdMenu\n"
                    + "inner join MainMenu N3 on N2.Dependiente=N3.IdMenu\n"
                    + "left join (Select mm.IdMenu,mm.Descripcion,MenuxPuestosxDptoxOficina.Estado from MenuxPuestosxDptoxOficina \n"
                    + "inner join MainMenu mm on mm.IdMenu = MenuxPuestosxDptoxOficina.IdMenu\n"
                    + "where IdOficina='" + idOficina + "' and IdDpto='" + idDpto + "' and IdPuesto='" + idPsto + "' and MenuxPuestosxDptoxOficina.Estado='1') c on c.IdMenu=N1.IdMenu\n"
                    + "order by cast(N1.Descripcion as nvarchar(45)), N3.Dependiente,N1.subnivel,N1.Nivel";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Menu objMenu = new Menu();
                objMenu.setCodigo(rs.getInt("IdMenu"));
                objMenu.setDescripcion(rs.getString("Descripcion"));
                objMenu.setUrl(rs.getString("URL"));
                objMenu.setDependiente(rs.getInt("Dependiente"));
                objMenu.setNivel(rs.getInt("Nivel"));
                objMenu.setDependiente2(rs.getInt("Dependiente2"));
                objMenu.setDescripcionN1(rs.getString("DescripcionN1"));
                objMenu.setDescripcionN2(rs.getString("DescripcionN2"));
                objMenu.setEstado(rs.getInt("Estado"));
                objMenu.setIcon(rs.getString("Icon"));

                lstMenu.add(objMenu);
            }

        } catch (Exception e) {

            throw e;

        }

        return lstMenu;

    }

    public String actualizarMenu(Menu objMenu) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_sistemas_ActualizarMenu(?,?,?,?,?,?)}");

            cs.setString(1, objMenu.getObjOficina().getIdOficina());
            cs.setString(2, objMenu.getObjArea().getCodigo());
            cs.setString(3, objMenu.getObjPuesto().getIdPuesto());
            cs.setInt(4, objMenu.getCodigo());
            cs.setInt(5, objMenu.getEstado());
            cs.setString(6, result);
            cs.registerOutParameter(6, java.sql.Types.INTEGER);
            cs.execute();
            result = cs.getString(6);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

}
