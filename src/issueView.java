import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import com.bulenkov.darcula.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import jiconfont.IconFont;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class issueView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
	    } catch(Exception ignored){}
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
	private JTextArea textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	
	public issueView() {
		IconFontSwing.register(FontAwesome.getIconFont());
		connection = sqlConnector.connector();
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 420);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(16, 48, 70, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(16, 84, 81, 15);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(16, 166, 70, 15);
		frame.getContentPane().add(lblProjectId);
		
		JLabel lblAssignedDeveloper = new JLabel("Assigned Developer");
		lblAssignedDeveloper.setBounds(16, 202, 141, 15);
		frame.getContentPane().add(lblAssignedDeveloper);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(16, 238, 70, 15);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(16, 310, 70, 15);
		frame.getContentPane().add(lblPriority);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(16, 274, 70, 15);
		frame.getContentPane().add(lblType);

		JLabel lblIssueId = new JLabel("Issue ID");
		lblIssueId.setBounds(16, 12, 70, 15);
		frame.getContentPane().add(lblIssueId);
		
		textField = new JTextField();
		textField.setBounds(190, 48, 141, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextArea();
		textField_1.setLineWrap(true);
		textField_1.setBounds(190, 84, 141, 60);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(190, 166, 141, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(190, 202, 141, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(190, 12, 141, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		String status[] = {"Open", "Closed"};
		JComboBox comboBox = new JComboBox(status);
		comboBox.setBounds(190, 238, 141, 24);
		frame.getContentPane().add(comboBox);
		
		String priority[] = {"Low", "Medium", "High"};
		JComboBox comboBox_1 = new JComboBox(priority);
		comboBox_1.setBounds(190, 310, 141, 24);
		frame.getContentPane().add(comboBox_1);
		
		String type[] = {"Bug", "Feature", "Error", "Other"};
		JComboBox comboBox_2 = new JComboBox(type);
		comboBox_2.setBounds(190, 274, 141, 24);
		frame.getContentPane().add(comboBox_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 12, 448, 359);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
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
		Icon refresh = IconFontSwing.buildIcon(FontAwesome.REFRESH, 15, new Color(255,255,255));
		btnRefresh.setIcon(refresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from issue";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(70);
					tcm.getColumn(1).setPreferredWidth(70);
					tcm.getColumn(2).setPreferredWidth(100);
					tcm.getColumn(3).setPreferredWidth(100);
					tcm.getColumn(4).setPreferredWidth(200);
					tcm.getColumn(5).setPreferredWidth(300);
					tcm.getColumn(6).setPreferredWidth(150);
					tcm.getColumn(7).setPreferredWidth(85);
					tcm.getColumn(8).setPreferredWidth(85);
					tcm.getColumn(9).setPreferredWidth(85);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnRefresh.setBounds(242, 346, 100, 25);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnUpdate = new JButton("Update");
		Icon update = IconFontSwing.buildIcon(FontAwesome.UPLOAD, 15, new Color(255,255,255));
		btnUpdate.setIcon(update);
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
		btnUpdate.setBounds(128, 346, 100, 25);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnInsert = new JButton("Insert");
		Icon insert = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 15, new Color(255,255,255));
		btnInsert.setIcon(insert);
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
		btnInsert.setBounds(16, 346, 100, 25);
		frame.getContentPane().add(btnInsert);
	}
}