import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				historyView.main(null);
			}
		});
		btnViewHistory.setBounds(163, 215, 123, 25);
		frame.getContentPane().add(btnViewHistory);
		
		JButton btnViewDiscussions = new JButton("View Discussions");
		btnViewDiscussions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				discussionView.main(null);
			}
		});
		btnViewDiscussions.setBounds(147, 167, 156, 25);
		frame.getContentPane().add(btnViewDiscussions);
		
		JButton btnViewUsers = new JButton("View Users");
		btnViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userView.main(null);
			}
		});
		btnViewUsers.setBounds(166, 119, 117, 25);
		frame.getContentPane().add(btnViewUsers);
		
		JButton btnViewIssues = new JButton("View Issues");
		btnViewIssues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				issueView.main(null);
			}
		});
		btnViewIssues.setBounds(156, 71, 137, 25);
		frame.getContentPane().add(btnViewIssues);
		
		JButton btnViewProjects = new JButton("View Projects");
		btnViewProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectView.main(null);
			}
		});
		btnViewProjects.setBounds(158, 23, 134, 25);
		frame.getContentPane().add(btnViewProjects);
	}
}
