/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author haava
 */

public class SendMessage {
    /**
     * Creates a new instance of SendMessage
     */
    public SendMessage() {
        
    }
    
    public void publish(String firstName, String lastName, Integer productId, String productName) {
        try {
            // Creating context, topic and topic factory
            InitialContext context = new InitialContext();
            Topic topic = (Topic) context.lookup("jms/myTopic");
            TopicConnectionFactory tcf = (TopicConnectionFactory) context.lookup("jms/myTopicFactory");
           
            
            // Creating topic connection
            TopicConnection topicConnection = tcf.createTopicConnection();
            
            // Creating topic session
            TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // Creating topic publisher
            TopicPublisher topicPublisher = topicSession.createPublisher(topic);
            topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            
            // Constructing message
            MapMessage message = topicSession.createMapMessage();
            message.setStringProperty("firstName", firstName);
            message.setStringProperty("lastName", lastName);
            message.setIntProperty("productId", productId);
            message.setStringProperty("productName", productName);
            
            // Publishing the message
            topicPublisher.publish(message);
            
            // Closing t
            topicConnection.close();
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
       
    }
    
}
