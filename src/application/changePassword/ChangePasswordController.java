package application.changePassword;

import java.io.IOException;

import application.SceneController;
import javafx.event.ActionEvent;

public class ChangePasswordController extends SceneController {
	
	public void switchToHome(ActionEvent e) throws IOException {
		super.switchToView(e, View.HOME);
	}

}
