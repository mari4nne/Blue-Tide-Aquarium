package business;
import ui.UIUsuario;
import ui.UIPeixe;
import ui.UIAquario;
import java.util.Scanner;

public class Main {
    private static Scanner scn = new Scanner(System.in);
    private static final UIUsuario uiUsuario = new UIUsuario();
    private static final UIPeixe uiPeixe = new UIPeixe();
    private static final UIAquario uiAquario = new UIAquario();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println();
            System.out.println("Blue-Tide-Aquarium:");
            System.out.println("1: Painel de Usuários");
            System.out.println("2: Painel de Peixes");
            System.out.println("3: Painel de Aquários");
            System.out.println("0: Sair");
            opcao = scn.nextInt();

            switch (opcao) {
                case 1:
                    painelUsuario();
                    break;
                case 2:
                    painelPeixe();
                    break;
                case 3:
                    painelAquario();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Operação inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    // temporário

    public static void painelUsuario() {
        int escolha;

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

    public static void painelPeixe () {
        int escolha;

        do {

            System.out.println();
            System.out.println("CRUD Peixe");
            System.out.println("1: Adicionar peixe");
            System.out.println("2: Atualizar peixe");
            System.out.println("3: Excluir peixe");
            System.out.println("4: Buscar peixe pelo ID");
            System.out.println("5: Listar peixes");
            System.out.println("6: Trocar peixe de aquário");
            System.out.println("0: Sair");
            System.out.print("Sua escolha: ");
            escolha = scn.nextInt();

            switch (escolha) {
                case 1:
                    uiPeixe.add();
                    break;
                case 2:
                    uiPeixe.update();
                    break;
                case 3:
                    uiPeixe.delete();
                    break;
                case 4:
                    uiPeixe.getById();
                    break;
                case 5:
                    uiPeixe.showAll();
                    break;
                case 6:
                    uiAquario.listar();
                    uiPeixe.switchAquario();
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

    public static void painelAquario() {
        int escolha;

        do {

            System.out.println();
            System.out.println("CRUD Aquario");
            System.out.println("1: Adicionar aquario");
            System.out.println("2: Atualizar aquario");
            System.out.println("3: Excluir aquario");
            System.out.println("4: Buscar aquario pelo ID");
            System.out.println("5: Listar aquarios");
            System.out.println("6: Alimentar aquario");
            System.out.println("0: Sair");
            System.out.print("Sua escolha: ");
            escolha = scn.nextInt();

            switch (escolha) {
                case 1:
                    uiAquario.add();
                    break;
                case 2:
                    uiAquario.update();
                    break;
                case 3:
                    uiAquario.deleteById();
                    break;
                case 4:
                    uiAquario.getById();
                    break;
                case 5:
                    uiAquario.listar();
                    break;
                case 6:
                    uiAquario.alimentar();
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