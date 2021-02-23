/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.RubroDAO;
import ll.entidades.administracion.Rubro;

/**
 *
 * @author RenRio
 */
public class RubroNEG {

    private static RubroNEG _Instancia;

    private RubroNEG() {
    }

    public static RubroNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new RubroNEG();
        }
        return _Instancia;
    }

    public List<Rubro> obtenerRubro() throws Exception {
        return RubroDAO.Instancia().obtenerRubro();
    }

}
