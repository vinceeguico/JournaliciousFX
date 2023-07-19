package application;
	
import java.io.File;
import java.sql.Connection;

import application.dal.DBConnection;
import application.dal.DBConnection.Databases;
import application.models.UserModel;
import application.dal.PasswordDAO;
import application.dal.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
	
	public static void main(String[] args) {
		launch(args);
	}
}
