/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.rrhh;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.GrupoHorarioPersonal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class HorarioDAO {

    private static HorarioDAO _Instancia;

    private HorarioDAO() {
    }

    public static HorarioDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new HorarioDAO();
        }
        return _Instancia;
    }

    public List<GrupoHorario> listarGrupo() throws Exception {

        List<GrupoHorario> lstGrupoHorario = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select idGrupo,nombreGrupo,descGrupo,CONVERT(varchar(15),diaEntrada,100) as diaEntrada\n"
                    + ",CONVERT(varchar(15),diaSalida,100) as diaSalida,CONVERT(varchar(15),tardeEntrada,100) as tardeEntrada\n"
                    + ",CONVERT(varchar(15),tardeSalida,100) as tardeSalida, diaGrupo from grupo where estado='1' order by nombreGrupo";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                GrupoHorario objGrupoHorario = new GrupoHorario();
                objGrupoHorario.setIdGrupo(rs.getInt("idGrupo"));
                objGrupoHorario.setNombreGrupo(rs.getString("nombreGrupo"));
                objGrupoHorario.setDescGrupo(rs.getString("descGrupo"));
                objGrupoHorario.setDiaEntrada(rs.getString("diaEntrada"));
                objGrupoHorario.setDiaSalida(rs.getString("diaSalida"));
                objGrupoHorario.setTardeEntrada(rs.getString("tardeEntrada"));
                objGrupoHorario.setTardeSalida(rs.getString("tardeSalida"));
                objGrupoHorario.setDiaHorario(rs.getString("diaGrupo"));
                lstGrupoHorario.add(objGrupoHorario);
            }

        } catch (Exception e) {
            throw e;
        }

        return lstGrupoHorario;
    }

    public String registrarGrupoHorario(GrupoHorario objGrupoHorario, String decision) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_upd_del_GrupoHorario(?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, decision);
            cs.setInt(2, objGrupoHorario.getIdGrupo());
            cs.setString(3, objGrupoHorario.getNombreGrupo());
            cs.setString(4, objGrupoHorario.getDescGrupo());
            cs.setString(5, objGrupoHorario.getDiaEntrada());
            cs.setString(6, objGrupoHorario.getDiaSalida());
            cs.setString(7, objGrupoHorario.getTardeEntrada());
            cs.setString(8, objGrupoHorario.getTardeSalida());
            cs.registerOutParameter(9, java.sql.Types.CHAR);

            cs.execute();

            result = cs.getString(9);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public List<GrupoHorarioPersonal> listarGrupoHorarioPersonal(String idGrupo) throws Exception {

        List<GrupoHorarioPersonal> lstGrupoHorarioPersonal = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select Grupo.idGrupo,Grupo.nombreGrupo,Personal.idpersona, Personal =(PersonaNatural.PreNombres+' '+ PersonaNatural.PrimerApellido+' '+PersonaNatural.SegundoApellido), \n"
                    + "Oficina = Oficina.Nombre, Area= Dpto.Nombre, Puesto = Puesto.Nombre from GrupoHorario_Personal \n"
                    + "join Grupo on GrupoHorario_Personal.idGrupo=Grupo.idGrupo\n"
                    + "join personal on GrupoHorario_Personal.idPersona = Personal.idPersona\n"
                    + "join PersonaNatural on Personal.IdPersona = PersonaNatural.IdPersona\n"
                    + "join Oficina on Personal.IdOficina = Oficina.IdOficina\n"
                    + "join Dpto on Personal.IdDpto = Dpto.IdDpto\n"
                    + "join Puesto on Personal.IdPuesto=Puesto.IdPuesto\n"
                    + "where Grupo.idGrupo ='" + idGrupo + "' \n"
                    + "";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                GrupoHorarioPersonal objGrupoHorarioPersonal = new GrupoHorarioPersonal();

                objGrupoHorarioPersonal.getObjGrupoHorario().setIdGrupo(rs.getInt("idGrupo"));
                objGrupoHorarioPersonal.getObjGrupoHorario().setNombreGrupo(rs.getString("nombreGrupo"));
                objGrupoHorarioPersonal.getObjEmpleado().setIdPersona(rs.getString("idpersona"));
                objGrupoHorarioPersonal.getObjEmpleado().setNombres(rs.getString("Personal"));
                objGrupoHorarioPersonal.getObjEmpleado().getObjOficina().setNombre(rs.getString("Oficina"));
                objGrupoHorarioPersonal.getObjEmpleado().getObjArea().setDescripcion(rs.getString("Area"));
                objGrupoHorarioPersonal.getObjEmpleado().getObjPuesto().setNombre(rs.getString("Puesto"));
                lstGrupoHorarioPersonal.add(objGrupoHorarioPersonal);
            }

        } catch (Exception e) {
            throw e;
        }

        return lstGrupoHorarioPersonal;
    }

    public String registrarGrupoHorarioPersonal(String idGrupo, String idPersona) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_HorarioPersonal(?,?,?)}");

            cs.setString(1, idGrupo);
            cs.setString(2, idPersona);
            cs.registerOutParameter(3, java.sql.Types.CHAR);

            cs.execute();

            result = cs.getString(3);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String eliminarPersonal(String idGrupo, String idPersona, Usuario objUsuario) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_del_HorarioPersonal(?,?,?,?)}");

            cs.setString(1, idGrupo);
            cs.setString(2, idPersona);
            cs.setString(3, objUsuario.getIdUsuario());
            cs.registerOutParameter(4, java.sql.Types.CHAR);

            cs.execute();

            result = cs.getString(4);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

}
