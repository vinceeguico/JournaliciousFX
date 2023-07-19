package application.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.dal.DBConnection.Databases;

public class UserDAO {
	private static final String USER_INFO_ROW_ID = "info";

	public String getSecurityQuestion() {
		String query = "SELECT * FROM user_info WHERE id = ?";
		String securityQuestion = "";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Databases.USER_INFO);
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, USER_INFO_ROW_ID);
			
			ResultSet queryOutput = statement.executeQuery();
			
			if (queryOutput.next()) {
				securityQuestion = queryOutput.getString("security_question");
			}
			
		} catch (Exception ex) {
			System.out.println("Failed to retrieve Security Question from database!");
			ex.printStackTrace();
		}
		
		return securityQuestion;
	}
	
	public void setSecurityQuestion(String newSecurityQuestion) {
		String updateQuery = "UPDATE user_info SET security_question = ? WHERE id = ?";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Databases.USER_INFO);
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setString(1, newSecurityQuestion);
			statement.setString(2, USER_INFO_ROW_ID);
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Failed to update security question in user-info.sqlite!");
			e.printStackTrace();
		}
	}
	
	
	
	public String getSecurityQuestionAnswer() {
		String query = "SELECT * FROM user_info WHERE id = ?";
		String securityQuestionAnswer = "";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Databases.USER_INFO);
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, USER_INFO_ROW_ID);
			
			ResultSet queryOutput = statement.executeQuery();
			
			if (queryOutput.next()) {
				securityQuestionAnswer = queryOutput.getString("security_question_answer");
			}
			
		} catch (Exception ex) {
			System.out.println("Failed to retrieve Security Question Answer from database!");
			ex.printStackTrace();
		}
		
		return securityQuestionAnswer;
	}
	
	public void setSecurityQuestionAnswer(String newSecurityQuestionAnswer) {
		String updateQuery = "UPDATE user_info SET security_question_answer = ? WHERE id = ?";
		
		try {
			
			Connection connection = DBConnection.getDBConnection(Databases.USER_INFO);
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setString(1,  newSecurityQuestionAnswer);
			statement.setString(2, USER_INFO_ROW_ID);
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Failed to update security question answer in user-info.sqlite!");
			e.printStackTrace();
		}
	}
}