package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.db.FabricaConexao;
import com.example.models.Pessoa;
import com.example.models.interfaces.PessoaCadastroDAO;

public class PessoaCadastroDAOImpl implements PessoaCadastroDAO{
    public boolean salvar(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO tca24_pessoa (nome_pessoa) VALUES ( ?)";
        try (Connection con = FabricaConexao.faz_Conexão();
         PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, pessoa.getNome_pessoa());
        System.out.println(sql + pessoa.getNome_pessoa());
        int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pessoa.setId_pessoa_pk(rs.getInt(1)); // Atualiza o ID no objeto Pessoa
                    }
                    else {
                        System.out.println("Erro: Nenhuma chave gerada foi retornada pelo banco.");
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Erro ao salvar o usuário: " + e.getMessage());
        }
            return false;
        }

        public boolean remover(int id) throws SQLException {
            String sql = "DELETE FROM tca24_pessoa WHERE id_pessoa_pk = ?";
            try (Connection con = FabricaConexao.faz_Conexão();
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
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

        public boolean atualizar(int id, String nome) throws SQLException {
        String sql = "UPDATE tca24_pessoa SET nome_pessoa=? WHERE id_pessoa_pk=?";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
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
}