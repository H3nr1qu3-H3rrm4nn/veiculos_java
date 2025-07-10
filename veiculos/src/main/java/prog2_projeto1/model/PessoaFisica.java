package prog2_projeto1.model;

import java.util.Date;

public class PessoaFisica extends EntidadeBase {

    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String telefone;
    private String email;

    public PessoaFisica() {
    }

    public PessoaFisica(int id, Date dataCadastro, String nome, String cpf, String rg, String endereco, String telefone,
            String email) {
        super(id, dataCadastro);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public PessoaFisica(String nome, String cpf, String rg, String endereco, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
