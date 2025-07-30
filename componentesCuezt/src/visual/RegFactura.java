package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Excepciones.ObjectAlreadyExistsException;
import logico.Cliente;
import logico.Combo;
import logico.Componente;
import logico.Factura;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private Dimension dim=null;
	private JTable tblDetalleFactura;
	private DefaultTableModel modelo;
	private Object [] row;
	private JComboBox<Combo> cboCombo;
	private JSpinner spnCombo;
	private JLabel lblTotalCalculado;
	private JButton btnFacturar;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		try {
			RegFactura dialog = new RegFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegFactura() {
		setTitle("Registrar Factura");
		dim = getToolkit().getScreenSize();
		setSize(dim.width/3,dim.height/3);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(60, 15, 46, 14);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setText(String.valueOf(Tienda.generadorFactura));
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(116, 12, 86, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(60, 46, 46, 14);
		contentPanel.add(lblCliente);
		
		JComboBox<Cliente> cboCliente = new JComboBox<Cliente>();
		cboCliente.setBounds(116, 43, 153, 20);
		contentPanel.add(cboCliente);
		
//		Agregar todos los clientes al combobox
		cboCliente.addItem(null);
		for(Cliente cliente : Tienda.getInstance().getClientes()) {
			cboCliente.addItem(cliente);
		}
		
		JLabel lblComponente = new JLabel("Componente:");
		lblComponente.setBounds(312, 15, 78, 14);
		contentPanel.add(lblComponente);
		
		JComboBox<Componente> cboComponente = new JComboBox<Componente>();
		cboComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboCombo.setSelectedItem(0);
			}
		});
		cboComponente.setBounds(400, 12, 236, 20);
		contentPanel.add(cboComponente);
		
		//Agregar todo los componentes al combobox
		cboComponente.addItem(null);
		for(Componente componente : Tienda.getInstance().getComponentes()) {
			cboComponente.addItem(componente);
		}
		
		JSpinner spnComponente = new JSpinner();
		spnComponente.setBounds(646, 12, 46, 20);
		contentPanel.add(spnComponente);
		
		JButton btnAgregarComponente = new JButton("Agregar");
		btnAgregarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente aux = (Componente) cboComponente.getSelectedItem();
				if(aux == null) {return;}
//				int cantRest=aux.getCantidad()-(int)spnComponente.getValue();
//				if(cantRest<=10 && cantRest>0) {
//					JOptionPane.showMessageDialog(RegFactura.this, 
//		                    aux + "casi esta agotado.", 
//		                    "Advertencia", 
//		                    JOptionPane.WARNING_MESSAGE);
//				} else if (cantRest<0) {
//					JOptionPane.showMessageDialog(RegFactura.this, 
//		                    "No hay suficiente stock para este combo", 
//		                    "Error", 
//		                    JOptionPane.ERROR_MESSAGE);
//		                return;
//				}
				int cantidadCompNuevo = (int) spnComponente.getValue();
		        if (cantidadCompNuevo <= 0) return;
		        boolean found = false;
		        for (int i = 0; i < modelo.getRowCount(); i++) {
		            int idEval = (int) modelo.getValueAt(i, 0);
		            if (idEval == aux.getId()) {
		                int cantidadCompEncontrado = (int) modelo.getValueAt(i, 2);
		                modelo.setValueAt(cantidadCompEncontrado + cantidadCompNuevo, i, 2);
		                found = true;
		                break;
		            }
		        }
		        if (!found) {
				row = new Object[tblDetalleFactura.getColumnCount()];
				row[0]= aux.getId();
				row[1]= aux.toString();
				row[2]= spnComponente.getValue();
				row[3]= aux.getPrecio();
				modelo.addRow(row);
				}
		        
		        spnComponente.setValue(1);
		        calcularTotal();
			}
		});
		btnAgregarComponente.setBounds(702, 11, 89, 23);
		contentPanel.add(btnAgregarComponente);
		
		cboCombo = new JComboBox<Combo>();
		cboCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboComponente.setSelectedIndex(0);
			}
		});
		cboCombo.setBounds(400, 43, 236, 20);
		contentPanel.add(cboCombo);
		
		//Agregar todos los combos al combobox
		cboCombo.addItem(null);
		for(Combo combo : Tienda.getInstance().getCombos()) {
			cboCombo.addItem(combo);
		}
		
		JLabel lblCombo = new JLabel("Combo:");
		lblCombo.setBounds(312, 46, 78, 14);
		contentPanel.add(lblCombo);
		
		spnCombo = new JSpinner();
		spnCombo.setBounds(646, 43, 46, 20);
		contentPanel.add(spnCombo);
		
		JButton btnAgregarCombo = new JButton("Agregar");
		btnAgregarCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Combo aux = (Combo) cboCombo.getSelectedItem();
				if(aux == null) {return;}
