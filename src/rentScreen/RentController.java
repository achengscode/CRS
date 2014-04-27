package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import payment.Payment;
import AddCustomer.AddCustomer;
import clerkScreen.ClerkScreenController;
import clerkScreen.VehicleSearchRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import databaseManagement.Query;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import login.LoginController;

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

	private RentalInfo info;
	
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
	
	// Price Information
	@FXML
	private TextField displayTotal;
	@FXML
	private TextField displayVehiclePrice;
	@FXML
	private TextField displayEqPrice;

	// Button declaration
	@FXML
	private Button updateButton;
	@FXML
	private Button backButton;
	@FXML
	private Button nextButton;

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

	// Price calculation object
	private Payment priceCalculator;
	private double skiRackPrice;
	private double childSeatPrice;
	private double liftGatePrice;
	private double towingEqPrice;
	
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
						
			info = RentalInfo.getRentalInfo();
						
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
			
			// Computing Prices using payment package
			priceCalculator = new Payment(tuple.getVehicleID());
			SimpleDateFormat formatter = new SimpleDateFormat("Yyyyy-MM-dd HH:mm:ss", Locale.US);
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
			
			try {
				Date startDate = new Date(formatter.parse(from).getTime());
				Date endDate = new Date(formatter.parse(to).getTime());
				
				// Getting Vehicle Price
				displayVehiclePrice.setText(priceCalculator.estimatePrice(startDate, endDate));
				
				// Additional Equipment prices
				skiRackPrice = currencyFormatter.parse(priceCalculator.estimateEquipmentPrice("1", startDate, endDate)).doubleValue();
				childSeatPrice = currencyFormatter.parse(priceCalculator.estimateEquipmentPrice("2", startDate, endDate)).doubleValue();
				liftGatePrice = currencyFormatter.parse(priceCalculator.estimateEquipmentPrice("3", startDate, endDate)).doubleValue();
				towingEqPrice = currencyFormatter.parse(priceCalculator.estimateEquipmentPrice("4", startDate, endDate)).doubleValue();
				

				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Setting intial Equipment ammount to zero
			displayEqPrice.setText("$0.00");
			
			// Calling method to set total Price Text Field
			setTotalPrice();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Default constructor used when back button on next screen is pushed
	 */
	public RentController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"RentScreen.fxml"));

		fxmlLoader.setController(this);
		try {
			info = RentalInfo.getRentalInfo();
			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent);
						
			// filling fields with arguments
			selectedVehicleID.setText(info.getVehicleID());
			selectedLicencePlate.setText(info.getLicense());
			selectedType.setText(info.getType());
			selectedCategory.setText(info.getCategory());
			selectedMake.setText(info.getMake());
			selectedModel.setText(info.getModel());
			selectedYear.setText(info.getYear());
			selectedColour.setText(info.getColour());
			displayFrom.setText(info.getFrom());
			displayTo.setText(info.getTo());
			
			// Setting previously selected customer info
			displayPhone.setText(info.getPhone());
			displayId.setText(info.getId());
			displayLname.setText(info.getLastname());
			displayFname.setText(info.getFirstname());
			skiRackCheck.setSelected(info.isSkiRack());
			childSeatCheck.setSelected(info.isChildSeat());
			liftGateCheck.setSelected(info.isLiftGate());
			towingEqCheck.setSelected(info.isTowingEq());
			
			// setting next button available
			nextButton.setDisable(false);

			
			
			// Setting on checkboxes according to selected vehicle type
			if (info.getType().equals("Car")) {
				skiRackCheck.setDisable(false);
				childSeatCheck.setDisable(false);
			}
			// Otherwise, for Truck vehicle type
			else {
				liftGateCheck.setDisable(false);
				towingEqCheck.setDisable(false);
			}
			
			// Computing Vehicle Price using payment package
			priceCalculator = new Payment(info.getVehicleID());
			SimpleDateFormat formatter = new SimpleDateFormat("Yyyyy-MM-dd HH:mm:ss", Locale.US);
			try {
				Date startDate = new Date(formatter.parse(info.getFrom()).getTime());
				Date endDate = new Date(formatter.parse(info.getTo()).getTime());
				displayVehiclePrice.setText(priceCalculator.estimatePrice(startDate, endDate));
					
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Computing Additional Equipment prices
			NumberFormat currencyFormatter = NumberFormat
					.getCurrencyInstance(Locale.US);
			try {
				Date startDate = new Date(formatter.parse(info.getFrom())
						.getTime());
				Date endDate = new Date(formatter.parse(info.getTo()).getTime());

				skiRackPrice = currencyFormatter.parse(
						priceCalculator.estimateEquipmentPrice("1", startDate,
								endDate)).doubleValue();
				childSeatPrice = currencyFormatter.parse(
						priceCalculator.estimateEquipmentPrice("2", startDate,
								endDate)).doubleValue();
				liftGatePrice = currencyFormatter.parse(
						priceCalculator.estimateEquipmentPrice("3", startDate,
								endDate)).doubleValue();
				towingEqPrice = currencyFormatter.parse(
						priceCalculator.estimateEquipmentPrice("4", startDate,
								endDate)).doubleValue();

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Setting intial Equipment ammount to zero
			displayEqPrice.setText("$0.00");
			
			// Calling method to set total Price Text Field
			setTotalPrice();
			
			
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

			cleanCustomerInfo();
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

			
			// Setting fields in customer information
			displayPhone.setText(phone);
			displayFname.setText(fname);
			displayLname.setText(lname);
			displayId.setText(id);
			displayStreet.setText(street);
			displayCity.setText(city);
			displayProvince.setText(province);
			displayPcode.setText(pCode);
			
			// Activating the Next Button 
			if (!displayId.getText().equals(""))
				nextButton.setDisable(false);

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

			Dialogs.showErrorDialog(stage, "No results found!","Error!");
			displayPhone.setText("");
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
		RentalInfo.flushInfo();
		LoginController login = new LoginController();
		login.launchLoginController(stage);
			
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
		fillRentalInfo();
		CosignerScreenController cosigner = new CosignerScreenController(new SelectedCustomer(displayPhone.getText(), displayLname.getText(), displayFname.getText(),displayId.getText()),displayFrom.getText(), displayTo.getText(), displayTotal.getText());
		cosigner.launchCosignerController(stage);
		cosigner.redirectHome(stage);	
		
	}
	
	/**
	 * Handler for checkboxes.
	 * @pre one of the additional equipment checkboxes is activated
	 * @post The price corresponding to the additional equipments is updated according to current selection
	 */
	@FXML
	private void handleAdditionalEq() {
	
		double total = 0;
		if (skiRackCheck.isSelected())
		{
			total = total + skiRackPrice;
		}
		if (childSeatCheck.isSelected())
		{
			total = total + childSeatPrice;
		}
		if (liftGateCheck.isSelected())
		{
			total = total + liftGatePrice;
		}
		if (towingEqCheck.isSelected())
		{
			total = total + towingEqPrice;
		}
		
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		
		displayEqPrice.setText(formatter.format(total));
		setTotalPrice();
		
	}
	
	/**
	 * Private method to fill all requested information from this screen into the singleton class to pass information to folowing screens.
	 */
	private void fillRentalInfo() {
		
		RentalInfo.setVehicleID(selectedVehicleID.getText());
		RentalInfo.setLicense(selectedLicencePlate.getText());
		RentalInfo.setType(selectedType.getText());
		RentalInfo.setCategory(selectedCategory.getText());
		RentalInfo.setMake(selectedMake.getText());
		RentalInfo.setModel(selectedModel.getText());
		RentalInfo.setYear(selectedYear.getText());
		RentalInfo.setColour(selectedColour.getText());
		RentalInfo.setId(displayId.getText());
		RentalInfo.setPhone(displayPhone.getText());
		RentalInfo.setLastname(displayLname.getText());
		RentalInfo.setFirstname(displayFname.getText());
		RentalInfo.setChildSeat(childSeatCheck.isSelected());
		RentalInfo.setSkiRack(skiRackCheck.isSelected());
		RentalInfo.setLiftGate(liftGateCheck.isSelected());
		RentalInfo.setTowingEq(towingEqCheck.isSelected());
		RentalInfo.setFrom(displayFrom.getText());
		RentalInfo.setTo(displayTo.getText());
		RentalInfo.setVehiclePrice(displayVehiclePrice.getText());
		RentalInfo.setEquipmentPrice(displayEqPrice.getText());
		RentalInfo.setTotalPrice(displayTotal.getText());
						
	}
	
	/**
	 * Private Method to calculate and set total price field
	 */
	private void setTotalPrice()
	{
		double totalPrice;
		
		// Setting the total value based on the sum of both values
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		
		try {
		totalPrice = currencyFormatter.parse(displayVehiclePrice.getText()).doubleValue();
		totalPrice = totalPrice + currencyFormatter.parse(displayEqPrice.getText()).doubleValue();
		
		// Changing format and displaying the total amount
		displayTotal.setText(currencyFormatter.format(totalPrice));
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@FXML
	/**
	 * Show the AddCustomer form
	 */
	private void showCustomerForm()
	{
		Stage newStage = new Stage();
		AddCustomer customerForm = new AddCustomer();
		customerForm.launchLoginController(newStage);
	}
	
	

}