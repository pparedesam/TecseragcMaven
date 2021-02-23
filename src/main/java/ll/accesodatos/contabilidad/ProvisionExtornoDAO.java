package ll.accesodatos.contabilidad;

import ll.entidades.agentes.ProvisionExtorno;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;


public class ProvisionExtornoDAO {

    private static ProvisionExtornoDAO _Instancia;

    private ProvisionExtornoDAO() {
    }

    public static ProvisionExtornoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new ProvisionExtornoDAO();
        }
        return _Instancia;
    }

    public List<ProvisionExtorno> ObtenerProvisionExtorno(String idDocumento, String nroDocEXT, String idOficinaEXT, String tipMonedaEXT)
            throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();

        List<ProvisionExtorno> lstProvisionExtorno = new ArrayList<>();

        try {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_sp_Vista_Extorno_Provision (?,?,?,?)}");

            cstmt.setString(1, idDocumento);
            cstmt.setString(2, nroDocEXT);
            cstmt.setString(3, idOficinaEXT);
            cstmt.setString(4, tipMonedaEXT);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                ProvisionExtorno objProvisionExtorno = new ProvisionExtorno();
                objProvisionExtorno.setCuota(rs.getString("Cuota"));
                objProvisionExtorno.setMontoProvi(rs.getDouble("MontoProvi"));
                objProvisionExtorno.setMontoProviExt(rs.getDouble("MontoExt"));
                objProvisionExtorno.setDiferencia(rs.getDouble("Diferencia"));
                lstProvisionExtorno.add(objProvisionExtorno);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            objConexion.close();
        }

        return lstProvisionExtorno;

    }

    public Boolean UpdateProvisionExtorno(String idDocumento, String nroDocEXT, String idOficinaEXT, String tipMonedaEXT) throws Exception {

        Connection objConexion = Connect.Instancia().getConnectBD();
        Boolean result = false;

        try {

            CallableStatement cstmt = objConexion.prepareCall("{call sch_Contabilidad_sp_Extorno_Provision (?,?,?,?)}");

            cstmt.setString(1, idDocumento);
            cstmt.setString(2, nroDocEXT);
            cstmt.setString(3, idOficinaEXT);
            cstmt.setString(4, tipMonedaEXT);

            result = cstmt.execute();

        } catch (Exception e) {

            throw e;

        } finally {
            objConexion.close();

        }
        return result;
    }

}
