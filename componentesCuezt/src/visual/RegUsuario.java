package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logico.Tienda;

import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField pwdPassword;
	private JLabel lblWarning;
	JComboBox<String> cboTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//RegUsuario dialog = new RegUsuario();
			Tienda.getInstance();
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario() {
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLogin = new JLabel("Username: ");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblLogin);
		
		txtUser = new JTextField();
		
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					registrar();
				}
			}
		});
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		lblWarning = new JLabel("Usuario ya existe.");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setVisible(false);
		lblWarning.setForeground(Color.RED);
		contentPanel.add(lblWarning);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					registrar();
				}
			}
		});
		pwdPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(pwdPassword);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblTipo);
		
		cboTipo = new JComboBox<String>();
		cboTipo.addItem("Empleado");
		cboTipo.addItem("Registrador");
		contentPanel.add(cboTipo);
		
		JSeparator separator = new JSeparator();
		contentPanel.add(separator);
		contentPanel.add(btnLogin);
	}
	protected void registrar() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		txtUser.setText("");
		pwdPassword.setText("");
	}
	}

