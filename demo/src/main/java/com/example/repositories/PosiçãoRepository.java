package com.example.repositories;

import java.sql.SQLException;
import java.util.List;

import com.example.DAO.PosiçãoDAOImpl;
import com.example.models.Posição;
public class PosiçãoRepository {
    PosiçãoDAOImpl posiçãodaoimpl;

    public PosiçãoRepository() throws SQLException {
        this.posiçãodaoimpl = new PosiçãoDAOImpl();
    }

    public boolean SalvarPosição(Posição posição) throws SQLException {
        return posiçãodaoimpl.salvar(posição);
    }

    public List<Posição> listarPosições() {
        return posiçãodaoimpl.listar_Posições();  
    }
}
