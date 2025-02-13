package com.example.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.App;
import com.example.models.Time;
import com.example.repositories.TimeRepository;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaInicialUsuário implements Initializable {

    @FXML
    private Button elenco;

    @FXML
    private Button jogos;

    @FXML
    private TableColumn<Time, Integer> part_jog;

    @FXML
    private TableColumn<Time, Integer> pontos_col;

    @FXML
    private TableColumn<Time, Integer> saldo_col;

    @FXML
    private TableColumn<Time, String> time_col;

    @FXML
    private TableView<Time> camp_table;

    @FXML
    private Button voltar_login;

    TimeRepository timerepository;

    @FXML
    void rota_TelaElenco(ActionEvent event) {
        App.MudarTela("elencoUsuário");
    }

    @FXML
    void rota_TelaJogos(ActionEvent event) {
        App.MudarTela("jogosUsuário");
    }

    @FXML
    void rota_TelaLogin(ActionEvent event) {
        App.MudarTela("login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        camp_table.refresh();

        time_col.setCellValueFactory(new PropertyValueFactory<>("nome_time"));
        pontos_col.setCellValueFactory(new PropertyValueFactory<>("pontuação_time"));
        saldo_col.setCellValueFactory(new PropertyValueFactory<>("saldo_gols_time"));
        part_jog.setCellValueFactory(new PropertyValueFactory<>("quant_partidas_jogadas_time"));

        try {
            timerepository = new TimeRepository();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        camp_table.setItems(FXCollections.observableArrayList(timerepository.listarTime()));

    }

}
