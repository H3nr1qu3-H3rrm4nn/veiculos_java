package prog2_projeto1.model;

import java.util.Date;

public abstract class EntidadeBase {
    private int id;
    private Date dataCadastro;

    

    public EntidadeBase() {
    }

    public EntidadeBase(int id, Date dataCadastro) {
        this.id = id;
        this.dataCadastro = dataCadastro;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean valida_data(){
        Date dataAtual = new Date();
        if (dataCadastro.after(dataAtual)) {
            System.out.println("Data de cadastro inválida: não pode ser no futuro.");
            return false;
        }
        return true;
    }

    public boolean valida_id() {
        if (id <= 0) {
            System.out.println("ID inválido: deve ser maior que zero.");
            return false;
        }
        return true;
    }
    
}


