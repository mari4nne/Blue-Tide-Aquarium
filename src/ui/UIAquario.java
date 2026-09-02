package ui;

import java.util.Scanner;

import business.Aquario;
import business.AquarioController;
import business.TipoAgua;

public class UIAquario {
    private final AquarioController controlador;

    private Scanner scn;

    public UIAquario(){
        controlador = new AquarioController();
        scn = new Scanner(System.in);
    }

    public void add(){
        System.out.println("Digite o código do aquário:");
        int codigo = scn.nextInt();

        System.out.println("Digite o volume do aquário:");
        float volume = scn.nextFloat();

        System.out.println("Digite o tipo de água:");
        System.out.println("1 - DOCE");
        System.out.println("2 - SALGADA");
        int opcao = scn.nextInt();

        TipoAgua tipo;

        if(opcao == 1){
            tipo = TipoAgua.DOCE;
        }else{
            tipo = TipoAgua.SALGADA;
        }

        Aquario aquario = new Aquario();
        aquario.setCodigo(codigo);
        aquario.setVolume(volume);
        aquario.setTipo(tipo);

        if(controlador.add(aquario)){
            System.out.println("Aquário adicionado com sucesso!");
        }else{
            System.out.println("Erro ao adicionar aquário.");
        }
    }

    public void update(){
        System.out.println("Digite o id do aquário:");
        int id = scn.nextInt();

        Aquario aquario = controlador.getById(id);

        if(aquario == null){
            System.out.println("Aquário não encontrado.");
            return;
        }

        System.out.println("Digite o novo código:");
        int codigo = scn.nextInt();

        System.out.println("Digite o novo volume:");
        float volume = scn.nextFloat();

        System.out.println("Digite o novo tipo de água:");
        System.out.println("1 - DOCE");
        System.out.println("2 - SALGADA");
        int opcao = scn.nextInt();

        TipoAgua tipo;

        if(opcao == 1){
            tipo = TipoAgua.DOCE;
        }else{
            tipo = TipoAgua.SALGADA;
        }

        aquario.setCodigo(codigo);
        aquario.setVolume(volume);
        aquario.setTipo(tipo);

        if(controlador.update(aquario)){
            System.out.println("Aquário atualizado com sucesso!");
        }else{
            System.out.println("Erro ao atualizar aquário.");
        }
    }

    public void deleteById(){
        System.out.println("Digite o id do aquário:");
        int id = scn.nextInt();

        Aquario aquario = controlador.deleteById(id);

        if(aquario == null){
            System.out.println("Aquário não encontrado.");
        }else{
            System.out.println("Aquário excluído com sucesso!");
        }
    }

    public void getById(){
        System.out.println("Digite o id do aquário:");
        int id = scn.nextInt();

        Aquario aquario = controlador.getById(id);

        if(aquario == null){
            System.out.println("Aquário não encontrado.");
        }else{
            System.out.println("Id: " + aquario.getId());
            System.out.println("Código: " + aquario.getCodigo());
            System.out.println("Volume: " + aquario.getVolume());
            System.out.println("Tipo de água: " + aquario.getTipo());
        }
    }
    public void listar(){
        for(Aquario aquario : controlador.getAll()){
            System.out.println("Id: " + aquario.getId());
            System.out.println("Código: " + aquario.getCodigo());
            System.out.println("Volume: " + aquario.getVolume());
            System.out.println("Tipo de água: " + aquario.getTipo());
            System.out.println();
        }
        }
}
