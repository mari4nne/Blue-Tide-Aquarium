package ui;

import business.TipoAgua;

public class UIConfig {
    private static int larguraAquarioId = 5;
    private static int larguraAquarioCodigo = 12;
    private static int larguraAquarioVolume = 10;
    private static int larguraAquarioTipo = 10;
    private static int larguraAquarioDono = 10;

    private static int larguraPeixeId = 5;
    private static int larguraPeixeNome = 10;
    private static int larguraPeixeTipoAgua = 10;
    private static int larguraPeixeIdAquario = 12;
    private static int larguraPeixeDono = 10;

    private static int larguraUsuarioId = 5;
    private static int larguraUsuarioNome = 12;
    private static int larguraUsuarioTipo = 10;
    private static int larguraUsuarioFone = 10;

    public static void cabecalhoAquario(boolean showId){
        String codId = "";
        if (showId)
            codId = "Id";
        else
            codId = "Cod";
        System.out.printf("%-" + UIConfig.larguraAquarioId + "s " +
                        "%-" + UIConfig.larguraAquarioCodigo + "s " +
                        "%-" + UIConfig.larguraAquarioVolume + "s " +
                        "%-" + UIConfig.larguraAquarioTipo + "s " +
                        "%-" + UIConfig.larguraAquarioDono + "s%n",
                codId, "CdIdentif", "Volume", "Tipo", "Dono");
    }

    public static void cabecalhoUsuario(boolean showId){
        String codId = "";
        if (showId)
            codId = "Id";
        else
            codId = "Cod";
        System.out.printf("%-" + UIConfig.larguraUsuarioId + "s " +
                        "%-" + UIConfig.larguraUsuarioNome + "s " +
                        "%-" + UIConfig.larguraUsuarioTipo + "s " +
                        "%-" + UIConfig.larguraUsuarioFone + "s%n",
                codId, "Nome", "Tipo", "Fone");
    }

    public static void cabecalhoPeixe(boolean showId){
        String codId = "";
        if (showId)
            codId = "Id";
        else
            codId = "Cod";
        System.out.printf("%-" + UIConfig.larguraPeixeId + "s " +
                        "%-" + UIConfig.larguraPeixeNome + "s " +
                        "%-" + UIConfig.larguraPeixeTipoAgua + "s " +
                        "%-" + UIConfig.larguraPeixeIdAquario + "s " +
                        "%-" + UIConfig.larguraPeixeDono + "s%n",
                codId, "Nome", "Tipo", "IdAquario", "Dono");
    }

    public static void listagemAquario(int cod, String codI, float volume, TipoAgua tipo, String nome){
        System.out.printf("%-" + UIConfig.larguraAquarioId + "d " +
                        "%-" + UIConfig.larguraAquarioCodigo + "s " +
                        "%-" + UIConfig.larguraAquarioVolume + ".1f " +
                        "%-" + UIConfig.larguraAquarioTipo + "s " +
                        "%-" + UIConfig.larguraAquarioDono + "s%n",
                cod, codI, volume, tipo, nome);
    }

    public static void listagemPeixe(int cod, String nome, String tipo, int idAquario, String dono){
        System.out.printf("%-" + UIConfig.larguraPeixeId + "d " +
                        "%-" + UIConfig.larguraPeixeNome + "s " +
                        "%-" + UIConfig.larguraPeixeTipoAgua + "s " +
                        "%-" + UIConfig.larguraPeixeIdAquario + "d " +
                        "%-" + UIConfig.larguraPeixeDono + "s%n",
                cod, nome, tipo, idAquario, dono);
    }

    public static void listagemUsuario(int cod, String nome, String tipo, String fone){
        System.out.printf("%-" + UIConfig.larguraUsuarioId + "d " +
                        "%-" + UIConfig.larguraUsuarioNome + "s " +
                        "%-" + UIConfig.larguraUsuarioTipo + "s " +
                        "%-" + UIConfig.larguraUsuarioFone + "s%n",
                cod, nome, tipo, fone);
    }

