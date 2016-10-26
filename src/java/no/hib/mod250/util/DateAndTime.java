/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
        try {
        String[] mainSplit = date.split(" ");
        String datePart = mainSplit[0];
        String timePart = mainSplit[1];
        
        String[] dateParts = datePart.split("\\.");
        String[] timeParts = timePart.split(":");
        
        // Some debugging...
        System.out.println("Date part: " + datePart);
        System.out.println("Time part: " + timePart);
        System.out.println("t0->: " + timeParts[0]);
        System.out.println("t1->: " + timeParts[1]);
        System.out.println("t2->: " + timeParts[2]);
        System.out.println("d0->: " + dateParts[0]);
        System.out.println("d1->: " + dateParts[1]);
        System.out.println("d2->: " + dateParts[2]);
    
       
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(dateParts[2]));
        cal.set(Calendar.MONTH, new Integer(dateParts[1])-1);
        cal.set(Calendar.DAY_OF_MONTH, new Integer(dateParts[0]));
        
        cal.set(Calendar.HOUR_OF_DAY, new Integer(timeParts[0]));
        cal.set(Calendar.MINUTE, new Integer(timeParts[1]));
        cal.set(Calendar.SECOND, new Integer(timeParts[2]));
        
        return cal.getTime();
        }
        catch (Exception e) {
            return null;
        }
        
        
        
    }
    
    /**
     * Takes a Date object and converts it to a string.
     * @param date a Date object
     * @return the date as a string
     */
    public static String dateToString(Date date) {
        
        //Debuging...
        System.out.println("Incoming date is " +date);
        
        if (date == null)
            return "Malformed date format.";
        
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");     
        return df.format(date);
        
    }
    
    /**
     * Calculates how much time is left until the given Date object.
     * @param date 
     * @return The time left returned in milliseconds, long.
     */
    public static long timeLeft(Date date) {
        
        try {
        Date current = new Date();
        
        //This is the difference in millisec
        return date.getTime() - current.getTime(); 
        }
        catch (Exception e) {
            return 0;
        }
       
    }
    
    /**
     * Boolean to check if there is time left
     * @param date current date
     * @return true/false based on if there is time left
     */
    public static boolean isThereTimeLeft(Date date) {
        Date current = new Date();
        
        try {
            return date.after(current);
        }
        catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Returns how much time is left of auction
     * @param date date of deadline
     * @return String of time left
     */
    public static String timeLeftString(Date date) {
        
        long diff = timeLeft(date);
        
        if (diff<=0)
            return "The auction for this product is now closed.";
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(diff));
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        
        int month = calendar.get(Calendar.MONTH);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        
        
     /*   
        long days = TimeUnit.DAYS.toSeconds(diff);
        long hours = TimeUnit.HOURS.toSeconds(diff) % 60;
        long minutes = TimeUnit.MINUTES.toSeconds(diff) % 60;
        long seconds = TimeUnit.SECONDS.toSeconds(diff) % 60;
      */
             
        return days + " days, " + hours + " hours, " + minutes + " minutes and " +
                seconds + " seconds.";
        
    }
    
}
