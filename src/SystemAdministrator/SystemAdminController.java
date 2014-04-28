package SystemAdministrator;

//import java.awt.Button;
import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import databaseManagement.Query;
import validator.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dataHold.ReportRow;
import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;

public class SystemAdminController 

{
	
		private Parent parent;
	    private Scene scene;
	    private Stage stage;
	    @FXML
	    private TextField EmpId;
	    @FXML
	    private TextField EmpFirstName;
	    @FXML
	    private TextField EmpPassword;
	    @FXML
	    private TextField EmpLastName;
	    @FXML
	    private Button AddButton;
	    @FXML
	    private ComboBox selectBox;
	    @FXML
	    private Label empidlabel;
	    @FXML
	    private Label fnLabel;  
	    @FXML
	    private Label pwdlabel;
	    @FXML
	    private Label lnlabel;
	    @FXML
	    private Label typelabel;
	    @FXML
	    private Label cancelButton;
	   
	    
	    
	    
	    
	    
	    public void redirectHome(Stage stage) {
	    	this.stage = stage;
	        stage.setTitle("Manager");
	        stage.setScene(scene);
	        stage.hide();
	        stage.show();
	    }
	    
	    public void launchLoginController(Stage stage) 
		 {
		        this.stage = stage;
		        stage.setTitle("User Login");
		        stage.setScene(scene);
		        stage.setResizable(false);
		        stage.hide();
		        stage.show();
		 }
	    public SystemAdminController() 
		
	 {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SystemAdminstrator.fxml"));
        fxmlLoader.setController(this);
        try {
       
       
        	parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
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
	    protected void setVisibleFalse1(){
	    	empidlabel.setVisible(false);
	    	fnLabel.setVisible(false);
	    	pwdlabel.setVisible(false);
	    	lnlabel.setVisible(false);
	    	typelabel.setVisible(false);
	    	
	    	
	    }
	    
	    private boolean setVisibleTrue(String EmpoyeeId, String firstName, String lastName , 
	    		String empPassword,String type)
	    {
	    	int count =0;
	    
	    	if(isNull(EmpoyeeId)){
	    		empidlabel.setVisible(true);
	    		count++;
	    	
	    		
	    	}
	    	
	    	if(isNull(firstName)){
	    		fnLabel.setVisible(true);
	    		count++;
	    	}
	    	
	    	if(isNull(lastName)){
	    		lnlabel.setVisible(true);
	    		count++;
	    	}
	    	
	    	if(isNull(empPassword)){
	    		pwdlabel.setVisible(true);
	    		count++;
	    	}
	    	
	    	if(isNull(type))
	    	{
	    		typelabel.setVisible(true);
	    		count++;
	    	}
	    	if(count == 0)
	    	{
	    		return true;
	    	}
	    	return false;
	    	
	    	
	    }
	   @FXML
	    protected void AddAction()
	   
	   		{
		   	setVisibleFalse1();
	    	String EmpoyeeId = EmpId.getText();
	    	String firstName = EmpFirstName.getText();
	    	String lastName =EmpLastName.getText();
	    	String empPassword = EmpPassword.getText();
	    	
	    	String type= selectBox.getValue().toString();
	    	System.out.println("Type is " + type);
	    	int empID = Integer.parseInt (EmpId.getText());
	    	
	    	
	    	
	    if(!setVisibleTrue(EmpoyeeId, firstName,  lastName , 
	    		empPassword, type)){
	    		return;
	    	}
	   
	   try {
	    		Query.autoCommitOff();
	    		
	    		Query.insert("INSERT INTO `employee`(`empID`, `empPwd`, `empFname`, `empLname`, `pwdSalt`, `empType`)"
						+ "VALUES ('" + EmpoyeeId+ "','" + empPassword+ "','" +firstName+"' ,'" + lastName + "','"  + System.nanoTime()+"','" + type +"')");
				System.out.println("Hello");
				
				Query.commit();
				Query.autoCommitOn();
				System.out.println("The employee is addded");
			    EmpId.clear();
			    EmpPassword.clear();
			    EmpFirstName.clear();
			    EmpLastName.clear();
			    selectBox.getSelectionModel().clearSelection();
			   
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
	 
	 @FXML
	 private void handleSearchButton()
	 {
		 
	 }
	 @FXML
	 private void toggle()
	 {
		 
	 }
}
	
