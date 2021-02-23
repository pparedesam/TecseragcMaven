/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.rrhh;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Area;
import ll.entidades.administracion.Banco;
import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;
import ll.entidades.administracion.Usuario;
import ll.entidades.rrhh.Contrato;
import ll.entidades.rrhh.ContratosEmpleado;
import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.SistemaPensiones;
import ll.entidades.rrhh.TipoEPS;
import ll.entidades.rrhh.TipoPlanEPS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AndZuñ
 */
public class ContratosEmpleadoDAO {

    private static ContratosEmpleadoDAO _Instancia;

    private ContratosEmpleadoDAO() {
    }

    public static ContratosEmpleadoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ContratosEmpleadoDAO();
        }
        return _Instancia;
    }

    public ContratosEmpleado obtenerContratosEmpleado(String IdPersona) throws Exception {
        ContratosEmpleado objContratoEmpleado = new ContratosEmpleado();

        List<Contrato> objHistorialContrato = new ArrayList<>();

        objContratoEmpleado = obtenerContratoEmpleado(IdPersona);
        objHistorialContrato = obtenerHistorialContratos(IdPersona);
        objContratoEmpleado.setObjHistorialContratos(objHistorialContrato);
        return objContratoEmpleado;
    }

    public ContratosEmpleado obtenerContratoEmpleado(String IdPersona) throws Exception {

        ContratosEmpleado objContratoEmpleado = new ContratosEmpleado();
        Empleado objEmpleado = new Empleado();
        Contrato objContrato = new Contrato();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            CallableStatement cstmt = cn.prepareCall("{call sch_RRHH_sp_ObtenerEmpleadoContratos (?)}");
            cstmt.setString("IdPersona", IdPersona);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                SistemaPensiones objSistemaPensiones = new SistemaPensiones();

                objSistemaPensiones.setIdSistema(rs.getString("IdSistema"));
                objSistemaPensiones.setNombre(rs.getString("NombreSistema"));

                Banco objBanco = new Banco();
                objBanco.setIdBanco(rs.getString("BancoSueldo"));
                objBanco.setNombreBanco(rs.getString("NombreBanco"));
                objEmpleado.setObjSistemaPensiones(objSistemaPensiones);

                objEmpleado.setCodigoSistema(rs.getString("CodigoSistema"));
                objEmpleado.setComisionMixta(rs.getString("ComisionMixta"));
                objEmpleado.setESSALUD(rs.getString("ESSALUD"));
                objEmpleado.setEPS(rs.getString("EPS"));
                objEmpleado.setBanco(objBanco);
                objEmpleado.setCtaSueldo(rs.getString("CtaSueldo"));
                objEmpleado.setNroHijos(rs.getInt("Hijos"));
                objEmpleado.setUsuarioEmp(rs.getString("Usuario"));
                objEmpleado.setCorreoEmp(rs.getString("Correo"));

                objEmpleado.setFoto(rs.getString("Foto"));
                objEmpleado.setFiscalizado(rs.getString("Fiscalizado"));

                TipoEPS objTipoEPS = new TipoEPS();
                objTipoEPS.setIdTipoEPS(rs.getString("IdTipoSeguroEPS"));
                objTipoEPS.setNombre(rs.getString("nombreTipoEPS"));

                TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
                objTipoPlanEPS.setIdEPS(rs.getInt("idPlanEPS"));
                objTipoPlanEPS.setDescripcion(rs.getString("DescripcionAportes"));
                objTipoPlanEPS.setTipoEPS(objTipoEPS);

                objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);

                Puesto objPuesto = new Puesto();
                objPuesto.setIdPuesto(rs.getString("idPuesto"));
                objPuesto.setNombre(rs.getString("Puesto"));
                Area objArea = new Area();
                objArea.setCodigo(rs.getString("idDpto"));
                objArea.setDescripcion(rs.getString("Dpto"));
                Oficina objOficina = new Oficina();
                objOficina.setIdOficina(rs.getString("idOficina"));
                objOficina.setNombre(rs.getString("Oficina"));

