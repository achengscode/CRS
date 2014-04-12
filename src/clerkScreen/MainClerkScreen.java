package clerkScreen;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainClerkScreen extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    ClerkScreenController clerkController = new ClerkScreenController();
		    clerkController.launchClerkController(primaryStage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}