/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author erikbjorvik
 */
public class DateAndTime {
    
    /**
     * Parsing a date string to Date
     * 
     * @param date as a string
     * @return the Date object.
     */
    public static Date getDateObject(String date) {
        
        //The format should be dd.mm.yyyy hh:mm:ss
        
        String[] mainSplit = date.split(" ");
        String datePart = mainSplit[0];
        String timePart = mainSplit[1];
        
        System.out.println("First part: " + datePart);
        System.out.println("Last part: " + timePart);
        
        
        
        String[] dateParts = datePart.split(".");
        String[] timeParts = timePart.split(":");
       
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(dateParts[2]));
        cal.set(Calendar.MONTH, new Integer(dateParts[1]));
        cal.set(Calendar.DAY_OF_MONTH, new Integer(dateParts[0]));
        
        cal.set(Calendar.HOUR_OF_DAY, new Integer(timeParts[0]));
        cal.set(Calendar.MINUTE, new Integer(timeParts[1]));
        cal.set(Calendar.SECOND, new Integer(timeParts[2]));
        
        return cal.getTime();
        
        
        
    }
    
    /**
     * Takes a Date object and converts it to a string.
     * @param date a Date object
     * @return the date as a string
     */
    public static String dateToString(Date date) {
        
        if (date == null)
            return "Malformed date format.";
        
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");     
        return df.format(date);
        
    }
    
}
