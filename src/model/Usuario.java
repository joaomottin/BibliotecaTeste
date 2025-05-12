package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa {
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, String endereco, String email, String telefone) {
        super(nome, endereco, email, telefone);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public String toString() {
        return String.format("[Usu ID:%d] %s | Email: %s | Tel: %s | End: %s", id, nome, email, telefone, endereco);
    }

    @Override
    public String getDescricao() {
        return toString();
    }
}