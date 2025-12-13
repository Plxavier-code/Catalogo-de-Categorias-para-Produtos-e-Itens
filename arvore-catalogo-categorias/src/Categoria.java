import java.util.LinkedList;
import java.util.List;
/**
 * Representa uma categoria do catálogo, que pode conter subcategorias
 * e produtos associados, formando uma estrutura em árvore.
 *
 * Cada categoria conhece seu pai, seus filhos e a lista de produtos
 * diretamente associados a ela.
 *
 * @author Matheus Pereira Rodrigues 
 * @since 2025
 */

public class Categoria {
    private String nome;
    private String codigo;
    private String descricao;
    private Categoria pai;
    private List<Categoria> filhos;
    private List<Produto> produtos;
    
    //Construtor 

/**
     * Cria uma categoria apenas com o nome.
     * Usada, por exemplo, quando se deseja definir primeiro a hierarquia
     * e preencher código/descrição depois.
     *
     * @param nome Nome da categoria
     */
    public Categoria(String nome) {
        this.nome = nome;
        this.filhos = new LinkedList<>();
        this.produtos = new LinkedList<>();
    }

/**
     * Cria uma categoria com nome, código e descrição.
     *
     * @param nome      Nome da categoria
     * @param codigo    Código identificador da categoria
     * @param descricao Descrição textual da categoria
     */
    public Categoria(String nome, String codigo, String descricao) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.filhos = new LinkedList<>();
        this.produtos = new LinkedList<>();
    }
    //METODOS:

    /**
     * Cria uma categoria com nome, código e descrição.
     *
     * @param nome      Nome da categoria
     * @param codigo    Código identificador da categoria
     * @param descricao Descrição textual da categoria
     */
    public void adicionarFilho(Categoria filho) {
        filho.setPai(this);
        this.filhos.add(filho);
    }

     /**
     * Remove uma subcategoria (filho) desta categoria.
     * Caso a remoção seja bem-sucedida, o pai da categoria filha
     * é definido como {@code null}.
     *
     * @param filho Categoria filha que será removida
     */
    public void removerFilho(Categoria filho) {
        if (this.filhos.remove(filho)) {
            filho.setPai(null);
        }
    }

    /**
     * Adiciona um produto diretamente a esta categoria.
     *
     * @param produto Produto a ser associado à categoria
     */
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    /**
     * Remove um produto desta categoria.
     *
     * @param produto Produto a ser removido
     */
    public void removerProduto(Produto produto) {
        this.produtos.remove(produto);
    }

/**
     * Verifica se a categoria é uma folha na árvore,
     * ou seja, se não possui subcategorias.
     *
     * @return {@code true} se não houver filhos, {@code false} caso contrário
     */
    public boolean isFolha() {
        return this.filhos.isEmpty();
    }

    /**
     * Obtém o nível da categoria na árvore.
     * A raiz está no nível 0; seus filhos diretos no nível 1; e assim por diante.
     *
     * @return Nível da categoria na hierarquia
     */
    public int getNivel() {
        if (this.pai == null) {
            return 0;
        }
        return this.pai.getNivel() + 1;
    }

    //Getters e setters

    /**
     * Retorna o nome da categoria.
     *
     * @return Nome da categoria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome Novo nome da categoria
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o código da categoria.
     *
     * @return Código da categoria
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define o código da categoria.
     *
     * @param codigo Novo código da categoria
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna a descrição da categoria.
     *
     * @return Descrição da categoria
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da categoria.
     *
     * @param descricao Nova descrição da categoria
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a categoria pai (imediata) desta categoria.
     *
     * @return Categoria pai ou {@code null} se for raiz
     */ 
    public Categoria getPai() {
        return pai;
    }

    /**
     * Define a categoria pai desta categoria.
     *
     * @param pai Nova categoria pai
     */
    public void setPai(Categoria pai) {
        this.pai = pai;
    }

/**
     * Retorna a lista de subcategorias (filhos) desta categoria.
     *
     * @return Lista de categorias filhas
     */
    public List<Categoria> getFilhos() {
        return filhos;
    }

    /**
     * Substitui a lista de subcategorias desta categoria.
     *
     * @param filhos Nova lista de categorias filhas
     */
    public void setFilhos(List<Categoria> filhos) {
        this.filhos = filhos;
    }
    
/**
     * Retorna a lista de produtos associados diretamente a esta categoria.
     *
     * @return Lista de produtos da categoria
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Substitui a lista de produtos associados à categoria.
     *
     * @param produtos Nova lista de produtos da categoria
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
