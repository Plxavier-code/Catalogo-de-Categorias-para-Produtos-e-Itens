import java.util.ArrayList;
import java.util.List;

/**
 * Classe de controle principal do catálogo de categorias e produtos.
 * Responsável por gerenciar a hierarquia de categorias e suas operações.
 * Implementa as funcionalidades: Associação (Subcategoria e Produto) e Lógica
 * de Busca.
 * 
 * 
 * @author Pedro Lucas Dos Santos Xavier
 * @author Paulo Vitor Dias Soares
 * @author Sávio Neri Pessoa 
 * @version 1.0
 * @since 2025
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
    // MÉTODOS DE INSERÇÃO
    
    /**
     * Insere uma nova categoria no catálogo.
     * A primeira categoria inserida se torna a raiz da árvore; as demais
     * são adicionadas como filhas diretas da raiz (categorias de nível 0).
     *
     * @param nome Nome da categoria a ser criada
     * @param categoriaCodigo Código identificador da categoria
     * @param descricao Descrição textual da categoria
     */

    public void inserirCategoria(String nome, String categoriaCodigo, String descricao) {
        Categoria novaCategoria = new Categoria(nome, categoriaCodigo, descricao);
        if (raiz == null) {
            raiz = novaCategoria; // primeira categoria vira raiz
        } else {
            raiz.adicionarFilho(novaCategoria); // demais: filhas diretas da raiz
        }
    }
    /**
     * Cria um novo produto no sistema.
     * O produto é armazenado na lista auxiliar de produtos cadastrados
     * e retornado para que possa ser associado a uma categoria em outro momento.
     *
     * @param nome Nome do produto
     * @param produtoCodigo Código identificador do produto
     * @param preco Preço do produto (deve ser não negativo)
     * @param marca Marca do produto
     * @return Instância de {@link Produto} criada com os dados informados
     */
    public Produto inserirProduto(String nome, String produtoCodigo, double preco, String marca) {
        Produto novoProduto = new Produto(nome, produtoCodigo, preco, marca);
        produtosCadastrados.add(novoProduto); // opcional: manter na lista auxiliar
        return novoProduto;
    }
    // MÉTODOS DE BUSCA

    /**
     * Busca uma categoria pelo nome em toda a árvore de categorias.
     * A comparação do nome é feita de forma case insensitive.
     *
     * @param nomeCategoria Nome da categoria a ser buscada
     * @return Categoria encontrada ou {@code null} se não existir
     */

    private Categoria buscarCategoria(String nomeCategoria) {
        if (raiz == null) {
            return null;
        }
        return buscarCategoriaRecursivo(raiz, nomeCategoria);
    }
