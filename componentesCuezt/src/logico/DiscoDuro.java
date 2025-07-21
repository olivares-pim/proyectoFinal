package logico;

public class DiscoDuro extends Componente {
	
	private String modelo;
	private String capacidadAlmacenamiento;
	private String tipoConectorDD;

	public DiscoDuro(String precio, int cantidad, String numeroSerie, String marca, String modelo, String capacidadAlmacenamiento, String tipoConectorDD) {
		super(precio, cantidad, numeroSerie, marca);
		this.modelo = modelo;
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
		this.tipoConectorDD = tipoConectorDD;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCapacidadAlmacenamiento() {
		return capacidadAlmacenamiento;
	}

	public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}

	public String getTipoConectorDD() {
		return tipoConectorDD;
	}

	public void setTipoConectorDD(String tipoConectorDD) {
		this.tipoConectorDD = tipoConectorDD;
	}
	
	public boolean verificarConector(TarjetaMadre tarjetaMadre) {
		return this.tipoConectorDD == tarjetaMadre.getTipoConectorDD();
	}
	

}
