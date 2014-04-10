package rentReturn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import rentReturn.RentController;



public class rentReturnMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    RentController rentController = new RentController();
		    rentController.launchRentingController(primaryStage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}