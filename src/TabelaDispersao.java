import java.util.LinkedList;

public class TabelaDispersao {
    private LinkedList<Veiculo>[] tabela;
    private int tamanho;

    public TabelaDispersao(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public void inserir(Veiculo veiculo) {
        int indice = calcularIndice(veiculo.getPlacaDescomprimida(
            HuffmanCompressor.getHuffmanRoot()
        )); // Use a placa descomprimida para calcular o índice.
        tabela[indice].add(veiculo);
    }

    private int calcularIndice(String placa) {
        return placa.hashCode() % tamanho;
    }

    public Veiculo buscarPorPlaca(String placa) {
        int indice = calcularIndice(placa);
        for (Veiculo veiculo : tabela[indice]) {
            if (veiculo.getPlacaDescomprimida(
                HuffmanCompressor.getHuffmanRoot()
            ).equals(placa)) { // Use a placa descomprimida para fazer a comparação.
                return veiculo;
            }
        }
        System.out.println("Veículo não encontrado.");
        return null;
    }

    public boolean removerPorPlaca(String placa) {
        int indice = calcularIndice(placa);
        LinkedList<Veiculo> lista = tabela[indice];
        for (Veiculo veiculo : lista) {
            if (veiculo.getPlacaDescomprimida(HuffmanCompressor.getHuffmanRoot()).equals(placa)) { // Use a placa descomprimida para fazer a comparação.
                lista.remove(veiculo);
                return true;
            }
        }
        return false;
    }

    public LinkedList<Veiculo>[] getTabela() {
        return tabela;
    }

    public LinkedList<Veiculo> listarTodos() {
        LinkedList<Veiculo> todosVeiculos = new LinkedList<>();
        for (LinkedList<Veiculo> compartimento : tabela) {
            todosVeiculos.addAll(compartimento);
        }
        return todosVeiculos;
    }

    public int tamanho() {
        int tamanhoTotal = 0;
        for (LinkedList<Veiculo> compartimento : tabela) {
            tamanhoTotal += compartimento.size();
        }
        return tamanhoTotal;
    }

    public int quantidadeCompartimentos() {
        return tabela.length;
    }
}