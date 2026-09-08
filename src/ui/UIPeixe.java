package ui;

import business.Peixe;
import business.PeixeController;
import business.TipoAgua;
import business.Usuario;
import business.Aquario;

import java.util.List;
import java.util.Scanner;

public class UIPeixe {
    private final PeixeController controlador;

    private Scanner scn;
    private Scanner scl; // Scanner para ler strings

    public UIPeixe(){
        controlador = new PeixeController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add(){
        String nome = "";
        do {
            System.out.println("Nome: ");
            nome = scl.nextLine();
        } while (nome.isBlank());

        TipoAgua tipo = null;
        int escolha;

        do {
            System.out.println();

            System.out.println("Tipos:");
            System.out.println("1: Água doce;");
            System.out.println("2: Água salgada;");
            System.out.println("Tipo escolhido:");
            escolha = scn.nextInt();

            if (escolha == 1) {
                tipo = TipoAgua.DOCE;
            }

            if (escolha == 2) {
                tipo = TipoAgua.SALGADA;
            }
        } while (escolha != 1 && escolha != 2);


        System.out.println();

        System.out.println("Digite o id do aquário ao qual o peixe pertence:");
        int idAquario = scn.nextInt();

        System.out.println();

        System.out.println("Digite o id do usuário ao qual o peixe pertence:");
        int idUsuario = scn.nextInt();

        if (controlador.verifyUsuarioExistence(idUsuario) && controlador.verifyAquarioExistence(idAquario)) {
            Peixe novoPeixe = Peixe.getInstance(nome, tipo, idAquario, idUsuario);

            if (novoPeixe != null && controlador.add(novoPeixe)){
                System.out.println("Peixe cadastrado!");
            } else {
                System.out.println("Falha ao cadastrar peixe!");
            }
        } else {
            System.out.println("Não foi possível cadastrar peixe!");
        }
    }

    public void switchAquario() {
        System.out.println("Verifique qual peixe deseja mudar de aquário:");
        showAll();

        System.out.println();
        System.out.println("Insira abaixo o id do peixe:");
        int codPeixe = scn.nextInt();

        do {
            System.out.println("Insira um valor válido");
            codPeixe = scn.nextInt();
        } while (codPeixe < 0);

        Peixe verificando = controlador.getById(codPeixe);

        System.out.println();
        System.out.println("Insira abaixo o id do aquário pra onde deseja transferir:");
        int codAquario = scn.nextInt();

        do {
            System.out.println("Insira um valor válido");
            codAquario = scn.nextInt();
        } while (codAquario < 0);

        System.out.println();
        System.out.println("Verificando tipo do aquário...");

        if (controlador.verifyTipoAgua(verificando, codAquario)
                && controlador.verifyDonoAquario(verificando, codAquario)
                && controlador.verifyUsuarioExistence(verificando.getIdUsuario())) {
            verificando.setIdAquario(codAquario);

            if (controlador.update(verificando)) {
                System.out.println("Peixe transferido com sucesso!");
            } else {
                System.out.println("Falha ao transferir peixe!");
            }
        } else {
            System.out.println("Não foi possível transferir: tipo de água incompatível, aquário não pertence ao usuário do peixe, ou usuário inexistente.");
        }
    }

    public void update(){
        showAll();

        int codigo;
        do {
            System.out.println("Código do peixe:");
            codigo = scn.nextInt();
        } while (codigo < 0);

        Peixe peixeAlterado = controlador.getById(codigo);
        if (peixeAlterado == null) {
            System.out.println("Peixe não encontrado.");
            return;
        }

        String nome = null;
        int escolha;
        do {
            System.out.println("Alterar nome:");
            System.out.println("1) - Sim");
            System.out.println("2) - Não");
            escolha = scn.nextInt();
            if (escolha == 1) {
                System.out.println("Novo nome do peixe:");
                nome = scl.nextLine();
            }
        } while (escolha != 1 && escolha != 2);

        if (nome != null) {
            peixeAlterado.setNomePeixe(nome);
        }

        if (controlador.update(peixeAlterado)) {
            System.out.println("Peixe alterado!");
        } else {
            System.out.println("Falha ao tentar alterar peixe!");
        }
    }

    public void delete(){
        showAll();
        int codigo;
        do {
            System.out.println("Codigo do peixe:");
            codigo = scn.nextInt();
        } while (codigo < 0);

        Peixe peixeEncontrado = controlador.getById(codigo);

        Peixe peixeRecuperado = controlador.deleteById(peixeEncontrado.getIdPeixe());

        if (peixeRecuperado != null) {
            System.out.println("O peixe foi excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir peixe!");
        }
    }

    public void getById(){
        int codigo;
        do {
            System.out.println("Id do peixe que deseja excluir:");
            codigo = scn.nextInt();
        } while (codigo < 0);

        Peixe peixeEncontrado = controlador.getById(codigo);

        if (peixeEncontrado != null && peixeEncontrado.getTipoAgua() == TipoAgua.DOCE) {
            System.out.println();
            System.out.println("Nome: " + peixeEncontrado.getNomePeixe());
            System.out.println("Tipo de água: Doce");
            System.out.println("Id do aquário: " + peixeEncontrado.getIdAquario());
            System.out.println("Id do usuário: " + peixeEncontrado.getIdUsuario());
        }

        if (peixeEncontrado != null && peixeEncontrado.getTipoAgua() == TipoAgua.SALGADA) {
            System.out.println();
            System.out.println("Nome: " + peixeEncontrado.getNomePeixe());
            System.out.println("Tipo de água: Salgada");
            System.out.println("Id do aquário: " + peixeEncontrado.getIdAquario());
            System.out.println("Id do usuário: " + peixeEncontrado.getIdUsuario());
        }

        if (peixeEncontrado == null) {
            System.out.println();
            System.out.println("Peixe não encontrado.");
        }
    }

    public void showAll(){
        System.out.println();
        System.out.println("Peixes:");
        List<Peixe> peixes = controlador.getAll();
        for (Peixe peixe : peixes) {
            if (peixe.getTipoAgua() == TipoAgua.DOCE) {
                System.out.println(peixe.getIdPeixe() + " " + peixe.getNomePeixe() + " " + peixe.getIdAquario() + " " + peixe.getIdAquario() + " AGUA DOCE");
            }
            if (peixe.getTipoAgua() == TipoAgua.SALGADA) {
                System.out.println(peixe.getIdPeixe() + " " + peixe.getNomePeixe() + " " + peixe.getIdAquario() + " " + peixe.getIdAquario() + " AGUA SALGADA");
            }
        }
    }
}