/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.EstadoDAO;
import ll.entidades.administracion.Estado;

/**
 *
 * @author DAVID
 */
public class EstadoNEG {
    
    private static EstadoNEG _Instancia;

    private EstadoNEG() {
    }

    public static EstadoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new EstadoNEG();
        }
        return _Instancia;
    }
    
    public List<Estado> listar() throws Exception {
        return EstadoDAO.Instancia().listar();
    }
    
}
