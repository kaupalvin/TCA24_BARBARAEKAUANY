package com.example.models;

public class Posição {
    
    private int id_posição_pk;
    private String descrição_posição;

    public Posição(int id_posição_pk, String descrição_posição) {
        this.id_posição_pk = id_posição_pk;
        this.descrição_posição = descrição_posição;
    }

    public Posição(String descrição_posição) {
        this.descrição_posição = descrição_posição;
    }

    public int getId_posição_pk() {
        return id_posição_pk;
    }

    public void setId_posição_pk(int id_posição_pk) {
        this.id_posição_pk = id_posição_pk;
    }

    public String getDescrição_posição() {
        return descrição_posição;
    }

    public void setDescrição_posição(String descrição_posição) {
        this.descrição_posição = descrição_posição;
    }

    @Override
    public String toString() {
        return this.descrição_posição; //combobox
    }
    
}
