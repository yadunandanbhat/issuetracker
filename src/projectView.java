import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.mysql.cj.util.StringUtils;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.Icon;
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
		FlatNordIJTheme.install();
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
		frame = new JFrame();
		frame.setTitle("Projects - Issue Tracker");
		frame.setResizable(false);
		IconFontSwing.register(FontAwesome.getIconFont());
		frame.setBounds(100, 100, 1024, 576);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(12, 24, 70, 15);
		frame.getContentPane().add(lblProjectId);
		
		JLabel lblTitle = new JLabel("Title *");
		lblTitle.setBounds(12, 56, 81, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(12, 88, 81, 15);
		frame.getContentPane().add(lblSummary);
		
		JLabel lblMaintainer = new JLabel("Maintainer *");
		lblMaintainer.setBounds(12, 225, 112, 15);
		frame.getContentPane().add(lblMaintainer);
		
		JLabel lblAssignedGroup = new JLabel("Assigned Group *");
		lblAssignedGroup.setBounds(12, 257, 112, 15);
		frame.getContentPane().add(lblAssignedGroup);
		
		JLabel lblDateCreated = new JLabel("Date Created");
		lblDateCreated.setBounds(12, 289, 95, 15);
		frame.getContentPane().add(lblDateCreated);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(147, 24, 190, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 56, 190, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		textArea.setBounds(147, 88, 190, 120);
		frame.getContentPane().add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 11, 625, 515);
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
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		textField_2 = new JTextField();
		textField_2.setBounds(147, 225, 190, 25);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(147, 257, 190, 25);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(147, 289, 190, 25);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		Icon insert = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 15, new Color(255,255,255));
		btnInsert.setIcon(insert);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField_1.getText();
				String summary = textArea.getText();
				String maintainer = textField_2.getText();
				String gID = textField_3.getText();
				
				if (title.isBlank()) {
					JOptionPane.showMessageDialog(null, "Title shouldn't be empty!");
				} else {
					if (maintainer=="" || (!maintainer.matches("[0-9]+"))){
						JOptionPane.showMessageDialog(null, "Maintainer shouldn't be empty or characters!");
					}
					else {
						if (gID=="" || (!gID.matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "User Group ID shouldn't be empty or characters!");
						}
						else {
							String query = "INSERT INTO `project`(`projectMaintainer`, `projectTitle`, `projectSummary`, `projectGroup`) VALUES (?, ?, ?, ?)";
							PreparedStatement pst;
							try {
								pst = connection.prepareStatement(query);
								pst.setInt(1, Integer.parseInt(maintainer));
								pst.setString(2, title);
								pst.setString(3, summary);
								pst.setInt(4, Integer.parseInt(gID));
								pst.execute();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);
							}
						}
					}
				}
			}
		});
		btnInsert.setBounds(113, 333, 146, 32);
		frame.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		Icon update = IconFontSwing.buildIcon(FontAwesome.UPLOAD, 15, new Color(255,255,255));
		btnUpdate.setIcon(update);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String projectID = textField.getText();
				String title = textField_1.getText();
				String summary = textArea.getText();
				String maintainer = textField_2.getText();
				String gID = textField_3.getText();
				
				if (title.isBlank()) {
					JOptionPane.showMessageDialog(null, "Title shouldn't be empty!");
				} else {
					if (maintainer=="" || (!maintainer.matches("[0-9]+"))){
						JOptionPane.showMessageDialog(null, "Maintainer shouldn't be empty or have characters!");
					}
					else {
						if (gID=="" || (!gID.matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "User Group ID shouldn't be empty or have  characters!");
						}
						else {
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
								} catch (SQLException e2) {
									JOptionPane.showMessageDialog(null, e2);
								}
							}
						}
					}
				}
		});
		btnUpdate.setBounds(113, 384, 146, 32);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnRefresh = new JButton("Refresh");
		Icon refresh = IconFontSwing.buildIcon(FontAwesome.REFRESH, 15, new Color(255,255,255));
		btnRefresh.setIcon(refresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from project";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(150);
					tcm.getColumn(3).setPreferredWidth(250);
					tcm.getColumn(4).setPreferredWidth(150);
					tcm.getColumn(5).setPreferredWidth(80);
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
			}
		});
		btnRefresh.setBounds(113, 435, 146, 32);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnDelete = new JButton("Delete");
		Icon delete = IconFontSwing.buildIcon(FontAwesome.TIMES, 15, new Color (255,255,255));
		btnDelete.setIcon(delete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String projectID = textField.getText();
				String query = "DELETE FROM `project` WHERE `projectID` = ?";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(projectID));
					pst.execute();
					} catch (SQLException e4) {
						JOptionPane.showMessageDialog(null, e4);
					}
			}
		});
		btnDelete.setBounds(113, 486, 146, 32);
		frame.getContentPane().add(btnDelete);
	}
}