/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import no.hib.mod250.entities.Product;

/**
 *
 * @author erikbjorvik
 */
@ManagedBean
public class ProductView {
    
    private Product product;
    private int productId;

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {
        
    }
    
    public Product getProduct() {
        return product;
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
    
    public Long getId() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance(); // Hva er FacesContext?
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String id = request.getParameter("id");
        return Long.parseLong(id);
       
    }
    
    
    
    
    
}
