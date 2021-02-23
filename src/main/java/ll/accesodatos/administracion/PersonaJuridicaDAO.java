package ll.accesodatos.administracion;

import ll.entidades.administracion.PersonaJuridica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Persona;

public class PersonaJuridicaDAO {

    private static PersonaJuridicaDAO _Instancia;

    private PersonaJuridicaDAO() {
    }

    public static PersonaJuridicaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new PersonaJuridicaDAO();
        }
        return _Instancia;
    }

    public Boolean insertar(PersonaJuridica objPersonaJuridica, String Accion) throws Exception {

        boolean result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_upd_PersonaJuridica (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cstmt.setString("TipDocumentoIdentidad", objPersonaJuridica.getObjTipoDocIdentidad().getIdTipoDocIdentidad());
            cstmt.setString("TipoOperacion", Accion);
            cstmt.setString("IdPersona", objPersonaJuridica.getIdPersona());
            cstmt.setString("NroRuc", objPersonaJuridica.getNroDocumento());
            cstmt.setString("RazonSocial", objPersonaJuridica.getNombres());
            cstmt.setString("ActividadEconomica", objPersonaJuridica.getObjActividadEconomica().getIdActEcon());
            //cstmt.setString("IdTipoVivienda", objPersonaJuridica.getTipoVivienda());
            cstmt.setString("FechaConstitucion", objPersonaJuridica.getFechaNacimiento());
            cstmt.setString("Urbanizacion", objPersonaJuridica.getObjUrbanizacion().getCodigo());
            cstmt.setString("Direccion", objPersonaJuridica.getDireccion());
            cstmt.setString("Telefono", objPersonaJuridica.getTelefono());
            cstmt.setString("UbigeoNac", objPersonaJuridica.getObjUbigeoNacionalidad().getCodigo());
            cstmt.setString("IdUbigeoDir", objPersonaJuridica.getObjUbigeo().getCodigo());
            cstmt.setString("Celular", objPersonaJuridica.getCelular());
            cstmt.setString("Referencia", objPersonaJuridica.getReferenciaDireccion());
            //cstmt.setFloat("Ingreso", objPersonaJuridica.getIngresoMensual());
            cstmt.setString("Email", objPersonaJuridica.getCorreo());
            cstmt.setString("IdUsuario", objPersonaJuridica.getIdUsuario());
            cstmt.setString("proveedor", objPersonaJuridica.getProveedor());
            cstmt.setString("idRubro", objPersonaJuridica.getIdRubro());
            cstmt.registerOutParameter("result", java.sql.Types.CHAR);

            cstmt.execute();
            String Result = cstmt.getString("result");
            System.out.println("Resultado" + Result);
            int valor = Integer.parseInt(Result);

            if (valor == 3) {
                throw new Exception("RUC YA ESTA REGISTRADO");
            }

            if (valor == 0) {
                throw new Exception("ERROR AL ACTUALIZAR PARAMETRO");
            } else {
                result = true;
            }

        } catch (Exception e) {

            e.getMessage();
            throw e;
        }

        return result;

    }

    public PersonaJuridica ObtenerPersonaJuridica(String idPersona) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        PersonaJuridica objPersonaJuridica = new PersonaJuridica();

        String Sql = "SELECT ISNULL( pj.IdPersona,'') as IdPersona ,pj.IdTipoDocIdentidad,pj.RazonSocial,pj.NroRuc,pj.IdActEcon,\n"
                + "CONVERT(VARCHAR(10),CONVERT(date,p.FechaNac),103) as FechaNac,\n"
                + "ISNULL(p.IdUbigeoDir,'01000000') as IdUbigeo,ISNULL(p.IdUrbanizacion,'') AS Urbanizacion,\n"
                + "ISNULL(p.Direccion,'') AS Direccion,ISNULL(p.Referencia,'') AS Referencia,p.Telefono,\n"
                + "ISNULL(p.Email,'') AS Email,ISNULL( p.Celular,'') AS Celular,Proveedores = case when Proveedores.Estado is not null then  Proveedores.Estado  else '0' end,idRubro = case when Proveedores.IdRubro IS null then '2101' else Proveedores.IdRubro end \n"
                + "from PersonaJuridica pj INNER JOIN Persona p ON pj.IdPersona = p.IdPersona\n"
                + "INNER JOIN UbiGeo ug ON p.IdUbigeoDir = ug.IdUbigeo\n"
                + "left join Proveedores on pj.IdPersona = Proveedores.IdPersona\n"
                + "WHERE pj.IdPersona='" + idPersona + "'";

        try {

            PreparedStatement pstm = objConexion.prepareStatement(Sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                objPersonaJuridica.setIdPersona(rs.getString("idPersona"));
                objPersonaJuridica.getObjTipoDocIdentidad().setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));
                objPersonaJuridica.getObjActividadEconomica().setIdActEcon(rs.getString("IdActEcon"));
                objPersonaJuridica.setFechaNacimiento(rs.getString("FechaNac"));
                objPersonaJuridica.getObjUbigeo().setUbigeo(rs.getString("IdUbigeo"));
                objPersonaJuridica.getObjUrbanizacion().setCodigo(rs.getString("Urbanizacion"));
                objPersonaJuridica.setDireccion(rs.getString("Direccion"));
                objPersonaJuridica.setCelular(rs.getString("Celular"));
                objPersonaJuridica.setTelefono(rs.getString("Telefono"));
                objPersonaJuridica.setCorreo(rs.getString("Email"));
                objPersonaJuridica.setReferenciaDireccion(rs.getString("Referencia"));
                objPersonaJuridica.setProveedor(rs.getString("Proveedores"));
                objPersonaJuridica.setIdRubro(rs.getString("idRubro"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            objConexion.close();
        }
        return objPersonaJuridica;
    }

    public List<PersonaJuridica> listarPersona(int criterio, String valor) throws Exception {
        List<PersonaJuridica> listaPersonaJuridicas = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String CriterioSQL;
            switch (criterio) {
                case 1:
                    CriterioSQL = "pj.NroRuc='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = "pj.RazonSocial  LIKE '%" + valor + "%'";
                    break;
                default:
                    CriterioSQL = " te.Aleatorio ='" + valor + "'";
                    break;
            }

            String sql = "SELECT pj.IdPersona,pj.RazonSocial,pj.NroRuc,p.direccion,Proveedores = case when pro.Estado is not null then case pro.Estado when '0' then 'NO' when '1' then 'SI' end else 'NO' end\n"
                    + "FROM PersonaJuridica pj JOIN Persona p ON pj.idPersona=p.idPersona\n"
                    + "left join proveedores pro on pj.IdPersona = pro.IdPersona\n"
                    + "WHERE pj.IdPersona IS NOT NULL AND " + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();
                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));
                objPersonaJuridica.setDireccion(rs.getString("direccion"));
                objPersonaJuridica.setProveedor(rs.getString("Proveedores"));
                listaPersonaJuridicas.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaJuridicas;

    }

    public List<PersonaJuridica> listarPersonaxRUC(String valor) throws Exception {
        List<PersonaJuridica> listaPersonaJuridica = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pj.IdPersona,pj.RazonSocial,pj.NroRuc "
                    + "FROM PersonaJuridica pj "
                    + "LEFT JOIN  Socio s ON pj.IdPersona=s.IdPersona "
                    + "WHERE pj.IdPersona IS NOT NULL AND  pj.NroRuc='" + valor + "'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();
                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));

                listaPersonaJuridica.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaJuridica;

    }

    public List<PersonaJuridica> listarPersonaxRS(String valor) throws Exception {
        List<PersonaJuridica> listaPersonaJuridica = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pj.IdPersona,pj.RazonSocial,pj.NroRuc "
                    + "FROM PersonaJuridica pj "
                    + "LEFT JOIN  Socio s ON pj.IdPersona=s.IdPersona "
                    + "WHERE pj.IdPersona IS NOT NULL AND pj.RazonSocial  LIKE '%" + valor + "%'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();
                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));

                listaPersonaJuridica.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaJuridica;

    }

    public List<PersonaJuridica> listarPersonaxTarjeta(String valor) throws Exception {
        List<PersonaJuridica> listaPersonaJuridica = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "SELECT pj.IdPersona,pj.RazonSocial,pj.NroRuc "
                    + "FROM PersonaJuridica pj "
                    + "LEFT JOIN  Socio s ON pj.IdPersona=s.IdPersona "
                    + "LEFT JOIN TarjetaElectronica te ON pj.IdPersona = te.IdPersona "
                    + "WHERE pj.IdPersona IS NOT NULL te.Aleatorio ='" + valor + "'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();
                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));

                listaPersonaJuridica.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaJuridica;

    }

    // Busques Persona Juridica No socio
    public PersonaJuridica ObtenerPersonaNoSocioNoEmpleado(String dni, String tipoPersona) throws Exception {
        PersonaJuridica objJuridica = new PersonaJuridica();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ObtenerPersonaNoSocioNoEmpleado(?,?)}");
            cstmt.setString("NroDocIdentidad", dni);
            cstmt.setString("tipoPersona", tipoPersona);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                objJuridica.setIdPersona(rs.getString("IdPersona"));
                objJuridica.setNroDocumento(rs.getString("NroRuc"));
                objJuridica.setNombres(rs.getString("RazonSocial"));
                objJuridica.getObjTipoDocIdentidad().setTipoPersona(rs.getString("TipoPersona"));
            }

        } catch (Exception e) {
            throw e;
        }

        return objJuridica;
    }

    public List<PersonaJuridica> obtenerPersonaJuridicaTEXT(String criterio) throws Exception {
        List<PersonaJuridica> listPersonaJuridica = new ArrayList<>();

        String sql;

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            sql = "select PJ.IdPersona, PJ.RazonSocial, PJ.NroRuc, P.Direccion\n"
                    + "from Personajuridica PJ\n"
                    + "join Persona P on PJ.IdPersona = P.IdPersona\n"
                    + "Where NroRuc LIKE '%'+ '" + criterio + "'  +'%'  Order by NroRuc";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();

                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));
                objPersonaJuridica.setDireccion(rs.getString("Direccion"));

                listPersonaJuridica.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPersonaJuridica;
    }

    public List<PersonaJuridica> obtenerPersonaNaturalJuridica(String Criterio, String TipoBusqueda, String TipoPersona) throws Exception {

        List<PersonaJuridica> listaPersona = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_Obtener_PersonaNaturalJuridica (?,?,?)}");

            cstmt.setString("criterio", Criterio);
            cstmt.setString("tipoBusqueda", TipoBusqueda);
            cstmt.setString("tipoPersona", TipoPersona);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                PersonaJuridica objPersona = new PersonaJuridica();
                objPersona.setIdPersona(rs.getString("IdPersona"));
                objPersona.setNombres(rs.getString("Nombres"));
                objPersona.setNroDocumento(rs.getString("NroDocIdentidad"));
                listaPersona.add(objPersona);
            }

        } catch (Exception ex) {
            throw ex;
        }

        return listaPersona;
    }

    public List<Persona> obtenerPersonaNaturalJuridicaText(String Criterio) throws Exception {

        List<Persona> listaPersona = new ArrayList<>();
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call obtenerPersonaNaturalJuridicaXnroDoc (?)}");

            cstmt.setString("criterio", Criterio);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Persona objPersona = new Persona();
                objPersona.setIdPersona(rs.getString("IdPersona"));
                objPersona.setNombres(rs.getString("Nombre"));
                objPersona.setNroDocumento(rs.getString("NroDocumento"));
                listaPersona.add(objPersona);
            }

        } catch (Exception ex) {
            throw ex;
        }

        return listaPersona;
    }

    public List<PersonaJuridica> listarPersonaJuridicaNoBancos(int criterio, String valor) throws Exception {
        List<PersonaJuridica> listaPersonaJuridicas = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String CriterioSQL;
            switch (criterio) {
                case 1:
                    CriterioSQL = "pj.NroRuc='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = "pj.RazonSocial  LIKE '%" + valor + "%'";
                    break;
                default:
                    CriterioSQL = " te.Aleatorio ='" + valor + "'";
                    break;
            }

            String sql = "Select pj.IdPersona,pj.RazonSocial,NroRuc = case when pj.NroRuc is NULL then '' else pj.NroRuc end from PersonaJuridica pj\n"
                    + "left join Bancos on Bancos.IdPersonaBanco = pj.IdPersona\n"
                    + "where Bancos.IdPersonaBanco is null  and " + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                PersonaJuridica objPersonaJuridica = new PersonaJuridica();
                objPersonaJuridica.setIdPersona(rs.getString("IdPersona"));
                objPersonaJuridica.setNombres(rs.getString("RazonSocial"));
                objPersonaJuridica.setNroDocumento(rs.getString("NroRuc"));
                listaPersonaJuridicas.add(objPersonaJuridica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaPersonaJuridicas;

    }

}
