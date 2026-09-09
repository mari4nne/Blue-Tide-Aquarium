package repository;
import java.util.ArrayList;
import java.util.List;
import business.Aquario;
import business.TipoAgua;

public class AquarioRepository {

    private final List<Aquario> aquarios;
    private static AquarioRepository instance;

    private AquarioRepository(){
        aquarios = new ArrayList<>();
        aquarios.add(Aquario.getInstance("AQ-01", 50.0f, TipoAgua.DOCE, 0));
        aquarios.add(Aquario.getInstance("AQ-02", 120.5f, TipoAgua.SALGADA, 1));
    }

    public static AquarioRepository getInstance() {
        if (instance == null) {
            instance = new AquarioRepository();
        }
        return instance;
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
                return new Aquario(aquario);
            }
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