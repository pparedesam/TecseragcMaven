/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.ChequeDAO;
import ll.accesodatos.contabilidad.MaeOperacionesDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.Cheque;
import ll.entidades.contabilidad.MaeOperaciones;

/**
 *
 * @author RenRio
 */
public class MaeOperacionesNEG {

    private static MaeOperacionesNEG _Instancia;

    private MaeOperacionesNEG() {
    }

    public static MaeOperacionesNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new MaeOperacionesNEG();
        }
        return _Instancia;
    }

    public List<Cheque> listarCheque(int criterio, String valor) throws Exception {
        return ChequeDAO.Instancia().listarCheque(criterio, valor);
    }
    
    public List<MaeOperaciones> listarTipoOperacionReciboCaja() throws Exception {
        return MaeOperacionesDAO.Instancia().listarTipoOperacionReciboCaja();
    }
     
   
}