//                Cargo objCargo=new Cargo();
//                objCargo.setIdCargo(rs.getString("idCargo"));
                objContrato.setPuesto(objPuesto);
                // objContrato.setObjCargo(objCargo);
                objContrato.setFecInicio(rs.getString("FecIncial"));
                objContrato.setRemIncio(rs.getString("salIncial"));
                objContrato.setFecFin(rs.getString("fecFin"));
                objContrato.setRemFinal(rs.getString(("salFinal")));
                objContrato.setArea(objArea);
                objContrato.setTipoContrato(rs.getString("tipCon"));
                objContrato.setOficina(objOficina);

                GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
                objGrupoHorarioLunVie.setIdGrupo(rs.getInt("lunVie"));
                objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);

                GrupoHorario objGrupoHorarioSab = new GrupoHorario();
                objGrupoHorarioSab.setIdGrupo(rs.getInt("Sab"));
                objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

            }
            objContratoEmpleado.setObjEmpleado(objEmpleado);
            objContratoEmpleado.setObjContratoActual(objContrato);

        } catch (Exception e) {
            throw e;
        }
        return objContratoEmpleado;
    }

    public List<Contrato> obtenerHistorialContratos(String IdPersona) throws Exception {
        List<Contrato> lstHistorialContratos = new ArrayList<>();

        try (Connection cn1 = Connect.Instancia().getConnectBD()) {
            String sql2 = "Select "
                    + "pto.Nombre as Puesto,"
                    + "pto.idPuesto,"
                    + "convert(varchar(20),convert(date,ch.FechaInicial)) as FecIncial,"
                    + "ch.remInicial,"
                    + "FecFin=case when RTRIM(ch.FecFinal)='INDETERMINADO' then ch.FecFinal else convert(varchar(10),ch.FecFinal,103) end,"
                    + "ch.RemFinal,"
                    + "d.idDpto ,"
                    + "d.Nombre as Dpto,"
                    + "ch.Tip_Con,"
                    + "o.idOficina,"
                    + "o.Nombre as Oficina"
                    + " from ContratosHistorico ch "
                    + " inner join Personal p on ch.idPersona = p.idPersona "
                    + " inner join Oficina o on o.idOficina=ch.idOficina "
                    + " inner join Puesto pto on pto.idPuesto=ch.idPuesto "
                    + " inner join Dpto d on d.idDpto=ch.idDpto "
                    + " where "
                    + " p.IdPersona='" + IdPersona + "' order by FecIncial desc";

            PreparedStatement pstm2 = cn1.prepareStatement(sql2);

            ResultSet rs2 = pstm2.executeQuery();

            while (rs2.next()) {
                Puesto objPuesto = new Puesto();
                objPuesto.setIdPuesto(rs2.getString("idPuesto"));
                objPuesto.setNombre(rs2.getString("Puesto"));
                Area objArea = new Area();
                objArea.setCodigo(rs2.getString("idDpto"));
                objArea.setDescripcion(rs2.getString("Dpto"));
                Oficina objOficina = new Oficina();
                objOficina.setIdOficina(rs2.getString("idOficina"));
                objOficina.setNombre(rs2.getString("Oficina"));

                Contrato objContrato2 = new Contrato();

                objContrato2.setPuesto(objPuesto);
                objContrato2.setFecInicio(rs2.getString("FecIncial"));
                objContrato2.setRemIncio(rs2.getString("remInicial"));
                objContrato2.setFecFin(rs2.getString("FecFin"));
                objContrato2.setRemFinal(rs2.getString("RemFinal"));
                objContrato2.setArea(objArea);
                objContrato2.setTipoContrato(rs2.getString("Tip_Con"));
                objContrato2.setOficina(objOficina);
                lstHistorialContratos.add(objContrato2);
            }

        } catch (Exception e) {
            throw e;
        }
        return lstHistorialContratos;
    }

    public String registarEmpleado(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_rrhh_sp_ins_upd_Contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "in");
            cs.setString(2, idPersona);
            cs.setString(3, objContrato.getPuesto().getIdPuesto());
            cs.setString(4, objContrato.getFecInicio());
            cs.setString(5, objContrato.getRemIncio());
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, objContrato.getArea().getCodigo());
            cs.setString(10, objContrato.getOficina().getIdOficina());
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, objEmpleado.getBanco().getIdBanco());
            cs.setString(15, objEmpleado.getObjSistemaPensiones().getIdSistema());
            cs.setString(16, objEmpleado.getCtaSueldo());
            cs.setString(17, objEmpleado.getCodigoSistema());
            cs.setString(18, objEmpleado.getEPS());
            cs.setString(19, objEmpleado.getComisionMixta());
            cs.setString(20, objEmpleado.getESSALUD());
            cs.setInt(21, objEmpleado.getNroHijos());
            cs.setInt(22, objEmpleado.getObjTipoPlanEPS().getIdEPS());
            cs.setString(23, objEmpleado.getFoto());
            cs.setString(24, objEmpleado.getFiscalizado());
            cs.setInt(25, objEmpleado.getObjHorarioLunVie().getIdGrupo());
            cs.setInt(26, objEmpleado.getObjHorarioSab().getIdGrupo());
            //cs.setInt(27, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(27, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(27);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String actualizarContratoEmpleado(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_rrhh_sp_ins_upd_Contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "up");
            cs.setString(2, idPersona);
            cs.setString(3, "");
            cs.setString(4, "");
            cs.setString(5, "");
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, "");
            cs.setString(10, "");
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, objEmpleado.getBanco().getIdBanco());
            cs.setString(15, objEmpleado.getObjSistemaPensiones().getIdSistema());
            cs.setString(16, objEmpleado.getCtaSueldo());
            cs.setString(17, objEmpleado.getCodigoSistema());
            cs.setString(18, objEmpleado.getEPS());
            cs.setString(19, objEmpleado.getComisionMixta());
            cs.setString(20, objEmpleado.getESSALUD());
            cs.setInt(21, objEmpleado.getNroHijos());
            cs.setInt(22, objEmpleado.getObjTipoPlanEPS().getIdEPS());
            cs.setString(23, objEmpleado.getFoto());
            cs.setString(24, objEmpleado.getFiscalizado());
            cs.setInt(25, objEmpleado.getObjHorarioLunVie().getIdGrupo());
            cs.setInt(26, objEmpleado.getObjHorarioSab().getIdGrupo());
            // cs.setInt(2, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(27, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(27);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String agregarContratoEmpleado(Contrato objContrato, String idPersona) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_rrhh_sp_ins_upd_Contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "nCon");
            cs.setString(2, idPersona);
            cs.setString(3, objContrato.getPuesto().getIdPuesto());
            cs.setString(4, objContrato.getFecInicio());
            cs.setString(5, objContrato.getRemIncio());
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, objContrato.getArea().getCodigo());
            cs.setString(10, objContrato.getOficina().getIdOficina());
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, "");
            cs.setString(14, "");
            cs.setString(15, "");
            cs.setString(16, "");
            cs.setString(17, "");
            cs.setString(18, "");
            cs.setString(19, "");
            cs.setString(20, "");
            cs.setInt(21, 0);
            cs.setInt(22, 0);
            cs.setString(23, "");
            cs.setString(24, "");
            cs.setString(25, "");
            cs.setString(26, "");
            //cs.setInt(27, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(27, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(27);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String finContratoEmpleado(String idPersona, String fecSalida, String motSalida, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_rrhh_sp_ins_upd_Contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "del");
            cs.setString(2, idPersona);
            cs.setString(3, "");
            cs.setString(4, "");
            cs.setString(5, "");
            cs.setString(6, "");
            cs.setString(7, "");
            cs.setString(8, "");
            cs.setString(9, "");
            cs.setString(10, "");
            cs.setString(11, motSalida);
            cs.setString(12, fecSalida);
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, "");
            cs.setString(15, "");
            cs.setString(16, "");
            cs.setString(17, "");
            cs.setString(18, "");
            cs.setString(19, "");
            cs.setString(20, "");
            cs.setString(21, "");
            cs.setString(22, "");
            cs.setString(23, "");
            cs.setString(24, "");
            cs.setString(25, "");
            cs.setString(26, "");
            //cs.setString(27, "");

            cs.registerOutParameter(27, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(27);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    /*---------------------------------------------------------------------------------------*/
    public ContratosEmpleado obtenerContratosColaborador(String IdPersona) throws Exception {
        ContratosEmpleado objContratoEmpleado = new ContratosEmpleado();

        List<Contrato> objHistorialContrato = new ArrayList<>();

        objContratoEmpleado = obtenerContratoColaborador(IdPersona);
        objHistorialContrato = obtenerHistorialContratosColaborador(IdPersona);
        objContratoEmpleado.setObjHistorialContratos(objHistorialContrato);
        return objContratoEmpleado;
    }

    public ContratosEmpleado obtenerContratoColaborador(String IdPersona) throws Exception {

        ContratosEmpleado objContratoEmpleado = new ContratosEmpleado();
        Empleado objEmpleado = new Empleado();
        Contrato objContrato = new Contrato();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            CallableStatement cstmt = cn.prepareCall("{call sch_RRHH_sp_ObtenerColaboradorContratos (?)}");
            cstmt.setString("IdPersona", IdPersona);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                SistemaPensiones objSistemaPensiones = new SistemaPensiones();
//
//                objSistemaPensiones.setIdSistema(rs.getString("IdSistema"));
//                objSistemaPensiones.setNombre(rs.getString("NombreSistema"));

                Banco objBanco = new Banco();
                objBanco.setIdBanco(rs.getString("BancoSueldo"));
                objBanco.setNombreBanco(rs.getString("NombreBanco"));
//                objEmpleado.setObjSistemaPensiones(objSistemaPensiones);

//                objEmpleado.setCodigoSistema(rs.getString("CodigoSistema"));
//                objEmpleado.setComisionMixta(rs.getString("ComisionMixta"));
//                objEmpleado.setESSALUD(rs.getString("ESSALUD"));
//                objEmpleado.setEPS(rs.getString("EPS"));
                objEmpleado.setBanco(objBanco);
                objEmpleado.setCtaSueldo(rs.getString("CtaSueldo"));
//                objEmpleado.setNroHijos(rs.getInt("Hijos"));
                objEmpleado.setUsuarioEmp(rs.getString("Usuario"));
                objEmpleado.setCorreoEmp(rs.getString("Correo"));

                objEmpleado.setFoto(rs.getString("Foto"));
                objEmpleado.setFiscalizado(rs.getString("Fiscalizado"));

//                TipoEPS objTipoEPS = new TipoEPS();
//                objTipoEPS.setIdTipoEPS(rs.getString("IdTipoSeguroEPS"));
//                objTipoEPS.setNombre(rs.getString("nombreTipoEPS"));

//                TipoPlanEPS objTipoPlanEPS = new TipoPlanEPS();
//                objTipoPlanEPS.setIdEPS(rs.getInt("idPlanEPS"));
//                objTipoPlanEPS.setDescripcion(rs.getString("DescripcionAportes"));
//                objTipoPlanEPS.setTipoEPS(objTipoEPS);

//                objEmpleado.setObjTipoPlanEPS(objTipoPlanEPS);

                Puesto objPuesto = new Puesto();
                objPuesto.setIdPuesto(rs.getString("idPuesto"));
                objPuesto.setNombre(rs.getString("Puesto"));
                Area objArea = new Area();
                objArea.setCodigo(rs.getString("idDpto"));
                objArea.setDescripcion(rs.getString("Dpto"));
                Oficina objOficina = new Oficina();
                objOficina.setIdOficina(rs.getString("idOficina"));
                objOficina.setNombre(rs.getString("Oficina"));

//                Cargo objCargo=new Cargo();
//                objCargo.setIdCargo(rs.getString("idCargo"));
                objContrato.setPuesto(objPuesto);
                // objContrato.setObjCargo(objCargo);
                objContrato.setFecInicio(rs.getString("FecIncial"));
                objContrato.setRemIncio(rs.getString("salIncial"));
                objContrato.setFecFin(rs.getString("fecFin"));
                objContrato.setRemFinal(rs.getString(("salFinal")));
                objContrato.setArea(objArea);
                objContrato.setTipoContrato(rs.getString("tipCon"));
                objContrato.setOficina(objOficina);

                GrupoHorario objGrupoHorarioLunVie = new GrupoHorario();
                objGrupoHorarioLunVie.setIdGrupo(rs.getInt("lunVie"));
                objEmpleado.setObjHorarioLunVie(objGrupoHorarioLunVie);

                GrupoHorario objGrupoHorarioSab = new GrupoHorario();
                objGrupoHorarioSab.setIdGrupo(rs.getInt("Sab"));
                objEmpleado.setObjHorarioSab(objGrupoHorarioSab);

            }
            objContratoEmpleado.setObjEmpleado(objEmpleado);
            objContratoEmpleado.setObjContratoActual(objContrato);

        } catch (Exception e) {
            throw e;
        }
        return objContratoEmpleado;
    }

    public List<Contrato> obtenerHistorialContratosColaborador(String IdPersona) throws Exception {
        List<Contrato> lstHistorialContratos = new ArrayList<>();

        try (Connection cn1 = Connect.Instancia().getConnectBD()) {
            String sql2 = "Select \n"
                    + "pto.Nombre as Puesto,\n"
                    + "pto.idPuesto,\n"
                    + "convert(varchar(20),convert(date,ch.FechaInicial)) as FecIncial,\n"
                    + "ch.remInicial,\n"
                    + "FecFin=case when RTRIM(ch.FecFinal)='INDETERMINADO' then ch.FecFinal else convert(varchar(10),ch.FecFinal,103) end,\n"
                    + "ch.RemFinal,\n"
                    + "d.idDpto ,\n"
                    + "d.Nombre as Dpto,\n"
                    + "ch.Tip_Con,\n"
                    + "o.idOficina,\n"
                    + "o.Nombre as Oficina\n"
                    + "from ContratosHistoricoColaborador ch \n"
                    + "inner join Colaborador p on ch.idpersona = p.idpersona \n"
                    + "inner join Oficina o on o.idOficina=ch.idOficina \n"
                    + "inner join Puesto pto on pto.idPuesto=ch.idPuesto \n"
                    + "inner join Dpto d on d.idDpto=ch.idDpto \n"
                    + "where \n"
                    + "p.IdPersona='" + IdPersona + "' order by FecIncial desc ";

            PreparedStatement pstm2 = cn1.prepareStatement(sql2);

            ResultSet rs2 = pstm2.executeQuery();

            while (rs2.next()) {
                Puesto objPuesto = new Puesto();
                objPuesto.setIdPuesto(rs2.getString("idPuesto"));
                objPuesto.setNombre(rs2.getString("Puesto"));
                Area objArea = new Area();
                objArea.setCodigo(rs2.getString("idDpto"));
                objArea.setDescripcion(rs2.getString("Dpto"));
                Oficina objOficina = new Oficina();
                objOficina.setIdOficina(rs2.getString("idOficina"));
                objOficina.setNombre(rs2.getString("Oficina"));

                Contrato objContrato2 = new Contrato();

                objContrato2.setPuesto(objPuesto);
                objContrato2.setFecInicio(rs2.getString("FecIncial"));
                objContrato2.setRemIncio(rs2.getString("remInicial"));
                objContrato2.setFecFin(rs2.getString("FecFin"));
                objContrato2.setRemFinal(rs2.getString("RemFinal"));
                objContrato2.setArea(objArea);
                objContrato2.setTipoContrato(rs2.getString("Tip_Con"));
                objContrato2.setOficina(objOficina);
                lstHistorialContratos.add(objContrato2);
            }

        } catch (Exception e) {
            throw e;
        }
        return lstHistorialContratos;
    }

    public String registarColaborador(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_upd_ContratoColaborador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "in");
            cs.setString(2, idPersona);
            cs.setString(3, objContrato.getPuesto().getIdPuesto());
            cs.setString(4, objContrato.getFecInicio());
            cs.setString(5, objContrato.getRemIncio());
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, objContrato.getArea().getCodigo());
            cs.setString(10, objContrato.getOficina().getIdOficina());
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, objEmpleado.getBanco().getIdBanco());
//            cs.setString(15, objEmpleado.getObjSistemaPensiones().getIdSistema());
            cs.setString(15, objEmpleado.getCtaSueldo());