    public static void setLarguraAquarioId(int larguraAquarioId) {
        if (larguraAquarioId > 0)
            UIConfig.larguraAquarioId = larguraAquarioId;
    }

    public static void setLarguraAquarioCodigo(int larguraAquarioCodigo) {
        if (larguraAquarioCodigo > 0)
            UIConfig.larguraAquarioCodigo = larguraAquarioCodigo;
    }

    public static void setLarguraAquarioVolume(int larguraAquarioVolume) {
        if (larguraAquarioVolume > 0)
            UIConfig.larguraAquarioVolume = larguraAquarioVolume;
    }

    public static void setLarguraAquarioTipo(int larguraAquarioTipo) {
        if (larguraAquarioTipo > 0)
            UIConfig.larguraAquarioTipo = larguraAquarioTipo;
    }

    public static void setLarguraAquarioDono(int larguraAquarioDono) {
        if (larguraAquarioDono > 0)
            UIConfig.larguraAquarioDono = larguraAquarioDono;
    }

    public static void setLarguraPeixeId(int larguraPeixeId) {
        if (larguraPeixeId > 0)
            UIConfig.larguraPeixeId = larguraPeixeId;
    }

    public static void setLarguraPeixeNome(int larguraPeixeNome) {
        if (larguraPeixeNome > 0)
            UIConfig.larguraPeixeNome = larguraPeixeNome;
    }

    public static void setLarguraPeixeTipoAgua(int larguraPeixeTipoAgua) {
        if (larguraPeixeTipoAgua > 0)
            UIConfig.larguraPeixeTipoAgua = larguraPeixeTipoAgua;
    }

    public static void setLarguraPeixeIdAquario(int larguraPeixeIdAquario) {
        if (larguraPeixeIdAquario > 0)
            UIConfig.larguraPeixeIdAquario = larguraPeixeIdAquario;
    }

    public static void setLarguraPeixeDono(int larguraPeixeDono) {
        if (larguraPeixeDono > 0)
            UIConfig.larguraPeixeDono = larguraPeixeDono;
    }

    public static void setLarguraUsuarioId(int larguraUsuarioId) {
        if (larguraUsuarioId > 0)
            UIConfig.larguraUsuarioId = larguraUsuarioId;
    }

    public static void setLarguraUsuarioNome(int larguraUsuarioNome) {
        if (larguraUsuarioNome > 0)
            UIConfig.larguraUsuarioNome = larguraUsuarioNome;
    }

    public static void setLarguraUsuarioTipo(int larguraUsuarioTipo) {
        if (larguraUsuarioTipo > 0)
            UIConfig.larguraUsuarioTipo = larguraUsuarioTipo;
    }

    public static void setLarguraUsuarioFone(int larguraUsuarioFone) {
        if (larguraUsuarioFone > 0)
            UIConfig.larguraUsuarioFone = larguraUsuarioFone;
    }

    public static int getLarguraAquarioId() {
        return larguraAquarioId;
    }

    public static int getLarguraAquarioCodigo() {
        return larguraAquarioCodigo;
    }

    public static int getLarguraAquarioVolume() {
        return larguraAquarioVolume;
    }

    public static int getLarguraAquarioTipo() {
        return larguraAquarioTipo;
    }

    public static int getLarguraAquarioDono() {
        return larguraAquarioDono;
    }

    public static int getLarguraPeixeId() {
        return larguraPeixeId;
    }

    public static int getLarguraPeixeNome() {
        return larguraPeixeNome;
    }

    public static int getLarguraPeixeTipoAgua() {
        return larguraPeixeTipoAgua;
    }

    public static int getLarguraPeixeIdAquario() {
        return larguraPeixeIdAquario;
    }

    public static int getLarguraPeixeDono() {
        return larguraPeixeDono;
    }

    public static int getLarguraUsuarioId() {
        return larguraUsuarioId;
    }

    public static int getLarguraUsuarioNome() {
        return larguraUsuarioNome;
    }

    public static int getLarguraUsuarioTipo() {
        return larguraUsuarioTipo;
    }

    public static int getLarguraUsuarioFone() {
        return larguraUsuarioFone;
    }
}
