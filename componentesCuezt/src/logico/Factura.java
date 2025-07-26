package logico;

import java.util.ArrayList;

public class Factura {
	
	private int id_factura;
	private Cliente cliente;
	private int fechaPedido;
	private double precioTotal;
	private ArrayList<Componente> componentesVendidos;
	private ArrayList<Combo> combosVendidos;
	
	public Factura(int id_factura, Cliente cliente, int fechaPedido, double precioTotal) {
		
		this.id_factura = id_factura;
		this.cliente = cliente;
		this.fechaPedido = fechaPedido;
		this.precioTotal = precioTotal;
		this.componentesVendidos = new ArrayList<>();
		this.combosVendidos = new ArrayList<>();
	}
	public int getId_factura() {
		return id_factura;
	}
	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public int getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(int fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public ArrayList<Componente> getComponentesVendidos() {
		return componentesVendidos;
	}
	public ArrayList<Combo> getCombosVendidos() {
		return combosVendidos;
	}
	
	private void ingresarComponente(Componente componentaVendido) {
		componentesVendidos.add(componentaVendido);
	}
	
	private void ingresarCombo(Combo comboVendido) {
		combosVendidos.add(comboVendido);
	}

	
}

