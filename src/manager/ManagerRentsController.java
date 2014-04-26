package manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import dataHold.RentRow;
import dataHold.ReportRow;
import dataHold.SetPriceRow;
import databaseManagement.Query;
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



public class ManagerRentsController implements Initializable {

	@FXML
	private Button generateButton;
	@FXML
	private TableView<RentRow> rentTable; 
	@FXML
	private TableColumn rentIDCol;
	@FXML
	private TableColumn vehicleIDCol;
	@FXML
	private TableColumn custIDCol;
	@FXML
	private TableColumn rentStartCol;
	@FXML
	private TableColumn rentEndCol;
	@FXML
	private TableColumn isBookedCol;
	@FXML
	ObservableList<RentRow> rentList;
	@FXML
	private TableColumn lastNameCol;
	@FXML
	private TableColumn makeCol;
	@FXML
	private TableColumn modelCol;
	@FXML
	private TableColumn coSignerCol;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rentList = FXCollections.observableArrayList();
		rentIDCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentID"));
		vehicleIDCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("vehicleID"));
		custIDCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("custID"));
		rentStartCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentStart"));
		
		rentEndCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("rentEnd"));
		isBookedCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("isBooked"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("lastName"));
		makeCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("make"));
		modelCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("model"));
			rentTable.setItems(rentList);
	    coSignerCol.setCellValueFactory(new PropertyValueFactory<RentRow, String>("coSigner"));
	    rentTable.setColumnResizePolicy(rentTable.CONSTRAINED_RESIZE_POLICY);
	    
	    
	}
	@FXML
	private void handleGenerateButton(){
		System.out.println("Hello Baby");
		try{
			rentList.clear();
			ResultSet result;
			result = Query.select("SELECT R.rentID, R.vehicleID, R.custID, R.rentStart, R.rentEnd, R.isBooked,C.custLname,V.model,V.make, Co.name"
					+ " FROM Rents R, customer C,Vehicle_Rent V, cosigner Co "
					+ "WHERE V.vehicleID = R.vehicleID AND R.custID = C.custID AND Co.custID = C.custID");
					
			System.out.println("Here");
			
			while (result.next()) {
				
				RentRow tuple = new RentRow();
				tuple.setRentID(result.getString(1));
				tuple.setVehicleID(result.getString(2));
				tuple.setCustID(result.getString(3));
				tuple.setRentStart(result.getString(4));
				tuple.setRentEnd(result.getString(5));
				tuple.setIsBooked(result.getString(6));
				tuple.setLastName(result.getString(7));
				tuple.setMake(result.getString(8));
				tuple.setModel(result.getString(9));
				tuple.setCoSigner(result.getString(10));
				System.out.println("Result is "+result.getString(1));
				rentList.add(tuple);

			}
		/*	ResultSet result2;
			result2 = Query.select("SELECT R.rentID, R.vehicleID, R.custID, R.rentStart, R.rentEnd, R.isBooked,C.custLname,V.model,V.make"
					+ " FROM Rents R, customer C,Vehicle_Rent V "
					+ "WHERE V.vehicleID = R.vehicleID AND R.custID = C.custID AND R.custID "
					+ " NOT IN ( SELECT custID FROM cosigner) ");
			
			while (result.next()) {
				
                
				tuple.setRentID(result2.getString(1));
				tuple.setVehicleID(result2.getString(2));
				tuple.setCustID(result2.getString(3));
				tuple.setRentStart(result2.getString(4));
				tuple.setRentEnd(result2.getString(5));
				tuple.setIsBooked(result2.getString(6));
				tuple.setLastName(result2.getString(7));
				tuple.setMake(result2.getString(8));
				tuple.setModel(result2.getString(9));
				tuple.setCoSigner("");
				System.out.println("Result is "+result.getString(1));
				rentList.add(tuple);

			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
