/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import ll.entidades.administracion.Parentesco;
import java.sql.*;
import java.util.*;
import ll.accesodatos.agente.Connect;

/**
 *
 * @author MarVer
 */
public class ParentescoDAO {
    
    
   private static ParentescoDAO _Instancia;

    private ParentescoDAO() {
    }

    public static ParentescoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ParentescoDAO();
        }
        return _Instancia;
    }
    
    
     public List<Parentesco> ObtenerParentesco()
            throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        List<Parentesco> lstParentesco = new ArrayList<>();

        String Sql = "select IdParentesco,Parentesco from Parentesco";

        try {

            PreparedStatement pstm = objConexion.prepareStatement(Sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Parentesco objParentesco = new Parentesco();

                objParentesco.setIdParentesco(rs.getString("idparentesco"));
                objParentesco.setParentesco(rs.getString("parentesco"));

                lstParentesco.add(objParentesco);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            objConexion.close();
        }

        return lstParentesco;

    }
}
    