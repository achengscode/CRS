package manager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;
public class ManagerController implements Validator,Initializable {
    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    private Text welcomeText;

    @FXML
    private Button Generate;
    
    @FXML
    private RestrictiveTextField vehicleID;
    
    
    @FXML
    private TextField plate;
    
    @FXML
    private TextField model;
    
    @FXML
    private TextField make;
    
    @SuppressWarnings("rawtypes")
	@FXML
    private ComboBox type;
    
    @SuppressWarnings("rawtypes")
	@FXML
    private ComboBox category;
    
    ObservableList<String> countries =
            FXCollections.observableArrayList(
            "Car",
            "Truck"
            );

    final ObservableList<String> carCategory =
            FXCollections.observableArrayList(
            		"Economy","Compact","Mid-size","Standard","Full-size","Premium","Luxury","SUV","Van"
            );

    final ObservableList<String> truckCategory =
            FXCollections.observableArrayList(
            		"24-foot","15-foot","12-foot","box Truck","Cargo Van");
    
   
    
    
    
    
	public ManagerController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Manager2Login.fxml"));
        fxmlLoader.setController(this);
        try {
       
       
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void redirectHome(Stage stage) {
    	this.stage = stage;
        stage.setTitle("Manager");
        stage.setScene(scene);
     //  welcomeText.setText("Hello " + name + "! You are welcome.");
       
      
      
        stage.hide();
        stage.show();
    }
    public boolean validator(String valid){
    	 int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1,
                 2, 3, 4, 5, 0, 7, 0, 9, 2, 3,
                 4, 5, 6, 7, 8, 9 };
int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9,
                  8, 7, 6, 5, 4, 3, 2 };

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
    else if (c >= '0' && c <= '9') value = c - '0';
        
    // illegal character
    else return false;

    sum = sum + weight * value;

}

// check digit
sum = sum % 11;
char check = s.charAt(8);
if (check != 'X' && (check < '0' || check > '9'))
    return false;
if      (sum == 10 && check == 'X') return true;
else if (sum == check - '0')        return true;
else                                return false;



    	
    	
    }
    
    @FXML
    protected void addVehicle(ActionEvent event){
    	String vID = vehicleID.getText();
    	
    	if(!this.validator(vID)){
    		System.out.println("Not valid ");
    		
    		return;
    	}
    	System.out.println("Vehicle ID entered : " + vID);
    	System.out.println("WOW");
    	String type2 = type.getValue().toString();
    	
    	System.out.println(type2);
    	
    	
    	
    	try {
			Query.insert("INSERT INTO `vehicle_rent`(`vehicleID`,`vehicle_type`, `is_available`) "
					+ "VALUES ('" + vID + "','" + type2 + "','" + 1 +"')");
			System.out.println("Inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("OOPS");
		}
    
    }
    
  

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//vehicleID = new RestrictiveTextField();
		vehicleID.setMaxLength(19);
		System.out.println("Hello");
		 type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
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
	}
    
  /*//  @FXML
    protected void handleGenerateReport(ActionEvent event){
    	System.out.println("WOW This is great!!!!!!!");
    	generateReport = new GenerateReport();
    	generateReport.redirectReport(stage);
    } */
}