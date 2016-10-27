/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import java.util.List;
import java.util.Iterator;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author haava
 */
public class Information {
    
    public static List<Product> products;

    public static List<Product> getProducts() {
        return Information.products;
    }
    
    public static boolean isEmpty() {
        return Information.products == null;
    }

    public static void setProducts(List<Product> products) {
        Information.products = products;
    }
    
    public static void removeFromList(int id) {
        Iterator<Product> i = Information.products.iterator();
        while(i.hasNext()) {
            Product p = i.next();
            if(p.getId() == id) {
                i.remove();
            }
        }
        
    }
   
}
