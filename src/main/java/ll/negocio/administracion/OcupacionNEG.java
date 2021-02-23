/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.OcupacionDAO;
import ll.entidades.administracion.Ocupacion;


/**
 *
 * @author paupar
 */
public class OcupacionNEG {

    private static OcupacionNEG _Instancia;

    private OcupacionNEG() {
    }

    public static OcupacionNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new OcupacionNEG();
        }
        return _Instancia;
    }

    public List<Ocupacion> listar() throws Exception {
        return OcupacionDAO.Instancia().listar();
    }

    public String registrar(Ocupacion objOcupacion) throws Exception {
        return OcupacionDAO.Instancia().registrar(objOcupacion);
    }

    public Boolean delete(String idOcupacion) throws Exception {

        return OcupacionDAO.Instancia().delete(idOcupacion);
    }


}
