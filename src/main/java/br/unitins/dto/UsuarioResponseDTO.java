package br.unitins.dto;

import br.unitins.model.Usuario;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String codArea;
    private String telefone;
    private String senha;
    private String nomeImagem;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.id;
        this.nome = usuario.nome;
        this.email = usuario.email;
        this.senha = usuario.senha;
        this.codArea = usuario.codArea;
        this.telefone = usuario.telefone;
        this.nomeImagem = usuario.nomeImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodArea() {
        return codArea;
    }
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

}
