package com.example.models;

public class Time {
    
    private int id_time_pk;
    private String nome_time;
    private int pontuação_time;
    private int saldo_gols_time;
    private int quant_partidas_jogadas_time;

    public Time(int id_time_pk, String nome_time, int pontuação_time, int saldo_gols_time, int quant_partidas_jogadas_time) {
        this.id_time_pk = id_time_pk;
        this.nome_time = nome_time;
        this.pontuação_time = pontuação_time;
        this.saldo_gols_time = saldo_gols_time;
        this.quant_partidas_jogadas_time = quant_partidas_jogadas_time;
    }

    public Time(String nome_time, int pontuação_time, int saldo_gols_time, int quant_partidas_jogadas_time) {
        this.nome_time = nome_time;
        this.pontuação_time = pontuação_time;
        this.saldo_gols_time = saldo_gols_time;
        this.quant_partidas_jogadas_time = quant_partidas_jogadas_time;
    }

    public Time(int id_time_pk, String nome_time) {
        this.id_time_pk = id_time_pk;
        this.nome_time = nome_time;
    }

    public int getId_time_pk() {
        return id_time_pk;
    }

    public void setId_time_pk(int id_time_pk) {
        this.id_time_pk = id_time_pk;
    }

    public String getNome_time() {
        return nome_time;
    }

    public void setNome_time(String nome_time) {
        this.nome_time = nome_time;
    }

    public int getPontuação_time() {
        return pontuação_time;
    }

    public void setPontuação_time(int pontuação_time) {
        this.pontuação_time = pontuação_time;
    }

    public int getSaldo_gols_time() {
        return saldo_gols_time;
    }

    public void setSaldo_gols_time(int saldo_gols_time) {
        this.saldo_gols_time = saldo_gols_time;
    }

    public int getQuant_partidas_jogadas_time() {
        return quant_partidas_jogadas_time;
    }

    public void setQuant_partidas_jogadas_time(int quant_partidas_jogadas_time) {
        this.quant_partidas_jogadas_time = quant_partidas_jogadas_time;
    }

    @Override
    public String toString() {
        return this.nome_time; //combobox
    }
    
}