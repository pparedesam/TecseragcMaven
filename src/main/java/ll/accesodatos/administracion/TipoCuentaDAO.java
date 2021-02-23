/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.accesodatos.administracion;

import ll.entidades.administracion.TipoCuenta;
import ll.entidades.administracion.Cuenta;
import ll.entidades.administracion.TipoPagoInteres;
import ll.entidades.administracion.CuentaPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;

/**
 *
 * @author MarVer
 */
public class TipoCuentaDAO {

    private static TipoCuentaDAO _Instancia;

    private TipoCuentaDAO() {
    }

    public static TipoCuentaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoCuentaDAO();
        }
        return _Instancia;
    }

    public List<TipoCuenta> listarTipoCuenta(String TipMoneda, String TipoPersona) throws Exception {

        List<TipoCuenta> lstTipoCuenta = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT dbo.TipoCuenta.IdTipCta, dbo.TipoCuenta.TipMoneda,dbo.TipoCuenta.Descripcion,TipoCuenta.TipoPersona,TipoCuenta.MontoMinimo "
                    + " From dbo.Producto  INNER JOIN dbo.TipoCuenta "
                    + " ON  dbo.Producto.IdProducto = dbo.TipoCuenta.IdProducto  INNER JOIN tipocuentaxrango "
                    + " on  tipocuentaxrango.IdTipCta=TipoCuenta.IdTipCta and  tipocuentaxrango.TipMon = TipoCuenta.TipMoneda  "
                    + " WHERE dbo.Producto.IdProducto IN('04','05') and TipoCuenta.tipmoneda='" + TipMoneda + "' and (TipoCuenta.TipoPersona in ('" + TipoPersona + "','X') or TipoCuenta.TipoPersona is null) and TipoCuenta.Estado=1"
                    + " group by dbo.TipoCuenta.IdTipCta, dbo.TipoCuenta.TipMoneda,dbo.TipoCuenta.Descripcion,TipoCuenta.TipoPersona,TipoCuenta.MontoMinimo "
                    + " order by idtipcta ";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoCuenta objTipoCuenta = new TipoCuenta();
                objTipoCuenta.setIdTipCta(rs.getString("IdTipCta"));
                objTipoCuenta.setTipMoneda(rs.getString("TipMoneda"));
                objTipoCuenta.setDescripcion(rs.getString("Descripcion"));
                objTipoCuenta.setMontoMinimo(rs.getFloat("MontoMinimo"));

                lstTipoCuenta.add(objTipoCuenta);
            }

        } catch (Exception e) {
            throw e;
        }

        return lstTipoCuenta;
    }

    public TipoCuenta obtenerTasaxMonto(String IdTipCta, String TipMoneda, double Monto) throws Exception {

        TipoCuenta objTipoCuenta = new TipoCuenta();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "{call sch_Afiliaciones_sp_ObtenerTasaxMonto (?,?,?)}";

            PreparedStatement pstm = objConexion.prepareStatement(sql);
            pstm.setDouble(1, Monto);
            pstm.setString(2, IdTipCta);
            pstm.setString(3, TipMoneda);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                objTipoCuenta.setTasaVigente(rs.getFloat("Tasa"));
                objTipoCuenta.setTEAVigente(rs.getFloat("TEA"));
                objTipoCuenta.setTasaAdelantada(rs.getFloat("TasaAd"));
                objTipoCuenta.setTEAAdelantada(rs.getFloat("TEAAd"));

            }

        } catch (Exception e) {
            throw e;
        }

        return objTipoCuenta;
    }

    public List<TipoPagoInteres> obtenerTipoPagoInteres() throws Exception {

        List<TipoPagoInteres> lstTipoPagoInteres = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select * from TipoPagoInteres";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TipoPagoInteres objTipoPagoInteres = new TipoPagoInteres();
                objTipoPagoInteres.setIdTipoPagoInteres(rs.getString("IdTipoPagoInteres"));
                objTipoPagoInteres.setDescripcion(rs.getString("Descripcion"));
                lstTipoPagoInteres.add(objTipoPagoInteres);

            }

        } catch (Exception e) {
            throw e;
        }

        return lstTipoPagoInteres;
    }

    public List<CuentaPersona> obtenerCuentasIA(String idPersona, String tipMoneda) throws Exception {

        List<CuentaPersona> lstCuentaPersona = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT cta.IdOficina,cta.IdTipCta,cta.TipMoneda,cta.NumCuenta,cta.IdPersona FROM dbo.CtaPersona cta "
                    + "INNER JOIN Cuenta c ON cta.IdOficina = c.IdOficina "
                    + "AND cta.IdTipCta = c.IdTipCta "
                    + "AND cta.TipMoneda = c.TipMoneda "
                    + "AND cta.NumCuenta = c.NumCuenta "
                    + "WHERE cta.IdPersona='" + idPersona + "' "
                    + "AND cta.TipMoneda='" + tipMoneda + "' "
                    + "AND cta.IdTipCta='Ia' "
                    + "AND LEFT(c.IdEstadoCta,1)='1' ";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TipoPagoInteres objTipoPagoInteres = new TipoPagoInteres();

                CuentaPersona objCuentaPersona = new CuentaPersona();
                Cuenta objCuenta = new Cuenta();

                objCuenta.getObjOficina().setIdOficina(rs.getString("IdOficina"));
                objCuenta.getObjTipoCuenta().setIdTipCta(rs.getString("IdTipCta"));
                objCuenta.getObjTipoCuenta().setTipMoneda(rs.getString("TipMoneda"));
                objCuenta.setNumCuenta(rs.getString("NumCuenta"));
                objCuentaPersona.setTitular(rs.getString("IdPersona"));
                objCuentaPersona.setObjCuenta(objCuenta);

                lstCuentaPersona.add(objCuentaPersona);

            }

        } catch (Exception e) {
            throw e;
        }

        return lstCuentaPersona;
    }
    
    public List<TipoCuenta> obtenerTipoCuentaPlazoFijo(String tipMoneda) throws Exception {

        List<TipoCuenta> lstTipoCuenta = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select DISTINCT tp.IdTipCta,tp.Descripcion from TipoCuenta tp inner join Producto p on tp.IdProducto=p.IdProducto "
                    + "where tp.IdProducto='05' and tp.TipMoneda='"+ tipMoneda +"'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TipoCuenta objCuenta = new TipoCuenta();
                objCuenta.setIdTipCta(rs.getString("IdTipCta"));                 
                objCuenta.setDescripcion(rs.getString("Descripcion"));
                lstTipoCuenta.add(objCuenta);

            }

        } catch (Exception e) {
            throw e;
        }

        return lstTipoCuenta;
    }
    
     public List<TipoCuenta> obtenerTiposCuentaMovCtas(String idProducto, String TipMoneda) throws Exception {
         
         String query;
         
         if(idProducto.equalsIgnoreCase("99")){
             query = " AND IdProducto IN('04','05')";
         }else{
             query = " AND IdProducto="+idProducto;
         }
        List<TipoCuenta> lstTipoCuenta = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT IdTipCta,Descripcion FROM  TIPOCUENTA WHERE TipMoneda="+TipMoneda+" "+query;

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TipoCuenta objCuenta = new TipoCuenta();
                objCuenta.setIdTipCta(rs.getString("IdTipCta"));                 
                objCuenta.setDescripcion(rs.getString("Descripcion"));
                lstTipoCuenta.add(objCuenta);

            }

        } catch (Exception e) {
            throw e;
        }

        return lstTipoCuenta;
    }
    
    

}
