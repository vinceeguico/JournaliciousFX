package application.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Singleton
public class DBConnection {
	private static final String jdbcPathURL = "jdbc:sqlite:resources/sqlite/";
	
	private static DBConnection dbConnection = new DBConnection();
	
	private static Connection userInfoDBConnection;
	private static Connection journalsDBConnection;
	
	private DBConnection() {
		try {
	
			userInfoDBConnection = DriverManager.getConnection(jdbcPathURL + Database.USER_INFO.getValue());
			journalsDBConnection = DriverManager.getConnection(jdbcPathURL + Database.JOURNALS.getValue());
			
		} catch (Exception ex) {
			
			System.out.println("Failed to connect to SQLite database");
			ex.printStackTrace();
		}
	}
	
	public static DBConnection getDBConnectionSingletonInstance() {
		return dbConnection;
	}
	
	
	public static Connection getDBConnection(Database db) throws Exception {
		Connection connection = null;
		switch (db) {
			case USER_INFO:
				connection = userInfoDBConnection;
				break;
			case JOURNALS:
				connection = journalsDBConnection;
				break;
			default:
				throw new Exception("The database you wish to connect to is not supported!");
		}
		
		return connection;
	}
	
	
	
	public enum Database {
		USER_INFO("user_info_db.sqlite"),
		JOURNALS("journals_db.sqlite");
		
		private final String db;
		
		private Database(String db) {
			this.db = db;
		}

		public String getValue() {
			return this.db;
		}
	}
	
}