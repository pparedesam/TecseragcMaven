package ll.accesodatos.administracion;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Puesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RenRio
 */
public class PuestoDAO {

    private static PuestoDAO _Instancia;

    private PuestoDAO() {
    }

    public static PuestoDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new PuestoDAO();
        }
        return _Instancia;
    }

    public List<Puesto> obtenerPstoxDptoxOficina(String idOficina, String idDpto) throws Exception {

        List<Puesto> listPuesto = new ArrayList<>();

        try (Connection objConexion = Connect.Instancia().getConnectBD();) {

            String sql = "select distinct psto.IdPuesto,psto.Nombre from PuestosxDptoxOficina \n"
                    + " inner join Dpto on dpto.IdDpto = PuestosxDptoxOficina.IdDpto\n"
                    + "	inner join Puesto psto on psto.IdPuesto = PuestosxDptoxOficina.IdPuesto\n"
                    + " where PuestosxDptoxOficina.IdOficina='" + idOficina + "' and PuestosxDptoxOficina.IdDpto='" + idDpto + "'";

            PreparedStatement pstm = objConexion.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Puesto objPuesto = new Puesto();
                objPuesto.setIdPuesto(rs.getString("IdPuesto"));
                objPuesto.setNombre(rs.getString("Nombre"));

                listPuesto.add(objPuesto);
            }

        } catch (Exception e) {
            throw e;
        }

        return listPuesto;
    }
}
