package com.example.models.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.models.Jogador;


public interface ElencoDAO {
    public boolean salvar(Jogador jogador) throws SQLException;

    public boolean atualizar(Jogador jogador) throws SQLException;

    public boolean attPJ(int id, int pj) throws SQLException;

    public boolean remover(int id) throws SQLException;

    public ArrayList<Jogador> listar();
}
