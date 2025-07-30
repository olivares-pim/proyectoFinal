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
	public static int generadorComponente = 1;
	public static int generadorCliente = 1;
	public static int generadorFactura = 1;
	public static int generadorCombo = 1;
	private static Tienda tiendaTon = null; //Singleton
	
	public static Tienda getInstance() {
		if(tiendaTon == null) {
			tiendaTon = new Tienda();
		}
		return tiendaTon;
	}
	
	private Tienda() {
		//DONT FORGET TO UNCOMMENT THE DATABASE CONNECTIONS IN CLIENTE AND COMBO
		this.componentes = new ArrayList<>();
		this.clientes = new ArrayList<>(); 
		this.facturas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.combos = new ArrayList<>();
		try {
			crearObjetosPrueba();
		} catch (ObjectAlreadyExistsException e) {
			e.printStackTrace();
		}
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
            //ClienteDAO.agregarCliente(nuevoCliente);
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
	
	public void agregarCombo (Combo comboNuevo) throws ObjectAlreadyExistsException {
		if(existeCombo(comboNuevo)) {
			throw new ObjectAlreadyExistsException("combo");
		} else {
			//if(ComboDAO.agregarCombo(comboNuevo))
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
	
	//BORRAR ESTO ANTES DE TERMINAR PROYECTO ESTO ES PARA PRUEBAS TAMBIEN INCLUIR LAS LINEAS DEBAJS DEL INCIO DEL CONSTRUCTOR
	
		public void crearObjetosPrueba() throws ObjectAlreadyExistsException {
			this.usuarios.add(new Usuario("admin","1234","Admin"));
			this.usuarios.add(new Usuario("root","root","Registrador"));
			for(TarjetaMadre tM : crearTarjetasMadre()) {
				agregarComponente(tM);
			}
			for(Microprocesador cpu : crearMicroprocesadores()) {
				agregarComponente(cpu);
			}
			for(DiscoDuro hdd : crearDiscosDuros()) {
				agregarComponente(hdd);
			}
			for(MemoriaRam ram : crearMemoriasRAM()) {
				agregarComponente(ram);
			}
			for(Combo combo : crearCombosBasicos()) {
				agregarCombo(combo);
			}
			for(Cliente cliente : crearClientesPrueba()) {
				agregarCliente(cliente);
			}
			
		}
		
		public ArrayList<Cliente> crearClientesPrueba() {
		    ArrayList<Cliente> clientes = new ArrayList<>();
		    
		    // Cliente 1 - Profesional TI
		    clientes.add(new Cliente(1, "001-1234567-8", "Juan", "P�rez", "juan.perez@email.com", "809-555-0101"));
		    
		    // Cliente 2 - Estudiante Universitario
		    clientes.add(new Cliente(2, "002-2345678-9", "Mar�a", "Garc�a", "maria.garcia@universidad.edu", "809-555-0202"));
		    
		    // Cliente 3 - Empresario
		    clientes.add(new Cliente(3, "003-3456789-0", "Carlos", "Rodr�guez", "c.rodriguez@empresa.com", "809-555-0303"));
		    
		    // Cliente 4 - Dise�ador Gr�fico
		    clientes.add(new Cliente(4, "004-4567890-1", "Ana", "Mart�nez", "ana.design@creative.com", "809-555-0404"));
		    
		    // Cliente 5 - Streamer/Gamer
		    clientes.add(new Cliente(5, "005-5678901-2", "Luis", "Hern�ndez", "luis.gamer@stream.tv", "829-555-0505"));
		    
		    // Cliente 6 - Profesor
		    clientes.add(new Cliente(6, "006-6789012-3", "Sof�a", "L�pez", "profe.sofia@escuela.edu", "809-555-0606"));
		    
		    // Cliente 7 - M�dico
		    clientes.add(new Cliente(7, "007-7890123-4", "Roberto", "S�nchez", "dr.sanchez@clinica.com", "829-555-0707"));
		    
		    // Cliente 8 - Peque�o Empresario
		    clientes.add(new Cliente(8, "008-8901234-5", "Patricia", "D�az", "paty@negocio.local", "809-555-0808"));
		    
		    // Cliente 9 - Estudiante de Secundaria
		    clientes.add(new Cliente(9, "009-9012345-6", "Miguel", "Fern�ndez", "mike.f@estudiante.edu", "829-555-0909"));
		    
		    // Cliente 10 - Jubilado
		    clientes.add(new Cliente(10, "010-0123456-7", "Ramona", "G�mez", "ramona.gomez@jubilada.com", "809-555-1010"));
		    
		    return clientes;
		}
		public ArrayList<Combo> crearCombosBasicos() {
			ArrayList<Combo> combos = new ArrayList<>();
		    ArrayList<TarjetaMadre> tarjetas = crearTarjetasMadre();
		    ArrayList<Microprocesador> cpus = crearMicroprocesadores();
		    ArrayList<DiscoDuro> discos = crearDiscosDuros();
		    ArrayList<MemoriaRam> rams = crearMemoriasRAM();
		    
		    // Combo 1: Gama alta AMD
		    Combo comboGamaAltaAMD = new Combo(1, "Combo Gama Alta AMD", tarjetas.get(0), 10.0);
		    comboGamaAltaAMD.ingresarComponente(cpus.get(0)); // Ryzen 9 5950X
		    comboGamaAltaAMD.ingresarComponente(discos.get(0)); // Samsung 980 Pro 1TB NVMe
		    comboGamaAltaAMD.ingresarComponente(rams.get(1)); // G.Skill 32GB DDR4
		    combos.add(comboGamaAltaAMD);
		    
		    // Combo 2: Gama media Intel
		    Combo comboGamaMediaIntel = new Combo(2, "Combo Gama Media Intel", tarjetas.get(1), 8.0);
		    comboGamaMediaIntel.ingresarComponente(cpus.get(3)); // Core i7-13700K
		    comboGamaMediaIntel.ingresarComponente(discos.get(3)); // Toshiba P300 1TB SATA
		    comboGamaMediaIntel.ingresarComponente(rams.get(3)); // Corsair 32GB DDR5
		    combos.add(comboGamaMediaIntel);
		    
		    // Combo 3: Presupuesto AMD
		    Combo comboPresupuestoAMD = new Combo(3, "Combo Presupuesto AMD", tarjetas.get(2), 12.0);
		    comboPresupuestoAMD.ingresarComponente(cpus.get(5)); // Ryzen 5 5500
		    comboPresupuestoAMD.ingresarComponente(discos.get(5)); // Kingston A400 480GB M.2 SATA
		    comboPresupuestoAMD.ingresarComponente(rams.get(5)); // Team Group 16GB DDR4
		    combos.add(comboPresupuestoAMD);
		    
		    // Combo 4: Servidor Empresarial
		    Combo comboServidor = new Combo(4, "Combo Servidor Empresarial", tarjetas.get(3), 5.0);
		    comboServidor.ingresarComponente(cpus.get(7)); // Core i3-10100
		    comboServidor.ingresarComponente(discos.get(7)); // Samsung PM983 960GB U.2
		    comboServidor.ingresarComponente(rams.get(7)); // Micron 32GB DDR4 ECC
		    combos.add(comboServidor);
		    
		    // Combo 5: Gama Alta Intel
		    Combo comboGamaAltaIntel = new Combo(5, "Combo Gama Alta Intel", tarjetas.get(4), 15.0);
		    comboGamaAltaIntel.ingresarComponente(cpus.get(8)); // Core i9-13900K
		    comboGamaAltaIntel.ingresarComponente(discos.get(9)); // Sabrent Rocket 4 1TB NVMe
		    comboGamaAltaIntel.ingresarComponente(rams.get(9)); // G.Skill 16GB DDR5
		    combos.add(comboGamaAltaIntel);
		    
		    return combos;
		}

		public ArrayList<Combo> crearCombosEspecializados() {
		    ArrayList<Combo> combos = new ArrayList<>();
		    ArrayList<TarjetaMadre> tarjetas = crearTarjetasMadre();
		    ArrayList<Microprocesador> cpus = crearMicroprocesadores();
		    ArrayList<DiscoDuro> discos = crearDiscosDuros();
		    ArrayList<MemoriaRam> rams = crearMemoriasRAM();
		    
		    // Combo 6: Estaci�n de Trabajo para Dise�o
		    Combo comboDiseno = new Combo(6, "Estaci�n de Trabajo para Dise�o", tarjetas.get(0), 7.0);
		    comboDiseno.ingresarComponente(cpus.get(0)); // Ryzen 9 5950X
		    comboDiseno.ingresarComponente(discos.get(0)); // Samsung 980 Pro 1TB NVMe
		    comboDiseno.ingresarComponente(discos.get(3)); // Toshiba P300 1TB SATA (almacenamiento adicional)
		    comboDiseno.ingresarComponente(rams.get(1)); // G.Skill 32GB DDR4
		    comboDiseno.ingresarComponente(rams.get(1)); // Doble kit de RAM (total 64GB)
		    combos.add(comboDiseno);
		    
		    // Combo 7: PC para Gaming
		    Combo comboGaming = new Combo(7, "PC Gaming de Alto Rendimiento", tarjetas.get(4), 10.0);
		    comboGaming.ingresarComponente(cpus.get(8)); // Core i9-13900K
		    comboGaming.ingresarComponente(discos.get(9)); // Sabrent Rocket 4 1TB NVMe
		    comboGaming.ingresarComponente(rams.get(9)); // G.Skill 16GB DDR5
		    comboGaming.ingresarComponente(rams.get(9)); // Doble kit de RAM (total 32GB)
		    combos.add(comboGaming);
		    
		    // Combo 8: Oficina/Economico
		    Combo comboOficina = new Combo(8, "PC de Oficina Econ�mico", tarjetas.get(2), 15.0);
		    comboOficina.ingresarComponente(cpus.get(6)); // Ryzen 5 5500
		    comboOficina.ingresarComponente(discos.get(5)); // Kingston A400 480GB M.2 SATA
		    comboOficina.ingresarComponente(rams.get(5)); // Team Group 16GB DDR4
		    combos.add(comboOficina);
		    
		    return combos;
		}
		public ArrayList<TarjetaMadre> crearTarjetasMadre() {
		    ArrayList<TarjetaMadre> tarjetas = new ArrayList<>();
		    
		    // Tarjeta Madre 1 - AMD High-End
		    tarjetas.add(new TarjetaMadre(1, 299.99, 10, "TM-ASUS-001", "ASUS", "ROG Crosshair VIII", "AM4", "DDR4", "M.2 NVMe","Tarjeta Madre"));
		    
		    // Tarjeta Madre 2 - Intel Mid-Range
		    tarjetas.add(new TarjetaMadre(2, 189.99, 15, "TM-GIGA-002", "Gigabyte", "B660 AORUS", "LGA1700", "DDR5", "SATA III","Tarjeta Madre"));
		    
		    // Tarjeta Madre 3 - AMD Budget
		    tarjetas.add(new TarjetaMadre(3, 119.99, 20, "TM-MSI-003", "MSI", "B550M PRO", "AM4", "DDR4", "M.2 SATA","Tarjeta Madre"));
		    
		    // Tarjeta Madre 4 - Intel Server
		    tarjetas.add(new TarjetaMadre(4, 399.99, 5, "TM-ASROCK-004", "ASRock", "X570D4U", "LGA1200", "DDR4 ECC", "U.2","Tarjeta Madre"));
		    
		    // Tarjeta Madre 5 - Intel High-End
		    tarjetas.add(new TarjetaMadre(5, 259.99, 8, "TM-ASUS-005", "ASUS", "PRIME Z790", "LGA1700", "DDR5", "M.2 NVMe","Tarjeta Madre"));
		    
		    return tarjetas;
		}
		
		public ArrayList<Microprocesador> crearMicroprocesadores() {
		    ArrayList<Microprocesador> cpus = new ArrayList<>();
		    
		    // Compatibles con TM-ASUS-001 (AM4)
		    cpus.add(new Microprocesador(1, 449.99, 12, "CPU-AMD-001", "AMD", "Ryzen 9 5950X", "AM4", "4.9GHz","Microprocesador"));
		    cpus.add(new Microprocesador(2, 299.99, 18, "CPU-AMD-002", "AMD", "Ryzen 7 5800X", "AM4", "4.7GHz","Microprocesador"));
		    
		    // Compatibles con TM-GIGA-002 (LGA1700)
		    cpus.add(new Microprocesador(3, 289.99, 15, "CPU-INTEL-003", "Intel", "Core i5-13600K", "LGA1700", "5.1GHz","Microprocesador"));
		    cpus.add(new Microprocesador(4, 399.99, 10, "CPU-INTEL-004", "Intel", "Core i7-13700K", "LGA1700", "5.4GHz","Microprocesador"));
		    
		    // Compatibles con TM-MSI-003 (AM4)
		    cpus.add(new Microprocesador(5, 199.99, 20, "CPU-AMD-005", "AMD", "Ryzen 5 5600X", "AM4", "4.6GHz","Microprocesador"));
		    cpus.add(new Microprocesador(6, 159.99, 25, "CPU-AMD-006", "AMD", "Ryzen 5 5500", "AM4", "4.2GHz","Microprocesador"));
		    
		    // Compatibles con TM-ASROCK-004 (LGA1200)
		    cpus.add(new Microprocesador(7, 229.99, 8, "CPU-INTEL-007", "Intel", "Core i5-11400", "LGA1200", "4.4GHz","Microprocesador"));
		    cpus.add(new Microprocesador(8, 179.99, 12, "CPU-INTEL-008", "Intel", "Core i3-10100", "LGA1200", "4.3GHz","Microprocesador"));
		    
		    // Compatibles con TM-ASUS-005 (LGA1700)
		    cpus.add(new Microprocesador(9, 589.99, 5, "CPU-INTEL-009", "Intel", "Core i9-13900K", "LGA1700", "5.8GHz","Microprocesador"));
		    cpus.add(new Microprocesador(10, 349.99, 15, "CPU-INTEL-010", "Intel", "Core i5-13600KF", "LGA1700", "5.1GHz","Microprocesador"));
		    
		    return cpus;
		}
		
		public ArrayList<DiscoDuro> crearDiscosDuros() {
		    ArrayList<DiscoDuro> discos = new ArrayList<>();
		    
		    // Compatibles con TM-ASUS-001 (M.2 NVMe)
		    discos.add(new DiscoDuro(1, 129.99, 20, "DD-SAMSUNG-001", "Samsung", "980 Pro", "1TB", "M.2 NVMe","Disco Duro"));
		    discos.add(new DiscoDuro(2, 89.99, 25, "DD-WD-002", "WD", "Black SN770", "500GB", "M.2 NVMe","Disco Duro"));
		    
		    // Compatibles con TM-GIGA-002 (SATA III)
		    discos.add(new DiscoDuro(3, 49.99, 30, "DD-SEAGATE-003", "Seagate", "BarraCuda", "2TB", "SATA III","Disco Duro"));
		    discos.add(new DiscoDuro(4, 59.99, 28, "DD-TOSHIBA-004", "Toshiba", "P300", "1TB", "SATA III","Disco Duro"));
		    
		    // Compatibles con TM-MSI-003 (M.2 SATA)
		    discos.add(new DiscoDuro(5, 79.99, 22, "DD-CRUCIAL-005", "Crucial", "MX500", "1TB", "M.2 SATA","Disco Duro"));
		    discos.add(new DiscoDuro(6, 69.99, 18, "DD-KINGSTON-006", "Kingston", "A400", "480GB", "M.2 SATA","Disco Duro"));
		    
		    // Compatibles con TM-ASROCK-004 (U.2)
		    discos.add(new DiscoDuro(7, 199.99, 10, "DD-INTEL-007", "Intel", "Optane 905P", "480GB", "U.2","Disco Duro"));
		    discos.add(new DiscoDuro(8, 249.99, 8, "DD-SAMSUNG-008", "Samsung", "PM983", "960GB", "U.2","Disco Duro"));
		    
		    // Compatibles con TM-ASUS-005 (M.2 NVMe)
		    discos.add(new DiscoDuro(9, 159.99, 15, "DD-CORSAIR-009", "Corsair", "MP600 Pro", "1TB", "M.2 NVMe","Disco Duro"));
		    discos.add(new DiscoDuro(10, 119.99, 20, "DD-SABRENT-010", "Sabrent", "Rocket 4", "1TB", "M.2 NVMe","Disco Duro"));
		    
		    return discos;
		}
		public ArrayList<MemoriaRam> crearMemoriasRAM() {
		    ArrayList<MemoriaRam> rams = new ArrayList<>();
		    
		    // Compatibles con TM-ASUS-001 (DDR4)
		    rams.add(new MemoriaRam(1, 89.99, 25, "RAM-CORSAIR-001", "Corsair", 16, "DDR4","Memoria RAM"));
		    rams.add(new MemoriaRam(2, 129.99, 20, "RAM-GSKILL-002", "G.Skill", 32, "DDR4","Memoria RAM"));
		    
		    // Compatibles con TM-GIGA-002 (DDR5)
		    rams.add(new MemoriaRam(3, 149.99, 18, "RAM-KINGSTON-003", "Kingston", 16, "DDR5","Memoria RAM"));
		    rams.add(new MemoriaRam(4, 279.99, 12, "RAM-CORSAIR-004", "Corsair", 32, "DDR5","Memoria RAM"));
		    
		    // Compatibles con TM-MSI-003 (DDR4)
		    rams.add(new MemoriaRam(5, 69.99, 30, "RAM-CRUCIAL-005", "Crucial", 8, "DDR4","Memoria RAM"));
		    rams.add(new MemoriaRam(6, 119.99, 22, "RAM-TEAM-006", "Team Group", 16, "DDR4","Memoria RAM"));
		    
		    // Compatibles con TM-ASROCK-004 (DDR4 ECC)
		    rams.add(new MemoriaRam(7, 129.99, 15, "RAM-SAMSUNG-007", "Samsung", 16, "DDR4 ECC","Memoria RAM"));
		    rams.add(new MemoriaRam(8, 229.99, 10, "RAM-MICRON-008", "Micron", 32, "DDR4 ECC","Memoria RAM"));
		    
		    // Compatibles con TM-ASUS-005 (DDR5)
		    rams.add(new MemoriaRam(9, 169.99, 20, "RAM-GSKILL-009", "G.Skill", 16, "DDR5","Memoria RAM"));
		    rams.add(new MemoriaRam(10, 199.99, 15, "RAM-KINGSTON-010", "Kingston", 16, "DDR5","Memoria RAM"));
		    
		    return rams;
		}
}