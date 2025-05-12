package model;

public class Funcionario extends Pessoa {
    private String cargo;

    public Funcionario(String nome, String endereco, String email, String telefone, String cargo) {
        super(nome, endereco, email, telefone);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return String.format("[Func ID:%d] %s - %s | Email: %s | Tel: %s | End: %s", id, nome, cargo, email, telefone, endereco);
    }

    @Override
    public String getDescricao() {
        return toString();
    }
}