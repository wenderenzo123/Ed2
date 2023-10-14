public class ProtocoloComunicacao {
    private Servidor servidor;

    public ProtocoloComunicacao(boolean tipoTabela) {
        servidor = new Servidor(tipoTabela);
    }

    public String cadastrarVeiculo(String veiculoComprimido, Huffman huffman) {
        return servidor.cadastrarVeiculo(veiculoComprimido, huffman);
    }

    public void listarVeiculos() {
        System.out.println(servidor.listarVeiculos());
    }

    public String alterarVeiculo(String placaVeiculoJson, Huffman huffman) {
        return servidor.alterarVeiculo(placaVeiculoJson, huffman);
    }

    public String removerVeiculo(String placaComprimida, Huffman huffman) {
        return servidor.removerVeiculo(placaComprimida, huffman);
    }

    public String quantidadeVeiculos(Huffman huffman) {
        return servidor.quantidadeVeiculos(huffman);
    }

    public String buscarVeiculo(String placaBuscaComprimida, Huffman huffman) {
        return servidor.BuscarVeiculoPorPlaca(placaBuscaComprimida, huffman);
    }
}
