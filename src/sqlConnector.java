import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
 
public class sqlConnector {
	Connection connection = null;
	public static Connection connector() {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/IssueTracker", "yadunandanbhat", "242882");
            return connection;
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e);
        	return null;
        }
	}
}