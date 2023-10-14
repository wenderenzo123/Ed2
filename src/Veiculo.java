import com.google.gson.Gson;

public class Veiculo {
    public String placa;
    public String renavam;
    public Condutor condutor;
    public String modelo;
    public int dataFabricacao;

    public Veiculo(String placa, String renavam, String nomeCondutor, String cpfCondutor, String modelo, int dataFabricacao) {
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

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Veiculo fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Veiculo.class);
    }
}