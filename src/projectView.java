import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class projectView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projectView window = new projectView();
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
	public projectView() {
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
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(12, 24, 70, 15);
		frame.getContentPane().add(lblProjectId);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 56, 70, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(12, 88, 81, 15);
		frame.getContentPane().add(lblSummary);
		
		JLabel lblMaintainer = new JLabel("Maintainer");
		lblMaintainer.setBounds(12, 225, 81, 15);
		frame.getContentPane().add(lblMaintainer);
		
		JLabel lblAssignedGroup = new JLabel("Assigned Group");
		lblAssignedGroup.setBounds(12, 257, 112, 15);
		frame.getContentPane().add(lblAssignedGroup);
		
		JLabel lblDateCreated = new JLabel("Date Created");
		lblDateCreated.setBounds(12, 289, 95, 15);
		frame.getContentPane().add(lblDateCreated);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(147, 24, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 56, 150, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		textArea.setBounds(147, 88, 150, 120);
		frame.getContentPane().add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 12, 448, 371);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				textField.setText(model.getValueAt(i, 0).toString());
				textField_1.setText(model.getValueAt(i, 2).toString());
				textArea.setText(model.getValueAt(i, 3).toString());
				textField_2.setText(model.getValueAt(i, 1).toString());
				textField_3.setText(model.getValueAt(i, 5).toString());
				textField_4.setText(model.getValueAt(i, 4).toString());
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		textField_2 = new JTextField();
		textField_2.setBounds(147, 225, 150, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(146, 257, 151, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(147, 289, 150, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField_1.getText();
				String summary = textArea.getText();
				String maintainer = textField_2.getText();
				String gID = textField_3.getText();
				
				String query = "INSERT INTO `project`(`projectMaintainer`, `projectTitle`, `projectSummary`, `projectGroup`) VALUES (?, ?, ?, ?)";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(maintainer));
					pst.setString(2, title);
					pst.setString(3, summary);
					pst.setInt(4, Integer.parseInt(gID));
					pst.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnInsert.setBounds(12, 330, 100, 25);
		frame.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String projectID = textField.getText();
				String title = textField_1.getText();
				String summary = textArea.getText();
				String maintainer = textField_2.getText();
				String gID = textField_3.getText();
				
				String query = "UPDATE `project` SET `projectMaintainer`=?,`projectTitle`=?,`projectSummary`=?,`projectGroup`=? WHERE `projectID`=?";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(maintainer));
					pst.setString(2, title);
					pst.setString(3, summary);
					pst.setInt(4, Integer.parseInt(gID));
					pst.setInt(5, Integer.parseInt(projectID));
					pst.executeUpdate();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnUpdate.setBounds(130, 330, 100, 25);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from project";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnRefresh.setBounds(250, 330, 100, 25);
		frame.getContentPane().add(btnRefresh);
	}
}
