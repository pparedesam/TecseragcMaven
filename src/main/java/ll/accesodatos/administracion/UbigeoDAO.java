/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Distrito;
import ll.entidades.administracion.Pais;
import ll.entidades.administracion.Provincia;
import ll.entidades.administracion.Ubigeo;

/**
 *
 * @author paupar
 */
public class UbigeoDAO {

    private static UbigeoDAO _Instancia;

    private UbigeoDAO() {
    }

    public static UbigeoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new UbigeoDAO();
        }
        return _Instancia;
    }

    public List<Pais> listarPaises() throws Exception {

        List<Pais> listPais = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select Left(IdUbigeo,2) as idPais, Descripcion from UbiGeo where  RIGHT(IdUbigeo,6) = '000000'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Pais objPais = new Pais();
                objPais.setCodigo(rs.getString("idPais"));
                objPais.setDescripcion(rs.getString("Descripcion"));

                listPais.add(objPais);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPais;
    }

    public List<Departamento> listarDepartamentos(String idPais) throws Exception {

        List<Departamento> listDepartamento = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select SubString(IdUbigeo,3,2) as idDpto, Descripcion from UbiGeo where  RIGHT(IdUbigeo,4) = '0000' and not  SubString(IdUbigeo,3,2) ='00'\n"
                    + "and SubString(IdUbigeo,1,2)  = '" + idPais + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Departamento objDepartamento = new Departamento();
                objDepartamento.setCodigo(rs.getString("idDpto"));
                objDepartamento.setDescripcion(rs.getString("Descripcion"));

                listDepartamento.add(objDepartamento);
            }

        } catch (Exception e) {
            throw e;
        }

        return listDepartamento;
    }

    public List<Provincia> listarProvincias(String idPais, String idDepartamento) throws Exception {

        List<Provincia> listProvincia = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select SubString(IdUbigeo,5,2) as idProvincia, Descripcion from UbiGeo where  RIGHT(IdUbigeo,2) = '00' and not  SubString(IdUbigeo,5,2) ='00'\n"
                    + " and SubString(IdUbigeo,1,2)  = '" + idPais + "' and SubString(IdUbigeo,3,2)  = '" + idDepartamento + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Provincia objProvincia = new Provincia();
                objProvincia.setCodigo(rs.getString("idProvincia"));
                objProvincia.setDescripcion(rs.getString("Descripcion"));

                listProvincia.add(objProvincia);
            }

        } catch (Exception e) {
            throw e;
        }

        return listProvincia;
    }

    public List<Distrito> listarDistritos(String idPais, String idDepartamento, String idProvincia) throws Exception {

        List<Distrito> listDistrito = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select SubString(IdUbigeo,7,2) as idDistrito, Descripcion from UbiGeo where   not  SubString(IdUbigeo,7,2) ='00'\n"
                    + "and SubString(IdUbigeo,1,2)  = '" + idPais + "' and SubString(IdUbigeo,3,2)  = '" + idDepartamento + "' and SubString(IdUbigeo,5,2)  = '" + idProvincia + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Distrito objDistrito = new Distrito();
                objDistrito.setCodigo(rs.getString("idDistrito"));
                objDistrito.setDescripcion(rs.getString("Descripcion"));

                listDistrito.add(objDistrito);
            }

        } catch (Exception e) {
            throw e;
        }

        return listDistrito;
    }

    public List<Ubigeo> listarUbigeo() throws Exception {

        List<Ubigeo> listUbigeo = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT ug.IdUbigeo,ug.Descripcion,ug.Nivel,ug.Ubigeo FROM UbiGeo ug";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Ubigeo objUbigeo = new Ubigeo();
                objUbigeo.setCodigo(rs.getString("IdUbigeo"));
                objUbigeo.setDescripcion(rs.getString("Descripcion"));
                objUbigeo.setNivel(rs.getString("Nivel"));
                objUbigeo.setUbigeo(rs.getString("Ubigeo"));
                listUbigeo.add(objUbigeo);
            }

        } catch (Exception e) {
            throw e;
        }

        return listUbigeo;
    }

    public List<Pais> listarPaisesGR() throws Exception {

        List<Pais> listPais = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT idPais=IdUbigeo,Descripcion FROM UBIGEO WHERE Nivel=0 ORDER BY IdUbigeo";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Pais objPais = new Pais();
                objPais.setCodigo(rs.getString("idPais"));
                objPais.setDescripcion(rs.getString("Descripcion"));

                listPais.add(objPais);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPais;
    }

    public List<Departamento> listarDepartamentos() throws Exception {

        List<Departamento> listDepartamento = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "Select idDpto=IdUbigeo, Descripcion from UbiGeo where Nivel=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Departamento objDepartamento = new Departamento();
                objDepartamento.setCodigo(rs.getString("idDpto"));
                objDepartamento.setDescripcion(rs.getString("Descripcion"));

                listDepartamento.add(objDepartamento);
            }

        } catch (Exception e) {
            throw e;
        }

        return listDepartamento;
    }

    public List<Provincia> listarPronvincias() throws Exception {

        List<Provincia> listProvincia = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            //String sql = "Select idProvincia=u2.IdUbigeo, u2.Descripcion as Provincia, Region=(Select idUbigeo from Ubigeo where Nivel=1 and trim(Descripcion)=trim(SUBSTRING(SUBSTRING(u2.Ubigeo,(CHARINDEX('-', u2.Ubigeo) + 1), LEN(u2.Ubigeo)), 1, CHARINDEX('-', SUBSTRING(u2.Ubigeo,(CHARINDEX('-', u2.Ubigeo) + 1), LEN(u2.Ubigeo))) - 1))) from (select * from ubigeo where nivel=2) as u2  ";
            String sql = "select idProvincia=up.IdUbigeo,Provincia=up.Descripcion,Region=ur.IdUbigeo from UbiGeo up join UbiGeo ur on CONCAT(left(up.IdUbigeo,4),'0000')=ur.idUbigeo where up.nivel='2' ";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Provincia objProvincia = new Provincia();
                objProvincia.setCodigo(rs.getString("idProvincia"));
                objProvincia.setDescripcion(rs.getString("Provincia"));
                objProvincia.setRegion(rs.getString("Region"));

                listProvincia.add(objProvincia);
            }

        } catch (Exception e) {
            throw e;
        }

        return listProvincia;
    }

    public List<Distrito> listarDistritos() throws Exception {

        List<Distrito> listDistrito = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            //String sql = "Select idDistrito=u2.IdUbigeo, u2.Descripcion as Distrito, Provincia=(Select idUbigeo from Ubigeo where Nivel=2 and trim(Descripcion)=trim(SUBSTRING(SUBSTRING(u2.Ubigeo,(CHARINDEX('-', u2.Ubigeo) + 1), LEN(u2.Ubigeo)), 1, CHARINDEX('-', SUBSTRING(u2.Ubigeo,(CHARINDEX('-', u2.Ubigeo) + 1), LEN(u2.Ubigeo))) - 1))) from (select * from ubigeo where nivel=3) as u2  ";
            String sql = "select idDistrito=ud.IdUbigeo,Distrito=ud.Descripcion,Provincia=up.IdUbigeo from UbiGeo ud join UbiGeo up on CONCAT(left(ud.IdUbigeo,6),'00')=up.idUbigeo where ud.nivel='3'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Distrito objDistrito = new Distrito();
                objDistrito.setCodigo(rs.getString("idDistrito"));
                objDistrito.setDescripcion(rs.getString("Distrito"));
                objDistrito.setProvincia(rs.getString("Provincia"));

                listDistrito.add(objDistrito);
            }

        } catch (Exception e) {
            throw e;
        }

        return listDistrito;
    }

    public String registrarUbigeo(Ubigeo objUbigeo, String Accion) throws Exception {

        String result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Afiliaciones_sp_ins_upd_Ubigeo(?,?,?,?,?,?)}");

            cstmt.setString("codigo", objUbigeo.getCodigo());
            cstmt.setString("descripcion", objUbigeo.getDescripcion());
            cstmt.setString("nivel", objUbigeo.getNivel());
            cstmt.setString("ubigeo", objUbigeo.getUbigeo());
            cstmt.setString("accion", Accion);

            cstmt.registerOutParameter("result", java.sql.Types.CHAR);

            cstmt.execute();
            result = cstmt.getString("result");

        } catch (Exception e) {

            e.getMessage();
            throw e;
        }

        return result.trim();
    }
}
