/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import ll.accesodatos.administracion.BitacoraDAO;
import ll.entidades.administracion.Bitacora;

/**
 *
 * @author CesGue
 */
public class BitacoraNEG {
     private static BitacoraNEG _Instancia;

    private BitacoraNEG() {
    }

    public static BitacoraNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new BitacoraNEG();
        }
        return _Instancia;
    }

//    public List<Bitacora> listar() throws Exception {
//        return CIIUDAO.Instancia().listar();
//    }
//    
     public Boolean registrar(Bitacora objBitacora) throws Exception {
       return BitacoraDAO.Instancia().registrarBitacora(objBitacora);
    }
}
