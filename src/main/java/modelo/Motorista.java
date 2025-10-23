package modelo;

public class Motorista {
    private String nome;
    private String endereco;
    private String cnh;
    private String setor;

    public Motorista() {
        this.nome = " ";
        this.endereco = " ";
        this.cnh = " ";
        this.setor = " ";
    }
    
    public Motorista(String nome, String endereco, String cnh, String setor) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnh = cnh;
        this.setor = setor;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
    
    
    
}
