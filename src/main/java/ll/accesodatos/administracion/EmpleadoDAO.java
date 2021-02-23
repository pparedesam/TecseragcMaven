package ll.accesodatos.administracion;

import ll.entidades.administracion.Empleado;
import ll.entidades.administracion.Profesion;
import ll.entidades.administracion.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ll.accesodatos.agente.Connect;

public class EmpleadoDAO {

    private static EmpleadoDAO _Instancia;

    private EmpleadoDAO() {
    }

    public static EmpleadoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new EmpleadoDAO();
        }
        return _Instancia;
    }

    public Usuario verficarAcceso(String idusuario, String clave) throws Exception {

        Usuario objUsuario = new Usuario();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT P.IdPersona AS idPersona "
                    + ", P.IdOficina AS idOficina "
                    + ", P.IdDpto AS idArea "
                    + ", P.IdPuesto AS idPuesto "
                    + ", PN.PrimerApellido AS apPaterno "
                    + ", PN.SegundoApellido AS apMaterno "
                    + ", PN.PreNombres AS nombres "
                    + ", PN.Sexo AS sexo "
                    + ", U.IdUsuario AS idUsuario "
                    + ", U.Activo AS estado "
                    + ", U.Clave AS clave "
                    + ", O.Nombre AS nombreOficina "
                    + ", O.Abrev AS nombreOficinaAbrev "
                    + ", D.Nombre AS nombreArea"
                    + ", PN.NroDocIdentidad as NroDocIdentidad,"
                    + "  PU.Nombre AS nombrePuesto "
                    + " FROM Usuario U JOIN "
                    + " Personal P ON(U.IdPersona = P.IdPersona) JOIN "
                    + " PersonaNatural PN ON(PN.IdPersona = P.IdPersona) JOIN "
                    + " Oficina O ON(O.IdOficina = P.IdOficina) JOIN "
                    + " Dpto D ON(P.IdDpto = D.IdDpto) JOIN "
                    + " Puesto PU ON(P.IdPuesto = PU.IdPuesto)"
                    + " WHERE U.IdUsuario = '" + idusuario + "'"
                    + " AND U.Clave = '" + clave + "'"
                    + " AND U.Activo = '1'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objUsuario.setIdUsuario(rs.getString("idUsuario"));
                objUsuario.setClave(rs.getString("clave"));
                objUsuario.setEstado("1");
                objUsuario.getObjEmpleado().getObjOficina().setIdOficina(rs.getString("idOficina"));
                objUsuario.getObjEmpleado().getObjOficina().setNombre(rs.getString("nombreOficina"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setSexo(rs.getString("sexo"));
                objUsuario.getObjEmpleado().getObjOficina().setNombreAbreviado(rs.getString("nombreOficinaAbrev"));
                objUsuario.getObjEmpleado().getObjArea().setCodigo(rs.getString("idArea"));
                objUsuario.getObjEmpleado().getObjArea().setDescripcion(rs.getString("nombreArea"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setNroDocumento(rs.getString("NroDocIdentidad"));
                objUsuario.getObjEmpleado().getObjPuesto().setIdPuesto(rs.getString("idPuesto"));
                objUsuario.getObjEmpleado().getObjPuesto().setNombre(rs.getString("nombrePuesto"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setNombres(rs.getString("nombres"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setApPaterno(rs.getString("apPaterno"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setApMaterno(rs.getString("apMaterno"));
                objUsuario.getObjEmpleado().setIdPersona(rs.getString("idPersona"));
            }

        } catch (Exception ex) {
            throw ex;

        }

        return objUsuario;

    }

    public String retiro(Profesion objProfesion) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_insertar_profesion(?,?)}");

            cs.setString(1, objProfesion.getProfesion());
            cs.setString(2, objProfesion.getIdProfesion());
            cs.registerOutParameter(2, java.sql.Types.CHAR);
            cs.execute();

            result = cs.getString(2);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public boolean cambioClave(Usuario objUsuario, String claveAnt, String claveNew) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE Usuario SET "
                    + "clave='" + claveNew + "'"
                    + "WHERE idUsuario = '" + objUsuario.getIdUsuario() + "' AND clave= '" + claveAnt + "' ";
            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;
        } catch (Exception e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();
        }
        return result;
    }

    public List<Empleado> ObtenerEncargadoBien(String nombres) throws Exception {

        List<Empleado> listEncargado = new ArrayList<>();
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {

            String sql = "select  personal.IdPersona,(PersonaNatural.PrimerApellido+' '+PersonaNatural.SegundoApellido+' '+PersonaNatural.PreNombres) as Nombres , Oficina.Nombre as Oficina,Dpto.Nombre as Area from Personal \n"
                    + "inner join PersonaNatural on personal.IdPersona =PersonaNatural.IdPersona\n"
                    + "inner join Oficina on Oficina.IdOficina = Personal.IdOficina\n"
                    + "inner join Dpto on Dpto.IdDpto = Personal.IdDpto\n"
                    + " WHERE Personal.IdPersona IS NOT NULL \n"
                    + " and (PersonaNatural.PrimerApellido+SPACE(1)+PersonaNatural.SegundoApellido+SPACE(1)+PersonaNatural.PreNombres LIKE '%" + nombres + "%')";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.getObjPersonaNatural().setIdPersona(rs.getString("IdPersona"));
                objEmpleado.getObjPersonaNatural().setNombres(rs.getString("Nombres"));
                objEmpleado.getObjOficina().setNombre(rs.getString("Oficina"));
                objEmpleado.getObjArea().setDescripcion(rs.getString("Area"));

                listEncargado.add(objEmpleado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listEncargado;
    }

    public List<Empleado> obtenerPersonal(String Criterio, String TipoBusqueda) throws Exception {

        List<Empleado> listaEmpleado = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Administracion_sp_Obtener_Personal (?,?)}");

            cstmt.setString("criterio", Criterio);
            cstmt.setString("tipoBusqueda", TipoBusqueda);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Empleado objEmpleado = new Empleado();
                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNombres(rs.getString("nombres"));
                objEmpleado.setNroDocumento(rs.getString("NroDocIdentidad"));
                objEmpleado.getObjArea().setDescripcion(rs.getString("area"));
                objEmpleado.getObjPuesto().setNombre(rs.getString("puesto"));
                listaEmpleado.add(objEmpleado);
            }

        } catch (Exception ex) {
            throw ex;
        }

        return listaEmpleado;
    }

    public List<Usuario> obtenerUsuarioMarcacion(String Criterio, String TipoBusqueda) throws Exception {

        List<Usuario> listaUsuarios = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectAsistencia()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Asistencia_sp_ObtenerUsuarioMarcacion (?,?)}");

            cstmt.setString("criterio", Criterio);
            cstmt.setString("tipoBusqueda", TipoBusqueda);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                Usuario objUsuario = new Usuario();
                objUsuario.setIdUsuario(rs.getString("USERID"));
                objUsuario.getObjEmpleado().setNombres(rs.getString("Name"));
                objUsuario.getObjEmpleado().setNroDocumento(rs.getString("BadgeNumber"));

                listaUsuarios.add(objUsuario);
            }

        } catch (Exception ex) {
            throw ex;
        }

        return listaUsuarios;
    }

//    public List<Usuario> obtenerUsuarioMarcacion(String valor) throws Exception {
//        List<Usuario> listaUsuarios = new ArrayList<>();
//        
//        try (Connection cn = Connect.Instancia().getConnectAsistencia()) {
//            
//            String sql = "select USERID,Name from USERINFO\n"
//                    + "where name like '%'+'" + valor + "'+'%'";
//            
//            PreparedStatement pstm = cn.prepareStatement(sql);
//            
//            ResultSet rs = pstm.executeQuery();
//            
//            while (rs.next()) {
//                
//                Usuario objUsuario = new Usuario();
//                objUsuario.setIdUsuario(rs.getString("USERID"));
//                objUsuario.getObjEmpleado().setNombres(rs.getString("Name"));
//                
//                listaUsuarios.add(objUsuario);
//            }
//            
//        } catch (Exception e) {
//            throw e;
//        }
//        
//        return listaUsuarios;
//        
//    }
    public boolean vinculacionMarcacion(Usuario objUsuario) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "update personal \n"
                    + "set userIdMarcacion ='" + objUsuario.getIdUsuario() + "'\n"
                    + "where idpersona ='" + objUsuario.getObjEmpleado().getIdPersona() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            result = pstm.executeUpdate() == 1;

        } catch (Exception e) {
            objConexion.rollback();
            throw e;
        } finally {
            objConexion.close();

        }

        return result;

    }

    public List<Empleado> obtenerAnalistaxOficina(String idoficina) throws Exception {

        List<Empleado> listAnalista = new ArrayList<>();
        String sCondOfi = "";
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {
            if (idoficina.equals("0")) {
                sCondOfi = "";
            } else {
                sCondOfi = " AND IdOficina='" + idoficina + "'";
            }

            String sql = "select usuario.idpersona,usuario.idusuario,NomUsu=left(rtrim(prenombres)+' '+rtrim(primerapellido)+' '+rtrim(segundoapellido),40) \n"
                    + "from usuario inner join personal on usuario.idpersona=personal.idpersona \n"
                    + "inner join personanatural on personanatural.idpersona=personal.idpersona \n"
                    + "where iddpto in('05','02') and idpuesto in ('06','03') and Usuario.Activo='1'" + sCondOfi;

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.getObjPersonaNatural().setIdPersona(rs.getString("idpersona"));
                objEmpleado.setIdUsuario(rs.getString("idusuario"));
                objEmpleado.getObjPersonaNatural().setNombres(rs.getString("NomUsu"));

                listAnalista.add(objEmpleado);
            }
        } catch (Exception e) {
            throw e;
        }

        return listAnalista;
    }

    public List<Empleado> obtenerPersonalCobranza(String idoficina) throws Exception {

        List<Empleado> listPersonalCobranza = new ArrayList<>();
        String sCondOfi = "";
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {
            if (idoficina.equals("0")) {
                sCondOfi = "";
            } else {
                sCondOfi = " AND IdOficina='" + idoficina + "'";
            }

            String sql = "select usuario.idpersona,usuario.idusuario,NomUsu=left(rtrim(prenombres)+' '+rtrim(primerapellido)+' '+rtrim(segundoapellido),40) from usuario inner join personal on usuario.idpersona=personal.idpersona inner join personanatural on personanatural.idpersona=personal.idpersona where iddpto in('09','19') and idpuesto in ('01','03') and Usuario.Activo='1'" + sCondOfi + " order by NomUsu";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.getObjPersonaNatural().setIdPersona(rs.getString("idpersona"));
                objEmpleado.setIdUsuario(rs.getString("idusuario"));
                objEmpleado.getObjPersonaNatural().setNombres(rs.getString("NomUsu"));

                listPersonalCobranza.add(objEmpleado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPersonalCobranza;
    }

    public List<Empleado> listaEmpleadoxCargo(Usuario objUsuario) throws Exception {

        List<Empleado> listaEmpleado = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            CallableStatement cs = objConexion.prepareCall("{call sch_RRHH_sp_ListaTrabajadoresxCargo(?)}");
//            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
//            cs.setString(2, objUsuario.getObjEmpleado().getObjArea().getCodigo());
//            cs.setString(3, objUsuario.getObjEmpleado().getObjPuesto().getIdPuesto());
            cs.setString(1, objUsuario.getIdUsuario());
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();
                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNroDocumento(rs.getString("DNI"));
                objEmpleado.setNombres(rs.getString("Trabajador"));
                objEmpleado.setCodTra(rs.getString("Cod_Tra"));
                objEmpleado.setEstado(rs.getString("Estado"));
                objEmpleado.getObjOficina().setNombre(rs.getString("Oficina"));
                objEmpleado.getObjArea().setDescripcion(rs.getString("Departamento"));
                objEmpleado.setCargo(rs.getString("Cargo"));
                listaEmpleado.add(objEmpleado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaEmpleado;
    }

    public List<Empleado> obtenerListaEmpleados(int criterio, String valor) throws Exception {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String CriterioSQL;
            switch (criterio) {
                case 1:
                    CriterioSQL = " pn.NroDocIdentidad='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = " pn.PrimerApellido+SPACE(1)+pn.SegundoApellido+SPACE(1)+pn.PreNombres LIKE '%" + valor + "%'";
                    break;
                default:
                    CriterioSQL = " te.Aleatorio ='" + valor + "'";
                    break;
            }
//          String sql="select DISTINCT pn.IdPersona, concat(pn.PrimerApellido,(' '), pn.SegundoApellido,(' '),pn.PreNombres) as Nombres,pn.NroDocIdentidad\n"
//            + "from \n"
//            + "Personal t \n"
//            + "inner join Persona p on t.IdPersona=p.IdPersona\n"
//            + "inner join PersonaNatural pn on p.IdPersona=pn.IdPersona\n" 
//            + "inner join ContratosHistorico c on t.IdTrabajador=c.Cod_Tra\n"
//            + "WHERE pn.IdPersona IS NOT NULL AND ( len(pn.NroDocIdentidad)>=8  and len(pn.NroDocIdentidad)<=11)and"+ CriterioSQL;

            String sql = "select DISTINCT pn.IdPersona, concat(pn.PrimerApellido,(' '), pn.SegundoApellido,(' '),pn.PreNombres) as Nombres,pn.NroDocIdentidad\n"
                    + "from \n"
                    + "PersonaNatural pn \n"
                    + "left join Personal p on pn.IdPersona=p.IdPersona\n"
                    + "left join ContratosColaborador cc on pn.IdPersona = cc.IdPersona\n"
                    + "WHERE pn.IdPersona IS NOT NULL AND ( len(pn.NroDocIdentidad)>=8  and len(pn.NroDocIdentidad)<=11) and cc.IdPersona is null and" + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNroDocumento(rs.getString("NroDocIdentidad"));
                objEmpleado.setNombres(rs.getString("Nombres"));
                listaEmpleados.add(objEmpleado);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaEmpleados;
    }

    public List<Empleado> obtenerListaColaborador(int criterio, String valor) throws Exception {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String CriterioSQL;
            switch (criterio) {
                case 1:
                    CriterioSQL = " pn.NroDocIdentidad='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = " pn.PrimerApellido+SPACE(1)+pn.SegundoApellido+SPACE(1)+pn.PreNombres LIKE '%" + valor + "%'";
                    break;
                default:
                    CriterioSQL = " te.Aleatorio ='" + valor + "'";
                    break;
            }
//          String sql="select DISTINCT pn.IdPersona, concat(pn.PrimerApellido,(' '), pn.SegundoApellido,(' '),pn.PreNombres) as Nombres,pn.NroDocIdentidad\n"
//            + "from \n"
//            + "Personal t \n"
//            + "inner join Persona p on t.IdPersona=p.IdPersona\n"
//            + "inner join PersonaNatural pn on p.IdPersona=pn.IdPersona\n" 
//            + "inner join ContratosHistorico c on t.IdTrabajador=c.Cod_Tra\n"
//            + "WHERE pn.IdPersona IS NOT NULL AND ( len(pn.NroDocIdentidad)>=8  and len(pn.NroDocIdentidad)<=11)and"+ CriterioSQL;

            String sql = "select DISTINCT pn.IdPersona, concat(pn.PrimerApellido,(' '), pn.SegundoApellido,(' '),pn.PreNombres) as Nombres,pn.NroDocIdentidad\n"
                    + "from \n"
                    + "PersonaNatural pn \n"
                    + "left join Colaborador c on pn.IdPersona=c.IdPersona\n"
                    + "left join Contratos cc on pn.IdPersona = cc.IdPersona\n"
                    + "WHERE pn.IdPersona IS NOT NULL AND ( len(pn.NroDocIdentidad)>=8  and len(pn.NroDocIdentidad)<=11) and cc.IdPersona is null and" + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNroDocumento(rs.getString("NroDocIdentidad"));
                objEmpleado.setNombres(rs.getString("Nombres"));
                listaEmpleados.add(objEmpleado);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaEmpleados;
    }

    public List<Usuario> obtenerAnalistasCredito(String idOficina) throws Exception {

        List<Usuario> listUsuario = new ArrayList<>();
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {

            String sql = "SELECT "
                    + "Usuario.IdUsuario, "
                    + "PERSONANATURAL.PRIMERAPELLIDO, "
                    + "PERSONANATURAL.SegundoApellido, "
                    + "PERSONANATURAL.PRENOMBRES, "
                    + "Usuario.IdPersona  "
                    + "FROM Usuario "
                    + "INNER JOIN Personal "
                    + "INNER JOIN  PERSONANATURAL ON Personal.IdPersona = PERSONANATURAL.IdPersona ON Usuario.idpersona = Personal.idpersona "
                    + "INNER JOIN PUESTOSXDPTOXOFICINA "
                    + "ON Personal.IdPuesto = PUESTOSXDPTOXOFICINA.IdPuesto AND Personal.IdDpto = PUESTOSXDPTOXOFICINA.IdDpto AND "
                    + "Personal.IdOficina = PUESTOSXDPTOXOFICINA.IdOficina INNER JOIN Puesto ON PUESTOSXDPTOXOFICINA.IdPuesto = Puesto.IdPuesto "
                    + "INNER JOIN dptoxoficina ON PUESTOSXDPTOXOFICINA.IdDpto = dptoxoficina.IdDpto AND PUESTOSXDPTOXOFICINA.IdOficina = dptoxoficina.IdOficina "
                    + "INNER JOIN Oficina ON dptoxoficina.IdOficina = Oficina.IdOficina INNER JOIN Dpto ON dptoxoficina.IdDpto = Dpto.IdDpto "
                    + "WHERE PUESTOSXDPTOXOFICINA.IdDpto='05' AND USUARIO.ACTIVO='1' AND PUESTOSXDPTOXOFICINA.IdPuesto in('06','03')  "
                    + " AND PUESTOSXDPTOXOFICINA.IDOFICINA='" + idOficina + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Usuario objUsuario = new Usuario();

                objUsuario.setIdUsuario(rs.getString("IdUsuario"));
                objUsuario.getObjEmpleado().setIdPersona(rs.getString("IdPersona"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setApMaterno(rs.getString("SegundoApellido"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setApPaterno(rs.getString("PRIMERAPELLIDO"));
                objUsuario.getObjEmpleado().getObjPersonaNatural().setNombres(rs.getString("PRENOMBRES"));

                listUsuario.add(objUsuario);
            }

        } catch (Exception e) {
            throw e;
        }

        return listUsuario;
    }

    public List<Empleado> listarBienEmpleados() throws Exception {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "Select p.IdPersona, Nombre=CONCAT(pn.PrimerApellido,' ', pn.SegundoApellido,' ', pn.PreNombres) from Personal p inner join Contratos c on p.IdTrabajador=c.Cod_Tra inner join PersonaNatural pn on p.IdPersona=pn.IdPersona order by Nombre";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNombres(rs.getString("Nombre"));
                listaEmpleados.add(objEmpleado);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaEmpleados;
    }

    public List<Empleado> obtenerEncargadoBien(String nombres) throws Exception {

        List<Empleado> listEncargado = new ArrayList<>();
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {
            String sql = "select  personal.IdPersona,(PersonaNatural.PrimerApellido+' '+PersonaNatural.SegundoApellido+' '+PersonaNatural.PreNombres) as Nombres , Oficina.Nombre as Oficina,Dpto.Nombre as Area from Personal \n"
                    + "inner join PersonaNatural on personal.IdPersona =PersonaNatural.IdPersona\n"
                    + "inner join Oficina on Oficina.IdOficina = Personal.IdOficina\n"
                    + "inner join Dpto on Dpto.IdDpto = Personal.IdDpto\n"
                    + " WHERE Personal.IdPersona IS NOT NULL \n"
                    + " and (PersonaNatural.PrimerApellido+SPACE(1)+PersonaNatural.SegundoApellido+SPACE(1)+PersonaNatural.PreNombres LIKE '%" + nombres + "%')";

//            String sql = "select  personal.IdPersona,(PersonaNatural.PrimerApellido+' '+PersonaNatural.SegundoApellido+' '+PersonaNatural.PreNombres) as Nombres , Oficina.Nombre as Oficina,Dpto.Nombre as Area from Personal \n"
//                    + "inner join PersonaNatural on personal.IdPersona =PersonaNatural.IdPersona\n"
//                    + "inner join Oficina on Oficina.IdOficina = Personal.IdOficina\n"
//                    + "inner join Dpto on Dpto.IdDpto = Personal.IdDpto\n"
//                    + " WHERE Personal.IdPersona IS NOT NULL \n"
//                    + " and (PersonaNatural.PrimerApellido+SPACE(1)+PersonaNatural.SegundoApellido+SPACE(1)+PersonaNatural.PreNombres LIKE '%" + nombres + "%') and personal.IdEstadoPersonal <> '1002'";
            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.getObjPersonaNatural().setIdPersona(rs.getString("IdPersona"));
                objEmpleado.getObjPersonaNatural().setNombres(rs.getString("Nombres"));
                objEmpleado.getObjOficina().setNombre(rs.getString("Oficina"));
                objEmpleado.getObjArea().setDescripcion(rs.getString("Area"));

                listEncargado.add(objEmpleado);
            }

        } catch (Exception e) {
            throw e;
        }

        return listEncargado;
    }

    public List<Empleado> listarEmpleados(String idOficina, String idDpto) throws Exception {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "Select p.IdPersona, Nombre=CONCAT(pn.PrimerApellido,' ', pn.SegundoApellido,' ', pn.PreNombres) \n"
                    + "from Personal p \n"
                    + "inner join PersonaNatural pn on p.IdPersona=pn.IdPersona \n"
                    + "where p.IdOficina='" + idOficina + "' and p.IdDpto='" + idDpto + "' and IdEstadoPersonal ='1508'\n"
                    + "order by Nombre";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Empleado objEmpleado = new Empleado();

                objEmpleado.setIdPersona(rs.getString("IdPersona"));
                objEmpleado.setNombres(rs.getString("Nombre"));
                listaEmpleados.add(objEmpleado);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaEmpleados;
    }
}
