// view/BibliotecaView.java
package view;

import controller.LivroController;
import controller.UsuarioController;
import controller.FuncionarioController;
import controller.EmprestimoController;
import model.PreCarga;
import model.Funcionario;
import model.Livro;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class BibliotecaView {
    public static void main(String[] args) {
        LivroController livroCtrl = new LivroController();
        UsuarioController usuarioCtrl = new UsuarioController();
        FuncionarioController funcionarioCtrl = new FuncionarioController();
        EmprestimoController emprestimoCtrl = new EmprestimoController(usuarioCtrl, livroCtrl);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Pesquisar livro");
            System.out.println("4. Cadastrar usuário");
            System.out.println("5. Listar usuários");
            System.out.println("6. Cadastrar funcionário");
            System.out.println("7. Listar funcionários");
            System.out.println("8. Registrar empréstimo");
            System.out.println("9. Registrar devolução");
            System.out.println("10. Listar todos empréstimos");
            System.out.println("11. Listar todos empréstimos (em ordem alfabética de titulo)");
            System.out.println("12. Livros emprestados atualmente");
            System.out.println("13. Usuários com devolução em atraso");
            System.out.println("14. Livros mais populares");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarLivro(scanner, livroCtrl);
                case 2 -> livroCtrl.listarLivros().forEach(System.out::println);
                case 3 -> pesquisarLivro(scanner, livroCtrl);
                case 4 -> cadastrarUsuario(scanner, usuarioCtrl);
                case 5 -> usuarioCtrl.listarUsuarios().forEach(System.out::println);
                case 6 -> cadastrarFuncionario(scanner, funcionarioCtrl);
                case 7 -> funcionarioCtrl.listarFuncionarios().forEach(System.out::println);
                case 8 -> registrarEmprestimo(scanner, livroCtrl, usuarioCtrl, emprestimoCtrl);
                case 9 -> registrarDevolucao(scanner, emprestimoCtrl);
                case 10 -> emprestimoCtrl.listarEmprestimos().forEach(System.out::println);
                case 11 -> emprestimoCtrl.listarEmprestimosOrdemAlfabetica().forEach(System.out::println);
                case 12 -> emprestimoCtrl.listarEmprestimosAtivos().forEach(System.out::println);
                case 13 -> {
                    var atrasados = emprestimoCtrl.listarUsuariosComAtraso(7);
                    if (atrasados.isEmpty()) System.out.println("Nenhum usuário com atraso.");
                    else atrasados.forEach(System.out::println);
                }
                case 14 -> {
                    var populares = emprestimoCtrl.livrosMaisPopulares();
                    if (populares.isEmpty()) System.out.println("Nenhum empréstimo registrado.");
                    else populares.forEach(e ->
                        System.out.printf("%s -> %d empréstimos\n", e.getKey().getTitulo(), e.getValue()));
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarLivro(Scanner sc, LivroController ctrl) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Exemplares: ");
        int exemplares = Integer.parseInt(sc.nextLine());
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(sc.nextLine());
        Livro livroCadastrado = ctrl.cadastrarLivro(titulo, autor, categoria, exemplares, ano);
        System.out.println("Livro cadastrado com ID: " + livroCadastrado.getId());
    }

    private static void pesquisarLivro(Scanner sc, LivroController ctrl) {
        System.out.print("Pesquisar por (id, titulo, autor, categoria): ");
        String tipo = sc.nextLine();
        System.out.print("Termo: ");
        String termo = sc.nextLine();
        List<String> encontrados = ctrl.pesquisarPor(termo, tipo);
        if (encontrados.isEmpty()) System.out.println("Nenhum livro encontrado.");
        else encontrados.forEach(System.out::println);
    }

    private static void cadastrarUsuario(Scanner sc, UsuarioController ctrl) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        ctrl.cadastrarUsuario(new Usuario(nome, endereco, email, telefone));
        System.out.println("Usuário cadastrado.");
    }

    private static void cadastrarFuncionario(Scanner sc, FuncionarioController ctrl) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Cargo: ");
        String cargo = sc.nextLine();
        ctrl.cadastrarFuncionario(new Funcionario(nome, endereco, email, telefone, cargo));
        System.out.println("Funcionário cadastrado.");
    }

    private static void registrarEmprestimo(Scanner sc, LivroController lCtrl, UsuarioController uCtrl, EmprestimoController eCtrl) {
        System.out.print("ID do Livro: ");
        int livroId = Integer.parseInt(sc.nextLine());
        System.out.print("ID do Usuário: ");
        int usuarioId = Integer.parseInt(sc.nextLine());
        Livro livro = lCtrl.getLivro(livroId);
        Usuario usuario = uCtrl.getUsuario(usuarioId);
        System.out.println(eCtrl.registrarEmprestimo(livro, usuario, null));
    }
    

    private static void registrarDevolucao(Scanner sc, EmprestimoController eCtrl) {
        System.out.print("ID do Empréstimo: ");
        int emprestimoId = Integer.parseInt(sc.nextLine());
        System.out.println(eCtrl.registrarDevolucao(emprestimoId));
    }
}
