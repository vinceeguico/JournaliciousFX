package application.models;

/**
 * A class that handles backend logic for user data.
 * 
 * @author Vince Eguico
 */
public class UserModel {
	private PasswordModel passwordModel;
	private String securityQuestion;
	private String securityQuestionAnswer;
	
	/**
	 * Constructs a user model.
	 */
	public UserModel() {
		this.passwordModel = new PasswordModel();
		this.securityQuestion = null;
		this.securityQuestionAnswer = null;
	}
	
	/**
	 * Returns the password model.
	 * 
	 * @return the user's password model.
	 */
	public PasswordModel getPasswordModel() {
		return this.passwordModel;
	}
	
	/**
	 * Sets the security question.
	 * 
	 * @param securityQuestion The chosen security question.
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	
	/**
	 * Sets the answer to the security question.
	 * 
	 * @param answer The answer to the selected security question.
	 */
	public void setSecurityQuestionAnswer(String answer) {
		this.securityQuestionAnswer = answer;
	}
	
	/**
	 * Checks if the entered answer matches the correct answer to the user's security question.
	 * 
	 * @param enteredAnswer The entered answer to the security question.
	 * @return Returns true if the entered answer matches the answer to the security questions. Otherwise, returns false.
	 */
	public boolean isCorrectSecurityQuestionAnswer(String enteredAnswer) {
		
		if (enteredAnswer.equals(this.securityQuestionAnswer)) {
			return true;
		}
		
		return false;
	}
}
