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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;





import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;
/**ManagerController class
 * 
 * Servers as a controller class for Manager2Login.fxml
 * 
 * @author Puneet Kumar Dimri
 *
 */

public class ManagerController implements Validator,Initializable {
    private Parent parent;
    private Scene scene;
    private Stage stage;
    
    @FXML
    private Text welcomeText;

    @FXML
    private Button Generate;
    /**
     * Used to store value for vehicleID.
     */
    @FXML
    private TextField vehicleID;
    /**
     * Used to store Plate Number information.
     */
    
    @FXML
    private TextField plate;
    /**
     * Used to store Model information of the vehicle.
     */
    @FXML
    private TextField model;
    /*
     * Used to store the Make information of the vehicle.
     */
    @FXML
    private TextField make;
    /*
     * Used to store color information of the vehicle.
     */
    @FXML
    private TextField color;
    /*
     * Used to store Manufacturing information of the vehicle.
     */
    @FXML
    private TextField manufacturing;
    /*
     * Used for displaying information about the vehicle id.
     */
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
    
    ObservableList<String> categories =
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
            scene = new Scene(parent, 800, 400);
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
    
    public boolean checkType(){
    	try{
    		type.getValue().toString();
    		return true;
    	}
    	catch(Exception e){
    		 	    return false;
    	}
    }
    
    public boolean checkCategory(){
    	try{
    		category.getValue().toString();
    		return true;
    	}
    	catch(Exception e){
    		
    	    return false;
    	}
    	
    }
    
    public boolean isInt(String s){
    	try{
    		Integer.parseInt(s);
    		return true;
    	}
    	catch(Exception e){
    	
    		return false;
    	}
    }
    
    public boolean isNull(String s){
    	if(s.equalsIgnoreCase("")){
    		return true;
    	}
    	return false;
    }
    protected void setVisibleFalse(){
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
    
    private boolean setVisibleTrue(String vID, String plateValue, String modelValue , 
    		String makeValue, String mnf, String colorValue)
    {
    	int count =0;
    	int flag =0;
    	int flag2 =0;
    	if(isNull(vID)){
    		idLabel.setVisible(true);
    		count++;
    		flag++;
    		
    	}
    	
    	if(isNull(plateValue)){
    		plateLabel.setVisible(true);
    		count++;
    	}
    	
    	if(isNull(modelValue)){
    		modelLabel.setVisible(true);
    		count++;
    	}
    	
    	if(isNull(makeValue)){
    		makeLabel.setVisible(true);
    		count++;
    	}
    	if(isNull(mnf)){
    		yearLabel.setVisible(true);
    		count++;
    		flag2++;
    	}
    	if(!isInt(mnf) && flag2 == 0){
    		numberLabel.setVisible(true);
    		count++;
    	}
    	
    	if(isNull(colorValue)){
    		colorLabel.setVisible(true);
    		count++;
    	}
    	if(!validator(vID) && flag == 0){
    		idInvalid.setVisible(true);
    		count++;
    		
    	}
    	if(!checkType()){
    		typeLabel.setVisible(true);
    		count++;
    	}
    	if(!checkCategory()){
    		categoryLabel.setVisible(true);
    		count++;
    	}
    	if(count == 0){
    		return true;
    	}
    	return false;
    	
    }
    @FXML
    protected void addVehicle(ActionEvent event){
    	setVisibleFalse();
    	String vID = vehicleID.getText();
    	
    	
    	String plateValue = plate.getText();
    	
    	String modelValue = model.getText();
    	
    	String makeValue =make.getText();
    	
    	String mnf = manufacturing.getText();
    	
    	String colorValue = color.getText();
    	
    	if(!setVisibleTrue(vID, plateValue,  modelValue , 
        		 makeValue,  mnf,  colorValue)){
    		return;
    	}
    	String type2 = type.getValue().toString();
    	System.out.println(type2);
    	String categoryValue = category.getValue().toString();
    	
    	
    	int manufacturingValue = Integer.parseInt(manufacturing.getText());
    	
    	try {
    		Query.autoCommitOff();
			Query.insert("INSERT INTO `Vehicle_Rent`(`vehicleID`,`vehicle_type`, `is_available`,`model`,`make`,`vehicle_year`,`license_plate`,`colour`) "
					+ "VALUES ('" + vID + "','" + type2 + "','" + 1 +"' ,'" + modelValue + "','"  + makeValue +"','" + manufacturingValue +"','" + plateValue + "','" + colorValue + "')");
			Query.insert("INSERT INTO `Vehicle_Category`(`vehicleID`,`vehicle_category`) "
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
		}
    	catch(MySQLIntegrityConstraintViolationException iCV){
    		try{
    			Query.rollback();
    			}
    			catch(SQLException ex){
    				ex.printStackTrace();
    			}
    		System.out.println("Value Already Present");
    	}
    	catch (SQLException e) {
			
			// TODO Auto-generated catch block
			try{
			Query.rollback();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("OOPS");
		}
    
    }
    
  

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//vehicleID = new RestrictiveTextField();
		//vehicleID.setMaxLength(17);
		vehicleID.setPromptText("VIN NUMBER");
		
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
    
 
}