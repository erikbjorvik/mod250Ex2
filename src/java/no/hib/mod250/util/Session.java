/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author haava
 */
public class Session {
    
    /**
     * Get user id of logged in user
     * @return the user id
     */
    public static Long getId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
    }
    
    /**
     * Set user id of logged in user
     * @param id from database
     */
    public static void setId(Long id) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);
    }
    
    /**
     * Checks if user is logged in
     * @return true/false based on if user is logged in
     */
    public static boolean isLoggedIn() {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id") != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
