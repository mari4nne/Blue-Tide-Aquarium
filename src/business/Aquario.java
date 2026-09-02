package business;
public class Aquario {

    private static int geraId = 0;
    private int codigo;
    private int id;
    private float volume;
    private TipoAgua tipo;
    public Aquario(){
        this.id = geraId++;
    }
    public Aquario(Aquario a){
        this.id = a.id;
        this.codigo = a.codigo;
        this.volume = a.volume;
        this.tipo = a.tipo;
    }
    public static int getGeraId(){
        return geraId;
    }
    public static void setGeraId(int geraId){
        Aquario.geraId = geraId;
	}
    public int getCodigo(){
        return codigo;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public float getVolume(){
        return volume;
    }
    public void setVolume(float volume){
        this.volume = volume;
    }
    public TipoAgua getTipo(){
        return tipo;
    }
    public void setTipo(TipoAgua tipo){
        this.tipo = tipo;
    }
}

