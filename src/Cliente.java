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
        Huffman huffman = new Huffman();
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
                    veiculo.placa = scanner.nextLine();
                    System.out.print("Renavam: ");
                    veiculo.renavam = scanner.nextLine();
                    System.out.print("Nome do condutor: ");
                    veiculo.condutor = new Condutor();
                    veiculo.condutor.nome = scanner.nextLine();
                    System.out.print("CPF do condutor: ");
                    veiculo.condutor.cpf = scanner.nextLine();
                    System.out.print("Modelo: ");
                    veiculo.modelo = scanner.nextLine();
                    System.out.print("Ano de fabricação: ");
                    veiculo.dataFabricacao = scanner.nextInt();
                    scanner.nextLine();
                    String veiculoJson = veiculo.toJson();
                    String veiculoComprimido = huffman.compress(veiculoJson);
                    String respostaCadastro = protocolo.cadastrarVeiculo(veiculoComprimido, huffman);
                    System.out.println(respostaCadastro);
                    break;
                case 2:
                    protocolo.listarVeiculos();
                    break;
                case 3:
                    System.out.print("Digite a placa do veículo que deseja alterar: ");
                    String placaAlteracao = scanner.nextLine();
                    Veiculo novoVeiculo = new Veiculo();
                    System.out.print("Placa: ");
                    novoVeiculo.placa = scanner.nextLine();
                    System.out.print("Renavam: ");
                    novoVeiculo.renavam = scanner.nextLine();
                    System.out.print("Nome do condutor: ");
                    novoVeiculo.condutor = new Condutor();
                    novoVeiculo.condutor.nome = scanner.nextLine();
                    System.out.print("CPF do condutor: ");
                    novoVeiculo.condutor.cpf = scanner.nextLine();
                    System.out.print("Modelo: ");
                    novoVeiculo.modelo = scanner.nextLine();
                    System.out.print("Ano de fabricação: ");
                    novoVeiculo.dataFabricacao = scanner.nextInt();
                    scanner.nextLine();
                    String novoVeiculoJson = novoVeiculo.toJson();
                    String placaVeiculoJson = placaAlteracao + "#" + novoVeiculoJson;
                    String placaVeiculoJsonComprimido = huffman.compress(placaVeiculoJson);
                    String respostaAlteracao = protocolo.alterarVeiculo(placaVeiculoJsonComprimido, huffman);
                    System.out.println(respostaAlteracao);
                    break;
                case 4:
                    System.out.print("Digite a placa do veículo que deseja remover: ");
                    String placaRemocao = scanner.nextLine();
                    String placaRemoveComprimida = huffman.compress(placaRemocao);
                    String respostaRemocao = protocolo.removerVeiculo(placaRemoveComprimida, huffman);
                    System.out.println(respostaRemocao);
                    break;
                case 5:
                    String quantidade = protocolo.quantidadeVeiculos(huffman);
                    int quantidadeDescomprimida = Integer.parseInt(huffman.decompress(quantidade));
                    System.out.println("Quantidade de veículos: " + quantidadeDescomprimida);
                    break;
                case 6:
                    System.out.print("Digite a placa do veículo que deseja buscar: ");
                    String placaBusca = scanner.nextLine();
                    String placaBuscaComprimida = huffman.compress(placaBusca);
                    String respostaBusca = protocolo.buscarVeiculo(placaBuscaComprimida, huffman);
                    String respostaBuscaDescomprimida = huffman.decompress(respostaBusca);
                    Veiculo veiculoBuscado = Veiculo.fromJson(respostaBuscaDescomprimida);
                    System.out.println(veiculoBuscado);
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
