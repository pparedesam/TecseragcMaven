package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.EmpleadoDAO;
import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Usuario;

public class EmpleadoNEG {

    private static EmpleadoNEG _Instancia;

    private EmpleadoNEG() {
    }

    public static EmpleadoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new EmpleadoNEG();
        }
        return _Instancia;
    }

    public Usuario verficarAcceso(String idUsuario, String clave) throws Exception {
        return EmpleadoDAO.Instancia().verficarAcceso(idUsuario, clave);
    }

    public boolean cambioClave(Usuario objUsuario, String claveAnt, String claveNew) throws Exception {

        return EmpleadoDAO.Instancia().cambioClave(objUsuario, claveAnt, claveNew);
    }

    public List<Empleado> ObtenerEncargadoBien(String nombres) throws Exception {

        try {
            return EmpleadoDAO.Instancia().ObtenerEncargadoBien(nombres);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Empleado> obtenerPersonal(String Criterio, String TipoBusqueda) throws Exception {
        return EmpleadoDAO.Instancia().obtenerPersonal(Criterio, TipoBusqueda);
    }

    public List<Usuario> obtenerUsuarioMarcacion(String Criterio, String TipoBusqueda) throws Exception {
        return EmpleadoDAO.Instancia().obtenerUsuarioMarcacion(Criterio, TipoBusqueda);
    }

    public boolean vinculacionMarcacion(Usuario objUsuario) throws Exception {

        return EmpleadoDAO.Instancia().vinculacionMarcacion(objUsuario);
    }

    public List<Empleado> obtenerPersonalCobranza(String idoficina) throws Exception {
        try {
            return EmpleadoDAO.Instancia().obtenerPersonalCobranza(idoficina);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empleado> listaEmpleadoxCargo(Usuario objUsuario) throws Exception {
        return EmpleadoDAO.Instancia().listaEmpleadoxCargo(objUsuario);
    }

    public List<Empleado> obtenerlistaEmpleado(int criterio, String valor) throws Exception {
        try {
            return EmpleadoDAO.Instancia().obtenerListaEmpleados(criterio, valor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empleado> obtenerListaColaborador(int criterio, String valor) throws Exception {
        try {
            return EmpleadoDAO.Instancia().obtenerListaColaborador(criterio, valor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empleado> listarBienEmpleados() throws Exception {
        try {
            return EmpleadoDAO.Instancia().listarBienEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empleado> obtenerEncargadoBien(String nombres) throws Exception {

        try {
            return EmpleadoDAO.Instancia().obtenerEncargadoBien(nombres);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Empleado> listarEmpleados(String idOficina, String idDpto) throws Exception {
        try {
            return EmpleadoDAO.Instancia().listarEmpleados(idOficina, idDpto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
