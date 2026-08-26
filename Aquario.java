package controller;
public class Aquario {

	private static int geraId = 1;
	private int codigo;
	private int id;
	private float volume;
    private TipoPeixe tipo;
    
    public Aquario(Aquario a) {
        this.id = a.id;
        this.codigo = a.codigo;
        this.volume = a.volume;
        this.tipo = a.tipo;
    }

    private Usuario(String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
        this.id = geraId++;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    
}
