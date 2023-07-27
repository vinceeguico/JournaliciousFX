package application.models;

import application.dal.UserDAO;

/**
 * A class that maintains data related to user info.
 * Singleton class to provide global access and prevent
 * multiple users from being created.
 */
public class UserModel {
	private static UserModel userModel = new UserModel();
	private static PasswordModel passwordModel = new PasswordModel();

	private static String securityQuestion;
	private static String securityQuestionAnswer;
	

	private UserModel() {
		UserDAO user = new UserDAO();
		securityQuestion = user.getSecurityQuestion();
		securityQuestionAnswer = user.getSecurityQuestionAnswer();
	}
	
	
	/**
	 * Gets the Singleton instance of the User Model.
	 * 
	 * @return a instance of the UserModel Singleton
	 */
	public static UserModel getUserModel() {
		return userModel;
	}
	

	/**
	 * Gets the Password Model associated with the User.
	 * Password Model should always be accessed through the
	 * User Model to ensure password info is up to date.
	 * 
	 * @return the Password Model representing the user's password
	 */
	public PasswordModel getPasswordModel() {
		return passwordModel;
	}
	
	
	/**
	 * Overwrites the user's security question to a new question
	 * 
	 * @param newSecurityQuestion the user's new security question
	 */
	public void setSecurityQuestion(String newSecurityQuestion) {
		// update in DB
		UserDAO user = new UserDAO();
		user.setSecurityQuestion(newSecurityQuestion);
		
		// update model
		securityQuestion = newSecurityQuestion;
	}
	
	
	/**
	 * Gets the security question set by the user
	 * 
	 * @return the user's security question
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	

	/**
	 * Overwrites the answer to the user's security question to a new answer
	 * 
	 * @param answer the answer to the user's security question
	 */
	public void setSecurityQuestionAnswer(String answer) {
		// update in DB
		UserDAO user = new UserDAO();
		user.setSecurityQuestionAnswer(answer);
		
		// update model
		securityQuestionAnswer = answer;
	}
	
	
	/**
	 * Checks if the entered answer matches the correct answer to the user's security question.
	 * 
	 * @param enteredAnswer The entered answer to the security question.
	 * @return Returns true if the entered answer matches the answer to the security questions. Otherwise, returns false.
	 */
	public boolean isCorrectSecurityQuestionAnswer(String enteredAnswer) {
		boolean answerIsCorrect = enteredAnswer.equals(securityQuestionAnswer);
		
		return answerIsCorrect;
	}
}