package dataHold;

import javafx.beans.property.SimpleStringProperty;

public class RentRow {
	private SimpleStringProperty rentID; 
	private SimpleStringProperty vehicleID;
	private SimpleStringProperty custID;
	private SimpleStringProperty rentStart;
	private SimpleStringProperty rentEnd;
	private SimpleStringProperty isBooked;
	private SimpleStringProperty lastName;
	private SimpleStringProperty make;
	private SimpleStringProperty model;
	private SimpleStringProperty coSigner;
	
	public RentRow(){
		this.rentID = new SimpleStringProperty("");
    	this.vehicleID = new SimpleStringProperty("");
    	this.custID = new SimpleStringProperty("");
    	this.rentStart = new SimpleStringProperty("");
    	this.rentEnd = new SimpleStringProperty("");
    	this.isBooked = new SimpleStringProperty("");
    	this.lastName = new SimpleStringProperty("");
    	this.make = new SimpleStringProperty("");
    	this.model = new SimpleStringProperty("");
    	this.coSigner = new SimpleStringProperty("");
	}
	public String getRentID() {
		return this.rentID.get();
	}
	public void setRentID(String id) {
		this.rentID.set(id);
	}
	public String getVehicleID() {
		return this.vehicleID.get();
	}
	public void setVehicleID(String VehicleID) {
		this.vehicleID.set(VehicleID);
	}
	public String getCustID() {
		return this.custID.get();
	}
	public void setCustID(String CustID) {
		this.custID.set(CustID);
	}

	public String getRentStart() {
		return this.rentStart.get();
	}
	public void setRentStart(String rentStart) {
		this.rentStart.set(rentStart);
	}
	public String getRentEnd() {
		return this.rentEnd.get();
	}
	public void setRentEnd(String rentEnd) {
		this.rentEnd.set(rentEnd);
	}
	public String getIsBooked() {
		return this.isBooked.get();
	}
	public void setIsBooked(String booked) {
		this.isBooked.set(booked);
	}
	public String getLastName() {
		return this.lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getMake() {
		return this.make.get();
	}
	public void setMake(String make) {
		this.make.set(make);
	}
	public String getModel() {
		return this.model.get();
	}
	public void setModel(String model) {
		this.model.set(model);
	}
	public String getCoSigner() {
		return this.coSigner.get();
	}
	public void setCoSigner(String coSigner) {
		this.coSigner.set(coSigner);
	}
}
