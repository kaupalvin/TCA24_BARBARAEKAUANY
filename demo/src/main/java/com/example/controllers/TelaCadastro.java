package com.example.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Pessoa;
import com.example.models.Usuario;
import com.example.repositories.PessoaCadastroRepository;
import com.example.repositories.UsuarioCadastroRepository;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastro {

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_nome;

    @FXML
    private PasswordField TF_senha;

    @FXML
    private PasswordField TF_senha_confirm;

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    UsuarioCadastroRepository usuariocadastroRepository;
    PessoaCadastroRepository pessoacadastroRepository;

    @FXML
    private void confirmar_cadastro() throws IOException, SQLException {
        usuariocadastroRepository = new UsuarioCadastroRepository();
        pessoacadastroRepository = new PessoaCadastroRepository();

        String email = TF_email.getText();
        String nome = TF_nome.getText();
        String senha = TF_senha.getText();
        String senhaConfirmacao = TF_senha_confirm.getText();

        if (email.isEmpty() || nome.isEmpty() || senha.isEmpty() || senhaConfirmacao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }

        if(senha.length() < 4) {
            JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo 4 caracteres");
            return;
        }

        if (!senha.equals(senhaConfirmacao)) {
            JOptionPane.showMessageDialog(null, "Por favor, verifique a senha informada");
            return;
        }	

        boolean emailJaExiste = usuariocadastroRepository.emailJaExiste(email);
        if (emailJaExiste) {
            JOptionPane.showMessageDialog(null, "Email já cadastrado!");
            return;
        }

        if (emailÉValido(email)) {
            Pessoa pessoa = new Pessoa(nome);
            boolean cadastro_sucesso_pessoa = pessoacadastroRepository.SalvarCadastroPessoa(pessoa);

            if (cadastro_sucesso_pessoa) {
                int idPessoa = pessoa.getId_pessoa_pk();
                if (idPessoa == 0) {
                    JOptionPane.showMessageDialog(null, "Erro ao gerar ID da pessoa.");
                    return;
                }
                Usuario usuario = new Usuario(idPessoa, email, senha);
                boolean cadastro_sucesso_usuário = usuariocadastroRepository.SalvarCadastroUsuário(usuario);

                if (cadastro_sucesso_usuário) {
                    limparCampos();
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\nPor favor, volte a tela de login");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar a pessoa.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido!");
        }
    }

    @FXML
    private void rota_TelaLogin() throws IOException {
        limparCampos();
        App.MudarTela("login");
    }
    
    private static boolean emailÉValido(String email) {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void limparCampos(){
        TF_email.clear();
        TF_nome.clear();
        TF_senha.clear();
        TF_senha_confirm.clear();
    }

}