import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principal da aplicação em console.
 * Exibe o menu de operações do catálogo, lê as opções do usuário
 * e delega as ações para a classe {@link CatalogoControll}.
 *
 * @author Matheus Pereira Rodrigues
 * @author Pedro Lucas Dos Santos Xavier
 * @author Paulo Vitor Dias Soares
 * @author Sávio Neri Pessoa
 * @author Osmir Santos Meira 
 * @since 2025
 */
public class Main {

    /**
     * Ponto de entrada da aplicação.
     * Cria o controlador do catálogo, inicializa o Scanner e executa
     * o loop do menu até que o usuário escolha a opção de saída (0).
     *
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        CatalogoControll controle = new CatalogoControll();

        Scanner input = new Scanner(System.in);
        String menu = "\n0 - Sair\n"
                + "1 - Inserir Categoria\n"
                + "2 - Inserir Produto\n"
                + "3 - Definir Subcategoria (pai -> filho)\n"
                + "4 - Associar Produto à Categoria\n"
                + "5 - Buscar Categoria pelo Nome\n"
                + "6 - Remover Categoria\n"
                + "7 - Listar Árvore de Categorias\n"
                + "8 - Gerar Árvore de Navegação (folhas)\n"
                + "\nDigite a opção desejada:";

        String saida = "";
        int opcao = -1;

        do {
            System.out.println(menu);
            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida! Informe um número entre 0 e 8: ");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 0:
                    System.out.println("\n=== PROGRAMA ENCERRADO ===");
                    break;

                case 1:
                    System.out.println("\n=== Inserir Categoria ===");
                    System.out.println("Insira o nome da categoria: ");
                    String nome = input.nextLine();

                    System.out.println("Insira o código da categoria: ");
                    String codigo = input.nextLine();

                    System.out.println("Insira a descrição da categoria ");
                    String descricao = input.nextLine();

                    controle.inserirCategoria(nome, codigo, descricao);
                    System.out.println("Categoria inserida com sucesso");
                    break;

                case 2:
                    System.out.println("\n=== Inserir Produto ===");
                    System.out.println("Insira o nome do produto: ");
                    String nomeProduto = input.nextLine();

                    System.out.println("Insira o código do produto: ");
                    String codigoProduto = input.nextLine();
                    double preco;
                    while (true) {
                        try {
                            System.out.println("Insira o preço do produto");
                            preco = input.nextDouble();
                            if (preco < 0) {
                                System.out.println("ERRO: Entrada invalida");
                                continue;
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número (ex: 10.50).\n");
                            input.nextLine();
                        }
                    }
                    input.nextLine();
                    System.out.println("Insira a  marca do produto ");
                    String marca = input.nextLine();
                    controle.inserirProduto(nomeProduto, codigoProduto, preco, marca);
                    System.out.println("Produto inserido com sucesso");
                    break;

                case 3:
                    System.out.println("\nDEFINIR SUBCATEGORIA");
                    System.out.print("Nome da categoria PAI: ");
                    String nomePai = input.nextLine();
                    System.out.print("Nome da categoria FILHA: ");
                    String nomeFilho = input.nextLine();

                    boolean sucesso = controle.definirSubcategoria(nomePai, nomeFilho);
                    if (sucesso) {
                        System.out.println("Subcategoria definida com sucesso!");
                    } else {
                        System.out.println("Falha ao definir subcategoria.");
                    }

                    break;

                case 4:
                    System.out.println("\n=== ASSOCIAR PRODUTO À CATEGORIA ===");
                    System.out.print("Nome da categoria: ");
                    String nomeCategoriaProd = input.nextLine();
                    System.out.print("Nome do produto: ");
                    String nomeProd = input.nextLine();
                    System.out.print("Código do produto: ");
                    String codigoProd = input.nextLine();
                    System.out.print("Preço do produto: ");
                    double precoProduto = input.nextDouble();
                    input.nextLine(); // Limpar buffer
                    System.out.print("Marca do produto: ");
                    String marcaProduto = input.nextLine();

                    Produto produto = new Produto(nomeProd, codigoProd, precoProduto, marcaProduto);
                    boolean associado = controle.associarProduto(nomeCategoriaProd, produto);

                    if (associado) {
                        System.out.println("Produto associado com sucesso!");
                    } else {
                        System.out.println("Falha ao associar produto.");
                    }

                    break;

                case 5:
                    System.out.println("\nInforme o nome da categoria: ");
                    String nomeCategoria = input.nextLine();
                    saida = controle.buscarCaminhoCompleto(nomeCategoria);
                    System.out.println(saida);
                    break;

                case 6:

                    break;

                case 7:
                    System.out.println("\n=== Árvore de Categorias ===");
                    controle.listarArvore();
                    break;

                case 8:
                    System.out.println("\n=== Gerando Árvore de Navegação ===");
                    controle.gerarArvoresDeNavegacao();
                    break;
            }

        } while (opcao != 0);
        input.close();
    }

}
