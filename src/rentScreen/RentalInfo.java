package rentScreen;

public class RentalInfo {
	
	// Car attributes
	private static RentalInfo currentInstance;
	private static String vehicleID;
	private static String license;
	private static String type;
	private static String category;
	private static String make;
	private static String model;
	private static String year;
	private static String colour;
	
	// Additional Equipment
	private static boolean skiRack;
	private static boolean childSeat;
	private static boolean liftGate;
	private static boolean towingEq;
	
	//Customer attributes
	private static String id;
	private static String phone;
	private static String lastname;
	private static String firstname;
	
	// Rental Period
	private static String from;
	private static String to;
	
	// Rent information
	private static String rentID;
	
	// Price information
	private static String vehiclePrice;
	private static String equipmentPrice;
	private static String totalPrice;
	
	// Cosigner Screen fields and information
	// License Information
	private static String customerLicense;
	private static String customerAge;
	private static String cosignerLastname;
	private static String cosignerFirstname;
	private static String cosignerLicense;
	private static String cosignerAge;
	
	// Payment Information
	private static String cardNumber;
	private static String cardCompany;
	private static String expMonth;
	private static String expYear;
	private static String holderName;
	
	// Booking status (previous or not)
	private static boolean isBooking;
	
	public static void flushInfo()
	{
		// Car attributes
		vehicleID = "";
		license = "";
		type = "";
		category = "";
		make = "";
		model = "";
		year = "";
		colour = "";
		
		// Additional Equipment
		skiRack = false;
		childSeat = false;
		liftGate = false;
		towingEq = false;
		
		//Customer attributes
		id = "";
		phone = "";
		lastname = "";
		firstname = "";
		
		// Rental Period
		from = "";
		to = "";
		rentID = "";
		
		// Price information
		vehiclePrice = "";
		equipmentPrice = "";
		totalPrice = "";
		
		// Cosigner Screen fields and information
		// License Information
		customerLicense = "";
		customerAge = "";
		cosignerLastname = "";
		cosignerFirstname = "";
		cosignerLicense = "";
		cosignerAge = "";
		
		// Payment Information
		cardNumber = "";
		cardCompany = "";
		expMonth = "";
		expYear = "";
		holderName = "";
	}
	
	public String getId() {
		return id;
	}


	public static void setId(String id) {
		RentalInfo.id = id;
	}


	public String getPhone() {
		return phone;
	}


	public static void setPhone(String phone) {
		RentalInfo.phone = phone;
	}


	public String getLastname() {
		return lastname;
	}


	public static void setLastname(String lastname) {
		RentalInfo.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public static void setFirstname(String firstname) {
		RentalInfo.firstname = firstname;
	}
	
	
	public  String getFrom() {
		return from;
	}


	public static void setFrom(String from) {
		RentalInfo.from = from;
	}


	public String getTo() {
		return to;
	}


	public static void setTo(String to) {
		RentalInfo.to = to;
	}


	public String getVehiclePrice() {
		return vehiclePrice;
	}


	public static void setVehiclePrice(String vehiclePrice) {
		RentalInfo.vehiclePrice = vehiclePrice;
	}


	public String getEquipmentPrice() {
		return equipmentPrice;
	}


	public static void setEquipmentPrice(String equipmentPrice) {
		RentalInfo.equipmentPrice = equipmentPrice;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public static void setTotalPrice(String totalPrice) {
		RentalInfo.totalPrice = totalPrice;
	}


	public static RentalInfo getCurrentInstance() {
		return getRentalInfo();
	}


	public String getVehicleID() {
		return vehicleID;
	}


	public static void setVehicleID(String vehicleID) {
		RentalInfo.vehicleID = vehicleID;
	}


	public String getLicense() {
		return license;
	}


	public static void setLicense(String license) {
		RentalInfo.license = license;
	}


	public String getType() {
		return type;
	}


	public static void setType(String type) {
		RentalInfo.type = type;
	}


	public String getCategory() {
		return category;
	}


	public static void setCategory(String category) {
		RentalInfo.category = category;
	}


	public String getMake() {
		return make;
	}


	public static void setMake(String make) {
		RentalInfo.make = make;
	}


	public String getModel() {
		return model;
	}


	public static void setModel(String model) {
		RentalInfo.model = model;
	}


	public String getYear() {
		return year;
	}


	public static void setYear(String year) {
		RentalInfo.year = year;
	}


	public String getColour() {
		return colour;
	}


	public static void setColour(String colour) {
		RentalInfo.colour = colour;
	}


	public boolean isSkiRack() {
		return skiRack;
	}


	public static void setSkiRack(boolean skiRack) {
		RentalInfo.skiRack = skiRack;
	}


	public boolean isChildSeat() {
		return childSeat;
	}


	public static void setChildSeat(boolean childSeat) {
		RentalInfo.childSeat = childSeat;
	}


	public boolean isLiftGate() {
		return liftGate;
	}


	public static void setLiftGate(boolean liftGate) {
		RentalInfo.liftGate = liftGate;
	}


	public boolean isTowingEq() {
		return towingEq;
	}


	public static void setTowingEq(boolean towingEq) {
		RentalInfo.towingEq = towingEq;
	}


	public String getCustomerLicense() {
		return customerLicense;
	}


	public static void setCustomerLicense(String customerLicense) {
		RentalInfo.customerLicense = customerLicense;
	}


	public String getCustomerAge() {
		return customerAge;
	}


	public static void setCustomerAge(String customerAge) {
		RentalInfo.customerAge = customerAge;
	}


	public String getCosignerLastname() {
		return cosignerLastname;
	}


	public static void setCosignerLastname(String cosignerLastname) {
		RentalInfo.cosignerLastname = cosignerLastname;
	}


	public String getCosignerFirstname() {
		return cosignerFirstname;
	}


	public static void setCosignerFirstname(String cosignerFirstname) {
		RentalInfo.cosignerFirstname = cosignerFirstname;
	}


	public String getCosignerLicense() {
		return cosignerLicense;
	}


	public static void setCosignerLicense(String cosignerLicense) {
		RentalInfo.cosignerLicense = cosignerLicense;
	}


	public String getCosignerAge() {
		return cosignerAge;
	}


	public static void setCosignerAge(String cosignerAge) {
		RentalInfo.cosignerAge = cosignerAge;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public static void setCardNumber(String cardNumber) {
		RentalInfo.cardNumber = cardNumber;
	}


	public String getCardCompany() {
		return cardCompany;
	}


	public static void setCardCompany(String cardCompany) {
		RentalInfo.cardCompany = cardCompany;
	}


	public String getExpMonth() {
		return expMonth;
	}


	public static void setExpMonth(String expMonth) {
		RentalInfo.expMonth = expMonth;
	}


	public String getExpYear() {
		return expYear;
	}


	public static void setExpYear(String expYear) {
		RentalInfo.expYear = expYear;
	}


	public static String getHolderName() {
		return holderName;
	}


	public static void setHolderName(String holderName) {
		RentalInfo.holderName = holderName;
	}

	public static void setBooking(boolean status) {
		isBooking = status;
	}
	
	public boolean getBookingStatus() {
		return isBooking;
	}
	
	private RentalInfo() {
		flushInfo();
	
	}
	
	protected static RentalInfo getRentalInfo(){
		if (currentInstance == null)
			currentInstance = new RentalInfo();
		return currentInstance;
	}
	
	public static void setRentId(String id){
		rentID = id;
	}
	
	public String getRentId() {
		return rentID;
	}
	
}
