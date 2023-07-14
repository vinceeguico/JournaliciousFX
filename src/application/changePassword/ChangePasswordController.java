package application.changePassword;


import java.net.URL;
import java.util.ResourceBundle;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.PasswordModel;
import models.UserModel;


public class ChangePasswordController extends SceneController implements Initializable {
	
	@FXML private TextField prevPasswordField;
	@FXML private TextField newPasswordField;
	@FXML private TextField confirmPasswordField;
	@FXML private ChoiceBox<String> securityQuestionChoiceBox;
	@FXML private TextField securityQuestionAnswerField;
	@FXML private Button submitBtn;
	@FXML private Label errorMsgLbl;
	
	private static final String[] SECURITY_QUESTIONS = {
		"What was your first pet's name?",			
		"What is your mother's maiden name?",
		"What street did you grow up on?",
		"What is your favorite programming language?",
		"What is your favorite food?"
	};
	
	private boolean isSecurityQuestionSelected;
	private String securityQuestion = null;
	private String securityQuestionAnswer = null;
	
	
	// constructor
	public ChangePasswordController() {
		this.isSecurityQuestionSelected = false;
		this.securityQuestion = null;
		this.securityQuestionAnswer = null;
	}
	
	
	// initialization method to setup and support dropdown menu (choice box)
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		securityQuestionChoiceBox.getItems().addAll(SECURITY_QUESTIONS);
		securityQuestionChoiceBox.setOnAction(this::handleSecurityQuestionSelection);
	}
	
	public void handleSecurityQuestionSelection(ActionEvent e) {
		
		this.securityQuestion = securityQuestionChoiceBox.getValue();
		
		if (securityQuestion != null) {
			this.isSecurityQuestionSelected = true;
		}
		else {
			this.isSecurityQuestionSelected = false;
		}
	}
	
	
	// handle form submission
	public void handleSubmit(ActionEvent e) {
		UserModel user = super.getUserModel();
		PasswordModel passwordModel = user.getPasswordModel();
		
		// check if previous password was incorrect
		String prevPassword = prevPasswordField.getText();
		if (!passwordModel.isCorrectPassword(prevPassword)) {
			// display error message and break
			errorMsgLbl.setText("Error: Previous password was incorrect! Please try again.");
			return;
		}
		
		// check if new password matches confirm new password
		String newPassword = newPasswordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		if (!newPassword.equals(confirmPassword)) {
			// display error message and break
			errorMsgLbl.setText("Error: New passwords did not match!");
			return;
		}
		
		// check if new password is valid
		if (!passwordModel.isValidNewPassword(newPassword)) {
			errorMsgLbl.setText("Error: Your new password is invalid! Please try something else.");
			return;
		}
		
		// check for empty security question field
		if (!this.isSecurityQuestionSelected || this.securityQuestion == null || this.securityQuestion.equals("")) {
			// display error message and break
			errorMsgLbl.setText("Error: You must select a security question!");
			return;
		}
		
		// check if security question was answered
		this.securityQuestionAnswer = securityQuestionAnswerField.getText();
		if (this.securityQuestionAnswer == null || this.securityQuestionAnswer.equals("")) {
			// display error message and break
			errorMsgLbl.setText("Error: You must answer your security question!");
			return;
		}
		
		
		// submit fields' data to "DB" (stored in model object's state for now)
		user.setSecurityQuestion(this.securityQuestion);
		user.setSecurityQuestionAnswer(this.securityQuestionAnswer);
		passwordModel.setPassword(newPassword);
		
		// display success message and redirect
		super.switchToView(e, View.HOME);
	}
	
}
