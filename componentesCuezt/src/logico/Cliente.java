package logico;

public class Cliente {
	private int id_cliente;
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	public Cliente(int id_cliente, String cedula, String nombre, String apellido, String email, String telefono) {
		this.id_cliente = id_cliente;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	
}
