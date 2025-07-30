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

import logico.Cliente;
import logico.Tienda;

public class RegClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTable table;
	private DefaultTableModel modelo;
	private Object [] row;
	private JLabel lblNombre;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JLabel lblCodigo;
	private JLabel lblCedula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegClientes dialog = new RegClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegClientes() {
		setTitle("Registro de Clientes");
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCodigo.setBounds(18, 16, 61, 16);
		contentPanel.add(lblCodigo);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(61, 49, 103, 16);
		contentPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(106, 44, 171, 26);
		contentPanel.add(txtEmail);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(334, 16, 61, 16);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(386, 11, 100, 26);
		contentPanel.add(txtNombre);
		
		lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedula.setBounds(175, 16, 45, 16);
		contentPanel.add(lblCedula);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(386, 49, 58, 16);
		contentPanel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(442, 44, 156, 26);
		contentPanel.add(txtTelefono);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(504, 16, 58, 16);
		contentPanel.add(lblApellido);
		
		JScrollPane scrollPane = new JScrollPane();
		{
			table = new JTable();
			modelo = new DefaultTableModel();
			String [] headers = {"Código","Cédula", "Nombre", "Apellido", "E-mail", "Teléfono"};
			for(Cliente hdd : Tienda.getInstance().getClientes()) {
				row = new Object[6];
				row[0] = hdd.getId_cliente();
				row[1] = hdd.getCedula();
				row[2] = hdd.getNombre();
				row[3] = hdd.getApellido();
				row[4] = hdd.getEmail();
				row[5] = hdd.getTelefono();
				modelo.addRow(row);
			}
			modelo.setColumnIdentifiers(headers);
			table.setModel(modelo);
			scrollPane.setViewportView(table);
		}
		scrollPane.setBounds(17, 77, 644, 333);
		contentPanel.add(scrollPane);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(61, 11, 100, 26);
		contentPanel.add(txtCodigo);
		
		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCedula.setColumns(10);
		txtCedula.setBounds(219, 11, 100, 26);
		contentPanel.add(txtCedula);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtApellido.setColumns(10);
		txtApellido.setBounds(561, 11, 100, 26);
		contentPanel.add(txtApellido);
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
