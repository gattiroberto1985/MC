
package org.bob.web.applications.mycash.engine.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * User bean for the application. 
 * 
 * @author roberto.gatti
 */
@Entity
@Table( name = "users" )
@XmlRootElement( name = "user" )
@XmlAccessorType( XmlAccessType.FIELD )
public class MCUser {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column( name = "id" )
    @XmlElement( required = true )
    private long id;
    
    @Column( name = "username" )
    @XmlElement( required = true )
    private String username;
    
    @Column( name = "password" )
    @XmlElement( required = true )
    private String password;
    
    @Column( name  ="salt" )
    @XmlElement( required = true )
    private String salt;
    
    @Column( name = "email" )
    @XmlElement( required = true )
    private String email;
    
    
    public MCUser(String username, String password)
    {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    public MCUser(String username, String password, String email)
    {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    // <editor-fold desc="GETTER AND SETTER">
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public String getSalt() {
        return this.salt;
    }
    
    /**
     * 
     * @param salt 
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    //</editor-fold>
    
}
