package dataHold;

import javafx.beans.property.SimpleStringProperty;


public class VehicleSearchRow {
    
    private SimpleStringProperty vehicleID; 
    private SimpleStringProperty licPlate;
    private SimpleStringProperty type;
    private SimpleStringProperty category;
    private SimpleStringProperty make;
    private SimpleStringProperty model;
    private SimpleStringProperty year;
    private SimpleStringProperty colour;
    private SimpleStringProperty number;
    /**
     * Default constructor
     * @pre none
     * @post A new VehicleSearchRow object is created with empty values on each field
     */
    public VehicleSearchRow()
    {
      	this.vehicleID = new SimpleStringProperty("");
    	this.licPlate = new SimpleStringProperty("");
    	this.type = new SimpleStringProperty("");
    	this.category = new SimpleStringProperty("");
    	this.make = new SimpleStringProperty("");
    	this.model = new SimpleStringProperty("");
    	this.year = new SimpleStringProperty("");
    	this.colour = new SimpleStringProperty("");
    	this.number = new SimpleStringProperty("");
    }
    
    /**
     * Parametrized constructor
     * @pre paramenters are valid string types
     * @post a new VehicleSearchRow is created with given values
     * @param id
     * @param lic
     * @param type
     * @param cat
     * @param make
     * @param model
     * @param year
     * @param colour
     */
    public VehicleSearchRow(String id, String lic, String type, String cat, String make, String model, String year, String colour)
    {
    	this.vehicleID = new SimpleStringProperty(id);
    	this.licPlate = new SimpleStringProperty(lic);
    	this.type = new SimpleStringProperty(type);
    	this.category = new SimpleStringProperty(cat);
    	this.make = new SimpleStringProperty(make);
    	this.model = new SimpleStringProperty(model);
    	this.year = new SimpleStringProperty(year);
    	this.colour = new SimpleStringProperty(colour);
    }
    /**
     * Getter method for vehicleID
     * @pre none
     * @post return vehicleID value as an string
     * @return
     */
    public String getVehicleID(){
        return vehicleID.get();
    }
    
    public void setVehicleID(String id){
    	vehicleID.set(id);
    }
    
    public String getLicPlate(){
        return licPlate.get();
    }
    
    public void setLicPlate(String lic){
    	licPlate.set(lic);
    }
    
    public String getType(){
        return type.get();
    }
    
    public void setType(String t){
    	type.set(t);
    }
    
    public String getCategory(){
        return category.get();
    }
    
    public void setCategory(String cat){
    	category.set(cat);
    }
    
    public String getMake(){
        return make.get();
    }
    
    public void setMake(String m){
    	make.set(m);
    }
    public String getModel(){
        return model.get();
    }
    
    public void setModel(String mod){
    	model.set(mod);
    }
    public String getYear(){
        return year.get();
    }
    
    public void setYear(String y){
    	year.set(y);
    }
    public String getColour(){
        return type.get();
    }
    
    public void setColour(String col){
    	colour.set(col);
    }
    public String getNumber(){
    	return number.get();
    }
    public void setNumber(String num){
    	number.set(num);
    }
}