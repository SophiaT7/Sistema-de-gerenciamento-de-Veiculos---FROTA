package com;

import dao.Dao;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Utilizacao;
import modelo.Veiculo;

public class ConsultaController {

    @FXML private TextField campoPlaca;
    @FXML private TableView<Utilizacao> tabelaUtilizacoes;
    @FXML private TableColumn<Utilizacao, String> colDataRetirada;
    @FXML private TableColumn<Utilizacao, String> colHoraRetirada;
    @FXML private TableColumn<Utilizacao, String> colMotorista;
    @FXML private TableColumn<Utilizacao, String> colOperador;
    @FXML private TableColumn<Utilizacao, String> colDataDevolucao;
    @FXML private TableColumn<Utilizacao, String> colHoraDevolucao;
    @FXML private TableColumn<Utilizacao, String> colStatus;

    private Dao<Utilizacao> daoUtilizacao;
    private Dao<Veiculo> daoVeiculo;
    private ObservableList<Utilizacao> listaUtilizacoes;

    private final DateTimeFormatter dataFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter horaFmt = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        daoUtilizacao = new Dao<>(Utilizacao.class);
        daoVeiculo = new Dao<>(Veiculo.class);

        // Configura as colunas
        colDataRetirada.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getDataRetirada() != null ? u.getValue().getDataRetirada().format(dataFmt) : "-"
            ));

        colHoraRetirada.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getHoraRetirada() != null ? u.getValue().getHoraRetirada().format(horaFmt) : "-"
            ));

        colMotorista.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getMotorista() != null ? u.getValue().getMotorista().getNome() : "-"
            ));

        colOperador.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getOperador() != null ? u.getValue().getOperador().getLogin() : "-"
            ));

        colDataDevolucao.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getDataDevolucao() != null ? u.getValue().getDataDevolucao().format(dataFmt) : "-"
            ));

        colHoraDevolucao.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getHoraDevolucao() != null ? u.getValue().getHoraDevolucao().format(horaFmt) : "-"
            ));

        colStatus.setCellValueFactory(u ->
            new javafx.beans.property.SimpleStringProperty(
                u.getValue().getDataDevolucao() == null ? "Em uso" : "Devolvido"
            ));
    }

    @FXML
    private void buscarUtilizacoes() {
        String placa = campoPlaca.getText().trim().toUpperCase();

        if (placa.isEmpty()) {
            mostrarAlerta("Digite uma placa para buscar.");
            return;
        }

        Veiculo veiculo = daoVeiculo.buscarPorChave("placa", placa);
        if (veiculo == null) {
            mostrarAlerta("Veículo com placa " + placa + " não encontrado!");
            tabelaUtilizacoes.setItems(FXCollections.observableArrayList());
            return;
        }

        List<Utilizacao> todas = daoUtilizacao.listarTodos().stream()
                .filter(u -> u.getVeiculo() != null &&
                        u.getVeiculo().getPlaca() != null &&
                        u.getVeiculo().getPlaca().trim().equalsIgnoreCase(placa))
                .collect(Collectors.toList());

        if (todas.isEmpty()) {
            mostrarAlerta("Nenhum registro encontrado para o veículo " + placa + ".");
        }

        listaUtilizacoes = FXCollections.observableArrayList(todas);
        tabelaUtilizacoes.setItems(listaUtilizacoes);
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
