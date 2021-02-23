/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.contabilidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.contabilidad.TipoComprobantePago;

/**
 *
 * @author PauPar
 */
public class TipoComprobantePagoDAO {

    private static TipoComprobantePagoDAO _Instancia;

    private TipoComprobantePagoDAO() {
    }

    public static TipoComprobantePagoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoComprobantePagoDAO();
        }
        return _Instancia;
    }

    public List<TipoComprobantePago> listar() throws Exception {

        List<TipoComprobantePago> listTipoComprobantePago = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT idTipoComprobantePago, Descripcion, IdDoc FROM TipoComprobantePagoSUNAT where  estado = 1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoComprobantePago objTipoComprobantePago = new TipoComprobantePago();
                objTipoComprobantePago.setIdTipoComprobante(rs.getString("idTipoComprobantePago"));
                objTipoComprobantePago.setDescripcion(rs.getString("Descripcion"));
                objTipoComprobantePago.setIdDoc(rs.getString("IdDoc"));

                listTipoComprobantePago.add(objTipoComprobantePago);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoComprobantePago;
    }
    
     public List<TipoComprobantePago> listarComprobanteCompra() throws Exception {

        List<TipoComprobantePago> listTipoComprobantePago = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT idTipoComprobantePago, Descripcion, IdDoc FROM TipoComprobantePagoSUNAT where  estado = 1 and compra=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoComprobantePago objTipoComprobantePago = new TipoComprobantePago();
                objTipoComprobantePago.setIdTipoComprobante(rs.getString("idTipoComprobantePago"));
                objTipoComprobantePago.setDescripcion(rs.getString("Descripcion"));
                objTipoComprobantePago.setIdDoc(rs.getString("IdDoc"));

                listTipoComprobantePago.add(objTipoComprobantePago);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTipoComprobantePago;
    }

}
