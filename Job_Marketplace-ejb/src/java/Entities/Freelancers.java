/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umangjain
 */
@Entity
@Table(name = "FREELANCERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freelancers.findAll", query = "SELECT f FROM Freelancers f")
    , @NamedQuery(name = "Freelancers.findById", query = "SELECT f FROM Freelancers f WHERE f.id = :id")
    , @NamedQuery(name = "Freelancers.findByName", query = "SELECT f FROM Freelancers f WHERE f.name = :name")
    , @NamedQuery(name = "Freelancers.findByUsername", query = "SELECT f FROM Freelancers f WHERE f.username = :username")
    , @NamedQuery(name = "Freelancers.findByPassword", query = "SELECT f FROM Freelancers f WHERE f.password = :password")
    , @NamedQuery(name = "Freelancers.findBySkills", query = "SELECT f FROM Freelancers f WHERE f.skills = :skills")
    , @NamedQuery(name = "Freelancers.findByBalance", query = "SELECT f FROM Freelancers f WHERE f.balance = :balance")
    , @NamedQuery(name = "Freelancers.findByDescription", query = "SELECT f FROM Freelancers f WHERE f.description = :description")})
public class Freelancers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SKILLS")
    private String skills;
    @Column(name = "BALANCE")
    private Integer balance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPTION")
    private String description;

    public Freelancers() {
    }

    public Freelancers(Integer id) {
        this.id = id;
    }

    public Freelancers(Integer id, String name, String username, String password, String skills, String description) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.skills = skills;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Freelancers)) {
            return false;
        }
        Freelancers other = (Freelancers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Freelancers[ id=" + id + " ]";
    }
    
}
