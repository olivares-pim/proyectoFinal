package logico;

import java.util.ArrayList;

public class Tienda {
	
	
	private ArrayList<Componente> componentes;
	private ArrayList<Cliente> clientes;
	private ArrayList<Factura> facturas;
	private ArrayList<Combo> combos;
	private static int generadorComponente = 1;
	private static int generadorCliente = 1;
	private static int generadorFactura = 1;
	private static int generadorCombo = 1;
	private static Tienda tiendaTon = null; //Singleton
	
	public static Tienda getInstance() {
		if(tiendaTon == null) {
			tiendaTon = new Tienda();
		}
		return tiendaTon;
	}
	
	private Tienda() {
		this.componentes = new ArrayList<>();
		this.clientes = new ArrayList<>(); 
		this.facturas = new ArrayList<>();
	}
	public ArrayList<Componente> getComponentes() {
		return componentes;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	
	public boolean existeComponente(Componente componenteBuscado){
	    for(Componente componente : componentes){
	        if(componenteBuscado.getNumeroSerie().equalsIgnoreCase(componente.getNumeroSerie())){
	            return true;
	        }
	    }
	    return false;
	}

	public boolean agregarComponente(Componente nuevoComponente){
	    if(!existeComponente(nuevoComponente)){
	        this.componentes.add(nuevoComponente);
	        this.generadorComponente++;
	        return true;
	    }
	    return false;
	}
	
	public boolean existeCliente(Cliente clienteBuscado){
        for(Cliente cliente : clientes){
            if(clienteBuscado.getCedula().equalsIgnoreCase(cliente.getCedula())){
                return true;
            }
        }
        return false;
    }
	
	public boolean agregarCliente(Cliente nuevoCliente){
        if(!existeCliente(nuevoCliente)){
            this.clientes.add(nuevoCliente);
            this.generadorCliente++;
            return true;
        }
        return false;
    }

	public boolean existeFactura(Factura facturaBuscada){
	    for(Factura factura : facturas){
	        if(facturaBuscada.getId().equalsIgnoreCase(factura.getId())){
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean agregarFactura(Factura nuevaFactura){
	    if(!existeFactura(nuevaFactura)){
	        this.facturas.add(nuevaFactura);
	        this.generadorFactura++;
	        return true;
	    }
	    return false;
	}
	
	
	

}
