package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.UserModel;

/**
 * Parent class for all scene controllers
 * provides access to a user model that persists through application's lifetime
 * 
 * @author Chase Barman
 */
public class SceneController {
	
	// instance made static to avoid being reset upon re-instantiation
	private static UserModel user = new UserModel();
	
	
	/**
	 * Gets the class's user model
	 * 
	 * @return a user model shared between all controllers
	 */
	protected UserModel getUserModel() {
		return user;
	}
	

	/**
	 * Handles the logic for switching from one view to another
	 * 
	 * @param e an event given by some user action on the application
	 * @param view one of the scene views offered by the View enum
	 */
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
	
	
	/**
	 * Enumerates all possible views the application can switch to
	 */
	protected enum View {
		LOGIN("/application/login/Login.fxml"),
		HOME("/application/home/Home.fxml"),
		CHANGE_PASSWORD("/application/changePassword/ChangePassword.fxml"),
		RESET_PASSWORD("/application/resetPassword/ResetPassword.fxml"),
		CREATE("/application/create/Create.fxml"),
		SEARCH("/application/search/Search.fxml");
		
		private final String view;
		
		/**
		 * Constructs the view and associates it with a String value
		 * 
		 * @param view the next scene view to switch to
		 */
		private View(String view) {
			this.view = view;
		}
		
		/**
		 * Provides the value associated with a view
		 * 
		 * @return a String representing the path to a view's location in local files
		 */
		public String getValue() {
			return this.view;
		}
	}
	
}
