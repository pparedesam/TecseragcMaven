package ll.accesodatos.rrhh;

import ll.accesodatos.administracion.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Oficina;
import ll.entidades.rrhh.SistemaPensiones;

/**
 *
 * @author CesGue
 */
public class SistemaPensionesDAO {

    private static SistemaPensionesDAO _Instancia;

    private SistemaPensionesDAO() {
    }

    public static SistemaPensionesDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new SistemaPensionesDAO();
        }
        return _Instancia;
    }

    public List<SistemaPensiones> listarSistemaPensiones() throws Exception {

        List<SistemaPensiones> listaSistemaPensiones = new ArrayList<>();

        try (Connection cn = Connect.Instancia().getConnectBD()) {

            String sql = "Select * from SistemaDePensiones sp where Estado=1 order by NombreSistema";

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SistemaPensiones objSistemaPensiones = new SistemaPensiones();
                objSistemaPensiones.setIdSistema(rs.getString("IdSistema"));
                objSistemaPensiones.setNombre(rs.getString("Nombresistema"));
                listaSistemaPensiones.add(objSistemaPensiones);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaSistemaPensiones;
    }
}
