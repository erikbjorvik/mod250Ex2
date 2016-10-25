/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author haava
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/myTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/myTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class NotifyBuyer implements MessageListener {
    
    public NotifyBuyer() {
    }
    
    @Override
    public void onMessage(Message message) {
        MapMessage msg = (MapMessage) message;
        String firstName = "";
        String lastName = "";
        Integer productId = null;
        String productName = "";
        
        try {
            firstName = msg.getString("firstname");
            lastName = msg.getString("lastname");
            productId = msg.getInt("productId");
            productName = msg.getString("productName");
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        System.out.println("---- START EMAIL to customer " + firstName + " " + lastName + " ----");
        System.out.println("Dear " + firstName + " " + lastName);
        System.out.println("Congratulations! You have won in bidding for product " + productName + ".");
        System.out.println("You can access the product using the following link:");
        System.out.println("URL=http://localhost:8080/faces/product?id=" + productId);
        System.out.println("---- END EMAIL to customer " + firstName + " " + lastName + " ----");
    }
    
}
