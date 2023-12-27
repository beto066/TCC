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
    private JsonWebToken token;

    @GET
    @RolesAllowed("")
    public List<Users> getAll() {
        return repository.findAllUsers2();
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
        System.out.println(new UserResponseDTO(repository.findById(id)));
        return new UserResponseDTO(repository.findById(id));
    }

    @PUT
    @Path("/putupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network Admin"})
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

        Users entity = repository.findById(token.<Long>getClaim("id"));

        if (form.getName() != null && form.getPassword().trim().length() >= 2) {
            entity.name = form.getName();
        }
        if (form.getEmail() !=  null && !EmailService.isEmail(form.getEmail())) {
            entity.email = form.getEmail();
        }
        if (form.getPassword() != null && form.getPassword().trim().length() >= 6) {
            entity.password = pService.getHash(form.getPassword());
        }
        if (imageName != null && !imageName.isEmpty()) {
            entity.imageName = imageName;
        }
        return new UserResponseDTO(entity);
    }

    @DELETE
    @Transactional
    @RolesAllowed({"Therapist", "Family", "Network Admin"})
    public void delete() {
        Users entity = repository.findById(token.<Long>getClaim("id"));
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