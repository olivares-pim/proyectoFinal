package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logico.Combo;
import logico.Componente;
import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Microprocesador;
import logico.TarjetaMadre;

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
		
		public static boolean actualizarCombo(Combo combo) {
		Connection conn = Conexion.getInstance().getConexion();
		boolean resultado = false;
		try {
			String sql = "UPDATE combos SET descuentoPorciento=?, descripcion=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, combo.getDescuentoPorciento());
			ps.setString(2, combo.getDescripcion());
			ps.setInt(3, combo.getId());
			ps.executeUpdate();
			resultado = true;
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
		

		public static Combo getCombo(int id) {
			Connection conn = Conexion.getInstance().getConexion();
			Combo combo = null;

			try {
				String sql = "SELECT * FROM combos WHERE id=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					combo = new Combo(id, rs.getString("descripcion"), rs.getDouble("descuentoPorciento"));

					String sqlDetalle =
						"SELECT cd.cantidad, c.id_componente, c.tipoComponente, c.precio, c.marca, c.numeroSerie " +
						"FROM combo_detalle cd " +
						"JOIN componente c ON cd.id_componente = c.id_componente " +
						"WHERE cd.id_combo = ?";

					PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
					psDetalle.setInt(1, combo.getId());
					ResultSet rsDetalle = psDetalle.executeQuery();

					while (rsDetalle.next()) {
						int idComp = rsDetalle.getInt("id_componente");
						String tipo = rsDetalle.getString("tipoComponente");
						double precio = rsDetalle.getDouble("precio");
						String marca = rsDetalle.getString("marca");
						String numeroSerie = rsDetalle.getString("numeroSerie");
						int cantidad = rsDetalle.getInt("cantidad");

						String modelo = null;

						String tablaModelo;
						switch (tipo) {
						case "DiscoDuro":
							tablaModelo = "discoDuro";
							break;
						case "MemoriaRam":
							tablaModelo = "memoriasRam";
							break;
						case "Microprocesador":
							tablaModelo = "microprocesadores";
							break;
						case "TarjetaMadre":
							tablaModelo = "tarjetaMadre";
							break;
						default:
							tablaModelo = null;
							break;
					}

						if (tablaModelo != null) {
							String sqlModelo = "SELECT modelo FROM " + tablaModelo + " WHERE id_componente = ?";
							PreparedStatement psModelo = conn.prepareStatement(sqlModelo);
							psModelo.setInt(1, idComp);
							ResultSet rsModelo = psModelo.executeQuery();

							if (rsModelo.next()) {
								modelo = rsModelo.getString("modelo");
							}

							rsModelo.close();
							psModelo.close();
						}
						
						Componente componente = null;

						switch (tipo) {
							case "DiscoDuro":
								componente = new DiscoDuro(idComp, precio, cantidad, numeroSerie, marca, tipo, modelo, "", "");
								break;
							case "MemoriaRam":
								componente = new MemoriaRam(idComp, precio, cantidad, numeroSerie, marca, tipo,0,"");
								break;
							case "Microprocesador":
								componente = new Microprocesador(idComp, precio, cantidad, numeroSerie, marca, tipo, modelo, "", "");
								break;
							case "TarjetaMadre":
								componente = new TarjetaMadre(idComp, precio, cantidad, numeroSerie, marca, tipo, modelo, "", "", "");
								break;
						}

						if (componente != null) {
							combo.ingresarComponente(componente);
						}
					}

					rsDetalle.close();
					psDetalle.close();
				}

				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return combo;
		}

		
}