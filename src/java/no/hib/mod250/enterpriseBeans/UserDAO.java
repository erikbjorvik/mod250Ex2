/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.enterpriseBeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import no.hib.mod250.entities.User;

/**
 *
 * @author erikbjorvik
 */

@Stateless
public class UserDAO {
    
    @PersistenceContext(unitName = "ex2PU")
    private EntityManager em;
    
    /**
     * Stores new user in the database
     * @param firstname user's firsname
     * @param lastname user's lastname
     * @param email user's email
     * @param password user's password
     */
    public void storeNewUser(String firstname, String lastname,
    String email, String password) {
        
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPasshash(password);
        
      
        em.persist(user);
        
    }
    
    /**
     * Finds user in database to validate login credentials
     * @param email the user's email
     * @param password the user's password
     * @return user-id, or -1 if not valid
     */
    public Long login(String email, String password) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.passhash = :passhash");
        List<User> resultList = query.setParameter("email", email).setParameter("passhash", password).getResultList();
        if(resultList.isEmpty()) {
            return -1L;
        }
        
        else {
            return resultList.get(0).getId();
        }
    }
    
    public boolean doesUserExist(long id) {
        
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
        List<User> resultList = query.setParameter("id",id ).getResultList();
        
        return !resultList.isEmpty();
            
    }
    
    
}
