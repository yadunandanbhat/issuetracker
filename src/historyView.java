import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
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
		FlatNordIJTheme.install();
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
	private JButton btnShowAllDeleted;
	/**
	 * Create the application.
	 */
	public historyView() {
			/**
	 * Initialize the contents of the frame.
	 */
		connection = sqlConnector.connector();
		frame = new JFrame();
		frame.setTitle("History - Issue Tracker");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 576);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		IconFontSwing.register(FontAwesome.getIconFont());
		
		btnLoadData = new JButton("Refresh");
		Icon refresh = IconFontSwing.buildIcon(FontAwesome.REFRESH, 15, new Color(255,255,255));
		btnLoadData.setIcon(refresh);
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
				} catch (SQLException e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnLoadData.setBounds(190, 24, 146, 32);
		frame.getContentPane().add(btnLoadData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 67, 986, 459);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		btnShowAllInserted = new JButton("Show all inserted");
		Icon insert = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 15, new Color(255,255,255));
		btnShowAllInserted.setIcon(insert);
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
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnShowAllInserted.setBounds(346, 24, 146, 32);
		frame.getContentPane().add(btnShowAllInserted);
		
		btnShowAllUpdated = new JButton("Show all updated");
		Icon update = IconFontSwing.buildIcon(FontAwesome.UPLOAD, 15, new Color(255,255,255));
		btnShowAllUpdated.setIcon(update);
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
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
			}
		});
		btnShowAllUpdated.setBounds(502, 24, 146, 32);
		frame.getContentPane().add(btnShowAllUpdated);
		
		btnShowAllDeleted = new JButton("Show all deleted");
		Icon delete = IconFontSwing.buildIcon(FontAwesome.TIMES, 15, new Color (255,255,255));
		btnShowAllDeleted.setIcon(delete);
		btnShowAllDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "call ShowAll('Delete')";
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
				} catch (SQLException e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
			}
		});
		btnShowAllDeleted.setBounds(658, 24, 146, 32);
		frame.getContentPane().add(btnShowAllDeleted);
	}
}
