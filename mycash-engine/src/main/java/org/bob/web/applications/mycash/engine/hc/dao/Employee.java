package org.bob.web.applications.mycash.engine.hc.dao;
// Generated 31-lug-2016 16.50.26 by Hibernate Tools 4.3.1



/**
 * Employee generated by hbm2java
 */
public class Employee  implements java.io.Serializable {


     private int id;
     private String firstName;
     private String lastName;
     private String email;
     private String phone;

    public Employee() {
    }

	
    public Employee(int id) {
        this.id = id;
    }
    public Employee(int id, String firstName, String lastName, String email, String phone) {
       this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phone = phone;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }




}


