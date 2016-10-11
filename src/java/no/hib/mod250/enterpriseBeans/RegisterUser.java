/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.enterpriseBeans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import no.hib.mod250.entities.User;

/**
 *
 * @author erikbjorvik
 */

@Stateless
public class RegisterUser {
    
    @PersistenceContext(unitName = "ex2PU")
    private EntityManager em;
    
    public void storeNewUser(String firstname, String lastname,
    String email, String password) {
        
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        
      
        em.persist(user);
        
    }
    
    public boolean login(String email, String password) {
        
    }
    
    
}
