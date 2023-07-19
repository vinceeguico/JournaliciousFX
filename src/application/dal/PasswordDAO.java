package application.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PasswordDAO {
	private static final String passwordPath = "resources/txts/password.txt";
	private static final File passFile = new File(passwordPath);

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
