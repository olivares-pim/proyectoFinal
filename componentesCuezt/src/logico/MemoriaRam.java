package logico;

public class MemoriaRam extends Componente {
	
	private double cantidadMemoria;
	private String tipoMemoria;

	public MemoriaRam(int id, double precio, int cantidad, String numeroSerie, String marca, String tipo, double cantidadMemoria, String tipoMemoria) {
		super(id, precio, cantidad, numeroSerie, marca, tipo);
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
	
	public boolean verificarConector(TarjetaMadre tarjetaMadre) {
		return this.tipoMemoria == tarjetaMadre.getTipoMemoriaRAM();
	}
	
	@Override
	public String toString() {
		return marca + " " + numeroSerie;
	}
	

}

