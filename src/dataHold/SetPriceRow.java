package dataHold;

import javafx.beans.property.SimpleStringProperty;

public class SetPriceRow {

	private SimpleStringProperty type; 
	private SimpleStringProperty hour;
	private SimpleStringProperty day;
	private SimpleStringProperty week;
	private SimpleStringProperty equipmentType;
	
	public SetPriceRow(){
		this.type = new SimpleStringProperty("");
    	this.hour = new SimpleStringProperty("");
    	this.day = new SimpleStringProperty("");
    	this.week = new SimpleStringProperty("");
    	this.equipmentType = new SimpleStringProperty("");
	}
	public String getType() {
		return this.type.get();
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public String getHour() {
		return this.hour.get();
	}
	public void setHour(String hour) {
		this.hour.set(hour);
	}
	public String getDay() {
		return this.day.get();
	}
	public void setDay(String day) {
		this.day.set(day);
	}
	public void setWeek(String week){
		this.week.set(week);
	}
	public String getWeek(){
		return week.get();
	}
	public String getEquipmentType() {
		return equipmentType.get();
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType.set(equipmentType);
	}
}
