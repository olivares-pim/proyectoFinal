package logico;

import java.util.ArrayList;

public class Combo {
	
	private int id;
	private String descripcion;
	private double descuentoPorciento;
	private ArrayList<Componente> componentesCombo;
	private TarjetaMadre compCentral;
	
	public Combo(int id, String descripcion, double descuentoPorciento) {
		this.id = id;
		this.descripcion = descripcion;
		this.setDescuentoPorciento(descuentoPorciento);
		this.componentesCombo = new ArrayList<>();
	}
	
	public Combo(int id, String descripcion, TarjetaMadre compCentral, double descuentoPorciento) {
		this.id = id;
		this.descripcion = descripcion;
		this.setDescuentoPorciento(descuentoPorciento);
		this.componentesCombo = new ArrayList<>();
		this.compCentral = compCentral;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TarjetaMadre getCompCentral() {
		return compCentral;
	}
	public void setCompCentral(TarjetaMadre compCentral) {
		this.compCentral = compCentral;
	}
	public ArrayList<Componente> getComponentesCombo() {
		return componentesCombo;
	}
	public void ingresarComponente(Componente compNuevo) {
		this.componentesCombo.add(compNuevo);
	}

	public double getDescuentoPorciento() {
		return descuentoPorciento;
	}

	public void setDescuentoPorciento(double descuentoPorciento) {
		this.descuentoPorciento = descuentoPorciento;
	}
	
	@Override
	public String toString(){
		return descripcion + " " + descuentoPorciento + "%";
	}
	
		
}
