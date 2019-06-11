/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Entities.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author umangjain
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminRemoteEJB {

    @PersistenceContext(unitName = "Job_Marketplace-ejbPU")
    private EntityManager em;

    @Override
    public List<Admin> searchAdmin(String username) {
        Query query = em.createNamedQuery("Admin.findByUsername").setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public String[] searchAdminName(String username, String password) {
        String [] data = new String[2];
        try{
            Query query = em.createNamedQuery("Admin.findByUsername").setParameter("username", username);
            Admin adm = (Admin)query.getResultList().get(0);
            data[0] = adm.getName();
            if(adm.getPassword().equals(password)){
                data[1] = "correct"; 
            }
            else{
                data[1] = "incorrect";
            }
        }
        catch(Exception e){
            data[0] = "Error";
            data[1] = "incorrect";
        }
        return data;
        }

    public void persist(Object object) {
        em.persist(object);
    }

     
}
