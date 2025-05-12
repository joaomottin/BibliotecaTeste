package controller;

import model.PreCarga;
import model.Usuario;
import java.util.*;

public class UsuarioController {
    private final Map<Integer, Usuario> usuarios = new HashMap<>();
    
    public void cadastrarUsuario(Usuario u) {
        usuarios.put(u.getId(), u);
    }
        public UsuarioController() {
        PreCarga.carregarUsuarios(this);
    }
    public Collection<Usuario> listarUsuarios() {
        return usuarios.values();
    }
    public Usuario getUsuario(int id) {
        return usuarios.get(id);
    }
}