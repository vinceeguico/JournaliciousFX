package application.login;

import java.net.URL;
import java.util.ResourceBundle;

import application.SceneController;

import models.PasswordModel;
import models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LoginController extends SceneController implements Initializable {
	// instance variables
	private PasswordModel passwordModel;
	
	@FXML private TextField passwordField;
	@FXML private Label errorMsgLbl;
	@FXML private Button loginBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// if first time user, display message reminder about default password
		if (this.passwordModel.isFirstTimeUser()) {
			errorMsgLbl.setText("Reminder: On first time login, default password is \"p\"");
		}
	}
	
	// constructor
	public LoginController() {
		UserModel user = super.getUserModel();
		this.passwordModel = user.getPasswordModel();
	}
	
	
	public void handleLoginAttempt(ActionEvent e) {		
		
		String enteredPassword = passwordField.getText();
		// logs in user, redirecting them to the next page
		if (this.passwordModel.isCorrectPassword(enteredPassword)) {
			// first time users must change password
			if (this.passwordModel.isFirstTimeUser()) {
				super.switchToView(e, View.CHANGE_PASSWORD);
			}
			// returning users go straight to home
			else {
				super.switchToView(e, View.HOME);
			}
		}
		else {
			// reset password field
			passwordField.setText("");
			
			// display error message depending on user status
			if (!this.passwordModel.isFirstTimeUser()) {
				errorMsgLbl.setText("Error: Password is invalid");
			}
			else {
				errorMsgLbl.setText("Error: Invalid Password. Please enter the default password \"p\"");
			}
		}
	}
	
	public void handleForgotPassword(ActionEvent e) {
		super.switchToView(e, View.RESET_PASSWORD);
	}
	
	
	// event listener for when enter key is pressed inside text box
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			ActionEvent actionEvent = new ActionEvent(e.getSource(), e.getTarget());
			handleLoginAttempt(actionEvent);
		}
	}
	
}