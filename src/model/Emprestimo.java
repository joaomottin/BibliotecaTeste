package model;

import java.time.LocalDate;

public class Emprestimo {
    private static int contador = 1;
    private final int id;
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucao;

    // Construtor principal: usa data atual
    public Emprestimo(Livro livro, Usuario usuario) {
        this(livro, usuario, LocalDate.now());
    }

    // Construtor de teste: recebe dataEmprestimo customizada
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
