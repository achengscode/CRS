package login;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import security.DBSecurity;
import databaseManagement.Query;
import manager.ManagerController;
/**
 * Controller class to handle all two button events from the 
 * Login form (namely the login button and the cancel button).
 * 
 * 
 * @author Aaron Cheng
 *
 */

public class LoginController implements Initializable{

	@FXML
	private Button loginBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private TextField userName;
	@FXML
	private PasswordField passwd;
	
	private ManagerController manager;
	 private Parent parent;
	    private Scene scene;
	    private Stage stage;
	
	
	public LoginController() {
	       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginScreen.fxml"));
	        fxmlLoader.setController(this);
	        try {
	            parent = (Parent) fxmlLoader.load();
	            scene = new Scene(parent, 600, 400);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
	 public void launchLogingController(Stage stage) {
	        this.stage = stage;
	        stage.setTitle("User Login");
	        stage.setScene(scene);
	        stage.setResizable(true);
	        stage.hide();
	        stage.show();
	    }
	//main method needed to be implemented by the controller class.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
		//empty for now
	}
	
	/**
	 * Event handler for the login button.
	 *  
	 */
	@FXML
	private void handleLoginButton()
	{
		//database call to check password occurs here as well as hashing
		try {
			
			String name = userName.getText();
			String pwd = passwd.getText();
			
			//compare the name given and password with one in database.
			//start by getting the salt.
			ResultSet result = Query.select("SELECT pwdSalt, empPwd, empType FROM employee WHERE empID = " +"'" + name + "'");
			result.next();
			String salt = result.getString(1);
			String dbPwd = result.getString(2);
			String empType = result.getString(3);
			
			String hashedString = DBSecurity.hashedString(pwd, salt);
			System.out.println("Hashed : " + hashedString);
			if (!dbPwd.equals(hashedString))
			{
				System.out.println("Password Incorrect!");
			}
			else
			{
			    // load the correct screen
			    // create the actual session object to be used for the
			    // rest of the application (for names and such)
				System.out.println("Password Correct!");
				System.out.println("The empType is : " + empType);
				if(empType.equalsIgnoreCase("clerk")){
					System.out.println("Yes");
				}
				if(empType.equalsIgnoreCase("manager")){
					manager = new ManagerController();
					manager.redirectHome(stage);
				}
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Event handler for the cancel/exit button
	 * 
	 */
	@FXML
	private void handleCancelButton()
	{
		System.out.println("You pressed cancel!");
		System.exit(0);
	}


	
}
