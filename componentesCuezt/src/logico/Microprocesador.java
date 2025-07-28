package logico;

public class Microprocesador extends Componente {
	
	private String modelo;
	private String tipoConectorSocket;
	private String velocidadProcesador;

	public Microprocesador(int id, double precio, int cantidad, String numeroSerie, String marca, String tipo, String modelo, String tipoConectorSocket, String velocidadProcesador) {
		super(id, precio, cantidad, numeroSerie, marca, tipo);
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
	
	public boolean verificarConector(TarjetaMadre tarjetaMadre) {
		return this.tipoConectorSocket == tarjetaMadre.getTipoConectorSocket();
	}
	
	@Override
	public String toString() {
		return marca + " " + modelo;
	}

}
