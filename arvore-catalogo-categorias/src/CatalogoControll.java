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

    // MÉTODOS DE BUSCA

    /**
     * Busca uma categoria pelo nome em toda a árvore hierárquica.
     * Realiza uma busca em profundidade (DFS) recursiva.
     * 
     * @param nomeCategoria Nome da categoria a ser buscada (case insensitive)
     * @return A categoria encontrada ou null se não existir
     */
    private Categoria buscarCategoria(String nomeCategoria) {
        if (raiz == null) {
            return null;
        }
        return buscarCategoriaRecursivo(raiz, nomeCategoria);
    }

    /**
     * Método auxiliar recursivo para busca de categorias.
     * Percorre a árvore em profundidade comparando os nomes.
     * 
     * @param atual Categoria atual sendo analisada na recursão
     * @param nome  Nome da categoria sendo buscada
     * @return Categoria encontrada ou null
     */
    private Categoria buscarCategoriaRecursivo(Categoria atual, String nome) {
        // Verifica se a categoria atual é a procurada (case insensitive)
        if (atual.getNome().equalsIgnoreCase(nome)) {
            return atual;
        }

        // Busca recursivamente em todas as subcategorias
        for (Categoria filho : atual.getFilhos()) {
            Categoria encontrada = buscarCategoriaRecursivo(filho, nome);
            if (encontrada != null) {
                return encontrada;
            }
        }

        return null; // Categoria não encontrada nesta ramificação
    }

    // MÉTODOS DE ASSOCIAÇÃO

    /**
     * Define uma categoria como subcategoria de outra.
     * Se a categoria filho já existir, ela é removida de seu pai atual.
     * Se não existir, uma nova categoria é criada.
     * 
     * @param nomePai   Nome da categoria pai
     * @param nomeFilho Nome da categoria filho (subcategoria)
     * @return true se a operação foi bem-sucedida, false caso contrário
     * @throws IllegalStateException Se a categoria pai não for encontrada
     */
    public boolean definirSubcategoria(String nomePai, String nomeFilho) {
        // Busca a categoria pai
        Categoria pai = buscarCategoria(nomePai);

        if (pai == null) {
            System.out.println("Categoria pai '" + nomePai + "' não encontrada!");
            return false;
        }

        // Verifica se a categoria filho já existe
        Categoria filho = buscarCategoria(nomeFilho);

        if (filho != null) {
            // Se já existe, remove do pai atual (se tiver) para evitar duplicidade
            if (filho.getPai() != null) {
                filho.getPai().removerFilho(filho);
            }
        } else {
            // Cria nova categoria com nome apenas (código e descrição podem ser definidos
            // depois)
            filho = new Categoria(nomeFilho);
        }

        // Associa pai-filho usando o método da classe Categoria
        pai.adicionarFilho(filho);

        System.out.println(" Subcategoria '" + nomeFilho + "' definida como filha de '" + nomePai + "'");
        return true;
    }

    /**
     * Associa um produto a uma categoria específica.
     * Adiciona o produto à lista da categoria e à lista geral de produtos
     * cadastrados.
     * DEPENDÊNCIA: Requer que produtos sejam criados previamente (responsabilidade
     * da Pessoa 1).
     * 
     * @param nomeCategoria Nome da categoria onde o produto será associado
     * @param produto       Produto a ser associado à categoria (não pode ser null)
     * @return true se a associação foi bem-sucedida, false caso contrário
     * @throws IllegalArgumentException Se o produto for null
     * @throws IllegalStateException    Se a categoria não for encontrada
     */
    public boolean associarProduto(String nomeCategoria, Produto produto) {
        // Validação de parâmetros
        if (produto == null) {
            System.out.println(" Produto não pode ser nulo!");
            return false;
        }

        // Busca a categoria
        Categoria categoria = buscarCategoria(nomeCategoria);

        if (categoria == null) {
            System.out.println(" Categoria '" + nomeCategoria + "' não encontrada!");
            return false;
        }

        // Associa o produto à categoria
        categoria.adicionarProduto(produto);
        produtosCadastrados.add(produto); // Adiciona à lista auxiliar

        System.out.println(" Produto '" + produto.getNome() + "' associado à categoria '" + nomeCategoria + "'");
        return true;
    }

    // MÉTODOS DE NAVEGAÇÃO E CONSULTA

    /**
     * Busca o caminho completo de uma categoria desde a raiz.
     * Retorna uma string formatada com todas as categorias ancestrais.
     * Exemplo: "Eletrônicos > Computadores > Notebooks > Ultraleves"
     * 
     * @param nomeCategoria Nome da categoria para buscar o caminho
     * @return String com o caminho completo ou mensagem de erro
     */
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

    /**
     * Método auxiliar recursivo para construção do caminho hierárquico.
     * Percorre do nó atual até a raiz, construindo o caminho reversamente.
     * 
     * @param categoria Categoria atual no percurso recursivo
     * @return String do caminho da raiz até esta categoria
     */
    private String buscarCaminhoRec(Categoria categoria) {
        if (categoria.getPai() == null) {
            return categoria.getNome();
        }
        return buscarCaminhoRec(categoria.getPai()) + " > " + categoria.getNome();
    }

    // GETTERS E SETTERS

    /**
     * Obtém a raiz da árvore de categorias.
     * 
     * @return A categoria raiz
     */
    public Categoria getRaiz() {
        return raiz;
    }

    /**
     * Define a raiz da árvore de categorias.
     * Utilizado pela Pessoa 1 para inicializar o catálogo.
     * 
     * @param raiz Nova categoria raiz
     */
    public void setRaiz(Categoria raiz) {
        this.raiz = raiz;
    }

    /**
     * Obtém a lista de todos os produtos cadastrados no sistema.
     * 
     * @return Lista de produtos cadastrados
     */
    public List<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }

}