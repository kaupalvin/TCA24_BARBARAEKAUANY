package com.example.repositories;

import java.sql.SQLException;

import com.example.DAO.PessoaCadastroDAOImpl;
import com.example.models.Pessoa;


public class PessoaCadastroRepository {
    PessoaCadastroDAOImpl pessoacadastrodaoimpl;

    public PessoaCadastroRepository() throws SQLException {
        this.pessoacadastrodaoimpl = new PessoaCadastroDAOImpl();

    }

    public boolean SalvarCadastroPessoa(Pessoa pessoa) throws SQLException {
        return pessoacadastrodaoimpl.salvar(pessoa);
    }

    public boolean RemoverCadastroPessoa(int id) throws SQLException{
        return pessoacadastrodaoimpl.remover(id);
    }

    public boolean AtualizarCadastroPessoa(int id, String nome) throws SQLException{
        return pessoacadastrodaoimpl.atualizar(id, nome);
    }

}
