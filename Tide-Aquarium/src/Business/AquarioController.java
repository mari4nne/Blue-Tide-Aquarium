package Business;

import Repository.AquarioRepository;

import java.util.List;

public class AquarioController {
    private final AquarioRepository repoAquario;

    public AquarioController(){
        repoAquario = new AquarioRepository();
    }

    public boolean add(Aquario aquario){
        if (aquario == null)
            return false;

        return repoAquario.add(aquario);
    }

    public boolean update(Aquario aquarioAlterado){
        if (aquarioAlterado == null)
            return false;

        return repoAquario.update(aquarioAlterado);
    }

    public Aquario deleteById(int id){
        if (id < 0)
            return null;

        return repoAquario.deleteById(id);
    }

    public Aquario getById(int id){
        if (id < 0)
            return null;

        return repoAquario.getById(id);
    }

    public List<Aquario> getAll(){
        return repoAquario.getAll();
    }
}
