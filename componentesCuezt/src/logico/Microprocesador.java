package logico;

public class Microprocesador extends Componente {
	
	private String modelo;
	private String tipoConectorSocket;
	private String velocidadProcesador;

	public Microprocesador(String precio, int cantidad, String numeroSerie, String marca, String modelo, String tipoConectorSocket, String velocidadProcesador) {
		super(precio, cantidad, numeroSerie, marca);
		this.modelo = modelo;
		this.tipoConectorSocket = tipoConectorSocket;
		this.velocidadProcesador = velocidadProcesador;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipoConectorSocket() {
		return tipoConectorSocket;
	}

	public void setTipoConectorSocket(String tipoConectorSocket) {
		this.tipoConectorSocket = tipoConectorSocket;
	}

	public String getVelocidadProcesador() {
		return velocidadProcesador;
	}

	public void setVelocidadProcesador(String velocidadProcesador) {
		this.velocidadProcesador = velocidadProcesador;
	}
	
	

}
