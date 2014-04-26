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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dataHold.ReportRow;
import dataHold.VehicleSearchRow;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dataHold.ReportRow;
import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;
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
    private String managerID;
	
	@FXML
	private Text welcomeText;

	@FXML
	private Button Generate;
	/**
	 * Below is the list of attributes used for the ADD VEHICLE TAB for the Manager.
	 */
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
// Declaring Table and Columns
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
    /**
     * Declaring below variables for Vehicle List tab of Manager
     */
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

		System.out.println("Hello");

		type.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener() {
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue ov, Object t, Object t1) {
						System.out.println("Here" + t1.toString());
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
		if (s.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	/**
	 * To set the visibility of the Labels in the ADD VEHICLE TAB.
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
		if (isNull(vID)) {
			idLabel.setVisible(true);
			count++;
			flag++;

		}

		if (isNull(plateValue)) {
			plateLabel.setVisible(true);
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
	 * Method used to add the Vehicle to the Database.
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
		System.out.println(type2);
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

			Query.commit();
			Query.autoCommitOn();
			System.out.println("Inserted");
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
				ex.printStackTrace();
			}
			System.out.println("Value Already Present");
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("OOPS");
		}

	}

		/**
		 * Used for linking the Observable list to the Table for List Of Vehicle table 
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
			System.out.println("Value selected is :" + select);
			if (select.equals("Daily Rental")) {

				flag = 1;
				result = Query
						.select("SELECT C.rentCategory, count(C.vehicleID) , sum(R.amount) FROM RentPayment R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.dateFrom = CAST(CURRENT_TIMESTAMP () AS DATE)GROUP BY C.rentCategory");
			} else {
				result = Query 
						.select("SELECT C.rentCategory, count(C.vehicleID) , sum(R.amount) FROM RentPayment R, Vehicle_Category C WHERE R.vehicleID = C.vehicleID and R.returnDate = CAST(CURRENT_TIMESTAMP () AS DATE) GROUP BY C.rentCategory");
			}
			double totalAmount = 0;
			double totalVehicle = 0;
			reportList.clear();

			while (result.next()) {
				ReportRow tuple = new ReportRow();

				tuple.setCategory(result.getString(1));
				tuple.setCount(result.getString(2));
				tuple.setSum(result.getString(3));
				totalAmount = totalAmount + Double.valueOf(result.getString(3));
				totalVehicle = totalVehicle
						+ Double.valueOf(result.getString(2));

				reportList.add(tuple);

			}
			System.out.println("Total value is :" + totalAmount);

			sum.setText(Double.toString(totalAmount));
			reportVehicle.setText(Double.toString(totalVehicle));

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

			
			e.printStackTrace();
		}
	}
/**
 * Method used to select vehicle from the Database's Sells Table.
 */
	private void displayVehicleSell(){
		try {
			setVisibleAll();
			//setVisibleNone();
			ResultSet result;
			///resultList.removeAll(resultList);
			//initial();
		    ///resultsTable.getItems().removeAll(resultList);
			//initial();
		    resultList.clear();
		    //resultsTable.setVisible(false);
		   
		    //n =3;
		   
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
		    	System.out.println("Inside Sale");
		    	String make = listMakeText.getText();
		    	String model = listModelText.getText();
		    	String color  = listColorText.getText();
		    	String type = "%";
		    	if(checkBox(listTypeComboBox)){
		    		type  = listTypeComboBox.getValue().toString();
		    	}
		    	//String type = listTypeComboBox.getValue().toString();
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

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

		
	}
	/**
	 * Selects Vehicle from the Vehicle_Rent table in Database.
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

		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		
	}
	/**
	 * Set All the Columns in the VEHICLE LIST TAB Table visible.
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
	 * @param year
	 * @param category
	 */
	private void displayRentYear(int year, String category){
		try {
			setVisibleAll();
			ResultSet result;
			System.out.println("Category value is " + category);
			resultList.clear();
	       
			
			if(category.equals("")){
				System.out.println("Here");
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
			System.out.println("Exception");
			e.printStackTrace();
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
	 * Displays the resuld according to the value selected when the Search Button is pressed.
	 */
	@FXML
	private void handleSearchButton() {
        if(listVehicleType.getValue().toString().equalsIgnoreCase("For Rent")){
        	
        	displayVehicleRent();
        }
        if(listVehicleType.getValue().toString().equalsIgnoreCase("For Sale")){
        	
        	displayVehicleSell();
        }
		
		
	}
	/**
	 * Hide or Shows Labels and Text Fields according to the type of vehicle selected.
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
	 */
	@FXML
	private void handleGetResult(){
		 if(!checkBox(listVehicleType)){
	        	System.out.println("Select a type");
	        	return;
	        }
	        if(!checkBox(listSearchType)){
	        	System.out.println("Select an option");
	        	return;
	        }
	        if(listSearchType.getValue().toString().equalsIgnoreCase("By Year")){
	        	if(isNull(listYear.getText())){
	        		System.out.println("Select a year");
	        		return;
	        	}
	        	if(!isInt(listYear.getText())){
	        		System.out.println("Value should be an integer");
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
	 * Disables All the Buttons from the SET PRICE TAB in manager.
	 */
	private void setDisableAll(){
		removeButton.setDisable(true);
		moveButton.setDisable(true);
		sellButton.setDisable(true);
		priceSellCom.setVisible(false);
		priceSellCardLabel.setVisible(false);
		priceSellLabel.setVisible(false);
		priceSellText.setVisible(false);
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
	 */
	@FXML
	private void handleRemoveButton(){
	   String vID =resultsTable.getSelectionModel().getSelectedItem().getVehicleID();	
	   System.out.println("Vehicle id selected is : " +vID);
	  
	   try {
			Query.autoCommitOff();
			Query.delete("DELETE FROM Vehicle_Rent  WHERE vehicleID ='" + vID + "'");
			
			System.out.println("Hello");
			
			Query.commit();
			Query.autoCommitOn();
			System.out.println("Deleted");
			handleSearchButton();
		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			System.out.println("Value Already Present");
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("OOPS");
		}

	}
	  /**
	   * Moves a vehicle from vehicle_rent to vehicle_sell.
	   */
	@FXML
	protected void handleMoveButton(){
		listPriceLabel.setVisible(true);
		listPriceText.setVisible(true);
		
		if(isNull(listPriceText.getText())){
		
			System.out.println("Provide a value");
		    return;
		}
		if(!isInt(listPriceText.getText())){
			System.out.println("Value to be a number");
			return;
		}
		try {
			String sellPrice = listPriceText.getText();
			
			String vID =resultsTable.getSelectionModel().getSelectedItem().getVehicleID();	
			System.out.println("Vehicle id selected is : " +vID);
			Query.autoCommitOff();
			Query.insert("INSERT INTO `Vehicle_Sale`(`vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate`) "
					+ "    SELECT `vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate` FROM `Vehicle_Rent` WHERE vehicleID ='" + vID + "'");
			Query.delete("DELETE FROM Vehicle_Rent  WHERE vehicleID ='" + vID + "'");
			
			Query.update("UPDATE Vehicle_Sale SET sellPrice = '"+ sellPrice+"'"
					+ "WHERE vehicleID = '"+vID +"'");
		        displayVehicleRent();
			Query.commit();
			Query.autoCommitOn();
			listPriceLabel.setVisible(false);
			listPriceText.setVisible(false);
			
			
		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			System.out.println("Value Already Present");
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("OOPS");
		}
		
		
		
	}
	/**
	 * Method used in the set Price Tab to generate price table.
	 */
	@FXML
	private void handlePriceGenerate(){
	     if(!checkBox(priceSelect)){
	    	 System.out.println("Price Select");
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
	      e.printStackTrace();	 
	     }
	     
	}
	
	
	/**
	 * For Update Button in SET PRICE Tab
	 */
	@FXML
	private void handelPriceUpdate(){
		priceCategoryLabel.setVisible(true);
		priceCategoryText.setVisible(true);
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
			priceEquipmentText.setText(priceTable.getSelectionModel().getSelectedItem().getEquipmentType());
			
		}
		
	}
	/**
	 * For Activating the Update button on click on a row in the Price Table
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
			System.out.println("Fields Should not be empty");
			
			return;
		}
		try{
		ResultSet result;
		Query.update("Update rentalrates SET hourlyPrice = '"+ Hour +"',"
				+ "dailyPrice = '"+ Day +"', weeklyPrice = '"+ Week+"' WHERE "
						+ "rentCategory = '"+ Category +"' ");
		handlePriceGenerate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		if(priceSelect.getValue().toString().equalsIgnoreCase("For Extra Equipment")){
			if(isNull(Category) || isNull(Day) || isNull(Week) || isNull(EquipmentType)){
				System.out.println("Fields Should not be empty");
				System.out.println("Here");
				return;
			}
			try{
				
			ResultSet result;
			
			Query.update("Update equipment SET "
					+ "dailyRent = '"+ Day +"', weeklyRent = '"+ Week+"' WHERE "
							+ "equipmentType = '"+ EquipmentType +"' AND vehicleType"
									+ "= '"+ Category +"' ");
			System.out.println("There");
			handlePriceGenerate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * Method used to sell a vehicle when SELL button is pressed.
	 */
	@FXML
	private void handleSellAction(){
		
		if(!checkBox(priceSellCom) || isNull(priceSellText.getText())){
			System.out.println("Should Enter Field values");
		}
		
	   try{
		   CreditCardValidation card = new CreditCardValidation();
		   
		  String vID = resultsTable.getSelectionModel().getSelectedItem().getVehicleID();
		  String sellingPrice = resultsTable.getSelectionModel().getSelectedItem().getSellingPrice();
		  System.out.println("Manager id is "+ managerID);
		  String num= priceSellText.getText();
		  System.out.println("Num is " + num);
		  /*if(!isInt(num)){
			  return;
		  }*/
		  long number = Long.parseLong(num);
		  System.out.println("Number is " + number);
		  String type = priceSellCom.getValue().toString();
		  System.out.println("Type is +" + type);
		  if(!card.isValid(number, type)){
			  System.out.println("Invalid Card");
			  return;
		  }
		  if(card.isValid(number, type)){
			  System.out.println("Valid");
			  return;
		  }
		  Query.autoCommitOff();
		  System.out.println("Here");
		  Query.insert("INSERT INTO `Sells`(`empID`, `vehicleID`, `price`) VALUES ('"+ managerID +"','"+ vID +"','"+ sellingPrice +"')");
		  System.out.println("There");
		 // Query.delete("DELETE from Vehicle_Sale WHERE vehicleID = '"+ vID +"'");  
		  Query.commit();
			Query.autoCommitOn();
	   }
	   
	   catch(Exception e){
		  /* try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}*/
		   e.printStackTrace();
	   }
	}
	/**
	 * To disable Label and Text Fields from the SET PRICE Tab.
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
		
	}
	}
