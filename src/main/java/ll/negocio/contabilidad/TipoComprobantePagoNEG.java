/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.TipoComprobantePagoDAO;
import ll.entidades.contabilidad.TipoComprobantePago;

/**
 *
 * @author PauPar
 */
public class TipoComprobantePagoNEG {
        private static TipoComprobantePagoNEG _Instancia;

    private TipoComprobantePagoNEG() {
    }

    public static TipoComprobantePagoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoComprobantePagoNEG();
        }
        return _Instancia;
    }

    public List<TipoComprobantePago> listar() throws Exception {
        return TipoComprobantePagoDAO.Instancia().listar();
    }

    public List<TipoComprobantePago> listarComprobanteCompra() throws Exception {
        return TipoComprobantePagoDAO.Instancia().listarComprobanteCompra();
    }
    
}
