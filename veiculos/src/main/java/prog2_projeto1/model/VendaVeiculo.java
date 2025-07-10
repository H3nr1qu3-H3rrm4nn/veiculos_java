package prog2_projeto1.model;

import java.time.LocalDate;
import java.util.Date;

public class VendaVeiculo extends EntidadeBase {

    private LocalDate dataVenda;
    private double valorDesconto;
    private double valorFinal;
    private Vendedor vendedor;
    private Cliente cliente;
    private Veiculo veiculo;

    public VendaVeiculo() {
    }

    public VendaVeiculo(int id, Date dataCadastro, LocalDate dataVenda, double valorDesconto, double valorFinal,
            Vendedor vendedor, Cliente cliente, Veiculo veiculo) {
        super(id, dataCadastro);
        this.dataVenda = dataVenda;
        this.valorDesconto = valorDesconto;
        this.valorFinal = valorFinal;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    
} 