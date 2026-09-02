package business;

import repository.PeixeRepository;

import java.util.List;

public class PeixeController {
    private final PeixeRepository repoPeixe;

    public PeixeController() {
        repoPeixe = new PeixeRepository();
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


}
