import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class discussionView {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					discussionView window = new discussionView();
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
	public discussionView() {
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

		JLabel lblEnterSpecificIssueid = new JLabel("Enter specific issueID");
		lblEnterSpecificIssueid.setBounds(25, 15, 167, 15);
		frame.getContentPane().add(lblEnterSpecificIssueid);
		
		textField = new JTextField();
		textField.setBounds(203, 12, 114, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnShowTheSpecefic = new JButton("Show comments");
		btnShowTheSpecefic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from comments where issueID = ?";
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowTheSpecefic.setBounds(335, 12, 149, 25);
		frame.getContentPane().add(btnShowTheSpecefic);
		
		JButton btnShowAttachments = new JButton("Show attachments");
		btnShowAttachments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from attachments where issueID = ?";
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAttachments.setBounds(502, 12, 167, 25);
		frame.getContentPane().add(btnShowAttachments);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(130, 48, 240, 80);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(450, 48, 240, 80);
		frame.getContentPane().add(textArea_1);
		
		JButton btnAddComment = new JButton("Add comment");
		btnAddComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO `comments`(`commenter`, `issueID`, `message`) VALUES (18057, ?, ?)";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					pst.setString(2, textArea.getText());
					pst.execute();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddComment.setBounds(176, 134, 149, 25);
		frame.getContentPane().add(btnAddComment);
		
		JButton btnAddAttachment = new JButton("Add attachment");
		btnAddAttachment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO `attachments`(`uploader`, `issueID`, `fileLink`) VALUES (18057, ?, ?)";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					pst.setString(2, textArea_1.getText());
					pst.execute();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddAttachment.setBounds(487, 134, 167, 25);
		frame.getContentPane().add(btnAddAttachment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 174, 796, 197);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
	}
}
