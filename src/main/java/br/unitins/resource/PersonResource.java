package br.unitins.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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