package Business;

import UI.UIUsuario;

import java.util.Scanner;

public class Main {
    private static Scanner scn = new Scanner(System.in);
    private static Scanner scl = new Scanner(System.in); //Usado para ler Strings
    private static final UIUsuario uiUsuario = new UIUsuario();

    public static void main() {
        int escolha;

        //temporario
        do {
            System.out.println();
            System.out.println("CRUD Usuario");
            System.out.println("1: Adicionar usuario");
            System.out.println("2: Atualizar usuario");
            System.out.println("3: Excluir usuario");
            System.out.println("4: Buscar usuario pelo ID");
            System.out.println("5: Listar usuarios");
            System.out.println("0: Sair");
            System.out.print("Sua escolha: ");
            escolha = scn.nextInt();

            switch (escolha) {
                case 1:
                    uiUsuario.add();
                    break;
                case 2:
                    uiUsuario.update();
                    break;
                case 3:
                    uiUsuario.delete();
                    break;
                case 4:
                    uiUsuario.getById();
                    break;
                case 5:
                    uiUsuario.showAll();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Operacao invalida.");
                    break;
            }

        } while (escolha != 0);
    }
}
