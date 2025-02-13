package com.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Time;
import com.example.repositories.TimeRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaInicial implements Initializable {

    @FXML
    private TextField TF_id_add;

    @FXML
    private TextField TF_nome;

    @FXML
    private TextField TF_pj;

    @FXML
    private TextField TF_pontos;

    @FXML
    private TextField TF_sg;

    @FXML
    private Button add_time;

    @FXML
    private Button alterar_time;

    @FXML
    private Button att_table;

    @FXML
    private Button excluir_time;

    @FXML
    private Button telaElenco;

    @FXML
    private Button telaJogos;

    @FXML
    private TableView<Time> camp_table;

    @FXML
    private TableColumn<Time, String> tv_nome;

    @FXML
    private TableColumn<Time, Integer> tv_pj;

    @FXML
    private TableColumn<Time, Integer> tv_pontos;

    @FXML
    private TableColumn<Time, Integer> tv_sg;

    @FXML
    private Button voltar_login;

    TimeRepository timerepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tv_nome.setCellValueFactory(new PropertyValueFactory<>("nome_time"));
        tv_pontos.setCellValueFactory(new PropertyValueFactory<>("pontuação_time"));
        tv_sg.setCellValueFactory(new PropertyValueFactory<>("saldo_gols_time"));
        tv_pj.setCellValueFactory(new PropertyValueFactory<>("quant_partidas_jogadas_time"));

        try {
            timerepository = new TimeRepository();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        att_tabela();

    }

    @FXML
    private void salvar_Time() throws IOException, SQLException {
        timerepository = new TimeRepository();
        String nome = TF_nome.getText();
        String pontos_s = TF_pontos.getText();
        String sg_s = TF_sg.getText();
        String pj_s = TF_pj.getText();

        if (nome.isEmpty() || pontos_s.isEmpty() || sg_s.isEmpty() || pj_s.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }

        boolean timeJaExiste = timerepository.timeJaExiste(nome);
        if (timeJaExiste) {
            JOptionPane.showMessageDialog(null, "Time já cadastrado!");
            return;
        }

        int pontos = Integer.parseInt(pontos_s);
        int sg = Integer.parseInt(sg_s);
        int pj = Integer.parseInt(pj_s);

        Time time = new Time(nome, pontos, sg, pj);
        boolean cadastro_sucesso_time = timerepository.SalvarTime(time);

        if (cadastro_sucesso_time) {
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            att_tabela();
            camp_table.refresh();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o time.");
        }
        limparCampos();
    }

    @FXML
    void alterar_Time(ActionEvent event) throws SQLException {
        Time selecionaTime = camp_table.getSelectionModel().getSelectedItem();

        if (selecionaTime != null) {

            selecionaTime.setNome_time(TF_nome.getText());
            selecionaTime.setPontuação_time(Integer.parseInt(TF_pontos.getText()));
            selecionaTime.setQuant_partidas_jogadas_time(Integer.parseInt(TF_pj.getText()));
            selecionaTime.setSaldo_gols_time(Integer.parseInt(TF_sg.getText()));

            if ((TF_nome.getText()).isEmpty() || (TF_pontos.getText()).isEmpty() || (TF_pj.getText()).isEmpty() || (TF_sg.getText()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                return;
            }

            try {
                boolean alteração_sucesso = timerepository.AtualizarTime(selecionaTime);

                if (alteração_sucesso) {
                    JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
                    ObservableList<Time> times = camp_table.getItems();
                    int index = times.indexOf(selecionaTime);
                    if (index != -1) {
                        times.set(index, selecionaTime); // Atualiza o item na lista
                    }
                    camp_table.refresh();
                    att_tabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar time");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            limparCampos();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um time para alterar!");
        }
    }

    @FXML
    void excluir_Time(ActionEvent event) throws Exception {

        Time selecionaTime = camp_table.getSelectionModel().getSelectedItem();
        if (selecionaTime != null) {
            boolean deleted = timerepository.RemoverTime(selecionaTime.getId_time_pk());
            if (deleted) {
                ObservableList<Time> times = camp_table.getItems();
                times.remove(selecionaTime);
                JOptionPane.showMessageDialog(null, "Time deletado com sucesso!");
                att_tabela();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar o time!");
            }
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um time para deletar!");
        }

    }

    @FXML
    void rota_TelaLogin(ActionEvent event) {
        App.MudarTela("login");
    }

    @FXML
    void rota_TelaElenco(ActionEvent event) {
        App.MudarTela("elencoAdm");
    }

    @FXML
    void rota_TelaJogos(ActionEvent event) {
        App.MudarTela("jogosAdm");
    }

    private void limparCampos() {
        TF_nome.clear();
        TF_pontos.clear();
        TF_sg.clear();
        TF_pj.clear();
    }

    private void att_tabela() {
        camp_table.setItems(FXCollections.observableArrayList(timerepository.listarTime()));
    }

}