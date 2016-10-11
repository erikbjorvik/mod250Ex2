/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import no.hib.mod250.enterpriseBeans.RegisterUser;

@MangedBean
public class LoginView {
    
    String username;
    String password;

    /**
     * Creates a new instance of LoginView
     */
    public LoginView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
