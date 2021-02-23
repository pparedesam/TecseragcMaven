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

import ll.entidades.contabilidad.MaeOperaciones;
import ll.entidades.contabilidad.Cheque;


/**
 *
 * @author RenRio
 */
public class MaeOperacionesDAO {

    private static MaeOperacionesDAO _Instancia;

    private MaeOperacionesDAO() {
    }

    public static MaeOperacionesDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new MaeOperacionesDAO();
        }
        return _Instancia;
    }

    public List<MaeOperaciones> listarTipoOperacionReciboCaja() throws Exception {

        List<MaeOperaciones> listaTipoOperacionReciboCaja = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT\n"
                    + "	IdOpe\n"
                    + "	,Operacion\n"
                    + "FROM\n"
                    + "	MaeOperaciones\n"
                    + "WHERE\n"
                    + "	IdTipOpe IN ('03','04')\n"
                    + "	AND Activo='1'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                MaeOperaciones objMaeOperaciones = new MaeOperaciones();

                objMaeOperaciones.setIdDoc(rs.getString("IdOpe"));
                objMaeOperaciones.setOperacion(rs.getString("Operacion"));

                listaTipoOperacionReciboCaja.add(objMaeOperaciones);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaTipoOperacionReciboCaja;

    }
}
