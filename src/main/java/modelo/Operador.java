package modelo;

public class Operador { 
    private Integer codigo;
    private String login;
    private String senha;
    private static int count=0;

    public Operador() {
        this.login = " ";
        this.senha = " ";
        count ++;
        this.codigo = count;
    }
    
    public Operador(String login, String senha) {
        this.login = login;
        this.senha = senha;
        count ++;
        this.codigo = count;
    }
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
