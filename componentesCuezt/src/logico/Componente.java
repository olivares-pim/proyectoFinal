package logico;

public abstract class Componente {
	
	protected int id;
	protected double precio;
	protected int cantidad;
	protected String numeroSerie;
	protected String marca;
	
	protected Componente(int id, double precio, int cantidad, String numeroSerie, String marca) {
		this.id = id;
		this.precio = precio;
		this.cantidad = cantidad;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
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
	
	@Override
	public abstract String toString();
}
