/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.enterpriseBeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import no.hib.mod250.entities.Bid;
import no.hib.mod250.entities.Product;
import no.hib.mod250.util.Session;

/**
 *
 * @author erikbjorvik
 */

@Stateless
public class ProductDAO {
    
    @PersistenceContext(unitName = "ex2PU")
    private EntityManager em;
    
    /**
     * Adds new product to database
     * @param name the name of the product
     * @param description description of the product
     * @param features product's features
     * @param deadline deadline of auction
     */
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
    
    /**
     * Gets all products for user
     * @param userId the id of the user
     * @return Get list of products
     */
    public List<Product> getProductsByUser(long userId) {
        Query query = em.createQuery("SELECT u FROM Product u WHERE u.sellerId = :userId");
        return query.setParameter("userId", userId).getResultList();
    }
    
    /**
     * Get all products
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT u FROM Product u");
        return query.getResultList();
    }
    
    public boolean doesProductExist(long productId) {
        
        Query query = em.createQuery("SELECT COUNT(u) FROM Product u WHERE u.id = :productId");
        int nrOfRows = (int) query.setParameter("productId", productId).getResultList().get(0);
        
        return nrOfRows>0;
        
    }
    
    /**
     * Get product by product-id
     * @param productId product-id
     * @return product-object
     */
    public Product getProductById(long productId) {
        Query query = em.createQuery("SELECT u FROM Product u WHERE u.id = :productId");
        Product p;
        
        try {
            p = (Product) query.setParameter("productId", productId).getResultList().get(0);
        }
        catch (NullPointerException e) {
            p = null;
        }
        
        return p;
        
    }
    
    /**
     * Places bid for product
     * @param userId id of user
     * @param productId id of product
     * @param sum bidding sum
     */
    public void placeBid(Bid bid) {
        em.persist(bid);
    }
    
    /**
     * Returns highest bid for product
     * @param productId id of product
     * @return Highest bid
     */
    public int getHighestBid(long productId) {
        
        try {
        Query query = em.createQuery("SELECT MAX(b.sum) FROM Bid b WHERE b.productId = :productId");
        return (int) query.setParameter("productId", productId).getResultList().get(0);
        }
        catch(Exception e) {
            return 0;
        }
        
    }
    
    /**
     * Current bid based on user id and product
     * @param userId id of user
     * @param productId id of product
     * @return current bid
     */
    public int getCurrentBid(long userId, long productId) {
          
        try {
        Query query = em.createQuery("SELECT MAX(b.sum) FROM Bid b WHERE b.productId = :productId AND b.userId =:userId");
        return (int) query.setParameter("productId", productId).setParameter("userId", userId).getResultList().get(0);
        }
        catch(Exception e) {
            return 0;
        }
    }
    
    
    
}
