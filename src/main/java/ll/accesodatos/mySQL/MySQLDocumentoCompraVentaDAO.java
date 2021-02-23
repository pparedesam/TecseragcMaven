/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.xmlFacturacion.DetalleItemxml;
import ll.entidades.xmlFacturacion.Facturaxml;
import ll.entidades.xmlFacturacion.Leyendaxml;
import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.DetalleDocBajaxml;
import ll.entidades.xmlFacturacion.DocumentoBajaxml;
import ll.entidades.xmlFacturacion.GuiaRemisionxml;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.entidades.xmlFacturacion.NotaDebitoxml;

public class MySQLDocumentoCompraVentaDAO {

    private static MySQLDocumentoCompraVentaDAO _Instancia;

    private MySQLDocumentoCompraVentaDAO() {
    }

    public static MySQLDocumentoCompraVentaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new MySQLDocumentoCompraVentaDAO();
        }
        return _Instancia;
    }

    public Facturaxml obtenerFacturaXML(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Facturaxml objFacturaXML = new Facturaxml();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "NombreComercial = pjem.RazonSocial,\n"
                    + "ID= RIGHT(ub.IdUbigeo,6),\n"
                    + "Direccion = pemi.Direccion,\n"
                    + "Urbanizacion = '',\n"
                    + "Provincia = (select descripcion from UbiGeo where left(IdUbigeo,6) = left(ub.IdUbigeo,6) and Nivel = '2'),\n"
                    + "Departamento = (select Descripcion from UbiGeo where left(IdUbigeo,4) = left(ub.IdUbigeo,4) and Nivel = '1'),\n"
                    + "Distrito = (select Descripcion from UbiGeo where IdUbigeo = ub.IdUbigeo and Nivel = '3'),\n"
                    + "CodPais = (select left(Descripcion,2) from UbiGeo where left(IdUbigeo,2) = left(ub.IdUbigeo,2) and Nivel = '0'),\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "NumeroRucEjecutor = pjej.NroRuc,\n"
                    + "TipoDocIdentidadEjecutor = docIdenEje.idTipDocIdentidad,\n"
                    + "RazonSocialEjecutor = pjej.RazonSocial,\n"
                    + "CodOperacionGravada = '1001',\n"
                    + "MontoOperacionGravada = (select convert(varchar(15),sum(monto)) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "CodOpeacionInafecta = '1002',\n"
                    + "MontoOperacionInafecta = '0',\n"
                    + "CodOpeacionExonerada = '1003',\n"
                    + "MontoOperacionExonerada = '0',\n"
                    + "ImporteTotal = (select convert(varchar(15),sum(Total)) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "Moneda = mon.CurrencyCode,\n"
                    + "IGV = 18,\n"
                    + "IGVTotal = (select convert(varchar(15),sum(Monto)) from DetalleDocumentoCompraVta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina and IdDetGastoSunat = '1000'),\n"
                    + "Leyenda1 = '1000',\n"
                    + "Observacion = dg.GlosaVariable,\n"
                    + "LeyendaDes1 = (SELECT dbo.fnNumeroExpresadoEnLetra2(sum(total),dg.tipmoneda) from DocumentoCompraVenta  WHERE (nroDoc = dg.nroDoc) AND (IdDoc = dg.IdDoc) AND (IdOficina = dg.idOficina) AND (TipMoneda = dg.tipMoneda)),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dcv on  dg.IdOficina=dcv.IdOficina and dg.IdDoc=dcv.IdDoc and dg.NroDoc= dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago = dcv.IdTipDocSUNAT\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dcv.IdEmisor\n"
                    + "join PersonaJuridica pjej on pjej.IdPersona  = dcv.IdEjecutor\n"
                    + "join Persona pemi on pemi.IdPersona = pjem.IdPersona\n"
                    + "join UbiGeo ub on ub.IdUbigeo = pemi.IdUbigeoDir\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "join TabDocIdentidad docIdenEje on docIdenEje.idTipDocIdentidad = pjej.IdTipoDocIdentidad\n"
                    + "join TipoMoneda mon on mon.IdTipMoneda = dg.TipMoneda\n"
                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objFacturaXML.setIdDoc(objDocumentoGenerado.getIdDoc());
                objFacturaXML.setNroDoc(objDocumentoGenerado.getNroDoc());
                objFacturaXML.setTipM(objDocumentoGenerado.getObjTipoMoneda().getId());
                objFacturaXML.setIdOfi(objDocumentoGenerado.getObjOficina().getIdOficina());

                objFacturaXML.setObservacion(rs.getString("Observacion"));
                objFacturaXML.setFechaEmision(rs.getString("FechaEmision"));
                objFacturaXML.getObjPersonaxmlEmisor().setNroRuc(rs.getString("Numeracion"));
                objFacturaXML.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objFacturaXML.setNombreComercial(rs.getString("NombreComercial"));
                objFacturaXML.getObjDomicilioFiscalxml().setId(rs.getString("ID"));
                objFacturaXML.getObjDomicilioFiscalxml().setDireccion(rs.getString("Direccion"));
                objFacturaXML.getObjDomicilioFiscalxml().setUrbanizacion(rs.getString("Urbanizacion"));
                objFacturaXML.getObjDomicilioFiscalxml().setProvincia(rs.getString("Provincia"));
                objFacturaXML.getObjDomicilioFiscalxml().setDepartamento(rs.getString("Departamento"));
                objFacturaXML.getObjDomicilioFiscalxml().setDistrito(rs.getString("Distrito"));
                objFacturaXML.getObjDomicilioFiscalxml().setCodPais(rs.getString("CodPais"));
                objFacturaXML.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objFacturaXML.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objFacturaXML.setTipoDocumento(rs.getString("TipoDocumentoSunat"));
                objFacturaXML.setNumeracionFactura(rs.getString("Numeracion"));
                objFacturaXML.setIgvTotal(rs.getString("IGVTotal"));
                objFacturaXML.getObjPersonaxmlEjecutor().setNroRuc(rs.getString("NumeroRucEjecutor"));
                objFacturaXML.getObjPersonaxmlEjecutor().setTipoDoc(rs.getString("TipoDocIdentidadEjecutor"));
                objFacturaXML.getObjPersonaxmlEjecutor().setRazonSocial(rs.getString("RazonSocialEjecutor"));
                objFacturaXML.getObjOperacionGravada().setCodOperacion(rs.getString("CodOperacionGravada"));
                objFacturaXML.getObjOperacionGravada().setMonto(rs.getString("MontoOperacionGravada"));
                objFacturaXML.getObjOperacionInafecta().setCodOperacion(rs.getString("CodOpeacionInafecta"));
                objFacturaXML.getObjOperacionInafecta().setMonto(rs.getString("MontoOperacionInafecta"));
                objFacturaXML.getObjOperacionExonerada().setCodOperacion(rs.getString("CodOpeacionExonerada"));
                objFacturaXML.getObjOperacionExonerada().setMonto(rs.getString("MontoOperacionExonerada"));
                objFacturaXML.setImporteTotal(rs.getString("ImporteTotal"));
                objFacturaXML.setTipMoneda(rs.getString("Moneda"));
                objFacturaXML.setPorcentajeImpuesto(rs.getString("IGV"));
                objFacturaXML.getObjLeyendaxml().setCodigo(rs.getString("Leyenda1"));
                objFacturaXML.getObjLeyendaxml().setDescripcion(rs.getString("LeyendaDes1"));

            }

            sql = "select distinct \n"
                    + "dcv.UM as UnidadMedida,\n"
                    + "dcv.Cantidad,\n"
                    + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
                    + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                    + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                    + "CodValorVentaUnit='01',\n"
                    + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
                    + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
                    + "TipoAfectacion = '10',\n"
                    + "CodTributo = ddcv.IdDetGastoSunat,\n"
                    + "NomTributo = ele.descripcion,\n"
                    + "CIT = ele.tipoImpuesto,\n"
                    + "ValorVentaItem = ValorUnitario,\n"
                    + "NumOrden = dcv.Linea+1\n"
                    + "FROM DocumentoCompraVenta AS dcv\n"
                    + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                    + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
                    + "JOIN ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'"
                    + "where dcv.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dcv.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dcv.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dcv.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' order by NumOrden";

            PreparedStatement pstm1 = objConexion.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                objDetalleItemxml.setUnidadMedida(rs1.getString("UnidadMedida"));
                objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                objDetalleItemxml.setDescripcion(rs1.getString("GlosaVariable"));
                objDetalleItemxml.setValUnitario(rs1.getString("ValorUnitario"));
                objDetalleItemxml.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                objDetalleItemxml.getObjprecUnitario().setCod(rs1.getString("CodValorVentaUnit"));
                objDetalleItemxml.getObjprecUnitario().setPrecioUnit(rs1.getString("PrecioVenta"));
                objDetalleItemxml.setIgv(rs1.getString("AfectacionIGV"));
                objDetalleItemxml.setTipoAfectacion(rs1.getString("TipoAfectacion"));
                objDetalleItemxml.setCodTributo(rs1.getString("CodTributo"));
                objDetalleItemxml.setNomTributo(rs1.getString("NomTributo"));
                objDetalleItemxml.setValorVta(rs1.getString("ValorVentaItem"));
                objDetalleItemxml.setCit(rs1.getString("CIT"));
                objDetalleItemxml.setNroOrden(rs1.getString("NumOrden"));

                objFacturaXML.getListDetalleItemxml().add(objDetalleItemxml);
            }

            sql = "select distinct gm.NroDocExt as NroDocExt, gm.IdTipDocSUNAT as IdTipDocSUNAT, sunat.descripcion, dcv.IdTipDocSUNAT as TipDoc\n"
                    + "from GuiaRemision gm\n"
                    + "JOIN TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago= gm.IdTipDocSUNAT \n"
                    + "JOIN RelaDoc rd on rd.IdDoc = gm.IdDoc and rd.NroDoc = gm.NroDoc and rd.TipMoneda = gm.TipMoneda and rd.IdOficina = gm.IdOficina\n"
                    + "JOIN DocumentoCompraVenta dcv on rd.IdDocR = dcv.IdDoc and rd.NroDocR = dcv.NroDoc and rd.TipMonedaR = dcv.TipMoneda and rd.IdOficinaR = dcv.IdOficina\n"
                    + "where rd.IdOficinaR = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and rd.IdDocR = '" + objDocumentoGenerado.getIdDoc()
                    + "' and rd.NroDocR = '" + objDocumentoGenerado.getNroDoc() + "' and rd.TipMonedaR = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm2 = objConexion.prepareStatement(sql);
            ResultSet rs2 = pstm2.executeQuery();
            while (rs2.next()) {

                GuiaRemisionxml objGuiaRemisionxml = new GuiaRemisionxml();

                objGuiaRemisionxml.setNroGuia(rs2.getString("NroDocExt"));
                objGuiaRemisionxml.setTipoGuia(rs2.getString("IdTipDocSUNAT"));
                objGuiaRemisionxml.setDescGuia(rs2.getString("descripcion"));
                objGuiaRemisionxml.setTipoDoc(rs2.getString("TipDoc"));

                objFacturaXML.getListGuiaRemisionxml().add(objGuiaRemisionxml);
            }

        } catch (SQLException e) {

            throw e;
        }

        return objFacturaXML;

    }

    public GuiaRemisionxml obtenerGuiaRemisionXML(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        GuiaRemisionxml objGuiaRemisionxml = new GuiaRemisionxml();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select gm.NroDocExt,gm.FechaProceso, gm.HoraProceso,gm.idTipDocSUNAT,\n"
                    + "RazonSocialRem = pjremitente.RazonSocial, \n"
                    + "tipoDocRemitente = docremitente.idTipDocIdentidad, \n"
                    + "nroDocRemitente = pjremitente.NroRuc,\n"
                    + "RazonSocialDes = pjrdestinatario.RazonSocial, \n"
                    + "tipoDocDestinatario = docdestinatario.idTipDocIdentidad,\n"
                    + "nroDocDestinatario = pjrdestinatario.NroRuc,\n"
                    + "motivo = mottras.Codigo,\n"
                    + "movilidad = movtra.Codigo,\n"
                    + "FechaIniTraslado,\n"
                    + "transportista = case when pjtrans.RazonSocial is null then pntrans.PrimerApellido + ' ' + pntrans.SegundoApellido + ' ' +  pntrans.PreNombres else pjtrans.RazonSocial end,\n"
                    + "tipoDocTrans = case when pntrans.IdTipoDocIdentidad is null then pjtrans.IdTipoDocIdentidad else pntrans.IdTipoDocIdentidad end,\n"
                    + "nrodocTrans = case when pjtrans.NroRuc is null then pntrans.NroDocIdentidad else pjtrans.NroRuc end,\n"
                    + "nrodocconductor = case when pnconductor.NroDocIdentidad is null THEN '' ELSE pnconductor.NroDocIdentidad END ,\n"
                    + "tipdocConductor = case when pnconductor.IdTipoDocIdentidad is null THEN '' ELSE pnconductor.NroDocIdentidad END,\n"
                    + "IdUbigeoLlegada,\n"
                    + "DireccionLlegada,\n"
                    + "IdUbigeoPartida,\n"
                    + "DireccionPartida,\n"
                    + "PesoBruto,\n"
                    + "Observaciones = dg.GlosaVariable,\n"
                    + "gm.IdUM,\n"
                    + "FechaEntrega,gm.NroPlacaTransportista\n"
                    + "from GuiaRemision gm\n"
                    + "join DocGenerado dg on dg.NroDoc = gm.NroDoc and dg.IdDoc = gm.IdDoc and dg.IdOficina = gm.IdOficina and dg.IdOficina = gm.IdOficina\n"
                    + "join PersonaJuridica pjremitente on pjremitente.IdPersona = gm.IdPersonaRemitente \n"
                    + "join TabDocIdentidad docremitente on docremitente.idTipDocIdentidad = pjremitente.IdTipoDocIdentidad\n"
                    + "join PersonaJuridica pjrdestinatario on pjrdestinatario.IdPersona = gm.IdPersonaDestinatario\n"
                    + "join TabDocIdentidad docdestinatario on docdestinatario.idTipDocIdentidad = pjrdestinatario.IdTipoDocIdentidad\n"
                    + "join MotivosTraslado mottras on mottras.Codigo = gm.IdMotivoTransporte\n"
                    + "join MovilidadTraslado movtra on movtra.Codigo = gm.IdModalidadTraslado \n"
                    + "left join Persona ptrans on ptrans.IdPersona = gm.IdPersonaTransportista\n"
                    + "left join PersonaJuridica pjtrans on pjtrans.IdPersona = ptrans.IdPersona \n"
                    + "left join PersonaNatural pntrans on pntrans.IdPersona = ptrans.IdPersona \n"
                    + "left join PersonaNatural pnconductor on pnconductor.IdPersona = gm.IdPersonaConductor "
                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objGuiaRemisionxml.setIdDoc(objDocumentoGenerado.getIdDoc());
                objGuiaRemisionxml.setNroDoc(objDocumentoGenerado.getNroDoc());
                objGuiaRemisionxml.setTipM(objDocumentoGenerado.getObjTipoMoneda().getId());
                objGuiaRemisionxml.setIdOfi(objDocumentoGenerado.getObjOficina().getIdOficina());
                objGuiaRemisionxml.setNroGuia(rs.getString("NroDocExt"));
                objGuiaRemisionxml.setFechaGuia(rs.getString("FechaProceso"));
                objGuiaRemisionxml.setHora(rs.getString("HoraProceso"));
                objGuiaRemisionxml.setTipoGuia(rs.getString("idTipDocSUNAT"));
                objGuiaRemisionxml.getObjPersonaRemitente().setNroRuc(rs.getString("nroDocRemitente"));
                objGuiaRemisionxml.getObjPersonaRemitente().setRazonSocial(rs.getString("RazonSocialRem"));
                objGuiaRemisionxml.getObjPersonaRemitente().setTipoDoc(rs.getString("tipoDocRemitente"));
                objGuiaRemisionxml.getObjPersonaDestinatario().setNroRuc(rs.getString("nroDocDestinatario"));
                objGuiaRemisionxml.getObjPersonaDestinatario().setRazonSocial(rs.getString("RazonSocialDes"));
                objGuiaRemisionxml.getObjPersonaDestinatario().setTipoDoc(rs.getString("tipoDocDestinatario"));
                objGuiaRemisionxml.getObjMotivoTransporte().setCodigo(rs.getString("motivo"));
                objGuiaRemisionxml.getObjModalidadTraslado().setCodigo(rs.getString("movilidad"));
                objGuiaRemisionxml.setPesoBruto(rs.getDouble("PesoBruto"));
                objGuiaRemisionxml.setObservaciones(rs.getString("Observaciones"));
                objGuiaRemisionxml.setUnidadMedida(rs.getString("IdUM"));
                objGuiaRemisionxml.setFechaIniTraslado(rs.getString("FechaIniTraslado"));
                objGuiaRemisionxml.setFechaEntrega(rs.getString("FechaEntrega"));
                objGuiaRemisionxml.getObjPersonaTransportista().setNroRuc(rs.getString("nrodocTrans"));
                objGuiaRemisionxml.getObjPersonaTransportista().setRazonSocial(rs.getString("transportista"));
                objGuiaRemisionxml.getObjPersonaTransportista().setTipoDoc(rs.getString("tipoDocTrans"));
                objGuiaRemisionxml.setNroPlacaTransportista(rs.getString("NroPlacaTransportista"));
                objGuiaRemisionxml.getObjPersonaConductor().setNroRuc(rs.getString("nrodocconductor"));
                objGuiaRemisionxml.getObjPersonaConductor().setTipoDoc(rs.getString("tipdocConductor"));
                objGuiaRemisionxml.getObjUbigeoLlegada().setCodigo(rs.getString("IdUbigeoLlegada"));
                objGuiaRemisionxml.getObjUbigeoLlegada().setDireccion(rs.getString("DireccionLlegada"));
                objGuiaRemisionxml.getObjUbigeoSalida().setCodigo(rs.getString("IdUbigeoPartida"));
                objGuiaRemisionxml.getObjUbigeoSalida().setDireccion(rs.getString("DireccionPartida"));

            }

            sql = "select orden = NLin+1,Cantidad,IdUM,Descripcion from DetalleGuiaRemision \n"
                    + "where IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm1 = objConexion.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                objDetalleItemxml.setUnidadMedida(rs1.getString("IdUM"));
                objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                objDetalleItemxml.setDescripcion(rs1.getString("Descripcion"));
                objDetalleItemxml.setNroOrden(rs1.getString("orden"));

                objGuiaRemisionxml.getListDetalleItemxml().add(objDetalleItemxml);
            }

        } catch (SQLException e) {

            throw e;
        }

        return objGuiaRemisionxml;

    }

    public List<Facturaxml> obtenerFacturasXML() throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        List<Facturaxml> listFacturaxml = new ArrayList<Facturaxml>();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "NombreComercial = pjem.RazonSocial,\n"
                    + "ID= RIGHT(ub.IdUbigeo,6),\n"
                    + "Direccion = pemi.Direccion,\n"
                    + "Urbanizacion = '',\n"
                    + "Provincia = (select descripcion from UbiGeo where left(IdUbigeo,6) = left(ub.IdUbigeo,6) and Nivel = '2'),\n"
                    + "Departamento = (select Descripcion from UbiGeo where left(IdUbigeo,4) = left(ub.IdUbigeo,4) and Nivel = '1'),\n"
                    + "Distrito = (select Descripcion from UbiGeo where IdUbigeo = ub.IdUbigeo and Nivel = '3'),\n"
                    + "CodPais = (select left(Descripcion,2) from UbiGeo where left(IdUbigeo,2) = left(ub.IdUbigeo,2) and Nivel = '0'),\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "NumeroRucEjecutor = pjej.NroRuc,\n"
                    + "TipoDocIdentidadEjecutor = docIdenEje.idTipDocIdentidad,\n"
                    + "RazonSocialEjecutor = pjej.RazonSocial,\n"
                    + "CodOperacionGravada = '1001',\n"
                    + "MontoOperacionGravada = (select convert(varchar(15),sum(monto)) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "CodOpeacionInafecta = '1002',\n"
                    + "MontoOperacionInafecta = '0',\n"
                    + "CodOpeacionExonerada = '1003',\n"
                    + "MontoOperacionExonerada = '0',\n"
                    + "ImporteTotal = (select convert(varchar(15),sum(Total)) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "Moneda = mon.CurrencyCode,\n"
                    + "IGV = 18,\n"
                    + "IGVTotal = (select convert(varchar(15),sum(Monto)) from DetalleDocumentoCompraVta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina and IdDetGastoSunat = '1000'),\n"
                    + "Leyenda1 = '1000',\n"
                    + "Observacion = dg.GlosaVariable,\n"
                    + "LeyendaDes1 = (SELECT dbo.fnNumeroExpresadoEnLetra2(sum(total),dg.tipmoneda) from DocumentoCompraVenta  WHERE (nroDoc = dg.nroDoc) AND (IdDoc = dg.IdDoc) AND (IdOficina = dg.idOficina) AND (TipMoneda = dg.tipMoneda)),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dcv on  dg.IdOficina=dcv.IdOficina and dg.IdDoc=dcv.IdDoc and dg.NroDoc= dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago = dcv.IdTipDocSUNAT\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dcv.IdEmisor\n"
                    + "join PersonaJuridica pjej on pjej.IdPersona  = dcv.IdEjecutor\n"
                    + "join Persona pemi on pemi.IdPersona = pjem.IdPersona\n"
                    + "join UbiGeo ub on ub.IdUbigeo = pemi.IdUbigeoDir\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "join TabDocIdentidad docIdenEje on docIdenEje.idTipDocIdentidad = pjej.IdTipoDocIdentidad\n"
                    + "join TipoMoneda mon on mon.IdTipMoneda = dg.TipMoneda\n";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Facturaxml objFacturaXML = new Facturaxml();

                objFacturaXML.setIdDoc(rs.getString("IdDoc"));
                objFacturaXML.setNroDoc(rs.getString("NroDoc"));
                objFacturaXML.setTipM(rs.getString("TipMoneda"));
                objFacturaXML.setIdOfi(rs.getString("IdOficina"));
                objFacturaXML.setObservacion(rs.getString("Observacion"));
                objFacturaXML.setFechaEmision(rs.getString("FechaEmision"));
                objFacturaXML.getObjPersonaxmlEmisor().setNroRuc(rs.getString("Numeracion"));
                objFacturaXML.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objFacturaXML.setNombreComercial(rs.getString("NombreComercial"));
                objFacturaXML.getObjDomicilioFiscalxml().setId(rs.getString("ID"));
                objFacturaXML.getObjDomicilioFiscalxml().setDireccion(rs.getString("Direccion"));
                objFacturaXML.getObjDomicilioFiscalxml().setUrbanizacion(rs.getString("Urbanizacion"));
                objFacturaXML.getObjDomicilioFiscalxml().setProvincia(rs.getString("Provincia"));
                objFacturaXML.getObjDomicilioFiscalxml().setDepartamento(rs.getString("Departamento"));
                objFacturaXML.getObjDomicilioFiscalxml().setDistrito(rs.getString("Distrito"));
                objFacturaXML.getObjDomicilioFiscalxml().setCodPais(rs.getString("CodPais"));
                objFacturaXML.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objFacturaXML.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objFacturaXML.setTipoDocumento(rs.getString("TipoDocumentoSunat"));
                objFacturaXML.setNumeracionFactura(rs.getString("Numeracion"));
                objFacturaXML.setIgvTotal(rs.getString("IGVTotal"));
                objFacturaXML.getObjPersonaxmlEjecutor().setNroRuc(rs.getString("NumeroRucEjecutor"));
                objFacturaXML.getObjPersonaxmlEjecutor().setTipoDoc(rs.getString("TipoDocIdentidadEjecutor"));
                objFacturaXML.getObjPersonaxmlEjecutor().setRazonSocial(rs.getString("RazonSocialEjecutor"));
                objFacturaXML.getObjOperacionGravada().setCodOperacion(rs.getString("CodOperacionGravada"));
                objFacturaXML.getObjOperacionGravada().setMonto(rs.getString("MontoOperacionGravada"));
                objFacturaXML.getObjOperacionInafecta().setCodOperacion(rs.getString("CodOpeacionInafecta"));
                objFacturaXML.getObjOperacionInafecta().setMonto(rs.getString("MontoOperacionInafecta"));
                objFacturaXML.getObjOperacionExonerada().setCodOperacion(rs.getString("CodOpeacionExonerada"));
                objFacturaXML.getObjOperacionExonerada().setMonto(rs.getString("MontoOperacionExonerada"));
                objFacturaXML.setImporteTotal(rs.getString("ImporteTotal"));
                objFacturaXML.setTipMoneda(rs.getString("Moneda"));
                objFacturaXML.setPorcentajeImpuesto(rs.getString("IGV"));
                objFacturaXML.getObjLeyendaxml().setCodigo(rs.getString("Leyenda1"));
                objFacturaXML.getObjLeyendaxml().setDescripcion(rs.getString("LeyendaDes1"));

                listFacturaxml.add(objFacturaXML);
            }

            for (int i = 0; i < listFacturaxml.size(); i++) {
                sql = "select distinct \n"
                        + "dcv.UM as UnidadMedida,\n"
                        + "dcv.Cantidad,\n"
                        + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
                        + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                        + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) , \n"
                        + "CodValorVentaUnit='01',\n"
                        + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
                        + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
                        + "TipoAfectacion = '10',\n"
                        + "CodTributo = ddcv.IdDetGastoSunat,\n"
                        + "NomTributo = ele.descripcion,\n"
                        + "CIT = ele.tipoImpuesto,\n"
                        + "ValorVentaItem = ValorUnitario,\n"
                        + "NumOrden = dcv.Linea+1\n"
                        + "FROM DocumentoCompraVenta AS dcv\n"
                        + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                        + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
                        + "join ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'"
                        + "where dcv.IdDoc = '" + listFacturaxml.get(i).getIdDoc() + "' and \n"
                        + "dcv.NroDoc = '" + listFacturaxml.get(i).getNroDoc() + "' and \n"
                        + "dcv.TipMoneda = '" + listFacturaxml.get(i).getTipMoneda() + "' and \n"
                        + "dcv.IdOficina = '" + listFacturaxml.get(i).getIdOfi() + "'";
                PreparedStatement pstm1 = objConexion.prepareStatement(sql);
                ResultSet rs1 = pstm1.executeQuery();
                while (rs1.next()) {

                    DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                    objDetalleItemxml.setUnidadMedida(rs1.getString("UnidadMedida"));
                    objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                    objDetalleItemxml.setDescripcion(rs1.getString("GlosaVariable"));
                    objDetalleItemxml.setValUnitario(rs1.getString("ValorUnitario"));
                    objDetalleItemxml.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                    objDetalleItemxml.getObjprecUnitario().setCod(rs1.getString("CodValorVentaUnit"));
                    objDetalleItemxml.getObjprecUnitario().setPrecioUnit(rs1.getString("PrecioVenta"));
                    objDetalleItemxml.setIgv(rs1.getString("AfectacionIGV"));
                    objDetalleItemxml.setTipoAfectacion(rs1.getString("TipoAfectacion"));
                    objDetalleItemxml.setCodTributo(rs1.getString("CodTributo"));
                    objDetalleItemxml.setNomTributo(rs1.getString("NomTributo"));
                    objDetalleItemxml.setValorVta(rs1.getString("ValorVentaItem"));
                    objDetalleItemxml.setCit(rs1.getString("CIT"));
                    objDetalleItemxml.setNroOrden(rs1.getString("NumOrden"));

                    listFacturaxml.get(i).getListDetalleItemxml().add(objDetalleItemxml);
                }

                sql = "select distinct gm.NroDocExt as NroDocExt, gm.IdTipDocSUNAT as IdTipDocSUNAT, sunat.descripcion, dcv.IdTipDocSUNAT as TipDoc\n"
                        + "from GuiaRemision gm\n"
                        + "JOIN TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago= gm.IdTipDocSUNAT \n"
                        + "JOIN RelaDoc rd on rd.IdDoc = gm.IdDoc and rd.NroDoc = gm.NroDoc and rd.TipMoneda = gm.TipMoneda and rd.IdOficina = gm.IdOficina\n"
                        + "JOIN DocumentoCompraVenta dcv on rd.IdDocR = dcv.IdDoc and rd.NroDocR = dcv.NroDoc and rd.TipMonedaR = dcv.TipMoneda and rd.IdOficinaR = dcv.IdOficina\n"
                        + "where rd.IdOficinaR = '" + listFacturaxml.get(i).getIdOfi()
                        + "' and rd.IdDocR = '" + listFacturaxml.get(i).getIdDoc()
                        + "' and rd.NroDocR = '" + listFacturaxml.get(i).getNroDoc()
                        + "' and rd.TipMonedaR = '" + listFacturaxml.get(i).getTipMoneda() + "'";

                PreparedStatement pstm2 = objConexion.prepareStatement(sql);
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {

                    GuiaRemisionxml objGuiaRemisionxml = new GuiaRemisionxml();

                    objGuiaRemisionxml.setNroGuia(rs2.getString("NroDocExt"));
                    objGuiaRemisionxml.setTipoGuia(rs2.getString("IdTipDocSUNAT"));
                    objGuiaRemisionxml.setDescGuia(rs2.getString("descripcion"));
                    objGuiaRemisionxml.setTipoDoc(rs2.getString("TipDoc"));

                    listFacturaxml.get(i).getListGuiaRemisionxml().add(objGuiaRemisionxml);
                }
            }
        } catch (SQLException e) {

            throw e;
        }

        return listFacturaxml;

    }

    public NotaCreditoxml obtenerNotaCreditoXML(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        NotaCreditoxml objNotaCreditoxml = new NotaCreditoxml();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "GlosaFija = dg.glosafija,\n"
                    + "DescripcionMotivo = dg.glosaVariable,\n"
                    + "NombreComercial = pjem.RazonSocial,\n"
                    + "ID= RIGHT(ub.IdUbigeo,6),\n"
                    + "Direccion = pemi.Direccion,\n"
                    + "Urbanizacion = '',\n"
                    + "Provincia = (select descripcion from UbiGeo where left(IdUbigeo,6) = left(ub.IdUbigeo,6) and Nivel = '2'),\n"
                    + "Departamento = (select Descripcion from UbiGeo where left(IdUbigeo,4) = left(ub.IdUbigeo,4) and Nivel = '1'),\n"
                    + "Distrito = (select Descripcion from UbiGeo where IdUbigeo = ub.IdUbigeo and Nivel = '3'),\n"
                    + "CodPais = (select left(Descripcion,2) from UbiGeo where left(IdUbigeo,2) = left(ub.IdUbigeo,2) and Nivel = '0'),\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "NumeroRucEjecutor = pjej.NroRuc,\n"
                    + "TipoDocIdentidadEjecutor = docIdenEje.idTipDocIdentidad,\n"
                    + "RazonSocialEjecutor = pjej.RazonSocial,\n"
                    + "CodOperacionGravada = '1001',\n"
                    + "MontoOperacionGravada = (select convert(varchar(15),sum(monto)) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "CodOpeacionInafecta = '1002',\n"
                    + "MontoOperacionInafecta = '0',\n"
                    + "CodOpeacionExonerada = '1003',\n"
                    + "MontoOperacionExonerada = '0',\n"
                    + "ImporteTotal = (select convert(varchar(15),sum(Total)) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "Moneda = mon.CurrencyCode,\n"
                    + "IGV = 18,\n"
                    + "IGVTotal = (select convert(varchar(15),sum(Monto)) from DetalleDocumentoCompraVta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina and IdDetGastoSunat = '1000'),\n"
                    + "Leyenda1 = '1000',\n"
                    + "LeyendaDes1 = (SELECT dbo.fnNumeroExpresadoEnLetra2(sum(total),dg.tipmoneda) from DocumentoCompraVenta  WHERE (nroDoc = dg.nroDoc) AND (IdDoc = dg.IdDoc) AND (IdOficina = dg.idOficina) AND (TipMoneda = dg.tipMoneda)),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda, \n"
                    + "tipDocFactura = (select top 1 idtipdocsunat from DocumentoCompraVenta where NroDoc = rela.NroDocR and IdDoc = rela.IdDocR and TipMoneda = rela.TipMonedaR and IdOficina = rela.IdOficinaR), \n"
                    + "Factura = (select top 1 NroDocExt from DocumentoCompraVenta where NroDoc = rela.NroDocR and IdDoc = rela.IdDocR and TipMoneda = rela.TipMonedaR and IdOficina = rela.IdOficinaR) \n"
                    + "from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dcv on  dg.IdOficina=dcv.IdOficina and dg.IdDoc=dcv.IdDoc and dg.NroDoc= dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago = dcv.IdTipDocSUNAT\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dcv.IdEmisor\n"
                    + "join PersonaJuridica pjej on pjej.IdPersona  = dcv.IdEjecutor\n"
                    + "join Persona pemi on pemi.IdPersona = pjem.IdPersona\n"
                    + "join UbiGeo ub on ub.IdUbigeo = pemi.IdUbigeoDir\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "join TabDocIdentidad docIdenEje on docIdenEje.idTipDocIdentidad = pjej.IdTipoDocIdentidad\n"
                    + "join TipoMoneda mon on mon.IdTipMoneda = dg.TipMoneda\n"
                    + "join RelaDoc rela on rela.IdDoc = dg.IdDoc and rela.IdOficina = dg.IdOficina and rela.NroDoc = dg.NroDoc and rela.TipMoneda = dg.TipMoneda\n"
                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objNotaCreditoxml.setIdDoc(objDocumentoGenerado.getIdDoc());
                objNotaCreditoxml.setNroDoc(objDocumentoGenerado.getNroDoc());
                objNotaCreditoxml.setTipM(objDocumentoGenerado.getObjTipoMoneda().getId());
                objNotaCreditoxml.setIdOfi(objDocumentoGenerado.getObjOficina().getIdOficina());
                objNotaCreditoxml.setTipoNota("");
                objNotaCreditoxml.setObservaciones("");

                String string = rs.getString("GlosaFija");
                String[] parts = string.split("/");

                for (int i = 0; i < parts.length; i++) {

                    if (i == 0) {
                        objNotaCreditoxml.setTipoNota(parts[0]);
                    }
                    if (i == 1) {
                        objNotaCreditoxml.setObservaciones(parts[1]);
                    }

                }

                objNotaCreditoxml.setDescripcionMotivo(rs.getString("DescripcionMotivo"));
                objNotaCreditoxml.setFechaEmision(rs.getString("FechaEmision"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("Numeracion"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objNotaCreditoxml.setNombreComercial(rs.getString("NombreComercial"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setId(rs.getString("ID"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDireccion(rs.getString("Direccion"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setUrbanizacion(rs.getString("Urbanizacion"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setProvincia(rs.getString("Provincia"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDepartamento(rs.getString("Departamento"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDistrito(rs.getString("Distrito"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setCodPais(rs.getString("CodPais"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objNotaCreditoxml.setTipoDocumento(rs.getString("TipoDocumentoSunat"));
                objNotaCreditoxml.setNumeracionFactura(rs.getString("Numeracion"));
                objNotaCreditoxml.setIgvTotal(rs.getString("IGVTotal"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setNroRuc(rs.getString("NumeroRucEjecutor"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setTipoDoc(rs.getString("TipoDocIdentidadEjecutor"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setRazonSocial(rs.getString("RazonSocialEjecutor"));
                objNotaCreditoxml.getObjOperacionGravada().setCodOperacion(rs.getString("CodOperacionGravada"));
                objNotaCreditoxml.getObjOperacionGravada().setMonto(rs.getString("MontoOperacionGravada"));
                objNotaCreditoxml.getObjOperacionInafecta().setCodOperacion(rs.getString("CodOpeacionInafecta"));
                objNotaCreditoxml.getObjOperacionInafecta().setMonto(rs.getString("MontoOperacionInafecta"));
                objNotaCreditoxml.getObjOperacionExonerada().setCodOperacion(rs.getString("CodOpeacionExonerada"));
                objNotaCreditoxml.getObjOperacionExonerada().setMonto(rs.getString("MontoOperacionExonerada"));
                objNotaCreditoxml.setImporteTotal(rs.getString("ImporteTotal"));
                objNotaCreditoxml.setTipMoneda(rs.getString("Moneda"));
                objNotaCreditoxml.setPorcentajeImpuesto(rs.getString("IGV"));
                objNotaCreditoxml.getObjLeyendaxml().setCodigo(rs.getString("Leyenda1"));
                objNotaCreditoxml.getObjLeyendaxml().setDescripcion(rs.getString("LeyendaDes1"));
                objNotaCreditoxml.getObjFacturaxml().setNumeracionFactura(rs.getString("Factura"));

            }

            sql = "select distinct \n"
                    + "dcv.UM as UnidadMedida,\n"
                    + "dcv.Cantidad,\n"
                    + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
                    + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                    + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) , \n"
                    + "CodValorVentaUnit='01',\n"
                    + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
                    + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
                    + "TipoAfectacion = '10',\n"
                    + "CodTributo = ddcv.IdDetGastoSunat,\n"
                    + "NomTributo = ele.descripcion,\n"
                    + "CIT = ele.tipoImpuesto,\n"
                    + "ValorVentaItem = ValorUnitario,\n"
                    + "NumOrden = dcv.Linea+1\n"
                    + "FROM DocumentoCompraVenta AS dcv\n"
                    + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                    + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
                    + "join ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'"
                    + "where dcv.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dcv.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dcv.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dcv.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and valorunitario > 0 order by NumOrden";

            PreparedStatement pstm1 = objConexion.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                objDetalleItemxml.setUnidadMedida(rs1.getString("UnidadMedida"));
                objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                objDetalleItemxml.setDescripcion(rs1.getString("GlosaVariable"));
                objDetalleItemxml.setValUnitario(rs1.getString("ValorUnitario"));
                objDetalleItemxml.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                objDetalleItemxml.getObjprecUnitario().setCod(rs1.getString("CodValorVentaUnit"));
                objDetalleItemxml.getObjprecUnitario().setPrecioUnit(rs1.getString("PrecioVenta"));
                objDetalleItemxml.setIgv(rs1.getString("AfectacionIGV"));
                objDetalleItemxml.setTipoAfectacion(rs1.getString("TipoAfectacion"));
                objDetalleItemxml.setCodTributo(rs1.getString("CodTributo"));
                objDetalleItemxml.setNomTributo(rs1.getString("NomTributo"));
                objDetalleItemxml.setValorVta(rs1.getString("ValorVentaItem"));
                objDetalleItemxml.setCit(rs1.getString("CIT"));
                objDetalleItemxml.setNroOrden(rs1.getString("NumOrden"));

                objNotaCreditoxml.getListDetalleItemxml().add(objDetalleItemxml);
            }
            sql = "select distinct gm.NroDocExt as NroDocExt, gm.IdTipDocSUNAT as IdTipDocSUNAT, sunat.descripcion, dcv.IdTipDocSUNAT as TipDoc\n"
                    + "from DocGenerado dg\n"
                    + "JOIN RelaDoc rd on rd.IdDoc = dg.IdDoc and rd.NroDoc = dg.NroDoc and rd.TipMoneda = dg.TipMoneda and rd.IdOficina = dg.IdOficina and rd.IdDocR = '0001'\n"
                    + "JOIN RelaDoc rd1 on rd.IdDocR = rd1.IdDocR and rd.NroDocR = rd1.NroDocR and rd.TipMonedaR = rd1.TipMonedaR and rd.IdOficinaR = rd1.IdOficinaR and rd1.IdDoc in ('0005','0006')\n"
                    + "JOIN GuiaRemision gm on  rd1.IdDoc = gm.IdDoc and rd1.NroDoc = gm.NroDoc and rd1.TipMoneda = gm.TipMoneda and rd1.IdOficina = gm.IdOficina\n"
                    + "JOIN TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago= gm.IdTipDocSUNAT \n"
                    + "JOIN DocumentoCompraVenta dcv on dg.IdDoc = dcv.IdDoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina\n"
                    + "where dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and dg.IdDoc = '"
                    + objDocumentoGenerado.getIdDoc() + "' and dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "'";

            PreparedStatement pstm2 = objConexion.prepareStatement(sql);
            ResultSet rs2 = pstm2.executeQuery();
            while (rs2.next()) {

                GuiaRemisionxml objGuiaRemisionxml = new GuiaRemisionxml();

                objGuiaRemisionxml.setNroGuia(rs2.getString("NroDocExt"));
                objGuiaRemisionxml.setTipoGuia(rs2.getString("IdTipDocSUNAT"));
                objGuiaRemisionxml.setDescGuia(rs2.getString("descripcion"));
                objGuiaRemisionxml.setTipoDoc(rs2.getString("TipDoc"));

                objNotaCreditoxml.getListGuiaRemisionxml().add(objGuiaRemisionxml);

            }
        } catch (SQLException e) {

            throw e;
        }

        return objNotaCreditoxml;

    }

    public NotaDebitoxml obtenerNotaDebitoXML(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        NotaDebitoxml objNotaDebitoxml = new NotaDebitoxml();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "GlosaFija = dg.glosafija,\n"
                    + "DescripcionMotivo = dg.glosaVariable,\n"
                    + "NombreComercial = pjem.RazonSocial,\n"
                    + "ID= RIGHT(ub.IdUbigeo,6),\n"
                    + "Direccion = pemi.Direccion,\n"
                    + "Urbanizacion = '',\n"
                    + "Provincia = (select descripcion from UbiGeo where left(IdUbigeo,6) = left(ub.IdUbigeo,6) and Nivel = '2'),\n"
                    + "Departamento = (select Descripcion from UbiGeo where left(IdUbigeo,4) = left(ub.IdUbigeo,4) and Nivel = '1'),\n"
                    + "Distrito = (select Descripcion from UbiGeo where IdUbigeo = ub.IdUbigeo and Nivel = '3'),\n"
                    + "CodPais = (select left(Descripcion,2) from UbiGeo where left(IdUbigeo,2) = left(ub.IdUbigeo,2) and Nivel = '0'),\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "NumeroRucEjecutor = pjej.NroRuc,\n"
                    + "TipoDocIdentidadEjecutor = docIdenEje.idTipDocIdentidad,\n"
                    + "RazonSocialEjecutor = pjej.RazonSocial,\n"
                    + "CodOperacionGravada = '1001',\n"
                    + "MontoOperacionGravada = (select convert(varchar(15),sum(monto)) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "CodOpeacionInafecta = '1002',\n"
                    + "MontoOperacionInafecta = '0',\n"
                    + "CodOpeacionExonerada = '1003',\n"
                    + "MontoOperacionExonerada = '0',\n"
                    + "ImporteTotal = (select convert(varchar(15),sum(Total)) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "Moneda = mon.CurrencyCode,\n"
                    + "IGV = 18,\n"
                    + "IGVTotal = (select convert(varchar(15),sum(Monto)) from DetalleDocumentoCompraVta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina and IdDetGastoSunat = '1000'),\n"
                    + "Leyenda1 = '1000',\n"
                    + "LeyendaDes1 = (SELECT dbo.fnNumeroExpresadoEnLetra2(sum(total),dg.tipmoneda) from DocumentoCompraVenta  WHERE (nroDoc = dg.nroDoc) AND (IdDoc = dg.IdDoc) AND (IdOficina = dg.idOficina) AND (TipMoneda = dg.tipMoneda)),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda, \n"
                    + "tipDocFactura = (select top 1 idtipdocsunat from DocumentoCompraVenta where NroDoc = rela.NroDocR and IdDoc = rela.IdDocR and TipMoneda = rela.TipMonedaR and IdOficina = rela.IdOficinaR), \n"
                    + "Factura = (select top 1 NroDocExt from DocumentoCompraVenta where NroDoc = rela.NroDocR and IdDoc = rela.IdDocR and TipMoneda = rela.TipMonedaR and IdOficina = rela.IdOficinaR) \n"
                    + "from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dcv on  dg.IdOficina=dcv.IdOficina and dg.IdDoc=dcv.IdDoc and dg.NroDoc= dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago = dcv.IdTipDocSUNAT\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dcv.IdEmisor\n"
                    + "join PersonaJuridica pjej on pjej.IdPersona  = dcv.IdEjecutor\n"
                    + "join Persona pemi on pemi.IdPersona = pjem.IdPersona\n"
                    + "join UbiGeo ub on ub.IdUbigeo = pemi.IdUbigeoDir\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "join TabDocIdentidad docIdenEje on docIdenEje.idTipDocIdentidad = pjej.IdTipoDocIdentidad\n"
                    + "join TipoMoneda mon on mon.IdTipMoneda = dg.TipMoneda\n"
                    + "join RelaDoc rela on rela.IdDoc = dg.IdDoc and rela.IdOficina = dg.IdOficina and rela.NroDoc = dg.NroDoc and rela.TipMoneda = dg.TipMoneda\n"
                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objNotaDebitoxml.setIdDoc(objDocumentoGenerado.getIdDoc());
                objNotaDebitoxml.setNroDoc(objDocumentoGenerado.getNroDoc());
                objNotaDebitoxml.setTipM(objDocumentoGenerado.getObjTipoMoneda().getId());
                objNotaDebitoxml.setIdOfi(objDocumentoGenerado.getObjOficina().getIdOficina());

                objNotaDebitoxml.setTipoNota(rs.getString("tipDocFactura"));
                objNotaDebitoxml.setObservaciones("");

                String string = rs.getString("GlosaFija");
                String[] parts = string.split("/");

                for (int i = 0; i < parts.length; i++) {

                    if (i == 0) {
                        objNotaDebitoxml.setTipoNota(parts[0]);
                    }
                    if (i == 1) {
                        objNotaDebitoxml.setObservaciones(parts[1]);
                    }

                }

                objNotaDebitoxml.setDescripcionMotivo(rs.getString("DescripcionMotivo"));
                objNotaDebitoxml.setFechaEmision(rs.getString("FechaEmision"));
                objNotaDebitoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("Numeracion"));
                objNotaDebitoxml.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objNotaDebitoxml.setNombreComercial(rs.getString("NombreComercial"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setId(rs.getString("ID"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setDireccion(rs.getString("Direccion"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setUrbanizacion(rs.getString("Urbanizacion"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setProvincia(rs.getString("Provincia"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setDepartamento(rs.getString("Departamento"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setDistrito(rs.getString("Distrito"));
                objNotaDebitoxml.getObjDomicilioFiscalxml().setCodPais(rs.getString("CodPais"));
                objNotaDebitoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objNotaDebitoxml.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objNotaDebitoxml.setTipoDocumento(rs.getString("TipoDocumentoSunat"));
                objNotaDebitoxml.setNumeracionFactura(rs.getString("Numeracion"));
                objNotaDebitoxml.setIgvTotal(rs.getString("IGVTotal"));
                objNotaDebitoxml.getObjPersonaxmlEjecutor().setNroRuc(rs.getString("NumeroRucEjecutor"));
                objNotaDebitoxml.getObjPersonaxmlEjecutor().setTipoDoc(rs.getString("TipoDocIdentidadEjecutor"));
                objNotaDebitoxml.getObjPersonaxmlEjecutor().setRazonSocial(rs.getString("RazonSocialEjecutor"));
                objNotaDebitoxml.getObjOperacionGravada().setCodOperacion(rs.getString("CodOperacionGravada"));
                objNotaDebitoxml.getObjOperacionGravada().setMonto(rs.getString("MontoOperacionGravada"));
                objNotaDebitoxml.getObjOperacionInafecta().setCodOperacion(rs.getString("CodOpeacionInafecta"));
                objNotaDebitoxml.getObjOperacionInafecta().setMonto(rs.getString("MontoOperacionInafecta"));
                objNotaDebitoxml.getObjOperacionExonerada().setCodOperacion(rs.getString("CodOpeacionExonerada"));
                objNotaDebitoxml.getObjOperacionExonerada().setMonto(rs.getString("MontoOperacionExonerada"));
                objNotaDebitoxml.setImporteTotal(rs.getString("ImporteTotal"));
                objNotaDebitoxml.setTipMoneda(rs.getString("Moneda"));
                objNotaDebitoxml.setPorcentajeImpuesto(rs.getString("IGV"));
                objNotaDebitoxml.getObjLeyendaxml().setCodigo(rs.getString("Leyenda1"));
                objNotaDebitoxml.getObjLeyendaxml().setDescripcion(rs.getString("LeyendaDes1"));
                objNotaDebitoxml.getObjFacturaxml().setNumeracionFactura(rs.getString("Factura"));

            }

//            sql = "select distinct \n"
////                    + "dcv.UM as UnidadMedida,\n"
////                    + "dcv.Cantidad,\n"
////                    + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
////                    + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
////                    + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
////                    + "CodValorVentaUnit='01',\n"
////                    + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
////                    + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
////                    + "TipoAfectacion = '10',\n"
////                    + "CodTributo = ddcv.IdDetGastoSunat,\n"
////                    + "NomTributo = ele.descripcion,\n"
////                    + "CIT = ele.tipoImpuesto,\n"
////                    + "ValorVentaItem = ValorUnitario,\n"
////                    + "NumOrden = dcv.Linea+1\n"
////                    + "FROM DocGenerado as dg\n"
////                    + "join RelaDoc rela on rela.IdDoc = dg.IdDoc and rela.IdOficina = dg.IdOficina and rela.NroDoc = dg.NroDoc and rela.TipMoneda = dg.TipMoneda\n"
////                    + "join DocumentoCompraVenta AS dcv ON rela.IdDocR = dcv.iddoc and rela.NroDocR = dcv.NroDoc and rela.TipMonedaR = dcv.TipMoneda and rela.IdOficinaR = dcv.IdOficina\n"
////                    + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
////                    + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
////                    + "join ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'\n"
////                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
////                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
////                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
////                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' \n"
////                    + "UNION\n"
////                    + "select distinct \n"
////                    + "dcv.UM as UnidadMedida,\n"
////                    + "dcv.Cantidad,\n"
////                    + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
////                    + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
////                    + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
////                    + "CodValorVentaUnit='01',\n"
////                    + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
////                    + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
////                    + "TipoAfectacion = '10',\n"
////                    + "CodTributo = ddcv.IdDetGastoSunat,\n"
////                    + "NomTributo = ele.descripcion,\n"
////                    + "CIT = ele.tipoImpuesto,\n"
////                    + "ValorVentaItem = ValorUnitario,\n"
////                    + "NumOrden = (select count(SubLinea)+1 from DetalleDocumentoCompraVta where rela.IdDocR = IdDoc and rela.IdOficinaR = IdOficina and rela.TipMonedaR = TipMoneda and rela.NroDocR = NroDoc and IdDetGastoSunat = '1000')\n"
////                    + "FROM DocGenerado as dg\n"
////                    + "join DocumentoCompraVenta AS dcv ON dg.IdDoc = dcv.iddoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina\n"
////                    + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
////                    + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
////                    + "join ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'\n"
////                    + "join RelaDoc rela on rela.IdDoc = dcv.IdDoc and rela.IdOficina = dcv.IdOficina and rela.NroDoc = dcv.NroDoc and rela.TipMoneda = dcv.TipMoneda\n"
////                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
////                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
////                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
////                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' \n"
////                    + "group by dcv.UM,dcv.Cantidad,dcv.GlosaVariable,dcv.IdDoc,dcv.IdOficina,dcv.TipMoneda, dcv.NroDoc,dcv.Linea,ddcv.IdDetGastoSunat,ele.descripcion,ele.tipoImpuesto,\n"
////                    + "dcv.PrecioUnitario,rela.IdDocR,rela.IdOficinaR,rela.TipMonedaR,rela.NroDocR,rela.IdDoc,ValorUnitario";
            sql = "select distinct \n"
                    + "dcv.UM as UnidadMedida,\n"
                    + "dcv.Cantidad,\n"
                    + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
                    + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                    + "PrecioUnitario = (select PrecioUnitario from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                    + "CodValorVentaUnit='01',\n"
                    + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
                    + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
                    + "TipoAfectacion = '10',\n"
                    + "CodTributo = ddcv.IdDetGastoSunat,\n"
                    + "NomTributo = ele.descripcion,\n"
                    + "CIT = ele.tipoImpuesto,\n"
                    + "ValorVentaItem = ValorUnitario,\n"
                    + "NumOrden = dcv.Linea+1\n"
                    + "FROM DocumentoCompraVenta AS dcv\n"
                    + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                    + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
                    + "JOIN ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'"
                    + "where dcv.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dcv.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dcv.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dcv.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "' and valorunitario > 0 order by NumOrden";

            PreparedStatement pstm1 = objConexion.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                objDetalleItemxml.setUnidadMedida(rs1.getString("UnidadMedida"));
                objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                objDetalleItemxml.setDescripcion(rs1.getString("GlosaVariable"));
                objDetalleItemxml.setValUnitario(rs1.getString("ValorUnitario"));
                objDetalleItemxml.setPrecioUnitario(rs1.getString("PrecioUnitario"));
                objDetalleItemxml.getObjprecUnitario().setCod(rs1.getString("CodValorVentaUnit"));
                objDetalleItemxml.getObjprecUnitario().setPrecioUnit(rs1.getString("PrecioVenta"));
                objDetalleItemxml.setIgv(rs1.getString("AfectacionIGV"));
                objDetalleItemxml.setTipoAfectacion(rs1.getString("TipoAfectacion"));
                objDetalleItemxml.setCodTributo(rs1.getString("CodTributo"));
                objDetalleItemxml.setNomTributo(rs1.getString("NomTributo"));
                objDetalleItemxml.setValorVta(rs1.getString("ValorVentaItem"));
                objDetalleItemxml.setCit(rs1.getString("CIT"));
                objDetalleItemxml.setNroOrden(rs1.getString("NumOrden"));

                objNotaDebitoxml.getListDetalleItemxml().add(objDetalleItemxml);

            }
        } catch (SQLException e) {

            throw e;
        }

        return objNotaDebitoxml;

    }

    public List<NotaCreditoxml> obtenerListaNotaCreditoXML() throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        List<NotaCreditoxml> listNotaCreditoxml = new ArrayList<NotaCreditoxml>();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "GlosaFija = dg.glosafija,\n"
                    + "DescripcionMotivo = dg.glosaVariable,\n"
                    + "NombreComercial = pjem.RazonSocial,\n"
                    + "ID= RIGHT(ub.IdUbigeo,6),\n"
                    + "Direccion = pemi.Direccion,\n"
                    + "Urbanizacion = '',\n"
                    + "Provincia = (select descripcion from UbiGeo where left(IdUbigeo,6) = left(ub.IdUbigeo,6) and Nivel = '2'),\n"
                    + "Departamento = (select Descripcion from UbiGeo where left(IdUbigeo,4) = left(ub.IdUbigeo,4) and Nivel = '1'),\n"
                    + "Distrito = (select Descripcion from UbiGeo where IdUbigeo = ub.IdUbigeo and Nivel = '3'),\n"
                    + "CodPais = (select left(Descripcion,2) from UbiGeo where left(IdUbigeo,2) = left(ub.IdUbigeo,2) and Nivel = '0'),\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "NumeroRucEjecutor = pjej.NroRuc,\n"
                    + "TipoDocIdentidadEjecutor = docIdenEje.idTipDocIdentidad,\n"
                    + "RazonSocialEjecutor = pjej.RazonSocial,\n"
                    + "CodOperacionGravada = '1001',\n"
                    + "MontoOperacionGravada = (select convert(varchar(15),sum(monto)) from DetalleDocumentoCompraVta where IdDetGastoSunat = '0001'and iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "CodOpeacionInafecta = '1002',\n"
                    + "MontoOperacionInafecta = '0',\n"
                    + "CodOpeacionExonerada = '1003',\n"
                    + "MontoOperacionExonerada = '0',\n"
                    + "ImporteTotal = (select convert(varchar(15),sum(Total)) from DocumentoCompraVenta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina),\n"
                    + "Moneda = mon.CurrencyCode,\n"
                    + "IGV = 18,\n"
                    + "IGVTotal = (select convert(varchar(15),sum(Monto)) from DetalleDocumentoCompraVta where iddoc = dg.IdDoc and NroDoc = dg.NroDoc and TipMoneda = dg.TipMoneda and IdOficina = dg.IdOficina and IdDetGastoSunat = '1000'),\n"
                    + "Leyenda1 = '1000',\n"
                    + "LeyendaDes1 = (SELECT dbo.fnNumeroExpresadoEnLetra2(sum(total),dg.tipmoneda) from DocumentoCompraVenta  WHERE (nroDoc = dg.nroDoc) AND (IdDoc = dg.IdDoc) AND (IdOficina = dg.idOficina) AND (TipMoneda = dg.tipMoneda)),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda, \n"
                    + "Factura = (select top 1 NroDocExt from DocumentoCompraVenta where NroDoc = rela.NroDocR and IdDoc = rela.IdDocR and IdTipMoneda = rela.TipMonedaR and IdOficina = rela.IdOficinaR) \n"
                    + "from DocGenerado dg\n"
                    + "join DocumentoCompraVenta dcv on  dg.IdOficina=dcv.IdOficina and dg.IdDoc=dcv.IdDoc and dg.NroDoc= dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago = dcv.IdTipDocSUNAT\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dcv.IdEmisor\n"
                    + "join PersonaJuridica pjej on pjej.IdPersona  = dcv.IdEjecutor\n"
                    + "join Persona pemi on pemi.IdPersona = pjem.IdPersona\n"
                    + "join UbiGeo ub on ub.IdUbigeo = pemi.IdUbigeoDir\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "join TabDocIdentidad docIdenEje on docIdenEje.idTipDocIdentidad = pjej.IdTipoDocIdentidad\n"
                    + "join TipoMoneda mon on mon.IdTipMoneda = dg.TipMoneda\n"
                    + "join RelaDoc rela on rela.IdDoc = dg.IdDoc and rela.IdOficina = dg.IdOficina and rela.NroDoc = dg.NroDoc and rela.TipMoneda = dg.TipMoneda\n";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                NotaCreditoxml objNotaCreditoxml = new NotaCreditoxml();

                objNotaCreditoxml.setIdDoc(rs.getString("IdDoc"));
                objNotaCreditoxml.setNroDoc(rs.getString("NroDoc"));
                objNotaCreditoxml.setTipM(rs.getString("TipMoneda"));
                objNotaCreditoxml.setIdOfi(rs.getString("IdOficina"));
                objNotaCreditoxml.setTipoNota("");
                objNotaCreditoxml.setObservaciones("");

                String string = rs.getString("GlosaFija");
                String[] parts = string.split("/");

                for (int i = 0; i < parts.length; i++) {

                    if (i == 0) {
                        objNotaCreditoxml.setTipoNota(parts[0]);
                    }
                    if (i == 1) {
                        objNotaCreditoxml.setObservaciones(parts[1]);
                    }

                }

                objNotaCreditoxml.setDescripcionMotivo(rs.getString("DescripcionMotivo"));
                objNotaCreditoxml.setFechaEmision(rs.getString("FechaEmision"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("Numeracion"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objNotaCreditoxml.setNombreComercial(rs.getString("NombreComercial"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setId(rs.getString("ID"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDireccion(rs.getString("Direccion"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setUrbanizacion(rs.getString("Urbanizacion"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setProvincia(rs.getString("Provincia"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDepartamento(rs.getString("Departamento"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setDistrito(rs.getString("Distrito"));
                objNotaCreditoxml.getObjDomicilioFiscalxml().setCodPais(rs.getString("CodPais"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objNotaCreditoxml.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objNotaCreditoxml.setTipoDocumento(rs.getString("TipoDocumentoSunat"));
                objNotaCreditoxml.setNumeracionFactura(rs.getString("Numeracion"));
                objNotaCreditoxml.setIgvTotal(rs.getString("IGVTotal"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setNroRuc(rs.getString("NumeroRucEjecutor"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setTipoDoc(rs.getString("TipoDocIdentidadEjecutor"));
                objNotaCreditoxml.getObjPersonaxmlEjecutor().setRazonSocial(rs.getString("RazonSocialEjecutor"));
                objNotaCreditoxml.getObjOperacionGravada().setCodOperacion(rs.getString("CodOperacionGravada"));
                objNotaCreditoxml.getObjOperacionGravada().setMonto(rs.getString("MontoOperacionGravada"));
                objNotaCreditoxml.getObjOperacionInafecta().setCodOperacion(rs.getString("CodOpeacionInafecta"));
                objNotaCreditoxml.getObjOperacionInafecta().setMonto(rs.getString("MontoOperacionInafecta"));
                objNotaCreditoxml.getObjOperacionExonerada().setCodOperacion(rs.getString("CodOpeacionExonerada"));
                objNotaCreditoxml.getObjOperacionExonerada().setMonto(rs.getString("MontoOperacionExonerada"));
                objNotaCreditoxml.setImporteTotal(rs.getString("ImporteTotal"));
                objNotaCreditoxml.setTipMoneda(rs.getString("Moneda"));
                objNotaCreditoxml.setPorcentajeImpuesto(rs.getString("IGV"));
                objNotaCreditoxml.getObjLeyendaxml().setCodigo(rs.getString("Leyenda1"));
                objNotaCreditoxml.getObjLeyendaxml().setDescripcion(rs.getString("LeyendaDes1"));
                objNotaCreditoxml.getObjFacturaxml().setNumeracionFactura(rs.getString("Factura"));

                listNotaCreditoxml.add(objNotaCreditoxml);
            }
            for (int i = 0; i < listNotaCreditoxml.size(); i++) {
                sql = "select distinct \n"
                        + "dcv.UM as UnidadMedida,\n"
                        + "dcv.Cantidad,\n"
                        + "GlosaVariable= Ltrim(dcv.GlosaVariable),\n"
                        + "ValorUnitario = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea) ,\n"
                        + "CodValorVentaUnit='01',\n"
                        + "PrecioVenta = (select sum(Monto) from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc  and SubLinea=dcv.Linea) ,\n"
                        + "AfectacionIGV = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '1000' and SubLinea=dcv.Linea),\n"
                        + "TipoAfectacion = '10',\n"
                        + "CodTributo = ddcv.IdDetGastoSunat,\n"
                        + "NomTributo = ele.descripcion,\n"
                        + "CIT = ele.tipoImpuesto,\n"
                        + "ValorVentaItem = (select Monto from  DetalleDocumentoCompraVta where dcv.IdDoc = IdDoc and dcv.IdOficina = IdOficina and dcv.TipMoneda = TipMoneda and dcv.NroDoc = NroDoc and IdDetGastoSunat = '0001' and SubLinea=dcv.Linea),\n"
                        + "NumOrden = dcv.Linea+1\n"
                        + "FROM DocumentoCompraVenta AS dcv\n"
                        + "JOIN DetalleDocumentoCompraVta AS ddcv ON ddcv.iddoc = dcv.iddoc and ddcv.NroDoc = dcv.NroDoc and ddcv.TipMoneda = dcv.TipMoneda and ddcv.IdOficina = dcv.IdOficina and dcv.Linea = ddcv.SubLinea\n"
                        + "JOIN TipoMoneda AS mon ON mon.IdTipMoneda = ddcv.TipMoneda\n"
                        + "join ElementoGasto as ele on ele.idIdentificadorGasto = ddcv.IdDetGastoSunat and ddcv.IdDetGastoSunat = '1000'"
                        + "where dcv.IdDoc = '" + listNotaCreditoxml.get(i).getIdDoc() + "' and \n"
                        + "dcv.NroDoc = '" + listNotaCreditoxml.get(i).getNroDoc() + "' and \n"
                        + "dcv.TipMoneda = '" + listNotaCreditoxml.get(i).getTipMoneda() + "' and \n"
                        + "dcv.IdOficina = '" + listNotaCreditoxml.get(i).getIdOfi() + "'";

                PreparedStatement pstm1 = objConexion.prepareStatement(sql);
                ResultSet rs1 = pstm1.executeQuery();
                while (rs1.next()) {

                    DetalleItemxml objDetalleItemxml = new DetalleItemxml();

                    objDetalleItemxml.setUnidadMedida(rs1.getString("UnidadMedida"));
                    objDetalleItemxml.setCantidad(rs1.getString("Cantidad"));
                    objDetalleItemxml.setDescripcion(rs1.getString("GlosaVariable"));
                    objDetalleItemxml.setValUnitario(rs1.getString("ValorUnitario"));
                    objDetalleItemxml.getObjprecUnitario().setCod(rs1.getString("CodValorVentaUnit"));
                    objDetalleItemxml.getObjprecUnitario().setPrecioUnit(rs1.getString("PrecioVenta"));
                    objDetalleItemxml.setIgv(rs1.getString("AfectacionIGV"));
                    objDetalleItemxml.setTipoAfectacion(rs1.getString("TipoAfectacion"));
                    objDetalleItemxml.setCodTributo(rs1.getString("CodTributo"));
                    objDetalleItemxml.setNomTributo(rs1.getString("NomTributo"));
                    objDetalleItemxml.setValorVta(rs1.getString("ValorVentaItem"));
                    objDetalleItemxml.setCit(rs1.getString("CIT"));
                    objDetalleItemxml.setNroOrden(rs1.getString("NumOrden"));

                    listNotaCreditoxml.get(i).getListDetalleItemxml().add(objDetalleItemxml);
                }
                sql = "select distinct gm.NroDocExt as NroDocExt, gm.IdTipDocSUNAT as IdTipDocSUNAT, sunat.descripcion, dcv.IdTipDocSUNAT as TipDoc\n"
                        + "from DocGenerado dg\n"
                        + "JOIN RelaDoc rd on rd.IdDoc = dg.IdDoc and rd.NroDoc = dg.NroDoc and rd.TipMoneda = dg.TipMoneda and rd.IdOficina = dg.IdOficina and rd.IdDocR = '0001'\n"
                        + "JOIN RelaDoc rd1 on rd.IdDocR = rd1.IdDocR and rd.NroDocR = rd1.NroDocR and rd.TipMonedaR = rd1.TipMonedaR and rd.IdOficinaR = rd1.IdOficinaR and rd1.IdDoc in ('0005','0006')\n"
                        + "JOIN GuiaRemision gm on  rd1.IdDoc = gm.IdDoc and rd1.NroDoc = gm.NroDoc and rd1.TipMoneda = gm.TipMoneda and rd1.IdOficina = gm.IdOficina\n"
                        + "JOIN TipoComprobantePagoSUNAT sunat on sunat.idTipoComprobantePago= gm.IdTipDocSUNAT \n"
                        + "JOIN DocumentoCompraVenta dcv on dg.IdDoc = dcv.IdDoc and dg.NroDoc = dcv.NroDoc and dg.TipMoneda = dcv.TipMoneda and dg.IdOficina = dcv.IdOficina\n"
                        + "where dg.IdOficina = '" + listNotaCreditoxml.get(i).getIdOfi() + "' and dg.IdDoc = '" + listNotaCreditoxml.get(i).getIdDoc() + "' and dg.NroDoc = '" + listNotaCreditoxml.get(i).getNroDoc() + "' and dg.TipMoneda = '" + listNotaCreditoxml.get(i).getTipMoneda() + "'";

                PreparedStatement pstm2 = objConexion.prepareStatement(sql);
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {

                    GuiaRemisionxml objGuiaRemisionxml = new GuiaRemisionxml();

                    objGuiaRemisionxml.setNroGuia(rs2.getString("NroDocExt"));
                    objGuiaRemisionxml.setTipoGuia(rs2.getString("IdTipDocSUNAT"));
                    objGuiaRemisionxml.setDescGuia(rs2.getString("descripcion"));
                    objGuiaRemisionxml.setTipoDoc(rs2.getString("TipDoc"));

                    listNotaCreditoxml.get(i).getListGuiaRemisionxml().add(objGuiaRemisionxml);

                }
            }
        } catch (SQLException e) {

            throw e;
        }

        return listNotaCreditoxml;

    }

    public Boolean registrarFacturaXML(Facturaxml objFacturaxml) throws Exception {
        Boolean result = false;

        try (Connection objC = Connect.Instancia().getConnectMySQL()) {
            String sql = "INSERT INTO FE_FacturaPrueba (FechaEmision, Direccion,"
                    + " Provincia, Departamento, Distrito, NroRucEmi, TipoDocIdenEmi, "
                    + " TipoDocumento, Numeracion, NroRucEjec, TipoDocIdenEjec, RazonSocialEjec, CodOperacGrav,"
                    + " MontoOperacGrav, CodOperacInaf, MontoOperacInaf, CodOperacExo, MontoOperacExo,PorcentajeIGV,IGV, ImporteTotal, "
                    + " CodMoneda, LeyendaDescripcion,DigestValue,SignatureValue) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = objC.prepareStatement(sql);
            pstm.setString(1, objFacturaxml.getFechaEmision());
            pstm.setString(2, objFacturaxml.getObjDomicilioFiscalxml().getDireccion());
            pstm.setString(3, objFacturaxml.getObjDomicilioFiscalxml().getProvincia());
            pstm.setString(4, objFacturaxml.getObjDomicilioFiscalxml().getDepartamento());
            pstm.setString(5, objFacturaxml.getObjDomicilioFiscalxml().getDistrito());
            pstm.setString(6, objFacturaxml.getObjPersonaxmlEmisor().getNroRuc());
            pstm.setString(7, objFacturaxml.getObjPersonaxmlEmisor().getTipoDoc());
            pstm.setString(8, objFacturaxml.getTipoDocumento());
            pstm.setString(9, objFacturaxml.getNumeracionFactura());
            pstm.setString(10, objFacturaxml.getObjPersonaxmlEjecutor().getNroRuc());
            pstm.setString(11, objFacturaxml.getObjPersonaxmlEjecutor().getTipoDoc());
            pstm.setString(12, objFacturaxml.getObjPersonaxmlEjecutor().getRazonSocial());
            pstm.setString(13, objFacturaxml.getObjOperacionGravada().getCodOperacion());
            pstm.setString(14, objFacturaxml.getObjOperacionGravada().getMonto());
            pstm.setString(15, objFacturaxml.getObjOperacionInafecta().getCodOperacion());
            pstm.setString(16, objFacturaxml.getObjOperacionInafecta().getMonto());
            pstm.setString(17, objFacturaxml.getObjOperacionExonerada().getCodOperacion());
            pstm.setString(18, objFacturaxml.getObjOperacionExonerada().getMonto());
            pstm.setString(19, objFacturaxml.getPorcentajeImpuesto());
            pstm.setString(20, objFacturaxml.getIgvTotal());
            pstm.setString(21, objFacturaxml.getImporteTotal());
            pstm.setString(22, objFacturaxml.getTipMoneda());
            pstm.setString(23, objFacturaxml.getObjLeyendaxml().getDescripcion());
            pstm.setString(24, objFacturaxml.getDigestValue());
            pstm.setString(25, objFacturaxml.getSignatureValue());

            result = pstm.execute();

            for (int i = 0; i < objFacturaxml.getListGuiaRemisionxml().size(); i++) {
                sql = "INSERT INTO FE_GuiaRemisionFacturaPrueba (Numeracion, NroGuia, TipoGuia, DescripcionTGuia, TipoDocumento) VALUES (?,?,?,?,?);";
                PreparedStatement pstm2 = objC.prepareStatement(sql);

                pstm2.setString(1, objFacturaxml.getNumeracionFactura());
                pstm2.setString(2, objFacturaxml.getListGuiaRemisionxml().get(i).getNroGuia());
                pstm2.setString(3, objFacturaxml.getListGuiaRemisionxml().get(i).getTipoGuia());
                pstm2.setString(4, objFacturaxml.getListGuiaRemisionxml().get(i).getDescGuia());
                pstm2.setString(5, objFacturaxml.getListGuiaRemisionxml().get(i).getTipoDoc());

                result = pstm2.execute();
            }

            for (int i = 0; i < objFacturaxml.getListDetalleItemxml().size(); i++) {
                sql = "INSERT INTO FE_DetalleFacturaPrueba (UnidadMedida, Cantidad, Descripcion, ValorUnitario,"
                        + " CodPrecioVenta, MontoPrecioVenta, MontoIGV, AfectacionIGV, CodTributo, NombreTributo,"
                        + " CodInternacTributo, ValorVenta, NroOrden, Numeracion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstm1 = objC.prepareStatement(sql);

                pstm1.setString(1, objFacturaxml.getListDetalleItemxml().get(i).getUnidadMedida());
                pstm1.setString(2, objFacturaxml.getListDetalleItemxml().get(i).getCantidad());
                pstm1.setString(3, objFacturaxml.getListDetalleItemxml().get(i).getDescripcion());
                pstm1.setString(4, objFacturaxml.getListDetalleItemxml().get(i).getValUnitario());
                pstm1.setString(5, objFacturaxml.getListDetalleItemxml().get(i).getObjprecUnitario().getCod());
                pstm1.setString(6, objFacturaxml.getListDetalleItemxml().get(i).getObjprecUnitario().getPrecioUnit());
                pstm1.setString(7, objFacturaxml.getListDetalleItemxml().get(i).getIgv());
                pstm1.setString(8, objFacturaxml.getListDetalleItemxml().get(i).getTipoAfectacion());
                pstm1.setString(9, objFacturaxml.getListDetalleItemxml().get(i).getCodTributo());
                pstm1.setString(10, objFacturaxml.getListDetalleItemxml().get(i).getNomTributo());
                pstm1.setString(11, objFacturaxml.getListDetalleItemxml().get(i).getCit());
                pstm1.setString(12, objFacturaxml.getListDetalleItemxml().get(i).getValorVta());
                pstm1.setString(13, objFacturaxml.getListDetalleItemxml().get(i).getNroOrden());
                pstm1.setString(14, objFacturaxml.getNumeracionFactura());
                result = pstm1.execute();
            }
        } catch (Exception ex) {
            throw ex;

        }

        return result;
    }

    public Boolean registrarNotaCreditoXML(NotaCreditoxml objNotaCreditoxml) throws Exception {
        Boolean result = false;

        try (Connection objC = Connect.Instancia().getConnectMySQL()) {
            String sql = "INSERT INTO FE_NotaCreditoPrueba (FechaEmision, Provincia, Departamento, Distrito, NroRucEmi, "
                    + " TipoDocIdenEmi, TipoDocumento, Numeracion, NroRucEjec, TipoDocIdenEjec, RazonSocialEjec,"
                    + " Direccion, CodOperacGrav, MontoOperacGrav, CodOperacInaf, MontoOperacInaf, CodOperacExo, "
                    + " MontoOperacExo, PorcentajeIGV, IGV, ImporteTotal, CodMoneda, LeyendaDescripcion, TipoNota,"
                    + " DescripcionMotivo, NumeracionFactura, DigestValue, SignatureValue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstm = objC.prepareStatement(sql);

            pstm.setString(1, objNotaCreditoxml.getFechaEmision());
            pstm.setString(2, objNotaCreditoxml.getObjDomicilioFiscalxml().getProvincia());
            pstm.setString(3, objNotaCreditoxml.getObjDomicilioFiscalxml().getDepartamento());
            pstm.setString(4, objNotaCreditoxml.getObjDomicilioFiscalxml().getDistrito());
            pstm.setString(5, objNotaCreditoxml.getObjPersonaxmlEmisor().getNroRuc());
            pstm.setString(6, objNotaCreditoxml.getObjPersonaxmlEmisor().getTipoDoc());
            pstm.setString(7, objNotaCreditoxml.getTipoDocumento());
            pstm.setString(8, objNotaCreditoxml.getNumeracionFactura());
            pstm.setString(9, objNotaCreditoxml.getObjPersonaxmlEjecutor().getNroRuc());
            pstm.setString(10, objNotaCreditoxml.getObjPersonaxmlEjecutor().getTipoDoc());
            pstm.setString(11, objNotaCreditoxml.getObjPersonaxmlEjecutor().getRazonSocial());
            pstm.setString(12, objNotaCreditoxml.getObjDomicilioFiscalxml().getDireccion());
            pstm.setString(13, objNotaCreditoxml.getObjOperacionGravada().getCodOperacion());
            pstm.setString(14, objNotaCreditoxml.getObjOperacionGravada().getMonto());
            pstm.setString(15, objNotaCreditoxml.getObjOperacionInafecta().getCodOperacion());
            pstm.setString(16, objNotaCreditoxml.getObjOperacionInafecta().getMonto());
            pstm.setString(17, objNotaCreditoxml.getObjOperacionExonerada().getCodOperacion());
            pstm.setString(18, objNotaCreditoxml.getObjOperacionExonerada().getMonto());
            pstm.setString(19, objNotaCreditoxml.getPorcentajeImpuesto());
            pstm.setString(20, objNotaCreditoxml.getIgvTotal());
            pstm.setString(21, objNotaCreditoxml.getImporteTotal());
            pstm.setString(22, objNotaCreditoxml.getTipMoneda());
            pstm.setString(23, objNotaCreditoxml.getObjLeyendaxml().getDescripcion());
            pstm.setString(24, objNotaCreditoxml.getTipoNota());
            pstm.setString(25, objNotaCreditoxml.getDescripcionMotivo());
            pstm.setString(26, objNotaCreditoxml.getObjFacturaxml().getNumeracionFactura());
            pstm.setString(27, objNotaCreditoxml.getDigestValue());
            pstm.setString(28, objNotaCreditoxml.getSignatureValue());

            result = pstm.execute();

            for (int i = 0; i < objNotaCreditoxml.getListGuiaRemisionxml().size(); i++) {
                sql = "INSERT INTO FE_GuiaRemisionFacturaPrueba (Numeracion, NroGuia, TipoGuia, DescripcionTGuia, TipoDocumento) VALUES (?,?,?,?,?);";
                PreparedStatement pstm2 = objC.prepareStatement(sql);

                pstm2.setString(1, objNotaCreditoxml.getNumeracionFactura());
                pstm2.setString(2, objNotaCreditoxml.getListGuiaRemisionxml().get(i).getNroGuia());
                pstm2.setString(3, objNotaCreditoxml.getListGuiaRemisionxml().get(i).getTipoGuia());
                pstm2.setString(4, objNotaCreditoxml.getListGuiaRemisionxml().get(i).getDescGuia());
                pstm2.setString(5, objNotaCreditoxml.getListGuiaRemisionxml().get(i).getTipoDoc());

                result = pstm2.execute();
            }

            for (int i = 0; i < objNotaCreditoxml.getListDetalleItemxml().size(); i++) {
                sql = "INSERT INTO FE_DetalleNotaCreditoPrueba (UnidadMedida, Cantidad, Descripcion, ValorUnitario,"
                        + " CodPrecioVenta, MontoPrecioVenta, MontoIGV, AfectacionIGV, CodTributo, NombreTributo,"
                        + " CodInternacTributo, ValorVenta, NroOrden, Numeracion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstm1 = objC.prepareStatement(sql);

                pstm1.setString(1, objNotaCreditoxml.getListDetalleItemxml().get(i).getUnidadMedida());
                pstm1.setString(2, objNotaCreditoxml.getListDetalleItemxml().get(i).getCantidad());
                pstm1.setString(3, objNotaCreditoxml.getListDetalleItemxml().get(i).getDescripcion());
                pstm1.setString(4, objNotaCreditoxml.getListDetalleItemxml().get(i).getValUnitario());
                pstm1.setString(5, objNotaCreditoxml.getListDetalleItemxml().get(i).getObjprecUnitario().getCod());
                pstm1.setString(6, objNotaCreditoxml.getListDetalleItemxml().get(i).getObjprecUnitario().getPrecioUnit());
                pstm1.setString(7, objNotaCreditoxml.getListDetalleItemxml().get(i).getIgv());
                pstm1.setString(8, objNotaCreditoxml.getListDetalleItemxml().get(i).getTipoAfectacion());
                pstm1.setString(9, objNotaCreditoxml.getListDetalleItemxml().get(i).getCodTributo());
                pstm1.setString(10, objNotaCreditoxml.getListDetalleItemxml().get(i).getNomTributo());
                pstm1.setString(11, objNotaCreditoxml.getListDetalleItemxml().get(i).getCit());
                pstm1.setString(12, objNotaCreditoxml.getListDetalleItemxml().get(i).getValorVta());
                pstm1.setString(13, objNotaCreditoxml.getListDetalleItemxml().get(i).getNroOrden());
                pstm1.setString(14, objNotaCreditoxml.getNumeracionFactura());
                result = pstm1.execute();
            }
        } catch (Exception ex) {
            throw ex;

        }

        return result;
    }

    public DocumentoBajaxml obtenerDocumentoBajaXML(DocumentoGenerado objDocumentoGenerado) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        DocumentoBajaxml objDocumentoBajaxml = new DocumentoBajaxml();
        Leyendaxml objLeyendaXML = new Leyendaxml();

        try {
            String sql = "select distinct \n"
                    + "FechaEmision =  convert(char(10),dg.FechaDocumento,126),\n"
                    + "FechaComunicacion =  convert(char(10),dg.FechaProceso,126),\n"
                    + "RazonSocialEmisor = pjem.RazonSocial,\n"
                    + "NumeroRucEmisor = pjem.NroRuc,\n"
                    + "TipoDocIdentidadEmisor = docIdenEmi.idTipDocIdentidad,\n"
                    + "Identificador = (SELECT dbo.fnObtenerParametroDocBaja()),\n"
                    + "dg.IdOficina,dg.IdDoc,dg.NroDoc,dg.TipMoneda \n"
                    + "from DocGenerado dg\n"
                    + "join PersonaJuridica pjem on pjem.IdPersona = dg.IdPersona\n"
                    + "join TabDocIdentidad docIdenEmi on docIdenEmi.idTipDocIdentidad = pjem.IdTipoDocIdentidad\n"
                    + "where dg.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "dg.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "dg.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "dg.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                objDocumentoBajaxml.setFechaGeneracion(rs.getString("FechaEmision"));
                objDocumentoBajaxml.setFechaComunicacion(rs.getString("FechaComunicacion"));
                objDocumentoBajaxml.getObjPersonaxmlEmisor().setRazonSocial(rs.getString("RazonSocialEmisor"));
                objDocumentoBajaxml.getObjPersonaxmlEmisor().setNroRuc(rs.getString("NumeroRucEmisor"));
                objDocumentoBajaxml.getObjPersonaxmlEmisor().setTipoDoc(rs.getString("TipoDocIdentidadEmisor"));
                objDocumentoBajaxml.setIdentificador(rs.getString("Identificador"));

            }

            sql = "select distinct \n"
                    + "TipoDocumentoSunat = sunat.idTipoComprobantePago,\n"
                    + "Numeracion = dcv.NroDocExt,\n"
                    + "Motivo = dg.GlosaVariable\n"
                    + "from reladoc rdoc\n"
                    + "join DocGenerado dg on dg.IdDoc = rdoc.IdDoc and dg.NroDoc = rdoc.NroDoc and dg.TipMoneda = rdoc.TipMoneda and dg.IdOficina = rdoc.IdOficina\n"
                    + "join DocumentoCompraVenta dcv on dcv.IdDoc = rdoc.IdDocR and dcv.NroDoc = rdoc.NroDocR and dcv.TipMoneda = rdoc.TipMonedaR and dcv.IdOficina = rdoc.IdOficinaR\n"
                    + "join TipoComprobantePagoSUNAT sunat on sunat.iddoc = dcv.IdDoc\n"
                    + "where rdoc.IdDoc = '" + objDocumentoGenerado.getIdDoc() + "' and \n"
                    + "rdoc.NroDoc = '" + objDocumentoGenerado.getNroDoc() + "' and \n"
                    + "rdoc.TipMoneda = '" + objDocumentoGenerado.getObjTipoMoneda().getId() + "' and \n"
                    + "rdoc.IdOficina = '" + objDocumentoGenerado.getObjOficina().getIdOficina() + "'";

            PreparedStatement pstm1 = objConexion.prepareStatement(sql);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                DetalleDocBajaxml objDetalleDocBajaxml = new DetalleDocBajaxml();

                objDetalleDocBajaxml.setMotivo(rs1.getString("Motivo"));
                objDetalleDocBajaxml.setNumeracionDocumento(rs1.getString("Numeracion"));
                objDetalleDocBajaxml.setTipoDocumento(rs1.getString("TipoDocumentoSunat"));

                objDocumentoBajaxml.getListDetalleDocBajaxml().add(objDetalleDocBajaxml);

            }

        } catch (SQLException e) {

            throw e;
        }

        return objDocumentoBajaxml;

    }
}
