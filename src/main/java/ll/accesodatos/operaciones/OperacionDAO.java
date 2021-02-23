package ll.accesodatos.operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ll.accesodatos.agente.Connect;
import ll.entidades.administracion.Operacion;


public class OperacionDAO {
	
	public static List<Operacion> obtenerRelacionOperaciones(String idOpe) throws Exception {

		Connection objConexion = Connect.Instancia().getConnectBD();
		Operacion objOperacion = null;
		
		String sql = null;
		
		sql = 	"SELECT M.IdOpe as idOpe, M.Operacion as operacion, RM.Orden as orden " +
				"FROM MaeOperaciones M " +
				"JOIN " +
				"( SELECT IdOpeR,Orden FROM RelaOpe WHERE (RelaOpe.IdOpe = '"+idOpe+"') ) RM " + 
				"ON (M.IdOpe = RM.IdOpeR) ";
		List<Operacion> operacionesList = new ArrayList<>();
		try {
			
			PreparedStatement pstm = objConexion.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) { 
				objOperacion = new Operacion();
				objOperacion.setIdOperacion(rs.getString("idOpe"));
				objOperacion.setNombreOperacion(rs.getString("operacion"));
				objOperacion.setOrden(rs.getInt("orden"));
				
				operacionesList.add(objOperacion);
				
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			objConexion.close();
		}

		return operacionesList;
	}
}
