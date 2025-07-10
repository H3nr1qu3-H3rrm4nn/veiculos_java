package prog2_projeto1.model;

import java.util.Date;

public class Cliente extends PessoaFisica {

    private String referencia_comercial;
    private Date data_nascimento;

    public Cliente() {
    }
    public Cliente(int id, Date dataCadastro, String nome, String cpf, String rg, String endereco, String telefone,
            String email, String referencia_comercial, Date data_nascimento) {
        super(id, dataCadastro, nome, cpf, rg, endereco, telefone, email);
        this.referencia_comercial = referencia_comercial;
        this.data_nascimento = data_nascimento;
    }
    
    public String getReferencia_comercial() {
        return referencia_comercial;
    }
    public void setReferencia_comercial(String referencia_comercial) {
        this.referencia_comercial = referencia_comercial;
    }
    public Date getData_nascimento() {
        return data_nascimento;
    }
    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " (CPF: " + getCpf() + ")";
    }
    
}
