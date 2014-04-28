package clerkScreen;

import java.util.Calendar;

import databaseManagement.Query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import eu.schudt.javafx.controls.calendar.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import login.LoginController;
import rentScreen.RentController;

/**
 * Controller class to handle Clerk Screens related to Rent, Return and Reports
 * 
 * @author Ignacio Perez
 * 
 */

public class ClerkScreenController implements Initializable {

	// Combo box declaration
	@FXML
	private ComboBox type;
	@FXML
	private ComboBox category;
	// Combo declarations to handle pick up and return dates
	@FXML
	private ComboBox fTimeBox;
	@FXML
	private ComboBox tTimeBox;

	// Text Label to display "Not found Message"
	@FXML
	private Label notFoundMsg;
	
	

	// Declaring Table and Columns
	@FXML
	private TableView<VehicleSearchRow> resultsTable;
	@FXML
	private TableColumn vehicleIDCol;
	@FXML
	private TableColumn licPlateCol;
	@FXML
	private TableColumn typeCol;
	@FXML
	private TableColumn categoryCol;
	@FXML
	private TableColumn makeCol;
	@FXML
	private TableColumn modelCol;
	@FXML
	private TableColumn yearCol;
	@FXML
	private TableColumn colourCol;

	// Button declaration
	@FXML
	private Button searchButton;
	@FXML
	private Button nextButton;
	@FXML
	private Button exitButton;

	// Date pickers
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
	
	// Labels
	@FXML
	private Label dateMessage;
	@FXML
	private Label timeMessage;
	@FXML
	private Label typeMessage;
	@FXML
	private Label categoryMessage;
	
	
	private Parent parent;
	private Scene scene;
	@SuppressWarnings("unused")
	private Stage stage;

	ObservableList<VehicleSearchRow> resultList;

	// List for date comboboxes
	final ObservableList<String> yearCategory = FXCollections
			.observableArrayList("2014", "2015");

	ObservableList<String> dayCategory = FXCollections.observableArrayList("1",
			"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
			"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
			"25", "26", "27", "28", "29", "30", "31");
	final ObservableList<String> monthCategory = FXCollections
			.observableArrayList("01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12");
	final ObservableList<String> timeCategory = FXCollections
			.observableArrayList("01:00", "02:00", "03:00", "04:00", "05:00",
					"06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
					"12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
					"18:00", "19:00", "20:00", "21:00", "22:00", "23:00",
					"24:00");
	// Vehicle selection comboboxes' lists.
	final ObservableList<String> typeCategory = FXCollections
			.observableArrayList("Car", "Truck", "All");
	final ObservableList<String> anyCategory = FXCollections
			.observableArrayList("All");

	final ObservableList<String> carCategory = FXCollections
			.observableArrayList("Economy", "Compact", "Mid-size", "Standard",
					"Full-size", "Premium", "Luxury", "SUV", "Van", "All");
	final ObservableList<String> truckCategory = FXCollections
			.observableArrayList("24-foot", "15-foot", "12-foot", "box Truck",
					"Cargo Van", "All");

