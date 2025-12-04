import java.util.Scanner;

public class Main {
    public  static void main(String[] args) {
        CatalogoControll controle = new CatalogoControll();

        Scanner input = new Scanner(System.in);
        String menu = "\n0 - Sair\n"
                + "1 - Inserir Categoria\n"
                + "2 - Definir Subcategoria (pai -> filho)\n"
                + "3 - Buscar Categoria pelo Nome\n"
                + "4 - Remover Categoria\n"
                + "5 - Listar Árvore de Categorias\n"
                + "6 - Gerar Árvore de Navegação (folhas)\n"
                + "\nDigite a opção desejada:";

        String saida = "";
        int opcao = -1;

        do {
            System.out.println(menu);
            opcao = input.nextInt();
            input.nextLine();

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

            }

        } while (opcao != 0);
    }
}
