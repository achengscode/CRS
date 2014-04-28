package payment;

import java.sql.Date;

/**
 * Simple method to calculate the difference between dates.
 * 
 * @invariant SECONDS_IN_DAY = 3600 * 24
 * @invariant SECONDS_IN_HOUR = 3600
 * @author Aaron Cheng
 *
 */
public class DateOperations {

    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    
    public static final int SECONDS_IN_HOUR = 60 * 60;
    
    /**
     * Calculate the difference between the dates between the given dates.
     * Does not take into account the number of weeks that have passed, i.e., 
     * calling getDayDifference() and getWeekDifference() will result in the same
     * time period being calculated.
     * 
     * I.e. if getWeekDifference() returns 3 (meaning three weeks have passed), 
     * getDayDifference() will return 21 (meaning 21 days have passed). Both refer to 
     * the same rental, so only one rate needs to be taken into account.
     * 
     * @pre first < second
     * @param first The first date
     * @param second The second date
     * @return The number of days which have passed between the first and second dates.
     */
    public static int getDayDifference(Date first, Date second)
    {
        //the difference in milliseconds
        long millisecDiff = second.getTime() - first.getTime();
        long seconds = millisecDiff / 1000; //second difference  
        long days = seconds / SECONDS_IN_DAY; //how many days
        return (int)days;
    }
    
    /**
     * Calculate the weeks that are different between the two given dates.
     * @pre first < second
     * @param first
     * @param second
     * @return The number of weeks which have passed between the first and second dates
     */
    public static int getWeekDifference(Date first, Date second)
    {
        long days = DateOperations.getDayDifference(first, second);
        int weeks = (int)days / 7;
        return weeks;
    }
    
    /**
     * Calculate the hours that are different between the two given dates.
     * Takes into account the number of days that have passed, and will
     * only return a number between 0-23.
     * 
     * @pre first < second
     * @param first The first date
     * @param second The second date
     * @return The hours that are different between the first and second dates.
     */
    public static int getHourDifference(Date first, Date second)
    {
        long millisecondDiff = second.getTime() - first.getTime();
        long dayMillisec = SECONDS_IN_DAY * 1000;
        long hourMillisec = SECONDS_IN_HOUR * 1000;
        int dayDiff = DateOperations.getDayDifference(first, second);
        dayMillisec *= dayDiff; 
        
        millisecondDiff -= dayMillisec; //subtract the number of days
        millisecondDiff /= hourMillisec; //divide by number of milliseconds in an hour
        
        return (int) millisecondDiff; 
        
    }
}
