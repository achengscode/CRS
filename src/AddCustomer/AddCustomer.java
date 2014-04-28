package AddCustomer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import databaseManagement.Query;

public class AddCustomer

{

	private Parent parent;
	private Scene scene;
	private Stage stage;

	@FXML
	private Button AddCustomer;
	@FXML
	private TextField custDriverLicense;
	@FXML
	private TextField custFirstName;
	@FXML
	private TextField custLastName;
	@FXML
	private TextField custUserName;
	@FXML
	private TextField custPhoneNumber;
	@FXML
	private TextField custPassword;
	@FXML
	private TextField custStreetAddress;
	@FXML
	private TextField custCity;
	@FXML
	private TextField custProvince;
	@FXML
	private TextField custPostal1;
	@FXML
	private TextField custPostal2;
	@FXML
	private Label custDLInstructLabel;
	@FXML
	private Label custFnInstructLabel;
	@FXML
	private Label custLnInstructLabel;
	@FXML
	private Label custUNInstructLabel;
	@FXML
	private Label custPhoneInstructLabel;
	@FXML
	private Label custPswdInstructLabel;
	@FXML
	private Label custAddInstructLabel;
	@FXML
	private Label custCityInstructLabel;
	@FXML
	private Label custProvInstructLabel;
	@FXML
	private Label custPostalInstructLabel;
	@FXML
	private RadioButton selectPhone;
	@FXML
	private RadioButton selectID;
	@FXML
	private RadioButton selectUserName;
	@FXML
	private TextField searchName;
	@FXML
	private TextField searchPhone;
	@FXML
	private TextField searchID;
	

	private String customerID;

	/*
	 * For the modify employee For the Modify Employee used different variables
	 * suggest a better.
	 */