/**
     * Método auxiliar recursivo para busca de categorias.
     * Percorre a árvore em profundidade a partir da categoria informada.
     *
     * @param atual Nó atual da árvore sendo visitado
     * @param nome Nome da categoria procurada
     * @return Categoria encontrada ou {@code null} se não estiver nessa subárvore
     */
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

    /**
     * Define uma categoria como subcategoria de outra.
     * Se a categoria filha já existir na árvore, ela é removida do pai atual
     * antes de ser associada ao novo pai.
     *
     * @param nomePai Nome da categoria que será o pai
     * @param nomeFilho Nome da categoria que será a subcategoria
     * @return {@code true} se a operação for bem-sucedida,
     *         {@code false} se o pai não for encontrado
     */

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
    /**
     * Associa um produto a uma categoria específica.
     * O produto é adicionado tanto à lista de produtos da categoria quanto
     * à lista auxiliar de produtos cadastrados no catálogo.
     *
     * @param nomeCategoria Nome da categoria onde o produto será associado
     * @param produto Produto previamente criado que será associado
     * @return {@code true} se a associação for bem-sucedida,
     *         {@code false} se a categoria não for encontrada ou o produto for nulo
     */

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
/**
     * Retorna o caminho completo de uma categoria na árvore.
     * O caminho é montado da raiz até a categoria, separando os nomes por " > ".
     *
     * @param nomeCategoria Nome da categoria cujo caminho será buscado
     * @return String com o caminho completo ou mensagem indicando que não foi encontrada
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
     * Método auxiliar recursivo que constrói o caminho da raiz até
     * a categoria informada.
     *
     * @param categoria Categoria atual no percurso da recursão
     * @return Caminho da raiz até a categoria atual
     */
    private String buscarCaminhoRec(Categoria categoria) {
        if (categoria.getPai() == null) {
            return categoria.getNome();
        }
        return buscarCaminhoRec(categoria.getPai()) + " > " + categoria.getNome();
    }

    /**
     * Método principal para iniciar a listagem da árvore hierárquica.
     * Chama o método recursivo a partir da raiz.
     * * @author Sávio Neri Pessoa
     */
    public void listarArvore() {
        if (raiz == null) {
            System.out.println("\n[AVISO] O catálogo está vazio (sem categorias).");
            return;
        }
        System.out.println("\n=== LISTAGEM DA ÁRVORE HIERÁRQUICA E PRODUTOS ===");
        exibirArvoreRecursivo(raiz);
        System.out.println("=================================================");
    }

    /**
     * Método auxiliar recursivo para percorrer e imprimir a árvore.
     * Exibe a categoria, percorre as subcategorias e depois lista os produtos
     * associados.
     * * @author Sávio Neri Pessoa
     * * @param atual A categoria sendo visitada no momento.
     */
    private void exibirArvoreRecursivo(Categoria atual) {
        // 1. Calcula a indentação baseada no nível da categoria na árvore
        // Usamos 4 espaços por nível conforme sugestão, ou poderia ser "|--"
        String indentacao = "    ".repeat(atual.getNivel());
        String prefixo = (atual.getNivel() == 0) ? "[Raiz] " : "|-- ";

        // 2. Exibe a Categoria
        System.out
                .println(indentacao + prefixo + "Categoria: " + atual.getNome() + " (Cód: " + atual.getCodigo() + ")");

        // 3. Percorre e exibe as subcategorias (recursividade)
        for (Categoria filho : atual.getFilhos()) {
            exibirArvoreRecursivo(filho);
        }

        // 4. Exibe a lista de produtos da categoria atual
        // Conforme PDF: "exibir a lista de produtos se ela existir, após listar as
        // subcategorias"
        if (!atual.getProdutos().isEmpty()) {
            for (Produto p : atual.getProdutos()) {
                System.out.println(indentacao + "    " + "-> [Produto] " + p.getNome()
                        + " | R$ " + String.format("%.2f", p.getPreco())
                        + " | Marca: " + p.getMarca());
            }
        }
    }

    /**
     * Implementa o requisito: Geração da Árvore de Navegação (Folhas).
     * Busca todos os caminhos que terminam em um nó folha.
     * @autor Osmir Santos Meira.
     */
    public void gerarArvoresDeNavegacao() {
        // Verifica se a árvore está vazia (raiz é null)
        if (this.raiz == null) {
            System.out.println("\n[AVISO] O catálogo está vazio. Nenhuma rota de navegação disponível.");
            return;
        }

        System.out.println("\n=== Árvore de Navegação (Rotas Finais) ===");
        // 1. Fazer uma travessia (recursiva) na árvore.
        percorrerParaGerarNavegacao(this.raiz);
        System.out.println("==========================================");
    }

    /**
     * Método auxiliar recursivo para percorrer a árvore.
     * @param atual A categoria sendo analisada no momento.
     * @autor Osmir Santos Meira.
     */
    private voiod percorrerParaGerarNavegacao(Categoria atual){
        // 2. Usar o método isFolha() da classe Categoria para identificar as categorias terminais.
        if (atual.isFolha()) {

            // 3. Para cada folha encontrada, chamar o método "buscarCaminhoCompleto"
            // O método espera uma String, então passamos atual.getNome()
            String caminho = buscarCaminhoCompleto(atual.getNome());

            // imprimir caminho
            System.out.println("Rota: " + caminho);
        } else {
            List<Categoria> filho = atual.getFilhos();
            if (filhos != null){
                for (Categoria filho : filhos){
                    percorrerParaGerarNavegacao(filho);
                }
            }
        }
    }

    // GETTERS E SETTERS
/**
     * Obtém a categoria raiz da árvore de categorias.
     *
     * @return Categoria que representa a raiz do catálogo
     */
    public Categoria getRaiz() {
        return raiz;
    }
/**
     * Define a categoria raiz da árvore de categorias.
     *
     * @param raiz Nova categoria raiz do catálogo
     */
    public void setRaiz(Categoria raiz) {
        this.raiz = raiz;
    }
    /**
     * Retorna a lista auxiliar com todos os produtos cadastrados no catálogo.
     *
     * @return Lista de produtos cadastrados
     */
    public List<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }
}
