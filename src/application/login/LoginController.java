package application.login;

import application.SceneController;

import models.PasswordModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LoginController extends SceneController {
	@FXML private TextField passwordField;
	@FXML private Label errorMsgLbl;
	@FXML private Button loginBtn;
	
	
	public void switchToHome(ActionEvent e) {
		super.switchToView(e, View.HOME);
	}
	
	
	public void handleLoginAttempt(ActionEvent e) {		
		PasswordModel password = new PasswordModel();
		
		String enteredPassword = passwordField.getText();
		// login to home if password is correct
		if (password.isCorrectPassword(enteredPassword)) {
			switchToHome(e);
		}
		else {
			// reset password field
			passwordField.setText("");
			
			// display error message depending on user status
			if (!password.isFirstTimeUser()) {
				errorMsgLbl.setText("Error: Password is invalid");
			}
			else if (password.isFirstTimeUser()) {
				errorMsgLbl.setText("Error: Invalid Password. Please enter default password \"p\"");
			}
		}
	}
	
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			ActionEvent actionEvent = new ActionEvent(e.getSource(), e.getTarget());
			handleLoginAttempt(actionEvent);
		}
	}
	
}