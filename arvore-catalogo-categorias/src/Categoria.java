import java.util.LinkedList;
import java.util.List;

public class Categoria {
    private String nome;
    private String codigo;
    private String descricao;
    private Categoria pai;
    private List<Categoria> filhos;
    private List<Produto> produtos;

    public Categoria(String nome) {
        this.nome = nome;
        this.filhos = new LinkedList<>();
        this.produtos = new LinkedList<>();
    }

    public Categoria(String nome, String codigo, String descricao) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.filhos = new LinkedList<>();
        this.produtos = new LinkedList<>();
    }

    // Adicionar filho
    public void adicionarFilho(Categoria filho) {
        filho.setPai(this);
        this.filhos.add(filho);
    }

    // Remover filho
    public void removerFilho(Categoria filho) {
        if (this.filhos.remove(filho)) {
            filho.setPai(null);
        }
    }

    // Adicionar um produto à categoria
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    // Remover um produto da categoria
    public void removerProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    // Verifica se é folha
    public boolean isFolha() {
        return this.filhos.isEmpty();
    }

    // Obtém o nível na árvore (0 para raiz, 1, 2, ...)
    public int getNivel() {
        if (this.pai == null) {
            return 0;
        }
        return this.pai.getNivel() + 1;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getPai() {
        return pai;
    }

    public void setPai(Categoria pai) {
        this.pai = pai;
    }

    public List<Categoria> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<Categoria> filhos) {
        this.filhos = filhos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
