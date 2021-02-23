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
import ll.entidades.administracion.ActividadEconomica;

/**
 *
 * @author CesGue
 */
public class ActividadEconomicaDAO {
    
     private static ActividadEconomicaDAO _Instancia;

    private ActividadEconomicaDAO() {
    }

    public static ActividadEconomicaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ActividadEconomicaDAO();
        }
        return _Instancia;
    }

    public List<ActividadEconomica> listarActividadEconomica() throws Exception{

       
        List<ActividadEconomica> listaActividadEconomica = new ArrayList<>();

          try  (Connection cn = Connect.Instancia().getConnectBD()){

            String sql = "SELECT IdActEcon, Descripcion FROM  ACTIVIDADECONOMICA WHERE Estado='1' ORDER BY Descripcion ";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                ActividadEconomica objActividadEconomica = new ActividadEconomica();
                objActividadEconomica.setIdActEcon(rs.getString("IdActEcon"));
                objActividadEconomica.setDescripcion(rs.getString("Descripcion"));
                listaActividadEconomica.add(objActividadEconomica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaActividadEconomica;
    }
    
}
