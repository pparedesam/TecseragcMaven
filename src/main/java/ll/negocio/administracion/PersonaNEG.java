/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.PersonaDAO;
import ll.entidades.administracion.Persona;

/**
 *
 * @author RenRio
 */
public class PersonaNEG {

    private static PersonaNEG _Instancia;

    private PersonaNEG() {
    }

    public static PersonaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaNEG();
        }
        return _Instancia;
    }

    public List<Persona> obtenerPersonaNaturalJuridica(int tipoBusqueda, String criterio) throws Exception {
        Persona objPersona = new Persona();
        switch (tipoBusqueda) {
            case 1:
                objPersona.setNroDocumento(criterio);
                break;
            case 2:
                objPersona.setNombres(criterio);
                break;
        }
        return PersonaDAO.Instancia().obtenerSoloSocioPersonaNaturalJuridica(objPersona);
    }

}
