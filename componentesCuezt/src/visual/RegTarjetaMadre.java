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

import logico.TarjetaMadre;
import logico.Tienda;

public class RegTarjetaMadre extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtCodigo;
	private JTextField txtNumSerie;
	private JTextField txtMarca;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTable table;
	private DefaultTableModel modelo;
	private Object [] row;
	private JLabel lblMarca;
	private JTextField txtModelo;
	private JComboBox cbxConectorDD;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JLabel lblTipoRam;
	private JComboBox cbxTipoRAM;
	private JComboBox cbxConectorSocket;
	private JLabel lblConectorSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegTarjetaMadre dialog = new RegTarjetaMadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegTarjetaMadre() {
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
		txtCodigo.setBounds(60, 11, 61, 26);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNumSerie = new JLabel("Núm. Serie");
		lblNumSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumSerie.setBounds(18, 49, 70, 16);
		contentPanel.add(lblNumSerie);
		
		txtNumSerie = new JTextField();
		txtNumSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNumSerie.setColumns(10);
		txtNumSerie.setBounds(87, 44, 77, 26);
		contentPanel.add(txtNumSerie);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(166, 49, 43, 16);
		contentPanel.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMarca.setColumns(10);
		txtMarca.setBounds(207, 44, 77, 26);
		contentPanel.add(txtMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(285, 49, 50, 16);
		contentPanel.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtModelo.setColumns(10);
		txtModelo.setBounds(332, 44, 77, 26);
		contentPanel.add(txtModelo);
		
		JLabel lblTipoDeConector = new JLabel("Conector DD");
		lblTipoDeConector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeConector.setBounds(121, 16, 77, 16);
		contentPanel.add(lblTipoDeConector);
		
		cbxConectorDD = new JComboBox();
		cbxConectorDD.setModel(new DefaultComboBoxModel(new String[] {"", "M.2 NVMe", "M.2 SATA", "SATA III", "U.2"}));
		cbxConectorDD.setToolTipText("");
		cbxConectorDD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxConectorDD.setBounds(194, 12, 104, 27);
		contentPanel.add(cbxConectorDD);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecio.setBounds(412, 49, 43, 16);
		contentPanel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(451, 44, 61, 26);
		contentPanel.add(txtPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad.setBounds(525, 49, 58, 16);
		contentPanel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(584, 44, 61, 26);
		contentPanel.add(txtCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		{
			table = new JTable();
			modelo = new DefaultTableModel();
			String [] headers = {"Código","Número de Serie", "Marca", "Modelo", "Conector Socket", "Tipo Memoria RAM", "Conector DD", "Precio", "Cantidad"};
			for(TarjetaMadre hdd : Tienda.getInstance().getTarjetasMadres()){
				row = new Object[9];
				row[0] = hdd.getId();
				row[1] = hdd.getNumeroSerie();
				row[2] = hdd.getMarca();
				row[3] = hdd.getModelo();
				row[4] = hdd.getTipoConectorSocket();
				row[5] = hdd.getTipoMemoriaRAM();
				row[6] = hdd.getTipoConectorDD();
				row[7] = hdd.getPrecio();
				row[8] = hdd.getCantidad();
				modelo.addRow(row);
			}
			modelo.setColumnIdentifiers(headers);
			table.setModel(modelo);
			scrollPane.setViewportView(table);
		}
		scrollPane.setBounds(17, 77, 644, 333);
		contentPanel.add(scrollPane);
		
		lblTipoRam = new JLabel("Tipo RAM");
		lblTipoRam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoRam.setBounds(299, 15, 61, 16);
		contentPanel.add(lblTipoRam);
		
		cbxTipoRAM = new JComboBox();
		cbxTipoRAM.setModel(new DefaultComboBoxModel(new String[] {"", "DDR4", "DDR5", "DDR4 ECC"}));
		cbxTipoRAM.setToolTipText("");
		cbxTipoRAM.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxTipoRAM.setBounds(358, 11, 84, 27);
		contentPanel.add(cbxTipoRAM);
		
		cbxConectorSocket = new JComboBox();
		cbxConectorSocket.setModel(new DefaultComboBoxModel(new String[] {"", "LGA 1200", "LGA 1700", "LGA 1151", "AM4", "AM5", "sTRX4"}));
		cbxConectorSocket.setToolTipText("");
		cbxConectorSocket.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxConectorSocket.setBounds(541, 12, 104, 27);
		contentPanel.add(cbxConectorSocket);
		
		lblConectorSocket = new JLabel("Conector Socket");
		lblConectorSocket.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConectorSocket.setBounds(448, 16, 96, 16);
		contentPanel.add(lblConectorSocket);
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
