package application.home;

import application.SceneController;
import javafx.event.ActionEvent;

/**
 * Controller for the "Home" screen.
 * 
 * @author Vince Eguico
 */

public class HomeController extends SceneController {
	/**
	 * Switches to "Login" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void switchToLogin(ActionEvent e) {
		super.switchToView(e, View.LOGIN);
	}
	
	/**
	 * Switches to "Create" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void switchToCreate(ActionEvent e) {
		super.switchToView(e, View.CREATE);
	}
	
	/**
	 * Switches to "Search" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void switchToSearch(ActionEvent e) {
		super.switchToView(e, View.SEARCH);
	}
	
	/**
	 * Switches to "Change Password" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void switchToChangePassword(ActionEvent e) {
		super.switchToView(e, View.CHANGE_PASSWORD);
	}
	
}