	public ClerkScreenController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"ClerkScreenController.fxml"));
		fxmlLoader.setController(this);
		try {
			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent);
			
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchClerkController(Stage stage) {
		this.stage = stage;
		stage.setTitle("Clerk View");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.hide();
		stage.show();
	}

	// main method needed to be implemented by the controller class.
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		resultList = FXCollections.observableArrayList();
		vehicleIDCol
				.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
						"vehicleID"));
		licPlateCol
				.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
						"licPlate"));
		typeCol.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"type"));
		categoryCol
				.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
						"category"));
		makeCol.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"make"));
		modelCol.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"model"));
		yearCol.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"year"));
		colourCol
				.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
						"colour"));

		resultsTable.setItems(resultList);

		// Setting initial default values for the combobox
		type.setItems(typeCategory);
		category.setItems(anyCategory);
		// type.setValue("All");
		// category.setValue("All");

		type.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener() {
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue ov, Object t, Object t1) {
						
						switch (t1.toString()) {
						case "0":
							category.setItems(carCategory);
							category.setValue("All");
							break;
						case "1":
							category.setItems(truckCategory);
							category.setValue("All");
							break;
						case "2":
							category.setItems(anyCategory);
							category.setValue("All");
							break;

						}
					}
				});

		// Populating Date comboboxes with FIXED values
		/*
		fYearBox.setItems(yearCategory);
		tYearBox.setItems(yearCategory);
		fDayBox.setItems(dayCategory);
		tDayBox.setItems(dayCategory);
		fMonthBox.setItems(monthCategory);
		tMonthBox.setItems(monthCategory);*/
		fTimeBox.setItems(timeCategory);
		tTimeBox.setItems(timeCategory);
		
		startDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		endDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	/**
	 * Event handler for the searchCustomer button.
	 * 
	 */
	@FXML
	private void handleSearchButton() {

		if (validateScreen()) {
			try {

				// Declaring a variable to store temporarily query result result
				ResultSet result;
				String dateFrom = fillDateFrom();
				String dateTo = fillDateTo();

				// SQL Query must be adjusted depending on the search parameter
				// given (phone or lastname)
				if (type.getValue() != "All") {
					if (category.getValue() != "All")
						result = Query
								.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
										+ "FROM Vehicle_Rent V, Vehicle_Category C "
										+ "WHERE V.vehicleID = C.vehicleID AND V.vehicle_type = '"
										+ type.getValue()
										+ "'  AND C.rentCategory = '"
										+ category.getValue()
										+ "' "
										+ "AND V.VehicleID IN "
										+ "(SELECT vehicleID FROM Vehicle_Rent "
										+ "WHERE vehicleID NOT IN "
										+ "(SELECT vehicleID FROM Rents WHERE (rentStart - timestamp('"
										+ dateFrom
										+ "')) < 0 and (rentEnd - timestamp('"
										+ dateFrom
										+ "')) > 0"
										+ " union "
										+ "SELECT VehicleID FROM Rents  WHERE  (rentStart - timestamp('"
										+ dateFrom
										+ "')) > 0 and (rentStart - timestamp('"
										+ dateTo + "')) <  0))");
					else
						result = Query
								.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
										+ "FROM Vehicle_Rent V, Vehicle_Category C "
										+ "WHERE V.vehicleID = C.vehicleID and V.vehicle_type = '"
										+ type.getValue()
										+ "' "
										+ "AND V.VehicleID IN "
										+ "(SELECT vehicleID FROM Vehicle_Rent "
										+ "WHERE vehicleID NOT IN "
										+ "(SELECT vehicleID FROM Rents WHERE (rentStart - timestamp('"
										+ dateFrom
										+ "')) < 0 and (rentEnd - timestamp('"
										+ dateFrom
										+ "')) > 0"
										+ " union "
										+ "SELECT VehicleID FROM Rents  WHERE  (rentStart - timestamp('"
										+ dateFrom
										+ "')) > 0 and (rentStart - timestamp('"
										+ dateTo + "')) <  0))");
				}

				else {
					if (category.getValue() != "All")
						result = Query
								.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
										+ "FROM Vehicle_Rent V, Vehicle_Category C "
										+ "WHERE V.vehicleID = C.vehicleID  AND C.rentCategory = '"
										+ category.getValue()
										+ "' "
										+ "AND V.VehicleID IN "
										+ "(SELECT vehicleID FROM Vehicle_Rent "
										+ "WHERE vehicleID NOT IN "
										+ "(SELECT vehicleID FROM Rents WHERE (rentStart - timestamp('"
										+ dateFrom
										+ "')) < 0 and (rentEnd - timestamp('"
										+ dateFrom
										+ "')) > 0"
										+ " union "
										+ "SELECT VehicleID FROM Rents  WHERE  (rentStart - timestamp('"
										+ dateFrom
										+ "')) > 0 and (rentStart - timestamp('"
										+ dateTo + "')) <  0))");
					else
						result = Query
								.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
										+ "FROM Vehicle_Rent V, Vehicle_Category C "
										+ "WHERE V.vehicleID = C.vehicleID "
										+ "AND V.VehicleID IN "
										+ "(SELECT vehicleID FROM Vehicle_Rent "
										+ "WHERE vehicleID NOT IN "
										+ "(SELECT vehicleID FROM Rents WHERE (rentStart - timestamp('"
										+ dateFrom
										+ "')) < 0 and (rentEnd - timestamp('"
										+ dateFrom
										+ "')) > 0"
										+ " union "
										+ "SELECT VehicleID FROM Rents  WHERE  (rentStart - timestamp('"
										+ dateFrom
										+ "')) > 0 and (rentStart - timestamp('"
										+ dateTo + "')) <  0))");
				}

				resultList.clear();

				// verify that the the result set has at least one value
				if (!result.next()) {
					Dialogs.showErrorDialog(stage, "No results found!","Error!");
					
					// Show label with Not found Message
					//notFoundMsg.setVisible(true);
					// Disable next button
					nextButton.setDisable(true);
					return;
				}

				// at this point we have a set of tuples as result
				// rewind result cursor
				result.beforeFirst();

				while (result.next()) {
					VehicleSearchRow tuple = new VehicleSearchRow();

					tuple.setVehicleID(result.getString(1));
					tuple.setLicPlate(result.getString(2));
					tuple.setType(result.getString(3));
					tuple.setCategory(result.getString(4));
					tuple.setMake(result.getString(5));
					tuple.setModel(result.getString(6));
					tuple.setYear(result.getString(7).substring(0, 4));
					tuple.setColour(result.getString(8));
					resultList.add(tuple);

				}

			} catch (SQLException e) {

				System.out.println("Exception");
				e.printStackTrace();
			}
		}
		else
		{
			Dialogs.showErrorDialog(stage, "Errors were found in your search Parameters","Error!");
			resultList.clear();
		}
	}

	/**
	 * Method that switches to next screen in rental process
	 * @pre A result row has been selected and the Next button was pressed
	 * @post The RentScreen Controller object is created and a new window containing selected row results is created
	 */
	@FXML
	private void handleNextButton() {
		
		
			RentController rent = new RentController(resultsTable
					.getSelectionModel().getSelectedItem(), fillDateFrom(),
					fillDateTo());
			rent.launchRentController(stage);
			rent.redirectHome(stage);
		
	}

	/**
	 * Handler to detect if comboboxes are filled with required information to
	 * initiate search process
	 */
	@FXML
	private void handleCombo() {
		
		notFoundMsg.setVisible(false);
		if (type.getValue() != null)
			typeMessage.setVisible(false);
		if (category.getValue() != null)
			categoryMessage.setVisible(false);
		
	}

	/**
	 * Handle method to verify if a row in the resutls table has been selected
	 * 
	 * @pre A mouse click on the table is performed
	 * @post if a row is selected the "next" button is activated.
	 */
	@FXML
	private void handleClickTable() {
		if (resultsTable.getSelectionModel().getSelectedItem() == null) {
			
			return;
		}
		
		nextButton.setDisable(false);
	}
	
	/**
	 * Private method to convert the starting rental date and time given into SQL timestamp format
	 * @pre None
	 * @post A string containing a valid timestamp sql format is returned
	 * @return a string with timestamp format filled according to the comoboxes values
	 */
	private String fillDateFrom()
	{
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(startDatePicker.getSelectedDate()) + " " + fTimeBox.getValue() + ":00";
		
		
	}
	
	/**
	 * Private method to convert the ending rental date and time given into SQL timestamp format
	 * @pre None
	 * @post A string containing a valid timestamp sql format is returned
	 * @return a string with timestamp format filled according to the comoboxes values
	 */
	private String fillDateTo()
	{

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(endDatePicker.getSelectedDate()) + " " + tTimeBox.getValue() + ":00";
		//return "2014-04-18 11:00:00";
	}
	
	public void redirectHome(Stage stage) {
		this.stage = stage;
		stage.setTitle("Clerk Rent");
		stage.setScene(scene);

		stage.hide();
		stage.show();
	}
	
	/**
	 * Method to validate input data in the screen before submitting the search query
	 * @pre none
	 * @post returns true if all fields are valid, false otherwise
	 * @return
	 */
	private boolean validateScreen() {
		
		// Manually running Date handler
		handleDate();
		
		
		boolean result = true;
		
		// Checking type
		if (type.getValue() == null) {
			result = false;
			typeMessage.setVisible(true);
		}
		// Checking category
		if (category.getValue() == null) {
			result = false;
			categoryMessage.setVisible(true);
		}
		
		// Checking null time
		if (fTimeBox.getValue() == null || tTimeBox.getValue() == null)
		{
			result = false;
			timeMessage.setVisible(true);
		}
				
		// Checking if for same start and end date the end time is before start time
		else if (startDatePicker.getSelectedDate() != null && endDatePicker.getSelectedDate() != null)
		{
			
			if ( startDatePicker.getSelectedDate().equals(endDatePicker.getSelectedDate()) )
			{
				
				if (fTimeBox.getValue().toString().compareTo(tTimeBox.getValue().toString()) > 0)
				{
					result = false;
					timeMessage.setVisible(true);
				}
				
			}
				
		}
		
		
		
	    
		// Checking empty date values 
		if (startDatePicker.getSelectedDate() == null || endDatePicker.getSelectedDate() == null)
		{
			result = false;
			dateMessage.setVisible(true);
		}
		
		// Checking start date after end date
		if (startDatePicker.getSelectedDate() != null && endDatePicker.getSelectedDate() != null)
		{
			
			java.util.Date today = new Date(System.currentTimeMillis());
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
				today = fmt.parse(fmt.format(today));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// Date validation
			if (startDatePicker.getSelectedDate().before(today)
					|| endDatePicker.getSelectedDate().before(today))
			{
				result = false;
				dateMessage.setVisible(true);
			}
			
			
			
			if ( startDatePicker.getSelectedDate().after(endDatePicker.getSelectedDate()) )
			{
				result = false;
				dateMessage.setVisible(true);
				
			}
				
		}
		
		
		return result;
	}
	
	@FXML
	private void handleTime() {
		if (fTimeBox.getValue() != null && tTimeBox.getValue() != null)
		{
			if (startDatePicker.getSelectedDate() != null && endDatePicker.getSelectedDate() != null)
			{
				
				if ( startDatePicker.getSelectedDate().equals(endDatePicker.getSelectedDate())  )
				{
					if (fTimeBox.getValue().toString().compareTo(tTimeBox.getValue().toString()) < 0)
						timeMessage.setVisible(false);
					
				}
				else
					timeMessage.setVisible(false);
			}	
		}
	}
	
	/**
	 * Handler that verifies if the date picker start value is before or at the same day of the returning day
	 */
	@FXML
	private void handleDate() {
		
		if (startDatePicker.getSelectedDate() != null && endDatePicker.getSelectedDate() != null)
		{
			
			java.util.Date today = new Date(System.currentTimeMillis());
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
				today = fmt.parse(fmt.format(today));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		    
			// Date validation
			if (startDatePicker.getSelectedDate().before(today)
					|| endDatePicker.getSelectedDate().before(today))
			{
				return;
			}
			
			else
			{
				//System.out.println(startDatePicker.getSelectedDate().before(endDatePicker.getSelectedDate()));
				if (startDatePicker.getSelectedDate().before(
						endDatePicker.getSelectedDate())
						|| startDatePicker.getSelectedDate().equals(
								endDatePicker.getSelectedDate())) {
					dateMessage.setVisible(false);

				}
			}
			
				
		}
	}
	
	/**
	 * Logout button handler
	 */
	@FXML
	private void handleCancelButton() {
		
		LoginController login = new LoginController();
		login.launchLoginController(stage);
			
	}
	
}
