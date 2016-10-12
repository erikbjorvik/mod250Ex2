/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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
        if(Session.isLoggedIn()) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            try {
                response.sendRedirect("my-products.xhtml");
            }
            
            catch(Exception e) {
                
            }
            
        }
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
            Session.setId(user.login(getEmail(), getPassword()));
            return "my-products?faces-redirect=true";
        }
        
        else {
            FacesContext.getCurrentInstance().addMessage("myForm:button", new FacesMessage("Invalid username or password. Try again"));
            return "login";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
    
}
