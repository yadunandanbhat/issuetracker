import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatNordIJTheme.install();
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
		frame.setTitle("Menu - Issue Tracker");
		frame.setBounds(100, 100, 500, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		IconFontSwing.register(FontAwesome.getIconFont());
		
		JButton btnViewHistory = new JButton("View History");
		Icon history = IconFontSwing.buildIcon(FontAwesome.HISTORY, 24, new Color(255, 255, 255));
		btnViewHistory.setIcon(history);
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				historyView.main(null);
			}
		});
		btnViewHistory.setBounds(178, 315, 128, 40);
		frame.getContentPane().add(btnViewHistory);
		
		JButton btnViewDiscussions = new JButton("View Discussions");
		Icon discussion = IconFontSwing.buildIcon(FontAwesome.COMMENTS_O, 24, new Color(255,255,255));
		btnViewDiscussions.setIcon(discussion);
		btnViewDiscussions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				discussionView.main(null);
			}
		});
		btnViewDiscussions.setBounds(164, 259, 156, 40);
		frame.getContentPane().add(btnViewDiscussions);
		
		JButton btnViewUsers = new JButton("View Users");
		Icon users = IconFontSwing.buildIcon(FontAwesome.USERS, 24, new Color(255,255,255));
		btnViewUsers.setIcon(users);
		btnViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userView.main(null);
			}
		});
		btnViewUsers.setBounds(179, 203, 125, 40);
		frame.getContentPane().add(btnViewUsers);
		
		JButton btnViewIssues = new JButton("View Issues");
		Icon issue = IconFontSwing.buildIcon(FontAwesome.BUG, 24, new Color(255,255,255));
		btnViewIssues.setIcon(issue);
		btnViewIssues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				issueView.main(null);
			}
		});
		btnViewIssues.setBounds(173, 147, 137, 40);
		frame.getContentPane().add(btnViewIssues);
		
		JButton btnViewProjects = new JButton("View Projects");
		Icon project = IconFontSwing.buildIcon(FontAwesome.BRIEFCASE, 24, new Color(255,255,255));
		btnViewProjects.setIcon(project);
		btnViewProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectView.main(null);
			}
		});
		btnViewProjects.setBounds(175, 91, 134, 40);
		frame.getContentPane().add(btnViewProjects);
		
		JLabel lblHello = new JLabel("Hello Admin!");
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblHello.setBounds(164, 27, 156, 40);
		frame.getContentPane().add(lblHello);
	}
}
