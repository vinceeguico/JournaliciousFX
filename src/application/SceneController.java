package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.PasswordModel;
import models.UserModel;

public class SceneController {
	
	// instance variables
	private static UserModel user = new UserModel();
	

	protected UserModel getUserModel() {
		return user;
	}
	

	// Handle the switching between scenes given a view to switch to
	protected void switchToView(ActionEvent e, View view) {
		try {
			// load the view from fxml file and create new scene
			BorderPane root = FXMLLoader.load(getClass().getResource(view.getValue()));
			Scene scene = new Scene(root);
			
			// Get event source so primary stage can be retrieved
			// then show the new scene on the stage
			Node source = (Node) e.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException ex) {
			System.out.println("Failed to switch scene");
			System.out.println(ex.getMessage());
		}
	}
	
	// Enumerate all possible views the application can switch to
	protected enum View {
		LOGIN("/application/login/Login.fxml"),
		HOME("/application/home/Home.fxml"),
		CHANGE_PASSWORD("/application/changePassword/ChangePassword.fxml"),
		RESET_PASSWORD("/application/resetPassword/ResetPassword.fxml"),
		CREATE("/application/create/Create.fxml"),
		SEARCH("/application/search/Search.fxml");
		
		// instance variable
		private final String view;
		
		// constructor
		private View(String view) {
			this.view = view;
		}
		
		// getValue() needed to retrieve value associated with enum
		public String getValue() {
			return this.view;
		}
	}
	
}
