package Business;

import Repository.UsuarioRepository;

import java.util.List;

public class UsuarioController {
    private final UsuarioRepository repoUsuario;

    public UsuarioController(){
        repoUsuario = new UsuarioRepository();
    }

    public boolean add(Usuario usuario){
        if (usuario == null)
            return false;

        return repoUsuario.add(usuario);
    }

    public boolean update(Usuario usuarioAlterado){
        if (usuarioAlterado == null)
            return false;

        return repoUsuario.update(usuarioAlterado);
    }

    public Usuario deleteById(int id){
        if (id < 0)
            return null;

        return repoUsuario.deleteById(id);
    }

    public Usuario getById(int id){
        if (id < 0)
            return null;

        return repoUsuario.getById(id);
    }

    public List<Usuario> getAll(){
    return repoUsuario.getAll();
    }
}
