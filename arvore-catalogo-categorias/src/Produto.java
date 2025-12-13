/**
 * Representa um produto do catálogo, com nome, código, preço e marca.
 * Esta classe é usada para armazenar as informações básicas de cada item.
 *
 * @author Matheus  Pereira  Rodrigues
 * @author Pedro Lucas Dos Santos Xavier
 * @since 2025
 */
public class Produto {
    private String nome;
    private String codigo;
    private double preco;
    private String marca;

    //construtor 

    /**
     * Cria uma nova instância de Produto com os dados informados.
     *
     * @param nome   Nome do produto
     * @param codigo Código identificador do produto
     * @param preco  Preço do produto
     * @param marca  Marca do produto
     */
    public Produto(String nome, String codigo, double preco, String marca){
        
        
        this.nome=nome;
        this.codigo=codigo;
        this.preco=preco;
        this.marca=marca;
    }

    //Getters e setters

    /**
     * Retorna o nome do produto.
     *
     * @return Nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome Novo nome do produto
     */ 
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o código do produto.
     *
     * @return Código do produto
     */
    public String getCodigo() {
        return codigo;
    }
 /**
     * Define o código do produto.
     *
     * @param codigo Novo código do produto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return Preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco Novo preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

     /**
     * Retorna a marca do produto.
     *
     * @return Marca do produto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do produto.
     *
     * @param marca Nova marca do produto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
}
