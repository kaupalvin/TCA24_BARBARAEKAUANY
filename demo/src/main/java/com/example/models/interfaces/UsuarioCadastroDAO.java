package com.example.models.interfaces;

import java.sql.SQLException;

import com.example.models.Usuario;

public interface UsuarioCadastroDAO {
    public boolean salvar(Usuario usuario) throws SQLException;

   public boolean emailJaExiste(String email) throws SQLException;
}
