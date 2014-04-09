package login;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import login.LoginController;

import login.LoginController;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    LoginController loginController = new LoginController();
		    loginController.launchLogingController(primaryStage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
