package com.example.models;

import java.sql.Date;

public class Jogador {

    private int idPessoaPkFk;
    private String nomePessoa;
    private Date dtNascJogador;
    private int idade;
    private String numCamisaJogador;
    private int golsJogador;
    private int totalPartidas;
    private int idPosicaoPkFk;
    private String descricaoPosicao;
    
    public Jogador(int idPessoaPkFk, int idPosicaoPkFk, Date dtNascJogador, String numCamisaJogador, int golsJogador) {
        this.idPessoaPkFk = idPessoaPkFk;
        this.dtNascJogador = dtNascJogador;
        this.numCamisaJogador = numCamisaJogador;
        this.golsJogador = golsJogador;
        this.idPosicaoPkFk = idPosicaoPkFk;
    }

    public Jogador(int idPessoaPkFk, String nomePessoa, int idade, String numCamisaJogador, int golsJogador, int totalPartidas, String descricaoPosicao) {
        this.idPessoaPkFk = idPessoaPkFk;
        this.nomePessoa = nomePessoa;
        this.idade = idade;
        this.numCamisaJogador = numCamisaJogador;
        this.golsJogador = golsJogador;
        this.totalPartidas = totalPartidas;
        this.descricaoPosicao = descricaoPosicao;
    }

    public int getIdPessoaPkFk() {
        return idPessoaPkFk;
    }

    public void setIdPessoaPkFk(int idPessoaPkFk){
        this.idPessoaPkFk = idPessoaPkFk;
    }

    public String getNumCamisaJogador() {
        return numCamisaJogador;
    }

    public void setNumCamisaJogador(String numCamisaJogador) {
        this.numCamisaJogador = numCamisaJogador;
    }

    public int getGolsJogador() {
        return golsJogador;
    }

    public void setGolsJogador(int golsJogador) {
        this.golsJogador = golsJogador;
    }

    public Date getDtNascJogador() {
        return dtNascJogador;
    }

    public void setDtNascJogador(Date dtNascJogador) {
        this.dtNascJogador = dtNascJogador;
    }

    public int getIdPosicaoPkFk() {
        return idPosicaoPkFk;
    }

    public void setIdPosicaoPkFk(int idPosicaoPkFk) {
        this.idPosicaoPkFk = idPosicaoPkFk;
    }

    public int getTotalPartidas() {
        return totalPartidas;
    }

    public void setTotalPartidas(int totalPartidas) {
        this.totalPartidas = totalPartidas;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getDescricaoPosicao() {
        return descricaoPosicao;
    }

    public void setDescricaoPosicao(String descricaoPosicao) {
        this.descricaoPosicao = descricaoPosicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
