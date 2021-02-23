/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.AreaDAO;
import ll.entidades.administracion.Area;
import ll.accesodatos.administracion.BancosDAO;
import java.util.List;
import ll.entidades.administracion.Banco;


/**
 *
 * @author DAVID
 */
public class AreaNEG {

    private static AreaNEG _Instancia;

    private AreaNEG() {
    }

    public static AreaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new AreaNEG();
        }
        return _Instancia;
    }

    public List<Area> listarAreaEncuestacombo() throws Exception {
        return AreaDAO.Instancia().listarAreaEncuestacombo();
    }

    public List<Area> listarArea() throws Exception {
        return AreaDAO.Instancia().listarArea();
    }



    public List<Area> obtenerDptoxOficina(String idOficina) throws Exception {
        return AreaDAO.Instancia().obtenerDptoxOficina(idOficina);
    }

    public List<Area> listarAreaOficina(String idOficina) throws Exception {
        return AreaDAO.Instancia().listarAreaOficina(idOficina);
    }

}
