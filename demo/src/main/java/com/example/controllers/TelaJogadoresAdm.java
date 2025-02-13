package com.example.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Jogador;
import com.example.models.Partida;
import com.example.models.Partida_Jogador;
import com.example.models.Pessoa;
import com.example.models.Posição;
import com.example.repositories.ElencoRepository;
import com.example.repositories.PartidaRepository;
import com.example.repositories.Partida_JogadorRepository;
import com.example.repositories.PessoaCadastroRepository;
import com.example.repositories.PosiçãoRepository;

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
import javafx.scene.image.ImageView;

public class TelaJogadoresAdm implements Initializable {

    @FXML
    private ComboBox<Partida> CB_jogos;

    @FXML
    private ComboBox<Posição> CB_posição;

    @FXML
    private DatePicker data_nasc;

    @FXML
    private Button adicionar_jog;

    @FXML
    private Button adicionar_pj;

    @FXML
    private Button alterar_jog;

    @FXML
    private Button excl_jog;

    @FXML
    private ImageView foto;

    @FXML
    private TextField gols;

    @FXML
    private TextField nome;

    @FXML
    private TextField num;

    @FXML
    private TableView<Jogador> table_view;

    @FXML
    private TableColumn<Jogador, Integer> tv_gols;

    @FXML
    private TableColumn<Jogador,Integer> tv_idade;

    @FXML
    private TableColumn<Jogador, String> tv_nome;

    @FXML
    private TableColumn<Jogador, String> tv_num;

    @FXML
    private TableColumn<Partida_Jogador, Integer> tv_pj;

    @FXML
    private TableColumn<Jogador, String> tv_posição;

    @FXML
    private Button voltar_inicio;

    ElencoRepository elencoRepository;
    PosiçãoRepository posiçãoRepository;
    PessoaCadastroRepository pessoacadastroRepository;
    PartidaRepository partidaRepository;
    Partida_JogadorRepository partidajogadorRepository;

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
        carregarPosições();
        carregarJogos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    @FXML
    void Adicionar_Jogador(ActionEvent event) throws SQLException {

        pessoacadastroRepository = new PessoaCadastroRepository();
        elencoRepository = new ElencoRepository();
        partidajogadorRepository = new Partida_JogadorRepository();

        String nome_jogador = nome.getText();
        String num_camisa = num.getText();
        String gols_jogador = gols.getText();
        LocalDate dt_nasc = data_nasc.getValue();
        Posição posição = CB_posição.getSelectionModel().getSelectedItem();
        
        if (nome_jogador.isEmpty() || num_camisa.isEmpty() || gols_jogador.isEmpty() || dt_nasc == null || posição == null) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }

