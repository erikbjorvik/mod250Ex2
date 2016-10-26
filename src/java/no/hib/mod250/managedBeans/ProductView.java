/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jms.SendMessage;
import jms.SubscriberClient;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.enterpriseBeans.UserDAO;
import no.hib.mod250.entities.Bid;
import no.hib.mod250.entities.Product;
import no.hib.mod250.entities.User;
import no.hib.mod250.util.DateAndTime;
import no.hib.mod250.util.Session;

/**
 *
 * @author erikbjorvik
 */
@ManagedBean
public class ProductView {
    
    private Product product;
    private int productId;
    private String deadline;
    private String timeleft;
    private boolean bidActive;
    private int bid;
    private String id;
    private int currentBid;
    
    private String actionId;
    
    @EJB
    private ProductDAO pDao;
    
    @EJB
    private UserDAO userDao;
   

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {
    }

    public String getId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
 
        if(params.get("id") != null) {
            return params.get("id");
        }
        else {
            return id;
        }
    }

    public int getCurrentBid() {
        finished();
        return pDao.getHighestBid(Long.parseLong(this.getId()));
        
     }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }
    
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
    
    
    
    public Product getProduct() {
        return pDao.getProductById(Integer.parseInt(this.getId()));
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    /**
     * Return true/false based on if product is active
     * @return if it's time left
     */
    public boolean getBidActive() {
        
        System.out.println(this.getId());
        
        return !DateAndTime.isThereTimeLeft(DateAndTime.getDateObject(
                        pDao.getProductById(Integer.parseInt(this.getId())).getDeadline()
                    )
                );
        
    }
    
    public void finished() {
        Product p = pDao.getProductById(Long.parseLong(this.getId()));
        
        try {
            if(p.isActive() && !DateAndTime.isThereTimeLeft(DateAndTime.getDateObject(pDao.getProductById(Integer.parseInt(this.getId())).getDeadline()))) {
                pDao.makeInactive(p);
                Bid b = pDao.getHighestBidObject(Long.parseLong(this.getId()));
                System.out.println("User id: " + b.getUserId());
                User user = userDao.getUser(b.getUserId());

                System.out.println("Inside finished()");

                SendMessage message = new SendMessage();
                SubscriberClient sub = new SubscriberClient();
                message.publish(user.getFirstname(), user.getLastname(), Integer.parseInt(this.getId()), this.getProduct().getName());
            }
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    /**
     * Adds a new bid (Does not work yet)
     * @return String with site to redirect
     */
    public String add() {
        
        Bid bid = new Bid();
        bid.setUserId(Session.getId()); 
        bid.setProductId(Long.parseLong(this.getId())); 
        bid.setSum( this.getBid());
        
        pDao.placeBid(bid);
        return "index";
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Get deadline
     * @return deadline
     */
    public String getDeadline() {
        return DateAndTime.dateToString(
                    DateAndTime.getDateObject(
                        pDao.getProductById(Integer.parseInt(this.getId())).getDeadline()
                    )
                );
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTimeleft() {
        return DateAndTime.timeLeftString(
                    DateAndTime.getDateObject(
                        pDao.getProductById(Integer.parseInt(this.getId())).getDeadline()
                    )
                );
    }

    public void setTimeleft(String timeleft) {
        this.timeleft = timeleft;
    }

    public ProductDAO getpDao() {
        return pDao;
    }

    public void setpDao(ProductDAO pDao) {
        this.pDao = pDao;
    }
    
    
    /*public int getId() {
        
       
    }*/
    
    
    
    
    
}
