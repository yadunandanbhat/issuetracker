

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txtRequired;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatNordIJTheme.install();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		IconFontSwing.register(FontAwesome.getIconFont());
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Login - Issue Tracker");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtRequired = new JTextField();
		txtRequired.setBounds(191, 45, 180, 25);
		frame.getContentPane().add(txtRequired);
		txtRequired.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(191, 102, 180, 25);
		frame.getContentPane().add(passwordField);
		
		JLabel lbluserName = new JLabel("Username");
		lbluserName.setBounds(96, 45, 70, 14);
		frame.getContentPane().add(lbluserName);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(96, 102, 70, 14);
		frame.getContentPane().add(lblpassword);
		
		btnLogin = new JButton("Login");
		Icon login = IconFontSwing.buildIcon(FontAwesome.SIGN_IN, 15, new Color(255,255,255));
		btnLogin.setIcon(login);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtRequired.getText();
				String password = passwordField.getText();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Username can't be empty");
				}
				else {
					if (password.equals("")) {
						JOptionPane.showMessageDialog(null, "Password can't be empty");
					}
					else {
						if (username.equals("admin") && password.equals("admin")) {
							JOptionPane.showMessageDialog(null, "Successfully logged in!");
							Main main = new Main();
							frame.dispose();
							main.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong username/password!");
						}
					}
				}
			}
		});
		btnLogin.setBounds(144, 195, 146, 32);
		frame.getContentPane().add(btnLogin);
	}
}