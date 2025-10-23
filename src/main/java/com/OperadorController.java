package com;

import dao.Dao;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Operador;

public class OperadorController {

    private Dao<Operador> dao;

    @FXML
    private TextField login;

    @FXML
    private TextField senha;

    @FXML
    private TableView<Operador> tabelaOperadores;

    @FXML
    private TableColumn<Operador, Integer> colCodigo;

    @FXML
    private TableColumn<Operador, String> colLogin;

    @FXML
    private TableColumn<Operador, String> colSenha;

    private ObservableList<Operador> listaOperadores;

    @FXML
    public void initialize() {
        dao = new Dao<>(Operador.class);

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        carregarOperadores();
    }

    @FXML
    private void gravar() {
        if (login.getText().isEmpty() || senha.getText().isEmpty()) {
            mostrarAlerta("Preencha todos os campos!");
            return;
        }
        
        Operador operador = new Operador(login.getText(), senha.getText());

        dao.inserir(operador);
        mostrarAlerta("Operador cadastrado com sucesso!");

        carregarOperadores();
        limparCampos();
    }

    @FXML
    private void excluir() {
    Operador selecionado = tabelaOperadores.getSelectionModel().getSelectedItem();

    if (selecionado == null) {
        mostrarAlerta("Selecione um operador para excluir!");
        return;
    }

    // Confirmação antes da exclusão
    Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
    confirmacao.setTitle("Confirmar exclusão");
    confirmacao.setHeaderText("Deseja realmente excluir este operador?");
    confirmacao.setContentText(
        "Login: " + selecionado.getLogin() + "\n" +
        "Código: " + selecionado.getCodigo()
    );

    confirmacao.showAndWait().ifPresent(resposta -> {
        if (resposta == ButtonType.OK) {
            // 🔹 Exclui pelo login (texto) em vez do código (Integer)
            boolean removido = dao.excluir("login", selecionado.getLogin());
            if (removido) {
                mostrarAlerta("Operador " + selecionado.getLogin() + " foi excluído com sucesso!");
                carregarOperadores();
            } else {
                mostrarAlerta("Erro ao excluir operador " + selecionado.getLogin() + "!");
            }
        }
    });
    }

    @FXML
    private void mostrar() {
        carregarOperadores();
        mostrarAlerta("Lista de operadores atualizada!");
    }

    private void carregarOperadores() {
        List<Operador> operadores = dao.listarTodos();
        listaOperadores = FXCollections.observableArrayList(operadores);
        tabelaOperadores.setItems(listaOperadores);
    }

    private void limparCampos() {
        login.clear();
        senha.clear();
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("telaInicial");
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
