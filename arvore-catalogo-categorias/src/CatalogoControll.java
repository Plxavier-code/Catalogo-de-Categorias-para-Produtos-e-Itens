public class CatalogoControll {


    private Categoria buscarCategoria(String nomeCategoria) {
        throw new UnsupportedOperationException("Operação não implementada");
    }


    /**
     * Busca o caminho completo desde a raiz até uma categoria específica.
     *
     * @param nomeCategoria Nome da categoria procurada.
     * @return Uma string representando o caminho hierárquico usando " > ",
     *         ou uma mensagem informando que a categoria não foi encontrada.
     */
    public String buscarCaminhoCompleto(String nomeCategoria) {
        Categoria categoria = buscarCategoria(nomeCategoria);
        String caminho;

        if (categoria == null) {
            caminho = "Categoria (" +nomeCategoria+ ") não encontrada";
        } else {
            caminho = "Caminho completo: " +buscarCaminhoRec(categoria);
        }

        return caminho;
    }

    /**
     * Monta o caminho hierárquico recursivamente.
     *
     * @param categoria Categoria atual.
     * @return O caminho completo desde a raiz até a categoria.
     */
    private String buscarCaminhoRec(Categoria categoria) {
        if (categoria.getPai() == null) {
            return categoria.getNome();
        }
        return buscarCaminhoRec(categoria.getPai()) + " > " + categoria.getNome();
    }
}
