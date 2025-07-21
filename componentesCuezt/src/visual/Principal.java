package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim=null;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnComponentes = new JMenu("Componentes");
		menuBar.add(mnComponentes);
		
		JMenu mnNuevoComponente = new JMenu("Nuevo");
		mnComponentes.add(mnNuevoComponente);
		
		JMenuItem mntmCombo = new JMenuItem("Crear Combo");
		mnNuevoComponente.add(mntmCombo);
		
		JMenuItem mntmDisco = new JMenuItem("Disco Almacenamiento");
		mnNuevoComponente.add(mntmDisco);
		
		JMenuItem mntmRAM = new JMenuItem("Memoria RAM");
		mnNuevoComponente.add(mntmRAM);
		
		JMenuItem mntmMicroProcesador = new JMenuItem("Microprocesador");
		mnNuevoComponente.add(mntmMicroProcesador);
		
		JMenuItem mntmTarjetaMade = new JMenuItem("Tarjeta Madre");
		mnNuevoComponente.add(mntmTarjetaMade);
		
		JMenuItem mntmComponentesExistentes = new JMenuItem("Ver Componentes");
		mnComponentes.add(mntmComponentesExistentes);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mnClientes.add(mntmNuevoCliente);
		
		JMenuItem mntmClientesExistentes = new JMenuItem("Ver Clientes");
		mnClientes.add(mntmClientesExistentes);
		
		JMenu mnFacturas = new JMenu("Factura");
		menuBar.add(mnFacturas);
		
		JMenuItem mntmNuevaFactura = new JMenuItem("Nueva Factura");
		mntmNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegFactura sum = new RegFactura();
				sum.setModal(true);
				sum.setVisible(true);
			}
		});
		mnFacturas.add(mntmNuevaFactura);
		
		JMenuItem mntmFacturasExistentes = new JMenuItem("Ver Facturas");
		mnFacturas.add(mntmFacturasExistentes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setBounds(100, 100, 776, 482);
		dim = getToolkit().getScreenSize();
		setSize(dim.width/2,dim.height/2);
		setLocationRelativeTo(null);
	}

}
