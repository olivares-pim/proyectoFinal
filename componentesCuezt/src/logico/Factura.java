package logico;

public class Factura {
	
	private int id_factura;
	private int id_cliente;
	private int id_combo;
	private int fechaPedido;
	private float precioTotal;
	public int getId_factura() {
		return id_factura;
	}
	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_combo() {
		return id_combo;
	}
	public void setId_combo(int id_combo) {
		this.id_combo = id_combo;
	}
	public int getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(int fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	
}


