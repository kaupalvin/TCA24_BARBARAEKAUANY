package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.db.FabricaConexao;
import com.example.models.Usuario;
import com.example.models.interfaces.UsuarioCadastroDAO;

public class UsuarioCadastroDAOImpl implements UsuarioCadastroDAO {

    // @Override
    public boolean salvar(Usuario usuario) throws SQLException {
        
        String sql = "INSERT INTO tca_usuario (id_pessoa_pk_fk, email_usuario, senha_usuario) VALUES (?, ?, ?)";

        try (Connection con = FabricaConexao.faz_Conexão();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId_pessoa_pk());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected>0) {
                return true;
            }
            else {
                return false;
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailJaExiste(String email) throws SQLException {
        String sql = "SELECT * FROM tca_usuario WHERE email_usuario = ?";

        try (Connection con = FabricaConexao.faz_Conexão();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; 
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}