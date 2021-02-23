/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import ll.accesodatos.administracion.PuestoDAO;
import ll.entidades.administracion.Puesto;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class PuestoNEG {

    private static PuestoNEG _Instancia;

    private PuestoNEG() {
    }

    public static PuestoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new PuestoNEG();
        }
        return _Instancia;
    }

    public List<Puesto> obtenerPstoxDptoxOficina(String idOficina, String idDpto) throws Exception {
        return PuestoDAO.Instancia().obtenerPstoxDptoxOficina(idOficina, idDpto);
    }

}
