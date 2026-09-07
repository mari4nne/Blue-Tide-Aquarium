package business;
import java.time.LocalTime;
public class Aquario {

    private static int geraId = 0;

    private String codigo;
    private int id;
    private float volume;
    private TipoAgua tipo;
    private Usuario usuario;
    private LocalTime ultimaAlimentacao;

    private Aquario(){
        this.id = geraId++;
    }

    private Aquario(String codigo, float volume, TipoAgua tipo, Usuario usuario){

        this();
        this.codigo = codigo;
        this.volume = volume;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Aquario(Aquario a){

        this.id = a.id;
        this.codigo = a.codigo;
        this.volume = a.volume;
        this.tipo = a.tipo;
        this.usuario = a.usuario;
        this.ultimaAlimentacao = a.ultimaAlimentacao;
    }

    public static Aquario getInstance(String codigo, float volume, TipoAgua tipo, Usuario usuario){

        if(!codigo.isBlank() && volume > 0 && tipo != null && usuario != null)
            return new Aquario(codigo, volume, tipo, usuario);
        return null;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
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

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public LocalTime getUltimaAlimentacao(){
        return ultimaAlimentacao;
    }
    
    public void setUltimaAlimentacao(LocalTime ultimaAlimentacao){
        this.ultimaAlimentacao = ultimaAlimentacao;
    }

    public LocalTime getProximaAlimentacao(){
        if(ultimaAlimentacao == null)
            return null;
        return ultimaAlimentacao.plusHours(10);
    }
}
