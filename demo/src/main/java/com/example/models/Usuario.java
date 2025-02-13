package com.example.models;

public class Usuario {

    private int id_pessoa_pk;
    private String email;
    private String senha;

    public Usuario(int id_pessoa_pk, String email, String senha) {
        this.id_pessoa_pk = id_pessoa_pk;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public int getId_pessoa_pk() {
        return id_pessoa_pk;
    }

    public void setId_pessoa_pk(int id_pessoa_pk) {
        this.id_pessoa_pk = id_pessoa_pk;
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

}
