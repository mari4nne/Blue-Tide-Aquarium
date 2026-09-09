package ui;

import business.*;

import java.util.List;
import java.util.Scanner;

public class UIPeixe {
    private final PeixeController controlador;

    private Scanner scn;
    private Scanner scl;

    public UIPeixe() {
        controlador = new PeixeController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add() {
        String nome = "";
        do {
            System.out.print("Nome: ");
            nome = scl.nextLine();
        } while (nome.isBlank());

        TipoAgua tipo = null;
        int escolha;

        do {
            System.out.println();

            System.out.println("Tipos:");
            System.out.println("1: Água doce;");
            System.out.println("2: Água salgada;");
            System.out.print("Tipo escolhido: ");
            escolha = scn.nextInt();

            if (escolha == 1) {
                tipo = TipoAgua.DOCE;
            }

            if (escolha == 2) {
                tipo = TipoAgua.SALGADA;
            }
        } while (escolha != 1 && escolha != 2);


        System.out.println();

        listarAquarios();

        System.out.print("Digite o id do aquário ao qual o peixe pertence: ");
        int idAquario = scn.nextInt();

        System.out.println();

        listarUsuarios();

        System.out.print("Digite o id do usuário ao qual o peixe pertence: ");
        int idUsuario = scn.nextInt();

        if (controlador.verifyUsuarioExistence(idUsuario) && controlador.verifyAquarioExistence(idAquario)) {
            Peixe novoPeixe = Peixe.getInstance(nome, tipo, idAquario, idUsuario);

            if (novoPeixe != null && controlador.add(novoPeixe)) {
                System.out.println("Peixe cadastrado!");
            } else {
                System.out.println("Falha ao cadastrar peixe!");
            }
        } else {
            System.out.println("Não foi possível cadastrar peixe!");
        }
    }

    public void switchAquario() {
        showAll();

        int codPeixe;
        do {
            System.out.print("Insira o código do peixe: ");
            codPeixe = scn.nextInt() - 1;
        } while (codPeixe < 0);

        Peixe verificando = controlador.getBySequence(codPeixe);
        if (verificando == null) {
            System.out.println("Peixe não encontrado.");
            return;
        }

        listarAquarios();

        System.out.print("Insira o id do aquário pra onde deseja transferir: ");
        int codAquario = scn.nextInt();

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

    public void update() {
        showAll();

        int cod;
        do {
            System.out.print("Código do peixe: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Peixe peixeAlterado = controlador.getBySequence(cod);
        if (peixeAlterado == null) {
            System.out.println("Peixe não encontrado.");
            return;
        }

        String nome = null;
        int escolha;
        do {
            System.out.println("Alterar nome: (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                System.out.print("Novo nome do peixe: ");
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

    public void delete() {
        showAll();
        int cod;
        do {
            System.out.print("Código do peixe: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Peixe peixeEncontrado = controlador.getBySequence(cod);
        if (peixeEncontrado == null) {
            System.out.println("Peixe não encontrado.");
            return;
        }

        Peixe peixeRecuperado = controlador.deleteById(peixeEncontrado.getIdPeixe());

        if (peixeRecuperado != null) {
            System.out.println("O peixe foi excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir peixe!");
        }
    }

    public void getById() {
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

    public void showAll() {
        System.out.println();
        System.out.println("Peixes:");
        System.out.println("Cod - Nome - AquarioID - Água");
        List<Peixe> peixes = controlador.getAll();
        for (int i = 0; i < peixes.size(); i++) {
            Peixe peixe = peixes.get(i);
            if (peixe.getTipoAgua() == TipoAgua.DOCE) {
                System.out.println((i + 1) + "  " + peixe.getNomePeixe() + "  " + peixe.getIdAquario() + "  AGUA DOCE");
            }
            if (peixe.getTipoAgua() == TipoAgua.SALGADA) {
                System.out.println((i + 1) + "  " + peixe.getNomePeixe() + "  " + peixe.getIdAquario() + "  AGUA SALGADA");
            }
        }
        System.out.println();
    }

    public void listarAquarios() {
        System.out.println();
        System.out.println("Aquários:");
        System.out.println("Cod - CodIdentif - Volume - Tipo - Dono");
        List<Aquario> aquarios = controlador.getAllAquarios();
        for (int i = 0; i < aquarios.size(); i++) {
            Aquario aquario = aquarios.get(i);
            String nomeUsuario = controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario());
            System.out.println(aquario.getId() + "  " + aquario.getCodigo() + "  " + aquario.getVolume() + "L  " + aquario.getTipo() + "  " + nomeUsuario);
        }
        System.out.println();
    }

    public void listarUsuarios() {
        System.out.println();
        System.out.println("Usuarios:");
        System.out.println("Cod - Nome - Tipo - Telefone");
        List<Usuario> usuarios = controlador.getAllUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getTipo() == TipoUsuario.PADRAO)
                System.out.println(usuarios.get(i).getId() + "  " + usuarios.get(i).getNome() + "  " + "PADRAO" + "  " + usuarios.get(i).getFone());
            else
                System.out.println(usuarios.get(i).getId() + "  " + usuarios.get(i).getNome() + "  " + "ADMIN" + "  " + usuarios.get(i).getFone());
        }
    }
}
