package com.example.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.models.Usuario;
import com.example.repositories.UsuarioLoginRepository;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TelaLogin {

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_senha;

    UsuarioLoginRepository usuarioRepository;

    @FXML
    private void rota_TelaCadastro() throws IOException {
        App.MudarTela("cad");
    }

    @FXML
    private void rota_TelaLoginAdm() throws IOException {
        App.MudarTela("loginAdm");
    }

    @FXML
    private void RealizarLogin() throws IOException, SQLException {
        usuarioRepository = new UsuarioLoginRepository();

        String email = TF_email.getText();
        String senha = TF_senha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;
        }
        Usuario usuario = new Usuario(email, senha);

        boolean Login_sucesso = usuarioRepository.RealizarLogin(usuario);
        if (Login_sucesso) {
            App.MudarTela("inicioUsuário");
            TF_email.clear();
            TF_senha.clear();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, crie uma conta!");
        }

    }
}