package login;
	
import javafx.application.Application;
import javafx.stage.Stage;


import login.LoginController;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    LoginController loginController = new LoginController();
		    loginController.launchLoginController(primaryStage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
