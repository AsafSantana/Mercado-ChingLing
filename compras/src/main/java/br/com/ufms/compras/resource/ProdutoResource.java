package br.com.ufms.compras.resource;

import br.com.ufms.compras.dto.ProdutoDTO;
import br.com.ufms.compras.entity.Produto;
import br.com.ufms.compras.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin("http://localhost:8080")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable("id") Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> list = produtoService.findAll();
        List<ProdutoDTO> listDto = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"idCategoria"})
    public ResponseEntity<Produto> insertProduto(@RequestBody Produto produto, @RequestParam ("idCategoria") Long idCategoria){
        return ResponseEntity.ok().body(produtoService.insert(produto, idCategoria));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody Produto produto, @RequestParam ("idCategoria") Long idCategoria){
        return ResponseEntity.ok().body(produtoService.update(produto, idCategoria));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){

        produtoService.delete(id);

        return  ResponseEntity.noContent().build();
    }


}
