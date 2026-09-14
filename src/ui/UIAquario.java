package ui;

import business.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UIAquario {
    private final AquarioController controlador;
    private Scanner scn;
    private Scanner scl;

    public UIAquario() {
        controlador = new AquarioController();
        scn = new Scanner(System.in);
        scl = new Scanner(System.in);
    }

    public void add() {
        String codigo = "";
        do {
            System.out.println("Digite o código do aquário:");
            codigo = scl.nextLine();
        } while (codigo.isBlank());

        float volume = 0;
        do {
            System.out.println("Digite o volume do aquário:");
            volume = scn.nextFloat();
        } while (volume < 0);

        TipoAgua tipo = null;
        int opcao;
        do {
            System.out.println("Digite o tipo de água:");
            System.out.println("1 - DOCE");
            System.out.println("2 - SALGADA");
            opcao = scn.nextInt();
        } while (opcao != 1 && opcao != 2);

        if (opcao == 1)
            tipo = TipoAgua.DOCE;
        else
            tipo = TipoAgua.SALGADA;

        listarUsuarios();

        int idUsuario;
        do {
            System.out.print("Digite o id do usuário: ");
            idUsuario = scn.nextInt();
        } while (idUsuario < 0 || !controlador.verifyUsuarioExistence(idUsuario));

        Aquario aquario = Aquario.getInstance(codigo, volume, tipo, idUsuario);
        if (aquario != null && controlador.add(aquario)) {
            System.out.println("Aquário adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar aquário.");
        }
    }

    public void update() {
        listar();

        int cod;
        do {
            System.out.print("Código do aquário: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Aquario aquario = controlador.getBySequence(cod);
        if (aquario == null) {
            System.out.println("Aquário não encontrado.");
            return;
        }

        boolean alterado = false;
        int escolha;
        do {
            System.out.println("Alterar código: (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                String codigo = "";
                do {
                    System.out.println("Digite o novo código:");
                    codigo = scl.nextLine();
                } while (codigo.isBlank());

                aquario.setCodigo(codigo);
            }
        } while (escolha != 1 && escolha != 2);

        do {
            System.out.println("Alterar volume: (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                float volume = 0;
                do {
                    System.out.println("Digite o novo volume:");
                    volume = scn.nextFloat();
                } while (volume < 0);

                aquario.setVolume(volume);
            }
        } while (escolha != 1 && escolha != 2);

        do {
            System.out.println("Alterar tipo de água: (1) Sim, (2) Não");
            System.out.print("Escolha: ");
            escolha = scn.nextInt();
            if (escolha == 1) {
                alterado = true;
                TipoAgua tipo = null;
                int opcao;
                do {
                    System.out.println("Digite o novo tipo de água:");
                    System.out.println("1 - DOCE");
                    System.out.println("2 - SALGADA");
                    opcao = scn.nextInt();
                } while (opcao != 1 && opcao != 2);

                if (opcao == 1)
                    tipo = TipoAgua.DOCE;
                else
                    tipo = TipoAgua.SALGADA;

                aquario.setTipo(tipo);
            }
        } while (escolha != 1 && escolha != 2);

        if (!alterado) {
            System.out.println("Aquário sem alteraçoes.");
            return;
        }

        if (controlador.update(aquario)) {
            System.out.println("Aquário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar aquário.");
        }
    }

    public void deleteById() {
        listar();
        int cod;
        do {
            System.out.print("Código do aquário: ");
            cod = scn.nextInt() - 1;
        } while (cod < 0);

        Aquario aquarioEncontrado = controlador.getBySequence(cod);
        if (aquarioEncontrado == null) {
            System.out.println("Aquário não encontrado.");
            return;
        }

        Aquario aquario = controlador.deleteById(aquarioEncontrado.getId());
        if (aquario == null) {
            System.out.println("Aquário não encontrado ou possui peixes cadastrados.");
        } else {
            System.out.println("Aquário excluído com sucesso!");
        }
    }

    public void getById() {
        listarPeloId();

        int id;
        do {
            System.out.println("Digite o id do aquário:");
            id = scn.nextInt();
        } while (id < 0);

        Aquario aquario = controlador.getById(id);
        if (aquario == null) {
            System.out.println("Aquário não encontrado.");
        } else {
            System.out.println("Id: " + aquario.getId());
            System.out.println("Código: " + aquario.getCodigo());
            System.out.println("Volume: " + aquario.getVolume());
            System.out.println("Tipo de água: " + aquario.getTipo());
            System.out.println("Usuário: " + controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario()));
            if (aquario.getUltimaAlimentacao() != null) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                System.out.println("Ultima alimentação: " + aquario.getUltimaAlimentacao().format(fmt));
                System.out.println("Próxima alimentação: " + aquario.getProximaAlimentacao().format(fmt));

            } else {
                System.out.println("Ultima alimentação: não registrada");
                System.out.println("Próxima alimentação: não registrada");
            }
        }
    }

    public void listar() {
        System.out.println();
        System.out.println("Aquários:");

        UIConfig.cabecalhoAquario(false);

        List<Aquario> aquarios = controlador.getAll();
        for (int i = 0; i < aquarios.size(); i++) {
            Aquario aquario = aquarios.get(i);
            String nomeUsuario = controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario());

            UIConfig.listagemAquario((i + 1), aquario.getCodigo(), aquario.getVolume(), aquario.getTipo(), nomeUsuario);
        }
        System.out.println();
    }

    public void listarPeloId() {
        System.out.println();
        System.out.println("Aquários:");

        UIConfig.cabecalhoAquario(true);

        List<Aquario> aquarios = controlador.getAll();
        for (int i = 0; i < aquarios.size(); i++) {
            Aquario aquario = aquarios.get(i);
            String nomeUsuario = controlador.buscarNomeUsuarioPorId(aquario.getIdUsuario());
            UIConfig.listagemAquario(aquario.getId(), aquario.getCodigo(), aquario.getVolume(), aquario.getTipo(), nomeUsuario);
        }
        System.out.println();
    }

    public void listarUsuarios() {
        System.out.println();
        System.out.println("Usuarios:");

        UIConfig.cabecalhoUsuario(false);

        List<Usuario> usuarios = controlador.getAllUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getTipo() == TipoUsuario.PADRAO)
                UIConfig.listagemUsuario(usuarios.get(i).getId(), usuarios.get(i).getNome(), "PADRAO", usuarios.get(i).getFone());
            else
                UIConfig.listagemUsuario(usuarios.get(i).getId(), usuarios.get(i).getNome(), "LOJA", usuarios.get(i).getFone());
        }
    }

    public void alimentar() {
        listarPeloId();

        int id;
        do {
            System.out.println("Digite o id do aquário:");
            id = scn.nextInt();
        } while (id < 0);

        Aquario aquario = controlador.getById(id);
        if (aquario == null) {
            System.out.println("Aquário não encontrado.");
            return;
        }

        LocalDateTime horarioAtual = LocalDateTime.now().withNano(0);
        aquario.setUltimaAlimentacao(horarioAtual);

        if (controlador.update(aquario)) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("Alimentação registrada às: " + aquario.getUltimaAlimentacao().format(fmt));
            System.out.println("Próxima alimentação: " + aquario.getProximaAlimentacao().format(fmt));
        } else {
            System.out.println("Erro ao registrar alimentação.");
        }
    }

    public void mudarListagem() {
        UIConfig.setLarguraAquarioId(solicitarNovaLargura("Cod/Id", UIConfig.getLarguraAquarioId()));
        UIConfig.setLarguraAquarioCodigo(solicitarNovaLargura("CdIdentif", UIConfig.getLarguraAquarioCodigo()));
        UIConfig.setLarguraAquarioVolume(solicitarNovaLargura("Volume", UIConfig.getLarguraAquarioVolume()));
        UIConfig.setLarguraAquarioTipo(solicitarNovaLargura("Tipo", UIConfig.getLarguraAquarioTipo()));
        UIConfig.setLarguraAquarioDono(solicitarNovaLargura("Dono", UIConfig.getLarguraAquarioDono()));

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