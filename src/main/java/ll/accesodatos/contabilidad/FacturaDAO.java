package ll.accesodatos.contabilidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.contabilidad.factura.CabeceraFact;
import ll.entidades.contabilidad.factura.DetalleFact;
import ll.entidades.contabilidad.factura.DetalleGuiaRemision;
import ll.entidades.contabilidad.factura.DetalleMontoFact;

/**
 *
 * @author RenRio
 */
public class FacturaDAO {

    private static FacturaDAO _Instancia;

    private FacturaDAO() {
    }

    public static FacturaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new FacturaDAO();
        }
        return _Instancia;
    }

    public int verificarNotaCredito(String nroFactura) throws Exception {

        CabeceraFact objFactura = new CabeceraFact();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String sql = "select distinct count(*) as cantidad from DocumentoCompraVenta DCV\n"
                    + "join RelaDoc rd on DCV.IdDoc=rd.IdDocR and DCV.NroDoc=rd.NroDocR and DCV.IdOficina = rd.IdOficinaR and dcv.TipMoneda=rd.TipMonedaR\n"
                    + " where NroDocExt='" + nroFactura + "' and rd.IdDoc = '0004'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                objFactura.setVerificarNC(rs.getInt("cantidad"));

            }

        } catch (Exception e) {
            throw e;
        }
        return objFactura.getVerificarNC();
    }

        public int verificarNotaDebito(String nroFactura) throws Exception {

        CabeceraFact objFactura = new CabeceraFact();

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String sql = "select distinct count(*) as cantidad from DocumentoCompraVenta DCV\n"
                    + "join RelaDoc rd on DCV.IdDoc=rd.IdDocR and DCV.NroDoc=rd.NroDocR and DCV.IdOficina = rd.IdOficinaR and dcv.TipMoneda=rd.TipMonedaR\n"
                    + " where NroDocExt='" + nroFactura + "' and rd.IdDoc = '0008'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                objFactura.setVerificarNC(rs.getInt("cantidad"));

            }

        } catch (Exception e) {
            throw e;
        }
        return objFactura.getVerificarNC();
    }

    
    public CabeceraFact obtenerCabFactura(String nroFactura) throws Exception {
        CabeceraFact objFactura = new CabeceraFact();
        List<DetalleFact> listDetalleFact = null;
        List<DetalleMontoFact> listDetalleMontoFact = null;
        List<DetalleGuiaRemision> listDetalleGR = null;

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String sql = "select distinct dg.IdOficina, dg.IdDoc,dg.TipMoneda,dg.NroDoc,dc.NroDocExt, convert(char(10),dg.FechaDocumento,103) as FechaDocumento,Emisor = pjem.RazonSocial,\n"
                    + " Observacion = dg.glosavariable,\n"
                    + " RUCEmisor = pjem.NroRuc,IdPersonaEmisor=pjem.IdPersona, Ejecutor = pjej.RazonSocial, RUCEjecutor = pjej.NroRuc,IdPersonaEjecutor= pjej.IdPersona,  \n"
                    + "VALORV = case when dg.IdEstDoc = '08' then (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end,\n"
                    + "IGV =  case when dg.IdEstDoc = '08' then  (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '1000' and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina ) else 0 end,\n"
                    + "Total=case when dg.IdEstDoc = '08' then (select sum(Total) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end\n"
                    + "from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dc on dg.IdDoc = dc.IdDoc and dc.NroDoc = dg.NroDoc and dg.IdOficina = dc.IdOficina and dg.TipMoneda = dc.TipMoneda\n"
                    + "join PersonaJuridica AS pjej ON dc.IdEjecutor = pjej.IdPersona\n"
                    + "join PersonaJuridica AS pjem ON dc.IdEmisor = pjem.IdPersona \n"
                    + "where dc.NroDocExt = '" + nroFactura + "'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                objFactura.setIdOficina(rs.getString("IdOficina"));
                objFactura.setObservacion(rs.getString("Observacion"));
                objFactura.setIdDoc(rs.getString("IdDoc"));
                objFactura.setNroDoc(rs.getString("NroDoc"));
                objFactura.setTipMoneda(rs.getString("TipMoneda"));
                objFactura.setNroDocExt(rs.getString("NroDocExt"));

                objFactura.setFechaDocumento(rs.getString("FechaDocumento"));
                objFactura.getObjEmisor().setNombres(rs.getString("Emisor"));
                objFactura.getObjEmisor().setNroDocumento(rs.getString("RUCEmisor"));
                objFactura.getObjEmisor().setIdPersona(rs.getString("IdPersonaEmisor"));
                objFactura.getObjEjecutor().setNombres(rs.getString("Ejecutor"));
                objFactura.getObjEjecutor().setNroDocumento(rs.getString("RUCEjecutor"));
                objFactura.getObjEjecutor().setIdPersona(rs.getString("IdPersonaEjecutor"));
                objFactura.setValorV(rs.getDouble("VALORV"));
                objFactura.setIgv(rs.getDouble("IGV"));
                objFactura.setTotal(rs.getDouble("Total"));
            }

            sql = "select distinct gm.NroDocExt, gm.IdTipDocSUNAT, gm.FechaDoctoExt, tSUNAT.Descripcion\n"
                    + "from GuiaRemision gm\n"
                    + "JOIN TipoComprobantePagoSUNAT tSUNAT on tSUNAT.idTipoComprobantePago = gm.IdTipDocSUNAT \n"
                    + "JOIN RelaDoc rd on rd.IdDoc = gm.IdDoc and rd.NroDoc = gm.NroDoc and rd.TipMoneda = gm.TipMoneda and rd.IdOficina = gm.IdOficina\n"
                    + "JOIN DocumentoCompraVenta ddcv on rd.IdOficinaR = ddcv.IdOficina and rd.IdDocR = ddcv.IdDoc  and rd.NroDocR = ddcv.NroDoc  and rd.TipMonedaR = ddcv.TipMoneda\n"
                    + "where ddcv.NroDocExt ='" + nroFactura + "'";

            PreparedStatement pstm3 = cn.prepareStatement(sql);
            ResultSet rs3 = pstm3.executeQuery();
            listDetalleGR = new ArrayList<>();
            while (rs3.next()) {
                DetalleGuiaRemision objDetalleGuiaRemision = new DetalleGuiaRemision();
                objDetalleGuiaRemision.setNroGuia(rs3.getString("NroDocExt"));
                objDetalleGuiaRemision.setDescripcion(rs3.getString("Descripcion"));
                objDetalleGuiaRemision.setTipGuia(rs3.getString("IdTipDocSUNAT"));
                objDetalleGuiaRemision.setFecha(rs3.getString("FechaDoctoExt"));

                listDetalleGR.add(objDetalleGuiaRemision);

            }

            objFactura.setListDetalleGuiaRemision(listDetalleGR);

            sql = "select Linea, IdTipDocSUNAT, Cantidad, idGlosa, gs.Descripcion, dc.um, um.Descripcion as UMdesc, GlosaVariable,PrecioUnitario, Total,  \n"
                    + "dc.ValorUnitario \n"
                    + "from DocumentoCompraVenta  dc\n"
                    + "join GlosaDetalleCompraVenta gs on dc.idGlosa = gs.idGlosaDetCV\n"
                    + "join UnidadesMedida um on um.um = dc.UM where dc.NroDocExt = '" + nroFactura + "'";

            PreparedStatement pstm1 = cn.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            listDetalleFact = new ArrayList<>();
            while (rs1.next()) {
                DetalleFact objDetalleFact = new DetalleFact();

                objDetalleFact.setLinea(rs1.getString("Linea"));
                objDetalleFact.setIdTipDocSUNAT(rs1.getString("IdTipDocSUNAT"));
                objDetalleFact.setValorUnitario(rs1.getString("ValorUnitario"));
                objDetalleFact.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                objDetalleFact.setCantidad(rs1.getString("Cantidad"));
                objDetalleFact.getObjUnidadMedida().setIdUM(rs1.getString("UM"));
                objDetalleFact.getObjUnidadMedida().setDesUM(rs1.getString("UMdesc"));
                objDetalleFact.getObjTipoServicio().setIdTipoServicio(rs1.getString("idGlosa"));
                objDetalleFact.getObjTipoServicio().setDescripcion(rs1.getString("Descripcion"));
                objDetalleFact.setGlosaVariable(rs1.getString("GlosaVariable"));
                objDetalleFact.setTotal(rs1.getString("Total"));

                listDetalleFact.add(objDetalleFact);
                objFactura.setListDetalleFact(listDetalleFact);

                sql = "select ddc.IdDetGastoSunat , ddc.Monto from DetalleDocumentoCompraVta ddc \n"
                        + "join DocumentoCompraVenta dc on ddc.iddoc = dc.IdDoc and ddc.NroDoc = dc.NroDoc and ddc.IdOficina = dc.IdOficina and ddc.TipMoneda = dc.TipMoneda and ddc.SubLinea = dc.Linea\n"
                        + " where dc.NroDocExt= '" + nroFactura + "' and SubLinea = '" + objDetalleFact.getLinea() + "'";

                PreparedStatement pstm2 = cn.prepareStatement(sql);
                ResultSet rs2 = pstm2.executeQuery();
                listDetalleMontoFact = new ArrayList<>();
                while (rs2.next()) {

                    DetalleMontoFact objDetalleMontoFact = new DetalleMontoFact();

                    objDetalleMontoFact.setIdDetGastoSunat(rs2.getString("IdDetGastoSunat"));
                    objDetalleMontoFact.setMonto(rs2.getString("Monto"));

                    listDetalleMontoFact.add(objDetalleMontoFact);

                }

                objFactura.getListDetalleFact().get(Integer.parseInt(objDetalleFact.getLinea())).setListDetalleMontoFact(listDetalleMontoFact);

            }

        } catch (Exception e) {
            throw e;
        }
        return objFactura;
    }

    public CabeceraFact obtenerCabFacturaAnulacion(String nroFactura, String idDoc) throws Exception {
        CabeceraFact objFactura = new CabeceraFact();
        List<DetalleFact> listDetalleFact = null;
        List<DetalleMontoFact> listDetalleMontoFact = null;
        List<DetalleGuiaRemision> listDetalleGR = null;

        try (Connection cn = Connect.Instancia().getConnectBD()) {
            String sql = "select distinct dg.IdOficina, dg.IdDoc,dg.TipMoneda,dg.NroDoc,dc.NroDocExt, convert(char(10),dg.FechaDocumento,103) as FechaDocumento,Emisor = pjem.RazonSocial,\n"
                    + " Observacion = dg.glosavariable,\n"
                    + " RUCEmisor = pjem.NroRuc,IdPersonaEmisor=pjem.IdPersona, Ejecutor = pjej.RazonSocial, RUCEjecutor = pjej.NroRuc,IdPersonaEjecutor= pjej.IdPersona,  \n"
                    + "VALORV = case when dg.IdEstDoc = '08' then (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end,\n"
                    + "IGV =  case when dg.IdEstDoc = '08' then  (select sum(monto) from DetalleDocumentoCompraVta where IdDetGastoSunat = '1000' and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina ) else 0 end,\n"
                    + "Total=case when dg.IdEstDoc = '08' then (select sum(Total) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina) else 0 end\n"
                    + "from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dc on dg.IdDoc = dc.IdDoc and dc.NroDoc = dg.NroDoc and dg.IdOficina = dc.IdOficina and dg.TipMoneda = dc.TipMoneda\n"
                    + "join PersonaJuridica AS pjej ON dc.IdEjecutor = pjej.IdPersona\n"
                    + "join PersonaJuridica AS pjem ON dc.IdEmisor = pjem.IdPersona \n"
                    + "where  dg.IdDoc='"+idDoc+"' and  dc.NroDocExt = '"+nroFactura + "'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                objFactura.setIdOficina(rs.getString("IdOficina"));
                objFactura.setObservacion(rs.getString("Observacion"));
                objFactura.setIdDoc(rs.getString("IdDoc"));
                objFactura.setNroDoc(rs.getString("NroDoc"));
                objFactura.setTipMoneda(rs.getString("TipMoneda"));
                objFactura.setNroDocExt(rs.getString("NroDocExt"));

                objFactura.setFechaDocumento(rs.getString("FechaDocumento"));
                objFactura.getObjEmisor().setNombres(rs.getString("Emisor"));
                objFactura.getObjEmisor().setNroDocumento(rs.getString("RUCEmisor"));
                objFactura.getObjEmisor().setIdPersona(rs.getString("IdPersonaEmisor"));
                objFactura.getObjEjecutor().setNombres(rs.getString("Ejecutor"));
                objFactura.getObjEjecutor().setNroDocumento(rs.getString("RUCEjecutor"));
                objFactura.getObjEjecutor().setIdPersona(rs.getString("IdPersonaEjecutor"));
                objFactura.setValorV(rs.getDouble("VALORV"));
                objFactura.setIgv(rs.getDouble("IGV"));
                objFactura.setTotal(rs.getDouble("Total"));
            }

            sql = "select distinct gm.NroDocExt, gm.IdTipDocSUNAT, gm.FechaDoctoExt, tSUNAT.Descripcion\n"
                    + "from GuiaRemision gm\n"
                    + "JOIN TipoComprobantePagoSUNAT tSUNAT on tSUNAT.idTipoComprobantePago = gm.IdTipDocSUNAT \n"
                    + "JOIN RelaDoc rd on rd.IdDoc = gm.IdDoc and rd.NroDoc = gm.NroDoc and rd.TipMoneda = gm.TipMoneda and rd.IdOficina = gm.IdOficina\n"
                    + "JOIN DocumentoCompraVenta ddcv on rd.IdOficinaR = ddcv.IdOficina and rd.IdDocR = ddcv.IdDoc  and rd.NroDocR = ddcv.NroDoc  and rd.TipMonedaR = ddcv.TipMoneda\n"
                    + "where ddcv.NroDocExt ='" + nroFactura + "'";

            PreparedStatement pstm3 = cn.prepareStatement(sql);
            ResultSet rs3 = pstm3.executeQuery();
            listDetalleGR = new ArrayList<>();
            while (rs3.next()) {
                DetalleGuiaRemision objDetalleGuiaRemision = new DetalleGuiaRemision();
                objDetalleGuiaRemision.setNroGuia(rs3.getString("NroDocExt"));
                objDetalleGuiaRemision.setDescripcion(rs3.getString("Descripcion"));
                objDetalleGuiaRemision.setTipGuia(rs3.getString("IdTipDocSUNAT"));
                objDetalleGuiaRemision.setFecha(rs3.getString("FechaDoctoExt"));

                listDetalleGR.add(objDetalleGuiaRemision);

            }

            objFactura.setListDetalleGuiaRemision(listDetalleGR);

            sql = "select Linea, IdTipDocSUNAT, Cantidad, idGlosa, gs.Descripcion, dc.um, um.Descripcion as UMdesc, GlosaVariable,PrecioUnitario, Total,  \n"
                    + "dc.ValorUnitario \n"
                    + "from DocumentoCompraVenta  dc\n"
                    + "join GlosaDetalleCompraVenta gs on dc.idGlosa = gs.idGlosaDetCV\n"
                    + "join UnidadesMedida um on um.um = dc.UM where dc.NroDocExt = '" + nroFactura + "'";

            PreparedStatement pstm1 = cn.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            listDetalleFact = new ArrayList<>();
            while (rs1.next()) {
                DetalleFact objDetalleFact = new DetalleFact();

                objDetalleFact.setLinea(rs1.getString("Linea"));
                objDetalleFact.setIdTipDocSUNAT(rs1.getString("IdTipDocSUNAT"));
                objDetalleFact.setValorUnitario(rs1.getString("ValorUnitario"));
                objDetalleFact.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                objDetalleFact.setCantidad(rs1.getString("Cantidad"));
                objDetalleFact.getObjUnidadMedida().setIdUM(rs1.getString("UM"));
                objDetalleFact.getObjUnidadMedida().setDesUM(rs1.getString("UMdesc"));
                objDetalleFact.getObjTipoServicio().setIdTipoServicio(rs1.getString("idGlosa"));
                objDetalleFact.getObjTipoServicio().setDescripcion(rs1.getString("Descripcion"));
                objDetalleFact.setGlosaVariable(rs1.getString("GlosaVariable"));
                objDetalleFact.setTotal(rs1.getString("Total"));

                listDetalleFact.add(objDetalleFact);
                objFactura.setListDetalleFact(listDetalleFact);

                sql = "select ddc.IdDetGastoSunat , ddc.Monto from DetalleDocumentoCompraVta ddc \n"
                        + "join DocumentoCompraVenta dc on ddc.iddoc = dc.IdDoc and ddc.NroDoc = dc.NroDoc and ddc.IdOficina = dc.IdOficina and ddc.TipMoneda = dc.TipMoneda and ddc.SubLinea = dc.Linea\n"
                        + " where dc.NroDocExt= '" + nroFactura + "' and SubLinea = '" + objDetalleFact.getLinea() + "'";

                PreparedStatement pstm2 = cn.prepareStatement(sql);
                ResultSet rs2 = pstm2.executeQuery();
                listDetalleMontoFact = new ArrayList<>();
                while (rs2.next()) {

                    DetalleMontoFact objDetalleMontoFact = new DetalleMontoFact();

                    objDetalleMontoFact.setIdDetGastoSunat(rs2.getString("IdDetGastoSunat"));
                    objDetalleMontoFact.setMonto(rs2.getString("Monto"));

                    listDetalleMontoFact.add(objDetalleMontoFact);

                }

                objFactura.getListDetalleFact().get(Integer.parseInt(objDetalleFact.getLinea())).setListDetalleMontoFact(listDetalleMontoFact);

            }

        } catch (Exception e) {
            throw e;
        }
        return objFactura;
    }
}
