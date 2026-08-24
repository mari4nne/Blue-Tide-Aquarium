package Business;

import Repository.UsuarioRepository;

import java.util.ArrayList;

public class UsuarioController {
    private UsuarioRepository repoUsuario;

    public UsuarioController(){
        repoUsuario = new UsuarioRepository();
    }

    public boolean add(Usuario u){
        if (u == null)
            return false;

        return repoUsuario.add(u);
    }

    public boolean update(Usuario uAlterado){
        if (uAlterado == null)
            return false;

        return repoUsuario.update(uAlterado);
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

    public ArrayList<Usuario> getAll(){
    return repoUsuario.getAll();
    }
}
