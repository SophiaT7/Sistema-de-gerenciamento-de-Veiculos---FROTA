package com;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.ControleUtilizacao;
import modelo.Motorista;
import modelo.Operador;
import modelo.Veiculo;

public class RetiradaController {

    @FXML private TextField loginOperador;
    @FXML private TextField senhaOperador;
    @FXML private TextField nomeMotorista;
    @FXML private TextField placaVeiculo;

    private Dao<Operador> daoOperador;
    private Dao<Motorista> daoMotorista;
    private Dao<Veiculo> daoVeiculo;
    private ControleUtilizacao controle;

    @FXML
    public void initialize() {
        daoOperador = new Dao<>(Operador.class);
        daoMotorista = new Dao<>(Motorista.class);
        daoVeiculo = new Dao<>(Veiculo.class);
        controle = new ControleUtilizacao();
    }

    @FXML
    private void registrarRetirada() {
        Operador op = daoOperador.buscarPorChave("login", loginOperador.getText());
System.out.println("Operador encontrado: " + (op != null ? op.getLogin() : "NENHUM"));
        
        if (op == null) {
            mostrarAlerta("Operador não encontrado!");
            return;
        }

        Motorista motorista = daoMotorista.buscarPorChave("nome", nomeMotorista.getText());
        if (motorista == null) {
            mostrarAlerta("Motorista não encontrado!");
            return;
        }

        Veiculo veiculo = daoVeiculo.buscarPorChave("placa", placaVeiculo.getText());
        if (veiculo == null) {
            mostrarAlerta("Veículo não encontrado!");
            return;
        }

    controle.registrarRetirada(op, senhaOperador.getText(), motorista, veiculo);
    mostrarAlerta("🚗 Retirada registrada com sucesso!\nOperador: " + op.getLogin() +
                  "\nMotorista: " + motorista.getNome() +
                  "\nVeículo: " + veiculo.getPlaca());
        limparCampos();
    }

    private void limparCampos() {
        loginOperador.clear();
        senhaOperador.clear();
        nomeMotorista.clear();
        placaVeiculo.clear();
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("telaInicial");
    }

    private void mostrarAlerta(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
