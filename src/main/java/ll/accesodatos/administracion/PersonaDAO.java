/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Persona;

/**
 *
 * @author RenRio
 */
public class PersonaDAO {

    private static PersonaDAO _Instancia;

    private PersonaDAO() {
    }

    public static PersonaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaDAO();
        }
        return _Instancia;
    }

      public List<Persona> obtenerSoloSocioPersonaNaturalJuridica(Persona objPersona) throws Exception {

        List<Persona> listaPersona = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Persona_sp_ObtenerPersonaNatJur (?,?)}");

            cstmt.setString("nombre", objPersona.getNombres());
            cstmt.setString("nroDocumento", objPersona.getNroDocumento());

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Persona objPersonas = new Persona();
                objPersonas.setIdPersona(rs.getString("IdPersona"));
                objPersonas.setNombres(rs.getString("Nombres"));
                objPersonas.setNroDocumento(rs.getString("NroDocIdentidad"));
                
                listaPersona.add(objPersonas);
            }

        } catch (Exception ex) {
            throw ex;
        }

        return listaPersona;
    }
    
}
