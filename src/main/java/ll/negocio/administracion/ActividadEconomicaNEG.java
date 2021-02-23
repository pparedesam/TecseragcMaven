/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.ActividadEconomicaDAO;
import ll.entidades.administracion.ActividadEconomica;

/**
 *
 * @author CesGue
 */
public class ActividadEconomicaNEG {
    
    private static ActividadEconomicaNEG _Instancia;

    private ActividadEconomicaNEG() {
    }

    public static ActividadEconomicaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ActividadEconomicaNEG();
        }
        return _Instancia;
    }

    public List<ActividadEconomica> listarActividadEconomica() throws Exception{            
        
        return ActividadEconomicaDAO.Instancia().listarActividadEconomica();
    }
}
