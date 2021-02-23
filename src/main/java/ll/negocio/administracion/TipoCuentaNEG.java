/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import ll.entidades.administracion.TipoPagoInteres;
import ll.entidades.administracion.TipoCuenta;
import ll.entidades.administracion.CuentaPersona;
import ll.accesodatos.administracion.TipoCuentaDAO;
import java.util.List;

/**
 *
 * @author MarVer
 */
public class TipoCuentaNEG {
    
    private static TipoCuentaNEG _Instancia;

    private TipoCuentaNEG() {
    }

    public static TipoCuentaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoCuentaNEG();
        }
        return _Instancia;
    }

    public List<TipoCuenta> listarTipoCuenta(String TipMoneda, String TipoPersona) throws Exception {
        return TipoCuentaDAO.Instancia().listarTipoCuenta(TipMoneda, TipoPersona);
    }

    public TipoCuenta obtenerTasaxMonto(String IdTipCta, String TipMoneda, double Monto) throws Exception {
        return TipoCuentaDAO.Instancia().obtenerTasaxMonto(IdTipCta, TipMoneda, Monto);
    }

    public List<TipoPagoInteres> obtenerTipoPagoInteres() throws Exception {
        return TipoCuentaDAO.Instancia().obtenerTipoPagoInteres();
    }

    public List<CuentaPersona> obtenerCuentasIA(String idPersona, String tipMoneda) throws Exception {
        return TipoCuentaDAO.Instancia().obtenerCuentasIA(idPersona, tipMoneda);
    }
    
    public List<TipoCuenta> obtenerTipoCuentaPlazoFijo(String tipMoneda) throws Exception {
        return TipoCuentaDAO.Instancia().obtenerTipoCuentaPlazoFijo(tipMoneda);
    }
     public List<TipoCuenta> obtenerTiposCuentaMovCtas(String idProducto, String TipMoneda) throws Exception {
         return TipoCuentaDAO.Instancia().obtenerTiposCuentaMovCtas(idProducto, TipMoneda);
     }
}
