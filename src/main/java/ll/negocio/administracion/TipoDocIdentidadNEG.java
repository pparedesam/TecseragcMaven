/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.TipoDocIdentidadDAO;
import ll.entidades.administracion.TipoDocIdentidad;

/**
 *
 * @author PauPar
 */
public class TipoDocIdentidadNEG {
    
     private static TipoDocIdentidadNEG _Instancia;

    private TipoDocIdentidadNEG() {
    }

    public static TipoDocIdentidadNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoDocIdentidadNEG();
        }
        return _Instancia;
    }
    
     public List<TipoDocIdentidad> listarTipoDocIdentidad(String tipoPersona) throws Exception
     {
         return TipoDocIdentidadDAO.Instancia().listarTipoDocIdentidad(tipoPersona);
     }
    
}
