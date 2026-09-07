package repository;
import java.util.ArrayList;
import java.util.List;
import business.Aquario;

public class AquarioRepository {
    private final List<Aquario> aquarios;
    public AquarioRepository(){
        aquarios = new ArrayList<>();
    }
    public boolean add(Aquario aquario){
        if(aquario == null)
            return false;
        aquarios.add(aquario);
        return true;
    }
    public boolean update(Aquario aquarioAlterado){
        if(aquarioAlterado == null)
            return false;
        for(int i = 0; i < aquarios.size(); i++){
            if(aquarios.get(i).getId() == aquarioAlterado.getId()){
                aquarios.set(i, aquarioAlterado);
                return true;
            }
        }
        return false;
    }
    public Aquario deleteById(int id){
        for(int i = 0; i < aquarios.size(); i++){
            if(aquarios.get(i).getId() == id){
                return aquarios.remove(i);
            }
        }
        return null;
    }
    public Aquario getById(int id){
        for(Aquario aquario : aquarios){
            if(aquario.getId() == id){
                return aquario;
            }
        }
        return null;
    }
    public List<Aquario> getAll(){
        return aquarios;
    }
}
