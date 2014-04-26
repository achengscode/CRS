package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import databaseManagement.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Controller class to handle final screen of rental process
 * The clerk must specify at this stage the cosigner (if any) and the credit card information
 * The screen finishes by launching a printable form to be signed by the customer and cosigner.
 * 
 * @author Ignacio Perez
 * 
 */


public class CosignerScreenController implements Initializable{
	
	private RentalInfo info;
	
	
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
	private TextField cosignerLastname;
	@FXML
	private TextField cosignerFirstname;
	@FXML
	private TextField finalAmount;
	@FXML
	private TextField cardNumber;
	@FXML
	private TextField holderName;

	
	// Labels
	@FXML
	private Label cosignerInfoLabel;
	@FXML
	private Label cosignerLicenseMessage;
	@FXML
	private Label cardMessage;
	@FXML
	private Label customerAgeMessage;
	@FXML
	private Label cosignerLnameMessage;
	@FXML
	private Label cosignerFnameMessage;
	@FXML
	private Label cosignerAgeMessage;
	@FXML
	private Label cardCompanyMessage;
	@FXML
	private Label cardExpMessage;
	@FXML
	private Label holderNameMessage;
	
	
	// Grid panel
	@FXML
	private GridPane cosignerInformation;
	
	// Comboboxes
	@FXML
	private ComboBox customerAge;
	@FXML
	private ComboBox cosignerAge;
	@FXML
	private ComboBox cardCompany;
	@FXML
	private ComboBox expMonth;
	@FXML
	private ComboBox expYear;
	
	// Buttons
	@FXML
	private Button nextButton;
	
	private Parent parent;
	private Scene scene;
	private Stage stage;
	
	final ObservableList<String> cardValues = FXCollections
			.observableArrayList("MasterCard", "American Express", "Visa" );
	
	final ObservableList<String> monthValues = FXCollections
			.observableArrayList("01", "02", "03","04", "05","06","07","08","09","10","11","12" );
	final ObservableList<String> yearValues = FXCollections
			.observableArrayList("14", "15", "16","17", "18","19","20","21","22","23","24","25" );
	
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
						
