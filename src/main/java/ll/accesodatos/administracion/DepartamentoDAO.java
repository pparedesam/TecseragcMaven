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
import ll.entidades.administracion.Departamento;

/**
 *
 * @author DAVID
 */
public class DepartamentoDAO {
    
      private static DepartamentoDAO _Instancia;

    private DepartamentoDAO() {
    }

    public static DepartamentoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new DepartamentoDAO();
        }
        return _Instancia;
    }
    
 
    public List<Departamento> listarDptolog() throws Exception {

        List<Departamento> ListDepartamento = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT dl.IdDpto,dl.Nombre from DptoLog dl";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Departamento departamento = new Departamento();
                departamento.setCodigo(rs.getString("IdDpto"));
                departamento.setDescripcion(rs.getString("Nombre"));
               

              ListDepartamento.add(departamento);
            }

        } catch (Exception e) {
            throw e;
        }

        return ListDepartamento;
    }

    
}
