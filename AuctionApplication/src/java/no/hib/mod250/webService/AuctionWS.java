/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.webService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import no.hib.mod250.enterpriseBeans.ProductDAO;
import no.hib.mod250.enterpriseBeans.UserDAO;
import no.hib.mod250.entities.Bid;
import no.hib.mod250.entities.Product;
import no.hib.mod250.util.DateAndTime;

/**
 *
 * @author erikbjorvik
 */
@WebService(serviceName = "AuctionWS")
@Stateless()
public class AuctionWS {

    @EJB
    private ProductDAO pDAO;
    
    @EJB
    private UserDAO uDAO;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getActiveAuctions")
    public List<Product> getActiveAuctions() {
     
        List<Product> activeOnes = new ArrayList<Product>();
        
        for (Product p : pDAO.getAllProducts() ) {
            
            Date date = DateAndTime.getDateObject(p.getDeadline());
            
            // Only add the entry to the return-list if its still time left.
            if (DateAndTime.isThereTimeLeft(date))
                activeOnes.add(p);
            
        }
        
       
        return activeOnes;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "bidForAuction")
    public String bidForAuction(@WebParam(name = "bid") Bid bid) {
        
        
        // First, we should check the input validity.
        
        // Does the user exist?
        if (!uDAO.doesUserExist(bid.getUserId()))
            return "This user does not exist!";
        
        //Product theProduct = pDAO.getProductById(productId);
        
        try {
            int curBid = pDAO.getHighestBid(bid.getProductId());
            if (curBid >= bid.getSum() || curBid<0 )
                return "Must be a valid product and bid needs to be higher then the current bid.";
        }
        catch (Exception e) {
            //...
        }
            
        pDAO.placeBid(bid);
        return "The bid was placed!";
        
    }
}
