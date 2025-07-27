package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logico.Cliente;

public class ClienteDAO {
	public static boolean agregarCliente (Cliente clientes) {
		Connection conn = Conexion.getInstance().getConexion();
		boolean resultado = false;
		try {
			String sql = "INSERT INTO clientes (cedula, nombre, apellido, email, telefono) VALUES (?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, clientes.getCedula());
			ps.setString(2, clientes.getNombre());
			ps.setString(3, clientes.getApellido());
			ps.setString(4, clientes.getEmail());
			ps.setString(5, clientes.getTelefono());
			ps.executeUpdate();
			resultado = true;
			ps.close();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
		
	}
}
