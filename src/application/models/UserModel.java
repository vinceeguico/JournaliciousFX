package application.models;

/**
 * A class that handles backend logic for user data.
 * 
 * @author Vince Eguico
 */
public class UserModel {
	
	private static UserModel userModel = new UserModel();
	private static PasswordModel passwordModel;
	private static JournalModel journalModel;
	
	private static String securityQuestion;
	private static String securityQuestionAnswer;
	
	/**
	 * Constructs a user model.
	 */
	private UserModel() {
		passwordModel = new PasswordModel();
		journalModel = new JournalModel();
		
		securityQuestion = null;
		securityQuestionAnswer = null;
	}
	
	
	public static UserModel getUserModel() {
		return userModel;
	}
	
	/**
	 * Returns the password model.
	 * 
	 * @return the user's password model.
	 */
	public PasswordModel getPasswordModel() {
		return passwordModel;
	}
	
	
	public JournalModel getJournalModel() {
		return journalModel;
	}
	
	
	/**
	 * Sets the security question.
	 * 
	 * @param newSecurityQuestion The chosen security question.
	 */
	public void setSecurityQuestion(String newSecurityQuestion) {
		securityQuestion = newSecurityQuestion;
	}
	
	/**
	 * Sets the answer to the security question.
	 * 
	 * @param answer The answer to the selected security question.
	 */
	public void setSecurityQuestionAnswer(String answer) {
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
