package Repository;

import Business.Peixe;

import java.util.ArrayList;
import java.util.List;

public class PeixeRepository {
    private List<Peixe> peixes;

    public PeixeRepository(){
        peixes = new ArrayList<>();
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
