package br.unitins.resource;

import java.io.IOException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.dto.UserResponseDTO;
import br.unitins.form.UserForm;
import br.unitins.model.Users;
import br.unitins.repository.UserRepository;
import br.unitins.service.EmailService;
import br.unitins.service.FileService;
import br.unitins.service.JwtService;
import br.unitins.service.PasswordService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserRepository repository;

    @Inject
    private FileService fileService;

    @Inject
    private PasswordService pService;

    @Inject
    JwtService jwtService;

    @Inject
    private JsonWebToken token;

    @GET
    @RolesAllowed("Therapist")
    public List<Users> getAll() {
        return repository.listAllUsers();
    }

    @GET
    @Path("/search/{name}")
    @RolesAllowed("")
    public List<UserResponseDTO> getListUser(@PathParam("name") String name) {
        return repository.findByName(name);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("")
    public UserResponseDTO get(@PathParam("id") Long id) {
        return new UserResponseDTO(repository.findById(id));
    }

    @GET
    @Path("/profile")
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public UserResponseDTO find() {
        return new UserResponseDTO(repository.findById(jwtService.getUserId(token)));
    }

    @PUT
    @Path("/putupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public UserResponseDTO update(@MultipartForm UserForm form) {
        String imageName = "";

        if (
            form.getImageName() != null &&
            !form.getImageName().isEmpty() &&
            form.getImagem() != null &&
            form.getImagem().length > 0
        ) {
            try {
                imageName = fileService.saveUserImage(form.getImagem(), form.getImageName());
            } catch (IOException e) {
                Response.ok(e.getMessage(), MediaType.TEXT_PLAIN).status(422).build();
                e.printStackTrace();
            }
        }

        Users entity = repository.findById(jwtService.getUserId(token));

        if (form.getName() != null) {
            if (form.getName().trim().length() < 4 || form.getName().trim().length() > 30) {
                throw new WebApplicationException(
                    "the field name must be greater than 4 and less than 30",
                    422
                );
            }
            entity.name = form.getName();
        }
        if (form.getEmail() != null) {
            if (!EmailService.isEmail(form.getEmail())) {
                throw new WebApplicationException(
                    "the field email must be an email",
                    422
                );
            }
            entity.email = form.getEmail();
        }
        if (form.getPassword() != null && form.getPassword().trim().length() >= 6) {
            if (form.getPassword().trim().length() < 6) {
                throw new WebApplicationException(
                    "the field password must be greater than 4",
                    422
                );
            }
            entity.password = pService.getHash(form.getPassword());
        }
        if (imageName != null && !imageName.isEmpty()) {
            entity.imageName = imageName;
        }
        return new UserResponseDTO(entity);
    }

    @DELETE
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network_Admin"})
    public void delete() {
        Users entity = repository.findById(jwtService.getUserId(token));
        if (entity == null){
            throw new NotFoundException();
        }

        repository.delete(entity);
    }

    @GET
    @Path("/count")
    @RolesAllowed("")
    public Long count() {
        return repository.count();
    }

    @GET
    @Path("/download/{imageName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed("")
    public Response downloadImage(@PathParam("imageName") String imageName) {
        ResponseBuilder response = Response.ok(fileService.download(imageName));
        response.header("Content-Disposition",
                "attachment;filename=" + imageName);
        return response.build();
    }
}