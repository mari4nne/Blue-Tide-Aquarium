package business;

public class Peixe {
    private static int geraId = 0;
    private final int idPeixe;
    private String nomePeixe;
    private final TipoAgua tipoAgua;
    private int idAquario;
    private int idUsuario;

    private Peixe (String nomePeixe, TipoAgua tipoAgua, int idAquario, int idUsuario) {
        this.idPeixe = geraId++;
        this.nomePeixe = nomePeixe;
        this.tipoAgua = tipoAgua;
        this.idAquario = idAquario;
        this.idUsuario = idUsuario;
    }

    public static Peixe getInstance(String nomePeixe, TipoAgua tipoAgua, int idAquario, int idUsuario) {
        if (!nomePeixe.isBlank() && tipoAgua != null && idAquario >= 0 && idUsuario >= 0) {
            return new Peixe(nomePeixe, tipoAgua, idAquario, idUsuario);
        } else {
            return null;
        }
    }

    public Peixe (Peixe outro) {
        this.idPeixe = outro.idPeixe;
        this.nomePeixe = outro.nomePeixe;
        this.tipoAgua = outro.tipoAgua;
        this.idAquario = outro.idAquario;
        this.idUsuario = outro.idUsuario;
    }

    public TipoAgua getTipoAgua() {
        return tipoAgua;
    }

    public String getNomePeixe() {
        return nomePeixe;
    }

    public void setNomePeixe(String nomePeixe) {
        this.nomePeixe = nomePeixe;
    }

    public int getIdPeixe() {
        return this.idPeixe;
    }

    public int getIdAquario() {
        return idAquario;
    }

    public void setIdAquario(int idAquario) {
        this.idAquario = idAquario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}