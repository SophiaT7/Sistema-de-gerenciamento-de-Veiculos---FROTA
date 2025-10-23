/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import modelo.Motorista;
import modelo.Veiculo;
import modelo.Utilizacao;
import modelo.Operador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class ControleUtilizacaoTest {
    
    public ControleUtilizacaoTest() {
    }

    /*@Test
    public void testOperadorUtilizacao() { ArrayList<Utilizacao> historico = new ArrayList<>();

        // Criando operador, motorista e veículo
        Operador operador = new Operador("maria", 1, "maria", "1234");
        Motorista motorista = new Motorista("joao", "123456789", 30, "(11)99999-9999");
        Veiculo veiculo = new Veiculo("ABC1234", "fiat uno", "prata");

        //retirada do veículo
        if (operador.getSenha().equals("1234")) { 
            Utilizacao utilizacao = new Utilizacao();
            utilizacao.retirar(LocalDate.now(), LocalTime.now(), motorista, veiculo);
            historico.add(utilizacao); // registra 
        } else {
            System.out.println("senha incorreta");
        }

        try { Thread.sleep(1000); } catch (InterruptedException e) { }

        // devolucao
        if (operador.getSenha().equals("1234")) {
            Utilizacao ultima = historico.get(historico.size() - 1); 
            ultima.devolver(LocalDate.now(), LocalTime.now());
        } else {
            System.out.println("senha incorreta");
        }

        // historico
        System.out.println("historico de utilizaçoes:");
        for (Utilizacao u : historico) {
            System.out.println("motorista: " + u.getMotorista().getNome() +
                               ", veiculo: " + u.getVeiculo().getPlaca() +
                               ", retirada: " + u.getDataRetirada() + " " + u.getHoraRetirada() +
                               ", devolucao: " + u.getDataDevolucao() + " " + u.getHoraDevolucao());
        }
    }*/
    
}
