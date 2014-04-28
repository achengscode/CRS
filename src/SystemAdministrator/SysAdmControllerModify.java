package SystemAdministrator;


import java.beans.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import databaseManagement.Query;
import validator.Validator;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dataHold.ReportRow;
import databaseManagement.Query;
import validator.Validator;
import limitTextFeild.RestrictiveTextField;
import security.DBSecurity;

/**System Admintrator Controller class
 * 
 * Servers as a controller class for SystemAdminstrator.fxml
 * 
 * @author Prateek Kumar
 *
 */
	public class SysAdmControllerModify implements Initializable
	{
	private Parent parent;
		    private Scene scene;
		    private Stage stage;
		    @FXML
		    private TextField Emp2id;
		    @FXML
		    private TextField fname2;
		    @FXML
		    private TextField lname2;
		    @FXML
		    private TextField type2;
		   
		   
		    @FXML
		    private Label Empid2Label;
		    @FXML
		    private Label fn2label;
		    @FXML
		    private Label ln2label;
		    @FXML
		    private Label type2label;
		    @SuppressWarnings("rawtypes")
			@FXML
		    private ComboBox Selectype2;
		    @FXML
		    
		    //table part
			private TableView<EmpSearchHold> searchemptable;
		    ObservableList<EmpSearchHold> searchEmplist;
		    @FXML
		    private TableColumn colEmpid;
		    @FXML
		    private TableColumn colPwd;
		    @FXML
		    private TableColumn colFname;
		    @FXML
		    private TableColumn colLastname;
		    @FXML
		    private TableColumn coltype;
		   
		    @FXML
		    private Button searchButton2;
		    
		    //For modify 
		    @FXML
		    private AnchorPane anchor;
		    @FXML
		    private TextField empUpdate;
		    @FXML
		    private TextField fnUpdate;
		    @FXML
		    private TextField lnUpdate;
		    @FXML
		    private TextField typeUpdate;
		    @FXML
		    private TextField passUpdate;
		    // Declaration for radio button.
		    
	  
		  @FXML
		    private RadioButton radio1;
		    @FXML
		    private RadioButton radio2;
		    
		    //Pane Declaration.
		    @FXML
		    private Pane searchpane;
		   @FXML
		   private Button cancelButton;
		   @FXML 
		   private Label pwdUpdateSuccess;
		 
		   	    
		    
		    @SuppressWarnings("unchecked")
			public void initialize(URL location, ResourceBundle resources) {
				
			System.out.println("Inside initialize");	
			searchEmplist = FXCollections.observableArrayList();
		    
		    colEmpid.setCellValueFactory(new PropertyValueFactory<EmpSearchHold, String>(
							"empid"));
			
		   // colPwd.setCellValueFactory(new PropertyValueFactory<EmpSearchHold, String>(
			//				"password"));
		    colFname.setCellValueFactory(new PropertyValueFactory<EmpSearchHold, String>(
					"fname"));
		    colLastname.setCellValueFactory(new PropertyValueFactory<EmpSearchHold, String>(
					"lname"));
		    coltype.setCellValueFactory(new PropertyValueFactory<EmpSearchHold, String>(
					"type"));
			
			searchemptable.setItems(searchEmplist);
			System.out.println("Initialize END");
			};
		    
		    
			 public void launchLoginController(Stage stage) 
			 {
			        this.stage = stage;
			        stage.setTitle("User Login");
			        stage.setScene(scene);
			        stage.setResizable(true);
			        stage.hide();
			        stage.show();
			 }
		    
		    public void redirectHome(Stage stage) {
		    	this.stage = stage;
		        stage.setTitle("Manager");
		        stage.setScene(scene);
		        stage.hide();
		        stage.show();
		    }
		    
		    
		    public SysAdmControllerModify () 
			
		 {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SystemAdminstrator.fxml"));
	        fxmlLoader.setController(this);
	        try {
	       
	       
	        	parent = (Parent) fxmlLoader.load();
	            scene = new Scene(parent);
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
		    	Empid2Label.setVisible(false);
		    	fname2.setVisible(false);
		    	type2label.setVisible(false);
		    	ln2label.setVisible(false);
		    	
		    }
		    

		    
		    	@FXML
				private void toggle(){
		    
		    		if (radio1.isSelected())
		    		{
		    			Emp2id.setDisable(false);
		    			fname2.clear();
		    			lname2.clear();
		    		
		    			fname2.setDisable(true);
		    			lname2.setDisable(true);
		    			Selectype2.setDisable(true);
		    		}
		    		else{
		    			Emp2id.clear(); 
		    			Emp2id.setDisable(true);
		    			fname2.setDisable(false);
		    			lname2.setDisable(false);
		    			Selectype2.setDisable(false);
		    			}
		    		}
		    	
		    	private boolean isBoxSelected(ComboBox b){
		    		try{
		    			b.getValue().toString();
		    			return true;
		    		}
		    		catch(Exception e){
		    			return false;
		    		}
		    	}
		    		
		    	        @FXML
		    			private void handleSearchButton()
		    			{
		    	        	String empid =null;
		    	        	String lname =null;
		    	        	String fnname =null;
		    	        	String type = null;

		    	        	type2label.setVisible(false);
		    	        	fn2label.setVisible(false);
		    	        	ln2label.setVisible(false);
		    	        	Empid2Label.setVisible(false);
		    		if(radio1.isSelected()){		
		    		 empid = Emp2id.getText();
		    		 if(isNull(empid))
		    		 {
		    			 Empid2Label.setVisible(true);
		    		 }
		    		 
		    		}
		    		else if(radio2.isSelected()){
		    		 fnname =fname2.getText();
    				 lname =lname2.getText();
    				 int count =0;
    				 if(isNull(fnname))
 					{
 						fn2label.setVisible(true);
 						count++;
 						
 					}
 					 if(isNull(lname))
 					{
 						ln2label.setVisible(true);
 						count++;
 					}
    				if(!isBoxSelected(Selectype2))
    				{
    					type2label.setVisible(true);
    					count++;
    					return;
    				}
    					if(count > 0){
    						return;
    					}
    				
    				 type =Selectype2.getValue().toString();
    				
		    		}
		    		else{
		    			System.out.println("Select a radio button");
		    		}
    				System.out.println("Here");
		    
		    		try{
		    		searchEmplist.clear();
		    		System.out.println("HELLO");
		    		ResultSet result = null;
		    	// populating the table with  the corresponding feild selected by the customer.
		    		result = Query.select("SELECT `empID`, `empPwd`, `empFname`, `empLname`"
		    				+ ", `empType` FROM `employee` W "
		    				+ "WHERE empid='"+empid+"'OR(empFname='"+fnname+"'AND empLname='"
		    				+lname+"'AND empType='"+type+"')");
		    		
		    		
		    		while(result.next()){
		    	    	EmpSearchHold tuple = new EmpSearchHold();
		    	    	tuple.setEmpid(result.getString(1));
		    	    	tuple.setPassword(result.getString(2));
		    	    	tuple.setFname(result.getString(3));
		    	    	tuple.setLname(result.getString(4));
		    	    	tuple.setType(result.getString(5));
		    	    	System.out.println("Hello");
		    	    	System.out.println(tuple.getType());
		    	    	searchEmplist.add(tuple);
		    	    
		    	    }
		    	}
		    	catch(Exception e){
		    		e.printStackTrace();
		    	}
		   }
		    	
		    
		    @FXML
		    private void handleRemoveEmp()
		    {
		   	String empid = Emp2id.getText();
		    String empid2 = searchemptable.getSelectionModel().getSelectedItem().getEmpid();
		    System.out.println("Empid is " + empid2);
		       
		    	try{
		    		searchEmplist.clear();
		    		System.out.println("HELLO");
		    		ResultSet result;
		    	
		    		Query.delete("DELETE FROM employee WHERE empID='"+empid2+"'");
		    		clearAllData();
		    	   
		    	}
		    	catch(Exception e){
		    		e.printStackTrace();
		    	}
		   
		    } 
		    
		    @FXML
			 private void handleChangeButton()
		     {	
		    	String empidup= searchemptable.getSelectionModel().getSelectedItem().getEmpid();
		    	String fnameUpdate=searchemptable.getSelectionModel().getSelectedItem().getFname();
		    	String lnameUpdate=searchemptable.getSelectionModel().getSelectedItem().getLname();
		    	String tyUpdate=searchemptable.getSelectionModel().getSelectedItem().getType();
		    	
		    	empUpdate.setText(empidup);
		    	fnUpdate.setText(fnameUpdate);
		    	lnUpdate.setText(lnameUpdate);
		    	typeUpdate.setText(tyUpdate);
		    	//passUpdate.setText(pwdUpdate);
		    	
		    	
		    	
			 }
		    @FXML
		    private void manageupdateButton()
		    {	System.out.println("Update button is clicked");
		    	String empid2=Emp2id.getText();
		    	String empid3=empUpdate.getText();
		    	String fn3=fnUpdate.getText();
		    	String ln3=lnUpdate.getText();
		    	String type3=typeUpdate.getText();
		    	String pwd3=passUpdate.getText();
		    	
		    	ResultSet result2;
		    	String salt = null;
				try {
					result2 = Query.select("SELECT pwdSalt FROM employee WHERE empID='"+empid2+"'");
					result2.next();
					salt = result2.getString(1);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		    	
		    	String newPassword = DBSecurity.hashedString(pwd3,salt);
		    	
		   try{ 	
		    Query.update("UPDATE `employee` SET `empID`='"+empid3+"',`empPwd`='"+newPassword+"',`empFname`='"+fn3+"',`empLname`='"+ln3+"',`pwdSalt`='"+salt+"',`empType`='"+type3+"' WHERE empID='"+empid2+"'");
		   
		    empUpdate.clear();
		    fnUpdate.clear();
		    lnUpdate.clear();
		    typeUpdate.clear();
		   //passUpdate.clear();
		    pwdUpdateSuccess.setVisible(true);
		    clearAllData();
		    
		   }
		   catch(Exception e){
	    		e.printStackTrace();
	    	}
		    	
		    }
		    
		    @FXML
			 private void AddAction()
		     {
				 
			 }
		    
		    @FXML
			 private void handlecancelButton()
			 {	 
				 searchpane.setVisible(true);
				 clearAllData();
				 
			 }
	   private void clearAllData()
	   {
		   searchEmplist.clear();
		   Emp2id.clear();
		   fname2.clear();
		   lname2.clear();
		   Empid2Label.setVisible(false);
		   fn2label.setVisible(false);
		   ln2label.setVisible(false);
		   type2label.setVisible(false);
		   empUpdate.clear();
		   fnUpdate.clear();
		   lnUpdate.clear();
		   typeUpdate.clear();
		   passUpdate.clear();
		   
	   }
	
	}
			
			
				 
				
			 
			 


