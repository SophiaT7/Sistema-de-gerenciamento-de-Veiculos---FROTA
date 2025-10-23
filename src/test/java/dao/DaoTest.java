/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import modelo.Operador;
import modelo.Veiculo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Blast
 */
public class DaoTest {
    
    public DaoTest() {
    }

    /*@Test
    public void testInserir() {
        Veiculo v = new Veiculo("DER9865", "Ford", "Mustang");
        Dao<Veiculo> dao= new Dao(Veiculo.class);
        
        Veiculo aux=dao.buscarPorChave("placa", "DER9865");
        if(aux==null){
            dao.inserir(null);
        }
        else{
            System.out.println("ja existe");
        }
        
        
    }*/
    
    @Test 
    public void testLogin(){
        Operador o = new Operador("admin", "123");
        Dao<Operador> dao = new Dao (Operador.class);
        
        Operador aux = dao.buscarPorChave("login", "admin");
        if(aux==null){
            dao.inserir(o);
        }
        else{
            System.out.println("ja existe esse user");    
        }
    }
    
}
