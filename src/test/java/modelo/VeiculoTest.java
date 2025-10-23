/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import modelo.Motorista;
import modelo.Veiculo;
import modelo.Utilizacao;


/**
 *
 * @author Blast
 */
public class VeiculoTest {
    
    public VeiculoTest() {
    }

    /*@Test
    public void testUtilizacao() {
        Motorista motorista = new Motorista("joao", "123456789", 30, "(11)99999-9999");
        Veiculo veiculo = new Veiculo("ABC1234", "fiat uno", "prata");

        Utilizacao utilizacao = new Utilizacao();

        LocalDate dataRetirada = LocalDate.of(2025, 10, 9);
        LocalTime horaRetirada = LocalTime.of(9, 30);

        utilizacao.retirar(dataRetirada, horaRetirada, motorista, veiculo);
        
        
        LocalDate dataDevolucao = LocalDate.of(2025, 10, 9);
        LocalTime horaDevolucao = LocalTime.of(12, 45);  
        
        utilizacao.devolver(dataDevolucao, horaDevolucao);
        
        
        // verifica se os dados foram registrados corretamente
        /*assertEquals(motorista, utilizacao.getMotorista());
        assertEquals(veiculo, utilizacao.getVeiculo());
        assertEquals(dataRetirada, utilizacao.getDataRetirada());
        assertEquals(horaRetirada, utilizacao.getHoraRetirada());
        assertNull(utilizacao.getDataDevolucao());
        assertNull(utilizacao.getHoraDevolucao());

        

 

        assertEquals(dataDevolucao, utilizacao.getDataDevolucao());
        assertEquals(horaDevolucao, utilizacao.getHoraDevolucao());  */
   //* }
}
    

