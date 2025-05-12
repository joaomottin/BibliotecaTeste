package controller;

import model.Funcionario;
import model.PreCarga;

import java.util.*;

public class FuncionarioController {
    private final Map<Integer, Funcionario> funcionarios = new HashMap<>();

    public void cadastrarFuncionario(Funcionario f) {
        funcionarios.put(f.getId(), f);
    }
    
    public FuncionarioController(){
        PreCarga.carregarFuncionarios(this);
    }

    public Collection<Funcionario> listarFuncionarios() {
        return funcionarios.values();
    }

    public Funcionario getFuncionario(int id) {
        return funcionarios.get(id);
    }
}