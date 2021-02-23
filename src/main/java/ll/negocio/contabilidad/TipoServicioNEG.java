/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;

import ll.accesodatos.contabilidad.TipoServicioDAO;

import ll.entidades.contabilidad.TipoServicio;

/**
 *
 * @author PauPar
 */
public class TipoServicioNEG {

    private static TipoServicioNEG _Instancia;

    private TipoServicioNEG() {
    }

    public static TipoServicioNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoServicioNEG();
        }
        return _Instancia;
    }

    public List<TipoServicio> listar() throws Exception {
        return TipoServicioDAO.Instancia().listar();
    }

    public String registrar(TipoServicio objTipoServicio, String registro ) throws Exception {
        return TipoServicioDAO.Instancia().registrar(objTipoServicio, registro);
    }

  

}
