package Business;

import Repository.AquarioRepository;

import java.util.List;

public class AquarioController {
    private final AquarioRepository repoAquario;

    public AquarioController(){
        repoAquario = new AquarioRepository();
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
