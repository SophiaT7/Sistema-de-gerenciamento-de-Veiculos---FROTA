package com;

import dao.Dao;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Veiculo;

public class VeiculoController {

    private Dao<Veiculo> dao;

    @FXML
    private TextField placa;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;
    
     @FXML
    private TableView<Veiculo> tabelaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> colPlaca;

    @FXML
    private TableColumn<Veiculo, String> colMarca;

    @FXML
    private TableColumn<Veiculo, String> colModelo;

    private ObservableList<Veiculo> listaVeiculos;


    @FXML
    public void initialize() {

        dao = new Dao<>(Veiculo.class);
        
        if (colPlaca != null) colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        if (colMarca != null) colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        if (colModelo != null) colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        carregarVeiculos();
    }

    @FXML
    private void gravar() {
            Veiculo veiculo = new Veiculo( );
            veiculo.setPlaca(placa.getText());
            veiculo.setMarca(marca.getText());
            veiculo.setModelo(modelo.getText());

            dao.inserir(veiculo);

            mostrarAlerta( "veiculo cadastrado");
            limparCampos();
        
    }
    
     @FXML
    private void excluir() {
        Veiculo selecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta("Selecione um veículo para excluir!");
            return;
        }

        // Confirmação antes de excluir
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar exclusão");
        confirmacao.setHeaderText("Deseja realmente excluir este veículo?");
        confirmacao.setContentText(
            "Placa: " + selecionado.getPlaca() + "\n" +
            "Marca: " + selecionado.getMarca() + "\n" +
            "Modelo: " + selecionado.getModelo()
        );

        confirmacao.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                boolean removido = dao.excluir("placa", selecionado.getPlaca());
                if (removido) {
                    mostrarAlerta("Veículo " + selecionado.getPlaca() + " foi excluído com sucesso!");
                    carregarVeiculos();
                } else {
                    mostrarAlerta("Erro ao excluir o veículo " + selecionado.getPlaca() + "!");
                }
            }
        });
    }

    @FXML
    private void mostrar() {
        carregarVeiculos();
        mostrarAlerta("Lista de veículos atualizada!");
    }

    private void carregarVeiculos() {
        List<Veiculo> veiculos = dao.listarTodos();
        listaVeiculos = FXCollections.observableArrayList(veiculos);
        tabelaVeiculos.setItems(listaVeiculos);
    }
    
    @FXML 
    private void voltar() throws IOException{ 
        App.setRoot("telaInicial");
    }

    private void limparCampos() {
        this.placa.clear();
        this.marca.clear();
        this.modelo.clear();
    }

    private void mostrarAlerta( String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
