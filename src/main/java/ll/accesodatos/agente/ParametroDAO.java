package ll.accesodatos.agente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author PauPar
 */
public class ParametroDAO {

    private static ParametroDAO _Instancia;

    private ParametroDAO() {
    }

    public static ParametroDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ParametroDAO();
        }
        return _Instancia;
    }

    public Boolean actualizarParametroFacturaElectronica(String nroParam, String param, String idOficina, String tipMoneda) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_ActualizarParametroFactura (?,?,?,?,?)}");

            cs.setString(1, nroParam);
            cs.setString(2, param);
            cs.setString(3, idOficina);
            cs.setString(4, tipMoneda);
            cs.setBoolean(5, result);

            cs.registerOutParameter(5, java.sql.Types.BIT);

            cs.execute();

            result = cs.getBoolean(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean actualizarParametroNotaCreditoElectronica(String nroParam, String param, String idOficina, String tipMoneda) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_ActualizarParametroNotaCredito (?,?,?,?,?)}");

            cs.setString(1, nroParam);
            cs.setString(2, param);
            cs.setString(3, idOficina);
            cs.setString(4, tipMoneda);
            cs.setBoolean(5, result);

            cs.registerOutParameter(5, java.sql.Types.BIT);

            cs.execute();

            result = cs.getBoolean(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean actualizarParametroNotaDebitoElectronica(String nroParam, String param, String idOficina, String tipMoneda) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_ActualizarParametroNotaDebito (?,?,?,?,?)}");

            cs.setString(1, nroParam);
            cs.setString(2, param);
            cs.setString(3, idOficina);
            cs.setString(4, tipMoneda);
            cs.setBoolean(5, result);

            cs.registerOutParameter(5, java.sql.Types.BIT);

            cs.execute();

            result = cs.getBoolean(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean actualizarParametroGuiaRemisionElectronica(String nroParam, String param, String idOficina, String tipMoneda) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_ActualizarParametroGRM (?,?,?,?,?)}");

            cs.setString(1, nroParam);
            cs.setString(2, param);
            cs.setString(3, idOficina);
            cs.setString(4, tipMoneda);
            cs.setBoolean(5, result);

            cs.registerOutParameter(5, java.sql.Types.BIT);

            cs.execute();

            result = cs.getBoolean(5);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }

    public Boolean actualizarParametroGuiaRemisionManual(String param, String idOficina, String tipMoneda) throws Exception {

        boolean result = false;

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {
            CallableStatement cs = objConexion.prepareCall("{call pa_ActualizarParametroGRMMANUAL (?,?,?,?)}");

            cs.setString(1, param);
            cs.setString(2, idOficina);
            cs.setString(3, tipMoneda);
            cs.setBoolean(4, result);

            cs.registerOutParameter(4, java.sql.Types.BIT);

            cs.execute();

            result = cs.getBoolean(4);

        } catch (SQLException e) {
            e.getMessage();
        }

        return result;
    }
}
