package application.dal;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

// Singleton
public class DBConnection {
	private static final String jdbcPathURL = "jdbc:sqlite:resources/sqlite/";
	
	private static DBConnection dbConnection = new DBConnection();
	
	private static Connection testDBConnection;
	private static Connection userInfoDBConnection;
	private static Connection journalsDBConnection;
	
	private DBConnection() {
		try {
			
			testDBConnection = DriverManager.getConnection(jdbcPathURL + Database.TEST.getValue());
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
			case TEST:
				connection = testDBConnection;
				break;
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
		TEST("test.sqlite"),
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
	
	
	
	public static void test() {
		printNames();
	}
	
	private static void printNames() {
		try {
			String query = "SELECT * FROM NAMES";
			
			Statement statement = testDBConnection.createStatement();
			ResultSet queryOutput = statement.executeQuery(query);
			
			while (queryOutput.next()) {
				System.out.println(queryOutput.getString("name"));
			}
			
		} catch (Exception ex) {
			System.out.println("Query failed!");
			ex.printStackTrace();
		}
	}
	
	private static void insertEntry(int id, String name, int age) {
		try {
			
			String update = "INSERT INTO NAMES (id, name, age) VALUES (?, ?, ?)";
			
			PreparedStatement statement = testDBConnection.prepareStatement(update);
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setInt(3, age);
			
			statement.executeUpdate();
			System.out.println("DB Updated!");
			
		} catch (Exception ex) {
			System.out.println("Could not create new database entry!");
			ex.printStackTrace();
		}
	}
	
}