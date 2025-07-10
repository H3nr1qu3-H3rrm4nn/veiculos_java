package prog2_projeto1.model;

import java.util.Date;

public class Vendedor extends PessoaFisica {
    
    private double salario;
    private double comissao;

    public Vendedor() {
    }

    public Vendedor(int id, Date dataCadastro, String nome, String cpf, String rg, String endereco, String telefone,
            String email, double salario, double comissao) {
        super(id, dataCadastro, nome, cpf, rg, endereco, telefone, email);
        this.salario = salario;
        this.comissao = comissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " (CPF: " + getCpf() + ")";
    }
   

}
