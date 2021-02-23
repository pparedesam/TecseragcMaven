/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;

import ll.negocio.administracion.*;
import java.util.List;
import ll.accesodatos.administracion.OficinaDAO;
import ll.accesodatos.rrhh.SistemaPensionesDAO;
import ll.entidades.administracion.Oficina;
import ll.entidades.rrhh.SistemaPensiones;

/**
 *
 * @author CesGue
 */
public class SistemaPensionesNEG {
     private static SistemaPensionesNEG _Instancia;

    private SistemaPensionesNEG() {
    }

    public static SistemaPensionesNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new SistemaPensionesNEG();
        }
        return _Instancia;
    }

    public List<SistemaPensiones> listarSistemaPensiones() throws Exception {
        return SistemaPensionesDAO.Instancia().listarSistemaPensiones();
    }
}
