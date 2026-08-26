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
        if (aquario == null)
            return false;
        return aquarios.add(aquario);
    }

    public boolean update(Aquario aquarioAlterado){
        if (aquarioAlterado == null)
            return false;
        for (int i = 0; i < aquarios.size(); i++){
            if (aquarios.get(i).getId() == aquarioAlterado.getId()){
                aquarios.set(i, aquarioAlterado);
                return true;
            }
        }
        return false;
    }

    public Aquario deleteById(int id){
        if (id < 0)
            return null;

        Aquario aquario = getById(id);

        if (aquario == null)
            return null;

        aquarios.remove(aquario);
        return new Aquario(aquario);
    }

    public Aquario getById(int id){
        if (id < 0)
            return null;
        for (Aquario aquario : aquarios){
            if (aquario.getId() == id)
                return aquario;
        }
        return null;
    }

    public List<Aquario> getAll(){
        List<Aquario> aux = new ArrayList<>();

        for (Aquario aquario : aquarios) {
            aux.add(new Aquario(aquario));
        }

        return aux;
    }
}
