package com.example.repositories;

import java.sql.SQLException;
import java.util.List;

import com.example.DAO.TimeDAOImpl;
import com.example.models.Time;


public class TimeRepository {
    TimeDAOImpl timedaoimpl;

    public TimeRepository() throws SQLException {
        this.timedaoimpl = new TimeDAOImpl();
    }

    public boolean SalvarTime(Time time) throws SQLException {
        return timedaoimpl.salvar_time(time);
    }

    public boolean AtualizarTime(Time time) throws SQLException {
        return timedaoimpl.atualizar(time);
    }

    public boolean RemoverTime(int id) throws SQLException {
        return timedaoimpl.remover(id);
    }

    public List<Time> listarTime() {
        return timedaoimpl.listarTime();  
    }

    public List<Time> listarComboBox() {
        return timedaoimpl.listarComboBox();
    }

    public boolean timeJaExiste(String nome_time) throws SQLException {
        return timedaoimpl.timeJaExiste(nome_time);
    }
}