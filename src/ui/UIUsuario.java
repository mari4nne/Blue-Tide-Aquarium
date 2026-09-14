package ui;

import business.TipoUsuario;
import business.Usuario;
import business.UsuarioController;

import java.util.List;
import java.util.Scanner;

public class UIUsuario {
    private final UsuarioController controlador;

    private Scanner scn;
    private Scanner scl;

    public UIUsuario(){
        controlador = new UsuarioController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add(){
        String nome = "";
        do {
            System.out.print("Nome: ");
            nome = scl.nextLine();
        } while (nome.isBlank());

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
                tipo = TipoUsuario.LOJA;

        } while (escolha != 1 && escolha != 2);

        System.out.println();


        String fone = null;
        do {
        System.out.print("Telefone (ex: 91111-1111): ");
            fone = scl.nextLine();
        } while (fone.length() != 10);

        Usuario novoUsuario = Usuario.getInstance(nome, tipo, fone);

        if (novoUsuario != null && controlador.add(novoUsuario))
            System.out.println("Usuario criado!");
        else
            System.out.println("Falha ao criar usuario!");
    }

    public void update(){
        showAll();

        int cod;
        do {
            System.out.print("Codigo do usuario: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Usuario usuarioAlterado = controlador.getBySequence(cod);
        if (usuarioAlterado == null) {
            System.out.println("Usuario nao encontrado.");
            return;
        }

        String nome = null;
        String fone = null;
        TipoUsuario tipo = null;
        boolean alterado = false;
        int escolha;
        do {
            System.out.println("Alterar nome: (1) Sim, (2) nao");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                System.out.print("Nome: ");
                nome = scl.nextLine();
            }
        } while (escolha != 1 && escolha != 2);

        do {
            System.out.println("Alterar tipo: (1) Sim, (2) nao");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                int preferencia;

                do {
                    System.out.println("Tipos");
                    System.out.println("1: Padrao");
                    System.out.println("2: Admin");
                    System.out.print("Tipo escolhido: ");
                    preferencia = scn.nextInt();

                    if (preferencia == 1)
                        tipo = TipoUsuario.PADRAO;

                    if (preferencia == 2)
                        tipo = TipoUsuario.LOJA;

                } while (preferencia != 1 && preferencia != 2);
            }
        } while (escolha != 1 && escolha != 2);

        do {
            System.out.println("Alterar telefone: (1) Sim, (2) nao");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                do {
                    System.out.print("Novo telefone (ex: 91111-1111): ");
                    fone = scl.nextLine();
                } while (fone.length() != 10);
            }
        } while (escolha != 1 && escolha != 2);

        if (!alterado) {
            System.out.println("Usuario sem alteraçoes.");
            return;
        }

        if (nome != null)
            usuarioAlterado.setNome(nome);
        if (tipo != null)
            usuarioAlterado.setTipo(tipo);
        if (fone != null)
            usuarioAlterado.setFone(fone);

        if (controlador.update(usuarioAlterado))
            System.out.println("Usuario alterado!");
        else
            System.out.println("Falha em alterar o usuario.");
    }

    public void delete(){
        showAll();
        int cod;
        do {
            System.out.print("Codigo do usuario: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Usuario usuarioEncontrado = controlador.getBySequence(cod);
        if (usuarioEncontrado == null) {
            System.out.println("Usuario nao encontrado.");
            return;
        }

        Usuario usuarioRecuperado = controlador.deleteById(usuarioEncontrado.getId());
        if (usuarioRecuperado != null)
            System.out.println("O usuario foi excluido com sucesso!");
        else
            System.out.println("Erro ao excluir usuario.");
    }

    public void getById(){
        showAllWithId();

        int id;
        do {
            System.out.print("Id do usuario: ");
            id = scn.nextInt();
        } while (id < 0);

        Usuario usuarioEncontrado = controlador.getById(id);

        if (usuarioEncontrado != null) {
            System.out.println("Informacoes do usuario");
            System.out.println("Nome: " + usuarioEncontrado.getNome());
            System.out.println("Tipo: " + usuarioEncontrado.getTipo());
            System.out.println("Telefone: " + usuarioEncontrado.getFone());

        } else
            System.out.println("Nao foi possivel encontrar o usuario.");
    }

    public void showAll(){
        System.out.println();
        System.out.println("Usuarios:");

        UIConfig.cabecalhoUsuario(false);

        List<Usuario> usuarios = controlador.getAll();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getTipo() == TipoUsuario.PADRAO)
                UIConfig.listagemUsuario((i+1), usuarios.get(i).getNome(), "PADRAO", usuarios.get(i).getFone());
            else
                UIConfig.listagemUsuario((i+1), usuarios.get(i).getNome(), "LOJA", usuarios.get(i).getFone());
        }
    }

    public void showAllWithId(){
        System.out.println();
        System.out.println("Usuarios:");

        UIConfig.cabecalhoUsuario(true);

        List<Usuario> usuarios = controlador.getAll();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getTipo() == TipoUsuario.PADRAO)
                UIConfig.listagemUsuario(usuarios.get(i).getId(), usuarios.get(i).getNome(), "PADRAO", usuarios.get(i).getFone());
            else
                UIConfig.listagemUsuario(usuarios.get(i).getId(), usuarios.get(i).getNome(), "LOJA", usuarios.get(i).getFone());
        }
    }

    public void mudarListagem() {
        UIConfig.setLarguraUsuarioId(solicitarNovaLargura("Cod/Id", UIConfig.getLarguraUsuarioId()));
        UIConfig.setLarguraUsuarioNome(solicitarNovaLargura("Nome", UIConfig.getLarguraUsuarioNome()));
        UIConfig.setLarguraUsuarioTipo(solicitarNovaLargura("Tipo", UIConfig.getLarguraUsuarioTipo()));
        UIConfig.setLarguraUsuarioFone(solicitarNovaLargura("Fone", UIConfig.getLarguraUsuarioFone()));

        System.out.println("Larguras modificadas!");
    }

    private int solicitarNovaLargura(String nomeColuna, int valorAtual) {
        int escolha;
        do {
            System.out.println("Alterar largura de " + nomeColuna + ": (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();

            if (escolha == 1) {
                int novaLargura;
                do {
                    System.out.print("Nova largura (entre 5 e 30): ");
                    novaLargura = scn.nextInt();
                } while (novaLargura < 5 || novaLargura > 30);
                return novaLargura;
            }
        } while (escolha != 2);

        return valorAtual;
    }
}
