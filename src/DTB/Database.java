package DTB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String db_svname = "LAPTOP-SJLNOI03";
	private static final String db_username = "sa";
	private static final String db_password = "123456";
	private static final String db_dbname = "ContactManagement";
	
	public static Connection getConnection() {
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String db_url = "jdbc:sqlserver://" + db_svname + ":1433" +
		";databaseName="+ db_dbname +";encrypt= true ; trustServerCertificate = true";
		
		return DriverManager.getConnection(db_url, db_username, db_password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
