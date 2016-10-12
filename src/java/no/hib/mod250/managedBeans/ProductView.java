/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.entities.Product;
import no.hib.mod250.util.DateAndTime;

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
    
    @EJB
    private ProductDAO pDao;

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {
        
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

    public void setProductId(int productId) {
        this.productId = productId;
    }

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
    
    
    
    public Long getId() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String id = request.getParameter("id");
        return Long.parseLong(id);
       
    }
    
    
    
    
    
}
