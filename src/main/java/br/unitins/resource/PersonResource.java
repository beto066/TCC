package br.unitins.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unitins.model.Person;

@Path("/person")
public class PersonResource  {
    @GET
    @Path("/search/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(String name) {
       return Person.findByName(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return System.getProperty("user.home");
    }
}