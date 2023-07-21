package application.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that faciliates the interaction and manipulation of journal data
 * within the flat files
 */
public class PasswordDAO {
	private static final String passwordPath = "resources/txts/password.txt";
	private static final File passFile = new File(passwordPath);

	/**
	 * Gets the password from the file system
	 * 
	 * @return the user's password that is stored in the file system
	 */
	public String getPassword() {
		String password = "";
		try (Scanner in = new Scanner(passFile)) {	
			
			if (in.hasNextLine()) {
				password = in.nextLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find password.txt in file system!");
			e.printStackTrace();
		}
		
		return password;
	}
	
	/**
	 * Overwrites the password in the file system
	 * 
	 * @param newPassword the new password to store in file system
	 */
	public void setPassword(String newPassword) {
		
		// if newPassword is null, then user entered an empty string
		if (newPassword == null) {
			newPassword = "";
		}
		
		try (FileWriter writer = new FileWriter(passFile)) {

			writer.write(newPassword);
			
		} catch (IOException e) {
			System.out.println("Failed to write to password.txt!");
			e.printStackTrace();
		}
	}
	
}
