/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.mySQL;

import java.util.HashMap;
import ll.accesodatos.mySQL.MySQLDocumentoCompraVentaDAO;
import ll.entidades.contabilidad.DocCompraVenta;
import ll.entidades.xmlFacturacion.Facturaxml;

import ll.entidades.operaciones.DocumentoGenerado;
import ll.entidades.xmlFacturacion.DocumentoBajaxml;
import ll.entidades.xmlFacturacion.GuiaRemisionxml;
import ll.entidades.xmlFacturacion.NotaCreditoxml;
import ll.entidades.xmlFacturacion.NotaDebitoxml;

/**
 *
 * @author PauPar
 */
public class MySQLDocumentoCompraVentaNEG {

    private static MySQLDocumentoCompraVentaNEG _Instancia;

    private MySQLDocumentoCompraVentaNEG() {
    }

    public static MySQLDocumentoCompraVentaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new MySQLDocumentoCompraVentaNEG();
        }
        return _Instancia;
    }

    public NotaCreditoxml obtenerNotaCreditoXML(DocumentoGenerado objDocumentoGenerado)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().obtenerNotaCreditoXML(objDocumentoGenerado);
    }

    public Facturaxml obtenerFacturaXML(DocumentoGenerado objDocumentoGenerado)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().obtenerFacturaXML(objDocumentoGenerado);
    }

    public GuiaRemisionxml obtenerGuiaRemisionXML(DocumentoGenerado objDocumentoGenerado)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().obtenerGuiaRemisionXML(objDocumentoGenerado);
    }

    public Boolean registrarFacturaXML(Facturaxml objFacturaXML)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().registrarFacturaXML(objFacturaXML);
    }

    public Boolean registrarNotaCreditoXML(NotaCreditoxml objNotaCreditoxml)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().registrarNotaCreditoXML(objNotaCreditoxml);
    }

    public NotaDebitoxml obtenerNotaDebitoXML(DocumentoGenerado objDocumentoGenerado)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().obtenerNotaDebitoXML(objDocumentoGenerado);
    }

    public DocumentoBajaxml obtenerDocumentoBajaXML(DocumentoGenerado objDocumentoGenerado)
            throws Exception {

        return MySQLDocumentoCompraVentaDAO.Instancia().obtenerDocumentoBajaXML(objDocumentoGenerado);
    }

}
