
package SystemAdministrator;

import javafx.beans.property.SimpleStringProperty;
/**System EmpSearchHold class
 * 
 * Servers as a controller class for SystemAdminstrator.fxml
 * Holding the search result from the dataBase.
 * @author Prateek Kumar
 *
 */

     	public class EmpSearchHold {

		private SimpleStringProperty empid; 
		private SimpleStringProperty password;
		private SimpleStringProperty fname;
		private SimpleStringProperty lname;
		private SimpleStringProperty type;
		
		public EmpSearchHold()
		{
			this.empid = new SimpleStringProperty("");
	    	this.password = new SimpleStringProperty("");
	    	this.fname = new SimpleStringProperty("");
	    	this.lname = new SimpleStringProperty("");
	    	this.type =new SimpleStringProperty("");
		}

		public String getEmpid() {
			return this.empid.get();
		}

		public void setEmpid(String empid) {
			this.empid.set(empid);
		}

		public String getPassword() {
			return this.password.get();
		}

		public void setPassword(String password) {
			this.password.set(password);
		}

		public String getFname() {
			return this.fname.get();
		}

		public void setFname(String fname) {
			this.fname.set(fname);
		}

		public String getLname() {
			return this.lname.get();
		}

		public void setLname(String lname) {
			this.lname.set(lname);
		}

		public String getType() {
			return this.type.get();
		}

		public void setType(String type) {
			this.type.set(type);
		}
	
}



