package clerkScreen;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import databaseManagement.Query;
import rentScreen.RentController;
import rentScreen.RentalInfo;

/**
 * Controller class for the reservations screen.
 * 
 * @author Aaron Cheng
 *
 */
public class reservationsController implements Initializable{
    
    @FXML
    private TableColumn<RentRow, String> rentIDCol;
    @FXML
    private TableColumn<RentRow, String> makeCol;
    
    @FXML
    private TableColumn<RentRow, String> modelCol;
    
    @FXML
    private TableColumn<RentRow, String> plateCol;
    
    @FXML
    private TableColumn<RentRow, String>startCol;
    
    @FXML
    private TableColumn<RentRow, String> endCol;

    @FXML
    private TableView<RentRow> resultsTable;
    
    private ObservableList<RentRow> resultList;
    
    @FXML
    private TextField rentID;
    
    @FXML
    private TextField phoneNo;
    
    @FXML
    private RadioButton searchNo;
    
    @FXML
    private RadioButton searchRent;
    
    @FXML
    private Label completeCriteria;
    
    @FXML
    private Label errorID;
    
    @FXML
    private Label errorPhone;
    
    private Stage owner;
    
    private VehicleSearchRow row;
    
    private RentRow tableRow;
    
    private Stage stage;
    
    @FXML
    private Button completeRent;
    
    @FXML
    /**
     * Method fired when the "Search" button is pressed.
     * @pre phoneNo != null
     * @pre rentID != null
     * @post resultList.newlength == resultList.prevLength + 1;
     */
    private void findRent()
    {
        //find the associated rent
        if (checkSearchNull()) { return;}
        owner = (Stage) resultsTable.getScene().getWindow();
        String query = buildSearchQuery();
        resultList.clear();
        resultsTable.setItems(resultList);
        try {
            ResultSet results = Query.select(query); 
            //check if empty
            if (!results.first())
            {
                Dialogs.showErrorDialog(owner, "No matching reservations found!", "Error!");
                return;
            }
            results.beforeFirst();
            //fill in the table
            while (results.next())
            {
                RentRow tableRow = new RentRow(results.getString(1), results.getString(2), results.getString(3),
                        results.getString(4), results.getString(5), results.getString(6));
                resultList.add(tableRow);
            }
            
            resultsTable.setItems(resultList);
                 
        } catch (SQLException e) {
          e.printStackTrace();
        }
        
    }
    
    @FXML
    /**
     * Method to handle the table clicked event.
     * 
     * @pre tableRow != null
     * @post completeRent.visible = true;
     */
    private void clickedRow()
    {
        row = new VehicleSearchRow();
        tableRow = resultsTable.getSelectionModel().getSelectedItem();
        if (tableRow == null) { return; }
        String rentID = tableRow.getRentID();
        System.out.println(tableRow.getRentID());
        
        row.setLicPlate(tableRow.getLicensePlate());
        row.setMake(tableRow.getVehicleMake());
        row.setModel(tableRow.getVehicleModel());
        String query = String.format("SELECT V.vehicle_year, V.colour, V.vehicleID, V.vehicle_type, "
                + "VC.rentCategory FROM Vehicle_Rent V, Vehicle_Category VC ,Rents R WHERE V.vehicleID = VC.vehicleID "
                + "AND R.vehicleID = V.vehicleID AND R.rentID='%s'", rentID);
        try {
            ResultSet vehicleData = Query.select(query);
            if (vehicleData.next())
            {
                row.setYear(vehicleData.getString(1));
                row.setColour(vehicleData.getString(2));
                row.setVehicleID(vehicleData.getString(3));
                row.setType(vehicleData.getString(4));
                row.setCategory(vehicleData.getString(5));
                completeRent.setDisable(false);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Check if the radio buttons are checked or not.
     * @return
     */
    private boolean checkSearchNull()
    {
        boolean isNull = false;
        completeCriteria.setVisible(false);
        
        if (!searchNo.isSelected() && !searchRent.isSelected())
        {
            completeCriteria.setVisible(true);
            isNull = true;
        }
        
        if (searchRent.isSelected() && rentID.getText().isEmpty())
        {
            completeCriteria.setVisible(true);
            isNull = true;
        }
        
        if (searchNo.isSelected() && phoneNo.getText().isEmpty())
        {
            completeCriteria.setVisible(true);
            isNull = true;
        }
        return isNull;
    }
    
    
    @FXML
    /**
     * 
     */
    private void completeRent()
    {
    	RentalInfo info = RentalInfo.getCurrentInstance();
    	RentalInfo.setBooking(true);
    	RentalInfo.setRentId(tableRow.getRentID());
        stage = (Stage)resultsTable.getScene().getWindow();
        RentController controller = new RentController(row, tableRow.getRentStart(), tableRow.getRentEnd());
        controller.launchRentController(stage);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        resultList = FXCollections.observableArrayList();
        rentIDCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentID"));
        makeCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("vehicleMake"));
        modelCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("vehicleModel"));
        plateCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("licensePlate"));
        startCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentEnd"));
        
    }
    
    /**
     * Builds the query needed to search for rentals
     * @pre phone.format = xxx-xxx-xxxx
     * @pre rentalID > 0
     * @pre plate.format = xxx-xxx
     * @return the query required
     */
    private String buildSearchQuery() 
    {
       String query = "";
       completeCriteria.setVisible(false);

       if (searchRent.isSelected())
       {
           String rentalID = rentID.getText();
           query = "SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd " +
                   "FROM Rents R, Vehicle_Rent V " + 
                   "WHERE R.vehicleID = V.vehicleID AND R.rentID ='" + rentalID + "' AND R.isBooked = 1"; 
       }
       else if (searchNo.isSelected())
       {
           String phone = phoneNo.getText();
           query = "SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd " +
                   "FROM Rents R, Vehicle_Rent V " + 
                   "WHERE R.vehicleID = V.vehicleID AND R.isBooked = 1 AND R.custID IN" +
                   "( SELECT custID FROM customer WHERE phoneNo='" + phone + "')";
       }
       return query;
    }
    
    @FXML
    /**
     * Checks if the phone numbers are in the format:
     * XXX-XXX-XXXX
     */
    private void checkPhoneFormat()
    {
        String pattern = "\\d{3}-\\d{3}-\\d{4}";
        if (phoneNo.getText().matches(pattern))
        {
            errorPhone.setVisible(false);
            return;
        }
        errorPhone.setVisible(true);
    }
    
    /**
     * Checks if the rentIDs are in the format:
     * [num]+
     */
    @FXML
    private void checkIDFormat()
    {
        String pattern = "\\d+";
        if (rentID.getText().matches(pattern))
        {
            errorID.setVisible(false);
            return;
        }
        errorID.setVisible(true);
    }
}
