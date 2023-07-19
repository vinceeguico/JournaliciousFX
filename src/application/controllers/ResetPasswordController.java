package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.PasswordModel;
import application.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for the "Reset Password" screen.
 * 
 * @author Vince Eguico
 */
public class ResetPasswordController extends SceneController implements Initializable {
	@FXML private Label securityQuestionLbl;
	@FXML private Label errorMsgLbl;
	@FXML private TextField newPasswordField;
	@FXML private TextField confirmPasswordField;
	@FXML private TextField securityQuestionAnswerField;
	
	
	/**
	 * Handles clicks on "Reset Password" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void handleBackBtnClick(ActionEvent e) {
		super.switchToView(e, View.LOGIN, View.RESET_PASSWORD);
	}


	// update security question label
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// fetch security question
		UserModel user = UserModel.getUserModel();
		String securityQuestion = user.getSecurityQuestion();
		
		// update security question label's text
		securityQuestionLbl.setText(securityQuestion);
	}
	
	
	public void handleSubmit(ActionEvent e) {
		UserModel user = UserModel.getUserModel();
		PasswordModel passwordModel = user.getPasswordModel();
								
		// check if new password is valid
		String newPassword = newPasswordField.getText();
		if (!passwordModel.isValidNewPassword(newPassword)) {
			errorMsgLbl.setText("Error: Your new password is invalid! Please try something else.");
			return;
		}
				
		// check if new password matches confirm new password field
		String confirmPassword = confirmPasswordField.getText();
		if (!newPassword.equals(confirmPassword)) {
			errorMsgLbl.setText("Error: New passwords did not match!");
			return;
		}
		
		// check if security question answer was correct
		String securityQuestionAnswer = securityQuestionAnswerField.getText();
		if (!user.isCorrectSecurityQuestionAnswer(securityQuestionAnswer)) {
			errorMsgLbl.setText("Error: Answer to security question was incorrect! Please Try again.");
			return;
		}
		
		// update DB
		passwordModel.setPassword(newPassword);
		
		// display success message (TODO!) and switch to home view
		super.switchToView(e, View.HOME, View.RESET_PASSWORD);
	}

	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			ActionEvent actionEvent = new ActionEvent(e.getSource(), e.getTarget());
			handleSubmit(actionEvent);
		}
	}
	
}