//            cs.setString(17, objEmpleado.getCodigoSistema());
//            cs.setString(18, objEmpleado.getEPS());
//            cs.setString(19, objEmpleado.getComisionMixta());
//            cs.setString(20, objEmpleado.getESSALUD());
//            cs.setInt(21, objEmpleado.getNroHijos());
//            cs.setInt(22, objEmpleado.getObjTipoPlanEPS().getIdEPS());
            cs.setString(16, objEmpleado.getFoto());
            cs.setString(17, objEmpleado.getFiscalizado());
            cs.setInt(18, objEmpleado.getObjHorarioLunVie().getIdGrupo());
            cs.setInt(19, objEmpleado.getObjHorarioSab().getIdGrupo());
            //cs.setInt(27, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(20, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(20);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String actualizarContratoColaborador(String idPersona, Empleado objEmpleado, Contrato objContrato, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_upd_ContratoColaborador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "up");
            cs.setString(2, idPersona);
            cs.setString(3, "");
            cs.setString(4, "");
            cs.setString(5, "");
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, "");
            cs.setString(10, "");
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, objEmpleado.getBanco().getIdBanco());
//            cs.setString(15, objEmpleado.getObjSistemaPensiones().getIdSistema());
            cs.setString(15, objEmpleado.getCtaSueldo());
