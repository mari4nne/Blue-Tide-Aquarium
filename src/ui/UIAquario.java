package ui;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import business.Aquario;
import business.AquarioController;
import business.TipoAgua;
import business.Usuario;
import business.UsuarioController;

public class UIAquario {

    private final AquarioController controlador;
    private final UsuarioController controladorUsuario;

    private Scanner scn;

    public UIAquario(UsuarioController controladorUsuario){

        controlador = new AquarioController();
        this.controladorUsuario = controladorUsuario;
        scn = new Scanner(System.in);

    }

    public void add(){

        System.out.println("Digite o código do aquário:");
        scn.nextLine();
        String codigo = scn.nextLine();

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

        List<Usuario> usuarios = controladorUsuario.getAll();

        if(usuarios.isEmpty()){

            System.out.println("Nenhum usuario cadastrado.");
            return;

        }

        System.out.println();
        System.out.println("Usuarios:");

        for(int i = 0; i < usuarios.size(); i++){

            System.out.println((i + 1) + " - " + usuarios.get(i).getNome());

        }

        System.out.print("Escolha o usuario: ");
        int escolhaUsuario = scn.nextInt();

        if(escolhaUsuario < 1 || escolhaUsuario > usuarios.size()){

            System.out.println("Usuario invalido.");
            return;

        }

        int idUsuario = escolhaUsuario - 1;
        Aquario aquario = Aquario.getInstance(codigo, volume, tipo, idUsuario);

        if(aquario != null && controlador.add(aquario)){

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
        scn.nextLine();
        String codigo = scn.nextLine();

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

        List<Usuario> usuarios = controladorUsuario.getAll();

        if(!usuarios.isEmpty()){

            System.out.println();
            System.out.println("Usuarios:");

            for(int i = 0; i < usuarios.size(); i++){

                System.out.println((i + 1) + " - " + usuarios.get(i).getNome());

            }

            System.out.print("Escolha o id do novo usuario: ");
            int escolhaUsuario = scn.nextInt();

            if(escolhaUsuario >= 1 && escolhaUsuario <= usuarios.size()){
                aquario.setIdUsuario(escolhaUsuario - 1);
            }
        }

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

            System.out.println("Usuario: " + controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario()));

            System.out.println("Ultima alimentação: " + aquario.getUltimaAlimentacao());
            System.out.println("Proxima alimentação: " + aquario.getProximaAlimentacao());

        }

    }

    public void listar(){

        for(Aquario aquario : controlador.getAll()){

            System.out.println("Id: " + aquario.getId());
            System.out.println("Código: " + aquario.getCodigo());
            System.out.println("Volume: " + aquario.getVolume());
            System.out.println("Tipo de água: " + aquario.getTipo());

            System.out.println("Usuario: " + controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario()));

            System.out.println("Ultima alimentação: " + aquario.getUltimaAlimentacao());
            System.out.println("Proxima alimentação: " + aquario.getProximaAlimentacao());

            System.out.println();

        }

    }

    public void alimentar(){

        System.out.println("Digite o id do aquário:");
        int id = scn.nextInt();

        Aquario aquario = controlador.getById(id);

        if(aquario == null){

            System.out.println("Aquário não encontrado.");
            return;

        }
        LocalTime horarioAtual = LocalTime.now();

        aquario.setUltimaAlimentacao(horarioAtual);

        System.out.println("Alimentação registrada às "
                + aquario.getUltimaAlimentacao());

        System.out.println("Próxima alimentação: "
                + aquario.getProximaAlimentacao());

    }

}
