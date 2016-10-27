/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.entities.Product;
import no.hib.mod250.util.Session;

/**
 *
 * @author erikbjorvik
 */
@ManagedBean
public class YourProductsView {
    
    private List<Product> prodList;
    
    @EJB
    ProductDAO pDao;
    /**
     * Creates a new instance of YourProducts
     * Checks if user is logged in. If not, he/she is redirected to login.xhtml
     */
    public YourProductsView() {
        
        if(!Session.isLoggedIn()) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            try {
                response.sendRedirect("login.xhtml");
            }
            
            catch(Exception e) {
                
            }
            
        }
        
    }

    public List<Product> getProdList() {
        return pDao.getProductsByUser(Session.getId());
    }

    public void setProdList(List<Product> prodList) {
        this.prodList = prodList;
    }
    
    /**
     * Gets current bid for product given by user
     * @param prodId product id
     * @return current bid
     */
    public int getCurrentBidUser(Long prodId) {
        return pDao.getCurrentBid(Session.getId(), prodId);
    }
    
}
