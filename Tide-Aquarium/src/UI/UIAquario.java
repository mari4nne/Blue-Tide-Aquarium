package UI;

import Business.Aquario;
import Business.AquarioController;
import Business.TipoAgua;

import java.util.Scanner;

public class UIAquario {
    private final UIUsuario uiUsuario;
    private final AquarioController controlador;

    private Scanner scn;
    private Scanner scl; //Usado para ler Strings

    public UIAquario(){
        uiUsuario = new UIUsuario();
        controlador = new AquarioController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add(){
        uiUsuario.listar();
        System.out.print("Id do usuario: ");
        int idUsuario;
        do{
            idUsuario = scn.nextInt(); //Falta verificar se existe
        } while (idUsuario < 0);

        System.out.print("Codigo identificador: ");
        String cod = scl.nextLine();

        System.out.print("Volume: ");
        double volume = scn.nextDouble();

        TipoAgua tipo = null;
        int escolha;
        do {
            System.out.println();

            System.out.println("Tipos");
            System.out.println("1: Agua DOCE");
            System.out.println("2: Agua SALGADA");
            System.out.print("Tipo escolhido: ");
            escolha = scn.nextInt();

            if (escolha == 1)
                tipo = TipoAgua.DOCE;

            if (escolha == 2)
                tipo = TipoAgua.SALGADA;

        } while (escolha != 1 && escolha != 2);

        Aquario novoAquario = Aquario.getInstance(idUsuario, cod, volume, tipo);

        if (novoAquario != null && controlador.add(novoAquario))
            System.out.println("Aquario criado!");
        else
            System.out.println("Falha ao criar aquario!");
    }

    public void update(){
        listar();
        System.out.println("Id do aquario: ");
        int id;
        do {
            id = scn.nextInt();
        } while (id < 0);

        Aquario aquarioAlterado = controlador.getById(id);
        if (aquarioAlterado == null){
            System.out.println("Aquario nao encontrado.");
            return;
        }

        uiUsuario.listar();
        System.out.print("Id do usuario: ");
        int idUsuario;
        do{
            idUsuario = scn.nextInt(); //Falta verificar se existe
        } while (idUsuario < 0);

        System.out.print("Codigo identificador: ");
        String cod = scl.nextLine();

        System.out.print("Volume: ");
        double volume = scn.nextDouble();

        TipoAgua tipo = null;
        int escolha;
        do {
            System.out.println();

            System.out.println("Tipos");
            System.out.println("1: Agua DOCE");
            System.out.println("2: Agua SALGADA");
            System.out.print("Tipo escolhido: ");
            escolha = scn.nextInt();

            if (escolha == 1)
                tipo = TipoAgua.DOCE;

            if (escolha == 2)
                tipo = TipoAgua.SALGADA;

        } while (escolha != 1 && escolha != 2);

        aquarioAlterado.setUsuarioId(idUsuario);
        aquarioAlterado.setCod(cod);
        aquarioAlterado.setVolume(volume);
        aquarioAlterado.setTipo(tipo);

        if (controlador.update(aquarioAlterado))
            System.out.println("Aquario alterado!");
        else
            System.out.println("Falha em alterar o aquario.");
    }

    public void deleteById(){
        listar();
        System.out.print("Id do aquario: ");

        int id;
        do {
            id = scn.nextInt();
        } while (id < 0);

        Aquario aquarioExcluido = controlador.deleteById(id);
        if (aquarioExcluido != null)
            System.out.println("O aquario foi excluido com sucesso!");
        else
            System.out.println("Erro ao excluir aquario.");
    }

    public void getById(){

    }

    public void listar(){

    }
}
