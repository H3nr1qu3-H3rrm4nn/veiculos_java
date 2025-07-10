package prog2_projeto1.model;

import java.util.Date;

public class Veiculo extends EntidadeBase {

    private String nome;
    private int ano;
    private String modelo;
    private String cor;
    private String placa;
    private boolean unicoDono;
    private Categoria categoria;

    public Veiculo() {
    }

    public Veiculo(int id, Date dataCadastro, String nome, int ano, String modelo, String cor, String placa,
            boolean unicoDono, Categoria categoria) {
        super(id, dataCadastro);
        this.nome = nome;
        this.ano = ano;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.unicoDono = unicoDono;
        this.categoria = categoria;
    }
    public Veiculo(String nome, int ano, String modelo, String cor, String placa, boolean unicoDono,
            Categoria categoria) {
        this.nome = nome;
        this.ano = ano;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.unicoDono = unicoDono;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isUnicoDono() {
        return unicoDono;
    }

    public void setUnicoDono(boolean unicoDono) {
        this.unicoDono = unicoDono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " " + getModelo() + " (" + getPlaca() + ")";
    }
    
}
