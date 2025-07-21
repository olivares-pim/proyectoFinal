package logico;

public abstract class Componente {
	protected String precio;
	protected int cantidad;
	protected String numeroSerie;
	protected String marca;
	
	public Componente(String precio, int cantidad, String numeroSerie, String marca) {
		this.precio = precio;
		this.cantidad = cantidad;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
