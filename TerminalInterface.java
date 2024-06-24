import java.util.Scanner;

public class TerminalInterface {
    private ControleDePilha controleDePilha;
    private Scanner scanner;

    public TerminalInterface() {
        controleDePilha = new ControleDePilha();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nSenhas do Consultório");
            System.out.println("1. Adicionar Senha");
            System.out.println("2. Chamar Senha");
            System.out.println("3. Atender Senha");
            System.out.println("4. Listar Senhas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir a nova linha

            switch (opcao) {
                case 1:
                    adicionarSenha();
                    break;
                case 2:
                    chamarSenha();
                    break;
                case 3:
                    atenderSenha();
                    break;
                case 4:
                    listarSenhas();
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarSenha() {
        System.out.println("Selecione o tipo de lista:");
        for (TipoLista tipo : TipoLista.values()) {
            System.out.println(tipo.ordinal() + 1 + ". " + tipo);
        }
        int tipoIndex = scanner.nextInt();
        scanner.nextLine(); // consumir a nova linha
        TipoLista tipoLista = TipoLista.values()[tipoIndex - 1];
        String resultado = controleDePilha.inserir(tipoLista);
        System.out.println(resultado);
    }

    private void chamarSenha() {
        String resultado = controleDePilha.chamar();
        if (resultado.equals("Já existe uma senha chamada que precisa ser atendida.")) {
            System.out.println("Não existe senhas cadastradas ou já existe uma senha chamada que precisa ser atendida.");
        } else {
            System.out.println(resultado);
        }
    }

    private void atenderSenha() {
        String resultado = controleDePilha.atender();
        if (resultado != null && !resultado.equals("Nenhuma senha chamada para ser atendida.")) {
            System.out.println(resultado);
        } else {
            System.out.println("Não existe senhas chamadas para atender.");
        }
    }

    private void listarSenhas() {
        System.out.println(controleDePilha.listar());
    }

    public static void main(String[] args) {
        TerminalInterface terminalInterface = new TerminalInterface();
        terminalInterface.run();
    }
}
