package rentScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import databaseManagement.Query;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
			
			fillSummary("Undefined");
			
			
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
		
		printButton.setDisable(false);	
		
	}

	@FXML
	private void handlePrintButton() {
	
		Query rentinsertion = new Query();
		rentinsertion.autoCommitOff();
	    rentinsertion.insert();

	Query.commit();

	Query.autoCommitOn();

	System.out.println("The employee is addded");
		System.out.println("INSERT INTO Rents (vehicleID,custID, RentStart, rentEnd, isBooked) values (" + info.getVehicleID() + "," + info.getId() + "," + info.getFrom() + "," + info.getTo() + ", 0)");	
		
	}
}
