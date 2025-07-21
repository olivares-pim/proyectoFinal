package logico;

import java.util.ArrayList;

public class Combo {
	
	private String id;
	//private TarjetaMadre compCentral;
	private String descripcion;
	private ArrayList<Componente> componentesCombo;
	public Combo(String id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.componentesCombo = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<Componente> getComponentesCombo() {
		return componentesCombo;
	}
	/* Comprobacion de tipo conector. Revisa si existe una tarjeta madre ya, si no tiene agrega componentes sin problema.
	 *  Si tiene utiliza el metodo verificarConector que esta en todos los componentes menos tarjeta madre.*/
	 
	 public TarjetaMadre existeTarjetaMadre() {
		TarjetaMadre madreAux = null;
		for(Componente componente : componentesCombo) {
			if(componente instanceof TarjetaMadre) {
				madreAux = (TarjetaMadre) componente;
				break;
			}
		}
		return madreAux;
	}
	
	public boolean ingresarComponente(Componente componenteAgregar) {
		TarjetaMadre madreAux = existeTarjetaMadre();
		
		if(madreAux!= null) {
				if(componenteAgregar instanceof TarjetaMadre) {
					return false;
				}else if(componenteAgregar instanceof DiscoDuro) {
					((DiscoDuro) componenteAgregar).verificarConector(madreAux);
					return true;
				}else if(componenteAgregar instanceof MemoriasRam) {
					((MemoriasRam) componenteAgregar).verificarConector(madreAux);
					return true;
				}else if(componenteAgregar instanceof Microprocesador) {
					((Microprocesador) componenteAgregar).verificarConector(madreAux);
					return true;
				}
			} else {
				this.componentesCombo.add(componenteAgregar);
				return true;
			}
		return false;
		}
		
}
