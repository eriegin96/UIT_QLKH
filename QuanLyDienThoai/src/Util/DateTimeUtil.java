package Util;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Utility class for handling date and time formatting
 * Provides consistent date/time handling across the application
 */
public class DateTimeUtil {
    
    // Format for displaying dates to users (Vietnamese format)
    public static final SimpleDateFormat DISPLAY_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    // Format for database TIMESTAMP (MySQL format)
    public static final SimpleDateFormat DB_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // Format for date picker (without time)
    public static final SimpleDateFormat DATE_PICKER_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Converts a Date object to database TIMESTAMP format string
     * @param date The date to convert
     * @return String in format "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatForDatabase(Date date) {
        if (date == null) {
            return DB_FORMAT.format(new Date()); // Return current timestamp if null
        }
        return DB_FORMAT.format(date);
    }
    
    /**
     * Converts a Date object to display format string
     * @param date The date to convert
     * @return String in format "dd/MM/yyyy HH:mm"
     */
    public static String formatForDisplay(Date date) {
        if (date == null) {
            return "";
        }
        return DISPLAY_FORMAT.format(date);
    }
    
    /**
     * Converts a Timestamp to display format string
     * @param timestamp The timestamp to convert
     * @return String in format "dd/MM/yyyy HH:mm"
     */
    public static String formatForDisplay(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        return DISPLAY_FORMAT.format(new Date(timestamp.getTime()));
    }
    
    /**
     * Parses a display format string to Date object
     * @param dateString String in format "dd/MM/yyyy HH:mm" or "dd/MM/yyyy"
     * @return Date object or null if parsing fails
     */
    public static Date parseDisplayFormat(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            // Try with time first
            return DISPLAY_FORMAT.parse(dateString);
        } catch (Exception e) {
            try {
                // Try without time
                return DATE_PICKER_FORMAT.parse(dateString);
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    /**
     * Parses a database format string to Date object
     * @param dateString String in format "yyyy-MM-dd HH:mm:ss"
     * @return Date object or null if parsing fails
     */
    public static Date parseDatabaseFormat(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            return DB_FORMAT.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Gets current timestamp as a Timestamp object
     * @return Current Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * Converts Date picker date (without time) to database format with current time
     * @param date Date from date picker
     * @return String in format "yyyy-MM-dd HH:mm:ss" with current time
     */
    public static String datePickerToDatabase(Date date) {
        if (date == null) {
            return DB_FORMAT.format(new Date());
        }
        // Use current time with the selected date
        return DB_FORMAT.format(date);
    }
}
