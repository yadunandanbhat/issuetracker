import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
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
		frame.setSize(745, 390);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from history";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLoadData.setBounds(314, 0, 117, 25);
		frame.getContentPane().add(btnLoadData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 67, 721, 274);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		btnShowAllInserted = new JButton("Show all inserted");
		btnShowAllInserted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "call ShowAll('Insert')";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllInserted.setBounds(150, 30, 158, 25);
		frame.getContentPane().add(btnShowAllInserted);
		
		btnShowAllUpdated = new JButton("Show all updated");
		btnShowAllUpdated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "call ShowAll('Update')";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAllUpdated.setBounds(437, 30, 158, 25);
		frame.getContentPane().add(btnShowAllUpdated);
	}
}
