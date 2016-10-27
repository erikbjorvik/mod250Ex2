/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import auctionclient.Information;
import javax.xml.ws.WebServiceRef;

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
public class UpdateList implements MessageListener {
    
    public UpdateList() {
        Information.setProducts(getActiveAuctions());
    }
    
    @Override
    public void onMessage(Message message) {
        MapMessage msg = (MapMessage)message;
        Integer productId = null;
        
        try {
            productId = msg.getIntProperty("productId");
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        if(!Information.isEmpty()) {
            System.out.println("Removing product id " + productId + " from list.");
            Information.removeFromList(productId);
        }
        
        else {
            System.out.println("List is empty.");
        }
        
       
        
    }

    private static java.util.List<auctionclient.Product> getActiveAuctions() {
        auctionclient.AuctionWS_Service service = new auctionclient.AuctionWS_Service();
        auctionclient.AuctionWS port = service.getAuctionWSPort();
        return port.getActiveAuctions();
    }
    
    
    
}
