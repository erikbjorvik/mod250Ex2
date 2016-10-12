package no.hib.mod250.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import no.hib.mod250.util.DateAndTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Math.toIntExact;

/**
 *
 * @author erikbjorvik
 */
public class DateAndTimeTest {
    
    public DateAndTimeTest() {
    }
    
    @Test
    public void testGetDateObject() {
        
        String date1 = "24.10.2016 22:29:45";
        String date2 = "27.06.2016 01:39:25";
        String date3 = "01.01.2016 01:39:25";
        
        // Check that parsing are working
        assertEquals(date3, DateAndTime.dateToString(DateAndTime.getDateObject(date3)));
        assertEquals(date1, DateAndTime.dateToString(DateAndTime.getDateObject(date1)));
        assertEquals(date2, DateAndTime.dateToString(DateAndTime.getDateObject(date2)));
        
        
    }
    
    @Test
    public void testTimeLeft() {
        

        boolean t1 = DateAndTime.isThereTimeLeft(DateAndTime.getDateObject("29.11.2016 23:59:59"));
        boolean t2 = DateAndTime.isThereTimeLeft(DateAndTime.getDateObject("24.10.2000 22:29:45"));
        
        
        // Check that parsing are working
        assertTrue(t1);
        assertFalse(t2);
        
        
        
    }
            
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private String Date() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
