package model;

import controller.LivroController;
import controller.UsuarioController;
import controller.FuncionarioController;

public class PreCarga {
    public static void carregarLivros(LivroController lc) {
        lc.cadastrarLivro("1984", "George Orwell", "Distopia", 5, 1949);
        lc.cadastrarLivro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 3, 1954);
    }
    
    public static void carregarUsuarios(UsuarioController uc) {
        uc.cadastrarUsuario(new Usuario("Joãozinho", "Rua XV", "joaozinho@gmail.com", "41997473031"));
        uc.cadastrarUsuario(new Usuario("Mariazinha", "Rua Fernando Torres", "mariazinha@gmail.com", "41984724817"));
    }

    public static void carregarFuncionarios(FuncionarioController fc){
        fc.cadastrarFuncionario(new Funcionario("Fernando", "Rua Oswaldo Cruz", "fernandosilva@gmail.com", "41947205730", "Chefe"));
        fc.cadastrarFuncionario(new Funcionario("Iago", "Rua José Pinto", "iagojunior@gmail.com", "41975295730", "Estagiário"));
    }
}
