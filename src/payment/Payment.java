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
    private static final double TAX_RATE = 1.12;
    private double weeklyRate;
    private double hourlyRate;
    private double dailyRate;
    
    /**
     * Payment constructor. Takes a vehicle ID,
     * and retrieves it's hourly, weekly and daily rates.
     */
    public Payment(String vehicleID)
    {
        try
        {
            ResultSet prices = Query.select("SELECT hourlyPrice, dailyPrice, weeklyPrice FROM rentalrates WHERE rent_category IN "
                    + "(SELECT rent_category FROM Vehicle_Category WHERE vehicleID = ' "+ vehicleID + " '");
            
            hourlyRate = prices.getDouble(1);
            dailyRate = prices.getDouble(2);
            weeklyRate = prices.getDouble(3);
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
    public String calculatePrice(Date startDate, Date dueDate, Date returnDate)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        if (startDate.after(dueDate) || startDate.after(returnDate))
        {
            return null; //should really throw exceptions here.
        }
        
        int rentalWeek = DateOperations.getWeekDifference(startDate, dueDate);
        int rentalDay = DateOperations.getDayDifference(startDate, dueDate);
        rentalDay -= (rentalWeek * 7); //subtract any multiple of 7 days.
        
        double totalPrice = rentalWeek * weeklyRate;
        totalPrice += rentalDay * dailyRate; 
        
        if (isOverdue(dueDate, returnDate))
        {
            int overdueHours = DateOperations.getHourDifference(dueDate, returnDate);
            int overdueDay = DateOperations.getDayDifference(dueDate, returnDate);
            int overdueWeek = DateOperations.getWeekDifference(dueDate, returnDate);
            overdueDay -= (overdueWeek * 7);
            
            totalPrice += overdueHours * hourlyRate;
        }
        
        totalPrice *= TAX_RATE;
        
        return formatter.format(totalPrice);
        
    }
    
    /**
     * Checks if the date objects are overdue or not.
     * @param startDate
     * @param returnDate
     * @return true if the rental is overdue, false otherwise.
     */
    private boolean isOverdue(Date dueDate, Date returnDate)
    {
        return dueDate.after(returnDate);
    }
}
