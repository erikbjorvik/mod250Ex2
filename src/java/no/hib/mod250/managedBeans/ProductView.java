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
import javax.servlet.http.HttpServletRequest;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.entities.Product;
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
    
    @EJB
    private ProductDAO pDao;
   

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {
        
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
    
    
    
    public Product getProduct() {
        return pDao.getProductById(this.getId());
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
        
        return !DateAndTime.isThereTimeLeft(DateAndTime.getDateObject(
                        pDao.getProductById(this.getId()).getDeadline()
                    )
                );
        
    }
    
    /**
     * Adds a new bid (Does not work yet)
     * @return String with site to redirect
     */
    public String add() {
        pDao.placeBid(Session.getId(), this.getBid(), productId);
        return "my-products";
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
                        pDao.getProductById(this.getId()).getDeadline()
                    )
                );
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTimeleft() {
        return DateAndTime.timeLeftString(
                    DateAndTime.getDateObject(
                        pDao.getProductById(this.getId()).getDeadline()
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
    
    
    public int getId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        productId = Integer.parseInt(params.get("id"));
        
        return productId;
       
    }
    
    
    
    
    
}
