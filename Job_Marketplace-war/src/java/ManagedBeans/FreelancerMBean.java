/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EJBs.FreelancerRemoteEJB;
import EJBs.JobRemoteEJB;
import Entities.Freelancers;
import Entities.Jobs;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author umangjain
 */
@Named(value = "freelancerMBean")
@SessionScoped
public class FreelancerMBean implements Serializable {
    
    @EJB
    FreelancerRemoteEJB freeBean;
    
    @EJB
    JobRemoteEJB job;
    
    private String username,password,name,description,skills;
    private int balance,id,jobid;
    private boolean canApply = true;
    private boolean isClosed = false;
    private boolean correct = false;
    

    /**
     * Creates a new instance of FreelancerMBean
     */
    public FreelancerMBean() {
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isCorrect() {
        return correct;
    }
    

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isCanApply() {
        return canApply;
    }

    public void setCanApply(boolean canApply) {
        this.canApply = canApply;
    }
    
    
           
    public List<Freelancers> searchFreelancers() {
        return freeBean.searchFreelancers();
    }
    
    public List<Freelancers> searchFreelancersName() {
        List<Freelancers> fl = freeBean.searchFreelancersName(username);
        Freelancers frl = fl.get(0);
        name = frl.getName();
        description = frl.getDescription();
        skills = frl.getSkills();
        password = frl.getPassword();    
        return fl;
    }
    
    public String getFreelancerName() {
        String[] data = freeBean.getFreelancerName(username,password);
        if(data[1]=="correct"){
            correct=true;
        }
        else{
            correct=false;
        }
        String uname = (String) data[0];
        return uname;
    }
    
    public int getFreelancerBalance() {
        return freeBean.getFreelancerBalance(username);
    }
    
    public void updateFreelancer() {
        freeBean.updateFreelancer(name, skills, description, username, password);
    }
    
    public void delete(Freelancers fl) {
        freeBean.delete(fl.getUsername());
    }
    
    public void applyJob(Jobs j) {
        canApply=false;
        freeBean.applyJob(j.getId(), username);
    }
    
    public void revokeJob(Jobs j) {
        canApply = true;
        freeBean.revokeJob(j.getId(), username);
    }
    
    public void storeJobId(Jobs j) {
        jobid = j.getId();
    }
    
    public void logout() {
        username = "";
        password = "";
    }
    
    public List<Jobs> singleJob() {
        List<Jobs> j = job.searchJobsByID(jobid);
        String unames = j.get(0).getFlnames();
        String status = j.get(0).getStatus();
        if(status.equals("Closed") || status.equals("Completed")){
            canApply=false;
            isClosed=true;
            return j;
        }
        if(unames==null){
            canApply=true;
            isClosed=false;
            return j;
        }
        else if(unames.contains(username)){
            canApply=false;
            isClosed=false;
            return j;
        }
        else{
            canApply=true;
            isClosed=false;
            return j;
        }
    }
    
    public List<Jobs> jobAssToMe() {
        return job.jobAssToMe(username);
    }
    
}
