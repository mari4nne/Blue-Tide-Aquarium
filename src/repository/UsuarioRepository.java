package repository;

import business.TipoUsuario;
import business.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;
    private static UsuarioRepository instance;

    private UsuarioRepository(){
        usuarios = new ArrayList<>();
        usuarios.add(Usuario.getInstance("Vic", TipoUsuario.ADMIN, "91111-1111"));
        usuarios.add(Usuario.getInstance("Joaozinho", TipoUsuario.PADRAO, "92222-2222"));
    }

    public static UsuarioRepository getInstance(){
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
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

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                return usuarios.remove(i);
            }
        }
        return null;
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