//				for(Componente comp : aux.getComponentesCombo()) {
//					int cantRest=comp.getCantidad()-(int)spnCombo.getValue();
//					if(cantRest<=10 && cantRest>0) {
//						JOptionPane.showMessageDialog(RegFactura.this, 
//			                    comp + "casi esta agotado.", 
//			                    "Advertencia", 
//			                    JOptionPane.WARNING_MESSAGE);
//					} else if (cantRest<0) {
//						JOptionPane.showMessageDialog(RegFactura.this, 
//			                    "No hay suficiente stock para este combo", 
//			                    "Error", 
//			                    JOptionPane.ERROR_MESSAGE);
//			                return;
//					}
//				}
				int cantidadComboNuevo = (int) spnCombo.getValue();
		        if (cantidadComboNuevo <= 0) return;
		        boolean found = false;
		        for (int i = 0; i < modelo.getRowCount(); i++) {
		            int idEval = (int) modelo.getValueAt(i, 0);
		            if (idEval == aux.getId()) {
		                int cantidadComboEncontrado = (int) modelo.getValueAt(i, 2);
		                modelo.setValueAt(cantidadComboEncontrado + cantidadComboNuevo, i, 2);
		                found = true;
		                break;
		            }
		        }
		        if (!found) {
				row = new Object[tblDetalleFactura.getColumnCount()];
				row[0]= aux.getId();
				row[1]= aux.toString();
				row[2]= spnCombo.getValue();
				row[3]= aux.getPrecio();
				modelo.addRow(row);
				}
		        spnCombo.setValue(1);
		        calcularTotal();
			}
		});
		btnAgregarCombo.setBounds(702, 42, 89, 23);
		contentPanel.add(btnAgregarCombo);
		
		tblDetalleFactura = new JTable();
		modelo = new DefaultTableModel();
		String[] headers = {"ID", "Nombre", "Cantidad", "Precio"};
		modelo.setColumnIdentifiers(headers);
		tblDetalleFactura.setModel(modelo);

		JScrollPane scrollPane = new JScrollPane(tblDetalleFactura);
		scrollPane.setBounds(10, 77, 827, 311);
		contentPanel.add(scrollPane);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(702, 393, 46, 14);
		contentPanel.add(lblTotal);
		
		lblTotalCalculado = new JLabel("$0.00");
		lblTotalCalculado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalCalculado.setBounds(747, 393, 86, 14);
		contentPanel.add(lblTotalCalculado);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnFacturar = new JButton("Facturar");
				btnFacturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 if(!lblTotalCalculado.getText().equals("$0.00")) {
					            Cliente cliente = (Cliente) cboCliente.getSelectedItem();
					            if (cliente == null) {
					                JOptionPane.showMessageDialog(RegFactura.this, 
					                    "Seleccione un cliente", 
					                    "Error", 
					                    JOptionPane.ERROR_MESSAGE);
					                return;
					            }
					            int fechaPedido = (int) (System.currentTimeMillis() / 1000);
					            double precioTotal = Double.parseDouble(lblTotalCalculado.getText().substring(1));
					            Factura factura = new Factura(
					                Tienda.generadorFactura,
					                cliente,
					                fechaPedido,
					                precioTotal
					            );
					            for (int i = 0; i < modelo.getRowCount(); i++) {
					                int id = (Integer) modelo.getValueAt(i, 0);
					                String nombre = (String) modelo.getValueAt(i, 1);
					                int cantidad = (Integer) modelo.getValueAt(i, 2);
					                double precio = (Double) modelo.getValueAt(i, 3);
					                if(nombre.contains("Combo")) {
					                	Combo combo = Tienda.getInstance().getComboPorId(id);
					                    if (combo != null) {
					                        for (int it = 0; it < cantidad; it++) {
					                            factura.ingresarCombo(combo);
					                        }
					                    } else {
					                    	Componente componente = Tienda.getInstance().getComponentePorId(id);
					                        if (componente != null) {
					                            for (int it = 0; it < cantidad; it++) {
					                                factura.ingresarComponente(componente);
					                            }
						                    }
						                };
						            }
					            }
					            try {
					                Tienda.getInstance().agregarFactura(factura);
					                JOptionPane.showMessageDialog(RegFactura.this, 
					                    "Factura creada exitosamente", 
					                    "Éxito", 
					                    JOptionPane.INFORMATION_MESSAGE);
					                dispose();
					            } catch (ObjectAlreadyExistsException ex) {
					                JOptionPane.showMessageDialog(RegFactura.this, 
					                    "Error al crear la factura: " + ex.getMessage(), 
					                    "Error", 
					                    JOptionPane.ERROR_MESSAGE);
					            }
						}
					 }
				});
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
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

	protected void calcularTotal() {
		double total = 0;
		for (int i = 0; i < modelo.getRowCount(); i++) {
	        int cantidadComp = (Integer) modelo.getValueAt(i, 2);
	        double precioComp = (Double) modelo.getValueAt(i, 3);
	        total += cantidadComp * precioComp;
	        
	    }
		
		lblTotalCalculado.setText("$"+String.format("%.2f",total));
	}
}
