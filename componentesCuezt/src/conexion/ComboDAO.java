package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logico.Combo;
import logico.Componente;

public class ComboDAO {

		public static boolean agregarCombo(Combo combo) {
		Connection conn = Conexion.getInstance().getConexion();
		boolean resultado = false;
		try {
			String sql = "INSERT INTO combos (descuentoPorciento, descripcion) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, combo.getDescuentoPorciento());
			ps.setString(2, combo.getDescripcion());
			ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            int idComboGenerado = rs.getInt(1);

	            String sqlDetalle = "INSERT INTO combo_detalle (id_combo, id_componente, cantidad) VALUES (?,?,?)";
	            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);

	            for (Componente c : combo.getComponentesCombo()) {
	                psDetalle.setInt(1, idComboGenerado);
	                psDetalle.setInt(2, c.getId());
	                psDetalle.setInt(3, c.getCantidad());

	                psDetalle.addBatch();
	            }

	            psDetalle.executeBatch();
	            psDetalle.close();
	            resultado = true;
	        }

	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return resultado;

}
		
		
}