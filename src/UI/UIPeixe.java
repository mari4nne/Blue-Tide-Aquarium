package ui;

import business.Peixe;
import business.PeixeController;
import business.TipoAgua;
import business.Usuario;

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

        Peixe novoPeixe = Peixe.getInstance(nome, tipo, idAquario, idUsuario);

        if (novoPeixe != null && controlador.add(novoPeixe)){
            System.out.println("Peixe criado!");
        } else {
            System.out.println("Falha ao criar peixe!");
        }
    }

    public void update(){

    }

    public void deleteById(){
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
                System.out.println(peixe.getIdPeixe() + " " + peixe.getNomePeixe() + " " + peixe.getIdAquario() + " " + peixe.getIdAquario() + "AGUA DOCE");
            }
            if (peixe.getTipoAgua() == TipoAgua.SALGADA) {
                System.out.println(peixe.getIdPeixe() + " " + peixe.getNomePeixe() + " " + peixe.getIdAquario() + " " + peixe.getIdAquario() + "AGUA SALGADA");
            }
        }
    }
}
