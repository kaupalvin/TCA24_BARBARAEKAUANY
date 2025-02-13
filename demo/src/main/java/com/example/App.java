package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    private static Scene telaLogin;
    private static Scene telaLoginAdm;
    private static Scene telaCadastro;
    private static Scene telaInicialAdm;
    private static Scene telaInicialUsuário;
    private static Scene telaElencoAdm;
    private static Scene telaElencoUsuário;
    private static Scene telaJogosUsuário;
    private static Scene telaJogosAdm;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("projeto-integrador-barbara-e-kauany");
        

        Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("telacadastro.fxml"));
        telaCadastro = new Scene(fxmlCadastro, 1000, 600);
        
        Parent fxmlLoginAdm = FXMLLoader.load(getClass().getResource("telaloginadm.fxml"));
        telaLoginAdm = new Scene(fxmlLoginAdm,1000, 600);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("telalogin.fxml"));
        telaLogin = new Scene(fxmlLogin, 1000, 600);

        Parent fxmlInicialAdm = FXMLLoader.load(getClass().getResource("telaInicial.fxml"));
        telaInicialAdm = new Scene(fxmlInicialAdm,1000, 600);

        Parent fxmlInicialUsuário = FXMLLoader.load(getClass().getResource("telainicialusuário.fxml"));
        telaInicialUsuário = new Scene(fxmlInicialUsuário,1000, 600);

        Parent fxmlElencoAdm = FXMLLoader.load(getClass().getResource("telajogadoresadm.fxml"));
        telaElencoAdm = new Scene(fxmlElencoAdm,1000, 600);

        Parent fxmlElencoUsuário = FXMLLoader.load(getClass().getResource("telajogadoresusuario.fxml"));
        telaElencoUsuário = new Scene(fxmlElencoUsuário,1000, 600);

        Parent fxmlJogosAdm = FXMLLoader.load(getClass().getResource("telajogosadm.fxml"));
        telaJogosAdm = new Scene(fxmlJogosAdm,1000, 600);

        Parent fxmlJogosUsuário = FXMLLoader.load(getClass().getResource("telajogosusuário.fxml"));
        telaJogosUsuário = new Scene(fxmlJogosUsuário,1000, 600);


        primaryStage.setScene(telaLogin);
        primaryStage.show();
    }

    public static void MudarTela(String tela) {
        switch(tela){

            case "cad":
            stage.setScene(telaCadastro);
            break;

            case "login":
            stage.setScene(telaLogin);
            break;

            case "loginAdm":
            stage.setScene(telaLoginAdm);
            break;

            case "inicioAdm":
            stage.setScene(telaInicialAdm);
            break;

            case "inicioUsuário":
            stage.setScene(telaInicialUsuário);
            break;

            case "elencoAdm":
            stage.setScene(telaElencoAdm);
            break;

            case "elencoUsuário":
            stage.setScene(telaElencoUsuário);
            break;
            
            case "jogosAdm":
            stage.setScene(telaJogosAdm);
            break;

            case "jogosUsuário":
            stage.setScene(telaJogosUsuário);
            break;

        }
    }

    public static void main(String[] args) {
        launch();
    }

}