package br.unitins.form;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class UsuarioForm {

    @FormParam("id")
    private Long id;

    @FormParam("nome")
    private String nome;

    @FormParam("email")
    private String email;

    // @FormParam("email")
    // private Telefone telefone;

    @FormParam("senha")
    private String senha;

    @FormParam("idCidade")
    private Long idCidade;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    @FormParam("nomeImagem")
    private String nomeImagem;

    @FormParam("telefone")
    private String telefone;

    @FormParam("codArea")
    private String codArea;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodArea() {
        return codArea;
    }

    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }
}