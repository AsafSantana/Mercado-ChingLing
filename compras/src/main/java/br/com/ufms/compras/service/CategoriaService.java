package br.com.ufms.compras.service;

import br.com.ufms.compras.entity.Categoria;
import br.com.ufms.compras.exception.ObjectNotFoundException;
import br.com.ufms.compras.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findOne(id);

        if (categoria == null) {
            throw new ObjectNotFoundException("Objeto não encontrado, Id: " + id
                    + " Classe: " + Categoria.class.getName());
        }

        return categoria;
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj){
        buscarCategoriaPorId(obj.getId());
        return categoriaRepository.save(obj);
    }

    public  void delete(Long id){
        buscarCategoriaPorId(id);
        categoriaRepository.delete(id);
    }

}
