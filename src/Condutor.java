public class Condutor {
    public byte[] nome;
    public byte[] cpf;

    public Condutor(byte[] nome, byte[] cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public Condutor() {
    }
    public byte[] getCpf() {
        return cpf;
    }
    public byte[] getNome() {
        return nome;
    }
    public void setCpf(byte[] cpf) {
        this.cpf = cpf;
    }
    public void setNome(byte[] nome) {
        this.nome = nome;
    }
}