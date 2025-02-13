package com.example.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Partida;
import com.example.repositories.PartidaRepository;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaJogosUsuário implements Initializable {

    @FXML
    private TableView<Partida> table_view1;

    @FXML
    private TableView<Partida> table_view2;

    @FXML
    private TableColumn<Partida, Date> tv1_data;

    @FXML
    private TableColumn<Partida, String> tv1_adversário;

    @FXML
    private TableColumn<Partida, Date> tv2_data;

    @FXML
    private TableColumn<Partida, String> tv2_adversário;

    @FXML
    private TableColumn<Partida, String> tv2_placar;

    @FXML
    private Button voltar_inicio;

    PartidaRepository partidaRepository;
    PartidaRepository partidaplanejadaRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        tv1_data.setCellValueFactory(new PropertyValueFactory<>("data_partida"));
        tv1_adversário.setCellValueFactory(new PropertyValueFactory<>("nome_time"));
        att_tabela_planejada();

        tv2_data.setCellValueFactory(new PropertyValueFactory<>("data_partida"));
        tv2_placar.setCellValueFactory(new PropertyValueFactory<>("placar_partida"));
        tv2_adversário.setCellValueFactory(new PropertyValueFactory<>("nome_time"));
        att_tabela();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    @FXML
    void rota_TelaInicial(ActionEvent event) {
        App.MudarTela("inicioUsuário");
    }

    private void att_tabela_planejada() throws SQLException {
        partidaplanejadaRepository = new PartidaRepository();
        table_view1.setItems(FXCollections.observableArrayList(partidaplanejadaRepository.listarPartidaPlanejada()));
    }

    private void att_tabela() throws SQLException {
        partidaRepository = new PartidaRepository();
        table_view2.setItems(FXCollections.observableArrayList(partidaRepository.listarPartida()));
    }

}
