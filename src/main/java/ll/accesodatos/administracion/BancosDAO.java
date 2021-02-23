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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Bancos;
import ll.entidades.administracion.CtaBanco;
import ll.entidades.administracion.TipoCtaBanco;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author RenRio
 */
public class BancosDAO {

    private static BancosDAO _Instancia;

    private BancosDAO() {
    }

    public static BancosDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new BancosDAO();
        }
        return _Instancia;
    }

    public List<Bancos> listarBancos() throws Exception {
        List<Bancos> listaBcos = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select IdPersonaBanco, NombreBanco,NombreAbrev from Bancos";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Bancos objBanco = new Bancos();
                objBanco.getObjPersonaBanco().setIdPersona(rs.getString("IdPersonaBanco"));
                objBanco.setNombreBanco(rs.getString("NombreBanco"));
                objBanco.setNombreAbv(rs.getString("NombreAbrev"));

                listaBcos.add(objBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaBcos;

    }

    public List<CtaBanco> listarCtaBancos(String IdPersonaBanco, String IdTipMoneda) throws Exception {
        List<CtaBanco> listaCtaBco = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select BD.IdTipoCtaBco , (rtrim(TCB.TipoCta) +'/'+ rtrim(BD.NroCtaBco))  AS CtaBanco from Bancos B  \n"
                    + "inner join DefineBancos BD on BD.idpersonaBco = B.IdPersonaBanco\n"
                    + "inner join TipCtaBanco TCB on BD.idTipoCtaBco = TCB.IdTipCtaBanco\n"
                    + "where BD.TipMonedaCtaBco ='" + IdTipMoneda + "' and BD.IdPersonaBco='" + IdPersonaBanco + "'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                CtaBanco objCtaBanco = new CtaBanco();
                objCtaBanco.setIdTipCtaBanco(rs.getInt("IdTipoCtaBco"));
                objCtaBanco.setCtaBanco(rs.getString("CtaBanco"));

                listaCtaBco.add(objCtaBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCtaBco;

    }

    public List<TipoCtaBanco> listarTipoCtaBancos() throws Exception {
        List<TipoCtaBanco> listaTipCtaBcos = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select IdTipCtaBanco,TipoCta from TipCtaBanco where Estado ='1'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoCtaBanco objTipCtaBanco = new TipoCtaBanco();
                objTipCtaBanco.setIdTipCtaBanco(rs.getInt("IdTipCtaBanco"));
                objTipCtaBanco.setTipoCtaBanco(rs.getString("TipoCta"));

                listaTipCtaBcos.add(objTipCtaBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaTipCtaBcos;

    }

    public List<Bancos> listarBancosBusqueda(int criterio, String valor) throws Exception {
        List<Bancos> listaBancos = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String CriterioSQL;
            switch (criterio) {
                case 1:
                    CriterioSQL = "pj.NroRuc='" + valor + "'";
                    break;
                case 2:
                    CriterioSQL = "b.NombreBanco  LIKE '%" + valor + "%'";
                    break;
                default:
                    CriterioSQL = " te.Aleatorio ='" + valor + "'";
                    break;
            }

            String sql = "select b.IdPersonaBanco, b.NombreBanco, pj.NroRuc from Bancos b\n"
                    + "join PersonaJuridica pj on b.IdPersonaBanco = pj.IdPersona\n"
                    + " WHERE b.IdPersonaBanco IS NOT NULL AND " + CriterioSQL;

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Bancos objBanco = new Bancos();
                objBanco.getObjPersonaBanco().setIdPersona(rs.getString("IdPersonaBanco"));
                objBanco.setNombreBanco(rs.getString("NombreBanco"));
                objBanco.getObjPersonaBanco().setNroDocumento(rs.getString("NroRuc"));

                listaBancos.add(objBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaBancos;

    }

    public Boolean registrarBanco(Bancos objBanco) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        Boolean Resultado = false;
        String sql = "INSERT INTO  Bancos "
                + "( IdPersonaBanco , NombreBanco , NombreAbrev)"
                + " VALUES "
                + "(?,?,?)";
        PreparedStatement pstm = objConexion.prepareStatement(sql);
        try {
            objConexion.setAutoCommit(false);

            pstm.setString(1, objBanco.getObjPersonaBanco().getIdPersona());
            pstm.setString(2, objBanco.getNombreBanco());
            pstm.setString(3, objBanco.getNombreAbv());
            pstm.executeUpdate();

            Resultado = true;
            objConexion.commit();

        } catch (SQLException e) {

            objConexion.rollback();
            e.getMessage();
        } finally {
            objConexion.setAutoCommit(true);
            objConexion.close();
        }
        return Resultado;
    }

    public Boolean registrarCtaBanco(CtaBanco objCtaBanco, Usuario objUsuario) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        Boolean Resultado = false;
        String sql = "INSERT INTO  DefineBancos "
                + "( IdTipoCtaBco , NroCtaBco ,IdPersonaBco, TipMonedaCtaBco,FechaApertura,Activo,IdUsuario)"
                + " VALUES "
                + "(?,?,?,?,?,?,?)";
        PreparedStatement pstm = objConexion.prepareStatement(sql);
        try {
            objConexion.setAutoCommit(false);

            pstm.setInt(1, objCtaBanco.getIdTipCtaBanco());
            pstm.setString(2, objCtaBanco.getCtaBanco());
            pstm.setString(3, objCtaBanco.getObjBanco().getObjPersonaBanco().getIdPersona());
            pstm.setString(4, objCtaBanco.getTipMoneda());
            pstm.setString(5, objCtaBanco.getFechaApertura());
            pstm.setString(6, "1");
            pstm.setString(7, objUsuario.getIdUsuario());
            pstm.executeUpdate();

            Resultado = true;
            objConexion.commit();

        } catch (SQLException e) {

            objConexion.rollback();
            e.getMessage();
        } finally {
            objConexion.setAutoCommit(true);
            objConexion.close();
        }
        return Resultado;
    }

    public List<CtaBanco> listarCta(String IdPersonaBanco) throws Exception {
        List<CtaBanco> listaCtaBco = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select  Bancos.NombreBanco,DefineBancos.NroCtaBco,\n"
                    + "Moneda = case when DefineBancos.TipMonedaCtaBco =1 then 'SOLES' else 'DOLARES' end,\n"
                    + "TipCtaBanco.TipoCta from DefineBancos\n"
                    + "join Bancos on DefineBancos.IdPersonaBco=Bancos.IdPersonaBanco\n"
                    + "join TipCtaBanco on DefineBancos.IdTipoCtaBco = TipCtaBanco.IdTipCtaBanco\n"
                    + "where IdPersonaBco='" + IdPersonaBanco + "'";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                CtaBanco objCtaBanco = new CtaBanco();
                objCtaBanco.getObjBanco().setNombreBanco(rs.getString("NombreBanco"));
                objCtaBanco.setCtaBanco(rs.getString("NroCtaBco"));
                objCtaBanco.setTipMoneda(rs.getString("Moneda"));
                objCtaBanco.setTipCtaBanco(rs.getString("TipoCta"));
                listaCtaBco.add(objCtaBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCtaBco;

    }

    //funciones nuevas
    public List<Bancos> listarBancosNEW() throws Exception {

        List<Bancos> listaBancos = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select Bancos.IdPersonaBanco, pj.RazonSocial,NombreAbrev = case when Bancos.NombreAbrev is null then '' else Bancos.NombreAbrev end,pj.NroRuc from Bancos\n"
                    + "join PersonaJuridica pj on pj.IdPersona = Bancos.IdPersonaBanco";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Bancos objBancos = new Bancos();
                objBancos.getObjPersonaBanco().setIdPersona(rs.getString("IdPersonaBanco"));
                objBancos.setNombreBanco(rs.getString("RazonSocial"));
                objBancos.setNombreAbv(rs.getString("NombreAbrev"));
                objBancos.getObjPersonaBanco().setNroDocumento(rs.getString("NroRuc"));
                listaBancos.add(objBancos);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaBancos;

    }

    public Bancos obtenerBanco(String idPersona) throws Exception {
        Bancos objBancos = new Bancos();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {
            String sql = "select Bancos.IdPersonaBanco, pj.RazonSocial,NombreAbrev = case when Bancos.NombreAbrev is null then '' else Bancos.NombreAbrev end,pj.NroRuc from Bancos\n"
                    + "join PersonaJuridica pj on pj.IdPersona = Bancos.IdPersonaBanco\n"
                    + "where Bancos.IdPersonaBanco='" + idPersona + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                objBancos.getObjPersonaBanco().setIdPersona(rs.getString("IdPersonaBanco"));
                objBancos.setNombreBanco(rs.getString("RazonSocial"));
                objBancos.setNombreAbv(rs.getString("NombreAbrev"));
                objBancos.getObjPersonaBanco().setNroDocumento(rs.getString("NroRuc"));
            }
        } catch (Exception e) {
            throw e;
        }
        return objBancos;
    }

    public List<CtaBanco> listaCuentaBancos(String idPersonaBco) throws Exception {

        List<CtaBanco> listaCtaBanco = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select  DB.IdPersonaBco,DB.IdTipoCtaBco,TCB.TipoCta,DB.NroCtaBco,DB.TipMonedaCtaBco,FechaApertura= convert(char(10), DB.FechaApertura,103) from DefineBancos DB\n"
                    + "join TipCtaBanco TCB on tcb.IdTipCtaBanco = DB.IdTipoCtaBco where DB.IdPersonaBco='" + idPersonaBco + "' and Activo='1'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                CtaBanco objCtaBanco = new CtaBanco();
                objCtaBanco.getObjBanco().getObjPersonaBanco().setIdPersona(rs.getString("IdPersonaBco"));
                objCtaBanco.getObjTipoCtaBanco().setIdTipCtaBanco(rs.getInt("IdTipoCtaBco"));
                objCtaBanco.getObjTipoCtaBanco().setTipoCtaBanco(rs.getString("TipoCta"));
                objCtaBanco.getObjTipoMoneda().setId(rs.getString("TipMonedaCtaBco"));
                objCtaBanco.getObjTipoMoneda().setDescripcion(rs.getString("TipMonedaCtaBco"));
                objCtaBanco.setCtaBanco(rs.getString("NroCtaBco"));
                objCtaBanco.setFechaApertura(rs.getString("FechaApertura"));
                listaCtaBanco.add(objCtaBanco);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCtaBanco;

    }

    public String registrarBancoNEW(Bancos objBancos) throws Exception {
        String result = null;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call sch_Admision_ins_Bancos(?,?,?,?)}");

            cs.setString("idPersona", objBancos.getObjPersonaBanco().getIdPersona());
            cs.setString("razonSocial", objBancos.getNombreBanco());
            cs.setString("abrev", objBancos.getNombreAbv());
            cs.registerOutParameter("result", java.sql.Types.VARCHAR);
            cs.execute();

            result = cs.getString("result");

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public String registrarCtaBancos(CtaBanco objCtaBanco, Usuario objUsuario) throws Exception {
        String result;
        try (Connection objConexion = Connect.Instancia().getConnectBD()) {
            CallableStatement cstmt = objConexion.prepareCall("{call sch_Admision_ins_del_CtaBancos (?,?,?,?,?,?,?,?)}");

            cstmt.setString("idPersona", objCtaBanco.getObjBanco().getObjPersonaBanco().getIdPersona());
            cstmt.setInt("idTipoCtaBco", objCtaBanco.getObjTipoCtaBanco().getIdTipCtaBanco());
            cstmt.setString("nroCtaBco", objCtaBanco.getCtaBanco());
            cstmt.setString("tipMoneda", objCtaBanco.getObjTipoMoneda().getId());
            cstmt.setString("fechaApertura", objCtaBanco.getFechaApertura());
            cstmt.setString("idUsuario", objUsuario.getIdUsuario());
            cstmt.setString("accion", objCtaBanco.getAccion());
            cstmt.registerOutParameter("result", java.sql.Types.VARCHAR);
            cstmt.execute();
            result = cstmt.getString("result");
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
