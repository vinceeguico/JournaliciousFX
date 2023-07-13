package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {
	
	protected enum View {
		LOGIN("/application/login/Login.fxml"),
		HOME("/application/home/Home.fxml"),
		CHANGE_PASSWORD("/application/changePassword/ChangePassword.fxml");
		
		private final String view;
		
		private View(String view) {
			this.view = view;
		}
		
		public String getValue() {
			return this.view;
		}
	}
	

	protected void switchToView(ActionEvent e, View view) throws IOException {
		BorderPane root = FXMLLoader.load(getClass().getResource(view.getValue()));
		Scene scene = new Scene(root);
		
		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
