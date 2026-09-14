package business;

import repository.PeixeRepository;
import repository.UsuarioRepository;
import repository.AquarioRepository;

import java.util.List;

public class PeixeController {
    private final PeixeRepository repoPeixe;
    private final UsuarioRepository repoUsuario;
    private final AquarioRepository repoAquario;

    public PeixeController() {
        repoPeixe = PeixeRepository.getInstance();
        repoUsuario = UsuarioRepository.getInstance();
        repoAquario = AquarioRepository.getInstance();
    }

    public boolean add(Peixe peixe) {
        if (peixe == null)
            return false;

        return repoPeixe.add(peixe);
    }

    public boolean update(Peixe peixeAlterado) {
        if (peixeAlterado == null)
            return false;

        return repoPeixe.update(peixeAlterado);
    }

    public Peixe deleteById(int id) {
        if (id < 0)
            return null;

        return repoPeixe.deleteById(id);
    }

    public Peixe getById(int id) {
        if (id < 0)
            return null;

        return repoPeixe.getById(id);
    }

    public List<Peixe> getAll() {
        return repoPeixe.getAll();
    }

    public List<Aquario> getAllAquarios(){
        return repoAquario.getAll();
    }

    public boolean  verifyTipoAgua (Peixe p, int codAquario) {
        if (p != null) {
            Aquario a = repoAquario.getById((codAquario));

            return a.getTipo() == p.getTipoAgua();
        } else {
            return false;
        }
    }

    public boolean verifyTipoAgua (TipoAgua t, int codAquario) {
        if (t != null) {
            Aquario a = repoAquario.getById((codAquario));

            return a.getTipo() == t;
        } else {
            return false;
        }
    }

    public String buscarNomeUsuarioPorId(int idUsuario){
        Usuario usuario = repoUsuario.getByIdUniversal(idUsuario);

        if (usuario != null)
            return usuario.getNome();
        return null;
    }

    public String buscarCodAquarioPorId(int idAquario){
        Aquario aquario = repoAquario.getByIdUniversal(idAquario);

        if (aquario != null)
            return aquario.getCodigo();
        return null;
    }

    public int getUserByAquarioId(int idAquario){
        return repoAquario.getByIdUniversal(idAquario).getIdUsuario();
    }

    public boolean verifyDonoAquario (Peixe p, int codAquario) {
        if (p == null) return false;

        Aquario a = repoAquario.getById(codAquario);
        if (a == null) return false;

        return p.getIdUsuario() == a.getIdUsuario();
    }

    public boolean verifyUsuarioExistence (int idUsuario) {
        if (idUsuario < 0) return false;
        else {
            Usuario verifica = repoUsuario.getById(idUsuario);
            if (verifica != null) return true;
            else return false;
        }
    }

    public boolean verifyAquarioExistence (int idAquario) {
        if (idAquario < 0) return false;
        else {
            Aquario verifica = repoAquario.getById(idAquario);
            if (verifica != null) return true;
            else return false;
        }
    }

    public Peixe getBySequence(int cod){
        if (cod < 0)
            return null;

        List<Peixe> peixes = getAll();

        if (peixes.size() <= cod)
            return null;

        return peixes.get(cod);
    }
}