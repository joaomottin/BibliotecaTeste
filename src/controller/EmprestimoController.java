package controller;

import model.Emprestimo;
import model.Livro;
import model.PreCarga;
import model.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class EmprestimoController {
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public EmprestimoController(UsuarioController uc, LivroController lc) {
        PreCarga.carregarEmprestimos(this, uc, lc);
    }

    public String registrarEmprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        if (livro == null || usuario == null) return "Livro ou usuário não encontrado.";
        if (livro.getExemplares() <= 0) return "Sem exemplares disponíveis.";
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario) && e.getDataDevolucao() == null) {
                return "Usuário já possui empréstimo ativo.";
            }
        }
        Emprestimo e;
        if (dataEmprestimo != null) {
            e = new Emprestimo(livro, usuario, dataEmprestimo);
        } else {
            e = new Emprestimo(livro, usuario);
        }
        emprestimos.add(e);
        livro.setExemplares(livro.getExemplares() - 1);
        usuario.getEmprestimos().add(e);
        return "Empréstimo realizado com sucesso. ID: " + e.getId();
    }

    public String registrarEmprestimo(Livro livro, Usuario usuario) {
        return registrarEmprestimo(livro, usuario, null);
    }

    public String registrarDevolucao(int emprestimoId) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == emprestimoId && e.getDataDevolucao() == null) {
                e.registrarDevolucao();
                e.getLivro().setExemplares(e.getLivro().getExemplares() + 1);
                
                Period periodo = Period.between(e.getDataEmprestimo(), e.getDataDevolucao());
                int diasTotais = periodo.getYears() * 365 + periodo.getMonths() * 30 + periodo.getDays();
                
                return "Devolução registrada. O livro ficou emprestado por " + diasTotais + " dias.";
                }
        }
        return "Empréstimo não encontrado ou já devolvido.";
    }

    public List<Emprestimo> listarEmprestimos() {
        return List.copyOf(emprestimos);
    }

        public List<Emprestimo> listarEmprestimosOrdemAlfabetica() {
        return emprestimos.stream()
                .sorted(Comparator.comparing(e -> e.getLivro().getTitulo().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Emprestimo> listarUltimosDoisEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (int i = emprestimos.size() - 1; i >= 0 && ativos.size() < 2; i--) {
            Emprestimo e = emprestimos.get(i);
            if (e.getDataDevolucao() == null) {
                ativos.add(e);
            }
        }
        Collections.reverse(ativos);
        return ativos;
    }

    public List<Emprestimo> listarEmprestimosComAtraso() {
    return emprestimos.stream()
        .filter(e -> e.getDiasDeAtraso() > 0)
        .sorted(Comparator.comparingInt(Emprestimo::getDiasDeAtraso).reversed())
        .collect(Collectors.toList());
    }

    public List<String> livrosMaisPopulares() {
        return emprestimos.stream()
            .collect(Collectors.groupingBy(e -> e.getLivro().getTitulo(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .map(e -> e.getKey() + " -> " + e.getValue() + " empréstimo (s)")
            .toList();
    }
}
