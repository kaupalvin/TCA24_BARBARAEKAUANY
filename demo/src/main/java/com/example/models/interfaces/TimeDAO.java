package com.example.models.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.models.Time;

public interface TimeDAO {
    public boolean salvar_time(Time time) throws SQLException;

    public boolean atualizar(Time time) throws SQLException;

    public boolean remover(int id) throws SQLException;

    public ArrayList<Time> listarTime();

    public ArrayList<Time> listarComboBox();

    public boolean timeJaExiste(String nome_time) throws SQLException;
}