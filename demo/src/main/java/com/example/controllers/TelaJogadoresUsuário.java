package com.example.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Jogador;
import com.example.repositories.ElencoRepository;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class TelaJogadoresUsuário implements Initializable {

    @FXML
    private ImageView foto;

    @FXML
    private TableView<Jogador> table_view;

    @FXML
    private TableColumn<Jogador, Integer> tv_gols;

    @FXML
    private TableColumn<Jogador, Integer> tv_idade;

    @FXML
    private TableColumn<Jogador, String> tv_nome;

    @FXML
    private TableColumn<Jogador, String> tv_num;

    @FXML
    private TableColumn<Jogador, Integer> tv_pj;

    @FXML
    private TableColumn<Jogador, String> tv_posição;

    @FXML
    private Button voltar_inicio;

    ElencoRepository elencoRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        tv_nome.setCellValueFactory(new PropertyValueFactory<>("nomePessoa"));
        tv_num.setCellValueFactory(new PropertyValueFactory<>("numCamisaJogador"));
        tv_gols.setCellValueFactory(new PropertyValueFactory<>("golsJogador"));
        tv_pj.setCellValueFactory(new PropertyValueFactory<>("totalPartidas"));
        tv_idade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        tv_posição.setCellValueFactory(new PropertyValueFactory<>("descricaoPosicao"));

        att_tabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    @FXML
    void rota_TelaInicial(ActionEvent event) {
        App.MudarTela("inicioUsuário");
    }

    private void att_tabela() throws SQLException {
        elencoRepository = new ElencoRepository();

        try {
            table_view.setItems(FXCollections.observableArrayList(elencoRepository.listarElenco()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a tabela: " + e.getMessage());
        }
    }

   // @Override
    //public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    //}
}