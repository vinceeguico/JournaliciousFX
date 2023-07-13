package application.home;

import java.io.IOException;

import application.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeController extends SceneController {

	public void switchToLogin(ActionEvent e) throws IOException {
		super.switchToView(e, View.LOGIN);
	}
	
}
