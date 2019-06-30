/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.JobRemoteEJB;
import EJBs.ProviderRemoteEJB;
import Entities.Freelancers;
import Entities.Jobs;
import Entities.Provider;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

/**
 *
 * @author umangjain
 */
@Named(value = "providerMBean")
@SessionScoped
public class ProviderMBean implements Serializable {
    
    @EJB
    ProviderRemoteEJB provBean;
    
    @EJB
    JobRemoteEJB job;
    
    private String username,password,name,description,title,keywords;
    int pay,jobid;
    private boolean correct = false;
    private String old_password,new_password;
    private boolean passChange=false;

    /**
     * Creates a new instance of ProviderMBean
     */
    public ProviderMBean() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }     

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public boolean isPassChange() {
        return passChange;
    }

    public void setPassChange(boolean passChange) {
        this.passChange = passChange;
    }
    
    
     
    public List<Provider> searchProviders() {
        return provBean.searchProviders();                
    }
    
    public String getProviderName() {
        String[] data = provBean.getProviderName(username,password);
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
    
    public void delete(Provider pr) {
        provBean.delete(pr.getUsername());
    }
    
    public List<Jobs> providerJob() {
        return job.searchProviderJob(username);
    }
    
    public void createJob() {
        keywords = keywords.toLowerCase();
        provBean.createJob(title, description, pay, keywords, username);
    }
    
    public List<Jobs> showJobApplicants() {
        return job.showOpenJobs(username);
    }
    
    public List<Freelancers> seeApplicants() {
        return job.seeApplicants(jobid);
    }
    
    public void storeJobId(Jobs j) {
        jobid = j.getId();
    }
    
    public void jobAssign(Freelancers fl) {
        job.jobAssign(jobid, fl.getUsername());
    }
    
    public void logout() {
        username = "";
        password = "";
        HttpSession session = SessionUtils.getSession();
	session.invalidate();
    }
    
    public List<Jobs> providerAssJob() {
        return job.showAssJobs(username);
    }
    
    public void complete(Jobs j) {
        provBean.complete(j.getId());
    }
    
    public void changePass() {
        passChange=provBean.changePass(old_password, new_password, username);
        if(passChange)
            password=new_password;
    }
           
}
