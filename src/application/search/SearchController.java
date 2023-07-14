package application.search;

import application.SceneController;
import javafx.event.ActionEvent;

public class SearchController extends SceneController {
	
	public void handleBackHomeClick(ActionEvent e) {
		super.switchToView(e, View.HOME);
	}
	
}
