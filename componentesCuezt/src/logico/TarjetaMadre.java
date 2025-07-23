package logico;

public class TarjetaMadre extends Componente {
	
	private String modelo;
	private String tipoConectorSocket;
	private String tipoMemoriaRAM;
	private String tipoConectorDD;

	public TarjetaMadre(int id ,double precio, int cantidad, String numeroSerie, String marca, String modelo, String tipoConectorSocket, String tipoMemoriaRAM, String tipoConectorDD) {
		super(id, precio, cantidad, numeroSerie, marca);
		this.modelo = modelo;
		this.tipoConectorSocket = tipoConectorSocket;
		this.tipoMemoriaRAM = tipoMemoriaRAM;
		this.tipoConectorDD = tipoConectorDD;
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

	public String getTipoMemoriaRAM() {
		return tipoMemoriaRAM;
	}

	public void setTipoMemoriaRAM(String tipoMemoriaRAM) {
		this.tipoMemoriaRAM = tipoMemoriaRAM;
	}

	public String getTipoConectorDD() {
		return tipoConectorDD;
	}

	public void setTipoConectorDD(String tipoConectorDD) {
		this.tipoConectorDD = tipoConectorDD;
	}
	
	@Override
	public String toString() {
		return marca + " " + modelo;
	}
	
}
