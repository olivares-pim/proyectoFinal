package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private Dimension dim=null;
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
	
		
		dim = getToolkit().getScreenSize();
		setSize(dim.width/3,dim.height/3);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(20, 45, 98, 14);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setText(String.valueOf(Tienda.generadorCombo));
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
		lblRam.setBounds(306, 90, 98, 14);
		contentPanel.add(lblRam);
		
		JComboBox<MemoriaRam> cboRAM = new JComboBox();
		cboRAM.addItem(null);
		for(MemoriaRam ram : rams) {
			cboRAM.addItem(ram);
		}
		cboRAM.setBounds(402, 90, 133, 20);
		contentPanel.add(cboRAM);
		
		
		JLabel lblHDD = new JLabel("Disco Duro");
		lblHDD.setBounds(306, 45, 98, 14);
		contentPanel.add(lblHDD);
		
		JComboBox<DiscoDuro> cboHDD = new JComboBox();
		cboHDD.addItem(null);
		for(DiscoDuro hdd : hdds) {
			cboHDD.addItem(hdd);
		}
		cboHDD.setBounds(402, 45, 133, 20);
		contentPanel.add(cboHDD);
		
		JLabel lblMicroProcesador = new JLabel("MicroProcesador");
		lblMicroProcesador.setBounds(547, 45, 98, 14);
		contentPanel.add(lblMicroProcesador);
		
		JComboBox<Microprocesador> cboCPU = new JComboBox();
		cboCPU.addItem(null);
		for(Microprocesador cpu : cpus) {
			cboCPU.addItem(cpu);
		}
		cboCPU.setBounds(667, 45, 133, 20);
		contentPanel.add(cboCPU);
		
		JLabel lblDescuento = new JLabel("Descuento %");
		lblDescuento.setBounds(547, 93, 75, 14);
		contentPanel.add(lblDescuento);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(667, 87, 133, 20);
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
	
	
	
}