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
    public String getEmail() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
    }
    
    public void setEmail(String email) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", email);
    }
}
