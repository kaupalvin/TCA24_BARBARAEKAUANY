package com.example.models;

public class Partida_Jogador {
    
    private int idPessoaPkFk;
    private int idPartidaPkFk;

    public Partida_Jogador(int idPessoaPkFk, int idPartidaPkFk) {
        this.idPessoaPkFk = idPessoaPkFk;
        this.idPartidaPkFk = idPartidaPkFk;
    }

    public int getIdPessoaPkFk() {
        return idPessoaPkFk;
    }

    public void setIdPessoaPkFk(int idPessoaPkFk) {
        this.idPessoaPkFk = idPessoaPkFk;
    }

    public int getIdPartidaPkFk() {
        return idPartidaPkFk;
    }

    public void setIdPartidaPkFk(int idPartidaPkFk) {
        this.idPartidaPkFk = idPartidaPkFk;
    }
    

}