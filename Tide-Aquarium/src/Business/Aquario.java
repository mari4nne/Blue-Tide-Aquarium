package Business;

public class Aquario {
    private static int novoId = 0;

    private final int id;
    private int usuarioId; //Relação N:1 com Usuario
    private String cod;
    private double volume;
    private TipoAgua tipo;

    private Aquario(){
        id = novoId++;
    }

    private Aquario(int usuarioId, String cod, double volume, TipoAgua tipo){
        this();
        this.usuarioId = usuarioId;
        this.cod = cod;
        this.volume = volume;
        this.tipo = tipo;
    }

    public Aquario(Aquario outro){
        this.id = outro.id;
        this.usuarioId = outro.usuarioId;
        this.cod = outro.cod;
        this.volume = outro.volume;
        this.tipo = outro.tipo;
    }

    public static Aquario getInstance(int usuarioId, String cod, double volume, TipoAgua tipo){
        if (!cod.isBlank() && volume >= 0 && tipo != null)
            return new Aquario(usuarioId, cod, volume, tipo);
        return null;
    }

    public int getId() {
        return id;
    }

    public void setUsuarioId(int usuarioId) {
        if (usuarioId > 0)
            this.usuarioId = usuarioId;
    }

    public void setCod(String cod) {
        if (!cod.isBlank())
            this.cod = cod;
    }

    public void setVolume(double volume) {
        if (volume >= 0)
           this.volume = volume;
    }

    public void setTipo(TipoAgua tipo) {
        if (tipo != null)
          this.tipo = tipo;
    }
}
