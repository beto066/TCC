package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.dto.UserResponseDTO;
import br.unitins.form.UserForm;
import br.unitins.repository.UserRepository;
import br.unitins.service.interfaces.UserService;
import br.unitins.service.utils.FileService;
import br.unitins.service.utils.JwtService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserRepository repository;

    @Inject
    private FileService fileService;

    @Inject
    JwtService jwtService;

    @Inject
    private JsonWebToken token;

    @Inject
    private UserService service;

    @GET
    @RolesAllowed({})
    public List<UserResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/search/{name}")
    @RolesAllowed({})
    public List<UserResponseDTO> getListUser(@PathParam("name") String name) {
        return service.getListUser(name);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({})
    public UserResponseDTO get(@PathParam("id") Long id) {
        return service.find(id);
    }

    @GET
    @Path("/profile")
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public UserResponseDTO find() {
        Long userId = jwtService.getUserId(token);

        return service.find(userId);
    }

    @PUT
    @Path("/putupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public UserResponseDTO update(@MultipartForm UserForm form) {
        Long userId = jwtService.getUserId(token);

        return service.update(form, userId);
    }

    @DELETE
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public Response delete() {
        Long userId = jwtService.getUserId(token);

        service.delete(userId);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({})
    public Long count() {
        return repository.count();
    }

    @GET
    @Path("/download/{imageName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({})
    public Response downloadImage(@PathParam("imageName") String imageName) {
        ResponseBuilder response = Response.ok(fileService.download(imageName));

        response.header("Content-Disposition",
                "attachment;filename=" + imageName);

        return response.build();
    }
}