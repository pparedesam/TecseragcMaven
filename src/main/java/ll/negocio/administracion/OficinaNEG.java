/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.OficinaDAO;
import ll.entidades.administracion.Oficina;

/**
 *
 * @author CesGue
 */
public class OficinaNEG {
     private static OficinaNEG _Instancia;

    private OficinaNEG() {
    }

    public static OficinaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new OficinaNEG();
        }
        return _Instancia;
    }

    public List<Oficina> listarOficinas() throws Exception {
        return OficinaDAO.Instancia().listarOficinas();
    }
}
