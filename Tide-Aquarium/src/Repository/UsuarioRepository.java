package Repository;

import Business.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;

    public UsuarioRepository(){
        usuarios = new ArrayList<>();
    }

    public boolean add(Usuario u){
        if (u == null)
            return false;
        return usuarios.add(u);
    }

    public boolean update(Usuario uAlterado){
        if (uAlterado == null)
            return false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == uAlterado.getId()){
                usuarios.set(i, uAlterado);
                return true;
            }
        }
        return false;
    }

    public Usuario deleteById(int id){
        if (id < 0)
            return null;

        Usuario u = buscarPorId(id);

        if (u == null)
            return null;

        usuarios.remove(u);
        return new Usuario(u);
    }

    public Usuario getById(int id){
        if (id < 0)
            return null;

        return buscarPorId(id);
    }

    public ArrayList<Usuario> getAll(){
        ArrayList<Usuario> aux = new ArrayList<>();

        for (Usuario u : usuarios) {
            aux.add(new Usuario(u));
        }

        return aux;
    }

    private Usuario buscarPorId(int id) {
        if (id < 0)
            return null;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id)
                return usuarios.get(i);
        }
        return null;
    }
}
