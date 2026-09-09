package business;
import java.util.List;
import repository.AquarioRepository;
import repository.UsuarioRepository;

public class AquarioController {

    private final AquarioRepository repoAquario;
    private final UsuarioRepository repoUsuario;

    public AquarioController() {
        repoAquario = AquarioRepository.getInstance();
        repoUsuario = UsuarioRepository.getInstance();
    }

    public boolean add(Aquario aquario) {
        if (aquario == null)
            return false;
        return repoAquario.add(aquario);
    }

    public boolean update(Aquario aquarioAlterado) {
        if (aquarioAlterado == null)
            return false;
        return repoAquario.update(aquarioAlterado);
    }

    public Aquario deleteById(int id) {
        if (id < 0)
            return null;
        return repoAquario.deleteById(id);
    }

    public Aquario getById(int id) {
        if (id < 0)
            return null;
        return repoAquario.getById(id);
    }

    public List<Aquario> getAll() {
        return repoAquario.getAll();
    }

    public List<Usuario> getAllUsuarios(){
        return repoUsuario.getAll();
    }

    public boolean pertenceAoUsuario(Aquario aquario, int idUsuario){
        if(aquario == null || idUsuario < 0)
            return false;
        return aquario.getIdUsuario() == idUsuario;
    }

    public String buscarNomeUsuarioPorId(int idUsuario){
        Usuario usuario = repoUsuario.getById(idUsuario);

        if (usuario != null)
            return usuario.getNome();
        return null;
    }

    public boolean verifyUsuarioExistence (int idUsuario) {
        if (idUsuario < 0) return false;
        else {
            Usuario verifica = repoUsuario.getById(idUsuario);
            if (verifica != null) return true;
            else return false;
        }
    }

    public Aquario getBySequence(int cod){
        if (cod < 0)
            return null;

        List<Aquario> aquarios = getAll();

        if (aquarios.size() <= cod)
            return null;

        return aquarios.get(cod);
    }
}