/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.contabilidad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.Cheque;

/**
 *
 * @author RenRio
 */
public class ChequeDAO {

    private static ChequeDAO _Instancia;

    private ChequeDAO() {
    }

    public static ChequeDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ChequeDAO();
        }
        return _Instancia;
    }

    public String registrarCheque(Cheque objCheque, Usuario objUsuario) throws Exception {
        String result = "ERROR";

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call schContabilidad_Insert_Cheque(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(2, objCheque.getObjPersona().getIdPersona());
            cs.setString(3, objCheque.getTipMoneda());
            cs.setString(4, objCheque.getFechaDoc());
            cs.setString(5, objCheque.getGlosaFija());
            cs.setString(6, objCheque.getGlosaVariable());
            cs.setString(7, objUsuario.getIdUsuario());
            cs.setInt(8, objCheque.getObjCtaBanco().getIdTipCtaBanco());
            cs.setString(9, objCheque.getObjCtaBanco().getCtaBanco());
            cs.setString(10, objCheque.getObjBanco().getObjPersonaBanco().getIdPersona());
            cs.setString(11, objCheque.getNrocheque());
            cs.setDouble(12, objCheque.getMonto());
            cs.setString(13, result);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);

            cs.execute();

            result = cs.getString(13);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public List<Cheque> listarCheque(int criterio, String valor) throws Exception {
        List<Cheque> listaCheque = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String[] parts = valor.split("&");
            String CriterioSQL = null;
            switch (criterio) {

                case 1:

                    CriterioSQL = "dg.FechaDocumento >= '" + parts[0] + "' and dg.FechaDocumento<= '" + parts[1] + "'";
                    break;
                case 2:
                    CriterioSQL = "mc.NroCheque='" + valor + "'";

                    break;
                case 3:
                    CriterioSQL = "dcv.FechaDoctoExt >= '" + parts[0] + "' and dcv.FechaDoctoExt<= '" + parts[1] + "' and dg.TipMoneda='" + parts[2] + "'";
                    break;
            }

            String sql = "select dg.idoficina,dg.TipMoneda,dg.IdDoc,dg.NroDoc ,mc.NroCheque, Nombres = case when pj.RazonSocial is not null then pj.RazonSocial else pn.PreNombres+' '+pn.primerapellido+' '+pn.SegundoApellido end,dg.FechaDocumento,\n"
                    + "Moneda = case when mc.TipMoneda =1 then 'SOLES' else 'DOLARES' end,\n"
                    + "Monto = case when mc.TipMoneda =1 then t.MontoSoles else t.MontoDolar end,ted.Estado from MoviCheques MC \n"
                    + "join Transaccion t on mc.idoficina = t.idoficina and mc.tipmoneda = t.idTipMoneda and mc.IdDoc =t.IdDoc and mc.NroDoc = t.NroDoc\n"
                    + "join DocGenerado dg on mc.idoficina = dg.idoficina and mc.tipmoneda = dg.TipMoneda and mc.IdDoc =dg.IdDoc and mc.NroDoc = dg.NroDoc\n"
                    + "join persona p on dg.IdPersona = p.IdPersona\n"
                    + "left join PersonaNatural pn on t.IdPersona = pn.IdPersona\n"
                    + "left join PersonaJuridica pj on t.IdPersona = pj.IdPersona\n"
                    + "join TabEstadoDoc ted on dg.IdEstDoc = ted.IdEstDoc and " + CriterioSQL + "order by dg.FechaDocumento";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Cheque objCheque = new Cheque();

                objCheque.getObjDocGenerado().getObjOficina().setIdOficina(rs.getString("idoficina"));
                objCheque.getObjDocGenerado().getObjTipoMoneda().setId(rs.getString("TipMoneda"));
                objCheque.getObjDocGenerado().setIdDoc(rs.getString("IdDoc"));
                objCheque.getObjDocGenerado().setNroDoc(rs.getString("NroDoc"));
                objCheque.setNrocheque(rs.getString("NroCheque"));
                objCheque.getObjPersona().setNombres(rs.getString("Nombres"));
                objCheque.setTipMoneda(rs.getString("Moneda"));
                objCheque.getObjDocGenerado().setFechaDocumento(rs.getString("FechaDocumento"));
                objCheque.setMonto(rs.getDouble("Monto"));
                objCheque.setEstado(rs.getString("Estado"));
                listaCheque.add(objCheque);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCheque;

    }

    public List<Cheque> listarChequesDelDia() throws Exception {
        List<Cheque> listaCheque = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "select \n"
                    + "		MC.NroCheque\n"
                    + "		,dg.IdDoc\n"
                    + "		,dg.NroDoc\n"
                    + "		,dg.IdOficina\n"
                    + "		,FechaDocumento=convert(char(10),dg.FechaDocumento,103)\n"
                    + "		,Nombres = case when pj.RazonSocial is not null then pj.RazonSocial else pn.PreNombres+' '+pn.primerapellido+' '+pn.SegundoApellido end\n"
                    + "		,Monto = case when mc.TipMoneda =1 then t.MontoSoles else t.MontoDolar end\n"
                    + "		,Letra = REPLACE(dbo.fnNumeroExpresadoEnLetra(case when mc.TipMoneda =1 then t.MontoSoles else t.MontoDolar end, mc.TipMoneda),'%','')\n"
                    + "		,TM.IdTipMoneda\n"
                    + "		,Moneda = TM.descripcion\n"
                    + "		,IdEstado=dg.IdEstDoc\n"
                    + "		,Estado = ted.Estado\n"
                    + "		,GlosaFija=UPPER(dg.GlosaFija)\n"
                    + "	from \n"
                    + "		MoviCheques MC\n"
                    + "		join TipoMoneda TM on TM.IdTipMoneda = MC.TipMoneda\n"
                    + "		join Transaccion t on mc.idoficina = t.idoficina and mc.tipmoneda = t.idTipMoneda and mc.IdDoc =t.IdDoc and mc.NroDoc = t.NroDoc\n"
                    + "		join DocGenerado dg on mc.idoficina = dg.idoficina and mc.tipmoneda = dg.TipMoneda and mc.IdDoc =dg.IdDoc and mc.NroDoc = dg.NroDoc\n"
                    + "		join persona p on dg.IdPersona = p.IdPersona\n"
                    + "		left join PersonaNatural pn on t.IdPersona = pn.IdPersona\n"
                    + "		left join PersonaJuridica pj on t.IdPersona = pj.IdPersona\n"
                    + "		join TabEstadoDoc ted on dg.IdEstDoc = ted.IdEstDoc\n"
                    + "where \n"
                    + "	dg.FechaDocumento=convert(date,getdate(),103) \n"
                    + "order by \n"
                    + "	dg.FechaDocumento desc";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Cheque objCheque = new Cheque();

                objCheque.setNrocheque(rs.getString("NroCheque"));
                objCheque.getObjDocGenerado().setFechaDocumento(rs.getString("FechaDocumento"));
                objCheque.getObjDocGenerado().setIdDoc(rs.getString("IdDoc"));
                objCheque.getObjDocGenerado().setNroDoc(rs.getString("NroDoc"));
                objCheque.getObjDocGenerado().getObjOficina().setIdOficina(rs.getString("IdOficina"));
                objCheque.getObjDocGenerado().getObjTipoMoneda().setId(rs.getString("IdTipMoneda"));
                objCheque.getObjDocGenerado().setIdEstadoDocumento(rs.getString("IdEstado"));
                objCheque.getObjPersona().setNombres(rs.getString("Nombres"));
                objCheque.setMonto(rs.getDouble("Monto"));
                objCheque.setTipMoneda(rs.getString("Moneda"));
                objCheque.setMontoLetras(rs.getString("Letra"));
                objCheque.setEstado(rs.getString("Estado"));
                objCheque.getObjDocGenerado().setGlosaVariable(rs.getString("GlosaFija"));
                listaCheque.add(objCheque);
            }
        } catch (Exception e) {
            throw e;
        }
        return listaCheque;
    }

    public List<Cheque> buscarCheques(String nroCheque, String fecIni, String fecFin) throws Exception {
        List<Cheque> listaCheque = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD()) {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_buscarCheque(?,?,?)}");
            cstmt.setString(1, nroCheque);
            cstmt.setString(2, fecIni);
            cstmt.setString(3, fecFin);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                Cheque objCheque = new Cheque();

                objCheque.setNrocheque(rs.getString("NroCheque"));
                objCheque.getObjDocGenerado().setFechaDocumento(rs.getString("FechaDocumento"));
                objCheque.getObjDocGenerado().setIdDoc(rs.getString("IdDoc"));
                objCheque.getObjDocGenerado().setNroDoc(rs.getString("NroDoc"));
                objCheque.getObjDocGenerado().getObjOficina().setIdOficina(rs.getString("IdOficina"));
                objCheque.getObjDocGenerado().getObjTipoMoneda().setId(rs.getString("IdTipMoneda"));
                objCheque.getObjDocGenerado().setIdEstadoDocumento(rs.getString("IdEstado"));
                objCheque.getObjPersona().setNombres(rs.getString("Nombres"));
                objCheque.setMonto(rs.getDouble("Monto"));
                objCheque.setTipMoneda(rs.getString("Moneda"));
                objCheque.setMontoLetras(rs.getString("Letra"));
                objCheque.setEstado(rs.getString("Estado"));
                objCheque.getObjDocGenerado().setGlosaVariable(rs.getString("GlosaFija"));
                listaCheque.add(objCheque);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCheque;

    }

    public Boolean anularCheque(Cheque objCheque, Usuario objUsuario, String motivo) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {
            String sql = "UPDATE DocGenerado\n"
                    + "   SET \n"
                    + "      [IdEstDoc] = '05'\n"
                    + "      ,[FechaCambioEstado] = CONVERT(CHAR(10),GETDATE(),103)\n"
                    + "      ,[GlosaVariable] = '" + motivo + "'\n"
                    + "      ,[HoraCambioEstado] = RIGHT( '0'+LTRIM(RIGHT(CONVERT(CHAR(19),GETDATE()),7)),7)\n"
                    + "      ,[IdUsuario] = '" + objUsuario.getIdUsuario() + "' "
                    + " where IdDoc='" + objCheque.getObjDocGenerado().getIdDoc() + "' and NroDoc ='" + objCheque.getObjDocGenerado().getNroDoc() + "' and IdOficina='" + objCheque.getObjDocGenerado().getObjOficina().getIdOficina() + "' and TipMoneda='" + objCheque.getObjDocGenerado().getObjTipoMoneda().getId() + "'";

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

    public List<Cheque> listarRptCheque(String valor) throws Exception {

        List<Cheque> listaCheque = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String[] parts = valor.split("&");

            String sql = "select dg.idoficina,dg.TipMoneda,dg.IdDoc,dg.NroDoc ,mc.NroCheque, Nombres = case when pj.RazonSocial is not null then pj.RazonSocial else pn.PreNombres+' '+pn.primerapellido+' '+pn.SegundoApellido end,dg.FechaDocumento,\n"
                    + "Moneda = case when mc.TipMoneda =1 then 'SOLES' else 'DOLARES' end,\n"
                    + "Monto = case when mc.TipMoneda =1 then t.MontoSoles else t.MontoDolar end,ted.Estado from MoviCheques MC \n"
                    + "join Transaccion t on mc.idoficina = t.idoficina and mc.tipmoneda = t.idTipMoneda and mc.IdDoc =t.IdDoc and mc.NroDoc = t.NroDoc\n"
                    + "join DocGenerado dg on mc.idoficina = dg.idoficina and mc.tipmoneda = dg.TipMoneda and mc.IdDoc =dg.IdDoc and mc.NroDoc = dg.NroDoc\n"
                    + "join persona p on dg.IdPersona = p.IdPersona\n"
                    + "left join PersonaNatural pn on t.IdPersona = pn.IdPersona\n"
                    + "left join PersonaJuridica pj on t.IdPersona = pj.IdPersona\n"
                    + "join TabEstadoDoc ted on dg.IdEstDoc = ted.IdEstDoc and dg.FechaDocumento >= '" + parts[0] + "' and dg.FechaDocumento<= '" + parts[1] + "' and dg.TipMoneda='" + parts[2] + "' order by dg.FechaDocumento";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Cheque objCheque = new Cheque();

                objCheque.getObjDocGenerado().getObjOficina().setIdOficina(rs.getString("idoficina"));
                objCheque.getObjDocGenerado().getObjTipoMoneda().setId(rs.getString("TipMoneda"));
                objCheque.getObjDocGenerado().setIdDoc(rs.getString("IdDoc"));
                objCheque.getObjDocGenerado().setNroDoc(rs.getString("NroDoc"));
                objCheque.setNrocheque(rs.getString("NroCheque"));
                objCheque.getObjPersona().setNombres(rs.getString("Nombres"));
                objCheque.setTipMoneda(rs.getString("Moneda"));
                objCheque.getObjDocGenerado().setFechaDocumento(rs.getString("FechaDocumento"));
                objCheque.setMonto(rs.getDouble("Monto"));
                objCheque.setEstado(rs.getString("Estado"));
                listaCheque.add(objCheque);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaCheque;

    }
}
