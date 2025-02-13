package com.example.models.interfaces;

import java.sql.SQLException;

import com.example.models.Partida_Jogador;

public interface Partida_JogadorDAO {
    public boolean salvar(Partida_Jogador partida_jogador) throws SQLException;

    public int contar(int idPessoa);

    public boolean remover(int id) throws SQLException;
}
