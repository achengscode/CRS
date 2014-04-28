package clerkScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import login.LoginController;
import databaseManagement.Query;

public class OverdueController implements Initializable {
    
    private ObservableList<RentRow> resultList;
    
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
    private Label errorCategory;
    
    @FXML
    private ComboBox<String> categorySelect;
    
    @FXML
    private TableView<RentRow> resultsTable;
    
    @FXML
    private void clickedRow()
    {
        //what happens when you click on the table row
    }
    
    @FXML
    /**
     * Generate the list of all overdue vehicles.
     * @pre categorySeledt.getValue() != null
     */
    private void generateReport()
    {
        
        if (categorySelect.getValue() == null)
        {
            errorCategory.setVisible(true);
            return;
        }
        errorCategory.setVisible(false);
        String category = categorySelect.getValue();
        Stage owner = (Stage)resultsTable.getScene().getWindow();
        resultsTable.setItems(resultList);
        String query = String.format("SELECT R.rentID, V.make, V.model, V.license_plate, R.rentStart, R.rentEnd "
                + "FROM Rents R, Vehicle_Rent V,  Vehicle_Category VC "
                + "WHERE R.vehicleID = V.vehicleID AND V.vehicleID = VC.vehicleID AND VC.rentCategory='%s'", category);
        try {
            ResultSet results = Query.select(query);
            if (!results.first())
            {
                Dialogs.showErrorDialog(owner, "No matching vehicles found!", "Error!");
                return;
            }
            results.beforeFirst();
            //fill in the table
            while (results.next())
            {
                RentRow tableRow = new RentRow(results.getString(1), results.getString(2), results.getString(3),
                        results.getString(4), results.getString(5), results.getString(6));
                resultList.add(tableRow);
                resultsTable.setItems(resultList);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
        resultList = FXCollections.observableArrayList();
        rentIDCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentID"));
        makeCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("vehicleMake"));
        modelCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("vehicleModel"));
        plateCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("licensePlate"));
        startCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentEnd"));
        
    }
    
	/**
	 * Logout button handler
	 */
	@FXML
	private void handleCancelButton() {
		Stage stage = (Stage) resultsTable.getScene().getWindow();
		LoginController login = new LoginController();
		login.launchLoginController(stage);
			
	}

}
