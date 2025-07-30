package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Microprocesador;
import logico.Tienda;

public class RegMicroprocesador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtCodigo;
	private JTextField txtNumSerie;
	private JTextField txtMarca;
	private JTextField txtVelocidad;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTable table;
	private DefaultTableModel modelo;
	private Object [] row;
	private JLabel lblMarca;
	private JTextField txtModelo;
	private JComboBox cbxConector;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegMicroprocesador dialog = new RegMicroprocesador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegMicroprocesador() {
		setTitle("Registro de Microprocesadores");
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblid_componente = new JLabel("Codigo");
		lblid_componente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblid_componente.setBounds(18, 16, 61, 16);
		contentPanel.add(lblid_componente);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCodigo.setBounds(70, 11, 77, 26);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNumSerie = new JLabel("Número de Serie");
		lblNumSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumSerie.setBounds(18, 49, 103, 16);
		contentPanel.add(lblNumSerie);
		
		txtNumSerie = new JTextField();
		txtNumSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNumSerie.setColumns(10);
		txtNumSerie.setBounds(120, 44, 103, 26);
		contentPanel.add(txtNumSerie);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(363, 16, 43, 16);
		contentPanel.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMarca.setColumns(10);
		txtMarca.setBounds(404, 11, 90, 26);
		contentPanel.add(txtMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(520, 16, 43, 16);
		contentPanel.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtModelo.setColumns(10);
		txtModelo.setBounds(565, 11, 96, 26);
		contentPanel.add(txtModelo);
		
		JLabel lblAlmacenamiento = new JLabel("Velocidad");
		lblAlmacenamiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAlmacenamiento.setBounds(176, 16, 61, 16);
		contentPanel.add(lblAlmacenamiento);
		
		txtVelocidad = new JTextField();
		txtVelocidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVelocidad.setColumns(10);
		txtVelocidad.setBounds(235, 11, 77, 26);
		contentPanel.add(txtVelocidad);
		
		JLabel lblNewLabel = new JLabel("GHz");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(314, 16, 24, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTipoDeConector = new JLabel("Conector");
		lblTipoDeConector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeConector.setBounds(235, 49, 61, 16);
		contentPanel.add(lblTipoDeConector);
		
		cbxConector = new JComboBox();
		cbxConector.setModel(new DefaultComboBoxModel(new String[] {"", "LGA 1200", "LGA 1700", "LGA 1151", "AM4", "AM5", "sTRX4"}));
		cbxConector.setToolTipText("");
		cbxConector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxConector.setBounds(293, 45, 123, 27);
		contentPanel.add(cbxConector);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecio.setBounds(428, 49, 43, 16);
		contentPanel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(467, 44, 61, 26);
		contentPanel.add(txtPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad.setBounds(541, 49, 58, 16);
		contentPanel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(600, 44, 61, 26);
		contentPanel.add(txtCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		{
			table = new JTable();
			modelo = new DefaultTableModel();
			String [] headers = {"Código","Número de Serie", "Marca", "Modelo", "Velocidad", "Conector", "Precio", "Cantidad"};
			for(Microprocesador hdd : Tienda.getInstance().getMicroProcesadores()){
				row = new Object[8];
				row[0] = hdd.getId();
				row[1] = hdd.getNumeroSerie();
				row[2] = hdd.getMarca();
				row[3] = hdd.getModelo();
				row[4] = hdd.getVelocidadProcesador();
				row[5] = hdd.getTipoConectorSocket();
				row[6] = hdd.getPrecio();
				row[7] = hdd.getCantidad();
				modelo.addRow(row);
			}
			modelo.setColumnIdentifiers(headers);
			table.setModel(modelo);
			scrollPane.setViewportView(table);
		}
		scrollPane.setBounds(17, 77, 644, 333);
		contentPanel.add(scrollPane);
		dim = getToolkit().getScreenSize();
        setSize(678,483);
        setLocationRelativeTo(null);
        setResizable(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			
			btnActualizar = new JButton("Actualizar");
			buttonPane.add(btnActualizar);
			
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
