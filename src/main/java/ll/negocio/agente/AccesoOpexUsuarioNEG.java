/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.agente;

import java.util.List;
import ll.accesodatos.agente.AccesoOpexUsuarioDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.agentes.AccesoOpexUsuario;

/**
 *
 * @author CesGue
 */
public class AccesoOpexUsuarioNEG {
    
    private static AccesoOpexUsuarioNEG _Instancia;

    private AccesoOpexUsuarioNEG() {}

    public static AccesoOpexUsuarioNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new AccesoOpexUsuarioNEG();
        }
        return _Instancia;
    }
    
    public  List<AccesoOpexUsuario> obtenerOperacionxUsuario(Usuario objUsuario) throws Exception{
     
        return AccesoOpexUsuarioDAO.Instancia().obtenerOperacionxUsuario(objUsuario);
    }
    
    
    public String[][] tieneAcceso(List<AccesoOpexUsuario> lista, String[][] listaAcceso) {
        
        for (int i = 0; i < listaAcceso.length;i++) {
            for (int j = 0; j < lista.size(); j++) {
               
                if (lista.get(j).getObjMaeOperaciones().getIdOpe().equalsIgnoreCase(listaAcceso[i][0])) {                    
                    listaAcceso[i][1] = lista.get(j).getTieneAcceso();
                }
            }
        }
        return listaAcceso;
    }
}
