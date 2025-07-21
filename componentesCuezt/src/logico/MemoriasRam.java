package logico;

public class MemoriasRam extends Componente {
	
	private double cantidadMemoria;
	private String tipoMemoria;

	public MemoriasRam(String precio, int cantidad, String numeroSerie, String marca, double cantidadMemoria, String tipoMemoria) {
		super(precio, cantidad, numeroSerie, marca);
		this.cantidadMemoria = cantidadMemoria;
		this.tipoMemoria = tipoMemoria;
	}

	public double getCantidadMemoria() {
		return cantidadMemoria;
	}

	public void setCantidadMemoria(double cantidadMemoria) {
		this.cantidadMemoria = cantidadMemoria;
	}

	public String getTipoMemoria() {
		return tipoMemoria;
	}

	public void setTipoMemoria(String tipoMemoria) {
		this.tipoMemoria = tipoMemoria;
	}
	

}
