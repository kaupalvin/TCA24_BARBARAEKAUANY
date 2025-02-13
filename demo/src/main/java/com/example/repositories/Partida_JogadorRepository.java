package com.example.repositories;

import java.sql.SQLException;

import com.example.DAO.Partida_JogadorDAOImpl;
import com.example.models.Partida_Jogador;

public class Partida_JogadorRepository {
    Partida_JogadorDAOImpl partida_jogadorDAOImpl;

    public Partida_JogadorRepository() throws SQLException {
        this.partida_jogadorDAOImpl = new Partida_JogadorDAOImpl();
    }

    public boolean salvarPartidaJogador(Partida_Jogador partida_jogador) throws SQLException{
        return partida_jogadorDAOImpl.salvar(partida_jogador);
    }

    public int contarPartidasJogador(int id){
        return partida_jogadorDAOImpl.contar(id);
    }

    public boolean RemoverPartidasJogador(int id) throws SQLException{
        return partida_jogadorDAOImpl.remover(id);
    }
}
