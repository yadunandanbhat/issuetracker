import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
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
		FlatNordIJTheme.install();
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
		frame = new JFrame();
		frame.setTitle("Discussions - Issue Tracker");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 576);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		IconFontSwing.register(FontAwesome.getIconFont());

		JLabel lblEnterSpecificIssueid = new JLabel("Enter specific issueID");
		lblEnterSpecificIssueid.setBounds(360, 20, 124, 15);
		frame.getContentPane().add(lblEnterSpecificIssueid);
		
		textField = new JTextField();
		textField.setBounds(494, 15, 146, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnShowTheSpecefic = new JButton("Show comments");
		Icon showComment = IconFontSwing.buildIcon(FontAwesome.COMMENT, 15, new Color(255, 255, 255));
		btnShowTheSpecefic.setIcon(showComment);
		btnShowTheSpecefic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from comments where issueID = ?";
				PreparedStatement pst;
				ResultSet rs;
				TableColumnModel tcm;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(300);
					tcm.getColumn(3).setPreferredWidth(250);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowTheSpecefic.setBounds(223, 53, 146, 32);
		frame.getContentPane().add(btnShowTheSpecefic);
		
		JButton btnShowAttachments = new JButton("Show attachments");
		Icon showAttachments = IconFontSwing.buildIcon(FontAwesome.FILE_ARCHIVE_O, 15, new Color(255, 255, 255));
		btnShowAttachments.setIcon(showAttachments);
		btnShowAttachments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from attachments where issueID = ?";
				PreparedStatement pst;
				ResultSet rs;
				TableColumnModel tcm;
				try {
					pst = connection.prepareStatement(query);
					pst.setInt(1, Integer.parseInt(textField.getText()));
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(80);
					tcm.getColumn(1).setPreferredWidth(80);
					tcm.getColumn(2).setPreferredWidth(300);
					tcm.getColumn(3).setPreferredWidth(250);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnShowAttachments.setBounds(633, 53, 152, 32);
		frame.getContentPane().add(btnShowAttachments);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(176, 96, 240, 80);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(592, 96, 240, 80);
		frame.getContentPane().add(textArea_1);
		
		JButton btnAddComment = new JButton("Add comment");
		Icon addComment = IconFontSwing.buildIcon(FontAwesome.COMMENTING, 15, new Color(255, 255, 255));
		btnAddComment.setIcon(addComment);
		btnAddComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textArea.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Comment can't be blank while inserting!");
				} else {
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
			}
		});
		btnAddComment.setBounds(223, 187, 146, 32);
		frame.getContentPane().add(btnAddComment);
		
		JButton btnAddAttachment = new JButton("Add attachment");
		Icon addAttachment = IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, 15, new Color(255, 255, 255));
		btnAddAttachment.setIcon(addAttachment);
		btnAddAttachment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textArea.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Attachment can't be blank while inserting!");
				} else {
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
			}
		});
		btnAddAttachment.setBounds(639, 187, 146, 32);
		frame.getContentPane().add(btnAddAttachment);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 229, 988, 297);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
	}
}