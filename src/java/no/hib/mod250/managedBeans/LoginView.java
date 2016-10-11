/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import no.hib.mod250.enterpriseBeans.UserDAO;
import no.hib.mod250.util.Session;

@ManagedBean
public class LoginView {
    
    @EJB
    UserDAO user;
    
    String email;
    String password;

    /**
     * Creates a new instance of LoginView
     */
    public LoginView() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        if(user.login(getEmail(), getPassword()) != -1) {
            Session session = new Session();
            session.setId(user.login(getEmail(), getPassword()));
            return "control-panel";
        }
        
        else {
            return "login?error=1";
        }
    }
    
}
