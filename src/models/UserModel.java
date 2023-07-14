package models;

public class UserModel {
	private PasswordModel passwordModel;
	private String securityQuestion;
	private String securityQuestionAnswer;
	
	public UserModel() {
		this.passwordModel = new PasswordModel();
		this.securityQuestion = null;
		this.securityQuestionAnswer = null;
	}
	
	public PasswordModel getPasswordModel() {
		return this.passwordModel;
	}
	
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	
	public void setSecurityQuestionAnswer(String answer) {
		this.securityQuestionAnswer = answer;
	}
	
	
	public boolean isCorrectSecurityQuestionAnswer(String enteredAnswer) {
		
		if (enteredAnswer.equals(this.securityQuestionAnswer)) {
			return true;
		}
		
		return false;
	}
}
