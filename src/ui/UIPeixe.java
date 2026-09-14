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
            System.out.println("1: AGUA DOCE");
            System.out.println("2: AGUA SALGADA");
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

        int idUsuario = controlador.getUserByAquarioId(idAquario);

        if (idUsuario == -1) {
            System.out.println("Aquario invalido.");
            return;
        }

        if (controlador.verifyUsuarioExistence(idUsuario) && controlador.verifyAquarioExistence(idAquario) && controlador.verifyTipoAgua(tipo, idAquario)) {
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
        boolean alterado = false;
        int escolha;
        do {
            System.out.println("Alterar nome: (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                System.out.print("Novo nome do peixe: ");
                nome = scl.nextLine();
            }
        } while (escolha != 1 && escolha != 2);

        if (!alterado) {
            System.out.println("Peixe sem alteraçoes.");
            return;
        }

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
        showAllWithId();
        int codigo;
        do {
            System.out.println("Id do peixe:");
            codigo = scn.nextInt();
        } while (codigo < 0);

        Peixe peixeEncontrado = controlador.getById(controlador.getBySequence(codigo).getIdPeixe());

        if (peixeEncontrado != null && peixeEncontrado.getTipoAgua() == TipoAgua.DOCE) {
            System.out.println();
            System.out.println("Nome: " + peixeEncontrado.getNomePeixe());
            System.out.println("Tipo de água: Doce");
            System.out.println("Id do aquário: " + peixeEncontrado.getIdAquario());
            System.out.println("Dono: " + controlador.buscarNomeUsuarioPorId(peixeEncontrado.getIdUsuario()));
        }

        if (peixeEncontrado != null && peixeEncontrado.getTipoAgua() == TipoAgua.SALGADA) {
            System.out.println();
            System.out.println("Nome: " + peixeEncontrado.getNomePeixe());
            System.out.println("Tipo de água: Salgada");
            System.out.println("Id do aquário: " + peixeEncontrado.getIdAquario());
            System.out.println("Dono: " + controlador.buscarNomeUsuarioPorId(peixeEncontrado.getIdUsuario()));
        }

        if (peixeEncontrado == null) {
            System.out.println();
            System.out.println("Peixe não encontrado.");
        }
    }

    public void showAll() {
        System.out.println();
        System.out.println("Peixes:");

        UIConfig.cabecalhoPeixe(false);

        List<Peixe> peixes = controlador.getAll();
        for (int i = 0; i < peixes.size(); i++) {
            Peixe peixe = peixes.get(i);
            if (peixe.getTipoAgua() == TipoAgua.DOCE) {
                UIConfig.listagemPeixe((i + 1), peixe.getNomePeixe(), "DOCE", controlador.buscarCodAquarioPorId(peixe.getIdAquario()), controlador.buscarNomeUsuarioPorId(peixe.getIdUsuario()));
            }
            if (peixe.getTipoAgua() == TipoAgua.SALGADA) {
                UIConfig.listagemPeixe((i + 1), peixe.getNomePeixe(), "SALGADA", controlador.buscarCodAquarioPorId(peixe.getIdAquario()), controlador.buscarNomeUsuarioPorId(peixe.getIdUsuario()));
            }
        }
        System.out.println();
    }

    public void showAllWithId() {
        System.out.println();
        System.out.println("Peixes:");

        UIConfig.cabecalhoPeixe(false);

        List<Peixe> peixes = controlador.getAll();
        for (int i = 0; i < peixes.size(); i++) {
            Peixe peixe = peixes.get(i);
            if (peixe.getTipoAgua() == TipoAgua.DOCE) {
                UIConfig.listagemPeixe(peixe.getIdPeixe(), peixe.getNomePeixe(), "DOCE", controlador.buscarCodAquarioPorId(peixe.getIdAquario()), controlador.buscarNomeUsuarioPorId(peixe.getIdUsuario()));
            }
            if (peixe.getTipoAgua() == TipoAgua.SALGADA) {
                UIConfig.listagemPeixe(peixe.getIdPeixe(), peixe.getNomePeixe(), "SALGADA", controlador.buscarCodAquarioPorId(peixe.getIdAquario()), controlador.buscarNomeUsuarioPorId(peixe.getIdUsuario()));
            }
        }
        System.out.println();
    }

    public void listarAquarios() {
        System.out.println();
        System.out.println("Aquários:");

        UIConfig.cabecalhoAquario(true);

        List<Aquario> aquarios = controlador.getAllAquarios();
        for (int i = 0; i < aquarios.size(); i++) {
            Aquario aquario = aquarios.get(i);
            String nomeUsuario = controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario());

            UIConfig.listagemAquario(aquario.getId(), aquario.getCodigo(), aquario.getVolume(), aquario.getTipo(), nomeUsuario);
        }
        System.out.println();
    }

    public void mudarListagem() {
        UIConfig.setLarguraPeixeId(solicitarNovaLargura("Cod/Id", UIConfig.getLarguraPeixeId()));
        UIConfig.setLarguraPeixeNome(solicitarNovaLargura("Nome", UIConfig.getLarguraPeixeNome()));
        UIConfig.setLarguraPeixeTipoAgua(solicitarNovaLargura("Tipo", UIConfig.getLarguraPeixeTipoAgua()));
        UIConfig.setLarguraPeixeIdAquario(solicitarNovaLargura("IdAquario", UIConfig.getLarguraPeixeIdAquario()));
        UIConfig.setLarguraPeixeDono(solicitarNovaLargura("Dono", UIConfig.getLarguraPeixeDono()));

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
