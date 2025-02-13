package com.example.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.example.db.FabricaConexao;
import com.example.models.Partida;
import com.example.models.interfaces.PartidaDAO;

public class PartidaDAOImpl implements PartidaDAO{
    public boolean salvar_Partida_Planejada(Partida partida) throws SQLException {
        String sql = "INSERT INTO tca24_partida(data_partida, placar_partida, id_time_pk_fk) VALUES (?, ?,?)";
        
        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, partida.getData_partida());
            stmt.setString(2, (partida.getPlacar_partida() == null || partida.getPlacar_partida().trim().isEmpty()) ? null : partida.getPlacar_partida());
            stmt.setInt(3, partida.getId_time_pk_fk());
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

    public boolean atualizar_Partida_Planejada(Partida partida) throws SQLException {
        String sql = "UPDATE tca24_partida SET data_partida=?, placar_partida = ?, id_time_pk_fk=? WHERE id_partida_pk = ?";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, partida.getData_partida());
            if (partida.getPlacar_partida() == null) {
                stmt.setNull(2, Types.VARCHAR); // Salva NULL no banco
            } else {
                stmt.setString(2, partida.getPlacar_partida()); // Salva o valor do placar no banco
            }
            stmt.setInt(3, partida.getId_time_pk_fk());
            stmt.setInt(4, partida.getId_partida_pk());
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

    public boolean remover_Partida(int id) throws SQLException {
        String sql = "DELETE FROM tca24_partida WHERE id_partida_pk = ?";
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

    public ArrayList<Partida> listar_Partida_Planejada() {
        ArrayList<Partida> partidasPlanejadas = new ArrayList<>();
        String sql = "SELECT p.id_partida_pk ,p.data_partida, p.placar_partida, t.nome_time " +
                     "FROM tca24_partida p " +
                     "JOIN tca24_time t ON p.id_time_pk_fk = t.id_time_pk " +
                    "WHERE p.placar_partida IS NULL";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idPartida = rs.getInt("id_partida_pk");
                    Date data = rs.getDate("data_partida");
                    String nome= rs.getString("nome_time");

                    Partida partida = new Partida(idPartida, data, nome);
                    
                    partidasPlanejadas.add(partida);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return partidasPlanejadas;  
    }

    public boolean salvar_Partida(Partida partida) throws SQLException {
        String sql = "INSERT INTO tca24_partida(data_partida, placar_partida, id_time_pk_fk) VALUES (?, ?, ?)";
        
        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, partida.getData_partida());
            stmt.setString(2, partida.getPlacar_partida());
            stmt.setInt(3, partida.getId_time_pk_fk());
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

    public boolean atualizar_Partida(Partida partida) throws SQLException {
        String sql = "UPDATE tca24_partida SET data_partida=?, placar_partida=?,id_time_pk_fk=? WHERE id_partida_pk = ?";
        
        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, partida.getData_partida());
            if (partida.getPlacar_partida() == null) {
                stmt.setNull(2, Types.VARCHAR); // Salva NULL no banco
            } else {
                stmt.setString(2, partida.getPlacar_partida()); // Salva o valor do placar no banco
            }
            stmt.setInt(3, partida.getId_time_pk_fk());
            stmt.setInt(4, partida.getId_partida_pk());
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

    public ArrayList<Partida> listar_Partida() {
        ArrayList<Partida> partidas = new ArrayList<>();
        String sql = "SELECT p.id_partida_pk, p.data_partida, p.placar_partida, t.nome_time " +
                 "FROM tca24_partida p " +
                 "JOIN tca24_time t ON p.id_time_pk_fk = t.id_time_pk " +
                 "WHERE p.placar_partida IS NOT NULL";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idPartida = rs.getInt("id_partida_pk");
                    Date data = rs.getDate("data_partida");
                    String placar = rs.getString("placar_partida");
                    String time = rs.getString("nome_time");

                    Partida partida = new Partida(idPartida, data, placar, time);

                    
                    partidas.add(partida);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return partidas;  
    }

    public ArrayList<Partida> listar_ComboBox() {
        ArrayList<Partida> partidas = new ArrayList<>();
        String sql = "SELECT p.id_partida_pk, t.nome_time FROM tca24_partida p JOIN tca24_time t ON p.id_time_pk_fk = t.id_time_pk WHERE p.placar_partida IS NOT NULL";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idPartida = rs.getInt("id_partida_pk");
                    String nomeTime = rs.getString("nome_time");

                    Partida partida = new Partida(idPartida, nomeTime);

                    partidas.add(partida);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return partidas; 
    }
}
