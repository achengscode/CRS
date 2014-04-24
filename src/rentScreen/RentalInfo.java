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
	

	public static String getId() {
		return id;
	}


	public static void setId(String id) {
		RentalInfo.id = id;
	}


	public static String getPhone() {
		return phone;
	}


	public static void setPhone(String phone) {
		RentalInfo.phone = phone;
	}


	public static String getLastname() {
		return lastname;
	}


	public static void setLastname(String lastname) {
		RentalInfo.lastname = lastname;
	}


	public static String getFirstname() {
		return firstname;
	}


	public static void setFirstname(String firstname) {
		RentalInfo.firstname = firstname;
	}
	
	
	public static String getFrom() {
		return from;
	}


	public static void setFrom(String from) {
		RentalInfo.from = from;
	}


	public static String getTo() {
		return to;
	}


	public static void setTo(String to) {
		RentalInfo.to = to;
	}


	public static String getVehiclePrice() {
		return vehiclePrice;
	}


	public static void setVehiclePrice(String vehiclePrice) {
		RentalInfo.vehiclePrice = vehiclePrice;
	}


	public static String getEquipmentPrice() {
		return equipmentPrice;
	}


	public static void setEquipmentPrice(String equipmentPrice) {
		RentalInfo.equipmentPrice = equipmentPrice;
	}


	public static String getTotalPrice() {
		return totalPrice;
	}


	public static void setTotalPrice(String totalPrice) {
		RentalInfo.totalPrice = totalPrice;
	}


	public static RentalInfo getCurrentInstance() {
		return currentInstance;
	}


	public static void setCurrentInstance(RentalInfo currentInstance) {
		RentalInfo.currentInstance = currentInstance;
	}


	public static String getVehicleID() {
		return vehicleID;
	}


	public static void setVehicleID(String vehicleID) {
		RentalInfo.vehicleID = vehicleID;
	}


	public static String getLicense() {
		return license;
	}


	public static void setLicense(String license) {
		RentalInfo.license = license;
	}


	public static String getType() {
		return type;
	}


	public static void setType(String type) {
		RentalInfo.type = type;
	}


	public static String getCategory() {
		return category;
	}


	public static void setCategory(String category) {
		RentalInfo.category = category;
	}


	public static String getMake() {
		return make;
	}


	public static void setMake(String make) {
		RentalInfo.make = make;
	}


	public static String getModel() {
		return model;
	}


	public static void setModel(String model) {
		RentalInfo.model = model;
	}


	public static String getYear() {
		return year;
	}


	public static void setYear(String year) {
		RentalInfo.year = year;
	}


	public static String getColour() {
		return colour;
	}


	public static void setColour(String colour) {
		RentalInfo.colour = colour;
	}


	public static boolean isSkiRack() {
		return skiRack;
	}


	public static void setSkiRack(boolean skiRack) {
		RentalInfo.skiRack = skiRack;
	}


	public static boolean isChildSeat() {
		return childSeat;
	}


	public static void setChildSeat(boolean childSeat) {
		RentalInfo.childSeat = childSeat;
	}


	public static boolean isLiftGate() {
		return liftGate;
	}


	public static void setLiftGate(boolean liftGate) {
		RentalInfo.liftGate = liftGate;
	}


	public static boolean isTowingEq() {
		return towingEq;
	}


	public static void setTowingEq(boolean towingEq) {
		RentalInfo.towingEq = towingEq;
	}


	public static String getCustomerLicense() {
		return customerLicense;
	}


	public static void setCustomerLicense(String customerLicense) {
		RentalInfo.customerLicense = customerLicense;
	}


	public static String getCustomerAge() {
		return customerAge;
	}


	public static void setCustomerAge(String customerAge) {
		RentalInfo.customerAge = customerAge;
	}


	public static String getCosignerLastname() {
		return cosignerLastname;
	}


	public static void setCosignerLastname(String cosignerLastname) {
		RentalInfo.cosignerLastname = cosignerLastname;
	}


	public static String getCosignerFirstname() {
		return cosignerFirstname;
	}


	public static void setCosignerFirstname(String cosignerFirstname) {
		RentalInfo.cosignerFirstname = cosignerFirstname;
	}


	public static String getCosignerLicense() {
		return cosignerLicense;
	}


	public static void setCosignerLicense(String cosignerLicense) {
		RentalInfo.cosignerLicense = cosignerLicense;
	}


	public static String getCosignerAge() {
		return cosignerAge;
	}


	public static void setCosignerAge(String cosignerAge) {
		RentalInfo.cosignerAge = cosignerAge;
	}


	public static String getCardNumber() {
		return cardNumber;
	}


	public static void setCardNumber(String cardNumber) {
		RentalInfo.cardNumber = cardNumber;
	}


	public static String getCardCompany() {
		return cardCompany;
	}


	public static void setCardCompany(String cardCompany) {
		RentalInfo.cardCompany = cardCompany;
	}


	public static String getExpMonth() {
		return expMonth;
	}


	public static void setExpMonth(String expMonth) {
		RentalInfo.expMonth = expMonth;
	}


	public static String getExpYear() {
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


	private RentalInfo() {
	
	}
	
	protected static RentalInfo getRentalInfo(){
		if (currentInstance == null)
			currentInstance = new RentalInfo();
		return currentInstance;
	}

}
