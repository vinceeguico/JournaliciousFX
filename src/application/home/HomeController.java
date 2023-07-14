package application.home;

import application.SceneController;
import javafx.event.ActionEvent;

public class HomeController extends SceneController {

	public void switchToLogin(ActionEvent e) {
		super.switchToView(e, View.LOGIN);
	}
	
}
