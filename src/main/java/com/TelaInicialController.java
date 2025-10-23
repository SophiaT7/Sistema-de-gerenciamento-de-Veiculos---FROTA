/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;
import java.io.IOException;
import javafx.fxml.FXML;



public class TelaInicialController {

    @FXML 
    private void abrirMotorista() throws IOException{ 
        App.setRoot("telaMotorista");
    }
    
    @FXML 
    private void abrirOperador() throws IOException{ 
        App.setRoot("telaOperador");
    }
    

    @FXML 
    private void abrirVeiculo() throws IOException{ 
        App.setRoot("telaVeiculo");
    }
    
    @FXML 
    private void abrirExcluirMotorista() throws IOException{ 
        App.setRoot("excluirMotorista");
    }
    
    @FXML 
    private void abrirExcluirOperador() throws IOException{ 
        App.setRoot("excluirOperador");
    }
    
    @FXML 
    private void abrirExcluirVeiculo() throws IOException{ 
        App.setRoot("excluirVeiculo");
    }
    
    @FXML 
    private void abrirDevolucao() throws IOException{ 
        App.setRoot("telaDevolucao");
    }
    
    @FXML 
    private void abrirRetirada() throws IOException{ 
        App.setRoot("telaRetirada");
    }
    
    @FXML 
    private void abrirConsultar() throws IOException{ 
        App.setRoot("telaConsulta");
    }
    
    @FXML 
    private void voltarLogin() throws IOException{ 
        App.setRoot("tela");
    }
    
}
