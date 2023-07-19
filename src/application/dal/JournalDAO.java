package application.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.dal.DBConnection.Database;

public class JournalDAO {

	public void createJournal(String title, String date, int hour, int minute, String context) {
		String updateQuery = "INSERT INTO journal (title, date, hour, minute, context) VALUES (?, ?, ?, ?, ?)";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Database.JOURNALS);
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setString(1, title);
			statement.setString(2, date);
			statement.setInt(3, hour);
			statement.setInt(4, minute);
			statement.setString(5, context);
			
			int rowsAdded = statement.executeUpdate();
			if (rowsAdded == 0) {
				System.out.println("Could not add journal to database!");
			}
			else {
				System.out.println("Added journal to DB!");	
			}
			
		} catch (Exception ex) {
			System.out.println("Failed to add journal to database!");
			ex.printStackTrace();
		}
	}
	
	public void getJournals() {
		String query = "SELECT * FROM journal";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Database.JOURNALS);
			Statement statement = connection.createStatement();
			
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				System.out.println(results.getString("title"));
			}
			
			
		} catch (Exception ex) {
			System.out.println("Failed to retrieve entries from journal_db.sqlite");
			ex.printStackTrace();
		}
	}
	
}
