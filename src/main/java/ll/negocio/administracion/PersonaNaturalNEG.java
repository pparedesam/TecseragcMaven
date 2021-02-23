package ll.negocio.administracion;

import ll.accesodatos.administracion.PersonaNaturalDAO;
import java.util.List;
import ll.entidades.administracion.PersonaNatural;

public class PersonaNaturalNEG {

    private static PersonaNaturalNEG _Instancia;

    private PersonaNaturalNEG() {
    }

    public static PersonaNaturalNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaNaturalNEG();
        }
        return _Instancia;
    }

    public PersonaNatural ObtenerPersonaSocio(String dni) throws Exception {
        return PersonaNaturalDAO.Instancia().ObtenerPersonaSocio(dni);
    }

    public PersonaNatural ObtenerPersonaGeneralNoEmpleado(String dni) throws Exception {
        return PersonaNaturalDAO.Instancia().ObtenerPersonaGeneralNoEmpleado(dni);
    }

    public String registrarPersonaNatural(PersonaNatural objPersonaNatural) throws Exception {
        return PersonaNaturalDAO.Instancia().insertar(objPersonaNatural);
    }

    public List<PersonaNatural> listarPersona(int criterio, String valor) throws Exception {
        return PersonaNaturalDAO.Instancia().listaPersonaNatural(criterio, valor);
    }

    public List<PersonaNatural> listarPersonas(int criterio, String valor) throws Exception {

        switch (criterio) {
            case 1:
                return PersonaNaturalDAO.Instancia().listarPersonaxDNISocio(valor);

            case 2:
                return PersonaNaturalDAO.Instancia().listarPersonaxNombreSocio(valor);

            default:
                return PersonaNaturalDAO.Instancia().listarPersonaxTarjetaSocio(valor);

        }

    }

    public PersonaNatural ObtenerPersonaNatural(String IdPersona) throws Exception {

        return PersonaNaturalDAO.Instancia().ObtenerPersonaNatural(IdPersona);
    }

    public PersonaNatural ObtenerPersonaNoSocioNoEmpleado(String dni, String tipoPersona) throws Exception {
        return PersonaNaturalDAO.Instancia().ObtenerPersonaNoSocioNoEmpleado(dni, tipoPersona);
    }

    public PersonaNatural ObtenerPersonaNoEmpleado(String dni, String tipoPersona) throws Exception {
        return PersonaNaturalDAO.Instancia().ObtenerPersonaNoEmpleado(dni, tipoPersona);
    }

    public List<PersonaNatural> obtenerPersonaNaturalTEXT(String criterio) throws Exception {
        return PersonaNaturalDAO.Instancia().obtenerPersonaNaturalTEXT(criterio);
    }

}
