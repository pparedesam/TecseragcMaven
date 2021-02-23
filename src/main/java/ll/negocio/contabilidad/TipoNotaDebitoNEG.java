/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.TipoNotaDebitoDAO;
import ll.entidades.contabilidad.TipoNotaDebito;

/**
 *
 * @author RenRio
 */
public class TipoNotaDebitoNEG {
      private static TipoNotaDebitoNEG _Instancia;

    private TipoNotaDebitoNEG() {
    }

    public static TipoNotaDebitoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoNotaDebitoNEG();
        }
        return _Instancia;
    }
    
    
    public List<TipoNotaDebito> listarTipoNotaDebito() throws Exception {
        return TipoNotaDebitoDAO.Instancia().listarTipoNotaDebito();
    }
}
