package payment;

import java.text.NumberFormat;
import java.util.Locale;

import databaseManagement.Query;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Payment subpackage of CRS.
 * Processes and calculates the prices for the current rental.
 * @author Aaron Cheng
 *
 */
public class Payment {
    
    /**
     * Tax rate, currently at 12% tax.
     */
    private final double TAX_RATE = 1.12;
    private double weeklyRate;
    private double hourlyRate;
    private double dailyRate;
    private double insuranceRate;
    
    private double equipment1Week;
    private double equipment1Daily;
   
    private double equipment2Week;
    private double equipment2Daily;
    
    private double finalPrice;
    private String vehicleCategory;
    
    /**
     * Payment constructor. Takes a vehicle ID,
     * and retrieves it's hourly, weekly and daily rates.
     */
    public Payment(String vehicleID)
    {
        try
        {
            ResultSet prices = Query.select("SELECT * FROM rentalrates WHERE rentCategory IN "
                    + "(SELECT rentCategory FROM Vehicle_Category WHERE vehicleID = '" + vehicleID + "')");
            prices.next();
            vehicleCategory = prices.getString(1);
            hourlyRate = prices.getDouble(2);
            dailyRate = prices.getDouble(3);
            weeklyRate = prices.getDouble(4);
            insuranceRate = prices.getDouble(5);
            
            equipment1Week = 0.0;
            equipment1Daily = 0.0;
            
            equipment2Week = 0.0;
            equipment2Daily = 0.0;
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Calculates the price of the rental, given two dates. Due dates will always be 11 AM
     * of the date the rental is due. 
     * 
     * Only need to calculate rentalHours if and only if the rental itself is overdue.
     * 
     * @pre startDate < returnDate
     * @post totalPrice.decimalPlace = 2
     * @param startDate The date the rental started
     * @param dueDate The date the rental is due
     * @param returnDate The date the rental is returned.
     * @return The price of the rental to two decimal places.
     */
    public String calculateTotalPrice(Date startDate, Date dueDate, Date returnDate)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        if (startDate.after(dueDate))
        {
            return null; //should really throw exceptions here.
        }
        
        double rentalWeek = DateOperations.getWeekDifference(startDate, dueDate);
        double rentalDay = DateOperations.getDayDifference(startDate, dueDate);
        double rentalHours = DateOperations.getHourDifference(startDate, dueDate);
        rentalDay -= (rentalWeek * 7); //subtract any multiple of 7 days.
        double totalPrice = (rentalWeek * weeklyRate);
        totalPrice += (rentalHours * hourlyRate);
        totalPrice += (rentalWeek * equipment1Week);
        totalPrice += (rentalWeek * insuranceRate);
        totalPrice += (rentalWeek * equipment2Week);
        totalPrice += (rentalDay * dailyRate);
        totalPrice += (rentalDay * equipment1Daily);
        totalPrice += (rentalDay * equipment2Daily);
        totalPrice += (rentalDay * insuranceRate);
        
        //calculate overdue prices (if any)
        if (isOverdue(dueDate, returnDate))
        {
            double overdueHours = DateOperations.getHourDifference(dueDate, returnDate);
            double overdueDay = DateOperations.getDayDifference(dueDate, returnDate);
            double overdueWeek = DateOperations.getWeekDifference(dueDate, returnDate);
            overdueDay -= (overdueWeek * 7);
            
            totalPrice += (overdueHours * hourlyRate * 1.5);
            totalPrice += (overdueDay * dailyRate * 1.5);
            totalPrice += (overdueWeek * weeklyRate * 1.5);
        }        
        totalPrice *= TAX_RATE;
        finalPrice = totalPrice;
        return formatter.format(totalPrice);
    }
    
    public double calculateDiscount(int membershipPoints)
    {
        int discountMultiplier = 0;
        if (vehicleCategory.equals("Premium") || vehicleCategory.equals("Full-size") || vehicleCategory.equals("Standard")
                || vehicleCategory.equals("Standard") || vehicleCategory.equals("Mid-size") || vehicleCategory.equals("Compact")
                || vehicleCategory.equals("Economy"))
        {
            discountMultiplier = membershipPoints / 1000;
        }
        else
        {
            discountMultiplier = membershipPoints / 1500;
        }
        return (discountMultiplier * dailyRate);
    }
    
    /**
     * Calculates a vehicle price ONLY given a start date, due date and return date.
     * 
     * If you are making a rent, dueDate = returnDate
     * @param startDate
     * @param dueDate
     * @param returnDate
     * @return
     */
    public String calculateVehiclePrice(Date startDate, Date dueDate)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        if (startDate.after(dueDate))
        {
            return null; //should really throw exceptions here.
        }
        
        double rentalWeek = DateOperations.getWeekDifference(startDate, dueDate);
        double rentalDay = DateOperations.getDayDifference(startDate, dueDate);
        double rentalHours = DateOperations.getHourDifference(startDate, dueDate);
        rentalDay -= (rentalWeek * 7); //subtract any multiple of 7 days.
        
        double totalPrice = (rentalWeek * weeklyRate);
        totalPrice += (rentalDay * dailyRate);
        totalPrice += (rentalHours * hourlyRate);
        
        totalPrice *= TAX_RATE;
        return formatter.format(totalPrice);
    }
    
