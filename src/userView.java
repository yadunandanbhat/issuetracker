import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userView {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userView window = new userView();
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
	Connection connection = null;
	public userView() {
		connection = sqlConnector.connector(); 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 80, 796, 290);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btnShowAllUsers = new JButton("Show all users");
		btnShowAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails";
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = connection.prepareStatement(query);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllUsers.setBounds(341, 8, 138, 25);
		frame.getContentPane().add(btnShowAllUsers);
		
		JButton btnShowUserProject = new JButton("Show specific group");
		btnShowUserProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails where userGroupID = ?";
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText().toString()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowUserProject.setBounds(136, 38, 184, 25);
		frame.getContentPane().add(btnShowUserProject);
		
		JButton btnShowUserRole = new JButton("Show specific role");
		btnShowUserRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails where userRole = ?";
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = connection.prepareStatement(query);
					pst.setString(1, textField_1.getText().toString());
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowUserRole.setBounds(624, 38, 184, 25);
		frame.getContentPane().add(btnShowUserRole);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(12, 38, 114, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(500, 38, 114, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
