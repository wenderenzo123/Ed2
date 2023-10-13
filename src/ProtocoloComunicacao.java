public class ProtocoloComunicacao {
    private Servidor servidor;

    public ProtocoloComunicacao(boolean tipoTabela) {
        servidor = new Servidor(tipoTabela);
    }

    public String cadastrarVeiculo(Veiculo veiculo) {
        return servidor.cadastrarVeiculo(veiculo);
    }

    public void listarVeiculos() {
        System.out.println(servidor.listarVeiculos());
    }

    public String alterarVeiculo(String placa, Veiculo novoVeiculo) {
        return servidor.alterarVeiculo(placa, novoVeiculo);
    }

    public String removerVeiculo(String placa) {
        return servidor.removerVeiculo(placa);
    }

    public int quantidadeVeiculos() {
        return servidor.quantidadeVeiculos();
    }

    public Veiculo buscarVeiculo(String placaBusca) {
        return servidor.BuscarVeiculoPorPlaca(placaBusca);
    }
}