        try {
            Pessoa pessoa = new Pessoa(nome_jogador);
            System.out.println(pessoa.toString());
            boolean resultado = pessoacadastroRepository.SalvarCadastroPessoa(pessoa);
            if (resultado) {
                int idPessoa = pessoa.getId_pessoa_pk();
            if (idPessoa == 0) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar ID da pessoa.");
                return;
            }
            Jogador jogador = new Jogador(idPessoa, posição.getId_posição_pk(), Date.valueOf(dt_nasc), num_camisa, Integer.parseInt(gols_jogador));
            boolean cadastro_sucesso_jogador = elencoRepository.SalvarJogador(jogador);
            
            if (cadastro_sucesso_jogador) {
                Partida_Jogador partidajogador = new Partida_Jogador(idPessoa, 0);

                boolean salvouNoBanco = partidajogadorRepository.salvarPartidaJogador(partidajogador);
                if(salvouNoBanco){
                    System.out.println("SALVOU PJ COMO 0 NO BANCO");
                }

                JOptionPane.showMessageDialog(null, "Jogador cadastrado com sucesso!");
                att_tabela();
                limparCampos();
            } else {
                System.out.println("Erro ao salvar o jogador.");
            }
        }    
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao salvar a pessoa: " + e.getMessage());
    }
        
    }

    @FXML
    void Adicionar_Partida_Jogada(ActionEvent event) throws SQLException {
        partidajogadorRepository = new Partida_JogadorRepository();
        elencoRepository = new ElencoRepository();

        Jogador selecionaJogador = table_view.getSelectionModel().getSelectedItem();
        if (selecionaJogador == null) {
            JOptionPane.showMessageDialog(null, "Nenhum jogador selecionado!");
            return;
        }
        else {
        int idPessoa = selecionaJogador.getIdPessoaPkFk(); 

        Partida partidaSelecionada = CB_jogos.getSelectionModel().getSelectedItem();


            if (partidaSelecionada != null) {

                Partida_Jogador partidaJogador = new Partida_Jogador(idPessoa, partidaSelecionada.getId_partida_pk());

                partidajogadorRepository.salvarPartidaJogador(partidaJogador);//salva na tabela da relação partida jogador

                int cont = partidajogadorRepository.contarPartidasJogador(idPessoa); //faz a contagem inserções com o valor de id Pessoa nessa tabela

                boolean sucesso = elencoRepository.AtualizarPJ(idPessoa, cont); //atualiza a variavel pj da tabela jogador
                if(sucesso) {
                JOptionPane.showMessageDialog(null, "Partida adicionada ao jogador!");
                System.out.println(selecionaJogador.getTotalPartidas());
                att_tabela();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar o total de partidas do jogador!");
                }
            }
        }
    }
    
    @FXML
    void Atualizar_Jogador(ActionEvent event) throws SQLException {
        pessoacadastroRepository = new PessoaCadastroRepository();
        elencoRepository = new ElencoRepository();


        Jogador jogadorSelecionado = table_view.getSelectionModel().getSelectedItem();

        if (jogadorSelecionado == null ) {
            JOptionPane.showMessageDialog(null, "Selecione um time para alterar!");
            return;
        }

            jogadorSelecionado.setNomePessoa(nome.getText());
            jogadorSelecionado.setGolsJogador(Integer.parseInt(gols.getText()));
            jogadorSelecionado.setNumCamisaJogador(num.getText());
            Posição novaPos = CB_posição.getSelectionModel().getSelectedItem();
            LocalDate novaData = data_nasc.getValue();
            
            if (novaData == null || novaPos == null || (nome.getText()).isEmpty() || (num.getText()).isEmpty() || (gols.getText()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                return;
            }

            try {
                boolean alteracaoPessoa = pessoacadastroRepository.AtualizarCadastroPessoa(jogadorSelecionado.getIdPessoaPkFk(), jogadorSelecionado.getNomePessoa());

                if (alteracaoPessoa) {
                    jogadorSelecionado.setDtNascJogador(Date.valueOf(novaData));
                    jogadorSelecionado.setIdPosicaoPkFk(novaPos.getId_posição_pk());
                    boolean alteracaoJogador = elencoRepository.AtualizarJogador(jogadorSelecionado);
                    if (alteracaoJogador) {
                        JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
                    }
                    ObservableList<Jogador> jogadores = table_view.getItems();
                    int index = jogadores.indexOf(jogadorSelecionado);
                    if (index != -1) {
                        jogadores.set(index, jogadorSelecionado); // Atualiza o item na lista
                    }
                    table_view.refresh();
                    att_tabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar jogador");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            limparCampos();

    }

    @FXML
    void Excluir_Jogador(ActionEvent event) throws SQLException {
        Jogador jogadorSelecionado = table_view.getSelectionModel().getSelectedItem();
        if (jogadorSelecionado != null) {
            boolean deletedPJ = partidajogadorRepository.RemoverPartidasJogador(jogadorSelecionado.getIdPessoaPkFk());
            if (deletedPJ) {
                    boolean deletedJ = elencoRepository.RemoverTime(jogadorSelecionado.getIdPessoaPkFk());
                    if (deletedJ) {
                        ObservableList<Jogador> jogadores = table_view.getItems();
                        jogadores.remove(jogadorSelecionado);
                        boolean deletedP = pessoacadastroRepository.RemoverCadastroPessoa(jogadorSelecionado.getIdPessoaPkFk());
                        if (deletedP) {
                            JOptionPane.showMessageDialog(null, "Erro ao deletar pessoa!");
                        }
                    }
                JOptionPane.showMessageDialog(null, "Jogador deletado com sucesso!");
                att_tabela();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar jogador!");
            }
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um jogador para deletar!");
        }
    }

    @FXML
    void rota_TelaInicial(ActionEvent event) {
        App.MudarTela("inicioAdm");
    }

    private void att_tabela() throws SQLException {
        partidajogadorRepository = new Partida_JogadorRepository();
        elencoRepository = new ElencoRepository();

        try {
            table_view.setItems(FXCollections.observableArrayList(elencoRepository.listarElenco()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a tabela: " + e.getMessage());
        }
    }

    private void carregarPosições() throws SQLException {
        posiçãoRepository = new PosiçãoRepository();
        try {
            List<Posição> posições = posiçãoRepository.listarPosições();

            if (posições != null && !posições.isEmpty()) {
                CB_posição.setItems(FXCollections.observableArrayList(posições));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar as posições: " + e.getMessage());
        }
    }

    private void carregarJogos() throws SQLException {
        partidaRepository = new PartidaRepository();
        try {
            List<Partida> partidas = partidaRepository.listarComboBox();

            if (partidas != null) {
                CB_jogos.setItems(FXCollections.observableArrayList(partidas));
                //att_tabela();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os jogos: " + e.getMessage());
        }
    }

    private void limparCampos(){
        nome.clear();
        num.clear();
        gols.clear();
        CB_posição.setValue(null);
        data_nasc.setValue(null);
    }
}
