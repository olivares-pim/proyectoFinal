package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.DiscoDuro;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegDiscoDuro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtCodigo;
	private JTextField txtNumSerie;
	private JTextField txtMarca;
	private JTextField textField;
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
			RegDiscoDuro dialog = new RegDiscoDuro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegDiscoDuro() {
		setTitle("Registro de Disco Duro");
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
		lblMarca.setBounds(373, 16, 135, 16);
		contentPanel.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMarca.setColumns(10);
		txtMarca.setBounds(418, 11, 90, 26);
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
		
		JLabel lblAlmacenamiento = new JLabel("Almacenamiento");
		lblAlmacenamiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAlmacenamiento.setBounds(159, 16, 103, 16);
		contentPanel.add(lblAlmacenamiento);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(258, 11, 77, 26);
		contentPanel.add(textField);
		
		JLabel lblNewLabel = new JLabel("GB");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(337, 16, 24, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTipoDeConector = new JLabel("Conector");
		lblTipoDeConector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeConector.setBounds(235, 49, 61, 16);
		contentPanel.add(lblTipoDeConector);
		
		cbxConector = new JComboBox();
		cbxConector.setModel(new DefaultComboBoxModel(new String[] {"", "M.2 NVMe", "M.2 SATA", "SATA III", "U.2"}));
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
			String [] headers = {"Código","Número de Serie", "Marca", "Modelo", "Almacenamiento", "Conector", "Precio", "Cantidad"};
			for(DiscoDuro hdd : Tienda.getInstance().getDiscosDuros()){
				row = new Object[8];
				row[0] = hdd.getId();
				row[1] = hdd.getNumeroSerie();
				row[2] = hdd.getMarca();
				row[3] = hdd.getModelo();
				row[4] = hdd.getCapacidadAlmacenamiento();
				row[5] = hdd.getTipoConectorDD();
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
