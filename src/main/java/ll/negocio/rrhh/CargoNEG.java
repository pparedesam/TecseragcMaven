/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;

import ll.accesodatos.rrhh.CargoDAO;

import ll.entidades.rrhh.Cargo;

import java.util.List;

/**
 *
 * @author RenRio
 */
public class CargoNEG {

    private static CargoNEG _Instancia;

    private CargoNEG() {
    }

    public static CargoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new CargoNEG();
        }
        return _Instancia;
    }

    public List<Cargo> listarCargo() throws Exception {
        return CargoDAO.Instancia().listarCargos();
    }

   

}
