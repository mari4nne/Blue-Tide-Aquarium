package Repository;

import Business.Aquario;

import java.util.ArrayList;
import java.util.List;

public class AquarioRepository {
    private List<Aquario> aquarios;

    public AquarioRepository(){
        aquarios = new ArrayList<>();
    }

    public boolean add(Aquario aquario){
        return false;
    }

    public boolean update(Aquario aquarioAlterado){
        return false;
    }

    public Aquario deleteById(int id){
        return null;
    }

    public Aquario getById(int id){
        return null;
    }

    public List<Aquario> getAll(){
        return null;
    }
}
