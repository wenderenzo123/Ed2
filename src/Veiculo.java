public class Veiculo {
    public byte[] placa;
    public byte[] renavam;
    public Condutor condutor;
    public byte[] modelo;
    public int dataFabricacao;

    public Veiculo(byte[] placa, byte[] renavam, byte[] nomeCondutor, byte[] cpfCondutor, byte[] modelo, int dataFabricacao) {
        this.placa = placa;
        this.renavam = renavam;
        this.condutor = new Condutor();
        this.condutor.nome = nomeCondutor;
        this.condutor.cpf = cpfCondutor;
        this.modelo = modelo;
        this.dataFabricacao = dataFabricacao;
    }

    public Veiculo() {
    }

    public String toString() {
        return "Placa: " + placa + "\nRenavam: " + renavam + "\nNome do condutor: " + condutor.nome + "\nCPF do condutor: " + condutor.cpf + "\nModelo: " + modelo + "\nAno de fabricação: " + dataFabricacao;
    }

    public byte[] getPlaca() {
        return placa;
    }

    public byte[] getRenavam() {
        return renavam;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public byte[] getModelo() {
        return modelo;
    }

    public int getDataFabricacao() {
        return dataFabricacao;
    }

    public void setPlaca(byte[] placa) {
        this.placa = placa;
    }

    public void setRenavam(byte[] renavam) {
        this.renavam = renavam;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public void setModelo(byte[] modelo) {
        this.modelo = modelo;
    }

    public void setDataFabricacao(int dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getPlacaDescomprimida(HuffmanNode huffmanRoot) {
        return HuffmanCompressor.decompress(placa, huffmanRoot);
    }

    public void setPlaca(String decompress) {
        this.placa = HuffmanCompressor.compress(decompress);
    }
}