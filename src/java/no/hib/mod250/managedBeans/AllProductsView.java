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
import jms.SendMessage;
import jms.SubscriberClient;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.entities.Product;
import no.hib.mod250.entities.Bid;
import no.hib.mod250.util.Session;

/**
 *
 * @author haava
 */
@ManagedBean
public class AllProductsView {
    
    private List<Product> prodList;
    
    @EJB
    ProductDAO pDao;

    /**
     * Creates a new instance of AllProductsView
     */
    public AllProductsView() {
    }
    
    public List<Product> getProdList() {
        SendMessage message = new SendMessage();
        SubscriberClient sub = new SubscriberClient();
        message.publish("Jens", "Stoltenberg", 15, "Sko");
        return pDao.getAllProducts();
    }

    public void setProdList(List<Product> prodList) {
        this.prodList = prodList;
    }
    
}
