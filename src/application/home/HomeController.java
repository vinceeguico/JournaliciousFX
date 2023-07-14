package application.home;

import application.SceneController;
import javafx.event.ActionEvent;

public class HomeController extends SceneController {

	public void switchToLogin(ActionEvent e) {
		super.switchToView(e, View.LOGIN);
	}
	
	public void switchToCreate(ActionEvent e) {
		super.switchToView(e, View.CREATE);
	}
	
	public void switchToSearch(ActionEvent e) {
		super.switchToView(e, View.SEARCH);
	}
	
	public void switchToChangePassword(ActionEvent e) {
		super.switchToView(e, View.CHANGE_PASSWORD);
	}
	
}
