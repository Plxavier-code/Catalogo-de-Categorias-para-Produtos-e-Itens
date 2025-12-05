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
                + "4 - Associas produto à categoria\n"
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

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

            }

        } while (opcao != 0);
    }
}
