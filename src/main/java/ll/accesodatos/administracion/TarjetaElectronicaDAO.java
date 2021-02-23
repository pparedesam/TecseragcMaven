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
import ll.entidades.administracion.TarjetaElectronica;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author Andrew
 */
public class TarjetaElectronicaDAO {

    private static TarjetaElectronicaDAO _Instancia;

    private TarjetaElectronicaDAO() {
    }

    public static TarjetaElectronicaDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TarjetaElectronicaDAO();
        }
        return _Instancia;
    }

    public List<TarjetaElectronica> obtenerTarjetaxPersona(String idPersona) throws Exception {

        List<TarjetaElectronica> listTarjetaElectronica = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT rtrim(Aleatorio) as Aleatorio, rtrim(Clave) as Clave, Estado  from TarjetaElectronica where IdPersona = '" + idPersona + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TarjetaElectronica objTarjetaElectronica = new TarjetaElectronica();
                objTarjetaElectronica.setNroTarjeta(rs.getString("Aleatorio"));
                objTarjetaElectronica.setClave(rs.getString("Clave"));
                objTarjetaElectronica.setEstado(rs.getString("Estado"));

                listTarjetaElectronica.add(objTarjetaElectronica);
            }

        } catch (Exception e) {
            throw e;
        }

        return listTarjetaElectronica;
    }

    public String registrarTarjetaElectronica(TarjetaElectronica objTarjetaElectronica, Usuario objUsuario, String accion) throws Exception {
        String resultado;

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            CallableStatement cs = cn.prepareCall("{call sch_Afiliaciones_sp_ins_upd_TarjetaElectronica(?,?,?,?,?,?,?,?)}");

            cs.setString(1, objTarjetaElectronica.getObjPersona().getIdPersona());
            cs.setString(2, objUsuario.getObjEmpleado().getObjOficina().getIdOficina());
            cs.setString(3, objTarjetaElectronica.getNroTarjeta());
            cs.setString(4, objTarjetaElectronica.getClave());
            cs.setString(5, objTarjetaElectronica.getEstado());
            cs.setString(6, objUsuario.getIdUsuario());
            cs.setString(7, accion);
            cs.registerOutParameter(8, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            resultado = cs.getString(8);

        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    public int anular(TarjetaElectronica objTarjetaElectronica) throws Exception {
        int result = 0;
        Connection objConexion = Connect.Instancia().getConnectBD();

        try {

            String sql = "update TarjetaElectronica set Estado = 2,EstadoBloqueo = ? where Aleatorio = ?";
            PreparedStatement pstm = objConexion.prepareStatement(sql);
            pstm.setString(1, objTarjetaElectronica.getEstadoBloqueo());
            pstm.setString(2, objTarjetaElectronica.getNroTarjeta());

            pstm.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            result = 1;
            objConexion.close();
        }

        return result;

    }

    public String actualizarContrasenia(String idPersona, String Clave, String nuevaClave, String nroTarjeta) throws Exception {

        String resultado;
        try (Connection cn = Connect.Instancia().getConnectBD();) {

            CallableStatement cs = cn.prepareCall("{call sch_Afiliaciones_sp_upd_claveTarjetaElectronica(?,?,?,?,?)}");
            cs.setString(1, Clave);
            cs.setString(2, idPersona);
            cs.setString(3, nuevaClave);
            cs.setString(4, nroTarjeta);
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            resultado = cs.getString(5);
            cs.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        return resultado;

    }

}
