package com;

import dao.Dao;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.ControleUtilizacao;
import modelo.Operador;
import modelo.Utilizacao;
import modelo.Veiculo;

public class DevolucaoController {

    @FXML private TextField loginOperador;
    @FXML private TextField senhaOperador;
    @FXML private TextField placaVeiculo;

    private Dao<Operador> daoOperador;
    private Dao<Veiculo> daoVeiculo;
    private Dao<Utilizacao> daoUtilizacao;
    private ControleUtilizacao controle;

    @FXML
    public void initialize() {
        daoOperador = new Dao<>(Operador.class);
        daoVeiculo = new Dao<>(Veiculo.class);
        daoUtilizacao = new Dao<>(Utilizacao.class);
        controle = new ControleUtilizacao();
    }

    @FXML
    private void registrarDevolucao() {
        try {
            // 🔹 1. Busca operador
            Operador op = daoOperador.buscarPorChave("login", loginOperador.getText());
            if (op == null) {
                mostrarAlerta("Operador não encontrado!");
                return;
            }

            // 🔹 2. Verifica senha
            if (!op.getSenha().equals(senhaOperador.getText())) {
                mostrarAlerta("Senha incorreta para o operador " + op.getLogin() + "!");
                return;
            }

            // 🔹 3. Busca veículo
            Veiculo veiculo = daoVeiculo.buscarPorChave("placa", placaVeiculo.getText());
            if (veiculo == null) {
                mostrarAlerta("Veículo com placa " + placaVeiculo.getText() + " não encontrado!");
                return;
            }

            // 🔹 4. Busca utilização em aberto para este veículo
            List<Utilizacao> todas = daoUtilizacao.listarTodos();
            Utilizacao emAberto = null;

            for (int i = todas.size() - 1; i >= 0; i--) {
                Utilizacao u = todas.get(i);

                if (u.getVeiculo() != null &&
                    u.getVeiculo().getPlaca() != null &&
                    u.getVeiculo().getPlaca().trim().equalsIgnoreCase(veiculo.getPlaca().trim()) &&
                    u.getDataDevolucao() == null) {

                    emAberto = u;
                    break;
                }
            }

            if (emAberto == null) {
                mostrarAlerta("Nenhuma utilização em aberto encontrada para o veículo " + veiculo.getPlaca() + ".");
                return;
            }

            // 🔹 5. Registra devolução e operador responsável
            emAberto.devolver(LocalDate.now(), LocalTime.now());
            emAberto.setOperador(op); // ✅ opcional: salva também quem devolveu

            // 🔹 6. Atualiza no banco (substitui pelo mesmo documento)
            daoUtilizacao.alterar("veiculo.placa", veiculo.getPlaca(), emAberto);

            mostrarAlerta("Devolução registrada com sucesso para o veículo " + veiculo.getPlaca() + "!");
            limparCampos();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro inesperado: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("telaInicial");
    }

    private void limparCampos() {
        loginOperador.clear();
        senhaOperador.clear();
        placaVeiculo.clear();
    }

    private void mostrarAlerta(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
