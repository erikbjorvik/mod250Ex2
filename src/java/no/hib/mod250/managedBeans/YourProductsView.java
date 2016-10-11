/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.entities.Product;

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
     */
    public YourProductsView() {
        

        
    }

    public List<Product> getProdList() {
        return pDao.getProductsByUser(0);
    }

    public void setProdList(List<Product> prodList) {
        this.prodList = prodList;
    }



    

    
    
    
}
