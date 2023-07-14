package application.resetPassword;

import application.SceneController;
import javafx.event.ActionEvent;

public class ResetPasswordController extends SceneController {
	
	public void handleBackToLoginClick(ActionEvent e) {
		super.switchToView(e, View.LOGIN);
	}
	
}
