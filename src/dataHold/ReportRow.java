package dataHold;

import javafx.beans.property.SimpleStringProperty;

public class ReportRow {

	private SimpleStringProperty category; 
	private SimpleStringProperty count;
	private SimpleStringProperty sum;
	
	public ReportRow(){
		this.category = new SimpleStringProperty("");
    	this.count = new SimpleStringProperty("");
    	this.sum = new SimpleStringProperty("");
	}
	public String getCategory() {
		return this.category.get();
	}
	public void setCategory(String category) {
		this.category.set(category);
	}
	public String getCount() {
		return this.count.get();
	}
	public void setCount(String count) {
		this.count.set(count);
	}
	public String getSum() {
		return this.sum.get();
	}
	public void setSum(String sum) {
		this.sum.set(sum);
	}
}
