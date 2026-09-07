package repository;

import business.Peixe;

import java.util.ArrayList;
import java.util.List;

public class PeixeRepository {
    private List<Peixe> peixes;

    public PeixeRepository() {
        peixes = new ArrayList<>();
    }

    public boolean add(Peixe peixe) {
        if (peixe == null)
            return false;

        return peixes.add(peixe);
    }

    public boolean update(Peixe peixeAlterado) {
        if (peixeAlterado == null)
            return false;

        for (int i = 0; i < peixes.size(); i++) {
            if (peixes.get(i).getIdPeixe() == peixeAlterado.getIdPeixe()) {
                peixes.set(i, peixeAlterado);
                return true;
            }
        }
        return false;
    }

    public Peixe deleteById(int id) {
        if (id < 0)
            return null;

        return peixes.remove(id);
    }

    public Peixe getById(int id) {
        if (id < 0)
            return null;
        for (Peixe peixe : peixes) {
            if (peixe.getIdPeixe() == id) {
                return new Peixe(peixe);
            }
        }
        return null;
    }

    public List<Peixe> getAll() {
        List<Peixe> aux = new ArrayList<>();

        for (Peixe peixe : peixes) {
            aux.add(new Peixe(peixe));
        }
        return aux;
    }
}
