import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class issueView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					issueView window = new issueView();
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	
	public issueView() {
		connection = sqlConnector.connector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 844, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(16, 48, 70, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(16, 84, 81, 15);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(16, 120, 70, 15);
		frame.getContentPane().add(lblProjectId);
		
		JLabel lblAssignedDeveloper = new JLabel("Assigned Developer");
		lblAssignedDeveloper.setBounds(16, 156, 141, 15);
		frame.getContentPane().add(lblAssignedDeveloper);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(16, 192, 70, 15);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(16, 228, 70, 15);
		frame.getContentPane().add(lblPriority);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(16, 264, 70, 15);
		frame.getContentPane().add(lblType);

		JLabel lblIssueId = new JLabel("Issue ID");
		lblIssueId.setBounds(16, 12, 70, 15);
		frame.getContentPane().add(lblIssueId);
		
		textField = new JTextField();
		textField.setBounds(190, 48, 141, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(190, 84, 141, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(190, 120, 141, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(190, 156, 141, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(190, 12, 141, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		String status[] = {"Open", "Closed"};
		JComboBox comboBox = new JComboBox(status);
		comboBox.setBounds(190, 187, 141, 24);
		frame.getContentPane().add(comboBox);
		
		String priority[] = {"Low", "Medium", "High"};
		JComboBox comboBox_1 = new JComboBox(priority);
		comboBox_1.setBounds(190, 223, 141, 24);
		frame.getContentPane().add(comboBox_1);
		
		String type[] = {"Bug", "Feature", "Error", "Other"};
		JComboBox comboBox_2 = new JComboBox(type);
		comboBox_2.setBounds(190, 259, 141, 24);
		frame.getContentPane().add(comboBox_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 12, 472, 339);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				textField.setText(model.getValueAt(i, 4).toString());
				textField_1.setText(model.getValueAt(i, 5).toString());
				textField_2.setText(model.getValueAt(i, 1).toString());
				textField_3.setText(model.getValueAt(i, 3).toString());
				textField_4.setText(model.getValueAt(i, 0).toString());
				String status = model.getValueAt(i, 7).toString();
					switch (status) {
						case "Open":
							comboBox.setSelectedIndex(0);
							break;
						case "Closed":
							comboBox.setSelectedIndex(1);
							break;
					}
				String priority = model.getValueAt(i, 8).toString();
					switch (priority) {
					case "Low":
						comboBox_1.setSelectedIndex(0);
						break;
					case "Medium":
						comboBox_1.setSelectedIndex(1);
						break;
					case "High":
						comboBox_1.setSelectedIndex(2);
						break;
					}
				String type = model.getValueAt(i, 9).toString();
					switch (type) {
					case "Bug":
						comboBox_2.setSelectedIndex(0);
						break;
					case "Feature":
						comboBox_2.setSelectedIndex(1);
						break;
					case "Error":
						comboBox_2.setSelectedIndex(2);
						break;
					case "Other":
						comboBox_2.setSelectedIndex(3);
						break;
					}
			}
		});
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from issue";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnRefresh.setBounds(242, 318, 100, 25);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText();
				String desc = textField_1.getText();
				String pID = textField_2.getText();
				String assgnD = textField_3.getText();
				String iID = textField_4.getText();
				String status = (String) comboBox.getSelectedItem();
				String priority = (String) comboBox_1.getSelectedItem();
				String type = (String) comboBox_2.getSelectedItem();
				
				String query = "UPDATE `issue` SET `projectID`=?,`issueSubmitter`=?,`assignedDeveloper`=?,`issueTitle`=?,`issueDescription`=?,`issueStatus`=?,`issuePriority`=?,`issueType`=? WHERE `issueID`=?";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(pID));
					pst.setInt(2, 18057);
					pst.setInt(3, Integer.parseInt(assgnD));
					pst.setString(4, title);
					pst.setString(5, desc);
					pst.setString(6, status);
					pst.setString(7, priority);
					pst.setString(8, type);
					pst.setInt(9, Integer.parseInt(iID));
					pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnUpdate.setBounds(128, 318, 100, 25);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText();
				String desc = textField_1.getText();
				String pID = textField_2.getText();
				String assgnD = textField_3.getText();
				String status = (String) comboBox.getSelectedItem();
				String priority = (String) comboBox_1.getSelectedItem();
				String type = (String) comboBox_2.getSelectedItem();
				
				String query = "insert into issue (`projectID`, `issueSubmitter`, `assignedDeveloper`, `issueTitle`, `issueDescription`, `issueStatus`, `issuePriority`, `issueType`) values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(pID));
					pst.setInt(2, 18057);
					pst.setInt(3, Integer.parseInt(assgnD));
					pst.setString(4, title);
					pst.setString(5, desc);
					pst.setString(6, status);
					pst.setString(7, priority);
					pst.setString(8, type);
					pst.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnInsert.setBounds(16, 318, 100, 25);
		frame.getContentPane().add(btnInsert);
		
	}
}
