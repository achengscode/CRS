package manager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;

import dataHold.ReportRow;
import dataHold.VehicleSearchRow;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dataHold.ReportRow;
import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;
import login.LoginController;
import dataHold.SetPriceRow;
import ui.AlertBox;
/**
 * ManagerController class
 * 
 * Servers as a controller class for Manager2Login.fxml
 * 
 * @author Puneet Kumar Dimri
 * 
 */

public class ManagerController implements Validator, Initializable {
	private Parent parent;
	private Scene scene;
	private Stage stage;
	/**
	 * managerID is used to hold the current managerID information.
	 */
    private String managerID;
	
	@FXML
	private Text welcomeText;

	@FXML
	private Button Generate;
	/**
	 * Below is the list of attributes used for the ADD VEHICLE TAB for the Manager.
	 */
	@FXML
	private Button logoutReportButton;
	
	@FXML
	private TextField vehicleID;
	
	@FXML
	private TextField plate;
	
	@FXML
	private TextField model;
	
	@FXML
	private TextField make;

	@FXML
	private TextField color;
	
	@FXML
	private TextField manufacturing;
	@FXML
	private Label idLabel;

	@FXML
	private Label modelLabel;

	@FXML
	private Label plateLabel;

	@FXML
	private Label makeLabel;

	@FXML
	private Label typeLabel;

	@FXML
	private Label categoryLabel;

	@FXML
	private Label yearLabel;

	@FXML
	private Label colorLabel;

	@FXML
	private Label numberLabel;

	@FXML
	private Label idInvalid;

	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox type;

	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox category;
	/**
	 * Below is the list of attributes used by manager for the ADD VEHICLE TAB
	 */

	@FXML
	private Button logoutAddButton;
	@FXML
	private TableView<ReportRow> reportTable;
	@FXML
	private TableColumn categoryyCol;
	@FXML
	private TableColumn numberCol;
	@FXML
	private TableColumn sumCol;

	@FXML
	private ComboBox selectReport;
	@FXML
	private Button generate;
	@FXML
	private TextField sum;
	@FXML
	private Label reportLabel;
	@FXML
	private TextField reportVehicle;
	@FXML
	private Label reportAmount;
	@FXML
	private Label reportDisplay;
	ObservableList<ReportRow> reportList;
	@FXML
	private Label plateIncorrectLabel;
    /**
     * Declaring below variables for Vehicle List tab of Manager
     */
	@FXML
	private Button logoutVehicleButton;
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
	@FXML
	private TableColumn listNumberCol;
	@FXML
	private TableColumn listSellingPriceCol;
	@FXML
	private Button moveButton;
	@FXML
	private Button removeButton;
	@FXML
	private ComboBox listVehicleType;
	@FXML
	private ComboBox listSearchType;
	@FXML
	private TextField listYear;
	@FXML
	private ComboBox listCategory;
	@FXML
	private Label listManuLabel;
	@FXML
	private Label listCategoryLabel;
	@FXML
	private Button listGetResult;
	@FXML
	private Label listVehicleIDLabel;
	@FXML
	private TextField listVehicleIDText;
	@FXML
	private Label listModelLabel;
	@FXML
	private TextField listModelText;
	@FXML
	private Label listMakeLabel;
	@FXML
	private TextField listMakeText;
	@FXML
	private Label listTypeLabel;
	@FXML
	private ComboBox listTypeComboBox;
	@FXML
	private Label listColorLabel;
	@FXML
	private TextField listColorText;
	@FXML
	private Label listPlateLabel;
	@FXML
	private TextField listPlateText;
	@FXML
	private Button listSearch;
	@FXML
	private Label listPriceLabel;
	@FXML
	private TextField listPriceText;
	@FXML
	private Label listSearchTypeLabel;
	@FXML
	private Label priceSellCardLabel;
	@FXML
	private Label priceSellLabel;
	@FXML
	private ComboBox priceSellCom;
	@FXML
	private TextField priceSellText;
	/**
	 * Below declarations used of the SET PRICE Tab of Manager
	 */
	@FXML
	private Button logoutPriceButton;
	@FXML
	private ComboBox priceSelect;
	@FXML
	private Button priceGenerate;
	@FXML
	private Button priceUpdate;
	@FXML
	private TableView<SetPriceRow> priceTable;
	@FXML
	private TableColumn priceTypeCol;
	@FXML
	private TableColumn priceHourCol;
	@FXML
	private TableColumn priceDayCol;
	@FXML
	private TableColumn priceWeekCol;
	@FXML
	private TableColumn priceEquipmentCol;
	@FXML
	private Label priceCategoryLabel;
	@FXML
	private TextField priceCategoryText;
	@FXML
	private Label priceHourLabel;
	@FXML
	private TextField priceHourText;
	@FXML
	private Label priceDayLabel;
	@FXML
	private TextField priceDayText;
	@FXML
	private Label priceWeekLabel;
	@FXML
	private TextField priceWeekText;
	@FXML
	private Button priceChangeButton;
	@FXML
	private Label priceEquipmentLabel;
	@FXML
	private TextField priceEquipmentText;
	@FXML
	private Button sellButton;
	@FXML
	private Label priceWarningLabel;
	
	ObservableList<SetPriceRow> priceList;
	ObservableList<VehicleSearchRow> resultList;
	ObservableList<String> categories = FXCollections.observableArrayList(
			"Car", "Truck");

