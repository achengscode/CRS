package login;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import security.DBSecurity;
import databaseManagement.Query;

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
			ResultSet result = Query.select("SELECT pwdSalt, empPwd FROM employee WHERE empID = " +"'" + name + "'");
			result.next();
			String salt = result.getString(1);
			String dbPwd = result.getString(2);
			
			String hashedString = DBSecurity.hashedString(pwd, salt);
			
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
