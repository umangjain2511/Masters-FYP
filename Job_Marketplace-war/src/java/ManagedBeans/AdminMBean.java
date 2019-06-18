/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.AdminRemoteEJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

/**
 *
 * @author umangjain
 */
@Named(value = "adminMBean")
@SessionScoped
public class AdminMBean implements Serializable {
    
    @EJB
    AdminRemoteEJB adm;
    
    String username,password,name;
    boolean correct = false;

    /**
     * Creates a new instance of AdminMBean
     */
    public AdminMBean() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    public String searchAdminName() {
        String[] data = adm.searchAdminName(username,password);
        if(data[1]=="correct"){
            correct=true;
            HttpSession session = SessionUtils.getSession();
	    session.setAttribute("username", username);
        }
        else{
            correct=false;
        }
        String uname = (String) data[0];
        return uname;
    }
    
    public void logout(){
        username = "";
        password = "";
        HttpSession session = SessionUtils.getSession();
	session.invalidate();
    }
    
}
