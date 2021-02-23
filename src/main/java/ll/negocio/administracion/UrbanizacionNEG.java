/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.UrbanizacionDAO;
import ll.entidades.administracion.Urbanizacion;

/**
 *
 * @author paupar
 */
public class UrbanizacionNEG {

    private static UrbanizacionNEG _Instancia;

    private UrbanizacionNEG() {
    }

    public static UrbanizacionNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new UrbanizacionNEG();
        }
        return _Instancia;
    }

    public List<Urbanizacion> listar() throws Exception {
        return UrbanizacionDAO.Instancia().listar();
    }

    public String registrar(Urbanizacion objUrbanizacion) throws Exception {
        return UrbanizacionDAO.Instancia().registrar(objUrbanizacion);
    }

    public Boolean delete(String idUrbanizacion) throws Exception {

        return UrbanizacionDAO.Instancia().delete(idUrbanizacion);
    }

}
