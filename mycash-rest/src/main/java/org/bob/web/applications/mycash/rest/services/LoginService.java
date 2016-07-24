package org.bob.web.applications.mycash.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author roberto.gatti
 */
@Path("login")
public class LoginService {

    @POST
    @Produces("application/json")
    public String login(String body)
    {
        
    }
    
}