	final ObservableList<String> carCategory = FXCollections
			.observableArrayList("Economy", "Compact", "Mid-size", "Standard",
					"Full-size", "Premium", "Luxury", "SUV", "Van");

	final ObservableList<String> truckCategory = FXCollections
			.observableArrayList("24-foot", "15-foot", "12-foot", "box Truck",
					"Cargo Van");

	public ManagerController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"Manager2Login.fxml"));
		fxmlLoader.setController(this);
		try {

			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent, 1400, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is called by the Login Controller class.
	 * @pre The manager provides the correct credentials.
	 * @post The manger login screen is displayed.
	 * @param stage
	 */
	public void redirectHome(Stage stage, String name) {
		this.stage = stage;
		managerID = name;
		stage.setTitle("Manager");
		stage.setScene(scene);
		// welcomeText.setText("Hello " + name + "! You are welcome.");

		stage.hide();
		stage.show();
	}
	/**
	 * To add listener to the category ComboBox in the ADD VEHICLE TAB..
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// vehicleID = new RestrictiveTextField();
		// vehicleID.setMaxLength(17);
		vehicleID.setPromptText("VIN NUMBER");

		
		type.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener() {
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue ov, Object t, Object t1) {
						
						switch (t1.toString()) {
						case "0":
							category.setItems(carCategory);
							break;
						case "1":
							category.setItems(truckCategory);
							break;

						}
					}

				});

		reportList = FXCollections.observableArrayList();
		categoryyCol
				.setCellValueFactory(new PropertyValueFactory<ReportRow, String>(
						"category"));
		numberCol
				.setCellValueFactory(new PropertyValueFactory<ReportRow, String>(
						"count"));
		sumCol.setCellValueFactory(new PropertyValueFactory<ReportRow, String>(
				"sum"));
		sumCol.setCellFactory(TextFieldTableCell.forTableColumn());
		sumCol.setOnEditCommit(new EventHandler<CellEditEvent<ReportRow, String>>() {
			@Override
			public void handle(CellEditEvent<ReportRow, String> t) {
				((ReportRow) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setSum(t
						.getNewValue());
			}
		});

		reportTable.setItems(reportList);

		reportTable.getColumns().get(2).setVisible(false);
		reportTable.setColumnResizePolicy(reportTable.CONSTRAINED_RESIZE_POLICY);
        initial();
        initializeSetPrice();
		
	}

/**
 * This method is used to verify the VIN number.
 * @pre The string provided should be not null
 * @post A boolean value is returned.
 */
	public boolean validator(String valid) {
		int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9,
				2, 3, 4, 5, 6, 7, 8, 9 };
		int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };

		String s = valid;
		s = s.replaceAll("-", "");
		s = s.toUpperCase();
		if (s.length() != 17)
			return false;

		int sum = 0;
		for (int i = 0; i < 17; i++) {
			char c = s.charAt(i);
			int value;
			int weight = weights[i];

			// letter
			if (c >= 'A' && c <= 'Z') {
				value = values[c - 'A'];
				if (value == 0)
					return false;
			}

			// number
			else if (c >= '0' && c <= '9')
				value = c - '0';

			// illegal character
			else
				return false;

			sum = sum + weight * value;

		}

		// check digit
		sum = sum % 11;
		char check = s.charAt(8);
		if (check != 'X' && (check < '0' || check > '9'))
			return false;
		if (sum == 10 && check == 'X')
			return true;
		else if (sum == check - '0')
			return true;
		else
			return false;

	}
