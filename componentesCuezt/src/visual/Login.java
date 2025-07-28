package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Log In");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLogin = new JLabel("Username: ");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblLogin);
		
		txtUser = new JTextField();
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(pwdPassword);
		
		JLabel lblWarning = new JLabel("Usuario o Contrase\u00F1a incorrecta!");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setVisible(false);
		lblWarning.setForeground(Color.RED);
		contentPanel.add(lblWarning);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tienda.getInstance().login(txtUser.getText(), pwdPassword.getText())) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}else {
					lblWarning.setVisible(true);
					clean();
				}
			}
		});
		contentPanel.add(btnLogin);
	}
	public void clean() {
		txtUser.setText("");
		pwdPassword.setText("");
	}
}
