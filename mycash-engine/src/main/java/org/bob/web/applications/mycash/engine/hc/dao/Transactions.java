package org.bob.web.applications.mycash.engine.hc.dao;
// Generated 31-lug-2016 16.50.26 by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;

/**
 * Transactions generated by hbm2java
 */
public class Transactions  implements java.io.Serializable {


     private int id;
     private Accounts accountsByToAcn;
     private Accounts accountsByFromAcn;
     private Date date;
     private String description;
     private Serializable type;
     private double amount;
     private Date tmstIns;
     private Date tmstUpd;

    public Transactions() {
    }

	
    public Transactions(int id, Date date, String description, Serializable type, double amount, Date tmstIns, Date tmstUpd) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.tmstIns = tmstIns;
        this.tmstUpd = tmstUpd;
    }
    public Transactions(int id, Accounts accountsByToAcn, Accounts accountsByFromAcn, Date date, String description, Serializable type, double amount, Date tmstIns, Date tmstUpd) {
       this.id = id;
       this.accountsByToAcn = accountsByToAcn;
       this.accountsByFromAcn = accountsByFromAcn;
       this.date = date;
       this.description = description;
       this.type = type;
       this.amount = amount;
       this.tmstIns = tmstIns;
       this.tmstUpd = tmstUpd;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Accounts getAccountsByToAcn() {
        return this.accountsByToAcn;
    }
    
    public void setAccountsByToAcn(Accounts accountsByToAcn) {
        this.accountsByToAcn = accountsByToAcn;
    }
    public Accounts getAccountsByFromAcn() {
        return this.accountsByFromAcn;
    }
    
    public void setAccountsByFromAcn(Accounts accountsByFromAcn) {
        this.accountsByFromAcn = accountsByFromAcn;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Serializable getType() {
        return this.type;
    }
    
    public void setType(Serializable type) {
        this.type = type;
    }
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
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




}


