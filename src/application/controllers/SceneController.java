package application.controllers;

import java.io.File;
import java.io.IOException;

import application.models.JournalModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Parent class for all scene controllers
 * provides access to a user model that persists through application's lifetime
 */
class SceneController {
	private static final String viewPackagePath = "resources/views/";
	
	// static class variable to avoid being reset upon re-instantiation
	private static View prevView;
	
	
	private static void setPrevView(View view) {
		prevView = view;
	}
	
	private static View getPrevView() {
		return prevView;
	}
	

	/**
	 * Handles the logic for switching from one view to another
	 * 
	 * @param e an event given by some user action on the application
	 * @param view one of the scene views offered by the View enum
	 * @param prevView the view that the user is switching from
	 */
	protected void switchToView(ActionEvent e, View view, View prevView) {
		try {
			setPrevView(prevView);
			
			// load the view from fxml file and create new scene
			String viewPath = viewPackagePath + view.getValue();
			File viewFile = new File(viewPath);
			BorderPane root = FXMLLoader.load(viewFile.toURI().toURL());
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
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Handles the logic for switching from the Search Page to the Edit Page
	 * 
	 * @param e an event given by some user action on the application 
	 * @param journal the journal entry to populate the edit page with
	 */
	protected void switchToEditView(ActionEvent e, JournalModel journal) {
		try {
			
			setPrevView(View.SEARCH);
			
			// load the view from fxml file and create new scene
			String viewPath = viewPackagePath + "Edit.fxml";
			File viewFile = new File(viewPath);
			FXMLLoader loader = new FXMLLoader(viewFile.toURI().toURL());
			BorderPane root = loader.load();
			
			// initalize the data on the page
			EditController controller = loader.getController();
			controller.init(journal);
			
			// set scene
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
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Handles the logic for switching to the view that
	 * the user was previously on
	 * 
	 * @param e an event given by some user action on the application
	 * @param currView the current view that the user is on
	 */
	protected void switchToPrevView(ActionEvent e, View currView) {
		this.switchToView(e, getPrevView(), currView);
	}
	
	
	/**
	 * Enumerates all possible views the application can switch to
	 */
	protected enum View {
		LOGIN("Login.fxml"),
		HOME("Home.fxml"),
		CHANGE_PASSWORD("ChangePassword.fxml"),
		RESET_PASSWORD("ResetPassword.fxml"),
		CREATE("Create.fxml"),
		SEARCH("Search.fxml");
		
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
