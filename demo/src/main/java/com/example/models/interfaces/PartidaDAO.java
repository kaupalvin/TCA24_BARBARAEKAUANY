package com.example.models.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.models.Partida;

public interface PartidaDAO {
    public boolean salvar_Partida_Planejada(Partida partida) throws SQLException;

    public boolean atualizar_Partida_Planejada(Partida partida) throws SQLException;

    public boolean remover_Partida(int id) throws SQLException;

    public ArrayList<Partida> listar_Partida_Planejada();

    public boolean salvar_Partida(Partida partida) throws SQLException;

    public boolean atualizar_Partida(Partida partida) throws SQLException;

    public ArrayList<Partida> listar_Partida();

    public ArrayList<Partida> listar_ComboBox();
}
