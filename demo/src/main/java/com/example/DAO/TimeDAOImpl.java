package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.db.FabricaConexao;
import com.example.models.Time;
import com.example.models.interfaces.TimeDAO;

public class TimeDAOImpl implements TimeDAO{
    
    public boolean salvar_time(Time time) throws SQLException {
        String sql = "INSERT INTO tca24_time (nome_time, pontuacao_time, saldo_gols_time, qnt_partidas_jogadas_time) VALUES ( ?, ?, ?, ?)";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, time.getNome_time());
            stmt.setInt(2, time.getPontuação_time());
            stmt.setInt(3, time.getSaldo_gols_time());
            stmt.setInt(4, time.getQuant_partidas_jogadas_time());
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

    public boolean atualizar(Time time) throws SQLException {
        String sql = "UPDATE tca24_time SET nome_time=? , pontuacao_time=?, saldo_gols_time=?, qnt_partidas_jogadas_time=? WHERE id_time_pk=?";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, time.getNome_time());
            stmt.setInt(2, time.getPontuação_time());
            stmt.setInt(3, time.getSaldo_gols_time());
            stmt.setInt(4, time.getQuant_partidas_jogadas_time());
            stmt.setInt(5, time.getId_time_pk());
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
        String sql = "DELETE FROM tca24_time WHERE id_time_pk = ?";
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

    public ArrayList<Time> listarTime() {
        ArrayList<Time> times = new ArrayList<>();
        String sql = "SELECT id_time_pk, nome_time, pontuacao_time, saldo_gols_time, qnt_partidas_jogadas_time FROM tca24_time ORDER BY pontuacao_time DESC, saldo_gols_time DESC";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idTime = rs.getInt("id_time_pk");
                    String nomeTime = rs.getString("nome_time");
                    int pontuacaoTime = Integer.parseInt(rs.getString("pontuacao_time"));
                    int saldoGolsTime = Integer.parseInt(rs.getString("saldo_gols_time"));
                    int qntPartidasJogadasTime = Integer.parseInt(rs.getString("qnt_partidas_jogadas_time"));

                    Time time = new Time(idTime, nomeTime, pontuacaoTime, saldoGolsTime, qntPartidasJogadasTime);
                    
                    times.add(time);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return times;  // Retorna a lista de times
    }

    public ArrayList<Time> listarComboBox() {
        ArrayList<Time> times = new ArrayList<>();
        String sql = "SELECT id_time_pk, nome_time FROM tca24_time WHERE nome_time != 'Rio Branco'";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idTime = rs.getInt("id_time_pk");
                    String nomeTime = rs.getString("nome_time");

                    Time time = new Time(idTime, nomeTime);
                    
                    times.add(time);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return times;  // Retorna a lista de times
    }

    public boolean timeJaExiste(String nome_time) throws SQLException {
        String sql = "SELECT * FROM tca24_time WHERE nome_time = ?";

        try (Connection con = FabricaConexao.faz_Conexão();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome_time);
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