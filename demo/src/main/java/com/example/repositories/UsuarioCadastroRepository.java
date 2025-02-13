package com.example.repositories;

import java.sql.SQLException;

import com.example.DAO.UsuarioCadastroDAOImpl;
import com.example.models.Usuario;

public class UsuarioCadastroRepository {
    UsuarioCadastroDAOImpl usuariocadastrodaoimpl;

    public UsuarioCadastroRepository() throws SQLException {
        this.usuariocadastrodaoimpl = new UsuarioCadastroDAOImpl();

    }

    public boolean SalvarCadastroUsuário(Usuario usuario) throws SQLException {
        return usuariocadastrodaoimpl.salvar(usuario);
    }

    public boolean emailJaExiste(String email) throws SQLException {
        return usuariocadastrodaoimpl.emailJaExiste(email);
    }
}
