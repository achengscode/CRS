package login;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clerkScreen.ClerkScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import security.DBSecurity;
import databaseManagement.Query;
import ui.AlertBox;


//import manager.ManagerController;

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
	@FXML
	private Label missingName;
	@FXML
	private Label missingPwd;
	

	//private ManagerController manager;
	 private Parent parent;
	    private Scene scene;
	    private Stage stage;
	

	
	/**
	 * Constructor for the LoginController.
	 * Loads the loginScreen onto the stage.
	 */
	public LoginController() 
	{
	       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginScreen.fxml"));
	       fxmlLoader.setController(this);
	        try {
	            parent = (Parent) fxmlLoader.load();
	            scene = new Scene(parent, 600, 400);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * Loads the login page onto the stage.
	 * @param stage
	 */
	 public void launchLoginController(Stage stage) 
	 {
	        this.stage = stage;
	        stage.setTitle("User Login");
	        stage.setScene(scene);
	        stage.setResizable(false);
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
	 * Checks if the fields are empty and also
	 * submits the query to the database.
	 *  
	 */
	@FXML
	private void handleLoginButton()
	{
		//database call to check password occurs here as well as hashing
		try {
			
			String name = userName.getText();
			String pwd = passwd.getText();
			
			//check if name and password are empty.
			if (isFieldsEmpty(name, pwd))
			{
			    return;
			}
			//compare the name given and password with one in database.
			//start by getting the salt.
			ResultSet result = Query.select("SELECT pwdSalt, empPwd, empType FROM employee WHERE empID = " +"'" + name + "'");
			if (!result.next())
			{
			    AlertBox.show("No results found!");
			    return;
			}
			String salt = result.getString(1);
			String dbPwd = result.getString(2);
			String empType = result.getString(3);
			
			String hashedString = DBSecurity.hashedString(pwd, salt);
			System.out.println("Hashed : " + hashedString);
			if (!dbPwd.equals(hashedString))
			{
				AlertBox.show("Incorrect Password!");
				return;
			}
			else
			{
			    System.out.println("Password Correct!");
				System.out.println("The empType is : " + empType);

				switchScreen(empType);

			}
			
		} catch (SQLException e)
		{
		    AlertBox.show(e.getMessage()); //if any errors occur
		}
	}
	
	/**
	 * Event handler for the cancel/exit button
	 * Exits out of the entire system.
	 */
	@FXML
	private void handleCancelButton()
	{
		System.out.println("You pressed cancel!");
		System.exit(0);
	}
	
	/**
	 * Function that switches screen to the new screen
	 * @pre role.length > 0
	 */
	private void switchScreen(String role)
	{
	    switch(role)
	    {
	        //create screens here
	        case "manager":
	        	//manager = new ManagerController();
				//manager.redirectHome(stage);
	           break;
	        case "clerk":
	        	// Launching Clerk Screen 
	        	ClerkScreenController clerk = new ClerkScreenController();
				clerk.redirectHome(stage);
	           break;
	        case "sysadmin":
	           break;
	    }
	}
	
	/**
	 * Check to see if the username and password fields are empty.
	 * @pre name.length > 0
	 * @pre password.length > 0
	 */
	private boolean isFieldsEmpty(String name, String password)
	{
	    boolean anyMissing = false;
	    missingName.setVisible(false);
	    missingPwd.setVisible(false);
	    if (name.length() == 0)
	    {
	        missingName.setVisible(true);
	        anyMissing  = true;
	    }
	    if (password.length() == 0)
	    {
	        missingPwd.setVisible(true);
	        anyMissing = true;
	    }
	    return anyMissing; 
	}
}
