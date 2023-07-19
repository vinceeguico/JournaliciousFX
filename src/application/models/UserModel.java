package application.models;

import application.dal.UserDAO;

/**
 * A class that handles backend logic for user data.
 * 
 * @author Vince Eguico
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
	
	
	public static UserModel getUserModel() {
		return userModel;
	}
	

	public PasswordModel getPasswordModel() {
		return passwordModel;
	}
	
	
	public void setSecurityQuestion(String newSecurityQuestion) {
		UserDAO user = new UserDAO();
		user.setSecurityQuestion(newSecurityQuestion);
		
		securityQuestion = newSecurityQuestion;
	}
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	

	public void setSecurityQuestionAnswer(String answer) {
		UserDAO user = new UserDAO();
		user.setSecurityQuestionAnswer(answer);
		
		securityQuestionAnswer = answer;
	}
	
	/**
	 * Checks if the entered answer matches the correct answer to the user's security question.
	 * 
	 * @param enteredAnswer The entered answer to the security question.
	 * @return Returns true if the entered answer matches the answer to the security questions. Otherwise, returns false.
	 */
	public boolean isCorrectSecurityQuestionAnswer(String enteredAnswer) {
		
		if (enteredAnswer.equals(securityQuestionAnswer)) {
			return true;
		}
		
		return false;
	}
}
