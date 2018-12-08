package br.com.ufms.compras.service;


import br.com.ufms.compras.entity.Categoria;
import br.com.ufms.compras.entity.Produto;
import br.com.ufms.compras.exception.ObjectNotFoundException;

import br.com.ufms.compras.repository.CategoriaRepository;
import br.com.ufms.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto buscarProdutoPorId(Long id) {

        Produto produto = produtoRepository.findOne(id);
        if (produto == null) {
            throw new ObjectNotFoundException("Objeto não encontrado, Id: " + id
                    + " Classe: " + Produto.class.getName());
        }

        return produto;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }



    public Produto insert(Produto produto, Long idCategoria) {
        Categoria categoria = categoriaRepository.findOne(idCategoria);
        produto.getCategorias().add(categoria);
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    public Produto update(Produto obj, Long idCategoria) {
        Categoria categoria = categoriaRepository.findOne(idCategoria);

        obj.getCategorias().add(categoria);
        return produtoRepository.save(obj);
    }

    public void delete(Long id) {
        buscarProdutoPorId(id);
        produtoRepository.delete(id);
    }


}
