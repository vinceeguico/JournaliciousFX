package application.create;

import application.SceneController;
import javafx.event.ActionEvent;

public class CreateController extends SceneController {
	
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME);
	}
	
}
