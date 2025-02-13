package com.example.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Partida;
import com.example.models.Time;
import com.example.repositories.PartidaRepository;
import com.example.repositories.TimeRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaJogosAdm implements Initializable {

    @FXML
    private DatePicker DP_data_jogo;

    @FXML
    private TextField TF_placar;

    @FXML
    private Button adicionar_partida;

    @FXML
    private Button alterar_partida;

    @FXML
    private Button alterar_partida_planejada;

    @FXML
    private Button deletar_partida;

    @FXML
    private Button deletar_partida_planejada;

    @FXML
    private TableView<Partida> table_view1;

    @FXML
    private TableView<Partida> table_view2;

    @FXML
    private ComboBox<Time> CB_time_adv;

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
    TimeRepository timeRepository;

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

        carregarTimes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    @FXML
    void rota_TelaInicial(ActionEvent event) {
        App.MudarTela("inicioAdm");
    }

    @FXML
    void Adicionar_Partida(ActionEvent event) throws SQLException {
        partidaRepository = new PartidaRepository();
        String placar = TF_placar.getText();
        Time timeSelecionado = CB_time_adv.getSelectionModel().getSelectedItem();
        LocalDate dataSelecionada = DP_data_jogo.getValue();
        LocalDate hoje = LocalDate.now();
        
        if (timeSelecionado == null|| dataSelecionada == null) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }

        try {
        if(TF_placar.getText().isEmpty()) {
            if (dataSelecionada.isBefore(hoje)) {
                JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida planejada só pode ser marcada para datas futuras.");
            }
            Partida partida = new Partida(Date.valueOf(dataSelecionada), timeSelecionado.getId_time_pk());
            boolean cadastro_sucesso_partida_planejada = partidaRepository.SalvarPartidaPlanejada(partida);

            if (cadastro_sucesso_partida_planejada) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                att_tabela_planejada();
                table_view1.refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar partida.");
            }
            limparCampos();
        }
        else {
            if (dataSelecionada.isAfter(hoje)) {
                JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida jogada só pode ser marcada para datas passadas.");
            }
            Partida partida = new Partida(Date.valueOf(dataSelecionada), placar, timeSelecionado.getId_time_pk());
            boolean cadastro_sucesso_partida = partidaRepository.SalvarPartida(partida);

            if (cadastro_sucesso_partida) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                att_tabela();
                table_view2.refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o partida.");
            }
            limparCampos();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
    }
    }


    @FXML
    void Alterar_Partida_Planejada(ActionEvent event) throws SQLException {
        Partida partidaPlanejadaSelecionada = table_view1.getSelectionModel().getSelectedItem();
    
    if (partidaPlanejadaSelecionada != null) {
        String novoPlacar = TF_placar.getText();
        Time novoTime = CB_time_adv.getSelectionModel().getSelectedItem();
        LocalDate novaData = DP_data_jogo.getValue();
        LocalDate hoje = LocalDate.now();

        if (novaData == null || novoTime == null) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            return;
        }

        // Verifica se o placar foi preenchido
        boolean passouParaJogada = novoPlacar != null && !novoPlacar.trim().isEmpty();

        if (novoPlacar == null || novoPlacar.trim().isEmpty()) {
            if (novaData.isBefore(hoje)) {
                JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida planejada só pode ser marcada para datas futuras.");
            }
            partidaPlanejadaSelecionada.setPlacar_partida(null); // Define como null se estiver vazio
        } else {
            if (novaData.isAfter(hoje)) {
                JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida jogada só pode ser marcada para datas passadas.");
            }
            partidaPlanejadaSelecionada.setPlacar_partida(novoPlacar);
        }
        partidaPlanejadaSelecionada.setData_partida(Date.valueOf(novaData));
        partidaPlanejadaSelecionada.setId_time_pk_fk(novoTime.getId_time_pk());

        boolean atualizada = partidaRepository.AtualizarPartida(partidaPlanejadaSelecionada);

        if (atualizada) {
            JOptionPane.showMessageDialog(null, "Partida atualizada com sucesso!");

            if (passouParaJogada) {
                // Move a partida para a tabela jogada
                table_view1.getItems().remove(partidaPlanejadaSelecionada);
                table_view2.getItems().add(partidaPlanejadaSelecionada);
                att_tabela(); // Atualiza a tabela jogada
            } else {
                // Mantém a partida na tabela planejada
                att_tabela_planejada(); // Atualiza a tabela planejada
            }

            table_view1.refresh();
            table_view2.refresh();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a partida.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecione uma partida planejada para atualizar.");
    }
}

    @FXML
    void Alterar_Partida(ActionEvent event) throws SQLException {
        Partida partidaSelecionada = table_view2.getSelectionModel().getSelectedItem();

        if (partidaSelecionada != null) {
            String novoPlacar = TF_placar.getText();
            Time timeSelecionado = CB_time_adv.getSelectionModel().getSelectedItem();
            LocalDate novaData = DP_data_jogo.getValue();
            LocalDate hoje = LocalDate.now();
    
            if (novaData == null || timeSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                return;
            }
    
            // Verifica se o placar foi apagado
            boolean voltouParaPlanejada = novoPlacar == null || novoPlacar.trim().isEmpty();

            if (novoPlacar == null || novoPlacar.trim().isEmpty()) {
                if(novaData.isBefore(hoje)) {
                    JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida planejada só pode ser marcada para datas futuras.");
                }
                partidaSelecionada.setPlacar_partida(null); // Define como null se estiver vazio
            } else {
                if (novaData.isAfter(hoje)) {
                    JOptionPane.showMessageDialog(null, "Data inválida!\nUma partida jogada só pode ser marcada para datas passadas.");
                }
                partidaSelecionada.setPlacar_partida(novoPlacar);
            }
            partidaSelecionada.setData_partida(Date.valueOf(novaData));
            partidaSelecionada.setId_time_pk_fk(timeSelecionado.getId_time_pk());
    
            boolean atualizada = partidaRepository.AtualizarPartida(partidaSelecionada);
    
            if (atualizada) {
                JOptionPane.showMessageDialog(null, "Partida atualizada com sucesso!");
    
                if (voltouParaPlanejada) {
                    // Remove da tabela jogada e adiciona na planejada
                    table_view2.getItems().remove(partidaSelecionada);
                    att_tabela_planejada(); // Atualiza a tabela planejada
                } else {
                    att_tabela(); // Atualiza a tabela jogada
                }
    
                table_view1.refresh();
                table_view2.refresh();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar a partida.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma partida jogada para atualizar.");
        }
    }

    @FXML
    void Remover_Partida_Planejada(ActionEvent event) throws SQLException {
        Partida partidaPlanejadaSelecionada = table_view1.getSelectionModel().getSelectedItem();
        if (partidaPlanejadaSelecionada != null) {
            System.out.println(partidaPlanejadaSelecionada.getId_partida_pk());
            boolean deleted = partidaRepository.RemoverPartida(partidaPlanejadaSelecionada.getId_partida_pk());
            if (deleted) {
                ObservableList<Partida> partidas = table_view1.getItems();
                partidas.remove(partidaPlanejadaSelecionada);
                JOptionPane.showMessageDialog(null, "Partida deletada com sucesso!");
                att_tabela_planejada();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar partida!");
            }
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Partida para deletar");
        }
    }

    @FXML
    void Remover_Partida(ActionEvent event) throws SQLException {

        Partida partidaSelecionada = table_view2.getSelectionModel().getSelectedItem();
        if (partidaSelecionada != null) {
            boolean deleted = partidaRepository.RemoverPartida(partidaSelecionada.getId_partida_pk());
            if (deleted) {
                ObservableList<Partida> partidas = table_view2.getItems();
                partidas.remove(partidaSelecionada);
                JOptionPane.showMessageDialog(null, "Partida deletada com sucesso!");
                att_tabela();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar partida!");
            }
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma partida para deletar");
        }
    }

    private void limparCampos() {
        TF_placar.clear();
        CB_time_adv.setValue(null);
        DP_data_jogo.setValue(null);
    }

    private void att_tabela_planejada() throws SQLException {
        partidaplanejadaRepository = new PartidaRepository();
        table_view1.setItems(FXCollections.observableArrayList(partidaplanejadaRepository.listarPartidaPlanejada()));
    }

    private void att_tabela() throws SQLException {
        partidaRepository = new PartidaRepository();
        table_view2.setItems(FXCollections.observableArrayList(partidaRepository.listarPartida()));
    }

    private void carregarTimes() throws SQLException {
        timeRepository = new TimeRepository();
        try {
            List<Time> times = timeRepository.listarComboBox();

            if (times != null) {
                CB_time_adv.setItems(FXCollections.observableArrayList(times));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os times: " + e.getMessage());
        }
    }

}
