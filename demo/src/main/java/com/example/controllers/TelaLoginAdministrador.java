package com.example.controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.example.App;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class TelaLoginAdministrador {

    @FXML
    private PasswordField PF_senha_acesso;

    @FXML
    private void realizar_Login() throws IOException {

        String senha = PF_senha_acesso.getText();

        String SenhaDeAcesso = "KABI";

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            PF_senha_acesso.clear();
            return;
        }

        if (senha.equals(SenhaDeAcesso)) {
            App.MudarTela("inicioAdm");
            PF_senha_acesso.clear();
        } else {
            JOptionPane.showMessageDialog(null, "Senha inválida!\nPor favor, digite a senha correta");
        }

    }

    @FXML
    private void rota_TelaLogin() throws IOException {
        App.MudarTela("login");
    }

}
