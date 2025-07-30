package logico;

import java.util.ArrayList;

import Excepciones.ObjectAlreadyExistsException;
import conexion.ClienteDAO;
import conexion.ComboDAO;

public class Tienda {
	
	
	private ArrayList<Componente> componentes;
	private ArrayList<Cliente> clientes;
	private ArrayList<Factura> facturas;
	private ArrayList<Combo> combos;
	private ArrayList<Usuario> usuarios;
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
		this.usuarios = new ArrayList<>();
		this.usuarios.add(new Usuario("admin","1234","Admin"));
		this.usuarios.add(new Usuario("root","root","Registrador"));
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
	public ArrayList<Combo> getCombos() {
		return combos;
	}

	public boolean existeComponente(Componente componenteBuscado) {
		for(Componente componente : componentes){
	        if(componenteBuscado.getNumeroSerie().equalsIgnoreCase(componente.getNumeroSerie())){
	            return true;
	        }
	    }
	    return false;
	}
	
	public void agregarComponente (Componente nuevoComponente) throws ObjectAlreadyExistsException{
	    	if(existeComponente(nuevoComponente)) {
	    		throw new ObjectAlreadyExistsException("componente");
	    	} else {
	    		this.componentes.add(nuevoComponente);
		        this.generadorComponente++;
	    	}
	    
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
            if(clienteBuscado.getCedula().equals(cliente.getCedula())){
                return true;
            }
        }
        return false;
    }
	
	public void agregarCliente(Cliente nuevoCliente) throws ObjectAlreadyExistsException{
        if(existeCliente(nuevoCliente)){
        	throw new ObjectAlreadyExistsException("cliente");
        } else {
        	this.clientes.add(nuevoCliente);
            ClienteDAO.agregarCliente(nuevoCliente);
            this.generadorCliente++;
        }
        
    }

	public boolean existeFactura(Factura facturaBuscada){
	    for(Factura factura : facturas){
	        if(facturaBuscada.getId_factura()==(factura.getId_factura())){
	            return true;
	        }
	    }
	    return false;
	}
	
	public void agregarFactura(Factura nuevaFactura) throws ObjectAlreadyExistsException{
	    if(existeFactura(nuevaFactura)){
	    	throw new ObjectAlreadyExistsException("factura");
	    } else {
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
	        
	    }
	    
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
	
	public void agregarcombo (Combo comboNuevo) throws ObjectAlreadyExistsException {
		if(existeCombo(comboNuevo)) {
			throw new ObjectAlreadyExistsException("combo");
		} else {
			if(ComboDAO.agregarCombo(comboNuevo))
				this.combos.add(comboNuevo);
				this.generadorCombo++;
		}
		
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
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public boolean existeUsuario(Usuario userEval) {
		for(Usuario user : usuarios) {
			if(user.getUsername().equals(userEval.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarUsuario(Usuario userNuevo) throws ObjectAlreadyExistsException {
		if(existeUsuario(userNuevo)) {
			throw new ObjectAlreadyExistsException("nombre de usuario");			
		} else {
			usuarios.add(userNuevo);
		}
	}
	
	public Usuario getUserByUsername(String username) {
		for(Usuario user : usuarios) {
			if(user.getUsername().equals(username)) {
			return user;
			}
		}
		return null;
	}

	public boolean login(String username, String password) {
		Usuario user = getUserByUsername(username);
		if(user!=null && user.getUsername().equals(username)&&user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}