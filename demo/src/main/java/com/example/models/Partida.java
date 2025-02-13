package com.example.models;

import java.sql.Date;

public class Partida {
    
    private int id_partida_pk;
    private Date data_partida;
    private String placar_partida;
    private int id_time_pk_fk;
    private String nome_time;

    public Partida(int id_partida_pk, Date data_partida, String placar_partida, String nome_time) {
        this.id_partida_pk = id_partida_pk;
        this.data_partida = data_partida;
        this.placar_partida = placar_partida;
        this.nome_time = nome_time;
    }

    public Partida(Date data_partida, String placar_partida, int id_time_pk_fk) {
        this.data_partida = data_partida;
        this.placar_partida = placar_partida;
        this.id_time_pk_fk = id_time_pk_fk;
    }

    public Partida(int id_partida_pk, Date data_partida, String nome_time) {
        this.id_partida_pk = id_partida_pk;
        this.data_partida = data_partida;
        this.nome_time = nome_time;
    }
    
    public Partida(Date data_partida, int id_time_pk_fk) {
        this.id_time_pk_fk = id_time_pk_fk;
        this.data_partida = data_partida;
    }

    public Partida(int id_partida_pk, String nome_time) {
        this.id_partida_pk = id_partida_pk;
        this.nome_time = nome_time;
    }


    public String getNome_time() {
        return nome_time;
    }

    public void setNome_time(String nome_time) {
        this.nome_time = nome_time;
    }

    public int getId_partida_pk(){
        return id_partida_pk;
    }

    public void setId_partida_pk(int id_partida_pk) {
        this.id_partida_pk = id_partida_pk;
    }

    public Date getData_partida() {
        return data_partida;
    }

    public void setData_partida(Date data_partida) {
        this.data_partida = data_partida;
    }

    public String getPlacar_partida() {
        return placar_partida;
    }

    public void setPlacar_partida(String placar_partida) {
        this.placar_partida = placar_partida;
    }

    public int getId_time_pk_fk() {
        return id_time_pk_fk;
    }

    public void setId_time_pk_fk(int id_time_pk_fk) {
        this.id_time_pk_fk = id_time_pk_fk;
    }
    
    @Override
    public String toString() {
        return this.nome_time; //combobox
    }
    
}
