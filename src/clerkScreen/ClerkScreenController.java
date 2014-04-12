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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.ResultSet;




/**
 * Controller class to ...
 * 
 * @author Ignacio Perez
 *
 */

public class ClerkScreenController implements Initializable{

	
	// Combo box declaration
	@FXML
    private ComboBox type;
    
    @FXML
    private ComboBox category;
	
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
	
	private Parent parent;
	private Scene scene;
	@SuppressWarnings("unused")
	private Stage stage;
	
	ObservableList<VehicleSearchRow> resultList;
	
    ObservableList<String> typeCategory =
            FXCollections.observableArrayList(
            "Car",
            "Truck",
            "All"
            );
	ObservableList<String> anyCategory =
            FXCollections.observableArrayList(
            "All"
            );
	
	final ObservableList<String> carCategory =
            FXCollections.observableArrayList(
            		"Economy","Compact","Mid-size","Standard","Full-size","Premium","Luxury","SUV","Van"
            );
	final ObservableList<String> truckCategory =
	            FXCollections.observableArrayList(
	            		"24-foot","15-foot","12-foot","box Truck","Cargo Van");
	    
	
	
	public ClerkScreenController() {

	       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClerkScreenController.fxml"));
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
	        stage.setTitle("Clerk Form");
	        stage.setScene(scene);
	        stage.setResizable(true);
	        stage.hide();
	        stage.show();
	    }
	//main method needed to be implemented by the controller class.
	 @SuppressWarnings("unchecked")
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		 resultList = FXCollections.observableArrayList();
		 vehicleIDCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("vehicleID")
				);
		 licPlateCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("licPlate")
				);
		 typeCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("type")
				);
		 categoryCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("category")
				);
		 makeCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("make")
				);
		 modelCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("model")
				);
		 yearCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("year")
				);
		 colourCol.setCellValueFactory(
				    new PropertyValueFactory<VehicleSearchRow,String>("colour")
				);
		 
		 resultsTable.setItems(resultList); 
	/*	 resultsTable.addListener(new ListChangeListener<VehicleSearchRow>() {
             @Override
             public void onChanged(ListChangeListener.Change<? extends VehicleSearchRow> c) {
                 ponerPersonaSeleccionada();
             }
         };*/
		 
		 
		type.setItems(typeCategory);
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
						case "2":
							category.setItems(anyCategory);
							break;

						}
					}
				});
	}

	/**
	 * Event handler for the searchCustomer button.
	 *  
	 */
	@FXML
	private void handleSearchButton()
	{
		//database call to search for customer info in database
		// so far only search by phone number is implemented
		try {
			
			
			String phone = "111";
			ResultSet result;
			
			// SQL Query must be adjusted depending on the search parameter given (phone or lastname)
			if (true)
			{
				result = Query.select("SELECT V.vehicleID, V.license_plate, V.vehicle_type, C.vehicle_category, V.make, V.model, V.vehicle_year, V.colour FROM Vehicle_Rent V, Vehicle_Category C WHERE V.vehicleID = C.vehicleID");
			}
			
			
			while(result.next())
		    {
				VehicleSearchRow tuple = new VehicleSearchRow();

		    	tuple.setVehicleID(result.getString(1));
		    	tuple.setLicPlate(result.getString(2));
		    	tuple.setType(result.getString(3));
		    	tuple.setCategory(result.getString(4));
		    	tuple.setMake(result.getString(5));
		    	tuple.setModel(result.getString(6));
		    	tuple.setYear(result.getString(7));
		    	tuple.setColour(result.getString(8));
		    	
		    	resultList.add(tuple);
		    	
		    	
		    }	
		

		} catch (SQLException e)
		{
			
/*			displayPhone.setText("Not found!");
			cleanCustomerInfo();
			searchPhone.setText("");
			searchName.setText("");*/
			e.printStackTrace();
		}
	}
	
	/**
	 * Event handler for the cancel/exit button
	 * 
	 */
	@FXML
	private void handleCancelButton()
	{
		System.out.println("You pressed cancel!");
		// Instead of exiting the program, cancel button should move the user to previous screen
		System.exit(0);
	}
	/**
	 * Event Handler launched when the search fields are modified. Multithreading candidate
	 * @pre phone textfield is edited
	 * @post NotFoundMessage is set to invisible
	 */
	@FXML
	private void handleNotFoundMsg()
	{
		System.out.println("Cleaning");
		
	}
	
}
