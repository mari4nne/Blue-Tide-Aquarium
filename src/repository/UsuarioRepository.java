package repository;

import business.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;

    public UsuarioRepository(){
        usuarios = new ArrayList<>();
    }

    public boolean add(Usuario usuario){
        if (usuario == null)
            return false;
        return usuarios.add(usuario);
    }

    public boolean update(Usuario usuarioAlterado){
        if (usuarioAlterado == null)
            return false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuarioAlterado.getId()){
                usuarios.set(i, usuarioAlterado);
                return true;
            }
        }
        return false;
    }

    public Usuario deleteById(int id){
        if (id < 0)
            return null;

        return usuarios.remove(id);
    }

    public Usuario getById(int id){
        if (id < 0)
            return null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id)
                return usuario;
        }
        return null;
    }

    public List<Usuario> getAll(){
        List<Usuario> aux = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            aux.add(new Usuario(usuario));
        }

        return aux;
    }
}