/**
 * Method used to check if value selected for ComboBox or not.
 * @return
 */
	public boolean checkType() {
		try {
			type.getValue().toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Method used to check if a category is selected for ComboBox or not.
	 * @return
	 */

	public boolean checkCategory() {
		try {
			category.getValue().toString();
			return true;
		} catch (Exception e) {

			return false;
		}

	}
/**
 * To check if the input in the text field is and Integer or not.
 * @pre The string provided should be not null.
 * @param s
 * @return
 */
	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
/**
 * To check if the input string is null or not.
 * @param s
 * @return
 */
	public boolean isNull(String s) {
		return s.equalsIgnoreCase("");
	}
	/**
	 * To set the visibility of the Labels in the ADD VEHICLE TAB.
	 * @post The visibility of the warning labels is set to false.
	 */

	protected void setVisibleFalse() {
		idLabel.setVisible(false);
		plateLabel.setVisible(false);
		modelLabel.setVisible(false);
		typeLabel.setVisible(false);
		makeLabel.setVisible(false);
		categoryLabel.setVisible(false);
		numberLabel.setVisible(false);
		yearLabel.setVisible(false);
		idInvalid.setVisible(false);
		plateIncorrectLabel.setVisible(false);

	}
/**
 * Used to Display the Labels under the Text Field for which value is not provided.
 * @param vID
 * @param plateValue
 * @param modelValue
 * @param makeValue
 * @param mnf
 * @param colorValue
 * @return
 */
	private boolean setVisibleTrue(String vID, String plateValue,
			String modelValue, String makeValue, String mnf, String colorValue) {
		int count = 0;
		int flag = 0;
		int flag2 = 0;
		int flag3 =0;
		if (isNull(vID)) {
			idLabel.setVisible(true);
			count++;
			flag++;

		}

		if (isNull(plateValue)) {
			plateLabel.setVisible(true);
			count++;
			flag3++;
		}
		if(!checkPlate(plateValue) && flag3 == 0){
			plateIncorrectLabel.setVisible(true);
			count++;
		}

		if (isNull(modelValue)) {
			modelLabel.setVisible(true);
			count++;
		}

		if (isNull(makeValue)) {
			makeLabel.setVisible(true);
			count++;
		}
		if (isNull(mnf)) {
			yearLabel.setVisible(true);
			count++;
			flag2++;
		}
		if (!isInt(mnf) && flag2 == 0) {
			numberLabel.setVisible(true);
			count++;
		}

		if (isNull(colorValue)) {
			colorLabel.setVisible(true);
			count++;
		}
		if (!validator(vID) && flag == 0) {
			idInvalid.setVisible(true);
			count++;

		}
		if (!checkType()) {
			typeLabel.setVisible(true);
			count++;
		}
		if (!checkCategory()) {
			categoryLabel.setVisible(true);
			count++;
		}
		if (count == 0) {
			return true;
		}
		return false;

	}
	/**
	 * Method used to verify Plate Number.
	 * @pre The string provided should be not null.
	 */
	private boolean checkPlate(String s){
		String plate = "\\d{3}[A-Z]{3}";
		return s.matches(plate);
	}
	/**
	 * Method used to add the Vehicle to the Database.
	 * @pre Manager is logged into the system.
	 * @post Vehicle is added to the database.
	 * @param event
	 */

	@FXML
	protected void addVehicle(ActionEvent event) {
		setVisibleFalse();
		String vID = vehicleID.getText();

		String plateValue = plate.getText();

		String modelValue = model.getText();

		String makeValue = make.getText();

		String mnf = manufacturing.getText();

		String colorValue = color.getText();

		if (!setVisibleTrue(vID, plateValue, modelValue, makeValue, mnf,
				colorValue)) {
			return;
		}
		String type2 = type.getValue().toString();
		
		String categoryValue = category.getValue().toString();

		int manufacturingValue = Integer.parseInt(manufacturing.getText());

		try {
			Query.autoCommitOff();
			Query.insert("INSERT INTO `Vehicle_Rent`(`vehicleID`,`vehicle_type`,`model`,`make`,`vehicle_year`,`license_plate`,`colour`) "
					+ "VALUES ('"
					+ vID
					+ "','"
					+ type2
					+ "','"
					+ modelValue
					+ "','"
					+ makeValue
					+ "','"
					+ manufacturingValue
					+ "','"
					+ plateValue
					+ "','"
					+ colorValue + "')");
			Query.insert("INSERT INTO `Vehicle_Category`(`vehicleID`,`rentCategory`) "
					+ "VALUES ('" + vID + "','" + categoryValue + "')");
            Dialogs.showInformationDialog(stage, "Vehicle Added");
			Query.commit();
			Query.autoCommitOn();
			
			
			vehicleID.clear();
			model.clear();
			make.clear();
			manufacturing.clear();
			type.getSelectionModel().clearSelection();
			category.getSelectionModel().clearSelection();
			color.clear();
			plate.clear();
		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
				//ex.printStackTrace();
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", iCV.getMessage());
			
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
			
		}

	}

		/**
		 * Used for linking the Observable list to the Table for List Of Vehicle table
		 * @post The observable list is linked to the table. 
		 */
	@SuppressWarnings("unchecked")
	public void initial(){
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
		listNumberCol
		.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"number"));
		listSellingPriceCol.setCellValueFactory(new PropertyValueFactory<VehicleSearchRow, String>(
				"sellingPrice"));
		resultsTable.setItems(resultList);
        resultsTable.setColumnResizePolicy(resultsTable.CONSTRAINED_RESIZE_POLICY);
		
	}
	/**
	 * Used for linking the observable list to the table for SET PRICE table.
	 * @post The observable list is linked to the table for the SET PRICE TAB.
	 */
	@SuppressWarnings("unchecked")
	public void initializeSetPrice(){
		priceList = FXCollections.observableArrayList();
		priceTypeCol.setCellValueFactory(new PropertyValueFactory<SetPriceRow, String>("type"));
		priceHourCol.setCellValueFactory(new PropertyValueFactory<SetPriceRow, String>("hour"));
		priceDayCol.setCellValueFactory(new PropertyValueFactory<SetPriceRow, String>("day"));
		priceWeekCol.setCellValueFactory(new PropertyValueFactory<SetPriceRow, String>("week"));
	    priceEquipmentCol.setCellValueFactory(new PropertyValueFactory<SetPriceRow, String>("equipmentType"));
		priceTable.setItems(priceList);
	    priceTable.setColumnResizePolicy(priceTable.CONSTRAINED_RESIZE_POLICY);
	    
	    
	}
