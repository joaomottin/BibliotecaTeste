package model;

import java.time.LocalDate;
import java.time.Period;

public class Emprestimo {
    private static int contador = 1;
    private final int id;
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this(livro, usuario, LocalDate.now());
    }

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.id = contador++;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(14);
        this.dataDevolucao = null;
    }

    public int getId() { return id; }
    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }

    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
    }

    public int getDiasDeAtraso() {
    if (dataDevolucao == null || !dataDevolucao.isAfter(dataDevolucaoPrevista)) return 0;
    Period atraso = Period.between(dataDevolucaoPrevista, dataDevolucao);
    return atraso.getYears() * 365 + atraso.getMonths() * 30 + atraso.getDays();
    }


    @Override
    public String toString() {
        return String.format(
            "Empréstimo #%d - Livro: %s | Usuário: %s | Data: %s | Previsto: %s | Devolvido: %s",
            id,
            livro.getTitulo(),
            usuario.getNome(),
            dataEmprestimo,
            dataDevolucaoPrevista,
            (dataDevolucao != null ? dataDevolucao : "Pendente")
        );
    }
}
