package com.example.repositories;

import java.sql.SQLException;
import java.util.List;

import com.example.DAO.PartidaDAOImpl;
import com.example.models.Partida;

public class PartidaRepository {
    PartidaDAOImpl partidadaoimpl;

    public PartidaRepository() throws SQLException {
        this.partidadaoimpl = new PartidaDAOImpl();
    }

    public boolean SalvarPartida(Partida partida) throws SQLException {
        return partidadaoimpl.salvar_Partida(partida);
    }

    public boolean AtualizarPartida(Partida partida) throws SQLException {
        return partidadaoimpl.atualizar_Partida(partida);
    }

    public boolean RemoverPartida(int id) throws SQLException {
        return partidadaoimpl.remover_Partida(id);
    }

    public List<Partida> listarPartida() {
        return partidadaoimpl.listar_Partida();  
    }

    public boolean SalvarPartidaPlanejada(Partida partida) throws SQLException {
        return partidadaoimpl.salvar_Partida_Planejada(partida);
    }

    public boolean AtualizarPartidaPlanejada(Partida partida) throws SQLException {
        return partidadaoimpl.atualizar_Partida_Planejada(partida);
    }

    
    public List<Partida> listarPartidaPlanejada() {
        return partidadaoimpl.listar_Partida_Planejada();  
    }

    public List<Partida> listarComboBox() {
        return partidadaoimpl.listar_ComboBox();  
    }

}
