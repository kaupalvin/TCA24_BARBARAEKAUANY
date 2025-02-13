package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/info23_BARBARA";
    private static final String USER = "info23_BARBARA";
    private static final String PASSWORD = "info23_BARBARA";

    public static Connection faz_Conexão() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }
}