			info = RentalInfo.getRentalInfo();
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Getting customers License Number
		ResultSet result;
		try {
			// System.out.println("SELECT driverLicenseNo FROM customer WHERE custID = " + info.getId());
			result = Query
					.select("SELECT driverLicenseNo FROM customer WHERE custID = " + info.getId());
			if (result.next());
				customerLicense.setText(result.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		expMonth.setItems(monthValues);
		expYear.setItems(yearValues);
		cardCompany.setItems(cardValues);
	}
	
	@FXML
	private void handleCancelButton() {
		// System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		System.exit(0);
	}
	
	/**
	 * handler to go back to previous screen
	 */
	@FXML
	private void handleBackButton() {
		
		RentController back = new RentController();
		back.launchRentController(stage);
		back.redirectHome(stage);
		
	}
	/**
	 * Handler for next button, takes to the final rental screen
	 */
	@FXML
	private void handleNextButton() {
		
		
		if (generalValidation())
		{
			fillInfo();
			FinalScreenController finalSc = new FinalScreenController();
			finalSc.launchFinalScreenController(stage);
			finalSc.redirectHome(stage);
		}
		else
		{
			displayErrors();
		}
		
	}
	
	
	/**
	 * Private method used internally to fill the shared variables object (Singleton)
	 */
	private void fillInfo() {
		
		// Dummy code to keep database connection alive
		try {
			Query.select("SELECT * from Rents");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RentalInfo.setCardNumber(cardNumber.getText());
		RentalInfo.setHolderName(holderName.getText());
		RentalInfo.setExpMonth(expMonth.getValue().toString());
		RentalInfo.setExpYear(expYear.getValue().toString());
		RentalInfo.setCardCompany(cardCompany.getValue().toString());
		RentalInfo.setCustomerLicense(customerLicense.getText());
		RentalInfo.setCustomerAge(customerAge.getValue().toString());
		
		// Information also necessary in case of Cosigner
		if (Integer.parseInt(customerAge.getValue().toString()) < 25)
		{
			RentalInfo.setCosignerLastname(cosignerLastname.getText());
			RentalInfo.setCosignerFirstname(cosignerFirstname.getText());
			RentalInfo.setCosignerLicense(cosignerLicense.getText());
			RentalInfo.setCosignerAge(cosignerAge.getValue().toString());
		}
				
	}
	/**
	 * Method that verifies if a string has characters in between
	 * @param str
	 * @return true if the string is numeric, false otherwise
	 * @pre str is a valid string data type
	 * @post returns true or false according to string content
	 */
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	/**
	 * Private method to validate comboboxes and some texfields before moving to next screen
	 * @pre none
	 * @post returns true if all screen fields are valid or false otherwise
	 * @return
	 */
	private boolean generalValidation() {
		
		// returning false if customer age not set
		if (customerAge.getValue() == null)
			return false;
		
		// case with cosigner
		if (Integer.parseInt(customerAge.getValue().toString()) < 25 )
		{
			if (cosignerAge.getValue() == null || cosignerLastname.getText() == null || cosignerFirstname.getText() == null)
				return false;
			
			// Validating Cosigner License
			if (cosignerLicense.getLength() != 7)
				return false;
		}
		if (holderName.getText() == null || cardCompany.getValue() == null || expMonth.getValue() == null || expYear.getValue() == null)
			return false;
		

		
		// Validating card number
		if (cardNumber.getLength() != 16)
			return false;
		
		// at this point all fields are valid
		return true;
		
	}
	/**
	 * Private method that display error messages in fields with missing information
	 */
	private void displayErrors() {
		
		if (cardCompany.getValue() == null)
			cardCompanyMessage.setVisible(true);
		
		if (expMonth.getValue() == null || expYear.getValue() == null)
			cardExpMessage.setVisible(true);
		
		if (holderName.getLength() == 0)
			holderNameMessage.setVisible(true);
		
		if (cardNumber.getLength() != 16)
			cardMessage.setVisible(true);
		
		if (customerAge.getValue() == null)
		{
			customerAgeMessage.setVisible(true);
			return;
		}
		
		if (Integer.parseInt(customerAge.getValue().toString()) < 25 )
		{
			if (cosignerLastname.getLength() == 0)
				cosignerLnameMessage.setVisible(true);
			if (cosignerFirstname.getLength() == 0)
				cosignerFnameMessage.setVisible(true);
			if (cosignerAge.getValue() == null)
				cosignerAgeMessage.setVisible(true);
			if (cosignerLicense.getLength() != 7)
				cosignerLicenseMessage.setVisible(true);
		}

	}
	
	/**
	 * Method to handle customer Age selection
	 * @pre none
	 * @post enables cosigner information if selected age is 24 or younger
	 */
	@FXML
	private void handleCustomerAge () {
		
		// Dummy code to keep database connection alive
		try {
			Query.select("SELECT * from Rents");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (customerAge.getValue() != null)
			customerAgeMessage.setVisible(false);
		
		if (Integer.parseInt(customerAge.getValue().toString()) < 25 )
		{
			cosignerInfoLabel.setVisible(true);
			cosignerInformation.setVisible(true);
			// cosignerAge.setDisable(false);
		}
		else
		{
			cosignerInfoLabel.setVisible(false);
			cosignerInformation.setVisible(false);
			// cosignerAge.setDisable(true);
		}
		
			
	}
	
	
	/** cosigner Age handler
	 * removes error message if a value is selected
	 */
	@FXML
	private void handleCosignerAge()
	{
		if (cosignerAge.getValue() != null)
			cosignerAgeMessage.setVisible(false);
	}
	
	@FXML
	private void handleHolder() {
		if (holderName.getText() != null)
			holderNameMessage.setVisible(false);
	}
	@FXML
	private void handleCosignerLastname () {
		if (cosignerLastname.getText() != null)
			cosignerLnameMessage.setVisible(false);
	}
	@FXML
	private void handleCosignerFirstname () {
		if (cosignerFirstname.getText() != null)
			cosignerFnameMessage.setVisible(false);
	}
	
	@FXML
	private void handleCardCompany() {
		if (cardCompany.getValue() != null)
			cardCompanyMessage.setVisible(false);
	}
	@FXML
	private void handleExpMonth() {
		if (expMonth.getValue() != null && expYear.getValue() != null)
			cardExpMessage.setVisible(false);
	}
	@FXML
	private void handleExpYear() {
		if (expMonth.getValue() != null && expYear.getValue() != null)
			cardExpMessage.setVisible(false);
	}
	/**
	 * Handler method to verify length of cosigners driver's license
	 */
	@FXML
	private void handleCosignerLicense () {
		
		// Validating Cosigner License
		if (cosignerLicense.getLength() == 7)
			cosignerLicenseMessage.setVisible(false);
		else
			cosignerLicenseMessage.setVisible(true);
	}

	/**
	 * Handler to validate number of digits and data type in customer credit card
	 */
	@FXML
	private void validateCardLength () {
		if (cardNumber.getLength() == 16)
			cardMessage.setVisible(false);
		else
			cardMessage.setVisible(true);
	}
//	@FXML
//	private void validateCardLength () {
//		if (cardNumber.getLength() == 16 && isNumeric(cardNumber.getText())) {
//			cardMessage.setVisible(false);
//			
//		}
//		else {
//			if (cardNumber.getLength() != 0)
//				cardMessage.setVisible(true);
//			nextButton.setDisable(true);
//		}
//	}
}
