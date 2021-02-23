package ll.negocio.contabilidad;

import ll.accesodatos.contabilidad.FacturaDAO;
import ll.entidades.contabilidad.factura.CabeceraFact;

/**
 *
 * @author RenRio
 */
public class FacturaNEG {

    private static FacturaNEG _Instancia;

    private FacturaNEG() {
    }

    public static FacturaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new FacturaNEG();
        }
        return _Instancia;
    }

    public int verificarNotaCredito(String nroFactura) throws Exception {
        return FacturaDAO.Instancia().verificarNotaCredito(nroFactura);
    }

    public int verificarNotaDebito(String nroFactura) throws Exception {
        return FacturaDAO.Instancia().verificarNotaDebito(nroFactura);
    }

    public CabeceraFact obtenerCabFactura(String nroFactura) throws Exception {
        return FacturaDAO.Instancia().obtenerCabFactura(nroFactura);
    }

    public CabeceraFact obtenerCabFacturaAnulacion(String nroFactura, String nroDoc) throws Exception {
        return FacturaDAO.Instancia().obtenerCabFacturaAnulacion(nroFactura, nroDoc);
    }
}
