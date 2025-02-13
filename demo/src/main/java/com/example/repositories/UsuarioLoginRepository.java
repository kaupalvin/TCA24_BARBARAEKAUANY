package com.example.repositories;

import java.sql.SQLException;
import com.example.DAO.UsuarioLoginDAOImpl;
import com.example.models.Usuario;

public class UsuarioLoginRepository {

    UsuarioLoginDAOImpl usuariodaoimpl;

    public UsuarioLoginRepository() throws SQLException {
        this.usuariodaoimpl = new UsuarioLoginDAOImpl();

    }

    public boolean RealizarLogin(Usuario usuario) throws SQLException {
        return usuariodaoimpl.realizar_Login(usuario);
    }

}
