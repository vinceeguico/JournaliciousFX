package application.resetPassword;

import application.SceneController;
import javafx.event.ActionEvent;

/**
 * Controller for the "Reset Password" screen.
 * 
 * @author Vince Eguico
 */

public class ResetPasswordController extends SceneController {
	
	/**
	 * Handles clicks on "Reset Password" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void handleBackToLoginClick(ActionEvent e) {
		super.switchToView(e, View.LOGIN);
	}
	
}
