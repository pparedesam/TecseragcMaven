/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import ll.entidades.administracion.Parentesco;
import ll.accesodatos.administracion.ParentescoDAO;
import java.util.List;

/**
 *
 * @author MarVer
 */
public class ParentescoNEG {

    private static ParentescoNEG _Instancia;

    private ParentescoNEG() {
    }

    public static ParentescoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ParentescoNEG();
        }
        return _Instancia;
    }

    public  List<Parentesco> obtenerParentesco() throws Exception {
        try {
            return ParentescoDAO.Instancia().ObtenerParentesco();
        } catch (Exception e) {
            throw e;
        }

    }
}