//            cs.setString(17, objEmpleado.getCodigoSistema());
//            cs.setString(18, objEmpleado.getEPS());
//            cs.setString(19, objEmpleado.getComisionMixta());
//            cs.setString(20, objEmpleado.getESSALUD());
//            cs.setInt(21, objEmpleado.getNroHijos());
//            cs.setInt(22, objEmpleado.getObjTipoPlanEPS().getIdEPS());
            cs.setString(16, objEmpleado.getFoto());
            cs.setString(17, objEmpleado.getFiscalizado());
            cs.setInt(18, objEmpleado.getObjHorarioLunVie().getIdGrupo());
            cs.setInt(19, objEmpleado.getObjHorarioSab().getIdGrupo());
            // cs.setInt(2, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(20, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(20);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String agregarContratoColaborador(Contrato objContrato, String idPersona) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_upd_ContratoColaborador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "nCon");
            cs.setString(2, idPersona);
            cs.setString(3, objContrato.getPuesto().getIdPuesto());
            cs.setString(4, objContrato.getFecInicio());
            cs.setString(5, objContrato.getRemIncio());
            cs.setString(6, objContrato.getFecFin());
            cs.setString(7, objContrato.getRemFinal());
            cs.setString(8, objContrato.getTipoContrato());
            cs.setString(9, objContrato.getArea().getCodigo());
            cs.setString(10, objContrato.getOficina().getIdOficina());
            cs.setString(11, "");
            cs.setString(12, "");
            cs.setString(13, "");
            cs.setString(14, "");
