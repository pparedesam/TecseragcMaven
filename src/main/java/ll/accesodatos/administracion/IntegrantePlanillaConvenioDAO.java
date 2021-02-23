/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.IntegrantePlanillaConvenio;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author DAVID
 */
public class IntegrantePlanillaConvenioDAO {
    
    // <editor-fold defaultstate="collapsed" desc="Instancia Clase.">
    private static IntegrantePlanillaConvenioDAO _Instancia;

    private IntegrantePlanillaConvenioDAO() {
    }

    public static IntegrantePlanillaConvenioDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new IntegrantePlanillaConvenioDAO();
        }
        return _Instancia;
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Integrante Planilla.">
    public List<IntegrantePlanillaConvenio> obtenerIntegrantePlanillaConvenio(String IdProductoConvenio, String IdPersonaApoderado) throws Exception{
        List<IntegrantePlanillaConvenio> listIntegrantePlanillaConvenios = new ArrayList<>();
        

        String sql;

           try (Connection cn = Connect.Instancia().getConnectBD()) {
               sql = "SELECT ip.IdPersonaApoderado, ip.IdPersonaPlanilla,ip.IdProductoConvenio,ip.Ptmo AS Prestamo,ip.Ap AS Aporte,ip.Fm AS FondoMourtorio,\n"
                       + "  (rtrim(pnIA.primerapellido)+SPACE(1)+rtrim(pnIA.segundoapellido)+SPACE(1)+rtrim(pnIA.prenombres)) as Apoderado,\n"
                       + "  (rtrim(pnI.primerapellido)+SPACE(1)+rtrim(pnI.segundoapellido)+SPACE(1)+rtrim(pnI.prenombres)) as Integrante, \n"
                       + "  isnull(s.IdSocio,'') as Cuenta,pc.NomProducto\n"
                       + "  FROM IntegrantePlanilla ip  \n"
                       + "  INNER JOIN ApoderadoPlanilla ap ON (ip.IdPersonaApoderado = ap.IdPersonaApoderado)\n"
                       + "  inner join PersonaNatural pnIA on  ap.IdPersonaApoderado=pnIA.IdPersona\n"
                       + "  INNER JOIN PersonaNatural pnI ON   ip.IdPersonaPlanilla=pnI.IdPersona   \n"
                       + "  LEFT JOIN Socio s ON ip.IdPersonaPlanilla=s.IdPersona  \n"
                       + "  INNER JOIN ProductoCrediticio pc ON ap.IdProductoConvenio=pc.IdTipoProducto\n"
                       + "  WHERE ip.IdProductoConvenio='"+IdProductoConvenio+"' AND ip.IdPersonaApoderado='"+IdPersonaApoderado+"'\n"
                       + "  ORDER BY Integrante";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                
                IntegrantePlanillaConvenio objIntegrantePlanillaConvenio = new IntegrantePlanillaConvenio();
             
                objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getObjSocio().getObjPersonaNatural().setIdPersona(rs.getString("IdPersonaApoderado"));                
                objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getObjSocio().getObjPersonaNatural().setNombres(rs.getString("Apoderado"));
                objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getObjCrediticio().setIdTipProducto(rs.getString("IdProductoConvenio"));
                objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getObjCrediticio().setNomProducto(rs.getString("NomProducto"));
                objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().setIdPersona(rs.getString("IdPersonaPlanilla"));
                objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().setNombres(rs.getString("Integrante"));
                objIntegrantePlanillaConvenio.getObjSocio().setIdSocio(rs.getString("Cuenta"));
                objIntegrantePlanillaConvenio.setPrestamo(rs.getString("Prestamo"));
                objIntegrantePlanillaConvenio.setAporte(rs.getString("Aporte"));
                objIntegrantePlanillaConvenio.setFondoMortuorio(rs.getString("FondoMourtorio"));
             
                listIntegrantePlanillaConvenios.add(objIntegrantePlanillaConvenio);
            }

         } catch (Exception e) {
            throw e;
        }

        return listIntegrantePlanillaConvenios;
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Registrar Integrante Planilla.">
    public String registrarApoderadoPlanilla(IntegrantePlanillaConvenio objIntegrantePlanillaConvenio,Usuario objUsuario,String Accion) throws Exception{
        
        String Result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {
            CallableStatement cstmt;
           
            cstmt  = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_upd_del_IntegrantePlanilla (?,?,?,?,?,?,?,?,?)}");   
                            
            cstmt.setString("Accion", Accion);
            cstmt.setString("IdPersonaApoderado",objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getIdPersonaApoderado());
            cstmt.setString("IdPersonaPlanilla",objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().getIdPersona());
            cstmt.setString("IdProductoConvenio",objIntegrantePlanillaConvenio.getObjCrediticio().getIdTipProducto());
            cstmt.setString("Ptmo",objIntegrantePlanillaConvenio.getPrestamo());
            cstmt.setString("Ap",objIntegrantePlanillaConvenio.getAporte());        
            cstmt.setString("Fm",objIntegrantePlanillaConvenio.getFondoMortuorio());
            cstmt.setString("IdUsuario",objUsuario.getIdUsuario());          
            cstmt.registerOutParameter("resultado", java.sql.Types.VARCHAR);

            cstmt.execute();
             Result = cstmt.getString("resultado"); 

        } catch (Exception e) {

            e.getMessage();
            throw e;
        }

        return Result;
        
    }
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Eliminar Integrante Planilla.">
    public String eliminarApoderado(IntegrantePlanillaConvenio objIntegrantePlanillaConvenio,Usuario objUsuario,String Accion) throws Exception{
        
         String Result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {
            
            CallableStatement cstmt;
            cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_upd_del_IntegrantePlanilla (?,?,?,?,?,?,?,?,?)}");
            
            cstmt.setString("Accion", "delete");
            cstmt.setString("IdPersonaApoderado",objIntegrantePlanillaConvenio.getObjApoderadoPlanillaConvenio().getIdPersonaApoderado());
            cstmt.setString("IdPersonaPlanilla",objIntegrantePlanillaConvenio.getObjSocio().getObjPersonaNatural().getIdPersona());
            cstmt.setString("IdProductoConvenio",objIntegrantePlanillaConvenio.getObjCrediticio().getIdTipProducto());
            cstmt.setNull("Ptmo",java.sql.Types.VARCHAR);
            cstmt.setNull("Ap",java.sql.Types.VARCHAR);
            cstmt.setNull("Fm",java.sql.Types.VARCHAR);
            cstmt.setString("IdUsuario",objUsuario.getIdUsuario());          
            cstmt.registerOutParameter("resultado", java.sql.Types.VARCHAR);           
          

            cstmt.execute();
            Result = cstmt.getString("resultado"); 

        } catch (Exception e) {

            e.getMessage();
            throw e;
        }

        return Result;
        
    }
    // </editor-fold>
}
    

