package application.dal;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

// Singleton
public class DBConnection {
	private static final String jdbcPathURL = "jdbc:sqlite:resources/sqlite/test.sqlite";
	
	private static DBConnection dbConnection = new DBConnection();
	private static Connection connection;
	
	private DBConnection() {
		try {
			
			connection = DriverManager.getConnection(jdbcPathURL);
			
		} catch (Exception ex) {
			
			System.out.println("Failed to connect to SQLite database");
			ex.printStackTrace();
		}
	}
	
	public static DBConnection getDBConnection() {
		return dbConnection;
	}
	
	
	
	public void test() {
		printNames();
//		insertEntry(3, "Jean", 50);
		printNames();
	}
	
	private void printNames() {
		try {
			String query = "SELECT * FROM NAMES";
			
			Statement statement = connection.createStatement();
			ResultSet queryOutput = statement.executeQuery(query);
			
			while (queryOutput.next()) {
				System.out.println(queryOutput.getString("name"));
			}
			
		} catch (Exception ex) {
			System.out.println("Query failed!");
			ex.printStackTrace();
		}
	}
	
	private void insertEntry(int id, String name, int age) {
		try {
			
			String update = "INSERT INTO NAMES (id, name, age) VALUES (?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(update);
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