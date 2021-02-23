/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;


import ll.accesodatos.rrhh.TipoEPSDAO;
import ll.accesodatos.rrhh.TipoPlanEPSDAO;

import ll.entidades.rrhh.TipoEPS;
import ll.entidades.rrhh.TipoPlanEPS;

import java.util.List;

/**
 *
 * @author RenRio
 */
public class TipoPlanEPSNEG {

    private static TipoPlanEPSNEG _Instancia;

    private TipoPlanEPSNEG() {
    }

    public static TipoPlanEPSNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoPlanEPSNEG();
        }
        return _Instancia;
    }

    public List<TipoPlanEPS> listaTipoPlanEPS(String idTipoPlanEPS) throws Exception {
        return TipoPlanEPSDAO.Instancia().listarTipoPlanEPS(idTipoPlanEPS);
    }

   

}
