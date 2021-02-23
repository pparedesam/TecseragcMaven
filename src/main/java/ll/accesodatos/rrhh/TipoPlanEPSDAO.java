/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.rrhh;

import ll.accesodatos.agente.Connect;

import ll.entidades.rrhh.TipoPlanEPS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class TipoPlanEPSDAO {

    private static TipoPlanEPSDAO _Instancia;

    private TipoPlanEPSDAO() {
    }

    public static TipoPlanEPSDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoPlanEPSDAO();
        }
        return _Instancia;
    }

    public List<TipoPlanEPS> listarTipoPlanEPS(String idTipoEPS) throws Exception {

        List<TipoPlanEPS> ListTipoPlanEPS= new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "Select * from PlanSaludEPS where IdTipoSeguroEPS='" + idTipoEPS + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
                objTipoPlanEPS.setIdEPS(rs.getInt("IdEPS"));
                objTipoPlanEPS.setDescripcion(rs.getString("Descripcionaportes"));

                ListTipoPlanEPS.add(objTipoPlanEPS);
            }

        } catch (Exception e) {
            throw e;
        }

        return ListTipoPlanEPS;
    }
}
