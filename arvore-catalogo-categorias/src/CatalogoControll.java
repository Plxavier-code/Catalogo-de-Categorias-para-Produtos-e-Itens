import java.util.List;
import java.util.ArrayList;

/**
 * Classe de controle principal do catálogo de categorias e produtos.
 * Responsável por gerenciar a hierarquia de categorias e suas operações.
 * Implementa as funcionalidades: Associação (Subcategoria e Produto) e Lógica
 * de Busca.
 * 
 * @author Paulo Vitor Dias Soares
 * @version 1.0
 * @since 2024
 */
public class CatalogoControll {

    // ATRIBUTOS

    /**
     * Raiz da árvore de categorias. Representa a categoria principal do catálogo.
     * Todas as outras categorias são descendentes desta.
     */
    private Categoria raiz;

    /**
     * Lista auxiliar de produtos cadastrados no sistema.
     * Mantém referência a todos os produtos para facilitar buscas e associações.
     */
    private List<Produto> produtosCadastrados;

    // CONSTRUTOR

    /**
     * Construtor padrão da classe CatalogoControll.
     * Inicializa a lista de produtos cadastrados.
     */
    public CatalogoControll() {
        this.produtosCadastrados = new ArrayList<>();
    }

    // MÉTODOS (PESSOA 1)

    public void inserirCategoria(String nome, String categoriaCodigo, String descricao) {
        Categoria novaCategoria = new Categoria(nome, categoriaCodigo, descricao);
        if (raiz == null) {
            raiz = novaCategoria;              // primeira categoria vira raiz
        } else {
            raiz.adicionarFilho(novaCategoria); // demais: filhas diretas da raiz
        }
    }

    public Produto inserirProduto(String nome, String produtoCodigo, double preco, String marca) {
        Produto novoProduto = new Produto(nome, produtoCodigo, preco, marca);
        produtosCadastrados.add(novoProduto);   // opcional: manter na lista auxiliar
        return novoProduto;
    }

    // MÉTODOS DE BUSCA

    private Categoria buscarCategoria(String nomeCategoria) {
        if (raiz == null) {
            return null;
        }
        return buscarCategoriaRecursivo(raiz, nomeCategoria);
    }

    private Categoria buscarCategoriaRecursivo(Categoria atual, String nome) {
        if (atual.getNome().equalsIgnoreCase(nome)) {
            return atual;
        }
        for (Categoria filho : atual.getFilhos()) {
            Categoria encontrada = buscarCategoriaRecursivo(filho, nome);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }

    // MÉTODOS DE ASSOCIAÇÃO

    public boolean definirSubcategoria(String nomePai, String nomeFilho) {
        Categoria pai = buscarCategoria(nomePai);
        if (pai == null) {
            System.out.println("Categoria pai '" + nomePai + "' não encontrada!");
            return false;
        }

        Categoria filho = buscarCategoria(nomeFilho);
        if (filho != null) {
            if (filho.getPai() != null) {
                filho.getPai().removerFilho(filho);
            }
        } else {
            filho = new Categoria(nomeFilho);
        }

        pai.adicionarFilho(filho);
        System.out.println(" Subcategoria '" + nomeFilho + "' definida como filha de '" + nomePai + "'");
        return true;
    }

    public boolean associarProduto(String nomeCategoria, Produto produto) {
        if (produto == null) {
            System.out.println(" Produto não pode ser nulo!");
            return false;
        }

        Categoria categoria = buscarCategoria(nomeCategoria);
        if (categoria == null) {
            System.out.println(" Categoria '" + nomeCategoria + "' não encontrada!");
            return false;
        }

        categoria.adicionarProduto(produto);
        produtosCadastrados.add(produto);
        System.out.println(" Produto '" + produto.getNome() + "' associado à categoria '" + nomeCategoria + "'");
        return true;
    }

    // MÉTODOS DE NAVEGAÇÃO E CONSULTA

    public String buscarCaminhoCompleto(String nomeCategoria) {
        Categoria categoria = buscarCategoria(nomeCategoria);
        String caminho;

        if (categoria == null) {
            caminho = "Categoria (" + nomeCategoria + ") não encontrada";
        } else {
            caminho = "Caminho completo: " + buscarCaminhoRec(categoria);
        }

        return caminho;
    }

    private String buscarCaminhoRec(Categoria categoria) {
        if (categoria.getPai() == null) {
            return categoria.getNome();
        }
        return buscarCaminhoRec(categoria.getPai()) + " > " + categoria.getNome();
    }

    // GETTERS E SETTERS

    public Categoria getRaiz() {
        return raiz;
    }

    public void setRaiz(Categoria raiz) {
        this.raiz = raiz;
    }

    public List<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }
}
