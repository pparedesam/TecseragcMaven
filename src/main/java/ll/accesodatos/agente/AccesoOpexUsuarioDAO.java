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
import ll.entidades.agentes.AccesoOpexUsuario;

/**
 *
 * @author CesGue
 */
public class AccesoOpexUsuarioDAO {
    
    private static AccesoOpexUsuarioDAO _Instancia;

    private AccesoOpexUsuarioDAO() {}

    public static AccesoOpexUsuarioDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new AccesoOpexUsuarioDAO();
        }
        return _Instancia;
    }
    
    public  List<AccesoOpexUsuario> obtenerOperacionxUsuario(Usuario objUsuario) throws Exception{
       List<AccesoOpexUsuario> listarAccesoOpexUsuario = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select IdOpe,TipMoneda,TieneAcceso  from AccesoOpexUsuario\n"
                    +"where IdUsuario='"+objUsuario.getIdUsuario()+"' and\n"
                    +"IdPuesto='"+objUsuario.getObjEmpleado().getObjPuesto().getIdPuesto()+"'\n"
                    +"and IdOficina='"+objUsuario.getObjEmpleado().getObjOficina().getIdOficina()+"'\n"
                    +"and IdDpto='"+objUsuario.getObjEmpleado().getObjArea().getCodigo()+"'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
               AccesoOpexUsuario objAccesoOpexUsuario = new AccesoOpexUsuario();
               objAccesoOpexUsuario.getObjMaeOperaciones().setIdOpe(rs.getString("IdOpe"));
               objAccesoOpexUsuario.getObjMoneda().setId(rs.getString("TipMoneda"));
               objAccesoOpexUsuario.setTieneAcceso(rs.getString("TieneAcceso"));
               listarAccesoOpexUsuario.add(objAccesoOpexUsuario);
            }

        } catch (Exception e) {
            throw e;
        }

        return listarAccesoOpexUsuario;
    }
    
}
