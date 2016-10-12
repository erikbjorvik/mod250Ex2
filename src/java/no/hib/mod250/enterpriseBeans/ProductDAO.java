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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import no.hib.mod250.entities.Bid;
import no.hib.mod250.entities.Product;
import no.hib.mod250.entities.User;
import no.hib.mod250.util.Session;

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
        product.setSellerId(Session.getId());
        
        em.persist(product);
        
    }
    
    public List<Product> getProductsByUser(long userId) {
        Query query = em.createQuery("SELECT u FROM Product u WHERE u.sellerId = :userId");
        return query.setParameter("userId", userId).getResultList();
    }
    
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT u FROM Product u");
        return query.getResultList();
    }
    
    public Product getProductById(long productId) {
        Query query = em.createQuery("SELECT u FROM Product u WHERE u.id = :productId");
        return (Product) query.setParameter("productId", productId).getResultList().get(0);
    }
    
    public void placeBid(long userId, long productId, int sum) {
        Bid bid = new Bid();
        bid.setSum(sum);
        bid.setProductId(productId);
        bid.setUserId(userId);
        
        em.persist(bid);
    }
    
    public Bid getHighestBid(long productId) {
        Query query = em.createQuery("SELECT MAX(b.sum) FROM Bid b WHERE b.productId = :productId");
        return (Bid) query.setParameter("productId", productId).getResultList().get(0);
    }
    
    public int getCurrentBid(long userId, long productId) {
        Query query = em.createQuery("SELECT MAX(b.sum) FROM Bid b WHERE b.productId = :productId AND b.userId = :userId");
        return query.setParameter("productId", productId).setParameter("userId", userId).getFirstResult();
    }
    
    
    
}
