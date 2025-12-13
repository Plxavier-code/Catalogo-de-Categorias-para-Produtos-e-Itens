public class CatalogoControll {
    private Categoria raiz;
    public void inserirCategoria(String nome, String CategoriaCodigo, String descricao){
        Categoria novaCategoria= new Categoria(nome, CategoriaCodigo, descricao);
        if (raiz== null){
        raiz= novaCategoria;
    }else{
        raiz.adicionarFilho(novaCategoria);
    }


    }
    public Produto inserirProduto(String nome, String produtoCodigo, double preco, String marca){
        Produto novoProduto= new Produto(nome, produtoCodigo, preco, marca);
        return novoProduto;
    }
}
