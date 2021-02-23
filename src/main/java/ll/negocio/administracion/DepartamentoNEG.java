/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.DepartamentoDAO;
import ll.entidades.administracion.Departamento;

/**
 *
 * @author DAVID
 */
public class DepartamentoNEG {
    
      private static DepartamentoNEG _Instancia;

    private DepartamentoNEG() {
    }

    public static DepartamentoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new DepartamentoNEG();
        }
        return _Instancia;
    }
    
    public List<Departamento> listarDptolog() throws Exception {
        return DepartamentoDAO.Instancia().listarDptolog();
    }
    
}
