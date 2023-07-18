package application.controllers;

import javafx.event.ActionEvent;

/**
 * Controller for the "Search" screen.
 * 
 * @author Vince Eguico
 */
public class SearchController extends SceneController {
	
	/**
	 * Handles mouse clicks on the "Search" screen.
	 * 
	 * @param e An event given by some user action on the application.
	 */
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME, View.SEARCH);
	}
	
}
