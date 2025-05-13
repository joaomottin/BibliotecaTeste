package model;

public class Livro implements Bibliotecavel {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int exemplares;
    private int anoPublicacao;

    public Livro(String titulo, String autor, String categoria, int exemplares, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.exemplares = exemplares;
        this.anoPublicacao = anoPublicacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getExemplares() { return exemplares; }
    public void setExemplares(int exemplares) { this.exemplares = exemplares; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    @Override
    public String toString() {
        return String.format("[Livro ID:%d] %s - %s (%d) | Categoria: %s | Disp.: %d",
                id, titulo, autor, anoPublicacao, categoria, exemplares);
    }

    @Override
    public String getDescricao() {
        return "O livro " + titulo + " de " + autor + ", foi publicado no ano de " + anoPublicacao + ", sendo uma clássica refencia da categoria de " + categoria;
    }
}