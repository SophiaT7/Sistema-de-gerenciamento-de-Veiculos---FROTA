package com;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Operador;

public class TelaControler {

    @FXML private TextField loginOperador;
    @FXML private PasswordField senhaOperador;

    private Dao<Operador> daoOperador;

    @FXML
    public void initialize() {
        daoOperador = new Dao<>(Operador.class);
    }

    @FXML
    private void entrar() {
        Operador operador = daoOperador.buscarPorChave("login", loginOperador.getText().trim());

        if (operador == null) {
            mostrarAlerta("Operador não encontrado!");
            return;
        }

        if (!operador.getSenha().equals(senhaOperador.getText().trim())) {
            mostrarAlerta("Senha incorreta!");
            return;
        }

        abrirTelaPrincipal();
    }

    private void abrirTelaPrincipal() {
        try {
            App.setRoot("telaInicial");

            // Fecha a tela atual de login
            Stage atual = (Stage) loginOperador.getScene().getWindow();
            atual.close();

        } catch (IOException e) {
            mostrarAlerta("Erro ao abrir a tela principal: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
