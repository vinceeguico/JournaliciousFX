package application;
	
import java.io.File;

import application.dal.DBConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Starter class launched upon running application
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
			
			File file = new File("resources/views/Login.fxml");
			BorderPane root = FXMLLoader.load(file.toURI().toURL());
			
			Scene scene = new Scene(root, 600, 400);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Journalicious");
			primaryStage.show();
			
			
			DBConnection db = DBConnection.getDBConnection();
			db.test();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
