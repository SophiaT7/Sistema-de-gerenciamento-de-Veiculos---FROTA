package modelo;

import java.util.List;

public interface IControleUtilizacao {
    void registrarRetirada(Operador operador, String senha, Motorista motorista, Veiculo veiculo);
    void registrarDevolucao(Operador operador, String senha, Veiculo veiculo);
    List<Utilizacao> listarPorPlaca(String placa);
}
