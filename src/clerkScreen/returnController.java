package clerkScreen;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import databaseManagement.Query;
import payment.Payment;
import payment.PaymentDialog;

public class returnController implements Initializable {
    @FXML 
    private static Label overdueStatus;
    
    @FXML
    private static Label pricePaid;
    
    @FXML
    private static Label totalPrice;
    
    @FXML
    private static Label paidStatus;
    
    @FXML
    private static Label pointsAvail;
   
    @FXML
    private Label errorPhone;
    
    @FXML
    private Label errorID;
    
    @FXML
    private Label errorPlate;
    @FXML
    private TextField rentID;
    
    @FXML
    private TextField licensePlate;
    
    @FXML
    private TextField custPhone;
    
    @FXML
    private CheckBox applyPoints;
    
    @FXML
    private RadioButton searchRent;
    
    @FXML
    private RadioButton searchPlate;
    @FXML
    private RadioButton searchNo;
    
    @FXML
    private TableView<RentRow> resultsTable;
    
    @FXML
    private Button completeRental;
    
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
    private TextArea equipList;
    
    @FXML
    private CheckBox roadStar;
    private ObservableList<RentRow> resultList;
    
    @FXML
    private TextField odometer;
    
    @FXML
    private ComboBox<String> tankStatus;
    
    @FXML 
    private Label errorMissingSearch;
    
    @FXML
    private Label errorOdometer;
    
    @FXML
    private Label errorTank;
    
    @FXML
    private Button startPayment;
    
    private double priceToPay;
    
    private double discount;
    
    private double insuranceDiscount;
    
    private Date returnDate;
    
    private Payment pay;
    
    private Stage owner;
    
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
    

