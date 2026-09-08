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

    public boolean verifyTipoAgua (Peixe p, int codAquario) {
        if (p != null) {
            Aquario a = repoAquario.getById((codAquario));

            return a.getTipo() == p.getTipoAgua();
        } else {
            return false;
        }
    }

    public boolean verifyDonoAquario (Peixe p, int codAquario) {
        if (p == null) return false;

        Aquario a = repoAquario.getById(codAquario);
        if (a == null) return false;

        return p.getIdUsuario() == a.getIdUsuario();
    }

    public boolean verifyIdUser (Usuario u, Peixe p) {
        if (u == null || p == null) return false;

        return p.getIdUsuario() == u.getId();
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
}