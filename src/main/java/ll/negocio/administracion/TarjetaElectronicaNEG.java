/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.TarjetaElectronicaDAO;
import ll.entidades.administracion.TarjetaElectronica;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author paupar
 */
public class TarjetaElectronicaNEG {

    private static TarjetaElectronicaNEG _Instancia;

    private TarjetaElectronicaNEG() {
    }

    public static TarjetaElectronicaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new TarjetaElectronicaNEG();
        }
        return _Instancia;
    }

    public List<TarjetaElectronica> obtenerTarjetaElectronicaxPersona(String idPersona) throws Exception {

        try {
            return TarjetaElectronicaDAO.Instancia().obtenerTarjetaxPersona(idPersona);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String registrarTarjetaElectronica(TarjetaElectronica objTarjetaElectronica, Usuario objUsuario, String accion) throws Exception {
        return TarjetaElectronicaDAO.Instancia().registrarTarjetaElectronica(objTarjetaElectronica, objUsuario, accion);
    }

    public int anular(TarjetaElectronica objTarjetaElectronica) throws Exception {
        try {
            return TarjetaElectronicaDAO.Instancia().anular(objTarjetaElectronica);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String actualizarContrasenia(String idPersona, String Clave, String nuevaClave, String nroTarjeta) throws Exception {
        return TarjetaElectronicaDAO.Instancia().actualizarContrasenia(idPersona, Clave, nuevaClave, nroTarjeta);
    }

}