    @FXML
    /**
     * Handle the search button that is pressed to look for rents.
     */
    private void findRent() 
    {
        if (checkSearchNull()) { return; }
        String query = buildSearchQuery();
        resultList.clear();
        resultsTable.setItems(resultList);
        try {
            ResultSet results = Query.select(query); 
            //check if empty
            if (!results.first())
            {
                Dialogs.showErrorDialog(owner, "No matching rents found!", "Error!");
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
       completeRental.setVisible(false);
       applyPoints.setVisible(false);
       
       if (searchRent.isSelected())
       {
           String rentalID = rentID.getText();
           query = "SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd " +
                   "FROM Rents R, Vehicle_Rent V " + 
                   "WHERE R.vehicleID = V.vehicleID AND R.rentID ='" + rentalID + "' AND R.isBooked = 0"; 
       }
       else if (searchPlate.isSelected())
       {
           String plate = licensePlate.getText();
           query = "SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd " +
                   "FROM Rents R, Vehicle_Rent V " + 
                   "WHERE R.vehicleID = V.VehicleID AND V.license_plate = " + "'" + plate + "' AND R.isBooked = 0"; 
       }
       else if (searchNo.isSelected())
       {
           String phone = custPhone.getText();
           query = "SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd " +
                   "FROM Rents R, Vehicle_Rent V " + 
                   "WHERE R.vehicleID = V.vehicleID AND R.isBooked = 0 AND R.custID IN" +
                   "( SELECT custID FROM customer WHERE phoneNo='" + phone + "')";
       }
       return query;
    }
    
    private boolean checkSearchNull()
    {
        boolean isNull = false;
        errorMissingSearch.setVisible(false);
        
        if (!searchNo.isSelected() && !searchPlate.isSelected() && !searchRent.isSelected())
        {
            errorMissingSearch.setVisible(true);
            isNull = true;
        }
        
        if (searchRent.isSelected() && rentID.getText().isEmpty())
        {
            errorMissingSearch.setVisible(true);
            isNull = true;
        }
        
        if (searchNo.isSelected() && custPhone.getText().isEmpty())
        {
            errorMissingSearch.setVisible(true);
            isNull = true;
        }
        
        if (searchPlate.isSelected() && licensePlate.getText().isEmpty())
        {
            errorMissingSearch.setVisible(true);
            isNull = true;
        }
        
        return isNull;
    }
    
    @FXML
    /**
     * Check the format of the string of the appropriate text field
     * IDs are numbers only.
     */
    private void checkIDFormat()
    {
        
        String pattern ="\\d+";
        if (rentID.getText().matches(pattern))
        {
            errorID.setVisible(false);
            return;
        }
        errorID.setVisible(true);
    }
    
    @FXML
    /**
     * Check if the license plate is of the format
     * 3 letters, followed by 3 numbers (no spaces)
     */
    private void checkPlateFormat()
    {
        String pattern = "\\d{3}[A-Z]{3}";
        if (licensePlate.getText().matches(pattern))
        {
            errorPlate.setVisible(false);
            return;
        }
        errorPlate.setVisible(true);
    }
    
    @FXML
    /**
     * Checks if the phone numbers are in the format:
     * XXX-XXX-XXXX
     */
    private void checkPhoneFormat()
    {
        String pattern = "\\d{3}-\\d{3}-\\d{4}";
        if (custPhone.getText().matches(pattern))
        {
            errorPhone.setVisible(false);
            return;
        }
        errorPhone.setVisible(true);
    }
    
    @FXML
    /**
     * Handle the selection of a row, calculates the total price of the rental so far.
     */
    private void clickedRow()
    {
        equipList.clear();
        startPayment.setVisible(false);
        RentRow selectedRow = resultsTable.getSelectionModel().getSelectedItem();
        owner = (Stage)resultsTable.getScene().getWindow();
        if (selectedRow == null) { return; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        pay = new Payment(this.findVehicleID(selectedRow.getRentID()));
        
        getPoints(); //get points, if any.
        if (!pointsAvail.getText().isEmpty())
        {
            applyPoints.setVisible(true);
        }
        try 
        {
           
        java.util.Date tempStartDate = formatter.parse(selectedRow.getRentStart());
        java.util.Date tempEndDate = formatter.parse(selectedRow.getRentEnd());
        
        Date startDate = new Date(tempStartDate.getTime());
        Date endDate = new Date(tempEndDate.getTime());
        returnDate = new Date(System.currentTimeMillis()); //not local because we need to use returnDate in the completeReturn() method.
        
        //check for equipment rentals.
        if (hasEquipment(selectedRow.getRentID()))
        {
            pay.getEquipmentRates(selectedRow.getRentID());
        }         
        //check if overdue
        if (returnDate.after(endDate))
        {
            overdueStatus.setTextFill(Paint.valueOf("RED"));
            overdueStatus.setText("OVERDUE");
            String rentalPrice = pay.calculateTotalPrice(startDate, endDate, returnDate);
            priceToPay = pay.getPrice();
            totalPrice.setText(rentalPrice);
        }
        else 
        {
            overdueStatus.setTextFill(Paint.valueOf("GREEN"));
            overdueStatus.setText("ON TIME");
            String rentalPrice = pay.calculateTotalPrice(startDate, endDate, returnDate);
            priceToPay = pay.getPrice();
            totalPrice.setText(rentalPrice);
        }
        startPayment.setVisible(true);
        
    } catch (ParseException e) {
        e.printStackTrace();
    }
    }
    
    /**
     * Make the payment dialog and process the payment.
     */
    @FXML 
    private void makePayment() 
    {
        
        PaymentDialog dialog = new PaymentDialog(rentID.getText());
        DialogResponse response = Dialogs.showCustomDialog(owner, dialog.getWindow(), "Please enter payment amount", "Payment"
                , DialogOptions.OK_CANCEL, dialog.paymentCallback());
        
        if (response == DialogResponse.OK)
        {
            double amount = dialog.getPaidAmount();
            if (priceToPay - amount < 0.01)
            {
                pricePaid.setText(totalPrice.getText());
                totalPrice.setText("$0.00");
                completeRental.setVisible(true);
            }
            if (applyPoints.isSelected())
            {
                pointsAvail.setText("0");
            }
        }
    }
    
    /**
     * Find the vehicle ID based on the rentID
     * @pre rentID > 0
     * @return
     */
    private String findVehicleID(String rentID)
    {
        String vehicleID="";
        try 
        {
            ResultSet result = Query.select("SELECT vehicleID FROM Rents WHERE rentID='" + rentID + "'");
            result.next();
            vehicleID = result.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleID;
    }
    
    /**
     * Find the customer ID  based on the rentID
     * @pre rentID > 0
     * @return
     */
    private String findCustomerID(String rentID)
    {
        String custID="";
        try 
        {
            ResultSet result = Query.select("SELECT custID FROM Rents WHERE rentID='" + rentID + "'");
            result.next();
            custID = result.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return custID;
    }
    
    /**
     * Update the available membership points label and price with points.
     */
    private void getPoints()
    {
        String rentID = resultsTable.getSelectionModel().getSelectedItem().getRentID();
        String custID = this.findCustomerID(rentID);
        try {
            ResultSet result = Query.select("SELECT points FROM customer_points WHERE custID='" + custID + "'");
            
            if (result.next())
            {
                pointsAvail.setText(result.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Check if the rental has extra equipment with it.
     * @param rentID
     * @return
     */
    private boolean hasEquipment(String rentID)
    {
        boolean hasEquipment = false;
        try {
            ResultSet result = Query.select("SELECT E.equipmentType "
                    + "FROM equipment E, EquipmentUsed U WHERE E.equpimentID = U.equipmentID AND rentID='" + rentID + "'");
            while (result.next())
            {
                hasEquipment = true;
                equipList.appendText(result.getString(1) + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasEquipment;
    }
    
    @FXML
    /**
     * Calculate price of the discount.
     * @pre pay != null
     */
    private void applyDiscount()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
        int availablePoints = Integer.parseInt(pointsAvail.getText());
        if (availablePoints < 1000)
        {
            Dialogs.showInformationDialog(owner, "Sorry, not enough points for a discount!", "Sorry!");
            applyPoints.setSelected(false);
            return;
        }
        discount = pay.calculateDiscount(availablePoints);
        if (applyPoints.isSelected())
        {
            priceToPay -= discount;
            totalPrice.setText(fmt.format(priceToPay));
        }
        else
        {
            priceToPay += discount;
            totalPrice.setText(fmt.format(priceToPay));
        }
    }
    
    @FXML
    private void completeReturn()
    {
        if (checkNull()) { return; }
        //get all required data to insert.
        Stage owner = (Stage)resultsTable.getScene().getWindow();
        String rentID = resultsTable.getSelectionModel().getSelectedItem().getRentID();
        String dateFrom = resultsTable.getSelectionModel().getSelectedItem().getRentStart();
        String dateTo = resultsTable.getSelectionModel().getSelectedItem().getRentEnd();
        String dateReturn = returnDate.toString();
        String vehicleID = this.findVehicleID(rentID);
        String custID = this.findCustomerID(rentID);
        String gasStatus = tankStatus.getValue().toString();
        try {
            Query.delete("DELETE FROM Rents WHERE rentID='" + rentID + "'"); //delete from rent
            Query.delete("DELETE FROM rentCardInfo WHERE rentID='" + rentID + "'"); //delete from cardInfo
            Query.insert(String.format("INSERT INTO RentPayment VALUES ('%s', '%s', '%s', '%s', '%s', '%.2f', '%s')"
                    , custID, vehicleID, dateTo, dateFrom, dateReturn, priceToPay, rentID)); //insert into rent payment
            Query.insert(String.format("INSERT INTO Vehicle_Rent (odometer, full_tank) WHERE vehicleID='%s' "
                    + "VALUES ('%s', '%s')", odometer.getText(), gasStatus));
            Dialogs.showInformationDialog(owner, "Return complete!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Check the form for null values
     */
    private boolean checkNull()
    {
        boolean hasNull = false;
        errorMissingSearch.setVisible(false);
        errorOdometer.setVisible(false);
        errorTank.setVisible(false);
        if (odometer.getText().isEmpty())
        {
            errorOdometer.setVisible(true);
            hasNull = true;
        }
        if (tankStatus.getValue() == null)
        {
            errorTank.setVisible(true);
            hasNull = true;
        }
        return hasNull;
    }
    
    @FXML
    private void calculateInsuranceDiscount()
    {
        RentRow selectedRow = resultsTable.getSelectionModel().getSelectedItem();
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
        if (selectedRow == null) { return; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            java.util.Date tempStartDate = formatter.parse(selectedRow.getRentStart());
            java.util.Date tempEndDate = formatter.parse(selectedRow.getRentEnd());
            
            Date startDate = new Date(tempStartDate.getTime());
            Date endDate = new Date(tempEndDate.getTime());
            
            if(roadStar.isSelected())
            {
                insuranceDiscount = pay.calculateInsuranceDiscount(startDate, endDate, returnDate);
                
                priceToPay -= insuranceDiscount;
                totalPrice.setText(fmt.format(priceToPay));
            }
            else 
            {
                priceToPay += insuranceDiscount;
                totalPrice.setText(fmt.format(priceToPay));
            }
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
