package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class RegDiscoDuro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dimension dim=null;
	private JTextField txtCodigo;


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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblid_componente = new JLabel("Codigo");
		lblid_componente.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblid_componente.setBounds(18, 16, 61, 16);
		contentPanel.add(lblid_componente);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(70, 11, 95, 26);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		dim = getToolkit().getScreenSize();
        setSize(678,483);
        setLocationRelativeTo(null);
        setResizable(false);
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
	}
}
