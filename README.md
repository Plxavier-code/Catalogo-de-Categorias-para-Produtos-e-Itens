📦 Catálogo de Categorias e Produtos

Um sistema robusto para gestão de catálogos baseado em uma Estrutura de Árvore N-ária. O projeto permite a organização hierárquica de categorias e a associação de produtos em qualquer nível da árvore.

📋 Sobre o Projeto

Este projeto foi desenvolvido como um trabalho em grupo, onde cada integrante ficou responsável por um módulo específico da lógica de negócios. O sistema roda via console e oferece um menu interativo para manipulação da estrutura de dados.

🚀 Funcionalidades Principais

Gestão de Categorias: Criação de raiz e subcategorias (Pai -> Filho).

Gestão de Produtos: Cadastro e associação de produtos a categorias específicas.

Busca e Navegação:

Busca de categoria por nome.

Visualização do caminho completo (breadcrumbs).

Geração de árvores de navegação (caminhos até as folhas).

Visualização: Listagem hierárquica completa (Árvore visual).

Remoção: Exclusão recursiva (remove a categoria e toda a sua subárvore).

🛠️ Estrutura do Projeto (Divisão de Tarefas)

O desenvolvimento foi modularizado conforme as responsabilidades abaixo:

Módulo

Responsabilidade

Descrição

Pessoa 1

Estrutura Base

Inicialização do catálogo, inserção básica de Categorias e Produtos.

Pessoa 2

Associação

Lógica de Pai -> Filho (Subcategorias) e associação de Produtos.

Pessoa 3

Busca de Caminho

Algoritmo para traçar o caminho da categoria até a raiz (Breadcrumbs).

Pessoa 4

Remoção

Lógica de remoção recursiva de nós e subárvores.

Pessoa 5

Listagem Hierárquica

Visualização completa da árvore e seus produtos formatados.

Pessoa 6

Navegação (Folhas)

Geração de todos os caminhos possíveis que levam a nós terminais.

💻 Como Executar

Pré-requisitos

Java JDK instalado (versão 8 ou superior).

Passo a Passo

Clone o repositório

git clone https://github.com/Plxavier-code/Catalogo-de-Categorias-para-Produtos-e-Itens.git


Compile o projeto
Navegue até a pasta src e compile os arquivos:

javac Main.java


Execute

java Main


🎨 Exemplo de Uso (Visualização)

Ao utilizar a opção 7 - Listar Árvore de Categorias, o sistema exibe a estrutura formatada:

=== LISTAGEM DA ÁRVORE HIERÁRQUICA E PRODUTOS ===

    |-> [Raiz] Categoria: Eletrônicos (Cód: 001)

    |-- Categoria: Computadores (Cód: 002)

    |-- Categoria: Notebooks (Cód: 003)

    |-> [Produto] MacBook Air | R$ 7500.00 | Marca: Apple

    |-> [Produto] Dell XPS | R$ 8900.00 | Marca: Dell

    |-- Categoria: Smartphones (Cód: 004)

    |-> [Produto] iPhone 15 | R$ 6000.00 | Marca: Apple

=================================================


📂 Estrutura de Classes

Main.java: Ponto de entrada. Gerencia o menu e a interação com o usuário.

CatalogoControll.java: O "cérebro" do sistema. Contém a lógica da árvore, buscas e manipulação de dados.

Categoria.java: Representa os nós da árvore. Contém listas de filhos (subcategorias) e produtos.

Produto.java: Objeto simples contendo preço, marca e código.

📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

<p align="center">
Desenvolvido com 💙 por Plxavier-code e Grupo
</p>