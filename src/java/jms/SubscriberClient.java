/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

/**
 *
 * @author haava
 */
public class SubscriberClient {
    public SubscriberClient() {
        try {
            InitialContext context = new InitialContext();
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("jms/myTopicFactory");
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber topicSubscriber = topicSession.createSubscriber((Topic)context.lookup("jms/myTopic"));
            NotifyBuyer topicListener = new NotifyBuyer();
                topicSubscriber.setMessageListener(topicListener);
                topicConnection.start();
            }

            catch(Exception e) {
                e.printStackTrace();
            }
    }
}
