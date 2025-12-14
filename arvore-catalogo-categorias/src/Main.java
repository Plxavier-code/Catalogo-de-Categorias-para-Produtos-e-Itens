import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public  static void main(String[] args) {
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
                opcao=-1;
                continue;
            }

            switch (opcao) {
                case 0:
                    System.out.println("\n=== PROGRAMA ENCERRADO ===");
                    break;

                case 1:
                    System.out.println("\n=== Inserir Categoria ===");
                    System.out.println("Insira o nome da categoria: ");
                    String nome=input.nextLine();
                    
                    System.out.println("Insira o código da categoria: ");
                    String codigo=input.nextLine();
                    
                    System.out.println("Insira a descrição da categoria ");
                    String descricao=input.nextLine();
                
                    controle.inserirCategoria(nome, codigo, descricao);
                    System.out.println("Categoria inserida com sucesso");
                    break;

                case 2:
                    System.out.println("\n=== Inserir Produto ===");
                    System.out.println("Insira o nome do produto: ");
                    String nomeProduto=input.nextLine();
                
                    System.out.println("Insira o código do produto: ");
                    String codigoProduto=input.nextLine();
                     double preco;
                     while(true){
                        try{
                         System.out.println("Insira o preço do produto");
                          preco=input.nextDouble();
                          if(preco<0){
                            System.out.println("ERRO: Entrada invalida");
                            continue;
                          }
                          break;
                        }catch(InputMismatchException e){
                             System.out.println("Entrada inválida! Digite um número (ex: 10.50).\n");
                             input.nextLine();
                        }
                     }
                    input.nextLine();
                    System.out.println("Insira a  marca do produto ");
                    String marca=input.nextLine();
                    controle.inserirProduto(nomeProduto, codigoProduto, preco, marca);
                      System.out.println("Produto inserido com sucesso");
                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("\nInforme o nome da categoria: ");
                    String nomeCategoria = input.nextLine();
                    saida = controle.buscarCaminhoCompleto(nomeCategoria);
                    System.out.println(saida);
                    break;

                case 6:
                    
                    System.out.println("\n=== Remover Categoria ===");
                    System.out.println("Insira o nome da categoria a remover: ");
                    String nomeRemover = input.nextLine();

                    boolean sucessoRemocao = controle.removerCategoria(nomeRemover);

                    if (sucessoRemocao) {
                        System.out.println("Sucesso! A categoria '" + nomeRemover + "' e todas as suas subcategorias foram removidas.");
                    } else {
                        System.out.println("Falha ao remover a categoria.");
                    }
                    break;
                    

                case 7:
                    System.out.println("\n=== Árvore de Categorias ===");
                    controle.listarArvore();
                    break;

                case 8:

                    break;

            }

        } while (opcao != 0);

        input.close();
    }
    
}
