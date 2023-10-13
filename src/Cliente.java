import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo de tabela:");
        System.out.println("1. Tabela de Dispersão com Endereçamento Aberto");
        System.out.println("2. Tabela de Dispersão Normal");
        System.out.print("Digite o número da opção: ");
        int escolhaTabela = scanner.nextInt();
        scanner.nextLine();

        boolean tipoTabela;
        if (escolhaTabela == 1) {
            tipoTabela = true; // Tabela de Dispersão com Endereçamento Aberto
        } else if (escolhaTabela == 2) {
            tipoTabela = false; // Tabela de Dispersão Normal
        } else {
            System.out.println("Opção inválida. Saindo do programa.");
            return;
        }

        ProtocoloComunicacao protocolo = new ProtocoloComunicacao(tipoTabela);

        while (true) {
            System.out.println("Menu de Opções:");
            System.out.println("1. Cadastrar veículo");
            System.out.println("2. Listar veículos");
            System.out.println("3. Alterar veículo");
            System.out.println("4. Remover veículo");
            System.out.println("5. Acessar a quantidade de veículos");
            System.out.println("6. Buscar veículo");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Veiculo veiculo = new Veiculo();
                    System.out.print("Placa: ");
                    byte[] placaComprimida = HuffmanCompressor.compress(scanner.nextLine());
                    System.out.print("Renavam: ");
                    byte[] renavamComprimido = HuffmanCompressor.compress(scanner.nextLine());
                    System.out.print("Nome do condutor: ");
                    veiculo.condutor = new Condutor();
                    byte[] nomeCondutorComprimido = HuffmanCompressor.compress(scanner.nextLine());
                    System.out.print("CPF do condutor: ");
                    byte[] cpfCondutorComprimido = HuffmanCompressor.compress(scanner.nextLine());
                    System.out.print("Modelo: ");
                    byte[] modeloComprimido = HuffmanCompressor.compress(scanner.nextLine());
                    System.out.print("Ano de fabricação: ");
                    veiculo.dataFabricacao = scanner.nextInt();
                    scanner.nextLine();
                    veiculo.placa = placaComprimida;
                    veiculo.renavam = renavamComprimido;
                    veiculo.condutor.nome = nomeCondutorComprimido;
                    veiculo.condutor.cpf = cpfCondutorComprimido;
                    veiculo.modelo = modeloComprimido;  
                    String respostaCadastro = protocolo.cadastrarVeiculo(veiculo);
                    System.out.println(respostaCadastro);
                    break;
                case 2:
                    protocolo.listarVeiculos();
                    break;
                case 3:
                    // System.out.print("Digite a placa do veículo que deseja alterar: ");
                    // String placaAlteracao = scanner.nextLine();
                    // Veiculo novoVeiculo = new Veiculo();
                    // System.out.print("Placa: ");
                    // novoVeiculo.placa = scanner.nextLine();
                    // System.out.print("Renavam: ");
                    // novoVeiculo.renavam = scanner.nextLine();
                    // System.out.print("Nome do condutor: ");
                    // novoVeiculo.condutor = new Condutor();
                    // novoVeiculo.condutor.nome = scanner.nextLine();
                    // System.out.print("CPF do condutor: ");
                    // novoVeiculo.condutor.cpf = scanner.nextLine();
                    // System.out.print("Modelo: ");
                    // novoVeiculo.modelo = scanner.nextLine();
                    // System.out.print("Ano de fabricação: ");
                    // novoVeiculo.dataFabricacao = scanner.nextInt();
                    // scanner.nextLine();
                    // String respostaAlteracao = protocolo.alterarVeiculo(placaAlteracao, novoVeiculo);
                    // System.out.println(respostaAlteracao);
                    break;
                case 4:
                    System.out.print("Digite a placa do veículo que deseja remover: ");
                    String placaRemocao = scanner.nextLine();
                    
                    String respostaRemocao = protocolo.removerVeiculo(placaRemocao);
                    System.out.println(respostaRemocao);
                    break;
                case 5:
                    int quantidade = protocolo.quantidadeVeiculos();
                    System.out.println("Quantidade de veículos: " + quantidade);
                    break;
                case 6:
                    System.out.print("Digite a placa do veículo que deseja buscar: ");
                    String placaBusca = scanner.nextLine();
                    Veiculo respostaBusca = protocolo.buscarVeiculo(placaBusca);
                    System.out.println(respostaBusca);
                    break;
                case 7:
                    System.out.println("Saindo do programa.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
