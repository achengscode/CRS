package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clerkScreen.ClerkScreenController;
import clerkScreen.VehicleSearchRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import databaseManagement.Query;

/**
 * Controller class to handle rent tab Controls Search Customer button to search
 * database for existing customers Controls New Customer to Add customer entry
 * and Update to modify existing data Controls Payment button to initiate
 * payment process Controls Cancel button to close and go back to previous
 * screen
 * 
 * @author Ignacio Perez
 * 
 */

public class RentController implements Initializable {

	// Search Text fields
	@FXML
	private TextField searchPhone;
	@FXML
	private TextField searchName;

	// Fields containing information of vehicle selected in previous screen
	@FXML
	private TextField selectedVehicleID;
	@FXML
	private TextField selectedLicencePlate;
	@FXML
	private TextField selectedType;
	@FXML
	private TextField selectedCategory;
	@FXML
	private TextField selectedMake;
	@FXML
	private TextField selectedModel;
	@FXML
	private TextField selectedYear;
	@FXML
	private TextField selectedColour;
	@FXML
	private TextField displayFrom;
	@FXML
	private TextField displayTo;
	@FXML
	private TextField selectedDate;
	
	// Display Text fields
	@FXML
	private TextField displayPhone;
	@FXML
	private TextField displayFname;
	@FXML
	private TextField displayLname;
	@FXML
	private TextField displayId;
	@FXML
	private TextField displayStreet;
	@FXML
	private TextField displayCity;
	@FXML
	private TextField displayProvince;
	@FXML
	private TextField displayPcode;
	@FXML
	private TextField displayTotal;

	// Button declaration
	@FXML
	private Button updateButton;
	@FXML
	private Button backButton;

	// Radio Buttons declaration
	@FXML
	private RadioButton nameRadButton;
	@FXML
	private RadioButton phoneRadButton;
	
	// Checkboxes for Additional Equipment
	@FXML
	private CheckBox skiRackCheck;
	@FXML
	private CheckBox childSeatCheck;
	@FXML
	private CheckBox liftGateCheck;
	@FXML
	private CheckBox towingEqCheck;
	
	

	private Parent parent;
	private Scene scene;
	private Stage stage;
	
	
	private VehicleSearchRow tuple;

