import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
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
		FlatNordIJTheme.install();
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
		frame = new JFrame();
		frame.setTitle("Users - Issue Tracker");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 576);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		IconFontSwing.register(FontAwesome.getIconFont());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 11, 625, 515);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btnShowAllUsers = new JButton("Show all users");
		Icon users = IconFontSwing.buildIcon(FontAwesome.USERS, 24, new Color(255, 255, 255));
		btnShowAllUsers.setIcon(users);
		btnShowAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails";
				PreparedStatement pst;
				ResultSet rs;
				TableColumnModel tcm;
				try {
					pst = connection.prepareStatement(query);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(100);
					tcm.getColumn(1).setPreferredWidth(200);
					tcm.getColumn(2).setPreferredWidth(200);
					tcm.getColumn(3).setPreferredWidth(100);
					tcm.getColumn(4).setPreferredWidth(200);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllUsers.setBounds(117, 146, 138, 40);
		frame.getContentPane().add(btnShowAllUsers);
		
		JButton btnShowUserProject = new JButton("Show specific group");
		Icon userGroup = IconFontSwing.buildIcon(FontAwesome.USER_O, 24, new Color(255, 255, 255));
		btnShowUserProject.setIcon(userGroup);
		btnShowUserProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails where userGroupID = ?";
				PreparedStatement pst;
				ResultSet rs;
				TableColumnModel tcm;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText().toString()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(100);
					tcm.getColumn(1).setPreferredWidth(200);
					tcm.getColumn(2).setPreferredWidth(200);
					tcm.getColumn(3).setPreferredWidth(100);
					tcm.getColumn(4).setPreferredWidth(200);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowUserProject.setBounds(155, 226, 184, 40);
		frame.getContentPane().add(btnShowUserProject);
		
		JButton btnShowUserRole = new JButton("Show specific role");
		Icon userRole = IconFontSwing.buildIcon(FontAwesome.BLACK_TIE, 24, new Color(255, 255, 255));
		btnShowUserRole.setIcon(userRole);
		btnShowUserRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from userDetails where userRole = ?";
				PreparedStatement pst;
				ResultSet rs;
				TableColumnModel tcm;
				try {
					pst = connection.prepareStatement(query);
					pst.setString(1, textField_1.getText().toString());
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(100);
					tcm.getColumn(1).setPreferredWidth(200);
					tcm.getColumn(2).setPreferredWidth(200);
					tcm.getColumn(3).setPreferredWidth(100);
					tcm.getColumn(4).setPreferredWidth(200);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowUserRole.setBounds(155, 306, 184, 40);
		frame.getContentPane().add(btnShowUserRole);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(31, 226, 114, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(31, 306, 114, 40);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}