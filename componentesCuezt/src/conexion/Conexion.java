package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	private static Conexion instancia;
	private Connection conexion;
	
	private final String URL = "jdbc:mysql://localhost:3306/componentesCuezt";
	private final String USER = "root";
	private final String PASSWORD = "123456";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private Conexion() {
		try {Class.forName(DRIVER);
		conexion = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Conexion establecida con exito");
		}
		catch (ClassNotFoundException | SQLException e){
			System.err.println("Error al conectar con la base de dato:");
			e.printStackTrace();
		}
	}
	
	public static Conexion getInstance() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void cerrarConexion(){
		try {
			if (conexion != null && !conexion.isClosed()){
				conexion.close();
				System.out.println("Conexion cerrada correctamente");
			}
		}
		catch (SQLException e) {
			System.err.println("Error al cerrar la conexion:");
			e.printStackTrace();
		}
	}

}
