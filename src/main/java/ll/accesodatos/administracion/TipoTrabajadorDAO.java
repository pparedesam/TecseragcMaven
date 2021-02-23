package ll.accesodatos.administracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.TipoTrabajador;

/**
 *
 * @author paupar
 */
public class TipoTrabajadorDAO {

    private static TipoTrabajadorDAO _Instancia;

    private TipoTrabajadorDAO() {
    }

    public static TipoTrabajadorDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new TipoTrabajadorDAO();
        }
        return _Instancia;
    }
    
        public List<TipoTrabajador> listar() throws Exception {

        List<TipoTrabajador> lstTipoTrabajador = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "SELECT IdTipoTrabajador, Descripcion, Estado from TipoTrabajador where Estado=1";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                TipoTrabajador objTipoTrabajador = new TipoTrabajador();
                objTipoTrabajador.setCodigo(rs.getString("IdTipoTrabajador"));
                objTipoTrabajador.setDescripcion(rs.getString("Descripcion"));
                objTipoTrabajador.setEstado(rs.getBoolean("Estado"));

                lstTipoTrabajador.add(objTipoTrabajador);
            }

        } catch (Exception e) {
            throw e;
        }

        return lstTipoTrabajador;
    }


}
