package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clerkScreen.ClerkScreenController;
import clerkScreen.VehicleSearchRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import databaseManagement.Query;

/**
 * Controller class to handle final screen of rental process
 * The clerk must specify at this stage the cosigner (if any) and the credit card information
 * The screen finishes by launching a printable form to be signed by the customer and cosigner.
 * 
 * @author Ignacio Perez
 * 
 */


public class CosignerScreenController implements Initializable{
	
	// Final Screen Buttons and Textfields declaration
	// Text fields
	@FXML
	private TextField displayFinalPhone;
	@FXML
	private TextField displayFinalLname;
	@FXML
	private TextField displayFinalFname;
	@FXML
	private TextField displayFinalId;
	@FXML
	private TextField customerLicense;
	@FXML
	private TextField cosignerLicense;
	@FXML
	private TextField finalAmount;

	// Labels
	@FXML
	private Label cosignerInfoLabel;
	
	// Grid panel
	@FXML
	private GridPane cosignerInformation;
	
	// Comboboxes
	@FXML
	private ComboBox customerAge;
	@FXML
	private ComboBox cosignerAge;
	
	private Parent parent;
	private Scene scene;
	private Stage stage;
	
	
	final ObservableList<String> customerAgeValues = FXCollections
			.observableArrayList("18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "33", "34", "35",
					"36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
					"58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
					"82", "83", "84","85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99");
	final ObservableList<String> cosignerAgeValues = FXCollections
			.observableArrayList("25", "26", "27", "28", "29", "30", "31", "32", "33", "33", "34", "35",
					"36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
					"58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
					"82", "83", "84","85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99");
	
	public CosignerScreenController(SelectedCustomer customer, String from, String to, String total) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"SelectCosignerScreen.fxml"));

		fxmlLoader.setController(this);
		try {
			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent);
						
			// filling fields with arguments
			displayFinalPhone.setText(customer.getPhone());
			displayFinalLname.setText(customer.getLastname());
			displayFinalFname.setText(customer.getFirstname());
			displayFinalId.setText(customer.getId());
			finalAmount.setText(total);
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchCosignerController(Stage stage) {
		this.stage = stage;
		stage.setTitle("Rent Vehicle");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.hide();
		stage.show();
		
	}
	
	public void redirectHome(Stage stage) {
		this.stage = stage;
		stage.setTitle("Clerk Rent");
		stage.setScene(scene);

		stage.hide();
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		customerAge.setItems(customerAgeValues);
		cosignerAge.setItems(cosignerAgeValues);
	}
	
	@FXML
	private void handleCancelButton() {
		// System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		System.exit(0);
	}
	
	@FXML
	private void handleBackButton() {
		// System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		// System.exit(0);
	}
	
	@FXML
	private void handleFinishButton() {
		// System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		System.exit(0);
	}
	
	/**
	 * Method to handle customer Age selection
	 * @pre none
	 * @post enables cosigner information if selected age is 24 or younger
	 */
	@FXML
	private void handleCustomerAge () {
		
		if (Integer.parseInt(customerAge.getValue().toString()) < 25 )
		{
			cosignerInfoLabel.setDisable(false);
			cosignerInformation.setDisable(false);
			cosignerAge.setDisable(false);
		}
		else
		{
			cosignerInfoLabel.setDisable(true);
			cosignerInformation.setDisable(true);
			cosignerAge.setDisable(true);
		}
		
			
	}
}
