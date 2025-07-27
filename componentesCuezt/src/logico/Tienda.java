package logico;

import java.util.ArrayList;

import conexion.ClienteDAO;
import conexion.ComboDAO;

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
	
	public Componente getComponente(Componente compBuscado) {
		for(Componente comp : componentes) {
			if(compBuscado.getId()==comp.getId()) {
				return comp;
			}
		}
		return null;
	}
	
	public boolean existeCliente(Cliente clienteBuscado){
        for(Cliente cliente : clientes){
            if(clienteBuscado.getId_cliente()==cliente.getId_cliente()){
                return true;
            }
        }
        return false;
    }
	
	public boolean agregarCliente(Cliente nuevoCliente){
        if(!existeCliente(nuevoCliente)){
            this.clientes.add(nuevoCliente);
            ClienteDAO.agregarCliente(nuevoCliente);
            this.generadorCliente++;
            return true;
        }
        return false;
    }

	public boolean existeFactura(Factura facturaBuscada){
	    for(Factura factura : facturas){
	        if(facturaBuscada.getId_factura()==(factura.getId_factura())){
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean agregarFactura(Factura nuevaFactura){
	    if(!existeFactura(nuevaFactura)){
	        this.facturas.add(nuevaFactura);
	        for(Componente comp : nuevaFactura.getComponentesVendidos()) {
	        	actualizarCantidadProducto(comp);
	        }
	        for(Combo combo : nuevaFactura.getCombosVendidos()) {
	        	for(Componente comp : combo.getComponentesCombo()) {
	        		actualizarCantidadProducto(comp);
	        	}
	        }
	        this.generadorFactura++;
	        return true;
	    }
	    return false;
	}

	public void actualizarCantidadProducto(Componente compVendido) {
		Componente compAlmacenado = getComponente(compVendido);
		if(compAlmacenado !=null){
			compAlmacenado.setCantidad(compAlmacenado.getCantidad()-compVendido.getCantidad());
			}
	}
	
	public boolean existeCombo(Combo comboEval) {
		for(Combo combo : combos) {
			if(combo.getId()==comboEval.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean agregarcombo (Combo comboNuevo) {
		if(!existeCombo(comboNuevo)) {
			if(ComboDAO.agregarCombo(comboNuevo))
			this.combos.add(comboNuevo);
			this.generadorCombo++;
			return true;
		}
		return false;
	}
	
	public ArrayList<TarjetaMadre> getTarjetasMadres() {
		ArrayList<TarjetaMadre> tarjetasMadres = new ArrayList<TarjetaMadre>();
		for(Componente componente : componentes) {
			if(componente instanceof TarjetaMadre) {
				tarjetasMadres.add((TarjetaMadre)componente);
			}
		}
		return tarjetasMadres;
	}

	
	public ArrayList<MemoriaRam> getMemoriasRam() {
		ArrayList<MemoriaRam> memoriasRam = new ArrayList<MemoriaRam>();
		for(Componente componente : componentes) {
			if(componente instanceof MemoriaRam) {
				memoriasRam.add((MemoriaRam)componente);
			}
		}
		return memoriasRam;
	}
	
	public ArrayList<MemoriaRam> getMemoriasRamCompatibles(TarjetaMadre tarjetaEval){
		ArrayList<MemoriaRam> memoriasAux = new ArrayList<MemoriaRam>();
		for(MemoriaRam ram : getMemoriasRam()) {
			if(ram.verificarConector(tarjetaEval)){
				memoriasAux.add(ram);
			}
		}
		return memoriasAux;
	}
	
	public ArrayList<Microprocesador> getMicroProcesadores() {
		ArrayList<Microprocesador> microProcesadores = new ArrayList<Microprocesador>();
		for(Componente componente : componentes) {
			if(componente instanceof Microprocesador) {
				microProcesadores.add((Microprocesador)componente);
			}
		}
		return microProcesadores;
	}
	
	public ArrayList<Microprocesador> getMicroProcesadoresCompatibles(TarjetaMadre tarjetaEval){
		ArrayList<Microprocesador> microProcesadoresAux = new ArrayList<Microprocesador>();
		for(Microprocesador mP : getMicroProcesadores()) {
			if(mP.verificarConector(tarjetaEval)){
				microProcesadoresAux.add(mP);
			}
		}
		return microProcesadoresAux;
	}
	
	public ArrayList<DiscoDuro> getDiscosDuros(){
		ArrayList<DiscoDuro> discosDuros = new ArrayList<DiscoDuro>();
		for(Componente componente : componentes) {
			if(componente instanceof DiscoDuro) {
				discosDuros.add((DiscoDuro)componente);
			}
		}
		return discosDuros;
	}
	
	public ArrayList<DiscoDuro> getDiscosDurosCompatibles(TarjetaMadre tarjetaEval){
		ArrayList<DiscoDuro>discosDurosAux = new ArrayList<DiscoDuro>();
		for(DiscoDuro dd : getDiscosDuros()) {
			if(dd.verificarConector(tarjetaEval)) {
				discosDurosAux.add(dd);
			}
		}
		return discosDurosAux;
	}
	
	

}