//            cs.setString(15, "");
            cs.setString(15, "");
//            cs.setString(17, "");
//            cs.setString(18, "");
//            cs.setString(19, "");
//            cs.setString(20, "");
//            cs.setInt(21, 0);
//            cs.setInt(22, 0);
            cs.setString(16, "");
            cs.setString(17, "");
            cs.setString(18, "");
            cs.setString(19, "");
            //cs.setInt(27, Integer.parseInt(objContrato.getObjCargo().getIdCargo()));

            cs.registerOutParameter(20, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(20);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public String finContratoColaborador(String idPersona, String fecSalida, String motSalida, Usuario objUsuario) throws Exception {
        String result = null;
        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ins_upd_ContratoColaborador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, "del");
            cs.setString(2, idPersona);
            cs.setString(3, "");
            cs.setString(4, "");
            cs.setString(5, "");
            cs.setString(6, "");
            cs.setString(7, "");
            cs.setString(8, "");
            cs.setString(9, "");
            cs.setString(10, "");
            cs.setString(11, motSalida);
            cs.setString(12, fecSalida);
            cs.setString(13, objUsuario.getIdUsuario());
            cs.setString(14, "");
            // cs.setString(15, "");
            cs.setString(15, "");
//            cs.setString(17, "");
//            cs.setString(18, "");
//            cs.setString(19, "");
//            cs.setString(20, "");
//            cs.setString(21, "");
//            cs.setString(22, "");
            cs.setString(16, "");
            cs.setString(17, "");
            cs.setString(18, "");
            cs.setString(19, "");
            //cs.setString(27, "");

            cs.registerOutParameter(20, java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString(20);
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

}
