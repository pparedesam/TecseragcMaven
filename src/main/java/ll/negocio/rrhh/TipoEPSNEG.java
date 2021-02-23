/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;


import ll.accesodatos.rrhh.TipoEPSDAO;
import ll.entidades.administracion.Usuario;

import ll.entidades.operaciones.DocumentoGenerado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ll.entidades.rrhh.TipoEPS;

/**
 *
 * @author RenRio
 */
public class TipoEPSNEG {

    private static TipoEPSNEG _Instancia;

    private TipoEPSNEG() {
    }

    public static TipoEPSNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoEPSNEG();
        }
        return _Instancia;
    }

    public List<TipoEPS> listaTipoEPS() throws Exception {

        return TipoEPSDAO.Instancia().listaTipoEPS();
    }

   

}
