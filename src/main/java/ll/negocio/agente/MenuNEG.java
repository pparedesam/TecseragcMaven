/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.agente;

import java.util.List;
import ll.accesodatos.agente.MenuDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.agentes.Menu;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author paupar
 */
public class MenuNEG {

    private static MenuNEG _Instancia;

    private MenuNEG() {
    }

    public static MenuNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new MenuNEG();
        }
        return _Instancia;
    }

    public List<Menu> obtenerMenu(Usuario objUsuario) throws Exception {

        return MenuDAO.Instancia().obtenerMenu(objUsuario);

    }

    public List<Menu> obtenerListaMenu(String idOficina, String idDpto, String IdPsto) throws Exception {

        return MenuDAO.Instancia().obtenerListaMenu(idOficina, idDpto, IdPsto);

    }

    public String actualizarMenu(ArrayList<HashMap<String, String>> listaMenu) throws Exception {

        String result = null;
        try {
            for (int i = 0; i < listaMenu.size(); i++) {

                Menu objMenu = new Menu();
                objMenu.getObjOficina().setIdOficina(listaMenu.get(i).get("idOficina"));
                objMenu.getObjArea().setCodigo(listaMenu.get(i).get("idDpto"));
                objMenu.getObjPuesto().setIdPuesto(listaMenu.get(i).get("idPsto"));
                objMenu.setCodigo(Integer.parseInt(listaMenu.get(i).get("idMenu")));
                objMenu.setEstado(Integer.parseInt(listaMenu.get(i).get("estado")));
                result = MenuDAO.Instancia().actualizarMenu(objMenu);

            }
        } catch (Exception e) {
            throw e;

        }

        return result;
    }

}
