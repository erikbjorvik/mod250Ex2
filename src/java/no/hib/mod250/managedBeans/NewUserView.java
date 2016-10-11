/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hib.mod250.managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import no.hib.mod250.enterpriseBeans.RegisterUser;



/**
 *
 * @author erikbjorvik
 */
@ManagedBean
public class NewUserView {
    
    @EJB
    RegisterUser registerUser;
    
    private int brukerType;
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String passwordRepeat;

    /**
     * Creates a new instance of Bruker
     */
    public NewUserView() {
    }

    public int getBrukerType() {
        return brukerType;
    }

    public void setBrukerType(int brukerType) {
        this.brukerType = brukerType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    
    public String add() {
        if(getPassword().equals(getPasswordRepeat())) {
            registerUser.storeNewUser(this.getFirstname(),
                this.getLastname(),
                this.getEmail(),
                "pass");
        
            return "user-created";
        }
        
        else {
            return "new-user";
        }
        
    }



    
    
  
}
