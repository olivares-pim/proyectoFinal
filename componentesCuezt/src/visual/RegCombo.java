package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Microprocesador;
import logico.TarjetaMadre;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class RegCombo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private ArrayList<TarjetaMadre> tarjetasMadres = Tienda.getInstance().getTarjetasMadres();
	private ArrayList<MemoriaRam> rams = Tienda.getInstance().getMemoriasRam();
	private ArrayList<Microprocesador> cpus = Tienda.getInstance().getMicroProcesadores();
	private ArrayList<DiscoDuro> hdds = Tienda.getInstance().getDiscosDuros();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCombo dialog = new RegCombo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCombo() {
		//BORRAR ESTA PARTE
		tarjetasMadres = crearTarjetasMadre();
		rams = crearMemoriasRAM();
		cpus = crearMicroprocesadores();
		hdds = crearDiscosDuros();
		//HASTA AQUI
		
		setBounds(100, 100, 483, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(20, 45, 98, 14);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(140, 45, 133, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Combo Nuevo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(10, 11, 123, 14);
		contentPanel.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Tarjeta Madre");
		lblNewLabel.setBounds(20, 90, 98, 14);
		contentPanel.add(lblNewLabel);
		
		JComboBox<TarjetaMadre> cboTarjetaMadre = new JComboBox<>();
		cboTarjetaMadre.addItem(null);
		for(TarjetaMadre tM : tarjetasMadres) {
			cboTarjetaMadre.addItem(tM);
		}
		
		cboTarjetaMadre.setBounds(140, 90, 133, 20);
		contentPanel.add(cboTarjetaMadre);
		
		JLabel lblRam = new JLabel("Memoria RAM");
		lblRam.setBounds(20, 135, 98, 14);
		contentPanel.add(lblRam);
		
		JComboBox<MemoriaRam> cboRAM = new JComboBox();
		cboRAM.addItem(null);
		for(MemoriaRam ram : rams) {
			cboRAM.addItem(ram);
		}
		cboRAM.setBounds(140, 135, 133, 20);
		contentPanel.add(cboRAM);
		
		
		JLabel lblHDD = new JLabel("Disco Duro");
		lblHDD.setBounds(20, 180, 98, 14);
		contentPanel.add(lblHDD);
		
		JComboBox<DiscoDuro> cboHDD = new JComboBox();
		cboHDD.addItem(null);
		for(DiscoDuro hdd : hdds) {
			cboHDD.addItem(hdd);
		}
		cboHDD.setBounds(140, 180, 133, 20);
		contentPanel.add(cboHDD);
		
		JLabel lblMicroProcesador = new JLabel("MicroProcesador");
		lblMicroProcesador.setBounds(20, 225, 98, 14);
		contentPanel.add(lblMicroProcesador);
		
		JComboBox<Microprocesador> cboCPU = new JComboBox();
		cboCPU.addItem(null);
		for(Microprocesador cpu : cpus) {
			cboCPU.addItem(cpu);
		}
		cboCPU.setBounds(140, 225, 133, 20);
		contentPanel.add(cboCPU);
		
		JLabel lblDescuento = new JLabel("Descuento %");
		lblDescuento.setBounds(306, 45, 75, 14);
		contentPanel.add(lblDescuento);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(391, 42, 43, 20);
		contentPanel.add(spinner);
		
		//Actulizacion de ComboBoxes segun Mobo
		
		cboTarjetaMadre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboRAM.removeAllItems();
				cboRAM.addItem(null);
				cboHDD.removeAllItems();
				cboHDD.addItem(null);
				cboCPU.removeAllItems();
				cboCPU.addItem(null);
				TarjetaMadre seleccionTM = (TarjetaMadre) cboTarjetaMadre.getSelectedItem();
			if(seleccionTM==null) {
				for(MemoriaRam ram : rams) {
					cboRAM.addItem(ram);
				}
				for(DiscoDuro hdd : hdds) {
					cboHDD.addItem(hdd);
				}
				for(Microprocesador cpu : cpus) {
					cboCPU.addItem(cpu);
				}	
			} else {
				for(MemoriaRam ram : rams) {
					if(ram.verificarConector(seleccionTM)) {
						cboRAM.addItem(ram);
					}
				}
				for(DiscoDuro hdd : hdds) {
					if(hdd.verificarConector(seleccionTM)) {
						cboHDD.addItem(hdd);
					}
				}
				for(Microprocesador cpu : cpus) {
					if(cpu.verificarConector(seleccionTM)) {
						cboCPU.addItem(cpu);
					}
				}
			}
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	//BORRAR ESTO ANTES DE TERMINAR PROYECTO ESTO ES PARA PRUEBAS TAMBIEN INCLUIR LAS LINEAS DEBAJS DEL INCIO DEL CONSTRUCTOR
	public ArrayList<TarjetaMadre> crearTarjetasMadre() {
	    ArrayList<TarjetaMadre> tarjetas = new ArrayList<>();
	    
	    // Tarjeta Madre 1 - AMD High-End
	    tarjetas.add(new TarjetaMadre(1, 299.99, 10, "TM-ASUS-001", "ASUS", "ROG Crosshair VIII", "AM4", "DDR4", "M.2 NVMe"));
	    
	    // Tarjeta Madre 2 - Intel Mid-Range
	    tarjetas.add(new TarjetaMadre(2, 189.99, 15, "TM-GIGA-002", "Gigabyte", "B660 AORUS", "LGA1700", "DDR5", "SATA III"));
	    
	    // Tarjeta Madre 3 - AMD Budget
	    tarjetas.add(new TarjetaMadre(3, 119.99, 20, "TM-MSI-003", "MSI", "B550M PRO", "AM4", "DDR4", "M.2 SATA"));
	    
	    // Tarjeta Madre 4 - Intel Server
	    tarjetas.add(new TarjetaMadre(4, 399.99, 5, "TM-ASROCK-004", "ASRock", "X570D4U", "LGA1200", "DDR4 ECC", "U.2"));
	    
	    // Tarjeta Madre 5 - Intel High-End
	    tarjetas.add(new TarjetaMadre(5, 259.99, 8, "TM-ASUS-005", "ASUS", "PRIME Z790", "LGA1700", "DDR5", "M.2 NVMe"));
	    
	    return tarjetas;
	}
	
	public ArrayList<Microprocesador> crearMicroprocesadores() {
	    ArrayList<Microprocesador> cpus = new ArrayList<>();
	    
	    // Compatibles con TM-ASUS-001 (AM4)
	    cpus.add(new Microprocesador(1, 449.99, 12, "CPU-AMD-001", "AMD", "Ryzen 9 5950X", "AM4", "4.9GHz"));
	    cpus.add(new Microprocesador(2, 299.99, 18, "CPU-AMD-002", "AMD", "Ryzen 7 5800X", "AM4", "4.7GHz"));
	    
	    // Compatibles con TM-GIGA-002 (LGA1700)
	    cpus.add(new Microprocesador(3, 289.99, 15, "CPU-INTEL-003", "Intel", "Core i5-13600K", "LGA1700", "5.1GHz"));
	    cpus.add(new Microprocesador(4, 399.99, 10, "CPU-INTEL-004", "Intel", "Core i7-13700K", "LGA1700", "5.4GHz"));
	    
	    // Compatibles con TM-MSI-003 (AM4)
	    cpus.add(new Microprocesador(5, 199.99, 20, "CPU-AMD-005", "AMD", "Ryzen 5 5600X", "AM4", "4.6GHz"));
	    cpus.add(new Microprocesador(6, 159.99, 25, "CPU-AMD-006", "AMD", "Ryzen 5 5500", "AM4", "4.2GHz"));
	    
	    // Compatibles con TM-ASROCK-004 (LGA1200)
	    cpus.add(new Microprocesador(7, 229.99, 8, "CPU-INTEL-007", "Intel", "Core i5-11400", "LGA1200", "4.4GHz"));
	    cpus.add(new Microprocesador(8, 179.99, 12, "CPU-INTEL-008", "Intel", "Core i3-10100", "LGA1200", "4.3GHz"));
	    
	    // Compatibles con TM-ASUS-005 (LGA1700)
	    cpus.add(new Microprocesador(9, 589.99, 5, "CPU-INTEL-009", "Intel", "Core i9-13900K", "LGA1700", "5.8GHz"));
	    cpus.add(new Microprocesador(10, 349.99, 15, "CPU-INTEL-010", "Intel", "Core i5-13600KF", "LGA1700", "5.1GHz"));
	    
	    return cpus;
	}
	
	public ArrayList<DiscoDuro> crearDiscosDuros() {
	    ArrayList<DiscoDuro> discos = new ArrayList<>();
	    
	    // Compatibles con TM-ASUS-001 (M.2 NVMe)
	    discos.add(new DiscoDuro(1, 129.99, 20, "DD-SAMSUNG-001", "Samsung", "980 Pro", "1TB", "M.2 NVMe"));
	    discos.add(new DiscoDuro(2, 89.99, 25, "DD-WD-002", "WD", "Black SN770", "500GB", "M.2 NVMe"));
	    
	    // Compatibles con TM-GIGA-002 (SATA III)
	    discos.add(new DiscoDuro(3, 49.99, 30, "DD-SEAGATE-003", "Seagate", "BarraCuda", "2TB", "SATA III"));
	    discos.add(new DiscoDuro(4, 59.99, 28, "DD-TOSHIBA-004", "Toshiba", "P300", "1TB", "SATA III"));
	    
	    // Compatibles con TM-MSI-003 (M.2 SATA)
	    discos.add(new DiscoDuro(5, 79.99, 22, "DD-CRUCIAL-005", "Crucial", "MX500", "1TB", "M.2 SATA"));
	    discos.add(new DiscoDuro(6, 69.99, 18, "DD-KINGSTON-006", "Kingston", "A400", "480GB", "M.2 SATA"));
	    
	    // Compatibles con TM-ASROCK-004 (U.2)
	    discos.add(new DiscoDuro(7, 199.99, 10, "DD-INTEL-007", "Intel", "Optane 905P", "480GB", "U.2"));
	    discos.add(new DiscoDuro(8, 249.99, 8, "DD-SAMSUNG-008", "Samsung", "PM983", "960GB", "U.2"));
	    
	    // Compatibles con TM-ASUS-005 (M.2 NVMe)
	    discos.add(new DiscoDuro(9, 159.99, 15, "DD-CORSAIR-009", "Corsair", "MP600 Pro", "1TB", "M.2 NVMe"));
	    discos.add(new DiscoDuro(10, 119.99, 20, "DD-SABRENT-010", "Sabrent", "Rocket 4", "1TB", "M.2 NVMe"));
	    
	    return discos;
	}
	public ArrayList<MemoriaRam> crearMemoriasRAM() {
	    ArrayList<MemoriaRam> rams = new ArrayList<>();
	    
	    // Compatibles con TM-ASUS-001 (DDR4)
	    rams.add(new MemoriaRam(1, 89.99, 25, "RAM-CORSAIR-001", "Corsair", 16, "DDR4"));
	    rams.add(new MemoriaRam(2, 129.99, 20, "RAM-GSKILL-002", "G.Skill", 32, "DDR4"));
	    
	    // Compatibles con TM-GIGA-002 (DDR5)
	    rams.add(new MemoriaRam(3, 149.99, 18, "RAM-KINGSTON-003", "Kingston", 16, "DDR5"));
	    rams.add(new MemoriaRam(4, 279.99, 12, "RAM-CORSAIR-004", "Corsair", 32, "DDR5"));
	    
	    // Compatibles con TM-MSI-003 (DDR4)
	    rams.add(new MemoriaRam(5, 69.99, 30, "RAM-CRUCIAL-005", "Crucial", 8, "DDR4"));
	    rams.add(new MemoriaRam(6, 119.99, 22, "RAM-TEAM-006", "Team Group", 16, "DDR4"));
	    
	    // Compatibles con TM-ASROCK-004 (DDR4 ECC)
	    rams.add(new MemoriaRam(7, 129.99, 15, "RAM-SAMSUNG-007", "Samsung", 16, "DDR4 ECC"));
	    rams.add(new MemoriaRam(8, 229.99, 10, "RAM-MICRON-008", "Micron", 32, "DDR4 ECC"));
	    
	    // Compatibles con TM-ASUS-005 (DDR5)
	    rams.add(new MemoriaRam(9, 169.99, 20, "RAM-GSKILL-009", "G.Skill", 16, "DDR5"));
	    rams.add(new MemoriaRam(10, 199.99, 15, "RAM-KINGSTON-010", "Kingston", 16, "DDR5"));
	    
	    return rams;
	}
}
