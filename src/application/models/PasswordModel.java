package application.models;

import application.dal.PasswordDAO;

/**
 * A class representing a Password for the application,
 * handles all logic related to storing and checking a password
 * 
 * @author Chase Barman
 */
public class PasswordModel {
	private static final String DEFAULT_PASSWORD = "p";
	
	/**
	 * Changes the password stored in the system
	 * 
	 * @param newPassword the new password to change the current password to
	 */
	public void setPassword(String newPassword) {
		PasswordDAO passDAO = new PasswordDAO();
		passDAO.setPassword(newPassword);
	}
	
	private String getPassword() {
		PasswordDAO passDAO = new PasswordDAO();
		return passDAO.getPassword();
	}
	
	
	/**
	 * Checks if the user is a first time user
	 * 
	 * @return a boolean indicating whether user is a first time user
	 */
	public boolean isFirstTimeUser() {
		String password = this.getPassword();
		if (password.equals(DEFAULT_PASSWORD)) {
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
		String password = this.getPassword();
		if (enteredPassword.equals(password)) {
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
