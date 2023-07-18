package application;
	
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
			
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/application/views/Login.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
