package controller;

import model.Livro;
import model.PreCarga;
import model.Bibliotecavel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class LivroController {
    private int proximoId = 1;
    private Map<Integer, Livro> livros = new HashMap<>();

    public Livro cadastrarLivro(String titulo, String autor, String categoria, int exemplares, int anoPublicacao) {
        Livro livro = new Livro(titulo, autor, categoria, exemplares, anoPublicacao);
        livro.setId(proximoId);
        livros.put(proximoId, livro);
        proximoId++;
        return livro;
    }
    

    public LivroController() {
        PreCarga.carregarLivros(this);
    }

    public Collection<Livro> listarLivros() {
        return livros.values();
    }

    public List<String> pesquisarPor(String termo, String tipo) {
        return livros.values().stream()
            .filter(l -> switch(tipo) {
                case "id" -> Integer.toString(l.getId()).equals(termo);
                case "titulo" -> l.getTitulo().equalsIgnoreCase(termo);
                case "autor" -> l.getAutor().equalsIgnoreCase(termo);
                case "categoria" -> l.getCategoria().equalsIgnoreCase(termo);
                default -> false;
            })
            .map(Bibliotecavel::getDescricao)
            .collect(Collectors.toList());
    }

    public Livro getLivro(int id) {
        return livros.get(id);
    }
}