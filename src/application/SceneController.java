package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {

	public void switchToHome(ActionEvent e) throws IOException {
		BorderPane root = FXMLLoader.load(getClass().getResource("/application/home/Home.fxml"));
		Scene scene = new Scene(root);
		
		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
