package rentScreen;
/**
 * Class that holds all necessary customer information to be passed to the next screen in the rental process (after RentScreen and before CosignerScreen
 * @author ignacio
 *
 */
public class SelectedCustomer {
	private String phone;
	private String lastname;
	private String firstname;
	private String id;
	
	/**
	 * Parametrized constructor
	 * @param p
	 * @param l
	 * @param f
	 * @param i
	 */
	public SelectedCustomer (String p, String l, String f, String i) {
		phone = p;
		lastname = l;
		firstname = f;
		id = i;
	}
	/**
	 * getter method for phone attribute
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * getter method for lastname attribute 
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * getter method for firstname
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * getter method for id attribute
	 * @return
	 */
	public String getId() {
		return id;
	}
}
