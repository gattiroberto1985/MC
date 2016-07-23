package org.bob.web.applications.mycash.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author roberto.gatti
 */
@Path("login")
public class LoginResource {

    @GET
    @Produces("text/plain")
    public String get() {
        return "page";
}
}