    /**
     * Wrapper method for estimating rental prices.
     * @param startDate Start date for the rental.
     * @param endDate End date for the rental.
     * @return the price.
     */
    public String estimatePrice(Date startDate, Date endDate)
    {
        return calculateVehiclePrice(startDate, endDate);
    }
    
    /**
     * Estimates the total rental price for a given piece of equipment.
     * @param equipmentID
     * @return
     */
    public String estimateEquipmentPrice(String equipmentID, Date startDate, Date endDate)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        double rentalWeek = DateOperations.getWeekDifference(startDate, endDate);
        double rentalDay = DateOperations.getDayDifference(startDate, endDate);
        double rentalHours = DateOperations.getHourDifference(startDate, endDate);
        double totalPrice = 0.0;
        double equipmentDay = 0.0;
        double equipmentWeek = 0.0;
        rentalDay -= (rentalWeek * 7); //subtract any multiple of 7 days.
        try {
            ResultSet results = Query.select("SELECT dailyRent, weeklyRent FROM equipment WHERE equpimentID='" + equipmentID +"'");
            if (results.next())
            {
                equipmentDay = results.getDouble(1);
                equipmentWeek = results.getDouble(2);
            }
            if (rentalHours > 0 && rentalDay == 0)
            {
                totalPrice += (equipmentDay);
            }
            totalPrice += (rentalWeek * equipmentWeek);
            totalPrice += (rentalDay * equipmentDay);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formatter.format(totalPrice);
        
    }
    
    /**
     * Calculates the insurance rate discount (if selected).
     * @param startDate
     * @param endDate
     * @param returnDate
     * @return
     */
    public double calculateInsuranceDiscount(Date startDate, Date endDate, Date returnDate)
    {
        double rentalWeek = DateOperations.getWeekDifference(startDate, endDate);
        double rentalDay = DateOperations.getDayDifference(startDate, endDate);
        rentalDay -= (rentalWeek * 7); //subtract any multiple of 7 days.
        
        double discount = rentalDay * (insuranceRate / 2);
        discount += rentalWeek * (insuranceRate / 2);
        
        return discount;
    }
        
    /**
     * Checks if the date objects are overdue or not.
     * @param startDate
     * @param returnDate
     * @return true if the rental is overdue, false otherwise.
     */
    private boolean isOverdue(Date dueDate, Date returnDate)
    {
        return returnDate.after(dueDate);
    }
    
    /**
     * Getter for the total price
     */
    public double getPrice()
    {
        return finalPrice;
    }
    
    /**
     * Finds the rates of the equipment that is rented.
     * @param rentID id of the rental which has equipment to rent
     */
    public void getEquipmentRates(String rentID)
    {
        try {
            ResultSet results = Query.select("SELECT * FROM equipment E, EquipmentUsed U WHERE E.equpimentID = U.equipmentID AND "
                    + "U.rentID='" + rentID + "'");
            
            while (results.next())
            {
                if (results.getRow() == 1)
                {
                    equipment1Week = results.getDouble(5);
                    equipment1Daily = results.getDouble(4);
                }
                else if (results.getRow() == 2)
                {
                    equipment2Week = results.getDouble(5);
                    equipment2Daily = results.getDouble(4);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}