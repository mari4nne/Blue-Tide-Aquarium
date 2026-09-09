package business;
import java.time.LocalTime;

public class Aquario {

    private static int geraId = 0;
    private String codigo;
    private int id;
    private float volume;
    private TipoAgua tipo;
    private int idUsuario;
    private LocalTime ultimaAlimentacao;
    private boolean excluido;

    private Aquario(){
        this.id = geraId++;
        this.excluido = false;
    }

    private Aquario(String codigo, float volume, TipoAgua tipo, int idUsuario){

        this();
        this.codigo = codigo;
        this.volume = volume;
        this.tipo = tipo;
        this.idUsuario = idUsuario;

    }

    public Aquario(Aquario a){

        this.id = a.id;
        this.codigo = a.codigo;
        this.volume = a.volume;
        this.tipo = a.tipo;
        this.idUsuario = a.idUsuario;
        this.ultimaAlimentacao = a.ultimaAlimentacao;

    }

    public static Aquario getInstance(String codigo, float volume, TipoAgua tipo, int idUsuario){

        if(!codigo.isBlank() && volume > 0 && tipo != null && idUsuario >= 0)
            return new Aquario(codigo, volume, tipo, idUsuario);
        return null;

    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        if(!codigo.isBlank())
            this.codigo = codigo;
    }

    public int getId(){
        return id;
    }

    public float getVolume(){
        return volume;
    }

    public void setVolume(float volume){
        if(volume > 0)
            this.volume = volume;
    }

    public TipoAgua getTipo(){
        return tipo;
    }

    public void setTipo(TipoAgua tipo){
        if(tipo != null)
            this.tipo = tipo;
    }

    public int getIdUsuario(){
        return idUsuario;
    }

    public LocalTime getUltimaAlimentacao(){
        return ultimaAlimentacao;
    }

    public void setUltimaAlimentacao(LocalTime ultimaAlimentacao){

        if(ultimaAlimentacao != null)
            this.ultimaAlimentacao = ultimaAlimentacao;
    }

    public LocalTime getProximaAlimentacao(){

        if(ultimaAlimentacao == null)
            return null;
        return ultimaAlimentacao.plusHours(10);

    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
}
