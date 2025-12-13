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
            raiz = novaCategoria; // primeira categoria vira raiz
        } else {
            raiz.adicionarFilho(novaCategoria); // demais: filhas diretas da raiz
        }
    }

    public Produto inserirProduto(String nome, String produtoCodigo, double preco, String marca) {
        Produto novoProduto = new Produto(nome, produtoCodigo, preco, marca);
        produtosCadastrados.add(novoProduto); // opcional: manter na lista auxiliar
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
     * @autor Osmir Santos Meira
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
     * @autor Osmir Santos Meira
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
