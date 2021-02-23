/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.TipoNotaCreditoDAO;
import ll.entidades.contabilidad.TipoNotaCredito;

/**
 *
 * @author PauPar
 */
public class TipoNotaCreditoNEG {

    private static TipoNotaCreditoNEG _Instancia;

    private TipoNotaCreditoNEG() {
    }

    public static TipoNotaCreditoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoNotaCreditoNEG();
        }
        return _Instancia;
    }
    
    
    public List<TipoNotaCredito> listar() throws Exception {
        return TipoNotaCreditoDAO.Instancia().listar();
    }

}
