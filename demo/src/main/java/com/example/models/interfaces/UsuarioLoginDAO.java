package com.example.models.interfaces;

import java.sql.SQLException;

//import javax.swing.text.StyledEditorKit.BoldAction;

import com.example.models.Usuario;

public interface UsuarioLoginDAO {
    public boolean realizar_Login(Usuario usuario) throws SQLException;
}
