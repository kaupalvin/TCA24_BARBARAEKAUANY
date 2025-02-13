package com.example.models.interfaces;

import java.sql.SQLException;

import com.example.models.Pessoa;

public interface PessoaCadastroDAO {
    public boolean salvar(Pessoa pessoa) throws SQLException;

    public boolean remover(int id) throws SQLException;

    public boolean atualizar(int id, String nome) throws SQLException;

}
