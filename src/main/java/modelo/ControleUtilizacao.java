package modelo;

import dao.Dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ControleUtilizacao implements IControleUtilizacao {

    private final Dao<Utilizacao> dao = new Dao<>(Utilizacao.class);

    /**
     * Registra a retirada de um veículo por um motorista e operador.
     */
    @Override
    public void registrarRetirada(Operador operador, String senha, Motorista motorista, Veiculo veiculo) {
        // Valida senha do operador
        if (!operador.getSenha().equals(senha)) {
            System.out.println("⚠️ Senha incorreta para o operador " + operador.getLogin());
            return;
        }

        Utilizacao u = new Utilizacao();
        u.retirar(LocalDate.now(), LocalTime.now(), motorista, veiculo, operador);
        dao.inserir(u);

        System.out.println("✅ Retirada registrada: Operador " + operador.getLogin() +
                ", Motorista " + motorista.getNome() +
                ", Veículo " + veiculo.getPlaca());
    }

    /**
     * Registra a devolução de um veículo, apenas se o operador for o mesmo que realizou a retirada.
     */
    @Override
    public void registrarDevolucao(Operador operador, String senha, Veiculo veiculo) {
        // Valida senha
        if (!operador.getSenha().equals(senha)) {
            System.out.println("⚠️ Senha incorreta para o operador " + operador.getLogin());
            return;
        }

        List<Utilizacao> lista = dao.listarTodos();

        for (int i = lista.size() - 1; i >= 0; i--) {
            Utilizacao u = lista.get(i);

            // Proteção contra nulls
            if (u.getVeiculo() == null || u.getOperador() == null) continue;

            boolean mesmaPlaca = u.getVeiculo().getPlaca() != null &&
                                 u.getVeiculo().getPlaca().trim().equalsIgnoreCase(veiculo.getPlaca().trim());
            boolean mesmoOperador = u.getOperador().getLogin().equalsIgnoreCase(operador.getLogin());
            boolean emAberto = u.getDataDevolucao() == null;

            // ✅ Só devolve se for o mesmo operador e o veículo ainda estiver em uso
            if (mesmaPlaca && mesmoOperador && emAberto) {
                u.devolver(LocalDate.now(), LocalTime.now());
                dao.alterar("veiculo.placa", veiculo.getPlaca(), u);

                System.out.println("✅ Devolução confirmada para operador " + operador.getLogin() +
                        " e veículo " + veiculo.getPlaca());
                return;
            }
        }

        System.out.println("⚠️ Nenhuma utilização em aberto encontrada para o operador "
                + operador.getLogin() + " e veículo " + veiculo.getPlaca());
    }

    /**
     * Lista todas as utilizações de um veículo específico pela placa.
     */
    @Override
    public List<Utilizacao> listarPorPlaca(String placa) {
        return dao.listarTodos().stream()
                .filter(u -> u.getVeiculo() != null &&
                        u.getVeiculo().getPlaca() != null &&
                        u.getVeiculo().getPlaca().trim().equalsIgnoreCase(placa.trim()))
                .collect(Collectors.toList());
    }
}
