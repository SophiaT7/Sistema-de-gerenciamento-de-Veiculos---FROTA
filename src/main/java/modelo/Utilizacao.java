package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Utilizacao {

    private Veiculo veiculo;
    private Motorista motorista;
    private Operador operador;
    private LocalDate dataRetirada;
    private LocalTime horaRetirada;
    private LocalDate dataDevolucao;
    private LocalTime horaDevolucao;

    public Utilizacao() {}

    public void retirar(LocalDate data, LocalTime hora, Motorista motorista, Veiculo veiculo, Operador operador) {
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.operador = operador;
        this.dataRetirada = data;
        this.horaRetirada = hora;

        System.out.println(motorista.getNome() + " retirou o veículo " 
                           + veiculo.getPlaca() + " (operador: " + operador.getLogin() + ")");
    }

    public void devolver(LocalDate data, LocalTime hora) {
        this.dataDevolucao = data;
        this.horaDevolucao = hora;

        System.out.println(motorista.getNome() + " devolveu o veículo " 
                           + veiculo.getPlaca() + " em " + data + " hora: " + hora);
    }

    public Veiculo getVeiculo() { return veiculo; }
    public Motorista getMotorista() { return motorista; }
    public Operador getOperador() { return operador; }
    public LocalDate getDataRetirada() { return dataRetirada; }
    public LocalTime getHoraRetirada() { return horaRetirada; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public LocalTime getHoraDevolucao() { return horaDevolucao; }

    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
    public void setOperador(Operador operador) { this.operador = operador; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }
    public void setHoraRetirada(LocalTime horaRetirada) { this.horaRetirada = horaRetirada; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public void setHoraDevolucao(LocalTime horaDevolucao) { this.horaDevolucao = horaDevolucao; }
}
