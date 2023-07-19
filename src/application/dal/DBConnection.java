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
	
	private DBConnection() {
		try {
			
			testDBConnection = DriverManager.getConnection(jdbcPathURL + Databases.TEST.getValue());
			userInfoDBConnection = DriverManager.getConnection(jdbcPathURL + Databases.USER_INFO.getValue());
			
		} catch (Exception ex) {
			
			System.out.println("Failed to connect to SQLite database");
			ex.printStackTrace();
		}
	}
	
	public static DBConnection getDBConnectionSingletonInstance() {
		return dbConnection;
	}
	
	
	public static Connection getDBConnection(Databases db) throws Exception {
		Connection connection = null;
		switch (db) {
			case TEST:
				connection = testDBConnection;
				break;
			case USER_INFO:
				connection = userInfoDBConnection;
				break;
			default:
				throw new Exception("The database you wish to connect to is not supported!");
		}
		
		return connection;
	}
	
	
	
	public enum Databases {
		TEST("test.sqlite"),
		USER_INFO("user-info.sqlite");
		
		private final String db;
		
		private Databases(String db) {
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