public class Produto {
    private String nome;
    private String codigo;
    private double preco;
    private String marca;
    //construtor 
    public Produto(String nome, String codigo, double preco, String marca){
        
        
        this.nome=nome;
        this.codigo=codigo;
        this.preco=preco;
        this.marca=marca;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
