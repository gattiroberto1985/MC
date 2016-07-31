package org.bob.web.applications.mycash.engine.hc.dao;
// Generated 31-lug-2016 16.50.26 by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private int id;
     private Serializable username;
     private Serializable salt;
     private Serializable password;
     private Date tmstIns;
     private Date tmstUpd;
     private Set portfolioses = new HashSet(0);

    public Users() {
    }

	
    public Users(int id, Date tmstIns, Date tmstUpd) {
        this.id = id;
        this.tmstIns = tmstIns;
        this.tmstUpd = tmstUpd;
    }
    public Users(int id, Serializable username, Serializable salt, Serializable password, Date tmstIns, Date tmstUpd, Set portfolioses) {
       this.id = id;
       this.username = username;
       this.salt = salt;
       this.password = password;
       this.tmstIns = tmstIns;
       this.tmstUpd = tmstUpd;
       this.portfolioses = portfolioses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Serializable getUsername() {
        return this.username;
    }
    
    public void setUsername(Serializable username) {
        this.username = username;
    }
    public Serializable getSalt() {
        return this.salt;
    }
    
    public void setSalt(Serializable salt) {
        this.salt = salt;
    }
    public Serializable getPassword() {
        return this.password;
    }
    
    public void setPassword(Serializable password) {
        this.password = password;
    }
    public Date getTmstIns() {
        return this.tmstIns;
    }
    
    public void setTmstIns(Date tmstIns) {
        this.tmstIns = tmstIns;
    }
    public Date getTmstUpd() {
        return this.tmstUpd;
    }
    
    public void setTmstUpd(Date tmstUpd) {
        this.tmstUpd = tmstUpd;
    }
    public Set getPortfolioses() {
        return this.portfolioses;
    }
    
    public void setPortfolioses(Set portfolioses) {
        this.portfolioses = portfolioses;
    }




}

