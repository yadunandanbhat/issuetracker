import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class historyView {

	private JFrame frame;
	private JButton btnLoadData;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historyView window = new historyView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JButton btnShowAllInserted;
	private JButton btnShowAllUpdated;
	/**
	 * Create the application.
	 */
	public historyView() {
			/**
	 * Initialize the contents of the frame.
	 */
		connection = sqlConnector.connector();
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 420);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from history";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel tcm= table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(80);
					tcm.getColumn(3).setPreferredWidth(80);
					tcm.getColumn(4).setPreferredWidth(80);
					tcm.getColumn(5).setPreferredWidth(80);
					tcm.getColumn(6).setPreferredWidth(200);
					tcm.getColumn(7).setPreferredWidth(85);
					tcm.getColumn(8).setPreferredWidth(85);
					tcm.getColumn(9).setPreferredWidth(85);
					tcm.getColumn(10).setPreferredWidth(150);
				} catch (SQLException e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLoadData.setBounds(342, 12, 117, 25);
		frame.getContentPane().add(btnLoadData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 67, 796, 304);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		btnShowAllInserted = new JButton("Show all inserted");
		btnShowAllInserted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "call ShowAll('Insert')";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel tcm= table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(80);
					tcm.getColumn(3).setPreferredWidth(80);
					tcm.getColumn(4).setPreferredWidth(80);
					tcm.getColumn(5).setPreferredWidth(80);
					tcm.getColumn(6).setPreferredWidth(200);
					tcm.getColumn(7).setPreferredWidth(85);
					tcm.getColumn(8).setPreferredWidth(85);
					tcm.getColumn(9).setPreferredWidth(85);
					tcm.getColumn(10).setPreferredWidth(150);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllInserted.setBounds(172, 30, 158, 25);
		frame.getContentPane().add(btnShowAllInserted);
		
		btnShowAllUpdated = new JButton("Show all updated");
		btnShowAllUpdated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "call ShowAll('Update')";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					TableColumnModel tcm= table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(80);
					tcm.getColumn(3).setPreferredWidth(80);
					tcm.getColumn(4).setPreferredWidth(80);
					tcm.getColumn(5).setPreferredWidth(80);
					tcm.getColumn(6).setPreferredWidth(200);
					tcm.getColumn(7).setPreferredWidth(85);
					tcm.getColumn(8).setPreferredWidth(85);
					tcm.getColumn(9).setPreferredWidth(85);
					tcm.getColumn(10).setPreferredWidth(150);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllUpdated.setBounds(471, 30, 158, 25);
		frame.getContentPane().add(btnShowAllUpdated);
	}
}
