package application.create;

import application.SceneController;
import javafx.event.ActionEvent;

/**
 * Controller for the "Create" page.
 * 
 * @author Vince Eguico
 */

public class CreateController extends SceneController {
	
	/**
	 * Handles clicks on "Back Home" button.
	 * 
	 * @param e An event given by some user action on the application
	 */
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME);
	}
	
}
