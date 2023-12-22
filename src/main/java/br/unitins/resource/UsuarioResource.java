package br.unitins.resource;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.form.UsuarioForm;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.FileService;
import br.unitins.service.PasswordService;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioRepository repository;

    @Inject
    private FileService fileService;

    @Inject
    PasswordService pService;

    @GET
    public List<Usuario> getAll() {
        return repository.findAllUsuarios2();
    }

    @GET
    @Path("/search/{nome}")
    public List<UsuarioResponseDTO> getListUsuario(String nome) {
        return repository.findByNome(nome);
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO get(Long id) {
        return new UsuarioResponseDTO(repository.findById(id));
    }

    @POST
    @Path("/postupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response create(@MultipartForm UsuarioForm form) {

        String nomeImagem = "";
        try {
            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {
            Response.ok(e.getMessage(), MediaType.TEXT_PLAIN).status(422).build();
            e.printStackTrace();
        }
        Usuario entity = new Usuario();
        entity.nome = form.getNome();
        entity.email = form.getEmail();
        entity.codArea = form.getCodArea();
        entity.telefone = form.getTelefone();
        entity.senha = pService.getHash(form.getSenha());
        entity.nomeImagem = nomeImagem;

        repository.persist(entity);

        return Response.created(URI.create("/usuarios/" + entity.id)).entity(new UsuarioResponseDTO(entity)).build();
    }

    @PUT
    @Path("/putupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public UsuarioResponseDTO update(@MultipartForm UsuarioForm form) {
        String nomeImagem = "";
        try {
            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {
            Response.ok(e.getMessage(), MediaType.TEXT_PLAIN).status(422).build();
            e.printStackTrace();
        }

        Usuario entity = repository.findById(form.getId());

        entity.nome = form.getNome();
        entity.email = form.getEmail();
        entity.codArea = form.getCodArea();
        entity.telefone = form.getTelefone();
        entity.senha = pService.getHash(form.getSenha());
        entity.nomeImagem = nomeImagem;
        return new UsuarioResponseDTO(entity);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Usuario entity = repository.findById(id);
        if (entity == null)
            throw new NotFoundException();
        repository.delete(entity);
    }

    @GET
    @Path("/count")
    public Long count() {
        return repository.count();
    }

    @GET
    @Path("/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition",
                "attachment;filename=" + nomeImagem);
        return response.build();
    }
}