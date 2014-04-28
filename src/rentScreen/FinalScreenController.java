package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clerkScreen.ClerkScreenController;
import databaseManagement.Query;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import login.LoginController;

/**
 * Controller class for final screen of rental process
 * Displays a text summary with rental information in a printable format
 * @author ignacio
 *
 */

public class FinalScreenController implements Initializable{

	@FXML
	private TextArea summary;
	
	// Button
	@FXML
	private Button finishButton;
	@FXML
	private Button printButton;
	@FXML
	private Button returnButton;
	@FXML
	private Button backButton;
	
	// Labels
	@FXML
	private Label finalMessage;
	@FXML
	private Label rentalIdMessage;
	@FXML
	private Label rentalNumberMessage;
	
	private Parent parent;
	private Scene scene;
	private Stage stage;
	
	private RentalInfo info;
	
	public FinalScreenController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"FinalScreen.fxml"));

		fxmlLoader.setController(this);
		try {
			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent);
						
			info = RentalInfo.getRentalInfo();
			if (info.getBookingStatus())
			{
				fillSummary(info.getRentId());
			}
			else
			{
				fillSummary("Undefined");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void launchFinalScreenController(Stage stage) {
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
	/**
	 * Method to fill information corresponding to Rental Report and form to be signed by customer
	 */
	private void fillSummary(String rentalId) {
		
		// Title
		summary.setText("\t\t\t Rental Summary \n\n");
		
		// Customer Info Field
		summary.appendText("Customer Information\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("Rental ID: " + rentalId + "\n");
		summary.appendText("Customer Name: " + info.getLastname() + ", " + info.getFirstname() +"\n");
		summary.appendText("Drivers License Number: " + info.getCustomerLicense() + "\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
		// Cosigner Field
		summary.appendText("Cosigner\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("Cosigner Required: ");
		if (Integer.parseInt(info.getCustomerAge()) < 25)
		{
			summary.appendText("Yes \n");
			summary.appendText("Cosigner Name: " + info.getCosignerLastname() + ", " + info.getCosignerFirstname() + "\n");
			summary.appendText("Cosigner License Number: " + info.getCosignerLicense() + "\n");
		}
		else
		{
			summary.appendText("No \n");
		}
		
		summary.appendText("\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
		// Car Information
		summary.appendText("Vehicle Information\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("Vehicle ID: " + info.getVehicleID() + "\n");
		summary.appendText("Vehicle Lic. Plate: " + info.getLicense() + "\n");
		summary.appendText("Vehicle Type: " + info.getType() + "\n");
		summary.appendText("Vehicle Category: " + info.getCategory() + "\n");
		summary.appendText("Vehicle Make: " + info.getMake() + "\n");
		summary.appendText("Vehicle Model: " + info.getModel() + "\n");
		summary.appendText("Vehicle Year: " + info.getYear() + "\n");
		summary.appendText("Vehicle Colour: " + info.getColour() + "\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
		// Additional Equipment
		summary.appendText("Additional Equipment\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("Ski Rack: ");
		if (info.isSkiRack())
			summary.appendText("Yes\n");
		else
			summary.appendText("No\n");
		
		summary.appendText("Child Seat: ");
		if (info.isChildSeat())
			summary.appendText("Yes\n");
		else
			summary.appendText("No\n");
		
		summary.appendText("Lift Gate: ");
		if (info.isLiftGate())
			summary.appendText("Yes\n");
		else
			summary.appendText("No\n");
		
		summary.appendText("Car-towing Eq.: ");
		if (info.isTowingEq())
			summary.appendText("Yes\n");
		else
			summary.appendText("No\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
		// Payment
		summary.appendText("Payment Information\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("Vehicle Price: " + info.getVehiclePrice() + "\n");
		summary.appendText("Equipment Price: " + info.getEquipmentPrice() + "\n");
		summary.appendText("Total Price: " + info.getTotalPrice() + "\n\n");
		
		summary.appendText("Credit Card Company: " + info.getCardCompany() + "\n");
		summary.appendText("Credit Card Number: " + info.getCardNumber() + "\n");
		summary.appendText("Credit Card Exp. Date: " + info.getExpMonth() + "/" + info.getExpYear() + "\n");
		summary.appendText("Credit Card Holder's Name: " + info.getHolderName() + "\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
		// Signatures
		summary.appendText("Signatures and Agreement\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		summary.appendText("\n\n\n");
		summary.appendText("\t--------------------");
		if (Integer.parseInt(info.getCustomerAge()) < 25)
			summary.appendText("\t\t\t\t--------------------");
		summary.appendText("\n\n");
		summary.appendText("\t Customer Signature");
		if (Integer.parseInt(info.getCustomerAge()) < 25)
			summary.appendText("\t\t Cosigner Signature");
		summary.appendText("\n\n");
		summary.appendText("-----------------------------------------------------------------------------------------------------\n");
		
	}
	
	@FXML
	private void handleFinishButton() {
		
		try {
			Query.autoCommitOff();
			String newId;
			
			// Getting next available ID in Rents Table
			ResultSet result;
			result = Query.select("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'crs_database' AND   TABLE_NAME   = 'Rents'");
			result.next();
			newId = result.getString(1);
			
			if (info.getBookingStatus())
			{
				String query = String.format("UPDATE Rents SET isBooked='0' WHERE rentID='%s'", info.getId());
				Query.update(query);
			}
			else
			{
				// Insert query to Rents Table
				Query.insert("INSERT INTO Rents (vehicleID,custID, RentStart, rentEnd, isBooked) values ('"
						+ info.getVehicleID()
						+ "',"
						+ info.getId()
						+ ",'"
						+ info.getFrom() + "','" + info.getTo() + "', 0)");

				// System.out.println("INSERT INTO Rents (vehicleID,custID, RentStart, rentEnd, isBooked) values ('"
				// + info.getVehicleID()
				// + "',"
				// + info.getId()
				// + ",'"
				// + info.getFrom() + "','" + info.getTo() + "', 0)");

				// Insert Query to rentCardInfo Table
				Query.insert("INSERT INTO rentCardInfo values (" + newId + ",'"
						+ info.getCardNumber() + "','" + info.getCardCompany()
						+ "','" + info.getExpYear() + "','"
						+ info.getExpMonth() + "')");
			}
			
//			System.out.println("INSERT INTO rentCardInfo values (" + newId + ",'"
//					+ info.getCardNumber() + "','" + info.getCardCompany() + "','" + info.getExpYear() + "','"
//					+ info.getExpMonth() + "')");
			
			// Insert into equipmentUsed Table
			if (info.isSkiRack())
				Query.insert("INSERT INTO EquipmentUsed values (" + newId + ",1)");
			if (info.isChildSeat())
				Query.insert("INSERT INTO EquipmentUsed values (" + newId + ",2)");
			if (info.isLiftGate())
				Query.insert("INSERT INTO EquipmentUsed values (" + newId + ",3)");
			if (info.isTowingEq())
				Query.insert("INSERT INTO EquipmentUsed values (" + newId + ",4)");
			
//			System.out.println("INSERT INTO EquipmentUsed values (" + newId + ",1)");
			
			// Insert into Cosigner Table if age is under 25
			// Converting cosigner age into birth date.
			// To be modified later!!
			
			
			if (Integer.parseInt(info.getCustomerAge()) < 25) {
				int birthYearNumber = 2014 - Integer.parseInt(info.getCosignerAge());
				String birthDate = birthYearNumber + "-01-01";

				Query.insert("INSERT INTO cosigner values (" + info.getId()
						+ ",'" + info.getCosignerLicense() + "','" + birthDate
						+ "','" + info.getCosignerLastname() + ", "
						+ info.getCosignerFirstname() + "')");

				// System.out.println("INSERT INTO cosigner values (" +
				// info.getId() + ",'"
				// + info.getCosignerLicense() + "','" + birthDate + "','"
				// + info.getCosignerLastname() + ", "
				// + info.getCosignerFirstname() + "')");
			}
			
			
			// End and commit queries
			Query.commit();
			Query.autoCommitOn();
			
			// Displaying successful transaction operation
			if (info.getBookingStatus())
			{
				Dialogs.showInformationDialog(stage, "Transaction Successfully Completed\n" + "Rent ID Number: " + newId,"Transaction Completed");
			}
			else
			{
				Dialogs.showInformationDialog(stage, "Transaction Successfully Completed\n!" + "Rent ID Number: " + info.getRentId(),
						"Transaction Completed");	
			}
			
			backButton.setDisable(true);
			finalMessage.setVisible(true);
			rentalNumberMessage.setVisible(true);
			rentalIdMessage.setText(newId);
			
			// Reprinting report including Rental ID
			fillSummary(newId);
			
			// Cleaning RentalInfo object
		    info.flushInfo();
			
			// Activating printing Button
			printButton.setDisable(false);
			// Activating ReturnHome Button
			returnButton.setDisable(false);
			// Deactivating Submit Button
			finishButton.setDisable(true);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Dialogs.showErrorDialog(stage, "Transaction Error, Please Verify Data and Try Again","Error!");
			e.printStackTrace();
			
		}
		
		
		
	}

	/**
	 * Handler for printing button
	 */
	@FXML
	private void handlePrintButton() {
	
	// Not implemented yet
	// Should launch a dialogbox indicating that the document was sent to printer
		// Displaying successful transaction operation
					Dialogs.showInformationDialog(stage, "Agreement Form Sent to Printer");	

	}
	
	/**
	 * Handler to return to main clerk screen
	 */
	@FXML
	private void handleReturn() {
		ClerkScreenController returnSc = new ClerkScreenController();
		returnSc.launchClerkController(stage);
		returnSc.redirectHome(stage);
	}
	
	/**
	 * Handler to go to previous screen
	 */
	@FXML
	private void handleBackButton() {
		CosignerScreenController backSc = new CosignerScreenController();
		backSc.launchCosignerController(stage);
		backSc.redirectHome(stage);
	}
	
	@FXML
	private void handleLogoutButton() {
		info.flushInfo();
		LoginController login = new LoginController();
		login.launchLoginController(stage);
			
	}
}

