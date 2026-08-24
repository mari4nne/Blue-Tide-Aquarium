package UI;

import Business.TipoUsuario;
import Business.Usuario;
import Business.UsuarioController;

import java.util.ArrayList;
import java.util.Scanner;

public class UIUsuario {
    UsuarioController controlador;
    private Scanner scn;
    private Scanner scl;

    public UIUsuario(){
        controlador = new UsuarioController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add(){
        System.out.print("Nome: ");
        String nome = scl.nextLine();

        TipoUsuario tipo = null;
        int escolha;

        do {
            System.out.println();

        System.out.println("Tipos");
        System.out.println("1: Padrao");
        System.out.println("2: Admin");
            System.out.print("Tipo escolhido: ");
            escolha = scn.nextInt();

            if (escolha == 1)
                tipo = TipoUsuario.PADRAO;

            if (escolha == 2)
                tipo = TipoUsuario.ADMIN;

        } while (escolha != 1 && escolha != 2);

        System.out.println();

        System.out.print("Telefone: ");
        String fone = scl.nextLine();

        Usuario novoUsuario = Usuario.getInstance(nome, tipo, fone);

        if (novoUsuario != null && controlador.add(novoUsuario))
            System.out.println("Usuario criado!");
        else
            System.out.println("Falha ao criar usuario!");
    }

    public void update(){
        listar();
        System.out.print("Id do usuario: ");
        int id = scn.nextInt();

        Usuario uAlterado = controlador.getById(id);
        if (uAlterado == null) {
            System.out.println("Usuario nao encontrado.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scl.nextLine();

        TipoUsuario tipo = null;
        int escolha;

        do {
            System.out.println("Tipos");
            System.out.println("1: Padrao");
            System.out.println("2: Admin");
            System.out.print("Tipo escolhido: ");
            escolha = scn.nextInt();

            if (escolha == 1)
                tipo = TipoUsuario.PADRAO;

            if (escolha == 2)
                tipo = TipoUsuario.ADMIN;

        } while (escolha != 1 && escolha != 2);

        System.out.print("Novo telefone: ");
        String fone = scl.nextLine();

        uAlterado.setNome(nome);
        uAlterado.setTipo(tipo);
        uAlterado.setFone(fone);

        if (controlador.update(uAlterado))
            System.out.println("Usuario alterado!");
        else
            System.out.println("Falha em alterar o usuario.");
    }

    public void deleteById(){
        listar();
        System.out.print("Id do usuario: ");
        int id = scn.nextInt();

        Usuario usuarioExcluido = controlador.deleteById(id);
        if (usuarioExcluido != null)
            System.out.println("O usuario foi excluido com sucesso!");
        else
            System.out.println("Erro ao excluir usuario.");
    }

    public void getById(){
        listar();
        System.out.print("Id do usuario: ");
        int id = scn.nextInt();

        Usuario usuarioEncontrado = controlador.getById(id);
        if (usuarioEncontrado != null) {
            System.out.println("Nome: " + usuarioEncontrado.getNome());
            System.out.println("Tipo: " + usuarioEncontrado.getTipo());
            System.out.println("Telefone: " + usuarioEncontrado.getFone());

        } else
            System.out.println("Nao foi possivel encontrar o usuario.");
    }

    public void listar(){
        System.out.println();
        System.out.println("Usuarios:");
        ArrayList<Usuario> usuarios = controlador.getAll();
        for (Usuario u : usuarios) {
            System.out.println(u.getId() + "  " + u.getNome() + "  " + u.getFone());
        }
    }
}
