package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Factura;
import logico.Tienda;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class ListarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTable tblFacturas;
	private DefaultTableModel modelo;
	private Object [] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarFactura dialog = new ListarFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarFactura() {
		setTitle("Facturas Existentes");
		
		getContentPane().setLayout(new BorderLayout());
		dim = getToolkit().getScreenSize();
        setSize(dim.width/3,dim.height/3);
        setLocationRelativeTo(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		{
			tblFacturas = new JTable();
			modelo = new DefaultTableModel();
			contentPanel.add(tblFacturas);
			String[] headers = {"ID", "Cliente", "Fecha", "Total"};
			modelo.setColumnIdentifiers(headers);
			tblFacturas.setModel(modelo);
			scrollPane.setViewportView(tblFacturas);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadFacturas();
	}
	private void loadFacturas() {
		modelo.setRowCount(0);
		row = new Object[tblFacturas.getColumnCount()];
		for(Factura factura : Tienda.getInstance().getFacturas()) {
			row[0]=factura.getId_factura();
			row[1]=factura.getCliente();
			row[2]=factura.getFechaPedido();
			row[3]=factura.getPrecioTotal();
			modelo.addRow(row);
		}
	}
}
