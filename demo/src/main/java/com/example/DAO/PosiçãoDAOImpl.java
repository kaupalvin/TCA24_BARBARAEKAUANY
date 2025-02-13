package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.db.FabricaConexao;
import com.example.models.Posição;
import com.example.models.interfaces.PosiçãoDAO;

public class PosiçãoDAOImpl implements PosiçãoDAO{
    
    public boolean salvar(Posição posição) throws SQLException {
        String sql = "INSERT INTO tca24_posição (descrição_posicao) VALUES (?)";

        try (Connection con = FabricaConexao.faz_Conexão();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "Goleiro");
            stmt.executeUpdate();

            stmt.setString(1, "Zagueiro");
            stmt.executeUpdate();

            stmt.setString(1, "Lateral Direito");
            stmt.executeUpdate();

            stmt.setString(1, "Lateral Esquerdo");
            stmt.executeUpdate();

            stmt.setString(1, "Volante");
            stmt.executeUpdate();

            stmt.setString(1, "Meia");
            stmt.executeUpdate();

            stmt.setString(1, "Ponta Direita");
            stmt.executeUpdate();

            stmt.setString(1, "Ponta Esquerda");
            stmt.executeUpdate();

            stmt.setString(1, "Centroavante");
            stmt.executeUpdate();

            stmt.setString(1, "Segundo Atacante");
            stmt.executeUpdate();
            stmt.executeUpdate();
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

    public ArrayList<Posição> listar_Posições() {
        ArrayList<Posição> posições = new ArrayList<>();
        String sql = "SELECT * FROM tca24_posição";
    
        try (Connection con = FabricaConexao.faz_Conexão(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    int idPosição = rs.getInt("id_posicao_pk");
                    String nomePosição = rs.getString("descricao_posicao");

                    Posição posição = new Posição(idPosição, nomePosição);

                    posições.add(posição);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return posições;  // Retorna a lista de times
    }

}
