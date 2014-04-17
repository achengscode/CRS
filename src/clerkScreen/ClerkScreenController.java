package clerkScreen;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.ResultSet;

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
	private ComboBox fMonthBox;
	@FXML
	private ComboBox fDayBox;
	@FXML
	private ComboBox fYearBox;
	@FXML
	private ComboBox fTimeBox;
	@FXML
	private ComboBox tMonthBox;
	@FXML
	private ComboBox tDayBox;
	@FXML
	private ComboBox tYearBox;
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
						System.out.println("Here" + t1.toString());
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
		fYearBox.setItems(yearCategory);
		tYearBox.setItems(yearCategory);
		fDayBox.setItems(dayCategory);
		tDayBox.setItems(dayCategory);
		fMonthBox.setItems(monthCategory);
		tMonthBox.setItems(monthCategory);
		fTimeBox.setItems(timeCategory);
		tTimeBox.setItems(timeCategory);
	}

	/**
	 * Event handler for the searchCustomer button.
	 * 
	 */
	@FXML
	private void handleSearchButton() {

		try {

			// Declaring a variable to store temporarily query result result
			ResultSet result;

			// SQL Query must be adjusted depending on the search parameter
			// given (phone or lastname)
			if (type.getValue() != "All") {
				if (category.getValue() != "All")
					result = Query
							.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.vehicle_category, V.make, V.model, V.vehicle_year, V.colour "
									+ "FROM Vehicle_Rent V, Vehicle_Category C "
									+ "WHERE V.vehicleID = C.vehicleID AND V.vehicle_type = '"
									+ type.getValue()
									+ "'  AND C.vehicle_category = '"
									+ category.getValue() + "'");
				else
					result = Query
							.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.vehicle_category, V.make, V.model, V.vehicle_year, V.colour "
									+ "FROM Vehicle_Rent V, Vehicle_Category C "
									+ "WHERE V.vehicleID = C.vehicleID and V.vehicle_type = '"
									+ type.getValue() + "'");
			}

			else {
				if (category.getValue() != "All")
					result = Query
							.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.vehicle_category, V.make, V.model, V.vehicle_year, V.colour "
									+ "FROM Vehicle_Rent V, Vehicle_Category C "
									+ "WHERE V.vehicleID = C.vehicleID  AND C.vehicle_category = '"
									+ category.getValue() + "'");
				else
					result = Query
							.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.vehicle_category, V.make, V.model, V.vehicle_year, V.colour "
									+ "FROM Vehicle_Rent V, Vehicle_Category C "
									+ "WHERE V.vehicleID = C.vehicleID");
			}

			resultList.clear();

			// verify that the the result set has at least one value
			if (!result.next()) {
				// Show label with Not found Message
				notFoundMsg.setVisible(true);
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
				// System.out.println(result.getString(8));
				resultList.add(tuple);

			}

		} catch (SQLException e) {

			System.out.println("Exception");
			e.printStackTrace();
		}
	}

	/**
	 * Event handler for the cancel/exit button
	 * 
	 */
	@FXML
	private void handleExitButton() {
		System.out.println("You pressed Exit!");
		// Instead of exiting the program, cancel button should move the user to
		// previous screen
		System.exit(0);
	}

	/**
	 * Method that switches to next screen in rental process
	 * @pre A result row has been selected and the Next button was pressed
	 * @post The RentScreen Controller object is created and a new window containing selected row results is created
	 */
	@FXML
	private void handleNextButton() {
		System.out.println(resultsTable.getSelectionModel().getSelectedItem()
				.getVehicleID());
		
		RentController rent = new RentController(resultsTable.getSelectionModel().getSelectedItem());
		rent.launchRentController(stage);
		rent.redirectHome(stage);
	}

	/**
	 * Handler to detect if comboboxes are filled with required information to
	 * initiate search process
	 */
	@FXML
	private void handleCombo() {
		// System.out.println("type: " + type.getValue());
		// System.out.println("category: " + category.getValue());
		notFoundMsg.setVisible(false);
		if (type.getValue() == null || category.getValue() == null)
			return;
		searchButton.setDisable(false);
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
			// System.out.println("No row selected");
			return;
		}
		// System.out.println("Row Successfully selected");
		nextButton.setDisable(false);
	}
}
