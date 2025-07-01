package prog2_projeto1.model;

public class Veiculo {
    private int id;
    private String nome;
    private int ano;
    private String modelo;
    private String cor;
    private String placa;
    private boolean unicoDono;
    private Categoria categoria;
 
    /**
     * @param id
     * @param nome
     * @param ano
     * @param modelo
     * @param cor
     * @param placa
     * @param unicoDono
     */
    public Veiculo(int id, String nome, int ano, String modelo, String cor, String placa, boolean unicoDono,
            Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.unicoDono = unicoDono;
        this.categoria = categoria;
    }

    /**
     * 
     */
    public Veiculo() {
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the unicoDono
     */
    public boolean isUnicoDono() {
        return unicoDono;
    }

    /**
     * @param unicoDono the unicoDono to set
     */
    public void setUnicoDono(boolean unicoDono) {
        this.unicoDono = unicoDono;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
