package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.xml.transform.Result;

import com.example.db.FabricaConexao;
import com.example.models.Usuario;
import com.example.models.interfaces.UsuarioLoginDAO;

public class UsuarioLoginDAOImpl implements UsuarioLoginDAO {
    public boolean realizar_Login(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM tca_usuario WHERE email_usuario = ? and senha_usuario = ?";
        try (Connection con = FabricaConexao.faz_Conexão(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
