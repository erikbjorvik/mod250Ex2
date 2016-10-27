/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import no.hib.mod250.enterpriseBeans.ProductDAO;

/**
 *
 * @author erikbjorvik
 */
@ManagedBean
public class NewProductView {
    
    @EJB
    ProductDAO pDAO;
    
    private String name;
    private String description;
    private String features;
    private String deadline;
    

    /**
     * Creates a new instance of NewProductView
     */
    public NewProductView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }


    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    
    public String add() {
        
        pDAO.storeNewProduct(this.getName(), this.getDescription(), this.getFeatures(),
                this.getDeadline());
        
        return "my-products";
        
    }
    
    
}
