package br.com.ufms.compras.dto;


import br.com.ufms.compras.entity.Produto;

public class ProdutoDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double preco;
    private String categoria;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();

        for (int i = 0; i <produto.getCategorias().size() ; i++) {
            if (produto.getCategorias().get(i) != null){
                this.categoria = produto.getCategorias().get(i).getNome();
            }
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


}