/**
 * Checks for the value selected for the ComboBox in the Generate Report.
 * @return
 */
	public boolean checkselectReport() {
		try {
			selectReport.getValue().toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
/**
 * Generates the report when the Generate Button is pressed.
 * @post The report list is produced.
 */
	@FXML
	private void handelGenerate() {

		int flag = 0;
		try {

			ResultSet result;
			reportDisplay.setVisible(false);

			if (!checkselectReport()) {
				reportDisplay.setVisible(true);
				return;
			}

			
			String select = selectReport.getValue().toString();
			
			if (select.equals("Daily Rental")) {

				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				String s = fmt.format(date) + " 00:00:00";
				System.out.println("Value of s is " +s);

				flag = 1;
				result = Query
						.select("SELECT C.rentCategory, count(C.vehicleID) , sum(R.isBooked) FROM Rents R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.rentStart >='" + s + "' GROUP BY C.rentCategory");
			} else {
				result = Query 
						.select("SELECT C.rentCategory, count(C.vehicleID) , sum(R.amount) FROM RentPayment R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.returnDate = CAST(CURRENT_TIMESTAMP () AS DATE) GROUP BY C.rentCategory");
			}
			double totalAmount = 0;
			int totalVehicle = 0;
			reportList.clear();

			while (result.next()) {
				ReportRow tuple = new ReportRow();

				tuple.setCategory(result.getString(1));
				tuple.setCount(result.getString(2));
				tuple.setSum(result.getString(3));
				totalAmount = totalAmount + Double.valueOf(result.getString(3));
				totalVehicle = totalVehicle
						+ Integer.parseInt(result.getString(2));

				reportList.add(tuple);

			}
			NumberFormat numFmt = NumberFormat.getCurrencyInstance(Locale.US);
			sum.setText(numFmt.format(totalAmount));
			reportVehicle.setText(Integer.toString(totalVehicle));

			if (flag == 0) {
				sum.setVisible(true);
				reportAmount.setVisible(true);
				reportTable.getColumns().get(2).setVisible(true);
			}
			if (flag == 1) {
				sum.setVisible(false);
				reportAmount.setVisible(false);
				reportTable.getColumns().get(2).setVisible(false);
			}
		} catch (SQLException e) {

			
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}
	}
/**
 * Method used to select vehicle from the Database's Sells Table.
 * @pre The Manager is in the VEHICLE LIST TAB
 * @post List of vehicle's to sell is generated.
 */
	private void displayVehicleSell(){
		try {
			setVisibleAll();
			
			ResultSet result;
			
		    resultList.clear();
		    
		   
		    if(!isNull(listVehicleIDText.getText())){
		    	String vid = listVehicleIDText.getText();
		    	result = Query
						.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
								+ "FROM Vehicle_Sale V, Vehicle_Category C "
								+ "WHERE V.vehicleID = C.vehicleID AND V.vehicleID = '" + vid + "' ");
		    }
		    else if (!isNull(listPlateText.getText())){
		    	String plate = listPlateText.getText();
		    	result = Query
						.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
								+ "FROM Vehicle_Sale V, Vehicle_Category C "
								+ "WHERE V.vehicleID = C.vehicleID AND V.license_plate = '" + plate + "' ");
		  
		    }
		    else {
		    	
		    	String make = listMakeText.getText();
		    	String model = listModelText.getText();
		    	String color  = listColorText.getText();
		    	String type = "%";
		    	if(checkBox(listTypeComboBox)){
		    		type  = listTypeComboBox.getValue().toString();
		    	}
		    	
		    	if(isNull(make)){
		    		make = "%";
		    	}
		    	if(isNull(model)){
		    		model = "%";
		    	}
		    	if(isNull(color)){
		    		color = "%";
		    	}
		    	result = Query
						.select("SELECT vehicleID, license_plate, vehicle_type, make, model, vehicle_year, colour, sellPrice "
								+ "FROM Vehicle_Sale  "
								+ "WHERE make LIKE '" + make + "' AND model LIKE '" + model + "' AND colour LIKE '" + color + "' AND vehicle_type LIKE '" + type + "'");
		    	
		    	
		    }
			while (result.next()) {
				
				VehicleSearchRow tuple = new VehicleSearchRow();

				tuple.setVehicleID(result.getString(1));
				tuple.setLicPlate(result.getString(2));
				tuple.setType(result.getString(3));
			
				tuple.setMake(result.getString(4));
				tuple.setModel(result.getString(5));
				tuple.setYear(result.getString(6).substring(0, 4));
				tuple.setColour(result.getString(7));
			
				tuple.setSellingPrice(result.getString(8));
				resultList.add(tuple);

			}
			resultsTable.getColumns().get(8).setVisible(false);
			resultsTable.getColumns().get(3).setVisible(false);

		} catch (SQLException e) {
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}

		
	}

	/**
	 * Selects Vehicle from the Vehicle_Rent table in Database.
	 * @pre The Generate Button is clicked.
	 * @post The list of vehicle is generated.
	 */
	private void displayVehicleRent(){
		try {
			
			setVisibleAll();
			
			ResultSet result;
			
		    resultList.clear();
		    
		    if(!isNull(listVehicleIDText.getText())){
		    	String vid = listVehicleIDText.getText();
		    	result = Query
						.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
								+ "FROM Vehicle_Rent V, Vehicle_Category C "
								+ "WHERE V.vehicleID = C.vehicleID AND V.vehicleID = '" + vid + "' ");
		    }
		    else if (!isNull(listPlateText.getText())){
		    	String plate = listPlateText.getText();
		    	result = Query
						.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
								+ "FROM Vehicle_Rent V, Vehicle_Category C "
								+ "WHERE V.vehicleID = C.vehicleID AND V.license_plate = '" + plate + "' ");
		  
		    }
		    else {
		    	String make = listMakeText.getText();
		    	String model = listModelText.getText();
		    	String color  = listColorText.getText();
		    	String type = "%";
		    	if(checkBox(listTypeComboBox)){
		    		type  = listTypeComboBox.getValue().toString();
		    	}
		    
		    	if(isNull(make)){
		    		make = "%";
		    	}
		    	if(isNull(model)){
		    		model = "%";
		    	}
		    	if(isNull(color)){
		    		color = "%";
		    	}
		    	result = Query
						.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.rentCategory, V.make, V.model, V.vehicle_year, V.colour "
								+ "FROM Vehicle_Rent V, Vehicle_Category C "
								+ "WHERE V.vehicleID = C.vehicleID AND V.make LIKE '" + make + "' AND V.model LIKE '" + model + "' AND V.colour LIKE '" + color + "' AND V.vehicle_type LIKE '" + type + "'");
		  
		    	
		    }
		   
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
			
            resultsTable.getColumns().get(8).setVisible(false);
            resultsTable.getColumns().get(9).setVisible(false);
            adjustVehicleListColumns();

		} catch (SQLException e) {
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
			}
		
	}
	/**
	 * Set All the Columns in the VEHICLE LIST TAB Table visible.
	 * @post The columns of the Result Table become visible.
	 */
	private void setVisibleAll(){
		resultsTable.getColumns().get(0).setVisible(true);
        resultsTable.getColumns().get(1).setVisible(true);
        resultsTable.getColumns().get(2).setVisible(true);
        resultsTable.getColumns().get(3).setVisible(true);
        resultsTable.getColumns().get(4).setVisible(true);
        resultsTable.getColumns().get(5).setVisible(true);
        resultsTable.getColumns().get(6).setVisible(true);
        resultsTable.getColumns().get(7).setVisible(true);
        resultsTable.getColumns().get(8).setVisible(true);
        resultsTable.getColumns().get(9).setVisible(true);
	}
	
	/**
	 * Selects the Vechile's from the Database for a particular year.
	 * @pre The Get Result button is pressed.
	 * @post The List of Vehicle is generated.
	 * @param year
	 * @param category
	 */
	private void displayRentYear(int year, String category){
		try {
			setVisibleAll();
			ResultSet result;
			
			resultList.clear();
	       
			
			if(category.equals("")){
				
				result = Query
						.select("SELECT C.rentCategory, count(C.vehicleID) FROM Vehicle_Rent R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.vehicle_year <  '" + year +"' GROUP BY C.rentCategory");
			}
			else{
			result = Query
					.select("SELECT C.rentCategory, count(C.vehicleID) FROM Vehicle_Rent R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.vehicle_year <  '" + year +"' and C.rentCategory = '" + category + "'GROUP BY C.rentCategory");
			}
			while (result.next()) {
				VehicleSearchRow tuple = new VehicleSearchRow();

				
				tuple.setCategory(result.getString(1));
				tuple.setNumber(result.getString(2));
				
				resultList.add(tuple);

			}
            resultsTable.getColumns().get(0).setVisible(false);
            resultsTable.getColumns().get(1).setVisible(false);
            resultsTable.getColumns().get(2).setVisible(false);
            resultsTable.getColumns().get(4).setVisible(false);
            resultsTable.getColumns().get(5).setVisible(false);
            resultsTable.getColumns().get(6).setVisible(false);
            resultsTable.getColumns().get(7).setVisible(false);
            resultsTable.getColumns().get(9).setVisible(false);
		} catch (SQLException e) {
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}
	}
	/**
	 * Checks if the ComboBox has a value selected.
	 * @param s
	 * @return
	 */
	public boolean checkBox(ComboBox s) {
		try {
			s.getValue().toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Method to adjust the column's width.
	 * @post The columns width in the resultsTable is adjusted.
	 */
	private void adjustVehicleListColumns(){
		vehicleIDCol.prefWidthProperty().bind(resultsTable.widthProperty().multiply(0.15));
		licPlateCol.prefWidthProperty().bind(resultsTable.widthProperty().multiply(0.15));
		categoryCol.prefWidthProperty().bind(resultsTable.widthProperty().multiply(0.10));
		colourCol.prefWidthProperty().bind(resultsTable.widthProperty().multiply(0.10));
		
	}
	/**
	 * Displays the result according to the value selected when the Search Button is pressed.
	 * @pre The Search Button is pressed.
	 * @post The List of vehicle is generated.
	 */
	@FXML
	private void handleSearchButton() {
		//adjustVehicleListColumns();
        if(listVehicleType.getValue().toString().equalsIgnoreCase("For Rent")){
        	
        	displayVehicleRent();
        }
        if(listVehicleType.getValue().toString().equalsIgnoreCase("For Sale")){
        	
        	displayVehicleSell();
        }
		
		
	}
	/**
	 * Hide or Shows Labels and Text Fields according to the type of vehicle selected.
	 * 
	 */
	@FXML
	private void handleListVehicleType(){
		setDisableAll();
		resultList.clear();
		if(listVehicleType.getValue().toString().equalsIgnoreCase("For Sale")){
			listSearchType.setVisible(false);
			 listSearchTypeLabel.setVisible(false);
			 listVehicleIDLabel.setVisible(true);
			 listVehicleIDText.setVisible(true);
			 listMakeLabel.setVisible(true);
			 listMakeText.setVisible(true);
			 listModelLabel.setVisible(true);
			 listModelText.setVisible(true);
			 listColorLabel.setVisible(true);
			 listColorText.setVisible(true);
			 listPlateLabel.setVisible(true);
			 listPlateText.setVisible(true);
			 listTypeLabel.setVisible(true);
			 listTypeComboBox.setVisible(true);
			 listSearch.setVisible(true);
			 
			 listManuLabel.setVisible(false);
			 listYear.setVisible(false);
			 listCategoryLabel.setVisible(false);
			 listCategory.setVisible(false);
			 listGetResult.setVisible(false);
		}
		if(listVehicleType.getValue().toString().equalsIgnoreCase("For Rent")){
			listSearchType.setVisible(true);
			 listSearchTypeLabel.setVisible(true);
		}
	}
	/**
	 * Hides or shows Text and Labels according to the type of Search Selected.
	 */
	@FXML
	private void handleListSearchType(){
		
		 if(!checkBox(listVehicleType)){
			   
	        	System.out.println("Select a type first");
	        	
	        	return;
	        }
		 if(listSearchType.getValue().toString().equalsIgnoreCase("By Year")){
			 listManuLabel.setVisible(true);
			 listYear.setVisible(true);
			 listCategoryLabel.setVisible(true);
			 listCategory.setVisible(true);
			 listGetResult.setVisible(true);
			 
			 listVehicleIDLabel.setVisible(false);
			 listVehicleIDText.setVisible(false);
			 listMakeLabel.setVisible(false);
			 listMakeText.setVisible(false);
			 listModelLabel.setVisible(false);
			 listModelText.setVisible(false);
			 listColorLabel.setVisible(false);
			 listColorText.setVisible(false);
			 listPlateLabel.setVisible(false);
			 listPlateText.setVisible(false);
			 listTypeLabel.setVisible(false);
			 listTypeComboBox.setVisible(false);
			 listSearch.setVisible(false);
	
		 }
		 if(listSearchType.getValue().toString().equalsIgnoreCase("General")){
			 listVehicleIDLabel.setVisible(true);
			 listVehicleIDText.setVisible(true);
			 listMakeLabel.setVisible(true);
			 listMakeText.setVisible(true);
			 listModelLabel.setVisible(true);
			 listModelText.setVisible(true);
			 listColorLabel.setVisible(true);
			 listColorText.setVisible(true);
			 listPlateLabel.setVisible(true);
			 listPlateText.setVisible(true);
			 listTypeLabel.setVisible(true);
			 listTypeComboBox.setVisible(true);
			 listSearch.setVisible(true);
			 
			 listManuLabel.setVisible(false);
			 listYear.setVisible(false);
			 listCategoryLabel.setVisible(false);
			 listCategory.setVisible(false);
			 listGetResult.setVisible(false);
		 }
		 
	}
	/**
	 * Displays the result when the Get Result Button is pressed.
	 * @pre The GET Result Button is pressed.
	 * @post The Result is generated.
	 */
	@FXML
	private void handleGetResult(){
		 if(!checkBox(listVehicleType)){
	        	
	        	Dialogs.showErrorDialog(stage, "Select A Type");
	        	return;
	        }
	        if(!checkBox(listSearchType)){
	        	
	        	Dialogs.showErrorDialog(stage, "Select An Option");
	        	return;
	        }
	        if(listSearchType.getValue().toString().equalsIgnoreCase("By Year")){
	        	if(isNull(listYear.getText())){
	        		Dialogs.showErrorDialog(stage, "Select A Year");
	        		return;
	        	}
	        	if(!isInt(listYear.getText())){
	        		Dialogs.showErrorDialog(stage, "Value should be an Integer.");
	        		return;
	        	}
	        	int year = Integer.parseInt(listYear.getText());
	        	if(!checkBox(listCategory)){
	        	displayRentYear(year,"");
	        	}
	        	else{
	        		String s = listCategory.getValue().toString();
	        		displayRentYear(year, s);
	        	}
	        }
	}
	/**
	 * Disables All the Buttons from the VEHICLE LIST TAB in manager.
	 * @post The Buttons are disabled.
	 */
	private void setDisableAll(){
		removeButton.setDisable(true);
		moveButton.setDisable(true);
		sellButton.setDisable(true);
		priceSellCom.setVisible(false);
		priceSellCardLabel.setVisible(false);
		priceSellLabel.setVisible(false);
		priceSellText.setVisible(false);
		listPriceLabel.setVisible(false);
		listPriceText.setVisible(false);
		
	}

	/**
	 * Disable and Enables button when a row is clicked in the resultsTable.
	 */
	@FXML
	private void handleClickTable() {
		if (resultsTable.getSelectionModel().getSelectedItem() == null) {
			// System.out.println("No row selected");
			moveButton.setDisable(true);
			removeButton.setDisable(true);
			
			
			return;
		}
		
		
		setDisableAll();
		if(listVehicleType.getValue().toString().equalsIgnoreCase("For Sale")){
			sellButton.setDisable(false);
			priceSellCom.setVisible(true);
			priceSellCardLabel.setVisible(true);
			priceSellLabel.setVisible(true);
			priceSellText.setVisible(true);
		}
		if(listVehicleType.getValue().toString().equalsIgnoreCase("For Rent")){
			removeButton.setDisable(false);
			moveButton.setDisable(false);
		}
	}
	/**
	 * Removes a vehicle from the database.
	 * @pre The Remove button is pressed.
	 * @post The selected vehicle is deleted.
	 */
	@FXML
	private void handleRemoveButton(){
	   String vID =resultsTable.getSelectionModel().getSelectedItem().getVehicleID();	
	   
	  
	   try {
			Query.autoCommitOff();
			Query.delete("DELETE FROM Vehicle_Rent  WHERE vehicleID ='" + vID + "'");
			
			
			Query.commit();
			Query.autoCommitOn();
			 Dialogs.showInformationDialog(stage, "Vehicle Deleted");
			
			handleSearchButton();
		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", iCV.getMessage());
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}

	}
	  /**
	   * Moves a vehicle from vehicle_rent to vehicle_sell.
	   * @pre The Move Button is pressed.
	   * @post The Vehicle is moved from rent to sale.
	   */
	@FXML
	protected void handleMoveButton(){
		listPriceLabel.setVisible(true);
		listPriceText.setVisible(true);
		
		if(isNull(listPriceText.getText())){
		
			Dialogs.showErrorDialog(stage, "Provide a selling price");
		    return;
		}
		if(!isInt(listPriceText.getText())){
			
			Dialogs.showErrorDialog(stage, "Value should be a digit");
			return;
		}
		try {
			String sellPrice = listPriceText.getText();
			
			String vID =resultsTable.getSelectionModel().getSelectedItem().getVehicleID();	
		
			Query.autoCommitOff();
			Query.insert("INSERT INTO `Vehicle_Sale`(`vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate`) "
					+ "    SELECT `vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate` FROM `Vehicle_Rent` WHERE vehicleID ='" + vID + "'");
			Query.delete("DELETE FROM Vehicle_Rent  WHERE vehicleID ='" + vID + "'");
			
			Query.update("UPDATE Vehicle_Sale SET sellPrice = '"+ sellPrice+"'"
					+ "WHERE vehicleID = '"+vID +"'");
		        displayVehicleRent();
			Query.commit();
			Query.autoCommitOn();
			 Dialogs.showInformationDialog(stage, "Vehicle Moved To 'For Sell'");
			listPriceLabel.setVisible(false);
			listPriceText.setVisible(false);
			
			
		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", iCV.getMessage());
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}
		
		
		
	}
	/**
	 * Method used in the set Price Tab to generate price table.
	 * @pre The Price Generate Button is pressed.
	 * @post The List is displayed.
	 */
	@FXML
	private void handlePriceGenerate(){
	    priceWarningLabel.setVisible(false); 
		if(!checkBox(priceSelect)){
	    	 priceWarningLabel.setVisible(true);
	    	
	         return;
	     }
	     try{
	    	 ResultSet result = null;
	    	 priceList.clear();
	   
	     if(priceSelect.getValue().toString().equalsIgnoreCase("For Category")){
	    	 
	    	result = Query.select("SELECT rentCategory , hourlyPrice, dailyPrice , "
	    	
	    			+ " weeklyPrice FROM rentalrates");
	    	
	    	while (result.next()) {
				SetPriceRow tuple = new SetPriceRow();

				
				tuple.setType(result.getString(1));
				tuple.setHour(result.getString(2));
				tuple.setDay(result.getString(3));
				tuple.setWeek(result.getString(4));
				
				priceList.add(tuple);

	    	}
	    	priceTable.getColumns().get(1).setVisible(false);
	    	}
	     if(priceSelect.getValue().toString().equalsIgnoreCase("For Extra Equipment")){
	    	result =  Query.select("SELECT vehicleType , equipmentType, dailyRent , "
	    	
	    			+ " weeklyRent FROM equipment");
	     
	    	while (result.next()) {
				SetPriceRow tuple = new SetPriceRow();

			
				tuple.setType(result.getString(1));
				tuple.setEquipmentType(result.getString(2));
			
				tuple.setDay(result.getString(3));
				tuple.setWeek(result.getString(4));
				
				priceList.add(tuple);

			}
	    	priceTable.getColumns().get(2).setVisible(false);
	    	priceTable.getColumns().get(1).setVisible(true);
	     }
	     
	     }
	     
	     catch(Exception e){
	    	 Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
	     }
	     
	}
	
	
	/**
	 * For Update Button in SET PRICE Tab.
	 * @post The visibility is set as below.
	 */
	@FXML
	private void handelPriceUpdate(){
		priceCategoryLabel.setVisible(true);
		priceCategoryText.setVisible(true);
		priceCategoryText.setEditable(false);
		priceHourLabel.setVisible(true);
		priceHourText.setVisible(true);
		priceDayLabel.setVisible(true);
		priceDayText.setVisible(true);
		priceWeekLabel.setVisible(true);
		priceWeekText.setVisible(true);
		priceChangeButton.setVisible(true);
		priceCategoryText.setText(priceTable.getSelectionModel().getSelectedItem().getType());
		priceHourText.setText(priceTable.getSelectionModel().getSelectedItem().getHour());
		priceDayText.setText(priceTable.getSelectionModel().getSelectedItem().getDay());
		priceWeekText.setText(priceTable.getSelectionModel().getSelectedItem().getWeek());
		priceEquipmentLabel.setVisible(false);
		priceEquipmentText.setVisible(false);
		
		if(priceSelect.getValue().toString().equalsIgnoreCase("For Extra Equipment")){
			priceHourLabel.setVisible(false);
			priceHourText.setVisible(false);
			priceEquipmentLabel.setVisible(true);
			priceEquipmentText.setVisible(true);
			priceEquipmentText.setEditable(false);
			priceEquipmentText.setText(priceTable.getSelectionModel().getSelectedItem().getEquipmentType());
			
		}
		
	}
	/**
	 * For Activating the Update button on click on a row in the Price Table
	 * @post The update button is updated.
	 */
	@FXML
	private void handlePriceTable(){
		if (priceTable.getSelectionModel().getSelectedItem() == null) {
			
			priceUpdate.setDisable(true);
			
			return;
		}
		
		priceUpdate.setDisable(false);
		
	}
	/**
	 * TO change the Price when Change Button is clicked.
	 * @pre The Change Button is clicked.
	 * @post The price is changed.
	 */
	@FXML
	private void handlePriceChangeButton(){
		
		String Category = priceCategoryText.getText();
		String Hour = priceHourText.getText();
		String Day = priceDayText.getText();
		String Week = priceWeekText.getText();
		String EquipmentType = priceEquipmentText.getText();
		if(priceSelect.getValue().toString().equalsIgnoreCase("For Category")){
		if(isNull(Category) || isNull(Hour) || isNull(Day) || isNull(Week)){
			
			Dialogs.showErrorDialog(stage, "Fields should not be empty");
			
			return;
		}
		try{
		ResultSet result;
		Query.update("Update rentalrates SET hourlyPrice = '"+ Hour +"',"
				+ "dailyPrice = '"+ Day +"', weeklyPrice = '"+ Week+"' WHERE "
						+ "rentCategory = '"+ Category +"' ");
		Dialogs.showInformationDialog(stage, "Price Updated");
		handlePriceGenerate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		if(priceSelect.getValue().toString().equalsIgnoreCase("For Extra Equipment")){
			if(isNull(Category) || isNull(Day) || isNull(Week) || isNull(EquipmentType)){
				
				Dialogs.showErrorDialog(stage, "Fields should not be empty");

				return;
			}
			try{
				
			ResultSet result;
			
			Query.update("Update equipment SET "
					+ "dailyRent = '"+ Day +"', weeklyRent = '"+ Week+"' WHERE "
							+ "equipmentType = '"+ EquipmentType +"' AND vehicleType"
									+ "= '"+ Category +"' ");
			
			handlePriceGenerate();
			Dialogs.showInformationDialog(stage, "Price Updated");
			}
			catch(Exception e){
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
			}
		}
	}
	/**
	 * TO check if the number is of type long or not.
	 * @pre The string provided is not null.
	 */
	private boolean isLong(String num){
		try{
			Long.parseLong(num);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * Method used to sell a vehicle when SELL button is pressed.
	 * @pre The Sell button is pressed.
	 * @post The Vehicle is moved to For Sell.
	 */
	
	@FXML
	private void handleSellAction(){
		
		if(!checkBox(priceSellCom) || isNull(priceSellText.getText())){
			Dialogs.showErrorDialog(stage, "Provie Field Values");
			
		}
		
	   try{
		   CreditCardValidation card = new CreditCardValidation();
		   
		  String vID = resultsTable.getSelectionModel().getSelectedItem().getVehicleID();
		  String sellingPrice = resultsTable.getSelectionModel().getSelectedItem().getSellingPrice();
		  
		  String num= priceSellText.getText();
		
		  /*if(!isInt(num)){
			  return;
		  }*/
		  if(!isLong(num)){
			  Dialogs.showErrorDialog(stage, "Card Numbers should have only Digits");
			  
			  return;
		  }
		  long number = Long.parseLong(num);
		  
		  String type = priceSellCom.getValue().toString();
		  
		  if(!card.isValid(number, type)){
			  Dialogs.showErrorDialog(stage, "Invalid Card Number");
			 
			  return;
		  }
		  
		  Query.autoCommitOff();
		  
		  Query.delete("DELETE FROM Vehicle_Sale WHERE vehicleID = '"+ vID +"'"); 
		  Query.insert("INSERT INTO `Sells`(`empID`, `vehicleID`, `price`) VALUES ('"+ managerID +"','"+ vID +"','"+ sellingPrice +"')");
		  
		  
		  Query.commit();
			Query.autoCommitOn();
			displayVehicleSell();
			 Dialogs.showInformationDialog(stage, "Vehicle Sold");
			
	   }
	   
	   catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", iCV.getMessage());
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				Dialogs.showErrorDialog(stage,"Oops!", "Exception!", ex.getMessage());
			}
			Dialogs.showErrorDialog(stage,"Oops!", "Exception!", e.getMessage());
		}
	}
	/**
	 * To disable Label and Text Fields from the SET PRICE Tab.
	 * @post The visibility is set to false to the below fields. 
	 */
	@FXML
	private void priceSelectAction(){
		priceCategoryLabel.setVisible(false);
		priceCategoryText.setVisible(false);
		priceHourLabel.setVisible(false);
		priceHourText.setVisible(false);
		priceDayLabel.setVisible(false);
		priceDayText.setVisible(false);
		priceWeekLabel.setVisible(false);
		priceWeekText.setVisible(false);
		priceChangeButton.setVisible(false);
		priceEquipmentLabel.setVisible(false);
		priceEquipmentText.setVisible(false);
		priceUpdate.setDisable(true);
		priceList.clear();
		
	}
	/**
	 * handler for logout button 
	 * Redirects to initial screen
	 */
	@FXML
	private void handleLogout() {
		LoginController login = new LoginController();
		login.launchLoginController(stage);
	}
	}
