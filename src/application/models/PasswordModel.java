package application.models;

/**
 * A class representing a Password for the application,
 * handles all logic related to storing and checking a password
 * 
 * @author Chase Barman
 */
public class PasswordModel {

	private static final String DEFAULT_PASSWORD = "p";
	
	
	private String password;
	
	
	/**
	 * Constructs a PasswordModel object,
	 * initializing the password to default password
	 */
	public PasswordModel() {
		this.password = DEFAULT_PASSWORD;
	}
	
	/**
	 * Changes the password stored in the system
	 * 
	 * @param password the new password to change the current password to
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * Checks if the user is a first time user
	 * 
	 * @return a boolean indicating whether user is a first time user
	 */
	public boolean isFirstTimeUser() {
		
		if (this.password.equals(DEFAULT_PASSWORD)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks if a given password matches the stored password
	 * 
	 * @param enteredPassword the password entered by the user
	 * @return a boolean indicating if the user entered the correct password
	 */
	public boolean isCorrectPassword(String enteredPassword) {
		
		if (enteredPassword.equals(this.password)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks if a given String is a valid new password
	 * 
	 * @param newPassword the new password entered by the user
	 * @return a boolean indicating whether the user's new password is valid
	 */
	public boolean isValidNewPassword(String newPassword) {
		
		if (newPassword.equals(DEFAULT_PASSWORD)) {
			return false;
		}
		
		return true;
	}
	
}
