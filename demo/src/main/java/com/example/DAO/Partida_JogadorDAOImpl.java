package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.db.FabricaConexao;
import com.example.models.Partida_Jogador;
import com.example.models.interfaces.Partida_JogadorDAO;

public class Partida_JogadorDAOImpl implements Partida_JogadorDAO {
    public boolean salvar(Partida_Jogador partida_jogador) throws SQLException {
        String sql = "INSERT INTO tca24_partida_jogador(id_pessoa_pk_fk, id_partida_pk_fk) VALUES (?,?)";
        
        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, partida_jogador.getIdPessoaPkFk());
            stmt.setInt(2, partida_jogador.getIdPartidaPkFk());
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

        public boolean remover(int id) throws SQLException {
            String sql = "DELETE FROM tca24_partida_jogador WHERE id_pessoa_pk_fk = ?";
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

        public int contar(int idPessoa) {
        String sql = "SELECT id_pessoa_pk_fk, COUNT(id_partida_pk_fk) AS total_partidas " +
                     "FROM tca24_partida_jogador " +
                     "WHERE id_pessoa_pk_fk=?";

        int contagem = 0;
        try (Connection con = FabricaConexao.faz_Conexão();
             PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, idPessoa);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        contagem = rs.getInt("total_partidas"); 
                    }
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contagem;
    }


}
