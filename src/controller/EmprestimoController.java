package controller;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.time.LocalDate;
import java.util.*;

public class EmprestimoController {
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public String registrarEmprestimo(Livro livro, Usuario usuario) {
        if (livro == null || usuario == null) return "Livro ou usuário não encontrado.";
        if (livro.getExemplares() <= 0) return "Sem exemplares disponíveis.";
        if (!usuario.getEmprestimos().isEmpty()) return "Usuário já possui empréstimo ativo.";

        Emprestimo e = new Emprestimo(livro, usuario);
        emprestimos.add(e);
        livro.setExemplares(livro.getExemplares() - 1);
        usuario.getEmprestimos().add(e);
        return "Empréstimo realizado com sucesso. ID: " + e.getId();
    }

    public String registrarDevolucao(int emprestimoId) {
        Optional<Emprestimo> opt = emprestimos.stream()
                .filter(e -> e.getId() == emprestimoId && e.getDataDevolucao() == null)
                .findFirst();
        if (opt.isEmpty()) return "Empréstimo não encontrado ou já devolvido.";
        Emprestimo e = opt.get();
        e.registrarDevolucao();
        e.getLivro().setExemplares(e.getLivro().getExemplares() + 1);
        return "Devolução registrada.";
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosPorUsuario(int usuarioId) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == usuarioId) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucao() == null) {
                ativos.add(e);
            }
        }
        return ativos;
    }

    public List<Usuario> listarUsuariosComAtraso(int diasLimite) {
        List<Usuario> atrasados = new ArrayList<>();
        LocalDate limite = LocalDate.now().minusDays(diasLimite);

        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucao() == null && e.getDataEmprestimo().isBefore(limite)) {
                Usuario u = e.getUsuario();
                if (!atrasados.contains(u)) {
                    atrasados.add(u);
                }
            }
        }
        return atrasados;
    }

    public List<Map.Entry<Livro, Integer>> livrosMaisPopulares() {
        Map<Livro, Integer> contagem = new HashMap<>();
        for (Emprestimo e : emprestimos) {
            Livro livro = e.getLivro();
            contagem.put(livro, contagem.getOrDefault(livro, 0) + 1);
        }

        List<Map.Entry<Livro, Integer>> lista = new ArrayList<>(contagem.entrySet());
        lista.sort((a, b) -> b.getValue() - a.getValue());
        return lista;
    }
}
