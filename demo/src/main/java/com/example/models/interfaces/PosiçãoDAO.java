package com.example.models.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.models.Posição;

public interface PosiçãoDAO {
    public boolean salvar(Posição posição) throws SQLException;

    public ArrayList<Posição> listar_Posições();
}
