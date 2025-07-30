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

import logico.MemoriaRam;
import logico.Tienda;

public class RegMemoriaRAM extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtCodigo;
	private JTextField txtNumSerie;
	private JTextField txtMarca;
	private JTextField txtCantidadMemoria;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTable table;
	private DefaultTableModel modelo;
	private Object [] row;
	private JLabel lblMarca;
	private JComboBox cbxtipoMemoria;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegMemoriaRAM dialog = new RegMemoriaRAM();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegMemoriaRAM() {
		setTitle("Registro de Memoria RAM");
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
		lblNumSerie.setBounds(61, 49, 103, 16);
		contentPanel.add(lblNumSerie);
		
		txtNumSerie = new JTextField();
		txtNumSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNumSerie.setColumns(10);
		txtNumSerie.setBounds(164, 44, 103, 26);
		contentPanel.add(txtNumSerie);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(383, 16, 45, 16);
		contentPanel.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMarca.setColumns(10);
		txtMarca.setBounds(428, 11, 100, 26);
		contentPanel.add(txtMarca);
		
		JLabel lblCantidadMemoria = new JLabel("Cantidad Memoria");
		lblCantidadMemoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidadMemoria.setBounds(154, 16, 114, 16);
		contentPanel.add(lblCantidadMemoria);
		
		txtCantidadMemoria = new JTextField();
		txtCantidadMemoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidadMemoria.setColumns(10);
		txtCantidadMemoria.setBounds(266, 11, 77, 26);
		contentPanel.add(txtCantidadMemoria);
		
		JLabel lblNewLabel = new JLabel("GB");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(347, 16, 24, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lbltipoMemoria = new JLabel("Tipo Memoria");
		lbltipoMemoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbltipoMemoria.setBounds(276, 49, 83, 16);
		contentPanel.add(lbltipoMemoria);
		
		cbxtipoMemoria = new JComboBox();
		cbxtipoMemoria.setModel(new DefaultComboBoxModel(new String[] {"", "DDR4", "DDR5", "DDR4 ECC"}));
		cbxtipoMemoria.setToolTipText("");
		cbxtipoMemoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxtipoMemoria.setBounds(357, 45, 123, 27);
		contentPanel.add(cbxtipoMemoria);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecio.setBounds(499, 49, 43, 16);
		contentPanel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(537, 44, 61, 26);
		contentPanel.add(txtPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad.setBounds(540, 16, 58, 16);
		contentPanel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(599, 11, 61, 26);
		contentPanel.add(txtCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		{
			table = new JTable();
			modelo = new DefaultTableModel();
			String [] headers = {"Código","Número de Serie", "Marca", "Cantidad Memoria", "Tipo Memoria", "Precio", "Cantidad"};
			for(MemoriaRam hdd : Tienda.getInstance().getMemoriasRam()){
				row = new Object[7];
				row[0] = hdd.getId();
				row[1] = hdd.getNumeroSerie();
				row[2] = hdd.getMarca();
				row[3] = hdd.getCantidadMemoria();
				row[4] = hdd.getTipoMemoria();
				row[5] = hdd.getPrecio();
				row[6] = hdd.getCantidad();
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


