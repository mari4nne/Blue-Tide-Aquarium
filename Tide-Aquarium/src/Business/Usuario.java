package Business;

public class Usuario {
    private static int novoId = 0;

    private int id;
    private String nome;
    private TipoUsuario tipo;
    private String fone;

    private Usuario(){
        this.id = novoId++;
    }

    private Usuario(String nome, TipoUsuario tipo, String fone){
        this();
        this.nome = nome;
        this.tipo = tipo;
        this.fone = fone;
    }

    public static Usuario getInstance(String nome, TipoUsuario tipo, String fone){
        if (!nome.isBlank() && !fone.isBlank() && tipo != null){
            return new Usuario(nome, tipo, fone);
        }
        return null;
    }

    public Usuario(Usuario outro) {
        this.id = outro.id;
        this.nome = outro.nome;
        this.tipo = outro.tipo;
        this.fone = outro.fone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFone() {
        return fone;
    }

    public void setNome(String nome) {
        if (!nome.isBlank())
            this.nome = nome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        if (tipo  != null)
            this.tipo = tipo;
    }

    public void setFone(String fone) {
        if (!fone.isBlank())
            this.fone = fone;
    }
}
