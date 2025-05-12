package model;

public abstract class Pessoa implements Bibliotecavel {
    private static int contadorGlobal = 1;
    protected int id;
    protected String nome;
    protected String endereco;
    protected String email;
    protected String telefone;

    public Pessoa(String nome, String endereco, String email, String telefone) {
        this.id = contadorGlobal++;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public abstract String getDescricao();
}