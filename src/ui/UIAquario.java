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

    private AquarioController controlador;
    private UsuarioController controladorUsuario;
    private Scanner scn;

    public UIAquario(){

        controlador = new AquarioController();
        this.controladorUsuario = new UsuarioController();
        scn = new Scanner(System.in);

    }

    public void add(){

        System.out.println("Digite o código do aquário:");
        scn.nextLine();
        String codigo = scn.nextLine();
        System.out.println("Digite o volume do aquário:");
        float volume = scn.nextFloat();

        int opcao;

        do {

            System.out.println("Digite o tipo de água:");
            System.out.println("1 - DOCE");
            System.out.println("2 - SALGADA");

            opcao = scn.nextInt();

        }while(opcao != 1 && opcao != 2);

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
            System.out.println(usuarios.get(i).getId() + " - " + usuarios.get(i).getNome());

        }

        int idUsuario;

        do {
            System.out.print("Digite o id do usuario: ");
            idUsuario = scn.nextInt();

        }while(idUsuario < 0 || controladorUsuario.getById(idUsuario) == null);
        Aquario aquario = Aquario.getInstance(codigo, volume, tipo, idUsuario);
        if(aquario != null && controlador.add(aquario)){
            System.out.println("Aquário adicionado com sucesso!");

        }else{
            System.out.println("Erro ao adicionar aquário.");
        }

    }

    public void update(){

        listar();

        System.out.println("Digite o id do aquário:");
        int id = scn.nextInt();
        Aquario aquario = controlador.getById(id);

        if(aquario == null){
            System.out.println("Aquário não encontrado.");
            return;

        }

        int escolha;

        do {

            System.out.println("Alterar código: (1) Sim, (2) nao");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();

            if(escolha == 1){

                System.out.println("Digite o novo código:");
                scn.nextLine();
                String codigo = scn.nextLine();
                aquario.setCodigo(codigo);

            }

        }while(escolha != 1 && escolha != 2);

        do {

            System.out.println("Alterar volume: (1) Sim, (2) nao");
            System.out.print("Escolha: ");

            escolha = scn.nextInt();

            if(escolha == 1){
                System.out.println("Digite o novo volume:");
                float volume = scn.nextFloat();
                aquario.setVolume(volume);

            }

        }while(escolha != 1 && escolha != 2);

        do {

            System.out.println("Alterar tipo de água: (1) Sim, (2) nao");
            System.out.print("Escolha: ");

            escolha = scn.nextInt();

            if(escolha == 1){
                int opcao;

                do {

                    System.out.println("Digite o novo tipo de água:");
                    System.out.println("1 - DOCE");
                    System.out.println("2 - SALGADA");
                    opcao = scn.nextInt();

                }while(opcao != 1 && opcao != 2);
                TipoAgua tipo;
                if(opcao == 1){
                    tipo = TipoAgua.DOCE;
                }else{
                    tipo = TipoAgua.SALGADA;
                }
                aquario.setTipo(tipo);
            }

        }while(escolha != 1 && escolha != 2);

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
        System.out.println("Alimentação registrada às " + aquario.getUltimaAlimentacao());
        System.out.println("Próxima alimentação: " + aquario.getProximaAlimentacao());

    }

}
