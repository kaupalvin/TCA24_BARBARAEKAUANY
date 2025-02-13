package com.example.repositories;

import java.sql.SQLException;
import java.util.List;

import com.example.DAO.ElencoDAOImpl;
import com.example.models.Jogador;


public class ElencoRepository {
    ElencoDAOImpl elencodaoimpl;

    public ElencoRepository() throws SQLException {
        this.elencodaoimpl = new ElencoDAOImpl();
    }

     public boolean SalvarJogador(Jogador jogador) throws SQLException {
        return elencodaoimpl.salvar(jogador);
    }

    public boolean AtualizarPJ(int id, int pj) throws SQLException {
        return elencodaoimpl.attPJ(id, pj);
    }

    public boolean AtualizarJogador(Jogador jogador) throws SQLException {
        return elencodaoimpl.atualizar(jogador);
    }

    public boolean RemoverTime(int id) throws SQLException {
        return elencodaoimpl.remover(id);
    }

    public List<Jogador> listarElenco() {
        return elencodaoimpl.listar();  
    }


}