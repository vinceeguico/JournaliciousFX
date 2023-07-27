package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Starter class that is launched upon running application
 **/
public class Main extends Application {
	
	/**
	 * Shows the user the login page upon application start
	 * 
	 * @param primaryStage the highest level container for the application that hosts scenes 
	 **/
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// get the Login View from local files and create scene
			File loginView = new File("resources/views/Login.fxml");
			BorderPane root = FXMLLoader.load(loginView.toURI().toURL());
			
			Scene scene = new Scene(root, 600, 400);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Journalicious");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Main class that launches the JavaFX application
	 * 
	 * @param args List of command line arguments to pass to JVM
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