	public AddCustomer()

	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"AddCustomer.fxml"));
		fxmlLoader.setController(this);
		try {

			parent = (Parent) fxmlLoader.load();
			scene = new Scene(parent, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchCustomerController(Stage stage) {
		this.stage = stage;
		stage.setTitle("User Login");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.hide();
		stage.show();
	}

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public boolean isNull(String s) {
		return (s == null);

	}

	protected void setVisibleFalse1() {
		custDLInstructLabel.setVisible(false);
		custFnInstructLabel.setVisible(false);
		custLnInstructLabel.setVisible(false);
		custUNInstructLabel.setVisible(false);
		custPhoneInstructLabel.setVisible(false);
		custPswdInstructLabel.setVisible(false);
		custAddInstructLabel.setVisible(false);
		custCityInstructLabel.setVisible(false);
		custProvInstructLabel.setVisible(false);
		custPostalInstructLabel.setVisible(false);
	}

	private boolean setVisibleTrue(String custDL, String custFN, String custLN,
			String custUn, String custPh, String custPsswd, String custAdd,
			String custCty, String custProv, String P1) {
		int count = 0;

		if (isNull(custDL)) {
			custDLInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custFN)) {
			custFnInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custLN)) {
			custLnInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custUn)) {
			custUNInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custPh)) {
			custPhoneInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custPsswd)) {
			custPswdInstructLabel.setVisible(true);
			count++;
		}

		if (isNull(custAdd)) {
			custAddInstructLabel.setVisible(true);
			count++;
		}
		if (isNull(custCty)) {
			custCityInstructLabel.setVisible(true);
			count++;
		}
		if (isNull(custProv)) {
			custProvInstructLabel.setVisible(true);
			count++;
		}
		if (isNull(P1)) {
			custPostalInstructLabel.setVisible(true);
			count++;
		}
		if (count == 0) {
			return true;
		}
		return false;

	}

	@FXML
	protected void searchCustomerRecord() {
		// searches the customer based on any combination of the 4 pks in modify
		// customer, stores it, and then calls the customer info to screen
		String query = buildQuery();
		try {
			ResultSet result = Query.select(query);
			if (result.next())
			{
				customerID = result.getString(1);
				custUserName.setText(result.getString(2));
				custPhoneNumber.setText(result.getString(3));
				custDriverLicense.setText(result.getString(6));
				custFirstName.setText(result.getString(7));
				custLastName.setText(result.getString(8));
				custStreetAddress.setText(result.getString(11));
				custCity.setText(result.getString(12));
				custProvince.setText(result.getString(13));
				custPostal1.setText(result.getString(14));
			}
			else
			{
				Dialogs.showErrorDialog(stage, "No results found!", "Oops!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String buildQuery()
	{
		String query = "";
		if (selectPhone.isSelected())
		{
			query = String.format("SELECT * FROM customer C, custAddress A WHERE C.custID = A.custID AND C.phoneNo='%s'",
					searchPhone.getText());
		} 
		else if (selectUserName.isSelected())
		{
			query = String.format("SELECT * FROM customer C, custAddress A WHERE C.custID = A.custID AND C.custUserName='%s'",
					searchName.getText());
		}
		else if (selectID.isSelected())
		{
			query = String.format("SELECT * FROM customer C, custAddress A WHERE C.custID = A.custID AND C.custID='%s'",
					searchID.getText());
		}
		return query;
	}

	@FXML
	private void updateCustomerRecord() {
		try {
			Query.autoCommitOff();

			Query.update("UPDATE  customer SET custUsername =" + "'"
					+ custUserName.getText() + "'" + "," + "phoneNo=" + "'"
					+ custPhoneNumber.getText() + "'" + ","
					+ "driverLicenseNo=" + "'" + custDriverLicense.getText()
					+ "'," + "custFname=" + "'" + custFirstName.getText() + "',"
					+ "custLname=" + "'" + custLastName.getText() + "'" + " WHERE custID=" + customerID);

			Query.commit();
			Query.autoCommitOn();
			Dialogs.showInformationDialog(stage, "Customer updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	private void AddAction() {
		setVisibleFalse1();
		String custDL = custDriverLicense.getText();
		String custFN = custFirstName.getText();
		String custLN = custLastName.getText();
		String custUN = custUserName.getText();
		String custPh = custPhoneNumber.getText();
		String custPwd = custPassword.getText();
		String custStAdd = custStreetAddress.getText();
		String custCty = custCity.getText();
		String custProv = custProvince.getText();
		String custP1 = custPostal1.getText();

		if (!setVisibleTrue(custDL, custFN, custLN, custUN, custPh, custPwd,
				custStAdd, custCty, custProv, custP1)) {
		}

		try {
			Query.autoCommitOff();

			Query.insert("INSERT INTO `customer`(`custUsername`, `phoneNo`, `custPassword`, `salt`, `driverLicenseNo`, `custFname`, `custLname`)"
					+ "VALUES ('"
					+ custUN
					+ "','"
					+ custPh
					+ "' ,'"
					+ custPwd
					+ "','"
					+ System.nanoTime()
					+ "','"
					+ custDL
					+ "','"
					+ custFN + "' ,'" + custLN + "')");
			
			String query = String.format("INSERT INTO custAddress VALUES(`%s`, `%s`, `%s`, `%s`, `%s`", customerID, custStreetAddress.getText(),
					custCity.getText(), custProvince.getText(), custPostal1.getText());
			Query.insert(query);

			Query.commit();
			Query.autoCommitOn();
			System.out.println("The customer is added");
			custDriverLicense.clear();
			custFirstName.clear();
			custLastName.clear();
			custUserName.clear();
			custPhoneNumber.clear();
			custPassword.clear();
			custStreetAddress.clear();
			custCity.clear();
			custProvince.clear();
			custPostal1.clear();
			custPostal2.clear();

		} catch (MySQLIntegrityConstraintViolationException iCV) {
			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (SQLException e) {

			try {
				Query.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("OOPS");
		}
	}
	@FXML
	private void keepAlive() {
		try {
			Query.select("Select * from equipment");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
