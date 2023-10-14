public class Condutor {
    public String nome;
    public String cpf;

    public Condutor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public Condutor() {
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}