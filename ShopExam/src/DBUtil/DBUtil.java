package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String myDriver ="oracle.jdbc.driver.OracleDriver";
		String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String myID = "jslhrd46";
		String myPass = "1234";
		
		try {
			Class.forName(myDriver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(myURL, myID, myPass);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
