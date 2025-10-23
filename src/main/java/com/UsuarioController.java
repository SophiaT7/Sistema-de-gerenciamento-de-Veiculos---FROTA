
package com;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UsuarioController {
    
    
    @FXML
    private TextField nome;
    
    @FXML
    private TextField senha;
    
      
    @FXML 
    private void voltar() throws IOException{ 
        App.setRoot("telaInicial");
    }
}
