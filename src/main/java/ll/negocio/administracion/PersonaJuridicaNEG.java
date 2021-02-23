/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.PersonaJuridicaDAO;
import ll.accesodatos.administracion.PersonaNaturalDAO;
import ll.entidades.administracion.Persona;
import ll.entidades.administracion.PersonaJuridica;
import ll.entidades.agentes.Parametro;

/*
 *
 * @author MarVer
 */
public class PersonaJuridicaNEG {

    private static PersonaJuridicaNEG _Instancia;

    private PersonaJuridicaNEG() {
    }

    public static PersonaJuridicaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaJuridicaNEG();
        }
        return _Instancia;
    }

    public PersonaJuridica obtenerPersonaJuridica(String idPersona) throws Exception {
        try {
            return PersonaJuridicaDAO.Instancia().ObtenerPersonaJuridica(idPersona);
        } catch (Exception e) {
            throw e;
        }

    }

    public List<PersonaJuridica> listarPersona(int criterio, String valor) throws Exception {
        return PersonaJuridicaDAO.Instancia().listarPersona(criterio, valor);
    }

    public boolean registrarPersonaJuridica(PersonaJuridica objPersonaJuridica, String Accion) throws Exception {

        try {
            return PersonaJuridicaDAO.Instancia().insertar(objPersonaJuridica, Accion);

        } catch (Exception ex) {
            throw ex;
        }

    }

    public List<PersonaJuridica> listarPersonas(int criterio, String valor) throws Exception {

        switch (criterio) {
            case 1:
                return PersonaJuridicaDAO.Instancia().listarPersonaxRUC(valor);

            case 2:
                return PersonaJuridicaDAO.Instancia().listarPersonaxRS(valor);

            default:
                return PersonaJuridicaDAO.Instancia().listarPersonaxTarjeta(valor);

        }

    }

    public PersonaJuridica ObtenerPersonaNoSocioNoEmpleado(String dni, String tipo) throws Exception {
        return PersonaJuridicaDAO.Instancia().ObtenerPersonaNoSocioNoEmpleado(dni, tipo);
    }

    public List<PersonaJuridica> obtenerPersonaJuridicaTEXT(String criterio) throws Exception {
        return PersonaJuridicaDAO.Instancia().obtenerPersonaJuridicaTEXT(criterio);
    }

    public List<PersonaJuridica> obtenerPersonaNaturalJuridica(String Criterio, String TipoBusqueda, String TipoPersona) throws Exception {
        return PersonaJuridicaDAO.Instancia().obtenerPersonaNaturalJuridica(Criterio, TipoBusqueda, TipoPersona);
    }

    public List<Persona> obtenerPersonaNaturalJuridicaText(String Criterio) throws Exception {
        return PersonaJuridicaDAO.Instancia().obtenerPersonaNaturalJuridicaText(Criterio);
    }

    public List<PersonaJuridica> listarPersonaJuridicaNoBancos(int criterio, String valor) throws Exception {
        return PersonaJuridicaDAO.Instancia().listarPersonaJuridicaNoBancos(criterio, valor);
    }
}

