package org.bob.web.applications.mycash.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.bob.web.applications.mycash.rest.services.pojos.Result;

/**
 *
 * @author roberto.gatti
 */
@Path("login")
public class LoginServices {

    @POST
    @Produces("application/json")
    public Result login(String body)
    {
        Result result = new Result();
        
        return result;
    }
    
}
