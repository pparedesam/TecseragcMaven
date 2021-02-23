package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ll.accesodatos.agente.Connect;

import ll.entidades.administracion.PersonaNatural;

public class PersonaNaturalDAO {

    private static PersonaNaturalDAO _Instancia;
    private boolean result;

    private PersonaNaturalDAO() {
    }

    public static PersonaNaturalDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaNaturalDAO();
        }
        return _Instancia;
    }

    /*
    Busqueda de personas naturales sea o no sea socio , utilizando 3 criterios de busqueda
    1 = nro doc
    2 = Apellidos o nombres    
    4 = tarjeta electronica
     */
    public List<PersonaNatural> listaPersonaNatural(int criterio, String valor) throws Exception {
        List<PersonaNatural> listaPersonaNatural = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String CriterioSQL = "";
            switch (criterio) {
                case 1:
                    CriterioSQL = " pn.NroDocIdentidad='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = " pn.PrimerApellido+SPACE(1)+pn.SegundoApellido+SPACE(1)+pn.PreNombres LIKE '%" + valor + "%'";
                    break;

            }

            String sql = "SELECT DISTINCT pn.IdPersona, pn.PrimerApellido+SPACE(1)+pn.SegundoApellido+SPACE(1)+pn.PreNombres as Nombres,ISNULL(pn.NroDocIdentidad,00000)   AS NroDocIdentidad, pn.Sexo ,Proveedores = case when pro.Estado is not null then case pro.Estado when '0' then 'NO' when '1' then 'SI' end else 'NO' end "
                    + " from PersonaNatural pn "
                    + "left join proveedores pro on pn.IdPersona = pro.IdPersona "
                    + " WHERE pn.IdPersona IS NOT NULL AND ( len(pn.NroDocIdentidad)>=8  and len(pn.NroDocIdentidad)<=11)"
                    + " and " + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaNatural objPersonaNatural = new PersonaNatural();
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setNombres(rs.getString("Nombres"));
                objPersonaNatural.setProveedor(rs.getString("Proveedores"));
                listaPersonaNatural.add(objPersonaNatural);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaNatural;

    }

    public String insertar(PersonaNatural objPersonaNatural) throws Exception {

        String result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_upd_PersonaNatural (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cstmt.setString(1, objPersonaNatural.getIdPersona());
            cstmt.setString(2, objPersonaNatural.getObjUrbanizacion().getCodigo());
            cstmt.setString(3, objPersonaNatural.getDireccion());
            cstmt.setString(4, objPersonaNatural.getFechaNacimiento());
            cstmt.setString(5, objPersonaNatural.getTelefono());
            cstmt.setString(6, objPersonaNatural.getObjUbigeoNacionalidad().getCodigo());
            cstmt.setString(7, objPersonaNatural.getCorreo());
            cstmt.setString(8, objPersonaNatural.getIdUsuario());
            cstmt.setString(9, objPersonaNatural.getReferenciaDireccion());
            cstmt.setString(10, objPersonaNatural.getCelular());
            cstmt.setString(11, objPersonaNatural.getApPaterno());
            cstmt.setString(12, objPersonaNatural.getApMaterno());
            cstmt.setString(13, objPersonaNatural.getNombres());
            cstmt.setString(14, objPersonaNatural.getSexo());
            cstmt.setString(15, objPersonaNatural.getObjTipoDocIdentidad().getIdTipoDocIdentidad());
            cstmt.setString(16, objPersonaNatural.getNroDocumento());
            cstmt.setString(17, objPersonaNatural.getEstadoCivil());
            cstmt.setString(18, objPersonaNatural.getGradoInstruccion());
            cstmt.setString(19, objPersonaNatural.getObjProfesion().getIdProfesion());
            cstmt.setString(20, objPersonaNatural.getProveedor());
            cstmt.setString(21, objPersonaNatural.getIdRubro());
            cstmt.setString(22, objPersonaNatural.getRus());

            cstmt.registerOutParameter(23, java.sql.Types.VARCHAR);

            cstmt.execute();
            result = cstmt.getString(23);

        } catch (Exception e) {

            throw e;
        }

        return result;

    }

    public PersonaNatural ObtenerPersonaSocio(String dni) throws Exception {

        PersonaNatural objPersonaNatural = new PersonaNatural();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT PN.idPersona,P.TipoPersona,PrimerApellido, SegundoApellido, PreNombres, NroDocIdentidad, Sexo,"
                    + " UR.Nombre as Urbanizacion ,P.Direccion, UBI.Ubigeo, T.Descripcion as TipoDocumento, "
                    + " CONVERT(int,ROUND(DATEDIFF(hour,P.FechaNac,GETDATE())/8766.0,0)) AS Edad  "
                    + " from PersonaNatural PN "
                    + " INNER JOIN Persona P ON (PN.IdPersona = P.IdPersona) "
                    + " INNER JOIN Urbanizacion UR ON (P.IdUrbanizacion = UR.IdUrbanizacion) "
                    + " INNER JOIN UbiGeo UBI on (P.IdUbigeoDir = UBI.IdUbigeo) "
                    + " INNER JOIN Tablas T on(PN.IdTipoDocIdentidad = T.IdTabla)"
                    + " LEFT JOIN Personal PE on (PE.IdPersona = P.IdPersona) "
                    + " WHERE NroDocIdentidad='" + dni + "' and (PE.IdPersona is null or PE.IdEstadoPersonal = '1002')";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objPersonaNatural.setIdPersona(rs.getString("idPersona"));
                objPersonaNatural.setApPaterno(rs.getString("PrimerApellido"));
                objPersonaNatural.setApMaterno(rs.getString("SegundoApellido"));
                objPersonaNatural.setNombres(rs.getString("PreNombres"));
                objPersonaNatural.getObjTipoDocIdentidad().setTipoPersona(rs.getString("TipoPersona"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setSexo(rs.getString("Sexo"));
                objPersonaNatural.getObjUrbanizacion().setDescripcion(rs.getString("Urbanizacion"));
                objPersonaNatural.setDireccion(rs.getString("Direccion"));
                objPersonaNatural.getObjUbigeoDireccion().setDescripcion(rs.getString("Ubigeo"));
                objPersonaNatural.getObjTipoDocIdentidad().setIdTipoDocIdentidad(rs.getString("TipoDocumento"));
                objPersonaNatural.setEdad(rs.getInt("Edad"));

            }

        } catch (Exception e) {
            throw e;
        }
        return objPersonaNatural;
    }

    //Obtiene datos de persona natural para el formulario persona natural
    public PersonaNatural ObtenerPersonaNatural(String IdPersona) throws Exception {

        PersonaNatural objPersonaNatural = null;
        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT ISNULL( pn.IdPersona,'') as IdPersona,pn.PrimerApellido,pn.SegundoApellido, pn.PreNombres , pn.IdTipoDocIdentidad,ISNULL( pn.NroDocIdentidad,'') AS Dni,\n"
                    + "  pn.Sexo,pn.IdEstadoCivil,pn.IdGradoInstruccion,CONVERT(VARCHAR(10),CONVERT(date,p.FechaNac),103) as FechaNac,ISNULL(p.Telefono,'') AS Telefono ,\n"
                    + "  ISNULL( p.Celular,'') AS Celular ,  ISNULL(p.Email,'') AS Email ,ISNULL(p.Direccion,'') AS Direccion,\n"
                    + "  ISNULL(p.Referencia,'') AS Referencia ,    p1.IdProfesion,p1.Descripcion AS Profesion, ISNULL(p.IdUbigeoDir,'01000000') as IdUbigeo,\n"
                    + "  ISNULL(p.IdUrbanizacion,'') AS Urbanizacion,\n"
                    + "  Proveedores = case when Proveedores.Estado is not null then  Proveedores.Estado  else '0' end,idRubro = case when Proveedores.IdRubro IS null then '2101' else Proveedores.IdRubro end,NroRus = case when Proveedores.NroRuc IS NULL then '' else Proveedores.NroRuc end\n"
                    + "  FROM PersonaNatural pn\n"
                    + "  INNER JOIN Persona p ON pn.IdPersona= p.IdPersona  \n"
                    + "  LEFT JOIN Profesiones p1 ON pn.IdProfesion=p1.IdProfesion  \n"
                    + "  LEFT join Proveedores on pn.IdPersona = Proveedores.IdPersona \n"
                    + "  WHERE p.TipoPersona='N' and pn.IdPersona='" + IdPersona + "'";

            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objPersonaNatural = new PersonaNatural();
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNombres(rs.getString("PreNombres"));
                objPersonaNatural.setApPaterno(rs.getString("PrimerApellido"));
                objPersonaNatural.setApMaterno(rs.getString("SegundoApellido"));
                objPersonaNatural.getObjTipoDocIdentidad().setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                objPersonaNatural.setNroDocumento(rs.getString("Dni"));
                objPersonaNatural.setSexo(rs.getString("Sexo"));
                objPersonaNatural.setEstadoCivil(rs.getString("IdEstadoCivil"));
                objPersonaNatural.setGradoInstruccion(rs.getString("IdGradoInstruccion"));
                objPersonaNatural.setFechaNacimiento(rs.getString("FechaNac"));
                objPersonaNatural.setTelefono(rs.getString("Telefono"));
                objPersonaNatural.setCelular(rs.getString("Celular"));
                objPersonaNatural.getObjProfesion().setIdProfesion(rs.getString("IdProfesion"));
                objPersonaNatural.getObjUbigeoNacionalidad().setCodigo(rs.getString("IdUbigeo"));
                objPersonaNatural.setCorreo(rs.getString("Email"));
                objPersonaNatural.getObjUrbanizacion().setCodigo(rs.getString("Urbanizacion"));
                objPersonaNatural.setDireccion(rs.getString("Direccion"));
                objPersonaNatural.setReferenciaDireccion(rs.getString("Referencia"));
                objPersonaNatural.setProveedor(rs.getString("Proveedores"));
                objPersonaNatural.setIdRubro(rs.getString("idRubro"));
                objPersonaNatural.setRus(rs.getString("NroRus"));
            }
        } catch (Exception e) {
            throw e;
        }
        return objPersonaNatural;

    }

    public PersonaNatural ObtenerPersonaGeneralNoEmpleado(String dni) throws Exception {

        PersonaNatural objPersonaNatural = new PersonaNatural();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            String sql = "select PN.IdPersona,PN.PreNombres,PN.PrimerApellido,PN.SegundoApellido,PN.NroDocIdentidad,PN.Sexo from Persona P "
                    + "INNER JOIN Socio S on P.IdPersona=s.IdPersona "
                    + "INNER JOIN PersonaNatural PN ON P.IdPersona=PN.IdPersona "
                    + "LEFT JOIN Personal PS ON P.IdPersona=PS.IdPersona "
                    + "where p.tipopersona='N' AND "
                    + "(PS.IdEstadoPersonal NOT IN ('1001','1003','1009','1010') OR PS.IdPersona IS NULL) AND LEFT(s.IdEstadoSocio,1) = 1 "
                    + "AND PN.NroDocIdentidad='" + dni + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objPersonaNatural.setIdPersona(rs.getString("idPersona"));
                objPersonaNatural.setApPaterno(rs.getString("PrimerApellido"));
                objPersonaNatural.setApMaterno(rs.getString("SegundoApellido"));
                objPersonaNatural.setNombres(rs.getString("PreNombres"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setSexo(rs.getString("Sexo"));
            }

        } catch (Exception e) {
            throw e;
        }
        return objPersonaNatural;
    }

    //ANDREW
    public List<PersonaNatural> listarPersonaxDNISocio(String valor) throws Exception {
        List<PersonaNatural> listaPersonaNatural = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pn.IdPersona, pn.PrimerApellido + SPACE(1) + pn.SegundoApellido + SPACE(1) + pn.PreNombres as Nombres, "
                    + "pn.NroDocIdentidad "
                    + "from PersonaNatural PN "
                    + "INNER JOIN Persona P ON (PN.IdPersona = P.IdPersona) "
                    + "INNER JOIN Socio s ON pn.IdPersona=s.IdPersona "
                    + "LEFT JOIN Personal PE on (PE.IdPersona = P.IdPersona) "
                    + "WHERE PN.NroDocIdentidad='" + valor + "' and (PE.IdPersona is null or PE.IdEstadoPersonal = '1002')";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaNatural objPersonaNatural = new PersonaNatural();
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setNombres(rs.getString("Nombres"));

                listaPersonaNatural.add(objPersonaNatural);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaNatural;

    }

    public List<PersonaNatural> listarPersonaxNombreSocio(String valor) throws Exception {
        List<PersonaNatural> listaPersonaNatural = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pn.IdPersona, pn.PrimerApellido + SPACE(1) + pn.SegundoApellido + SPACE(1) + pn.PreNombres as Nombres, "
                    + "pn.NroDocIdentidad "
                    + "from PersonaNatural PN "
                    + "INNER JOIN Persona P ON (PN.IdPersona = P.IdPersona) "
                    + "INNER JOIN Socio s ON pn.IdPersona=s.IdPersona "
                    + "LEFT JOIN Personal PE on (PE.IdPersona = P.IdPersona) "
                    + "WHERE PN.PrimerApellido + SPACE(1) + PN.SegundoApellido + SPACE(1) + PN.PreNombres LIKE '%" + valor + "%' and (PE.IdPersona is null or PE.IdEstadoPersonal = '1002')";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaNatural objPersonaNatural = new PersonaNatural();
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setNombres(rs.getString("Nombres"));

                listaPersonaNatural.add(objPersonaNatural);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaNatural;

    }

    public List<PersonaNatural> listarPersonaxTarjetaSocio(String valor) throws Exception {
        List<PersonaNatural> listaPersonaNatural = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pn.IdPersona, pn.PrimerApellido + SPACE(1) + pn.SegundoApellido + SPACE(1) + pn.PreNombres as Nombres, "
                    + "pn.NroDocIdentidad "
                    + "from PersonaNatural PN "
                    + "INNER JOIN Persona P ON (PN.IdPersona = P.IdPersona) "
                    + "INNER JOIN Socio s ON pn.IdPersona=s.IdPersona "
                    + "LEFT JOIN Personal PE on (PE.IdPersona = P.IdPersona) "
                    + "INNER JOIN TarjetaElectronica TE ON TE.IdPersona =  PN.IdPersona "
                    + "WHERE TE.Aleatorio='" + valor + "' and (PE.IdPersona is null or PE.IdEstadoPersonal = '1002')";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaNatural objPersonaNatural = new PersonaNatural();
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setNombres(rs.getString("Nombres"));

                listaPersonaNatural.add(objPersonaNatural);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaNatural;

    }

    // Busques Persona No socio no empleado para Registro de SocioNatural
    public PersonaNatural ObtenerPersonaNoSocioNoEmpleado(String dni, String tipoPersona) throws Exception {
        PersonaNatural objPersonaNatural = new PersonaNatural();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ObtenerPersonaNoSocioNoEmpleado (?,?)}");
            cstmt.setString("NroDocIdentidad", dni);
            cstmt.setString("tipoPersona", tipoPersona);

            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                objPersonaNatural.setApPaterno(rs.getString("PrimerApellido"));
                objPersonaNatural.setApMaterno(rs.getString("SegundoApellido"));
                objPersonaNatural.setNombres(rs.getString("PreNombres"));
                objPersonaNatural.getObjTipoDocIdentidad().setTipoPersona(rs.getString("TipoPersona"));
                objPersonaNatural.setFechaNacimiento(rs.getString("FechaNac"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.calcularEdad();
            }
        } catch (Exception e) {
            throw e;
        }

        return objPersonaNatural;
    }

    // Busques Persona No socio no empleado para Registro de SocioNatural
    public PersonaNatural ObtenerPersonaNoEmpleado(String dni, String tipoPersona) throws Exception {
        PersonaNatural objPersonaNatural = new PersonaNatural();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ObtenerPersonaNoEmpleado (?,?)}");
            cstmt.setString("NroDocIdentidad", dni);
            cstmt.setString("tipoPersona", tipoPersona);

            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                objPersonaNatural.setApPaterno(rs.getString("PrimerApellido"));
                objPersonaNatural.setApMaterno(rs.getString("SegundoApellido"));
                objPersonaNatural.setNombres(rs.getString("PreNombres"));
                objPersonaNatural.getObjTipoDocIdentidad().setTipoPersona(rs.getString("TipoPersona"));
                objPersonaNatural.setFechaNacimiento(rs.getString("FechaNac"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));
                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.calcularEdad();
            }
        } catch (Exception e) {
            throw e;
        }

        return objPersonaNatural;
    }

    public List<PersonaNatural> obtenerPersonaNaturalTEXT(String criterio) throws Exception {
        List<PersonaNatural> listPersonaNatural = new ArrayList<>();

        String sql;

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            sql = "select IdPersona,(PreNombres+' '+PrimerApellido+' '+SegundoApellido) as Nombres, NroDocIdentidad from PersonaNatural  \n"
                    + "where NroDocIdentidad like '%" + criterio + "%'  Order by NroDocIdentidad";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaNatural objPersonaNatural = new PersonaNatural();

                objPersonaNatural.setIdPersona(rs.getString("IdPersona"));
                objPersonaNatural.setNombres(rs.getString("Nombres"));
                objPersonaNatural.setNroDocumento(rs.getString("NroDocIdentidad"));

                listPersonaNatural.add(objPersonaNatural);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPersonaNatural;
    }

}
