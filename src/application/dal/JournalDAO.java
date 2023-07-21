package application.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.dal.DBConnection.Database;

/**
 * A class that faciliates interacting and manipulating journal data
 * that is stored in the "journals" database
 */
public class JournalDAO {

	/**
	 * Creates a journal entry and inserts it into the journals database
	 * 
	 * @param title the title of the journal entry in string format
	 * @param date the date of the journal entry in string format
	 * @param hour the hour of the time the journal entry was created in integer format
	 * @param minute the minute of the time the journal entry was created in integer format
	 * @param context the context of the journal entry in string format
	 */
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
	
	/**
	 * Experimental method for gathering journal entries from the database.
	 * Gets the context of every journal entry in the DB
	 */
	public void getJournals() {
		String query = "SELECT * FROM journal";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Database.JOURNALS);
			Statement statement = connection.createStatement();
			
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				System.out.println(results.getString("context"));
			}
			
			
		} catch (Exception ex) {
			System.out.println("Failed to retrieve entries from journal_db.sqlite");
			ex.printStackTrace();
		}
	}
	
}
