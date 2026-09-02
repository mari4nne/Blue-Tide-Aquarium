package business;

import repository.PeixeRepository;

import java.util.List;

public class PeixeController {
    private final PeixeRepository repoPeixe;

    public PeixeController(){
        repoPeixe = new PeixeRepository();
    }

    public boolean add(Peixe peixe){
        return false;
    }

    public boolean update(Peixe peixeAlterado){
        return false;
    }

    public Peixe deleteById(int id){
        return null;
    }

    public Peixe getById(int id){
        return null;
    }

    public List<Peixe> getAll(){
        return null;
    }
}
