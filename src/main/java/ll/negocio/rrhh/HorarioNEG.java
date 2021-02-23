/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.rrhh;

import ll.accesodatos.rrhh.HorarioDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.GrupoHorarioPersonal;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class HorarioNEG {

    private static HorarioNEG _Instancia;

    private HorarioNEG() {
    }

    public static HorarioNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new HorarioNEG();
        }
        return _Instancia;
    }

    public List<GrupoHorario> listarGrupo() throws Exception {

        return HorarioDAO.Instancia().listarGrupo();
    }

    public String registrarGrupoHorario(GrupoHorario objGrupoHorario , String decision) throws Exception {
        return HorarioDAO.Instancia().registrarGrupoHorario(objGrupoHorario,decision);
    }

    public List<GrupoHorarioPersonal> listarGrupoHorarioPersonal(String idGrupo) throws Exception {

        return HorarioDAO.Instancia().listarGrupoHorarioPersonal(idGrupo);
    }

    public String registrarGrupoHorarioPersonal(String idGrupo, String idPersona) throws Exception {
        return HorarioDAO.Instancia().registrarGrupoHorarioPersonal(idGrupo, idPersona);
    }

    public String eliminarPersonal(String idGrupo, String idPersona, Usuario objUsuario) throws Exception {
        return HorarioDAO.Instancia().eliminarPersonal(idGrupo, idPersona, objUsuario);
    }
}
