package models;

public class PasswordModel {
	// constants
	private static final String DEFAULT_PASSWORD = "p";
	
	// instance variables
	private String password;
	
	
	// constructor
	public PasswordModel() {
		this.password = DEFAULT_PASSWORD;
	}
	
	public boolean isFirstTimeUser() {
		
		if (this.password.equals(DEFAULT_PASSWORD)) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean isCorrectPassword(String enteredPassword) {
		
		if (enteredPassword.equals(this.password)) {
			return true;
		}
		
		return false;
	}
	
	public boolean isValidNewPassword(String newPassword) {
		
		if (newPassword.equals(DEFAULT_PASSWORD)) {
			return false;
		}
		
		return true;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
