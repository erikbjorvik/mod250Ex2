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
    public static Long getId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
    }
    
    public static void setId(Long id) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", id);
    }
    
    public static boolean isLoggedIn() {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id") != null) {
            return true;
        }
        else {
            return false;
        }
    }
}