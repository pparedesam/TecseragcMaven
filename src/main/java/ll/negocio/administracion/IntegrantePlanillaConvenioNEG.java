/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.IntegrantePlanillaConvenioDAO;
import ll.entidades.administracion.IntegrantePlanillaConvenio;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author DAVID
 */
public class IntegrantePlanillaConvenioNEG {
    
    // <editor-fold defaultstate="collapsed" desc="Instancia Clase.">
    private static IntegrantePlanillaConvenioNEG _Instancia;

    private IntegrantePlanillaConvenioNEG() {
    }

    public static IntegrantePlanillaConvenioNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new IntegrantePlanillaConvenioNEG();
        }
        return _Instancia;
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Integrante Planilla.">
    public List<IntegrantePlanillaConvenio> obtenerIntegrantePlanillaConvenio(String IdProductoConvenio, String idPersonaApoderado) throws Exception{
      return  IntegrantePlanillaConvenioDAO.Instancia().obtenerIntegrantePlanillaConvenio(IdProductoConvenio, idPersonaApoderado);
    }
     // </editor-fold>
    
     public String registrarApoderadoPlanilla(IntegrantePlanillaConvenio objIntegrantePlanillaConvenio,Usuario objUsuario,String Accion) throws Exception{
         return IntegrantePlanillaConvenioDAO.Instancia().registrarApoderadoPlanilla(objIntegrantePlanillaConvenio, objUsuario, Accion);
     }
     
     public String eliminarApoderado(IntegrantePlanillaConvenio objIntegrantePlanillaConvenio,Usuario objUsuario,String Accion) throws Exception{
         return IntegrantePlanillaConvenioDAO.Instancia().eliminarApoderado(objIntegrantePlanillaConvenio, objUsuario, Accion);
     }
     
     
     
}
