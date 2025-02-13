package com.example.models;

public class Pessoa {

    private int id_pessoa_pk;
    private String nome_pessoa; 

    public Pessoa(int id_pessoa_pk, String nome_pessoa) {
        this.id_pessoa_pk = id_pessoa_pk;
        this.nome_pessoa = nome_pessoa;
    }

    public Pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public int getId_pessoa_pk() {
        return id_pessoa_pk;
    }

    public void setId_pessoa_pk(int id_pessoa_pk) {
        this.id_pessoa_pk = id_pessoa_pk;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    @Override
    public String toString() {
        return "Pessoa [id_pessoa_pk=" + id_pessoa_pk + ", nome_pessoa=" + nome_pessoa + "]";
    }

    
}
