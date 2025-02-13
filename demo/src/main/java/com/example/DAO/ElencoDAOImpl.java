package com.example.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.db.FabricaConexao;
import com.example.models.Jogador;
import com.example.models.interfaces.ElencoDAO;

public class ElencoDAOImpl implements ElencoDAO{

    public boolean salvar(Jogador jogador) throws SQLException {
       String sql = "INSERT INTO tca24_jogador(id_pessoa_pk_fk, id_posicao_pk_fk, dt_nasc_jogador, num_camisa_jogador, gols_jogador, total_partidas) VALUES (?, ?, ?, ?, ?, 0)";
           
        try(Connection con = FabricaConexao.faz_Conexão(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, jogador.getIdPessoaPkFk());
                stmt.setInt(2, jogador.getIdPosicaoPkFk());
                stmt.setDate(3, Date.valueOf(jogador.getDtNascJogador().toLocalDate()));
                stmt.setString(4, jogador.getNumCamisaJogador());
                stmt.setInt(5, jogador.getGolsJogador());
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

    public boolean attPJ(int id, int pj) throws SQLException {
        String sql = "UPDATE tca24_jogador SET total_partidas = ? WHERE id_pessoa_pk_fk = ?";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, pj);
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

     public boolean atualizar(Jogador jogador) throws SQLException {
        String sql = "UPDATE tca24_jogador SET id_posicao_pk_fk=?, dt_nasc_jogador=?, num_camisa_jogador=?, gols_jogador=? WHERE id_pessoa_pk_fk = ?";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, jogador.getIdPosicaoPkFk());
            stmt.setDate(2, Date.valueOf(jogador.getDtNascJogador().toLocalDate()));
            stmt.setString(3, jogador.getNumCamisaJogador());
            stmt.setInt(4, jogador.getGolsJogador());
            stmt.setInt(5, jogador.getIdPessoaPkFk());
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
        String sql = "DELETE FROM tca24_jogador WHERE id_pessoa_pk_fk = ?";
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

     public ArrayList<Jogador> listar() {
         ArrayList<Jogador> jogadores = new ArrayList<>();
         String sql = "SELECT j.id_pessoa_pk_fk, p.nome_pessoa, TIMESTAMPDIFF(YEAR,(j.dt_nasc_jogador),CURDATE()) AS idade, j.num_camisa_jogador, j.gols_jogador, j.total_partidas, pos.descricao_posicao" +
         " FROM tca24_jogador j " +
         "JOIN tca24_pessoa p ON id_pessoa_pk_fk = id_pessoa_pk " +
         "JOIN tca24_posição pos ON id_posicao_pk_fk = id_posicao_pk ";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idPessoa = rs.getInt("id_pessoa_pk_fk");
                    String nome = rs.getString("nome_pessoa");
                    int dataNasc = rs.getInt("idade");
                    String num_camisa = rs.getString("num_camisa_jogador");
                    int gols = Integer.parseInt(rs.getString("gols_jogador"));
                    int qnt_jogos = rs.getInt("total_partidas");
                    String pos = rs.getString("descricao_posicao");

                    Jogador jogador = new Jogador(idPessoa, nome, dataNasc, num_camisa, gols, qnt_jogos, pos);

                    
                    jogadores.add(jogador);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return jogadores;  // Retorna a lista de jogadores
    }

}
