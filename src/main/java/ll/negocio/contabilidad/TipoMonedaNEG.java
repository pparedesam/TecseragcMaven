/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.TipoMonedaDAO;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.TipoMoneda;

/**
 *
 * @author PauPar
 */
public class TipoMonedaNEG {

    private static TipoMonedaNEG _Instancia;

    private TipoMonedaNEG() {
    }

    public static TipoMonedaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoMonedaNEG();
        }
        return _Instancia;
    }

    
    public List<TipoMoneda> listar(Oficina objOficina) throws Exception {
        return TipoMonedaDAO.Instancia().listar(objOficina);
    }
}
