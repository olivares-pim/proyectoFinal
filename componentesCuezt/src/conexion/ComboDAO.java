package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logico.Combo;

public class ComboDAO {

		public static boolean agregarCombo(Combo combo) {
		Connection conn = Conexion.getInstance().getConexion();
		boolean resultado = false;
		try {
			String sql = "INSERT INTO combos (descuentoPorciento) VALUES (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, combo.getDescuentoPorciento());
			ps.executeUpdate();
			resultado = true;
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();

}
		return resultado;
}
		
}