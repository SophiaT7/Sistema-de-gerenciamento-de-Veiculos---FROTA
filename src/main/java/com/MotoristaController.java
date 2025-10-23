package com;

import dao.Dao;
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
import modelo.Motorista;

public class MotoristaController {

    private Dao<Motorista> dao;

    @FXML
    private TextField nome;

    @FXML
    private TextField endereco;

    @FXML
    private TextField cnh;

    @FXML
    private TextField setor;
    
    @FXML
    private TableView<Motorista> tabelaMotoristas;

    @FXML
    private TableColumn<Motorista, String> colNome;

    @FXML
    private TableColumn<Motorista, String> colEndereco;

    @FXML
    private TableColumn<Motorista, String> colCnh;

    @FXML
    private TableColumn<Motorista, String> colSetor;

    private ObservableList<Motorista> listaMotoristas;

    @FXML
    public void initialize() {
        dao = new Dao<>(Motorista.class);

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colCnh.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        colSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));

        carregarMotoristas();
    }

    @FXML
    private void gravar() {
            Motorista motorista = new Motorista();
            motorista.setNome(nome.getText());
            motorista.setEndereco(endereco.getText());
            motorista.setCnh(cnh.getText());
            motorista.setSetor(setor.getText());

            dao.inserir(motorista);

            mostrarAlerta( "motorista cadastrado");
            limparCampos();
            carregarMotoristas(); 
        
    }
    
    @FXML
    private void excluir() {
    Motorista selecionado = tabelaMotoristas.getSelectionModel().getSelectedItem();

    if (selecionado == null) {
        mostrarAlerta("Selecione um motorista para excluir!");
        return;
    }

    Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
    confirmacao.setTitle("Confirmar exclusão");
    confirmacao.setHeaderText("Deseja realmente excluir este motorista?");
    confirmacao.setContentText(
        "Nome: " + selecionado.getNome() + "\n" +
        "CNH: " + selecionado.getCnh()
    );

    confirmacao.showAndWait().ifPresent(resposta -> {
        if (resposta == ButtonType.OK) {
            boolean removido = dao.excluir("cnh", selecionado.getCnh());
            if (removido) {
                mostrarAlerta("Motorista " + selecionado.getNome() + " (CNH: " + selecionado.getCnh() + ") foi excluído com sucesso!");
                carregarMotoristas();
            } else {
                mostrarAlerta("Erro ao excluir motorista " + selecionado.getNome() + "!");
            }
        }
    });
}

    @FXML
    private void mostrar() {
        carregarMotoristas();
        mostrarAlerta("Lista de motoristas atualizada!");
    }

    private void carregarMotoristas() {
        List<Motorista> motoristas = dao.listarTodos();
        listaMotoristas = FXCollections.observableArrayList(motoristas);
        tabelaMotoristas.setItems(listaMotoristas);
    }
    
    @FXML 
    private void voltar() throws IOException{ 
        App.setRoot("telaInicial");
    }
    

    private void limparCampos() {
        this.nome.clear();
        this.endereco.clear();
        this.cnh.clear();
        this.setor.clear();
    }

    private void mostrarAlerta( String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
