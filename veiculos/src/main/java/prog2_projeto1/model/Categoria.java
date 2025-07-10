package prog2_projeto1.model;

import java.util.Date;

public class Categoria extends EntidadeBase {
    private String nome;


    public Categoria() {
    }

    public Categoria(int id, Date dataCadastro, String nome) {
        super(id, dataCadastro);
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }

    

}
