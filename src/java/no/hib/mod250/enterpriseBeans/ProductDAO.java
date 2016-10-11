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
import no.hib.mod250.entities.Product;
import no.hib.mod250.entities.User;

/**
 *
 * @author erikbjorvik
 */

@Stateless
public class ProductDAO {
    
    @PersistenceContext(unitName = "ex2PU")
    private EntityManager em;
    
    public void storeNewProduct(String name, String description, String features, 
            String deadline) {
        
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setFeatures(features);
        product.setDeadline(deadline);
        product.setRating(0);
        
        em.persist(product);
        
    }
    
    
}
