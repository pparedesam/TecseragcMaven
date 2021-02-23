/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import ll.accesodatos.administracion.BancosDAO;
import java.util.List;
import ll.entidades.administracion.Bancos;


/**
 *
 * @author Andree
 */
public class BancoNEG {
     private static BancoNEG _Instancia;

    private BancoNEG() {
    }

    public static BancoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new BancoNEG();
        }
        return _Instancia;
    }

    public List<Bancos> listarBancos() throws Exception {
        return BancosDAO.Instancia().listarBancos();
    }
}
