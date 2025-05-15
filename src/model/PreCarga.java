package model;

import controller.LivroController;
import controller.UsuarioController;
import controller.FuncionarioController;
import controller.EmprestimoController;
import java.time.LocalDate;

public class PreCarga {
    public static void carregarLivros(LivroController lc) {
        lc.cadastrarLivro("1984", "George Orwell", "Distopia", 5, 1949);
        lc.cadastrarLivro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 3, 1954);
    }
    
    public static void carregarUsuarios(UsuarioController uc) {
        uc.cadastrarUsuario(new Usuario("Joãozinho", "Rua XV", "joaozinho@gmail.com", "41997473031"));
        uc.cadastrarUsuario(new Usuario("Mariazinha", "Rua Fernando Torres", "mariazinha@gmail.com", "41984724817"));
        uc.cadastrarUsuario(new Usuario("Pedro", "Rua Brasil", "pedro@gmail.com", "41991234567"));
        uc.cadastrarUsuario(new Usuario("Ana", "Rua Curitiba", "ana@gmail.com", "41998765432"));
        uc.cadastrarUsuario(new Usuario("Carlos", "Rua X", "carlos@gmail.com", "41990000001"));
        uc.cadastrarUsuario(new Usuario("Luciana", "Rua Y", "luciana@gmail.com", "41990000002"));
    }

    public static void carregarFuncionarios(FuncionarioController fc) {
        fc.cadastrarFuncionario(new Funcionario("Fernando", "Rua Oswaldo Cruz", "fernandosilva@gmail.com", "41947205730", "Chefe"));
        fc.cadastrarFuncionario(new Funcionario("Iago", "Rua José Pinto", "iagojunior@gmail.com", "41975295730", "Estagiário"));
    }

    public static void carregarEmprestimos(EmprestimoController ec, UsuarioController uc, LivroController lc) {
        Usuario carlos = uc.listarUsuarios().stream().filter(u -> "Carlos".equals(u.getNome())).findFirst().orElse(null);
        Usuario luciana = uc.listarUsuarios().stream().filter(u -> "Luciana".equals(u.getNome())).findFirst().orElse(null);
        Livro livro1 = lc.listarLivros().stream().filter(l -> "1984".equals(l.getTitulo())).findFirst().orElse(null);
        Livro livro2 = lc.listarLivros().stream().filter(l -> "O Senhor dos Anéis".equals(l.getTitulo())).findFirst().orElse(null);
        if (carlos != null && livro1 != null) {ec.registrarEmprestimo(livro1, carlos, LocalDate.of(2025, 4, 1));}
        if (luciana != null && livro2 != null) {ec.registrarEmprestimo(livro2, luciana, LocalDate.of(2025, 3, 20));}
    }
}
