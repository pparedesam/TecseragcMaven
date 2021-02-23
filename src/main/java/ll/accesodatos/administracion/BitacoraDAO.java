/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Bitacora;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author CesGue
 */
public class BitacoraDAO {
    
    private static BitacoraDAO _Instancia;

    private BitacoraDAO() { }

    public static BitacoraDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new BitacoraDAO();
        }
        return _Instancia;
    }
    
    public Boolean registrarBitacora(Bitacora objBitacora) throws Exception {
        Connection objConexion = Connect.Instancia().getConnectBD();

        String result,sql;

        sql = "INSERT into bitacora (Registro,Fecha) values (?,GETDATE())";

        PreparedStatement pstm = objConexion.prepareStatement(sql);

        try {

            objConexion.setAutoCommit(false);
           
            pstm.setString(1, objBitacora.getRegistro());
            pstm.executeUpdate();
            objConexion.commit();
        } catch (SQLException e) {            
            objConexion.rollback();
            throw  e;
            
        } finally {
            objConexion.setAutoCommit(true);            
            pstm.close();
            objConexion.close();
            
        }

        return true;

    }
}