	/**
	 * Controller constructor
	 * @param tuple
	 * @pre tuple is a valid object of VehicleSearchRow class
	 * @post a new RentController object is created and corresponding fields are filled with information passed as argument
	 */
	public RentController(VehicleSearchRow selection, String from, String to) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"RentScreen.fxml"));

		fxmlLoader.setController(this);
		try {
			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent);
			tuple = new VehicleSearchRow(selection.getVehicleID(),selection.getLicPlate(),selection.getType(),selection.getCategory(),selection.getMake(),selection.getModel(),selection.getYear(),selection.getColour());
			
			// filling fields with arguments
			selectedVehicleID.setText(tuple.getVehicleID());
			selectedLicencePlate.setText(tuple.getLicPlate());
			selectedType.setText(tuple.getType());
			selectedCategory.setText(tuple.getCategory());
			selectedMake.setText(tuple.getMake());
			selectedModel.setText(tuple.getModel());
			selectedYear.setText(tuple.getYear());
			selectedColour.setText(tuple.getColour());
			displayFrom.setText(from);
			displayTo.setText(to);
			// NOT IMPLEMENTING PRICE CALCULATION YET!!
			displayTotal.setText("125.00");
			
			
			// Setting on checkboxes according to selected vehicle type
			if (tuple.getType().equals("Car")) {
				skiRackCheck.setDisable(false);
				childSeatCheck.setDisable(false);
			}
			// Otherwise, for Truck vehicle type
			else {
				liftGateCheck.setDisable(false);
				towingEqCheck.setDisable(false);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchRentController(Stage stage) {
		this.stage = stage;
		stage.setTitle("Rent Vehicle");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.hide();
		stage.show();
		
	}

	
	// main method needed to be implemented by the controller class.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	/**
	 * Event handler for the searchCustomer button.
	 * 
	 */
	@FXML
	private void handleSearchCustomerButton() {
		// database call to search for customer info in database
		// so far only search by phone number is implemented
		try {

			String phone = searchPhone.getText();
			String lastname = searchName.getText();

			// compare the name given and password with one in database.
			// start by getting the salt.

			ResultSet result;

			// SQL Query must be adjusted depending on the search parameter
			// given (phone or lastname)
			if (searchName.isDisabled()) {
				result = Query
						.select("SELECT C.phoneNo, C.custLname, C.custFname, C.custID, A.street, A.city, A.province, A.postalcode FROM customer C, custAddress A WHERE C.custID = A.custID AND C.phoneNo = "
								+ "'" + phone + "'");
			} else {
				// at this point search name is enabled, meaning searchPhone is
				// disabled
				result = Query
						.select("SELECT C.phoneNo, C.custLname, C.custFname, C.custID, A.street, A.city, A.province, A.postalcode FROM customer C, custAddress A WHERE C.custID = A.custID AND C.custLname = "
								+ "'" + lastname + "'");
			}

			result.next();
			phone = result.getString(1);
			String lname = result.getString(2);
			String fname = result.getString(3);
			String id = result.getString(4);
			String street = result.getString(5);
			String city = result.getString(6);
			String province = result.getString(7);
			String pCode = result.getString(8);

			System.out.println("Customer with PhoneNo: " + phone + " found!");
			// Setting fields in customer information
			displayPhone.setText(phone);
			displayFname.setText(fname);
			displayLname.setText(lname);
			displayId.setText(id);
			displayStreet.setText(street);
			displayCity.setText(city);
			displayProvince.setText(province);
			displayPcode.setText(pCode);

			// Setting fields as editable
			displayPhone.setEditable(true);
			displayFname.setEditable(true);
			displayLname.setEditable(true);
			displayStreet.setEditable(true);
			displayCity.setEditable(true);
			displayProvince.setEditable(true);
			displayPcode.setEditable(true);

			// cleaning search input
			searchPhone.setText("");
			searchName.setText("");

		} catch (SQLException e) {

			displayPhone.setText("Not found!");
			cleanCustomerInfo();
			searchPhone.setText("");
			searchName.setText("");
			// e.printStackTrace();
		}
	}

	/**
	 * Event handler for the cancel/exit button
	 * 
	 */
	@FXML
	private void handleCancelButton() {
		System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		System.exit(0);
	}

	/**
	 * Event Handler launched when the search fields are modified.
	 * Multithreading candidate
	 * 
	 * @pre phone textfield is edited
	 * @post NotFoundMessage is set to invisible
	 */
	@FXML
	private void handleEnableRadName() {
		searchName.setDisable(false);
		searchPhone.setDisable(true);
	}
	/**
	 * Event Handler launched when the search fields are modified.
	 * Multithreading candidate
	 * 
	 * @pre phone textfield is edited
	 * @post NotFoundMessage is set to invisible
	 */
	@FXML
	private void handleEnableRadPhone() {
		searchPhone.setDisable(false);
		searchName.setDisable(true);
	}
	/**
	 * Event Handler launched when the search fields are modified.
	 * Multithreading candidate
	 * 
	 * @pre phone textfield is edited
	 * @post NotFoundMessage is set to invisible
	 */
	@FXML
	private void handleEnableUpdate() {
		updateButton.setDisable(false);
	}

	/**
	 * Private method used to clean up retrieved info for the customer
	 * 
	 * @pre none
	 * @post all text fields except for phone N` are set to ""
	 */
	private void cleanCustomerInfo() {
		// cleaning results
		displayFname.setText("");
		displayLname.setText("");
		displayId.setText("");
		displayStreet.setText("");
		displayCity.setText("");
		displayProvince.setText("");
		displayPcode.setText("");

		// disabling editing mode
		// Setting fields as editable
		displayPhone.setEditable(false);
		displayFname.setEditable(false);
		displayLname.setEditable(false);
		displayId.setEditable(false);
		displayStreet.setEditable(false);
		displayCity.setEditable(false);
		displayProvince.setEditable(false);
		displayPcode.setEditable(false);
	}

	public void redirectHome(Stage stage) {
		this.stage = stage;
		stage.setTitle("Clerk Rent");
		stage.setScene(scene);

		stage.hide();
		stage.show();
	}
	
	/**
	 * Handler for back button.
	 * @pre The RentScreen is running
	 * @post RentScreen is closed and the previous screen (ClerkScreen) is relaunched
	 */
	@FXML
	private void handleBackButton() {
		// System.out.println("BackButton");
		ClerkScreenController clerk = new ClerkScreenController();
		clerk.redirectHome(stage);
	}
	
	/**
	 * Handler for NextButton
	 * @pre The RentScreen is running and a valid customer from the database has been selected
	 */
	@FXML
	private void handleNextButton() {
		CosignerScreenController cosigner = new CosignerScreenController(new SelectedCustomer(displayPhone.getText(), displayLname.getText(), displayFname.getText(),displayId.getText()),displayFrom.getText(), displayTo.getText(), displayTotal.getText());
		cosigner.launchCosignerController(stage);
		cosigner.redirectHome(stage);	
		
	}
	
}