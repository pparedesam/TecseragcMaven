/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.TipoAperturaDAO;
import ll.entidades.administracion.TipoApertura;

/**
 *
 * @author CesGue
 */
public class TipoAperturaNEG {
    
  private static TipoAperturaNEG _Instancia;

    private TipoAperturaNEG() {
    }

    public static TipoAperturaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoAperturaNEG();
        }
        return _Instancia;
    }
    
     public List<TipoApertura> obtenerTiposApertura(String tipoPersona) throws Exception {
         return TipoAperturaDAO.Instancia().obtenerTiposApertura(tipoPersona);
     }
     
     public List<TipoApertura> obtenerTipoAperturaxId(String id) throws Exception {
         return TipoAperturaDAO.Instancia().obtenerTipoAperturaxId(id);
     };
}
