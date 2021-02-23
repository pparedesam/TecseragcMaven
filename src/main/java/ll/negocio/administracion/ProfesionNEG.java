/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.ProfesionDAO;
import ll.entidades.administracion.Profesion;

/**
 *
 * @author paupar
 */
public class ProfesionNEG {

    private static ProfesionNEG _Instancia;

    private ProfesionNEG() {
    }

    public static ProfesionNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ProfesionNEG();
        }
        return _Instancia;
    }

    public List<Profesion> listar() throws Exception {
        return ProfesionDAO.Instancia().listar();
    }

    public String registrar(Profesion objProfesion,String accion) throws Exception {
        return ProfesionDAO.Instancia().registrarProfesion(objProfesion,accion);
    } 

}
