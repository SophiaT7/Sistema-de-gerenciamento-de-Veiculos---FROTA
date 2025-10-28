package modelo;

import dao.Dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ControleUtilizacao implements IControleUtilizacao {

    private final Dao<Utilizacao> dao = new Dao<>(Utilizacao.class);

    @Override
    public void registrarRetirada(Operador operador, String senha, Motorista motorista, Veiculo veiculo) {
        if (!operador.getSenha().equals(senha)) {
            System.out.println("senha incorreta para o operador " + operador.getLogin());
            return;
        }

        Utilizacao u = new Utilizacao();
        u.retirar(LocalDate.now(), LocalTime.now(), motorista, veiculo, operador);
        dao.inserir(u);

        System.out.println("retirada registrada: Operador " + operador.getLogin() +
                ", Motorista " + motorista.getNome() +
                ", Veículo " + veiculo.getPlaca());
    }

    @Override
    public void registrarDevolucao(Operador operador, String senha, Veiculo veiculo) {
        if (!operador.getSenha().equals(senha)) {
            System.out.println("senha incorreta para o operador " + operador.getLogin());
            return;
        }

        List<Utilizacao> lista = dao.listarTodos();

        for (int i = lista.size() - 1; i >= 0; i--) {
            Utilizacao u = lista.get(i);

            if (u.getVeiculo() == null) continue;

            boolean mesmaPlaca = u.getVeiculo().getPlaca() != null &&
                                 u.getVeiculo().getPlaca().trim().equalsIgnoreCase(veiculo.getPlaca().trim());
            boolean emAberto = u.getDataDevolucao() == null;

            if (mesmaPlaca && emAberto) {
                u.devolver(LocalDate.now(), LocalTime.now());
                u.setOperador(operador);
                dao.alterar("veiculo.placa", veiculo.getPlaca(), u);

                System.out.println("devolução registrada para o veículo " + veiculo.getPlaca() +
                                   " por operador " + operador.getLogin());
                return;
            }
        }

        System.out.println("nenhuma utilização em aberto encontrada para o veículo " + veiculo.getPlaca());
    }

    @Override
    public List<Utilizacao> listarPorPlaca(String placa) {
        return dao.listarTodos().stream()
                .filter(u -> u.getVeiculo() != null &&
                        u.getVeiculo().getPlaca() != null &&
                        u.getVeiculo().getPlaca().trim().equalsIgnoreCase(placa.trim()))
                .collect(Collectors.toList());
    }
}
